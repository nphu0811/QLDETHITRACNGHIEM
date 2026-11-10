-- =============================================================================
-- CÁC STORED PROCEDURE SAO LƯU, PHỤC HỒI VÀ XOÁ LỊCH SỬ (THITRACNGHIEM)
-- Đường dẫn file sao lưu mặc định: C:\SQLBackup\THITRACNGHIEM.bak
-- =============================================================================

-- -----------------------------------------------------------------------------
-- 1. SAO LƯU DỮ LIỆU (Chạy trên THITRACNGHIEM)
--    @reset = 0 -> WITH NOINIT (thêm bản sao lưu vào cuối file .bak)
--    @reset = 1 -> WITH INIT   (ghi đè các bản cũ trong file .bak)
-- -----------------------------------------------------------------------------
USE [THITRACNGHIEM];
GO

CREATE OR ALTER PROCEDURE dbo.SP_SaoLuuDuLieu
    @tep nvarchar(260) = N'C:\SQLBackup\THITRACNGHIEM.bak',
    @reset bit = 0
AS
BEGIN
    SET NOCOUNT ON;

    IF @reset = 1
        BACKUP DATABASE [THITRACNGHIEM]
        TO DISK = @tep
        WITH INIT, CHECKSUM, STATS = 10;
    ELSE
        BACKUP DATABASE [THITRACNGHIEM]
        TO DISK = @tep
        WITH NOINIT, CHECKSUM, STATS = 10;
END;
GO

-- -----------------------------------------------------------------------------
-- 2. ĐỌC DANH SÁCH BẢN SAO LƯU TRONG FILE .BAK VÀ PHỤC HỒI
--    - SP_DocHeaderSaoLuu: chạy RESTORE HEADERONLY để lấy BackupSetGUID, Position
--    - SP_PhucHoiDuLieu (ở master, WITH EXECUTE AS OWNER + ký chứng chỉ):
--      kiểm tra file bằng RESTORE VERIFYONLY, chuyển SINGLE_USER WITH ROLLBACK IMMEDIATE,
--      phục hồi WITH FILE = @viTri, REPLACE, RECOVERY, rồi đưa về MULTI_USER.
-- -----------------------------------------------------------------------------
USE [THITRACNGHIEM];
GO

CREATE OR ALTER PROCEDURE dbo.SP_DocHeaderSaoLuu
    @tep nvarchar(260) = N'C:\SQLBackup\THITRACNGHIEM.bak'
AS
BEGIN
    SET NOCOUNT ON;
    RESTORE HEADERONLY FROM DISK = @tep;
END;
GO

USE [master];
GO

CREATE OR ALTER PROCEDURE dbo.SP_PhucHoiDuLieu
    @tep nvarchar(260) = N'C:\SQLBackup\THITRACNGHIEM.bak',
    @viTri int
WITH EXECUTE AS OWNER
AS
BEGIN
    SET NOCOUNT ON;
    DECLARE @daChuyenCheDo bit = 0;

    BEGIN TRY
        RESTORE VERIFYONLY FROM DISK = @tep WITH FILE = @viTri;
        ALTER DATABASE [THITRACNGHIEM] SET SINGLE_USER WITH ROLLBACK IMMEDIATE;
        SET @daChuyenCheDo = 1;
        RESTORE DATABASE [THITRACNGHIEM] FROM DISK = @tep WITH FILE = @viTri, REPLACE, RECOVERY, STATS = 10;
        ALTER DATABASE [THITRACNGHIEM] SET MULTI_USER;
    END TRY
    BEGIN CATCH
        IF @daChuyenCheDo = 1 AND DATABASEPROPERTYEX(N'THITRACNGHIEM', 'Status') = N'ONLINE'
            ALTER DATABASE [THITRACNGHIEM] SET MULTI_USER WITH ROLLBACK IMMEDIATE;
        THROW;
    END CATCH;
END;
GO

-- -----------------------------------------------------------------------------
-- 3. ĐỔ BẢNG TỪ MSDB VÀ XOÁ LỊCH SỬ SAO LƯU
--    - SP_LayLichSuSaoLuu: lấy backup_set_uuid, position, backup_finish_date từ msdb
--    - SP_XoaLichSuSaoLuu: gọi msdb.dbo.sp_delete_database_backuphistory
-- -----------------------------------------------------------------------------
USE [THITRACNGHIEM];
GO

