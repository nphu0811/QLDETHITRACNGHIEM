import java.awt.Window;
import java.lang.reflect.Field;
import javax.swing.*;
import session.sessionSinhVien;
import baocao.XemKetQua;
import thi.KetQuaThi;

/** Checks student identity isolation and read-only exam detail controls. */
public class VerifyReviewSession {
    static Object field(Object frame, String name) throws Exception {
        Field f = frame.getClass().getDeclaredField(name);
        f.setAccessible(true);
        return f.get(frame);
    }
    static sessionSinhVien student(String ma, String lop) {
        sessionSinhVien sv = new sessionSinhVien();
        sv.setMaSV(ma); sv.setHo("Nguyen"); sv.setTen(ma);
        sv.setMaLop("D21"); sv.setTenLop(lop);
        return sv;
    }
    static void check(XemKetQua form, sessionSinhVien sv) throws Exception {
        if (!((JTextField) field(form, "txtLogin")).getText().equals(sv.getMaSV())) throw new AssertionError("Wrong student ID");
        if (!((JTextField) field(form, "txtHoTen")).getText().equals("Nguyen " + sv.getMaSV())) throw new AssertionError("Wrong name");
        JTextField lop = (JTextField) field(form, "txtLop");
        if (!lop.getText().equals(sv.getTenLop())) throw new AssertionError("Wrong class");
        for (String name : new String[]{"txtLogin", "txtHoTen", "txtLop", "txtMonHoc", "txtTrinhDo", "txtLanThi", "txtNgayThi", "txtDiem", "txtSoCauDung", "txtTongSoCau"}) {
            if (((javax.swing.text.JTextComponent) field(form, name)).isEditable()) throw new AssertionError("Editable detail: " + name);
        }
        for (String name : new String[]{"txtMonHoc", "txtTrinhDo", "txtLanThi"}) {
            if (!((JTextField) field(form, name)).getText().isEmpty()) throw new AssertionError("Fake exam info");
        }
        for (Field f : XemKetQua.class.getDeclaredFields()) {
            if (JComboBox.class.isAssignableFrom(f.getType())) throw new AssertionError("Combobox remains");
        }
        JTable table = (JTable) field(form, "tblChiTietBaiThi");
        String[] columns = {"Câu số (trong bộ đề)", "Đã chọn", "Đáp án"};
        if (table.getColumnCount() != columns.length) throw new AssertionError("Wrong table columns");
        for (int i = 0; i < columns.length; i++) {
            if (!table.getColumnName(i).equals(columns[i]) || table.getModel().isCellEditable(0, i))
                throw new AssertionError("Wrong or editable table column");
        }
        if (((JButton) field(form, "btnTraCuu")).isVisible()) throw new AssertionError("Other student lookup visible");
    }
    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            try {
                sessionSinhVien a = student("SV001", "Class A"), b = student("SV002", "Class B");
                XemKetQua one = new XemKetQua(a), two = new XemKetQua(b);
                check(one, a); check(two, b); check(one, a);
                one.dispose(); two.dispose();
                KetQuaThi result = new KetQuaThi(a);
                result.setVisible(true);
                ((JButton) field(result, "btnXemChiTiet")).doClick(0);
                if (result.isDisplayable()) throw new AssertionError("Old result form still open");
                XemKetQua details = null;
                for (Window w : Window.getWindows()) if (w instanceof XemKetQua && w.isVisible()) details = (XemKetQua) w;
                if (details == null || field(details, "nguoiDung") != a) throw new AssertionError("Detail button lost session");
                check(details, a); details.dispose();
                XemKetQua noSession = new XemKetQua();
                if (!((JTextField) field(noSession, "txtLogin")).getText().isEmpty()) throw new AssertionError("Stale identity");
                noSession.dispose();
                System.out.println("PASS: two student sessions isolated; fields read-only; detail navigation preserves session; no session leaves identity empty.");
            } catch (Exception e) { throw new RuntimeException(e); }
            finally { for (Window w : Window.getWindows()) w.dispose(); }
        });
    }
}
