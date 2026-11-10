-- Chạy toàn bộ file trên THITRACNGHIEM sau migration 20261106.
-- I = thêm, U = hiệu chỉnh, D = xóa, R = phục hồi thao tác đã ghi.
USE [THITRACNGHIEM];
GO

IF TYPE_ID(N'dbo.DongLopChoGhi') IS NULL
    EXEC(N'CREATE TYPE dbo.DongLopChoGhi AS TABLE (
        MALOP nvarchar(15) PRIMARY KEY, TENLOP nvarchar(50) NULL
    )');

IF TYPE_ID(N'dbo.DongSinhVienChoGhi') IS NULL
    EXEC(N'CREATE TYPE dbo.DongSinhVienChoGhi AS TABLE (
        MASV nvarchar(8) PRIMARY KEY, HO nvarchar(50) NULL, TEN nvarchar(10) NULL,
        NGAYSINH date NULL, DIACHI nvarchar(100) NULL, MALOP nvarchar(15) NULL
    )');

IF TYPE_ID(N'dbo.DongGiaoVienChoGhi') IS NULL
    EXEC(N'CREATE TYPE dbo.DongGiaoVienChoGhi AS TABLE (
        MAGV nvarchar(8) PRIMARY KEY, HO nvarchar(50) NULL, TEN nvarchar(10) NULL,
        SODTLL nchar(15) NULL, DIACHI nvarchar(50) NULL
    )');

IF TYPE_ID(N'dbo.DongMonHocChoGhi') IS NULL
    EXEC(N'CREATE TYPE dbo.DongMonHocChoGhi AS TABLE (
        MAMH nvarchar(5) PRIMARY KEY, TENMH nvarchar(50) NULL, TENCU nvarchar(50) NULL
    )');

IF TYPE_ID(N'dbo.DongCauHoiChoGhi') IS NULL
    EXEC(N'CREATE TYPE dbo.DongCauHoiChoGhi AS TABLE (
        STT int PRIMARY KEY, CAUHOI int NULL, MAMH nvarchar(5) NULL, TRINHDO char(1) NULL,
        NOIDUNG nvarchar(500) NULL, A nvarchar(200) NULL, B nvarchar(200) NULL,
        C nvarchar(200) NULL, D nvarchar(200) NULL, DAP_AN char(1) NULL, MAGV nvarchar(8) NULL
    )');

IF TYPE_ID(N'dbo.DongCaThiChoGhi') IS NULL
    EXEC(N'CREATE TYPE dbo.DongCaThiChoGhi AS TABLE (
        MALOP nvarchar(15), MAMH nvarchar(5), LAN smallint,
        TRINHDO char(1) NULL, NGAYTHI datetime NULL, SOCAUTHI smallint NULL,
        THOIGIAN smallint NULL, MAGV nvarchar(8) NULL, PRIMARY KEY (MALOP, MAMH, LAN)
    )');

IF OBJECT_ID(N'dbo.LichSuQuanLy', N'U') IS NULL
BEGIN
    CREATE TABLE dbo.LichSuQuanLy (
        Ma bigint IDENTITY PRIMARY KEY,
        TaiKhoan varbinary(85) NOT NULL,
        Nhom varchar(20) NOT NULL,
        PhamVi nvarchar(15) NOT NULL, -- Mã lớp đối với sinh viên.
        ThaoTac char(1) NOT NULL CHECK (ThaoTac IN ('I', 'U', 'D')),
        Truoc nvarchar(max) NOT NULL,
        Sau nvarchar(max) NOT NULL,
        DaPhucHoi bit NOT NULL DEFAULT 0
    );
    CREATE INDEX IX_LichSuQuanLy_PhucHoi
        ON dbo.LichSuQuanLy (TaiKhoan, Nhom, PhamVi, DaPhucHoi, Ma DESC);
END;
GO

-- Các SP gọi thủ tục này sau BEGIN TRAN để xếp thứ tự Ghi, Xóa và Phục hồi.
CREATE OR ALTER PROCEDURE dbo.SP_KhoaGhiQuanLy
AS
BEGIN
    DECLARE @Khoa int;
    EXEC @Khoa = sys.sp_getapplock @Resource = N'GhiDanhMucQuanLy',
        @LockMode = 'Exclusive', @LockOwner = 'Transaction', @LockTimeout = 10000;
    IF @Khoa < 0 THROW 51120, N'Dữ liệu quản lý đang được ghi. Vui lòng thử lại.', 1;
END;
GO

CREATE OR ALTER PROCEDURE dbo.SP_DocLanPhucHoiQuanLy
    @Nhom varchar(20), @PhamVi nvarchar(15),
    @Ma bigint OUTPUT, @Lenh char(1) OUTPUT, @DuLieu nvarchar(max) OUTPUT, @Sau nvarchar(max) OUTPUT
AS
BEGIN
    SELECT TOP (1) @Ma = Ma,
        @Lenh = CASE ThaoTac WHEN 'I' THEN 'D' WHEN 'D' THEN 'I' ELSE 'U' END,
        @DuLieu = CASE ThaoTac WHEN 'I' THEN Sau ELSE Truoc END,
        @Sau = Sau
    FROM dbo.LichSuQuanLy
    WHERE TaiKhoan = SUSER_SID(ORIGINAL_LOGIN()) AND Nhom = @Nhom AND PhamVi = @PhamVi AND DaPhucHoi = 0
    ORDER BY Ma DESC;

    IF @Ma IS NULL THROW 51121, N'Chưa có thao tác nào để phục hồi ở bảng này.', 1;
END;
GO

ALTER PROCEDURE dbo.SP_GhiLop
    @ThaoTac char(1), @MaLop nvarchar(15) = NULL, @TenLop nvarchar(50) = NULL,
    @DanhSach dbo.DongLopChoGhi READONLY
