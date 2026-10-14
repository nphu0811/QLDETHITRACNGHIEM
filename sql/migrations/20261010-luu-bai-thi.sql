-- Chạy với database THITRACNGHIEM được chọn trong SSMS.
-- Script có thể chạy lại; không xóa dữ liệu đã có.
SET NOCOUNT ON;
SET XACT_ABORT ON;
GO

IF OBJECT_ID('dbo.TRANGTHAITHI', 'U') IS NULL
BEGIN
    CREATE TABLE dbo.TRANGTHAITHI (
        MASV NCHAR(8) NOT NULL,
        MAMH NCHAR(5) NOT NULL,
        LAN SMALLINT NOT NULL,
        THUTU INT NOT NULL,
        CAUHOI INT NOT NULL,
        TENMH NVARCHAR(50) NOT NULL,
        NGAYTHI DATE NOT NULL,
        TRINHDO CHAR(1) NOT NULL,
        SOCAUTHI SMALLINT NOT NULL,
        THOIGIAN SMALLINT NOT NULL,
        BATDAU DATETIME2(3) NOT NULL,
        HANKETTHUC DATETIME2(3) NOT NULL,
        CAUDANGXEM INT NOT NULL,
        TRINHDOCAU CHAR(1) NOT NULL,
        NOIDUNG NVARCHAR(500) NOT NULL,
        A NVARCHAR(200) NOT NULL,
        B NVARCHAR(200) NOT NULL,
        C NVARCHAR(200) NOT NULL,
        D NVARCHAR(200) NOT NULL,
        DAP_AN CHAR(1) NOT NULL,
        CAUTRALOI NVARCHAR(1) NOT NULL CONSTRAINT DF_TRANGTHAITHI_TraLoi DEFAULT N'',
        CONSTRAINT PK_TRANGTHAITHI PRIMARY KEY (MASV, MAMH, LAN, THUTU),
        CONSTRAINT UQ_TRANGTHAITHI_Cau UNIQUE (MASV, MAMH, LAN, CAUHOI),
        CONSTRAINT CK_TRANGTHAITHI_ViTri CHECK (CAUDANGXEM BETWEEN 1 AND SOCAUTHI),
        CONSTRAINT CK_TRANGTHAITHI_TraLoi CHECK (CAUTRALOI IN (N'', N'A', N'B', N'C', N'D'))
    );
END;
GO

IF OBJECT_ID('dbo.CHITIETBAITHI', 'U') IS NULL
BEGIN
    -- Giữ nguyên toàn bộ bản chụp của bài thi khi chuyển sang chi tiết.
    SELECT TOP (0) * INTO dbo.CHITIETBAITHI FROM dbo.TRANGTHAITHI;
    ALTER TABLE dbo.CHITIETBAITHI ADD NGAYNOP DATETIME2(3) NOT NULL;
    ALTER TABLE dbo.CHITIETBAITHI ADD CONSTRAINT PK_CHITIETBAITHI
        PRIMARY KEY (MASV, MAMH, LAN, THUTU);
    ALTER TABLE dbo.CHITIETBAITHI ADD CONSTRAINT FK_CHITIETBAITHI_BANGDIEM
        FOREIGN KEY (MASV, MAMH, LAN) REFERENCES dbo.BANGDIEM (MASV, MAMH, LAN);
END;
GO

CREATE OR ALTER PROCEDURE dbo.SP_DocBaiThi
    @MASV NCHAR(8),
    @MAMH NCHAR(5) = NULL,
    @LAN SMALLINT = NULL
