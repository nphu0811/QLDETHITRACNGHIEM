-- Chạy trên cơ sở dữ liệu THITRACNGHIEM.
SET ANSI_NULLS ON;
SET QUOTED_IDENTIFIER ON;
SET XACT_ABORT ON;
GO

BEGIN TRANSACTION;

IF OBJECT_ID(N'dbo.FK_GIAOVIEN_DANGKY_GIAOVIEN', N'F') IS NULL
    ALTER TABLE dbo.GIAOVIEN_DANGKY WITH CHECK
        ADD CONSTRAINT FK_GIAOVIEN_DANGKY_GIAOVIEN FOREIGN KEY (MAGV) REFERENCES dbo.GIAOVIEN (MAGV);

IF OBJECT_ID(N'dbo.FK_TRANGTHAITHI_MONHOC', N'F') IS NULL
    ALTER TABLE dbo.TRANGTHAITHI WITH CHECK
        ADD CONSTRAINT FK_TRANGTHAITHI_MONHOC FOREIGN KEY (MAMH) REFERENCES dbo.MONHOC (MAMH);

IF OBJECT_ID(N'dbo.FK_TRANGTHAITHI_SINHVIEN', N'F') IS NULL
    ALTER TABLE dbo.TRANGTHAITHI WITH CHECK
        ADD CONSTRAINT FK_TRANGTHAITHI_SINHVIEN FOREIGN KEY (MASV) REFERENCES dbo.SINHVIEN (MASV);
GO

ALTER PROCEDURE dbo.SP_XoaMonHoc
    @MaMH nvarchar(4000),
    @TenCu nvarchar(4000)
AS
BEGIN
    SET NOCOUNT ON;

    BEGIN TRY
        DELETE FROM dbo.MONHOC
        WHERE MAMH = @MaMH
          AND EXISTS (
              SELECT CONVERT(varbinary(8000), TENMH)
              INTERSECT
              SELECT CONVERT(varbinary(8000), @TenCu)
          );

        IF @@ROWCOUNT = 0
            THROW 51012, N'Môn học đã thay đổi hoặc không còn tồn tại. Hãy tải lại danh sách.', 1;
    END TRY
    BEGIN CATCH
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

ALTER PROCEDURE dbo.SP_GhiCaThiQuanLy
    @ThaoTac char(1), @MaLop nvarchar(15), @MaMH nvarchar(5), @Lan smallint,
    @TrinhDo char(1) = NULL, @NgayThi date = NULL, @SoCau smallint = NULL, @ThoiGian smallint = NULL
AS
BEGIN
    SET NOCOUNT ON;
    DECLARE @MaGV sysname = USER_NAME();
    DECLARE @HomNay date = CONVERT(date, DATEADD(hour, 7, SYSUTCDATETIME()));

    IF NOT EXISTS (SELECT 1 FROM dbo.GIAOVIEN WHERE MAGV = @MaGV)
        THROW 51105, N'Không xác định được giáo viên đăng nhập.', 1;

    IF @ThaoTac = 'D'
    BEGIN
        DELETE dbo.GIAOVIEN_DANGKY
        WHERE MALOP = @MaLop AND MAMH = @MaMH AND LAN = @Lan
          AND (MAGV = @MaGV OR MAGV IS NULL) AND (NGAYTHI IS NULL OR NGAYTHI >= DATEADD(day, 1, @HomNay))
          AND NOT EXISTS (
              SELECT 1 FROM dbo.SINHVIEN sv WITH (HOLDLOCK)
              WHERE sv.MALOP = @MaLop
                AND (
                    EXISTS (SELECT 1 FROM dbo.BANGDIEM bd WITH (HOLDLOCK)
                        WHERE bd.MASV = sv.MASV AND bd.MAMH = @MaMH AND bd.LAN = @Lan)
                    OR EXISTS (SELECT 1 FROM dbo.TRANGTHAITHI tt WITH (HOLDLOCK)
                        WHERE tt.MASV = sv.MASV AND tt.MAMH = @MaMH AND tt.LAN = @Lan)
                )
          );
        IF @@ROWCOUNT = 0
            THROW 51108, N'Chỉ xóa được ca chưa tới ngày thi, do mình đăng ký hoặc chưa có người phụ trách, và chưa có bài làm hay điểm thi.', 1;
        RETURN;
    END;

    IF @NgayThi IS NULL OR @NgayThi < @HomNay OR @TrinhDo IS NULL OR @TrinhDo NOT IN ('A', 'B', 'C')
        OR @SoCau IS NULL OR @SoCau NOT BETWEEN 10 AND 100 OR @ThoiGian IS NULL OR @ThoiGian NOT BETWEEN 15 AND 60
        THROW 51109, N'Ngày thi, trình độ, số câu hoặc thời gian thi không hợp lệ.', 1;

    DECLARE @Dem TABLE (CUNGTRINHDO int, THAPHON int);
    INSERT @Dem EXEC dbo.SP_DemCauHoiThi @MaMH, @TrinhDo;
    DECLARE @CanCungMuc int = CASE WHEN @TrinhDo = 'C' THEN @SoCau ELSE ROUND(@SoCau * 0.7, 0) END;
    IF EXISTS (SELECT 1 FROM @Dem WHERE CUNGTRINHDO < @CanCungMuc OR THAPHON < @SoCau - @CanCungMuc)
        THROW 51110, N'Bộ đề chưa đủ câu hỏi theo trình độ đã chọn.', 1;

    IF @ThaoTac = 'I'
    BEGIN
        INSERT dbo.GIAOVIEN_DANGKY (MAGV, MALOP, MAMH, LAN, TRINHDO, NGAYTHI, SOCAUTHI, THOIGIAN)
        VALUES (@MaGV, @MaLop, @MaMH, @Lan, @TrinhDo, @NgayThi, @SoCau, @ThoiGian);
        RETURN;
    END;

    IF @ThaoTac = 'U'
    BEGIN
        UPDATE dbo.GIAOVIEN_DANGKY SET MAGV = @MaGV, TRINHDO = @TrinhDo, NGAYTHI = @NgayThi,
            SOCAUTHI = @SoCau, THOIGIAN = @ThoiGian
        WHERE MALOP = @MaLop AND MAMH = @MaMH AND LAN = @Lan
          AND (MAGV = @MaGV OR MAGV IS NULL) AND (NGAYTHI IS NULL OR NGAYTHI >= DATEADD(day, 1, @HomNay));
        IF @@ROWCOUNT = 0 THROW 51108, N'Chỉ sửa được ca chưa tới ngày thi do mình đăng ký hoặc chưa có người phụ trách.', 1;
        RETURN;
    END;

    THROW 51100, N'Thao tác không hợp lệ.', 1;
END;
GO

COMMIT TRANSACTION;
GO