AS
BEGIN
    SET NOCOUNT ON;
    SET XACT_ABORT ON;
    IF @ThaoTac IS NULL OR @ThaoTac NOT IN ('I', 'U', 'D', 'R')
        THROW 51100, N'Thao tác không hợp lệ.', 1;

    DECLARE @Dong dbo.DongLopChoGhi;
    DECLARE @Lenh char(1) = @ThaoTac, @MaLichSu bigint, @DuLieu nvarchar(max), @Sau nvarchar(max);
    BEGIN TRY
        BEGIN TRANSACTION;
        EXEC dbo.SP_KhoaGhiQuanLy;

        IF @ThaoTac = 'R'
        BEGIN
            EXEC dbo.SP_DocLanPhucHoiQuanLy 'LOP', N'',
                @MaLichSu OUTPUT, @Lenh OUTPUT, @DuLieu OUTPUT, @Sau OUTPUT;
            INSERT @Dong (MALOP, TENLOP)
            SELECT MALOP, TENLOP FROM OPENJSON(@DuLieu)
                WITH (MALOP nvarchar(15), TENLOP nvarchar(50));
        END
        ELSE IF @ThaoTac IN ('I', 'U') AND EXISTS (SELECT 1 FROM @DanhSach)
            INSERT @Dong SELECT * FROM @DanhSach;
        ELSE
            INSERT @Dong VALUES (@MaLop, @TenLop);

        DECLARE @SoDong int = (SELECT COUNT(*) FROM @Dong);
        DECLARE @Truoc nvarchar(max) = ISNULL((
            SELECT lop.* FROM dbo.LOP lop WITH (UPDLOCK, HOLDLOCK)
            JOIN @Dong d ON d.MALOP = lop.MALOP
            ORDER BY lop.MALOP FOR JSON PATH, INCLUDE_NULL_VALUES
        ), N'[]');
        IF @ThaoTac = 'R' AND CONVERT(varbinary(max), @Truoc) <> CONVERT(varbinary(max), @Sau)
            THROW 51122, N'Dữ liệu lớp đã thay đổi kể từ lần ghi. Không thể phục hồi đợt này.', 1;

        IF @Lenh = 'I'
            INSERT dbo.LOP (MALOP, TENLOP) SELECT MALOP, TENLOP FROM @Dong;
        ELSE IF @Lenh = 'U'
        BEGIN
            UPDATE lop SET TENLOP = d.TENLOP
            FROM dbo.LOP lop JOIN @Dong d ON d.MALOP = lop.MALOP;
            IF @@ROWCOUNT <> @SoDong THROW 51101, N'Có lớp không còn tồn tại.', 1;
        END
        ELSE
        BEGIN
            DELETE lop FROM dbo.LOP lop JOIN @Dong d ON d.MALOP = lop.MALOP;
            IF @@ROWCOUNT <> @SoDong THROW 51101, N'Có lớp không còn tồn tại.', 1;
        END;

        IF @ThaoTac = 'R'
            UPDATE dbo.LichSuQuanLy SET DaPhucHoi = 1 WHERE Ma = @MaLichSu;
        ELSE
        BEGIN
            SET @Sau = ISNULL((
                SELECT lop.* FROM dbo.LOP lop JOIN @Dong d ON d.MALOP = lop.MALOP
                ORDER BY lop.MALOP FOR JSON PATH, INCLUDE_NULL_VALUES
            ), N'[]');
            INSERT dbo.LichSuQuanLy (TaiKhoan, Nhom, PhamVi, ThaoTac, Truoc, Sau)
            VALUES (SUSER_SID(ORIGINAL_LOGIN()), 'LOP', N'', @ThaoTac, @Truoc, @Sau);
        END;
        COMMIT;
    END TRY
    BEGIN CATCH
        IF XACT_STATE() <> 0 ROLLBACK;
        THROW;
    END CATCH;
END;
GO

ALTER PROCEDURE dbo.SP_GhiSinhVien
    @ThaoTac char(1), @MaSV nvarchar(8) = NULL, @MaLop nvarchar(15) = NULL,
    @Ho nvarchar(50) = NULL, @Ten nvarchar(10) = NULL,
    @NgaySinh date = NULL, @DiaChi nvarchar(100) = NULL,
    @DanhSach dbo.DongSinhVienChoGhi READONLY