CREATE OR ALTER PROCEDURE dbo.SP_LayLichSuSaoLuu
    @tep nvarchar(260) = N'C:\SQLBackup\THITRACNGHIEM.bak'
AS
BEGIN
    SET NOCOUNT ON;

    SELECT s.backup_set_uuid, s.position, s.backup_finish_date
    FROM msdb.dbo.backupset AS s
    JOIN msdb.dbo.backupmediafamily AS f ON f.media_set_id = s.media_set_id
    WHERE s.database_name = N'THITRACNGHIEM'
      AND s.type = 'D'
      AND f.physical_device_name = @tep
    ORDER BY s.backup_finish_date DESC, s.backup_set_id DESC;
END;
GO

CREATE OR ALTER PROCEDURE dbo.SP_XoaLichSuSaoLuu
    @tenCSDL sysname = N'THITRACNGHIEM'
AS
BEGIN
    SET NOCOUNT ON;
    EXEC msdb.dbo.sp_delete_database_backuphistory @database_name = @tenCSDL;
END;
GO

-- -----------------------------------------------------------------------------
-- PHÂN QUYỀN CHO ROLE GiaoVien
-- -----------------------------------------------------------------------------
USE [THITRACNGHIEM];
GO
GRANT BACKUP DATABASE TO [GiaoVien];
GRANT EXECUTE ON OBJECT::dbo.SP_SaoLuuDuLieu TO [GiaoVien];
GRANT EXECUTE ON OBJECT::dbo.SP_DocHeaderSaoLuu TO [GiaoVien];
GRANT EXECUTE ON OBJECT::dbo.SP_LayLichSuSaoLuu TO [GiaoVien];
GRANT EXECUTE ON OBJECT::dbo.SP_XoaLichSuSaoLuu TO [GiaoVien];
GO

USE [msdb];
GO
IF DATABASE_PRINCIPAL_ID(N'GiaoVien') IS NULL
    CREATE ROLE [GiaoVien];
GRANT EXECUTE ON OBJECT::dbo.sp_delete_database_backuphistory TO [GiaoVien];
GO

USE [master];
GO
IF DATABASE_PRINCIPAL_ID(N'GiaoVien') IS NULL
    CREATE ROLE [GiaoVien];
GRANT CREATE DATABASE TO [GiaoVien];
GRANT EXECUTE ON OBJECT::dbo.SP_PhucHoiDuLieu TO [GiaoVien];

IF NOT EXISTS (SELECT 1 FROM sys.certificates WHERE name = N'TTN_SaoLuuPhucHoi_Cert')
    CREATE CERTIFICATE [TTN_SaoLuuPhucHoi_Cert]
        ENCRYPTION BY PASSWORD = N'TTN_SaoLuuPhucHoi#2026!'
        WITH SUBJECT = N'Chung chi phuc hoi CSDL THITRACNGHIEM';

IF NOT EXISTS (SELECT 1 FROM sys.server_principals WHERE name = N'TTN_SaoLuuPhucHoi_CertLogin')
    CREATE LOGIN [TTN_SaoLuuPhucHoi_CertLogin] FROM CERTIFICATE [TTN_SaoLuuPhucHoi_Cert];

GRANT CONTROL SERVER TO [TTN_SaoLuuPhucHoi_CertLogin];
ALTER SERVER ROLE [dbcreator] ADD MEMBER [TTN_SaoLuuPhucHoi_CertLogin];
ALTER SERVER ROLE [sysadmin] ADD MEMBER [TTN_SaoLuuPhucHoi_CertLogin];

IF EXISTS (SELECT 1 FROM sys.crypt_properties WHERE major_id = OBJECT_ID(N'dbo.SP_PhucHoiDuLieu'))
    DROP SIGNATURE FROM OBJECT::dbo.SP_PhucHoiDuLieu BY CERTIFICATE [TTN_SaoLuuPhucHoi_Cert];

ADD SIGNATURE TO OBJECT::dbo.SP_PhucHoiDuLieu
    BY CERTIFICATE [TTN_SaoLuuPhucHoi_Cert]
    WITH PASSWORD = N'TTN_SaoLuuPhucHoi#2026!';
GO
