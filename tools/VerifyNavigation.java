import java.awt.Window;
import java.awt.event.WindowEvent;
import java.lang.reflect.Field;
import javax.swing.*;
import session.*;

public class VerifyNavigation {
    private static final String[][] ROUTES = {
        {"trangchu.TrangChu", "btnMonHoc", "quanly.NhapMonHoc"},
        {"trangchu.TrangChu", "btnLopSinhVien", "quanly.NhapLopSinhVien"},
        {"trangchu.TrangChu", "btnGiaoVien", "quanly.NhapGiaoVien"},
        {"trangchu.TrangChu", "btnBoDe", "quanly.NhapBoDe"},
        {"trangchu.TrangChu", "btnChuanBiThi", "quanly.ChuanBiThi"},
        {"trangchu.TrangChu", "btnChonCaThi", "thi.ChonCaThi"},
        {"trangchu.TrangChu", "btnLamBai", "thi.LamBaiThi"},
        {"trangchu.TrangChu", "btnKetQua", "thi.KetQuaThi"},
        {"trangchu.TrangChu", "btnXemBaiThi", "baocao.XemKetQua"},
        {"trangchu.TrangChu", "btnBangDiem", "baocao.BangDiemMonHoc"},
        {"trangchu.TrangChu", "btnTaoTaiKhoan", "taikhoan.TaoTaiKhoan"},
        {"trangchu.TrangChu", "btnDoiMatKhau", "taikhoan.DoiMatKhau"},
        {"trangchu.TrangChu", "btnSaoLuuPhucHoi", "hethong.SaoLuuPhucHoi"},
        {"thi.ChonCaThi", "btnBatDauThi", "thi.LamBaiThi"},
        {"thi.ChonCaThi", "btnXemBaiLanTruoc", "baocao.XemKetQua"},
        {"thi.ChonCaThi", "btnTroVe", "trangchu.TrangChu"},
        {"thi.KetQuaThi", "btnXemChiTiet", "baocao.XemKetQua"},
        {"thi.KetQuaThi", "btnVeTrangChu", "trangchu.TrangChu"},
        {"baocao.XemKetQua", "btnXemBanIn", "baocao.InBaiThi"},
        {"baocao.BangDiemMonHoc", "btnXemBanIn", "baocao.InBangDiem"},
        {"baocao.InBaiThi", "btnDong", "baocao.XemKetQua"},
        {"baocao.InBangDiem", "btnDong", "baocao.BangDiemMonHoc"},
        {"taikhoan.TaoTaiKhoan", "btnHuy", "trangchu.TrangChu"},
        {"taikhoan.DoiMatKhau", "btnHuy", "trangchu.TrangChu"}
    };
    static Object field(Object instance, String name) throws Exception {
        Field f = instance.getClass().getDeclaredField(name);
        f.setAccessible(true);
        return f.get(instance);
    }
    static JFrame open(String type, SessionDangNhap session) throws Exception {
        JFrame frame = (JFrame) Class.forName(type).getConstructor(SessionDangNhap.class).newInstance(session);
        frame.setVisible(true);
        return frame;
    }
    static void verifyDestination(JFrame source, String type, SessionDangNhap session) throws Exception {
        if (source.isDisplayable()) throw new AssertionError("Source not disposed: " + source.getClass());
        JFrame destination = null;
        for (Window w : Window.getWindows()) {
            if (!w.isVisible()) continue;
            if (destination != null) throw new AssertionError("Multiple visible windows");
            if (!w.getClass().getName().equals(type)) throw new AssertionError("Wrong destination: " + w.getClass());
            destination = (JFrame) w;
        }
        if (destination == null) throw new AssertionError("Missing destination: " + type);
        if (session != null && field(destination, "nguoiDung") != session)
            throw new AssertionError("Session lost: " + type);
        destination.dispose();
    }
    static void verifyRole(SessionDangNhap session) throws Exception {
        int count = 0;
        for (String[] route : ROUTES) {
            JFrame source = open(route[0], session);
            JButton button = (JButton) field(source, route[1]);
            if (!button.isVisible()) { source.dispose(); continue; }
            button.doClick(0);
            verifyDestination(source, route[2], session);
            count++;
        }
        // X now exits the app; never dispatch WINDOW_CLOSING inside this test.
        JFrame home = open("trangchu.TrangChu", session);
        ((JButton) field(home, "btnDangXuat")).doClick(0);
        verifyDestination(home, session instanceof sessionSinhVien
                ? "dangNhapdangKy.DangNhapSV" : "dangNhapdangKy.DangNhap", null);
        System.out.println(session.getClass().getSimpleName() + ": " + count + " transitions preserve session; logout returns to login");
    }
    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            try {
                sessionSinhVien sv = new sessionSinhVien();
                sv.setMaSV("SV001"); sv.setHo("Nguyen"); sv.setTen("An");
                sessionGiaoVien gv = new sessionGiaoVien();
                gv.setMaGV("GV001"); gv.setHo("Tran"); gv.setTen("Binh");
                verifyRole(sv);
            } catch (Exception e) { throw new RuntimeException(e); }
            finally { for (Window w : Window.getWindows()) w.dispose(); }
        });
    }
}
