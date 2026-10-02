import java.lang.reflect.Field;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import session.SessionDangNhap;
import session.sessionGiaoVien;
import session.sessionSinhVien;
import trangchu.TrangChu;

/** Checks the complete home menu for each session type without SQL access. */
public class VerifyHomeRoles {
    private static final Set<String> COMMON = Set.of("btnTrangChu", "btnDangXuat", "btnThoat");
    private static final Set<String> STUDENT = Set.of("btnChonCaThi", "btnLamBai",
            "btnKetQua", "btnXemBaiThi", "btnDangXuat", "btnThoat");
    private static final Set<String> TEACHER = Set.of("btnMonHoc", "btnLopSinhVien",
            "btnGiaoVien", "btnBoDe", "btnChuanBiThi", "btnBangDiem", "btnTaoTaiKhoan",
            "btnDoiMatKhau", "btnSaoLuuPhucHoi", "btnDangXuat", "btnThoat", "btnTrangChu");

    private static void check(SessionDangNhap session, String role) throws Exception {
        TrangChu home = new TrangChu(session);
        try {
            int buttons = 0;
            for (Field field : TrangChu.class.getDeclaredFields()) {
                if (field.getType() != JButton.class) continue;
                field.setAccessible(true);
                JButton button = (JButton) field.get(home);
                Set<String> visible = role.equals("teacher") ? TEACHER
                        : role.equals("student") ? STUDENT : COMMON;
                boolean expected = visible.contains(field.getName());
                if (button.isVisible() != expected) {
                    throw new AssertionError(role + ": unexpected visibility of " + field.getName());
                }
                buttons++;
            }
            if (buttons != 16) throw new AssertionError("Menu changed: " + buttons + " buttons");
            System.out.println(role + ": all " + buttons + " menu buttons have correct visibility");
        } finally {
            home.dispose();
        }
    }

    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            try {
                check(new sessionGiaoVien(), "teacher");
                check(new sessionSinhVien(), "student");
                check(null, "no session");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
}