AS
BEGIN
    SET NOCOUNT ON;
    SET XACT_ABORT ON;
    IF @ThaoTac IS NULL OR @ThaoTac NOT IN ('I', 'U', 'D', 'R')
        THROW 51100, N'Thao tác không hợp lệ.', 1;

    DECLARE @Dong dbo.DongSinhVienChoGhi;
    DECLARE @Lenh char(1) = @ThaoTac, @MaLichSu bigint, @DuLieu nvarchar(max), @Sau nvarchar(max);
    BEGIN TRY
        BEGIN TRANSACTION;
        EXEC dbo.SP_KhoaGhiQuanLy;

        IF @ThaoTac = 'R'
        BEGIN
            EXEC dbo.SP_DocLanPhucHoiQuanLy 'SINHVIEN', @MaLop,
                @MaLichSu OUTPUT, @Lenh OUTPUT, @DuLieu OUTPUT, @Sau OUTPUT;
            INSERT @Dong (MASV, HO, TEN, NGAYSINH, DIACHI, MALOP)
            SELECT MASV, HO, TEN, NGAYSINH, DIACHI, MALOP FROM OPENJSON(@DuLieu)
                WITH (MASV nvarchar(8), HO nvarchar(50), TEN nvarchar(10),
                      NGAYSINH date, DIACHI nvarchar(100), MALOP nvarchar(15));
        END
        ELSE IF @ThaoTac IN ('I', 'U') AND EXISTS (SELECT 1 FROM @DanhSach)
        BEGIN
            INSERT @Dong SELECT * FROM @DanhSach;
            SELECT @MaLop = MIN(MALOP) FROM @Dong;
            IF @MaLop IS NULL OR EXISTS (SELECT 1 FROM @Dong WHERE MALOP IS NULL OR MALOP <> @MaLop)
                THROW 51123, N'Mỗi đợt ghi sinh viên phải thuộc cùng một lớp.', 1;
        END
        ELSE
            INSERT @Dong VALUES (@MaSV, @Ho, @Ten, @NgaySinh, @DiaChi, @MaLop);

        IF @Lenh = 'D'
        BEGIN
            -- Cùng khóa với SP_MoBaiThi để sinh viên không bắt đầu bài thi trong lúc bị xóa.
            DECLARE @Khoa int, @TenKhoa nvarchar(255), @MaCanKhoa nvarchar(8);
            DECLARE sinhVienCanKhoa CURSOR LOCAL FAST_FORWARD FOR SELECT MASV FROM @Dong ORDER BY MASV;
            OPEN sinhVienCanKhoa;
            FETCH NEXT FROM sinhVienCanKhoa INTO @MaCanKhoa;
            WHILE @@FETCH_STATUS = 0
            BEGIN
                SET @TenKhoa = N'BaiThi:' + RTRIM(@MaCanKhoa);
                EXEC @Khoa = sys.sp_getapplock @Resource = @TenKhoa,
                    @LockMode = 'Exclusive', @LockOwner = 'Transaction', @LockTimeout = 10000;
                IF @Khoa < 0 THROW 51113, N'Bài thi đang được xử lý. Vui lòng thử lại.', 1;
                FETCH NEXT FROM sinhVienCanKhoa INTO @MaCanKhoa;
            END;
            CLOSE sinhVienCanKhoa;
            DEALLOCATE sinhVienCanKhoa;
        END;

        DECLARE @SoDong int = (SELECT COUNT(*) FROM @Dong);
        DECLARE @Truoc nvarchar(max) = ISNULL((
            SELECT sv.* FROM dbo.SINHVIEN sv WITH (UPDLOCK, HOLDLOCK)
            JOIN @Dong d ON d.MASV = sv.MASV
            ORDER BY sv.MASV FOR JSON PATH, INCLUDE_NULL_VALUES
        ), N'[]');
        IF @ThaoTac = 'R' AND CONVERT(varbinary(max), @Truoc) <> CONVERT(varbinary(max), @Sau)
            THROW 51122, N'Dữ liệu sinh viên đã thay đổi kể từ lần ghi. Không thể phục hồi đợt này.', 1;

        IF @Lenh = 'I'
            INSERT dbo.SINHVIEN (MASV, HO, TEN, NGAYSINH, DIACHI, MALOP)
            SELECT MASV, HO, TEN, NGAYSINH, DIACHI, MALOP FROM @Dong;
        ELSE IF @Lenh = 'U'
        BEGIN
            UPDATE sv SET HO = d.HO, TEN = d.TEN, NGAYSINH = d.NGAYSINH, DIACHI = d.DIACHI
            FROM dbo.SINHVIEN sv JOIN @Dong d ON d.MASV = sv.MASV AND d.MALOP = sv.MALOP;
            IF @@ROWCOUNT <> @SoDong THROW 51102, N'Có sinh viên không còn trong lớp đang chọn.', 1;
        END
        ELSE
        BEGIN
            DELETE sv FROM dbo.SINHVIEN sv JOIN @Dong d ON d.MASV = sv.MASV AND d.MALOP = sv.MALOP
            WHERE NOT EXISTS (SELECT 1 FROM dbo.TRANGTHAITHI tt WHERE tt.MASV = sv.MASV);
            IF @@ROWCOUNT <> @SoDong
                THROW 51102, N'Có sinh viên không còn trong lớp hoặc đang có bài thi.', 1;
        END;

        IF @ThaoTac = 'R'
            UPDATE dbo.LichSuQuanLy SET DaPhucHoi = 1 WHERE Ma = @MaLichSu;
        ELSE
        BEGIN
            SET @Sau = ISNULL((
                SELECT sv.* FROM dbo.SINHVIEN sv JOIN @Dong d ON d.MASV = sv.MASV
                ORDER BY sv.MASV FOR JSON PATH, INCLUDE_NULL_VALUES
            ), N'[]');
            INSERT dbo.LichSuQuanLy (TaiKhoan, Nhom, PhamVi, ThaoTac, Truoc, Sau)
            VALUES (SUSER_SID(ORIGINAL_LOGIN()), 'SINHVIEN', COALESCE(@MaLop, N''), @ThaoTac, @Truoc, @Sau);
        END;
        COMMIT;
    END TRY
    BEGIN CATCH
        IF XACT_STATE() <> 0 ROLLBACK;
        THROW;
    END CATCH;
END;
GO

ALTER PROCEDURE dbo.SP_GhiGiaoVien
    @ThaoTac char(1), @MaGV nvarchar(8) = NULL, @Ho nvarchar(50) = NULL,
    @Ten nvarchar(10) = NULL, @SoDT nchar(15) = NULL, @DiaChi nvarchar(50) = NULL,
    @DanhSach dbo.DongGiaoVienChoGhi READONLY
