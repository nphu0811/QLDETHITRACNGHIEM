import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.lang.reflect.Field;
import javax.imageio.ImageIO;
import javax.swing.*;
import session.*;
import trangchu.TrangChu;

/** Isolated UI checks: no SQL, exam, printing or data changes. */
public class VerifyAppLayout {
    static Object field(Object object, String name) throws Exception {
        Field f = object.getClass().getDeclaredField(name);
        f.setAccessible(true);
        return f.get(object);
    }
    static void click(Object frame, String name) throws Exception {
        ((JButton) field(frame, name)).doClick(0);
    }
    static void save(JFrame frame, String directory, String name) throws Exception {
        frame.validate();
        Container content = frame.getContentPane();
        BufferedImage image = new BufferedImage(content.getWidth(), content.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
        content.printAll(graphics);
        graphics.dispose();
        ImageIO.write(image, "png", new File(directory, name + ".png"));
    }
    static void closePolicy(JFrame frame) {
        if (frame.getDefaultCloseOperation() != JFrame.EXIT_ON_CLOSE)
            throw new AssertionError("X must exit: " + frame.getClass());
        if (frame.getWindowListeners().length != 0)
            throw new AssertionError("Unexpected window navigation listener");
    }
    static void verifyTeacher(String directory) throws Exception {
        sessionGiaoVien gv = new sessionGiaoVien();
        gv.setMaGV("GV001"); gv.setHo("Nguyễn Văn"); gv.setTen("An");
        TrangChu home = new TrangChu(gv);
        home.setVisible(true);
        closePolicy(home);
        if (((JPanel) field(home, "pnlTxtMaLop")).isVisible()) throw new AssertionError("Teacher must not show student class field");
        save(home, directory, "home-teacher");
        String[][] routes = {
            {"btnMonHoc", "quanly.NhapMonHoc"}, {"btnLopSinhVien", "quanly.NhapLopSinhVien"},
            {"btnGiaoVien", "quanly.NhapGiaoVien"}, {"btnBoDe", "quanly.NhapBoDe"},
            {"btnChuanBiThi", "quanly.ChuanBiThi"}, {"btnBangDiem", "baocao.BangDiemMonHoc"},
            {"btnTaoTaiKhoan", "taikhoan.TaoTaiKhoan"}, {"btnDoiMatKhau", "taikhoan.DoiMatKhau"},
            {"btnSaoLuuPhucHoi", "hethong.SaoLuuPhucHoi"}
        };
        int x = -1, width = -1, y = -1;
        for (String[] route : routes) {
            JButton button = (JButton) field(home, route[0]);
            if (x >= 0 && (button.getX() != x || button.getWidth() != width || button.getY() <= y))
                throw new AssertionError("Teacher menu must be one vertical column");
            x = button.getX(); width = button.getWidth(); y = button.getY();
            click(home, route[0]); home.validate();
            JFrame form = (JFrame) field(home, "formDangMo");
            if (!form.getClass().getName().equals(route[1]) || field(form, "nguoiDung") != gv)
                throw new AssertionError("Wrong embedded form/session");
            if (!home.isVisible() || form.isVisible()) throw new AssertionError("Teacher menu must persist");
            if (!Boolean.TRUE.equals(button.getClientProperty("selected")))
                throw new AssertionError("Active menu not highlighted");
            int selected = 0;
            for (Field f : TrangChu.class.getDeclaredFields()) {
                if (f.getType() != JButton.class) continue;
                f.setAccessible(true);
                JButton b = (JButton) f.get(home);
                if (Boolean.TRUE.equals(b.getClientProperty("selected"))) selected++;
            }
            if (selected != 1) throw new AssertionError("Exactly one selected menu is required");
            closePolicy(form);
            if (route[0].equals("btnMonHoc")) save(home, directory, "teacher-monhoc");
            if (route[0].equals("btnBangDiem")) {
                click(form, "btnXemBanIn");
                JFrame preview = (JFrame) field(home, "formDangMo");
                if (!preview.getClass().getName().equals("baocao.InBangDiem")) throw new AssertionError("Preview missing");
                if (!Boolean.TRUE.equals(button.getClientProperty("selected"))) throw new AssertionError("Preview lost selected menu");
                click(preview, "btnDong");
            }
        }
        click(home, "btnDoiMatKhau");
        click(field(home, "formDangMo"), "btnHuy");
        if (field(home, "formDangMo") != null || !home.isVisible()) throw new AssertionError("Cancel must return to same teacher home");
        if (!((JTextField) field(home, "txtMaNguoiDung")).getText().equals("GV001")) throw new AssertionError("Home lost teacher info");
        home.dispose();
        System.out.println("PASS teacher: persistent sidebar, nine destinations, selected state, preview/back, session and X exit policy");
    }
    static void verifyStudent(String directory) throws Exception {
        sessionSinhVien sv = new sessionSinhVien();
        sv.setMaSV("SV001"); sv.setHo("Trần Thị"); sv.setTen("Bình");
        sv.setMaLop("D21CQCN01");
        TrangChu home = new TrangChu(sv);
        home.setVisible(true); home.validate(); closePolicy(home);
        JTextField maLop = (JTextField) field(home, "txtMaLop");
        if (!maLop.getText().equals("D21CQCN01") || maLop.isEditable() || !maLop.isShowing()) {
            throw new AssertionError("Class field must show the session class and be read-only");
        }
        save(home, directory, "home-student");
        JButton left = (JButton) field(home, "btnChonCaThi"), right = (JButton) field(home, "btnLamBai");
        if (left.getWidth() != right.getWidth() || left.getY() != right.getY()) throw new AssertionError("Student columns uneven");
        if (((JButton) field(home, "btnMonHoc")).isVisible()) throw new AssertionError("Student management menu visible");
        click(home, "btnLamBai");
        if (home.isDisplayable()) throw new AssertionError("Student navigation must dispose source");
        for (Window window : Window.getWindows()) if (window.isVisible()) {
            JFrame form = (JFrame) window;
            closePolicy(form);
            if (field(form, "nguoiDung") != sv) throw new AssertionError("Student session lost");
            if (!((JButton) field(form, "btnTroVe")).isVisible()) throw new AssertionError("Return button missing");
            save(form, directory, "student-lambai");
            form.dispose();
        }
        JFrame review = new baocao.XemKetQua(sv);
        review.setVisible(true); save(review, directory, "student-review");
        if (!((JButton) field(review, "btnTroVe")).isVisible()) throw new AssertionError("Review return button missing");
        review.dispose();
        System.out.println("PASS student: balanced columns, original window navigation, session and new return buttons");
    }
    public static void main(String[] args) throws Exception {
        new File(args[0]).mkdirs();
        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        SwingUtilities.invokeAndWait(() -> {
            try { verifyTeacher(args[0]); verifyStudent(args[0]); }
            catch (Exception e) { throw new RuntimeException(e); }
            finally { for (Window w : Window.getWindows()) w.dispose(); }
        });
    }
}
