
CREATE   PROCEDURE dbo.SP_LayCaThiSinhVien
    @MASV    NCHAR(8),
    @MAMH    NCHAR(5) = NULL,
    @NGAYTHI DATE = NULL,
    @LAN     SMALLINT = NULL
AS
BEGIN
    SET NOCOUNT ON;

    SELECT
        RTRIM(dk.MAMH) AS MAMH,
        mh.TENMH,
        dk.NGAYTHI,
        dk.LAN,
        RTRIM(dk.TRINHDO) AS TRINHDO,
        dk.SOCAUTHI,
        dk.THOIGIAN
    FROM dbo.SINHVIEN AS sv
    INNER JOIN dbo.GIAOVIEN_DANGKY AS dk
        ON dk.MALOP = sv.MALOP
    INNER JOIN dbo.MONHOC AS mh
        ON mh.MAMH = dk.MAMH
    WHERE
        sv.MASV = @MASV

        -- Không chọn môn thì bỏ qua lọc môn.
        AND (@MAMH IS NULL OR dk.MAMH = @MAMH)

        -- Không nhập ngày thì bỏ qua lọc ngày.
        AND (
            @NGAYTHI IS NULL
            OR (
                dk.NGAYTHI >= @NGAYTHI
                AND dk.NGAYTHI < DATEADD(DAY, 1, @NGAYTHI)
            )
        )

        -- Không chọn lần thì bỏ qua lọc lần thi.
        AND (@LAN IS NULL OR dk.LAN = @LAN)

    ORDER BY dk.NGAYTHI, mh.TENMH, dk.LAN;
END;