WITH EXECUTE AS OWNER
AS
BEGIN
    SET NOCOUNT ON;
    SET XACT_ABORT ON;
    IF @ThaoTac IS NULL OR @ThaoTac NOT IN ('I', 'U', 'D', 'R')
        THROW 51100, N'Thao tác không hợp lệ.', 1;

    DECLARE @Dong dbo.DongGiaoVienChoGhi;
    DECLARE @Lenh char(1) = @ThaoTac, @MaLichSu bigint, @DuLieu nvarchar(max), @Sau nvarchar(max);
    BEGIN TRY
        BEGIN TRANSACTION;
        EXEC dbo.SP_KhoaGhiQuanLy;

        IF @ThaoTac = 'R'
        BEGIN
            EXEC dbo.SP_DocLanPhucHoiQuanLy 'GIAOVIEN', N'',
                @MaLichSu OUTPUT, @Lenh OUTPUT, @DuLieu OUTPUT, @Sau OUTPUT;
            INSERT @Dong (MAGV, HO, TEN, SODTLL, DIACHI)
            SELECT MAGV, HO, TEN, SODTLL, DIACHI FROM OPENJSON(@DuLieu)
                WITH (MAGV nvarchar(8), HO nvarchar(50), TEN nvarchar(10),
                      SODTLL nchar(15), DIACHI nvarchar(50));
        END
        ELSE IF @ThaoTac IN ('I', 'U') AND EXISTS (SELECT 1 FROM @DanhSach)
            INSERT @Dong SELECT * FROM @DanhSach;
        ELSE
            INSERT @Dong VALUES (@MaGV, @Ho, @Ten, @SoDT, @DiaChi);

        DECLARE @SoDong int = (SELECT COUNT(*) FROM @Dong);
        DECLARE @Truoc nvarchar(max) = ISNULL((
            SELECT gv.* FROM dbo.GIAOVIEN gv WITH (UPDLOCK, HOLDLOCK)
            JOIN @Dong d ON d.MAGV = gv.MAGV
            ORDER BY gv.MAGV FOR JSON PATH, INCLUDE_NULL_VALUES
        ), N'[]');
        IF @ThaoTac = 'R' AND CONVERT(varbinary(max), @Truoc) <> CONVERT(varbinary(max), @Sau)
            THROW 51122, N'Dữ liệu giáo viên đã thay đổi kể từ lần ghi. Không thể phục hồi đợt này.', 1;

        IF @Lenh = 'I'
            INSERT dbo.GIAOVIEN (MAGV, HO, TEN, SODTLL, DIACHI)
            SELECT MAGV, HO, TEN, SODTLL, DIACHI FROM @Dong;
        ELSE IF @Lenh = 'U'
        BEGIN
            UPDATE gv SET HO = d.HO, TEN = d.TEN, SODTLL = d.SODTLL, DIACHI = d.DIACHI
            FROM dbo.GIAOVIEN gv JOIN @Dong d ON d.MAGV = gv.MAGV;
            IF @@ROWCOUNT <> @SoDong THROW 51103, N'Có giáo viên không còn tồn tại.', 1;
        END
        ELSE
        BEGIN
            IF EXISTS (SELECT 1 FROM @Dong WHERE DATABASE_PRINCIPAL_ID(RTRIM(MAGV)) IS NOT NULL)
                THROW 51104, N'Có giáo viên đã có tài khoản.', 1;
            DELETE gv FROM dbo.GIAOVIEN gv JOIN @Dong d ON d.MAGV = gv.MAGV
            WHERE NOT EXISTS (SELECT 1 FROM dbo.GIAOVIEN_DANGKY dk WHERE dk.MAGV = gv.MAGV);
            IF @@ROWCOUNT <> @SoDong
                THROW 51103, N'Có giáo viên không còn tồn tại hoặc đã đăng ký ca thi.', 1;
        END;

        IF @ThaoTac = 'R'
            UPDATE dbo.LichSuQuanLy SET DaPhucHoi = 1 WHERE Ma = @MaLichSu;
        ELSE
        BEGIN
            SET @Sau = ISNULL((
                SELECT gv.* FROM dbo.GIAOVIEN gv JOIN @Dong d ON d.MAGV = gv.MAGV
                ORDER BY gv.MAGV FOR JSON PATH, INCLUDE_NULL_VALUES
            ), N'[]');
            INSERT dbo.LichSuQuanLy (TaiKhoan, Nhom, PhamVi, ThaoTac, Truoc, Sau)
            VALUES (SUSER_SID(ORIGINAL_LOGIN()), 'GIAOVIEN', N'', @ThaoTac, @Truoc, @Sau);
        END;
        COMMIT;
    END TRY
    BEGIN CATCH
        IF XACT_STATE() <> 0 ROLLBACK;
        THROW;
    END CATCH;
END;
GO

ALTER PROCEDURE dbo.SP_ThemMonHoc
    @MaMH nvarchar(4000) = NULL, @TenMH nvarchar(4000) = NULL,
    @DanhSach dbo.DongMonHocChoGhi READONLY
AS
BEGIN
    SET NOCOUNT ON;
    SET XACT_ABORT ON;

    DECLARE @Dong dbo.DongMonHocChoGhi;
    IF EXISTS (SELECT 1 FROM @DanhSach)
        INSERT @Dong (MAMH, TENMH) SELECT TRIM(MAMH), TRIM(TENMH) FROM @DanhSach;
    ELSE
    BEGIN
        SET @MaMH = TRIM(@MaMH);
        SET @TenMH = TRIM(@TenMH);
        IF @MaMH IS NULL OR DATALENGTH(@MaMH) NOT BETWEEN 2 AND 10
            THROW 51010, N'Mã môn học phải có từ 1 đến 5 ký tự.', 1;
        IF @TenMH IS NULL OR DATALENGTH(@TenMH) NOT BETWEEN 2 AND 100
            THROW 51011, N'Tên môn học phải có từ 1 đến 50 ký tự.', 1;
        INSERT @Dong (MAMH, TENMH) VALUES (@MaMH, @TenMH);
    END;
    IF EXISTS (SELECT 1 FROM @Dong WHERE MAMH = N'')
        THROW 51010, N'Mã môn học phải có từ 1 đến 5 ký tự.', 1;
    IF EXISTS (SELECT 1 FROM @Dong WHERE TENMH IS NULL OR TENMH = N'')
        THROW 51011, N'Tên môn học phải có từ 1 đến 50 ký tự.', 1;

    BEGIN TRY
        BEGIN TRANSACTION;
        EXEC dbo.SP_KhoaGhiQuanLy;
        INSERT dbo.MONHOC (MAMH, TENMH) SELECT MAMH, TENMH FROM @Dong;

        DECLARE @Sau nvarchar(max) = (
            SELECT mh.* FROM dbo.MONHOC mh JOIN @Dong d ON d.MAMH = mh.MAMH
            ORDER BY mh.MAMH FOR JSON PATH, INCLUDE_NULL_VALUES
        );
        INSERT dbo.LichSuQuanLy (TaiKhoan, Nhom, PhamVi, ThaoTac, Truoc, Sau)
        VALUES (SUSER_SID(ORIGINAL_LOGIN()), 'MONHOC', N'', 'I', N'[]', @Sau);
        COMMIT;
    END TRY
    BEGIN CATCH
        IF XACT_STATE() <> 0 ROLLBACK;
        THROW;
    END CATCH;
END;
GO

ALTER PROCEDURE dbo.SP_HieuChinhMonHoc
    @MaMH nvarchar(4000) = NULL, @TenMH nvarchar(4000) = NULL, @TenCu nvarchar(4000) = NULL,
    @DanhSach dbo.DongMonHocChoGhi READONLY