AS
BEGIN
    SET NOCOUNT ON;
    IF @MAMH IS NULL OR @LAN IS NULL
    BEGIN
        SELECT TOP (1) @MAMH = MAMH, @LAN = LAN
        FROM dbo.CHITIETBAITHI
        WHERE MASV = @MASV
        ORDER BY NGAYNOP DESC, MAMH, LAN DESC;
    END;

    IF EXISTS (SELECT 1 FROM dbo.BANGDIEM WHERE MASV = @MASV AND MAMH = @MAMH AND LAN = @LAN)
    BEGIN
        SELECT t.*, CAST(1 AS BIT) AS DANOP, CAST(0 AS BIGINT) AS CONLAI_MS, bd.DIEM,
            SUM(CASE WHEN t.CAUTRALOI <> N'' AND t.CAUTRALOI = t.DAP_AN THEN 1 ELSE 0 END)
                OVER () AS SOCAUDUNG
        FROM dbo.CHITIETBAITHI t
        JOIN dbo.BANGDIEM bd ON bd.MASV = t.MASV AND bd.MAMH = t.MAMH AND bd.LAN = t.LAN
        WHERE t.MASV = @MASV AND t.MAMH = @MAMH AND t.LAN = @LAN
        ORDER BY t.THUTU;
    END
    ELSE
    BEGIN
        -- Không gửi đáp án đúng ra màn hình trong khi đang làm bài.
        SELECT MASV, MAMH, LAN, THUTU, CAUHOI, TENMH, NGAYTHI, TRINHDO,
            SOCAUTHI, THOIGIAN, BATDAU, HANKETTHUC, CAUDANGXEM, TRINHDOCAU,
            NOIDUNG, A, B, C, D, CAST(NULL AS CHAR(1)) AS DAP_AN, CAUTRALOI,
            CAST(NULL AS DATETIME2(3)) AS NGAYNOP,
            CAST(0 AS BIT) AS DANOP,
            DATEDIFF_BIG(MILLISECOND, SYSUTCDATETIME(), HANKETTHUC) AS CONLAI_MS,
            CAST(0 AS FLOAT) AS DIEM, 0 AS SOCAUDUNG
        FROM dbo.TRANGTHAITHI
        WHERE MASV = @MASV AND MAMH = @MAMH AND LAN = @LAN
        ORDER BY THUTU;
    END;
END;
GO

CREATE OR ALTER PROCEDURE dbo.SP_NopBaiThi
    @MASV NCHAR(8), @MAMH NCHAR(5), @LAN SMALLINT
AS
BEGIN
    SET NOCOUNT ON;
    SET XACT_ABORT ON;
    BEGIN TRY
        BEGIN TRANSACTION;
        -- Các thao tác của cùng sinh viên phải lần lượt, kể cả mở app hai lần.
        DECLARE @Khoa INT, @TenKhoa NVARCHAR(255) = N'BaiThi:' + RTRIM(@MASV);
        EXEC @Khoa = sys.sp_getapplock @Resource = @TenKhoa,
            @LockMode = 'Exclusive', @LockOwner = 'Transaction', @LockTimeout = 10000;
        IF @Khoa < 0 THROW 50001, N'Bài thi đang được xử lý, vui lòng thử lại.', 1;

        IF NOT EXISTS (SELECT 1 FROM dbo.BANGDIEM WHERE MASV = @MASV AND MAMH = @MAMH AND LAN = @LAN)
        BEGIN
            DECLARE @Tong INT, @Dung INT, @SoCau INT, @Ngay DATE;
            SELECT @Tong = COUNT(*), @SoCau = MAX(SOCAUTHI), @Ngay = MAX(NGAYTHI),
                @Dung = SUM(CASE WHEN CAUTRALOI <> N'' AND CAUTRALOI = DAP_AN THEN 1 ELSE 0 END)
            FROM dbo.TRANGTHAITHI
            WHERE MASV = @MASV AND MAMH = @MAMH AND LAN = @LAN;
            IF @Tong = 0 OR @Tong <> @SoCau
                THROW 50004, N'Không tìm thấy đầy đủ trạng thái bài thi.', 1;

            INSERT dbo.BANGDIEM (MASV, MAMH, LAN, NGAYTHI, DIEM)
            VALUES (@MASV, @MAMH, @LAN, @Ngay, ROUND(10.0 * @Dung / @Tong, 2));

            INSERT dbo.CHITIETBAITHI (MASV, MAMH, LAN, THUTU, CAUHOI, TENMH, NGAYTHI,
                TRINHDO, SOCAUTHI, THOIGIAN, BATDAU, HANKETTHUC, CAUDANGXEM,
                TRINHDOCAU, NOIDUNG, A, B, C, D, DAP_AN, CAUTRALOI, NGAYNOP)
            SELECT MASV, MAMH, LAN, THUTU, CAUHOI, TENMH, NGAYTHI,
                TRINHDO, SOCAUTHI, THOIGIAN, BATDAU, HANKETTHUC, CAUDANGXEM,
                TRINHDOCAU, NOIDUNG, A, B, C, D, DAP_AN, CAUTRALOI, SYSUTCDATETIME()
            FROM dbo.TRANGTHAITHI
            WHERE MASV = @MASV AND MAMH = @MAMH AND LAN = @LAN;

            DELETE dbo.TRANGTHAITHI WHERE MASV = @MASV AND MAMH = @MAMH AND LAN = @LAN;
        END;
        COMMIT TRANSACTION;
    END TRY
    BEGIN CATCH
        IF @@TRANCOUNT > 0 ROLLBACK TRANSACTION;
        THROW;
    END CATCH;
    -- Nộp lại chỉ trả kết quả cũ, không ghi điểm hay chi tiết lần nữa.
    EXEC dbo.SP_DocBaiThi @MASV, @MAMH, @LAN;
