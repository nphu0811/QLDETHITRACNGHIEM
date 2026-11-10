package Helper;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public final class DateHelper {
    private static final DateTimeFormatter DINH_DANG = DateTimeFormatter.ofPattern("dd/MM/uuuu")
            .withResolverStyle(ResolverStyle.STRICT);

    private DateHelper() {
    }

    public static String dinhDang(Date ngay) {
        return ngay == null ? "" : ngay.toLocalDate().format(DINH_DANG);
    }

    public static Date docNgay(String chuoi) {
        try {
            if (chuoi != null && chuoi.length() == 10) {
                LocalDate ngay = LocalDate.parse(chuoi, DINH_DANG);
                if (ngay.getYear() >= 1) {
                    return Date.valueOf(ngay);
                }
            }
        } catch (DateTimeParseException e) {
            // Ngày sai và định dạng sai dùng chung thông báo bên dưới.
        }
        throw new IllegalArgumentException("Nhập ngày hợp lệ theo dạng dd/MM/yyyy.");
    }
}