AS
BEGIN
    SET NOCOUNT ON;
    SET XACT_ABORT ON;

    DECLARE @Dong dbo.DongMonHocChoGhi;
    IF EXISTS (SELECT 1 FROM @DanhSach)
        INSERT @Dong SELECT MAMH, TRIM(TENMH), TENCU FROM @DanhSach;
    ELSE
    BEGIN
        SET @TenMH = TRIM(@TenMH);
        IF @TenMH IS NULL OR DATALENGTH(@TenMH) NOT BETWEEN 2 AND 100
            THROW 51011, N'Tên môn học phải có từ 1 đến 50 ký tự.', 1;
        IF @MaMH IS NULL OR DATALENGTH(RTRIM(@MaMH)) > 10 OR DATALENGTH(@TenCu) > 100
            THROW 51012, N'Môn học đã thay đổi hoặc không còn tồn tại. Hãy tải lại danh sách.', 1;
        INSERT @Dong VALUES (RTRIM(@MaMH), @TenMH, @TenCu);
    END;
    IF EXISTS (SELECT 1 FROM @Dong WHERE TENMH IS NULL OR TENMH = N'')
        THROW 51011, N'Tên môn học phải có từ 1 đến 50 ký tự.', 1;

    BEGIN TRY
        BEGIN TRANSACTION;
        EXEC dbo.SP_KhoaGhiQuanLy;
        DECLARE @SoDong int = (SELECT COUNT(*) FROM @Dong);
        DECLARE @Truoc nvarchar(max) = (
            SELECT mh.* FROM dbo.MONHOC mh WITH (UPDLOCK, HOLDLOCK)
            JOIN @Dong d ON d.MAMH = mh.MAMH
            ORDER BY mh.MAMH FOR JSON PATH, INCLUDE_NULL_VALUES
        );

        UPDATE mh SET TENMH = d.TENMH
        FROM dbo.MONHOC mh JOIN @Dong d ON d.MAMH = mh.MAMH
        WHERE EXISTS (
            SELECT CONVERT(varbinary(8000), mh.TENMH) INTERSECT SELECT CONVERT(varbinary(8000), d.TENCU)
        );
        IF @@ROWCOUNT <> @SoDong
            THROW 51012, N'Có môn học đã thay đổi hoặc không còn tồn tại. Hãy tải lại danh sách.', 1;

        DECLARE @Sau nvarchar(max) = (
            SELECT mh.* FROM dbo.MONHOC mh JOIN @Dong d ON d.MAMH = mh.MAMH
            ORDER BY mh.MAMH FOR JSON PATH, INCLUDE_NULL_VALUES
        );
        INSERT dbo.LichSuQuanLy (TaiKhoan, Nhom, PhamVi, ThaoTac, Truoc, Sau)
        VALUES (SUSER_SID(ORIGINAL_LOGIN()), 'MONHOC', N'', 'U', @Truoc, @Sau);
        COMMIT;
    END TRY
    BEGIN CATCH
        IF XACT_STATE() <> 0 ROLLBACK;
        THROW;
    END CATCH;
END;
GO

ALTER PROCEDURE dbo.SP_XoaMonHoc
    @MaMH nvarchar(4000), @TenCu nvarchar(4000)
AS
BEGIN
    SET NOCOUNT ON;
    SET XACT_ABORT ON;
    BEGIN TRY
        BEGIN TRANSACTION;
        EXEC dbo.SP_KhoaGhiQuanLy;
        DECLARE @Truoc nvarchar(max) = (
            SELECT mh.* FROM dbo.MONHOC mh WITH (UPDLOCK, HOLDLOCK)
            WHERE MAMH = @MaMH FOR JSON PATH, INCLUDE_NULL_VALUES
        );
        DELETE FROM dbo.MONHOC
        WHERE MAMH = @MaMH AND EXISTS (
            SELECT CONVERT(varbinary(8000), TENMH) INTERSECT SELECT CONVERT(varbinary(8000), @TenCu)
        );
        IF @@ROWCOUNT = 0
            THROW 51012, N'Môn học đã thay đổi hoặc không còn tồn tại. Hãy tải lại danh sách.', 1;

        INSERT dbo.LichSuQuanLy (TaiKhoan, Nhom, PhamVi, ThaoTac, Truoc, Sau)
        VALUES (SUSER_SID(ORIGINAL_LOGIN()), 'MONHOC', N'', 'D', @Truoc, N'[]');
        COMMIT;
    END TRY
    BEGIN CATCH
        IF XACT_STATE() <> 0 ROLLBACK;
        IF ERROR_NUMBER() = 547
        BEGIN
            IF EXISTS (SELECT 1 FROM dbo.BODE WHERE MAMH = @MaMH)
                THROW 51013, N'Không thể xóa môn học vì bộ đề còn câu hỏi của môn này.', 1;
            IF EXISTS (SELECT 1 FROM dbo.GIAOVIEN_DANGKY WHERE MAMH = @MaMH)
                THROW 51014, N'Không thể xóa môn học vì vẫn còn ca thi đã đăng ký.', 1;
            IF EXISTS (SELECT 1 FROM dbo.BANGDIEM WHERE MAMH = @MaMH)
                THROW 51015, N'Không thể xóa môn học vì đã có điểm thi.', 1;
            IF EXISTS (SELECT 1 FROM dbo.TRANGTHAITHI WHERE MAMH = @MaMH)
                THROW 51016, N'Không thể xóa môn học vì vẫn còn bài thi đang làm.', 1;
        END;
        THROW;
    END CATCH;
END;
GO

CREATE OR ALTER PROCEDURE dbo.SP_PhucHoiMonHoc
AS
BEGIN
    SET NOCOUNT ON;
    SET XACT_ABORT ON;
    DECLARE @Dong dbo.DongMonHocChoGhi;
    DECLARE @Lenh char(1), @MaLichSu bigint, @DuLieu nvarchar(max), @Sau nvarchar(max);
    BEGIN TRY
        BEGIN TRANSACTION;
        EXEC dbo.SP_KhoaGhiQuanLy;
        EXEC dbo.SP_DocLanPhucHoiQuanLy 'MONHOC', N'',
            @MaLichSu OUTPUT, @Lenh OUTPUT, @DuLieu OUTPUT, @Sau OUTPUT;
        INSERT @Dong (MAMH, TENMH)
        SELECT MAMH, TENMH FROM OPENJSON(@DuLieu) WITH (MAMH nvarchar(5), TENMH nvarchar(50));

        DECLARE @HienTai nvarchar(max) = ISNULL((
            SELECT mh.* FROM dbo.MONHOC mh WITH (UPDLOCK, HOLDLOCK)
            JOIN @Dong d ON d.MAMH = mh.MAMH
            ORDER BY mh.MAMH FOR JSON PATH, INCLUDE_NULL_VALUES
        ), N'[]');
        IF CONVERT(varbinary(max), @HienTai) <> CONVERT(varbinary(max), @Sau)
            THROW 51122, N'Dữ liệu môn học đã thay đổi kể từ lần ghi. Không thể phục hồi đợt này.', 1;

        IF @Lenh = 'I'
            INSERT dbo.MONHOC (MAMH, TENMH) SELECT MAMH, TENMH FROM @Dong;
        ELSE IF @Lenh = 'U'
            UPDATE mh SET TENMH = d.TENMH FROM dbo.MONHOC mh JOIN @Dong d ON d.MAMH = mh.MAMH;
        ELSE
            DELETE mh FROM dbo.MONHOC mh JOIN @Dong d ON d.MAMH = mh.MAMH;

        UPDATE dbo.LichSuQuanLy SET DaPhucHoi = 1 WHERE Ma = @MaLichSu;
        COMMIT;
    END TRY
    BEGIN CATCH
        IF XACT_STATE() <> 0 ROLLBACK;
        THROW;
    END CATCH;