END;
GO

CREATE OR ALTER PROCEDURE dbo.SP_MoBaiThi
    @MASV NCHAR(8), @MAMH NCHAR(5), @LAN SMALLINT
AS
BEGIN
    SET NOCOUNT ON;
    SET XACT_ABORT ON;
    BEGIN TRY
        BEGIN TRANSACTION;
        DECLARE @Khoa INT, @TenKhoa NVARCHAR(255) = N'BaiThi:' + RTRIM(@MASV);
        EXEC @Khoa = sys.sp_getapplock @Resource = @TenKhoa,
            @LockMode = 'Exclusive', @LockOwner = 'Transaction', @LockTimeout = 10000;
        IF @Khoa < 0 THROW 50001, N'Bài thi đang được xử lý, vui lòng thử lại.', 1;

        -- Có bài đang làm thì tiếp tục bài đó, không tạo thêm một bài khác.
        IF EXISTS (SELECT 1 FROM dbo.TRANGTHAITHI WHERE MASV = @MASV)
            SELECT TOP (1) @MAMH = MAMH, @LAN = LAN
            FROM dbo.TRANGTHAITHI WHERE MASV = @MASV ORDER BY BATDAU, THUTU;

        IF NOT EXISTS (SELECT 1 FROM dbo.BANGDIEM WHERE MASV = @MASV AND MAMH = @MAMH AND LAN = @LAN)
            AND NOT EXISTS (SELECT 1 FROM dbo.TRANGTHAITHI WHERE MASV = @MASV AND MAMH = @MAMH AND LAN = @LAN)
        BEGIN
            DECLARE @Ngay DATE, @TrinhDo CHAR(1), @SoCau SMALLINT, @Phut SMALLINT, @Ten NVARCHAR(50);
            SELECT @Ngay = CONVERT(DATE, dk.NGAYTHI), @TrinhDo = dk.TRINHDO,
                @SoCau = dk.SOCAUTHI, @Phut = dk.THOIGIAN, @Ten = mh.TENMH
            FROM dbo.SINHVIEN sv
            JOIN dbo.GIAOVIEN_DANGKY dk ON dk.MALOP = sv.MALOP
            JOIN dbo.MONHOC mh ON mh.MAMH = dk.MAMH
            WHERE sv.MASV = @MASV AND dk.MAMH = @MAMH AND dk.LAN = @LAN;

            IF @Ngay IS NULL THROW 50002, N'Ca thi không thuộc lớp của sinh viên.', 1;
            -- Ngày nghiệp vụ Việt Nam; các mốc đếm thời lượng lưu theo UTC.
            IF @Ngay <> CONVERT(DATE, DATEADD(HOUR, 7, SYSUTCDATETIME()))
                THROW 50002, N'Chỉ được bắt đầu vào đúng ngày thi.', 1;
            IF @SoCau IS NULL OR @SoCau <= 0 OR @Phut IS NULL OR @Phut <= 0
                OR @TrinhDo IS NULL OR @TrinhDo NOT IN ('A', 'B', 'C') OR @Ten IS NULL
                THROW 50002, N'Thông số ca thi không hợp lệ.', 1;

            DECLARE @CungTrinhDo INT = @SoCau, @ThapHon INT = 0;
            IF @TrinhDo IN ('A', 'B')
            BEGIN
                SET @CungTrinhDo = CONVERT(INT, ROUND(@SoCau * 0.7, 0));
                SET @ThapHon = @SoCau - @CungTrinhDo;
            END;

            -- Chụp nội dung ngay khi chọn, không đọc lại BODE ở bước lưu cuối.
            DECLARE @Cau TABLE (
                CAUHOI INT PRIMARY KEY, TRINHDO CHAR(1), NOIDUNG NVARCHAR(500),
                A NVARCHAR(200), B NVARCHAR(200), C NVARCHAR(200), D NVARCHAR(200), DAP_AN CHAR(1)
            );
            INSERT @Cau
            SELECT TOP (@CungTrinhDo) CAUHOI, TRINHDO, NOIDUNG, A, B, C, D, DAP_AN FROM dbo.BODE
            WHERE MAMH = @MAMH AND TRINHDO = @TrinhDo AND DAP_AN IN ('A', 'B', 'C', 'D')
                AND NOIDUNG IS NOT NULL AND A IS NOT NULL AND B IS NOT NULL AND C IS NOT NULL AND D IS NOT NULL
            ORDER BY NEWID();
            IF @@ROWCOUNT <> @CungTrinhDo
                THROW 50006, N'Bộ đề chưa đủ câu hỏi đúng trình độ.', 1;

            INSERT @Cau
            SELECT TOP (@ThapHon) CAUHOI, TRINHDO, NOIDUNG, A, B, C, D, DAP_AN FROM dbo.BODE b
            WHERE MAMH = @MAMH
                AND ((@TrinhDo = 'A' AND TRINHDO IN ('B', 'C')) OR (@TrinhDo = 'B' AND TRINHDO = 'C'))
                AND DAP_AN IN ('A', 'B', 'C', 'D')
                AND NOIDUNG IS NOT NULL AND A IS NOT NULL AND B IS NOT NULL AND C IS NOT NULL AND D IS NOT NULL
                AND NOT EXISTS (SELECT 1 FROM @Cau c WHERE c.CAUHOI = b.CAUHOI)
            ORDER BY NEWID();
            IF @@ROWCOUNT <> @ThapHon
                THROW 50006, N'Bộ đề chưa đủ câu hỏi trình độ thấp hơn.', 1;

            DECLARE @BatDau DATETIME2(3) = SYSUTCDATETIME();
            INSERT dbo.TRANGTHAITHI (MASV, MAMH, LAN, THUTU, CAUHOI, TENMH, NGAYTHI,
                TRINHDO, SOCAUTHI, THOIGIAN, BATDAU, HANKETTHUC, CAUDANGXEM,
                TRINHDOCAU, NOIDUNG, A, B, C, D, DAP_AN, CAUTRALOI)
            SELECT @MASV, @MAMH, @LAN, ROW_NUMBER() OVER (ORDER BY NEWID()), b.CAUHOI, @Ten, @Ngay,
                @TrinhDo, @SoCau, @Phut, @BatDau, DATEADD(MINUTE, @Phut, @BatDau), 1,
                b.TRINHDO, b.NOIDUNG, b.A, b.B, b.C, b.D, b.DAP_AN, N''
            FROM @Cau b;
            IF @@ROWCOUNT <> @SoCau THROW 50006, N'Chưa tạo được đầy đủ câu hỏi của bài thi.', 1;
        END;
        COMMIT TRANSACTION;
    END TRY
    BEGIN CATCH
        IF @@TRANCOUNT > 0 ROLLBACK TRANSACTION;
        THROW;
    END CATCH;

    IF EXISTS (SELECT 1 FROM dbo.TRANGTHAITHI
        WHERE MASV = @MASV AND MAMH = @MAMH AND LAN = @LAN AND HANKETTHUC <= SYSUTCDATETIME())
        EXEC dbo.SP_NopBaiThi @MASV, @MAMH, @LAN;
    ELSE
        EXEC dbo.SP_DocBaiThi @MASV, @MAMH, @LAN;
