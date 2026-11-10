-- Chạy bằng sysadmin sau khi đã ký các SP quản lý tài khoản giáo viên.
USE [THITRACNGHIEM];
GO

SET XACT_ABORT ON;

BEGIN TRY
    BEGIN TRAN;

    EXEC sys.sp_executesql N'
CREATE OR ALTER PROCEDURE dbo.SP_DoiMatKhauQuanLy
    @MatKhauMoi nvarchar(4000)
AS
BEGIN
    SET NOCOUNT ON;

    IF @MatKhauMoi IS NULL OR DATALENGTH(@MatKhauMoi) NOT BETWEEN 2 AND 256
        THROW 51111, N''Mật khẩu không hợp lệ.'', 1;

    IF SUSER_SID() IS NULL OR SUSER_SID() <> SUSER_SID(ORIGINAL_LOGIN())
        THROW 51112, N''Hãy kết nối bằng tài khoản cần đổi mật khẩu.'', 1;

    -- sp_password mặc định chọn login của kết nối hiện tại.
    DECLARE @KetQua int;
    EXEC @KetQua = sys.sp_password @new = @MatKhauMoi;
    IF @KetQua <> 0
        THROW 51117, N''Đổi mật khẩu thất bại.'', 1;
END;';

    ADD SIGNATURE TO dbo.SP_DoiMatKhauQuanLy BY CERTIFICATE [TTN_QuanLyTaiKhoanGV_Cert];
    GRANT EXECUTE ON OBJECT::dbo.SP_DoiMatKhauQuanLy TO [GiaoVien];

    COMMIT;
END TRY
BEGIN CATCH
    IF XACT_STATE() <> 0 ROLLBACK;
    THROW;
END CATCH;
GO