END;
GO

ALTER PROCEDURE dbo.SP_GhiBoDe
    @ThaoTac char(1), @CauHoi int = NULL, @MaMH nvarchar(5) = NULL, @TrinhDo char(1) = NULL,
    @NoiDung nvarchar(200) = NULL, @A nvarchar(200) = NULL, @B nvarchar(200) = NULL,
    @C nvarchar(200) = NULL, @D nvarchar(200) = NULL, @DapAn char(1) = NULL,
    @DanhSach dbo.DongCauHoiChoGhi READONLY
AS
BEGIN
    SET NOCOUNT ON;
    SET XACT_ABORT ON;
    IF @ThaoTac IS NULL OR @ThaoTac NOT IN ('I', 'U', 'D', 'R')
        THROW 51100, N'Thao tác không hợp lệ.', 1;
    DECLARE @MaGV sysname = USER_NAME();
    IF NOT EXISTS (SELECT 1 FROM dbo.GIAOVIEN WHERE MAGV = @MaGV)
        THROW 51105, N'Không xác định được giáo viên đăng nhập.', 1;

    DECLARE @Dong dbo.DongCauHoiChoGhi;
    DECLARE @Lenh char(1) = @ThaoTac, @MaLichSu bigint, @DuLieu nvarchar(max), @Sau nvarchar(max);
    BEGIN TRY
        BEGIN TRANSACTION;
        EXEC dbo.SP_KhoaGhiQuanLy;

        IF @ThaoTac = 'R'
        BEGIN
            EXEC dbo.SP_DocLanPhucHoiQuanLy 'BODE', N'',
                @MaLichSu OUTPUT, @Lenh OUTPUT, @DuLieu OUTPUT, @Sau OUTPUT;
            INSERT @Dong (STT, CAUHOI, MAMH, TRINHDO, NOIDUNG, A, B, C, D, DAP_AN, MAGV)
            SELECT ROW_NUMBER() OVER (ORDER BY CAUHOI), CAUHOI, MAMH, TRINHDO, NOIDUNG, A, B, C, D, DAP_AN, MAGV
            FROM OPENJSON(@DuLieu)
                WITH (CAUHOI int, MAMH nvarchar(5), TRINHDO char(1), NOIDUNG nvarchar(500),
                      A nvarchar(200), B nvarchar(200), C nvarchar(200), D nvarchar(200),
                      DAP_AN char(1), MAGV nvarchar(8));
        END
        ELSE IF @ThaoTac IN ('I', 'U') AND EXISTS (SELECT 1 FROM @DanhSach)
            INSERT @Dong SELECT STT, CAUHOI, MAMH, TRINHDO, NOIDUNG, A, B, C, D, DAP_AN, @MaGV FROM @DanhSach;
        ELSE
            INSERT @Dong VALUES (1, @CauHoi, @MaMH, @TrinhDo, @NoiDung, @A, @B, @C, @D, @DapAn, @MaGV);

        IF @ThaoTac IN ('I', 'U') AND EXISTS (
            SELECT 1 FROM @Dong
            WHERE TRINHDO IS NULL OR TRINHDO NOT IN ('A', 'B', 'C')
               OR DAP_AN IS NULL OR DAP_AN NOT IN ('A', 'B', 'C', 'D')
               OR NULLIF(TRIM(NOIDUNG), N'') IS NULL OR DATALENGTH(NOIDUNG) > 400
               OR NULLIF(TRIM(A), N'') IS NULL OR NULLIF(TRIM(B), N'') IS NULL
               OR NULLIF(TRIM(C), N'') IS NULL OR NULLIF(TRIM(D), N'') IS NULL OR MAMH IS NULL
        )
            THROW 51107, N'Chọn môn học, trình độ, đáp án và nhập đủ nội dung câu hỏi.', 1;

        IF @ThaoTac = 'I'
        BEGIN
            DECLARE @SoCuoi int = (SELECT COALESCE(MAX(CAUHOI), 0) FROM dbo.BODE WITH (UPDLOCK, HOLDLOCK));
            WITH DanhSo AS (SELECT STT, ROW_NUMBER() OVER (ORDER BY STT) AS SoThuTu FROM @Dong)
            UPDATE d SET CAUHOI = @SoCuoi + s.SoThuTu FROM @Dong d JOIN DanhSo s ON s.STT = d.STT;
        END;

        DECLARE @SoDong int = (SELECT COUNT(*) FROM @Dong);
        DECLARE @Truoc nvarchar(max) = ISNULL((
            SELECT bd.* FROM dbo.BODE bd WITH (UPDLOCK, HOLDLOCK)
            JOIN @Dong d ON d.CAUHOI = bd.CAUHOI
            ORDER BY bd.CAUHOI FOR JSON PATH, INCLUDE_NULL_VALUES
        ), N'[]');
        IF @ThaoTac = 'R' AND CONVERT(varbinary(max), @Truoc) <> CONVERT(varbinary(max), @Sau)
            THROW 51122, N'Dữ liệu câu hỏi đã thay đổi kể từ lần ghi. Không thể phục hồi đợt này.', 1;

        IF @Lenh = 'I'
            INSERT dbo.BODE (CAUHOI, MAMH, TRINHDO, NOIDUNG, A, B, C, D, DAP_AN, MAGV)
            SELECT CAUHOI, MAMH, TRINHDO, NOIDUNG, A, B, C, D, DAP_AN, MAGV FROM @Dong;
        ELSE IF @Lenh = 'U'
        BEGIN
            UPDATE bd SET MAMH = d.MAMH, TRINHDO = d.TRINHDO, NOIDUNG = d.NOIDUNG,
                A = d.A, B = d.B, C = d.C, D = d.D, DAP_AN = d.DAP_AN, MAGV = d.MAGV
            FROM dbo.BODE bd JOIN @Dong d ON d.CAUHOI = bd.CAUHOI
            WHERE bd.MAGV = @MaGV OR bd.MAGV IS NULL;
            IF @@ROWCOUNT <> @SoDong THROW 51106, N'Có câu hỏi đã bị xóa hoặc thuộc giáo viên khác.', 1;
        END
        ELSE
        BEGIN
            DELETE bd FROM dbo.BODE bd JOIN @Dong d ON d.CAUHOI = bd.CAUHOI
            WHERE bd.MAGV = @MaGV OR bd.MAGV IS NULL;
            IF @@ROWCOUNT <> @SoDong THROW 51106, N'Có câu hỏi đã bị xóa hoặc thuộc giáo viên khác.', 1;
        END;

        IF @ThaoTac = 'R'
            UPDATE dbo.LichSuQuanLy SET DaPhucHoi = 1 WHERE Ma = @MaLichSu;
        ELSE
        BEGIN
            SET @Sau = ISNULL((
                SELECT bd.* FROM dbo.BODE bd JOIN @Dong d ON d.CAUHOI = bd.CAUHOI
                ORDER BY bd.CAUHOI FOR JSON PATH, INCLUDE_NULL_VALUES
            ), N'[]');
            INSERT dbo.LichSuQuanLy (TaiKhoan, Nhom, PhamVi, ThaoTac, Truoc, Sau)
            VALUES (SUSER_SID(ORIGINAL_LOGIN()), 'BODE', N'', @ThaoTac, @Truoc, @Sau);
        END;
        COMMIT;
    END TRY
    BEGIN CATCH
        IF XACT_STATE() <> 0 ROLLBACK;
        THROW;
    END CATCH;