END;
GO

CREATE OR ALTER PROCEDURE dbo.SP_LuuTienDoThi
    @MASV NCHAR(8), @MAMH NCHAR(5), @LAN SMALLINT,
    @THUTU INT, @CAUTRALOI NVARCHAR(1), @CAUDANGXEM INT
AS
BEGIN
    SET NOCOUNT ON;
    SET XACT_ABORT ON;
    BEGIN TRY
        BEGIN TRANSACTION;
        DECLARE @Khoa INT, @TenKhoa NVARCHAR(255) = N'BaiThi:' + RTRIM(@MASV);
        EXEC @Khoa = sys.sp_getapplock @Resource = @TenKhoa,
            @LockMode = 'Exclusive', @LockOwner = 'Transaction', @LockTimeout = 10000;
        IF @Khoa < 0 THROW 50001, N'Bài thi đang được xử lý, vui lòng thử lại.', 1;
        DECLARE @Han DATETIME2(3), @SoCau INT;
        SELECT TOP (1) @Han = HANKETTHUC, @SoCau = SOCAUTHI FROM dbo.TRANGTHAITHI
        WHERE MASV = @MASV AND MAMH = @MAMH AND LAN = @LAN;
        IF @Han IS NULL THROW 50004, N'Bài thi đã nộp hoặc không tồn tại.', 1;
        IF SYSUTCDATETIME() >= @Han THROW 50003, N'Bài thi đã hết thời gian.', 1;
        IF @THUTU IS NULL OR @THUTU < 1 OR @THUTU > @SoCau
            OR @CAUDANGXEM IS NULL OR @CAUDANGXEM < 1 OR @CAUDANGXEM > @SoCau
            OR (@CAUTRALOI IS NOT NULL AND @CAUTRALOI NOT IN (N'', N'A', N'B', N'C', N'D'))
            THROW 50005, N'Câu hỏi hoặc lựa chọn không hợp lệ.', 1;

        -- NULL: chỉ đổi vị trí. Chuỗi rỗng: xóa đáp án đã chọn.
        IF @CAUTRALOI IS NOT NULL
            UPDATE dbo.TRANGTHAITHI SET CAUTRALOI = @CAUTRALOI
            WHERE MASV = @MASV AND MAMH = @MAMH AND LAN = @LAN AND THUTU = @THUTU;
        UPDATE dbo.TRANGTHAITHI SET CAUDANGXEM = @CAUDANGXEM
        WHERE MASV = @MASV AND MAMH = @MAMH AND LAN = @LAN;
        COMMIT TRANSACTION;
    END TRY
    BEGIN CATCH
        IF @@TRANCOUNT > 0 ROLLBACK TRANSACTION;
        THROW;
    END CATCH;
