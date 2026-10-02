package trangchu;

import javax.swing.JFrame;
import session.SessionDangNhap;
import session.sessionGiaoVien;

// Chỉ điều hướng giao diện, không xử lý nghiệp vụ của các form.
public class DieuHuong {
    public static void moForm(JFrame hienTai, JFrame formMoi, SessionDangNhap nguoiDung) {
        if (nguoiDung instanceof sessionGiaoVien) {
            Object menu = hienTai.getRootPane().getClientProperty("menuGiaoVien");
            TrangChu khung = hienTai instanceof TrangChu
                    ? (TrangChu) hienTai : menu instanceof TrangChu ? (TrangChu) menu : null;
            if (khung != null) {
                if (formMoi instanceof TrangChu) {
                    khung.hienThiTrangChu();
                    formMoi.dispose();
                } else {
                    khung.hienThiForm(formMoi);
                }
                return;
            }
        }
        formMoi.setVisible(true);
        hienTai.dispose();
    }
}