END;
GO

ALTER PROCEDURE dbo.SP_GhiCaThiQuanLy
    @ThaoTac char(1), @MaLop nvarchar(15) = NULL, @MaMH nvarchar(5) = NULL, @Lan smallint = NULL,
    @TrinhDo char(1) = NULL, @NgayThi date = NULL, @SoCau smallint = NULL, @ThoiGian smallint = NULL,
    @DanhSach dbo.DongCaThiChoGhi READONLY
AS
BEGIN
    SET NOCOUNT ON;
    SET XACT_ABORT ON;
    IF @ThaoTac IS NULL OR @ThaoTac NOT IN ('I', 'U', 'D', 'R')
        THROW 51100, N'Thao tác không hợp lệ.', 1;
    DECLARE @MaGV sysname = USER_NAME();
    DECLARE @HomNay date = CONVERT(date, DATEADD(hour, 7, SYSUTCDATETIME()));
    IF NOT EXISTS (SELECT 1 FROM dbo.GIAOVIEN WHERE MAGV = @MaGV)
        THROW 51105, N'Không xác định được giáo viên đăng nhập.', 1;

    DECLARE @Dong dbo.DongCaThiChoGhi;
    DECLARE @Lenh char(1) = @ThaoTac, @MaLichSu bigint, @DuLieu nvarchar(max), @Sau nvarchar(max);
    BEGIN TRY
        BEGIN TRANSACTION;
        EXEC dbo.SP_KhoaGhiQuanLy;

        IF @ThaoTac = 'R'
        BEGIN
            EXEC dbo.SP_DocLanPhucHoiQuanLy 'CATHI', N'',
                @MaLichSu OUTPUT, @Lenh OUTPUT, @DuLieu OUTPUT, @Sau OUTPUT;
            INSERT @Dong (MALOP, MAMH, LAN, TRINHDO, NGAYTHI, SOCAUTHI, THOIGIAN, MAGV)
            SELECT MALOP, MAMH, LAN, TRINHDO, NGAYTHI, SOCAUTHI, THOIGIAN, MAGV FROM OPENJSON(@DuLieu)
                WITH (MALOP nvarchar(15), MAMH nvarchar(5), LAN smallint, TRINHDO char(1),
                      NGAYTHI datetime, SOCAUTHI smallint, THOIGIAN smallint, MAGV nvarchar(8));
        END
        ELSE IF @ThaoTac IN ('I', 'U') AND EXISTS (SELECT 1 FROM @DanhSach)
            INSERT @Dong
            SELECT MALOP, MAMH, LAN, TRINHDO, CONVERT(date, NGAYTHI), SOCAUTHI, THOIGIAN, @MaGV FROM @DanhSach;
        ELSE
            INSERT @Dong VALUES (@MaLop, @MaMH, @Lan, @TrinhDo, @NgayThi, @SoCau, @ThoiGian, @MaGV);

        IF @ThaoTac IN ('I', 'U')
        BEGIN
            IF EXISTS (
                SELECT 1 FROM @Dong
                WHERE NGAYTHI IS NULL OR NGAYTHI < @HomNay OR TRINHDO IS NULL OR TRINHDO NOT IN ('A', 'B', 'C')
                   OR SOCAUTHI IS NULL OR SOCAUTHI NOT BETWEEN 10 AND 100
                   OR THOIGIAN IS NULL OR THOIGIAN NOT BETWEEN 15 AND 60
            )
                THROW 51109, N'Ngày thi, trình độ, số câu hoặc thời gian thi không hợp lệ.', 1;

            -- Kiểm tra một lần cho số câu lớn nhất của mỗi môn và trình độ trong đợt.
            DECLARE @Dem TABLE (CUNGTRINHDO int, THAPHON int);
            DECLARE @CanCungMuc int;
            DECLARE monCanKiemTra CURSOR LOCAL FAST_FORWARD FOR
                SELECT MAMH, TRINHDO, MAX(SOCAUTHI) FROM @Dong GROUP BY MAMH, TRINHDO;
            OPEN monCanKiemTra;
            FETCH NEXT FROM monCanKiemTra INTO @MaMH, @TrinhDo, @SoCau;
            WHILE @@FETCH_STATUS = 0
            BEGIN
                DELETE FROM @Dem;
                INSERT @Dem EXEC dbo.SP_DemCauHoiThi @MaMH, @TrinhDo;
                SET @CanCungMuc = CASE WHEN @TrinhDo = 'C' THEN @SoCau ELSE ROUND(@SoCau * 0.7, 0) END;
                IF EXISTS (SELECT 1 FROM @Dem WHERE CUNGTRINHDO < @CanCungMuc OR THAPHON < @SoCau - @CanCungMuc)
                    THROW 51110, N'Bộ đề chưa đủ câu hỏi theo trình độ đã chọn.', 1;
                FETCH NEXT FROM monCanKiemTra INTO @MaMH, @TrinhDo, @SoCau;
            END;
            CLOSE monCanKiemTra;
            DEALLOCATE monCanKiemTra;
        END;

        DECLARE @SoDong int = (SELECT COUNT(*) FROM @Dong);
        DECLARE @Truoc nvarchar(max) = ISNULL((
            SELECT dk.* FROM dbo.GIAOVIEN_DANGKY dk WITH (UPDLOCK, HOLDLOCK)
            JOIN @Dong d ON d.MALOP = dk.MALOP AND d.MAMH = dk.MAMH AND d.LAN = dk.LAN
            ORDER BY dk.MALOP, dk.MAMH, dk.LAN FOR JSON PATH, INCLUDE_NULL_VALUES
        ), N'[]');
        IF @ThaoTac = 'R' AND CONVERT(varbinary(max), @Truoc) <> CONVERT(varbinary(max), @Sau)
            THROW 51122, N'Dữ liệu ca thi đã thay đổi kể từ lần ghi. Không thể phục hồi đợt này.', 1;

        IF @ThaoTac = 'R' AND @Lenh IN ('I', 'U') AND EXISTS (SELECT 1 FROM @Dong WHERE NGAYTHI < @HomNay)
            THROW 51108, N'Không thể phục hồi ca thi đã qua ngày thi.', 1;
        IF (@Lenh = 'D' OR @ThaoTac = 'R') AND EXISTS (
            SELECT 1 FROM @Dong d
            JOIN dbo.SINHVIEN sv WITH (HOLDLOCK) ON sv.MALOP = d.MALOP
            WHERE EXISTS (SELECT 1 FROM dbo.BANGDIEM bd WITH (HOLDLOCK)
                          WHERE bd.MASV = sv.MASV AND bd.MAMH = d.MAMH AND bd.LAN = d.LAN)
               OR EXISTS (SELECT 1 FROM dbo.TRANGTHAITHI tt WITH (HOLDLOCK)
                          WHERE tt.MASV = sv.MASV AND tt.MAMH = d.MAMH AND tt.LAN = d.LAN)
        )
            THROW 51108, N'Có ca thi đã có bài làm hoặc điểm thi.', 1;

        IF @Lenh = 'I'
            INSERT dbo.GIAOVIEN_DANGKY (MAGV, MALOP, MAMH, LAN, TRINHDO, NGAYTHI, SOCAUTHI, THOIGIAN)
            SELECT MAGV, MALOP, MAMH, LAN, TRINHDO, NGAYTHI, SOCAUTHI, THOIGIAN FROM @Dong;
        ELSE IF @Lenh = 'U'
        BEGIN
            UPDATE dk SET MAGV = d.MAGV, TRINHDO = d.TRINHDO, NGAYTHI = d.NGAYTHI,
                SOCAUTHI = d.SOCAUTHI, THOIGIAN = d.THOIGIAN
            FROM dbo.GIAOVIEN_DANGKY dk
            JOIN @Dong d ON d.MALOP = dk.MALOP AND d.MAMH = dk.MAMH AND d.LAN = dk.LAN
            WHERE (dk.MAGV = @MaGV OR dk.MAGV IS NULL) AND (dk.NGAYTHI IS NULL OR dk.NGAYTHI >= DATEADD(day, 1, @HomNay));
            IF @@ROWCOUNT <> @SoDong
                THROW 51108, N'Chỉ sửa được ca chưa tới ngày thi do mình đăng ký hoặc chưa có người phụ trách.', 1;
        END
        ELSE
        BEGIN
            DELETE dk FROM dbo.GIAOVIEN_DANGKY dk
            JOIN @Dong d ON d.MALOP = dk.MALOP AND d.MAMH = dk.MAMH AND d.LAN = dk.LAN
            WHERE (dk.MAGV = @MaGV OR dk.MAGV IS NULL) AND (dk.NGAYTHI IS NULL OR dk.NGAYTHI >= DATEADD(day, 1, @HomNay));
            IF @@ROWCOUNT <> @SoDong
                THROW 51108, N'Chỉ xóa được ca chưa tới ngày thi do mình đăng ký hoặc chưa có người phụ trách.', 1;
        END;

        IF @ThaoTac = 'R'
            UPDATE dbo.LichSuQuanLy SET DaPhucHoi = 1 WHERE Ma = @MaLichSu;
        ELSE
        BEGIN
            SET @Sau = ISNULL((
                SELECT dk.* FROM dbo.GIAOVIEN_DANGKY dk
                JOIN @Dong d ON d.MALOP = dk.MALOP AND d.MAMH = dk.MAMH AND d.LAN = dk.LAN
                ORDER BY dk.MALOP, dk.MAMH, dk.LAN FOR JSON PATH, INCLUDE_NULL_VALUES
            ), N'[]');
            INSERT dbo.LichSuQuanLy (TaiKhoan, Nhom, PhamVi, ThaoTac, Truoc, Sau)
            VALUES (SUSER_SID(ORIGINAL_LOGIN()), 'CATHI', N'', @ThaoTac, @Truoc, @Sau);
        END;
        COMMIT;
    END TRY
    BEGIN CATCH
        IF XACT_STATE() <> 0 ROLLBACK;
        THROW;
    END CATCH;
END;
GO

GRANT EXECUTE, REFERENCES ON TYPE::dbo.DongLopChoGhi TO [GiaoVien];
GRANT EXECUTE, REFERENCES ON TYPE::dbo.DongSinhVienChoGhi TO [GiaoVien];
GRANT EXECUTE, REFERENCES ON TYPE::dbo.DongGiaoVienChoGhi TO [GiaoVien];
GRANT EXECUTE, REFERENCES ON TYPE::dbo.DongMonHocChoGhi TO [GiaoVien];
GRANT EXECUTE, REFERENCES ON TYPE::dbo.DongCauHoiChoGhi TO [GiaoVien];
GRANT EXECUTE, REFERENCES ON TYPE::dbo.DongCaThiChoGhi TO [GiaoVien];
GRANT EXECUTE ON dbo.SP_PhucHoiMonHoc TO [GiaoVien];
GO