END;
GO

CREATE OR ALTER PROCEDURE dbo.SP_LayBaiThiDangDo @MASV NCHAR(8)
AS
BEGIN
    SET NOCOUNT ON;
    SELECT TOP (1) MAMH, TENMH, NGAYTHI, LAN, TRINHDO, SOCAUTHI, THOIGIAN
    FROM dbo.TRANGTHAITHI WHERE MASV = @MASV ORDER BY BATDAU, THUTU;
END;
GO

CREATE OR ALTER PROCEDURE dbo.SP_LayCaThiSinhVien
    @MASV NCHAR(8), @MAMH NCHAR(5) = NULL, @NGAYTHI DATE = NULL, @LAN SMALLINT = NULL
AS
BEGIN
    SET NOCOUNT ON;
    DECLARE @HomNay DATE = CONVERT(DATE, DATEADD(HOUR, 7, SYSUTCDATETIME()));
    WITH CaThi AS (
        SELECT dk.MAMH, mh.TENMH, CONVERT(DATE, dk.NGAYTHI) AS NGAYTHI,
            dk.LAN, dk.TRINHDO, dk.SOCAUTHI, dk.THOIGIAN
        FROM dbo.SINHVIEN sv
        JOIN dbo.GIAOVIEN_DANGKY dk ON dk.MALOP = sv.MALOP
        JOIN dbo.MONHOC mh ON mh.MAMH = dk.MAMH
        WHERE sv.MASV = @MASV AND CONVERT(DATE, dk.NGAYTHI) >= @HomNay
            AND NOT EXISTS (SELECT 1 FROM dbo.BANGDIEM bd
                WHERE bd.MASV = @MASV AND bd.MAMH = dk.MAMH AND bd.LAN = dk.LAN)
            AND NOT EXISTS (SELECT 1 FROM dbo.TRANGTHAITHI t
                WHERE t.MASV = @MASV AND t.MAMH = dk.MAMH AND t.LAN = dk.LAN)
        UNION ALL
        -- Bài đang làm vẫn xuất hiện nếu đã sang ngày hôm sau.
        SELECT MAMH, TENMH, NGAYTHI, LAN, TRINHDO, SOCAUTHI, THOIGIAN
        FROM dbo.TRANGTHAITHI WHERE MASV = @MASV AND THUTU = 1
    )
    SELECT RTRIM(MAMH) AS MAMH, TENMH, NGAYTHI, LAN, RTRIM(TRINHDO) AS TRINHDO, SOCAUTHI, THOIGIAN
    FROM CaThi
    WHERE (@MAMH IS NULL OR MAMH = @MAMH)
        AND (@NGAYTHI IS NULL OR NGAYTHI = @NGAYTHI)
        AND (@LAN IS NULL OR LAN = @LAN)
    ORDER BY NGAYTHI, TENMH, LAN;
END;
GO

IF DATABASE_PRINCIPAL_ID(N'sv') IS NOT NULL
BEGIN
    GRANT EXECUTE ON dbo.SP_MoBaiThi TO sv;
    GRANT EXECUTE ON dbo.SP_DocBaiThi TO sv;
    GRANT EXECUTE ON dbo.SP_LuuTienDoThi TO sv;
    GRANT EXECUTE ON dbo.SP_NopBaiThi TO sv;
    GRANT EXECUTE ON dbo.SP_LayBaiThiDangDo TO sv;
    GRANT EXECUTE ON dbo.SP_LayCaThiSinhVien TO sv;
END;
GO
