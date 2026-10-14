import java.awt.Component;
import java.awt.Container;
import java.awt.Window;
import java.awt.event.MouseEvent;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import session.CaThi;
import session.MonHoc;
import session.sessionSinhVien;
import thi.ChonCaThi;
import thi.LamBaiThi;

/** Checks exam selection through the real Swing handlers; SQL is read only. */
public class VerifyCaThiSelection {
    static ChonCaThi form;
    static sessionSinhVien student;
    static final String today = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

    static Object field(String name) throws Exception {
        Field field = ChonCaThi.class.getDeclaredField(name);
        field.setAccessible(true);
        return field.get(form);
    }

    static JTable table() throws Exception { return (JTable) field("tblCaThi"); }
    static JButton start() throws Exception { return (JButton) field("btnBatDauThi"); }

    static void openChooser() throws Exception {
        SwingUtilities.invokeAndWait(() -> form = new ChonCaThi(student));
        long deadline = System.nanoTime() + 15_000_000_000L;
        boolean[] ready = {false};
        while (!ready[0] && System.nanoTime() < deadline) {
            SwingUtilities.invokeAndWait(() -> {
                try {
                    ready[0] = ((JComboBox<?>) field("cboMonHoc")).isEnabled() && table().getRowCount() > 0;
                } catch (Exception e) { throw new RuntimeException(e); }
            });
            Thread.sleep(40);
        }
        if (!ready[0]) throw new AssertionError("SQL exam list did not load");
    }

    static void fixtures() throws Exception {
        DefaultTableModel model = (DefaultTableModel) table().getModel();
        table().clearSelection();
        model.setRowCount(0);
        model.addRow(new Object[]{new MonHoc("AVCB", "Anh văn"), today, "1", "A", "20", "30"});
        model.addRow(new Object[]{new MonHoc("MMTCB", "Mạng máy tính"), today, "2", "B", "25", "40"});
        model.addRow(new Object[]{new MonHoc("CSDL", "Cơ sở dữ liệu"),
            LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), "1", "C", "10", "15"});
    }

    static int rowFor(String code) throws Exception {
        for (int row = 0; row < table().getRowCount(); row++) {
            MonHoc subject = (MonHoc) table().getModel().getValueAt(table().convertRowIndexToModel(row), 0);
            if (subject.getMaMH().equals(code)) return row;
        }
        throw new AssertionError("Missing test subject " + code);
    }

    static void select(String code, boolean mouseClick) throws Exception {
        int row = rowFor(code);
        table().setRowSelectionInterval(row, row);
        if (mouseClick) {
            MouseEvent event = new MouseEvent(table(), MouseEvent.MOUSE_CLICKED,
                System.currentTimeMillis(), 0, 1, 1, 1, false);
            for (var listener : table().getMouseListeners()) listener.mouseClicked(event);
        }
    }

    static void checkStart(String code, String name, int attempt, String level, int questions, int duration) throws Exception {
        start().doClick(0);
        CaThi exam = (CaThi) field("cathi");
        if (!name.equals(exam.getTenMH()))
            throw new AssertionError("Stale exam: expected " + name + ", got " + exam.getTenMH());
        String actualCode;
        try { actualCode = (String) CaThi.class.getMethod("getMaMH").invoke(exam); }
        catch (NoSuchMethodException e) { throw new AssertionError("CaThi must retain MAMH"); }
        if (!code.equals(actualCode) || !today.equals(exam.getNgaythi()) || exam.getLan() != attempt
                || !level.equals(exam.getTrinhDo()) || exam.getSoCauThi() != questions
                || exam.getThoiGianThi() != duration) throw new AssertionError("Wrong selected exam details");
        LamBaiThi opened = null;
        for (Window window : Window.getWindows())
            if (window instanceof LamBaiThi && window.isShowing()) opened = (LamBaiThi) window;
        if (opened == null || form.isDisplayable()) throw new AssertionError("Exam navigation failed");
        Field identity = LamBaiThi.class.getDeclaredField("nguoiDung");
        identity.setAccessible(true);
        if (identity.get(opened) != student) throw new AssertionError("Student session was lost");
        opened.dispose();
    }

    static JOptionPane message(Component component) {
        if (component instanceof JOptionPane) return (JOptionPane) component;
        if (component instanceof Container) {
            for (Component child : ((Container) component).getComponents()) {
                JOptionPane found = message(child);
                if (found != null) return found;
            }
        }
        return null;
    }

    static void checkBlocked(String expected) throws Exception {
        String[] actual = {null};
        Timer closer = new Timer(40, event -> {
            for (Window window : Window.getWindows()) {
                if (window instanceof JDialog && window.getOwner() == form && window.isShowing()) {
                    JOptionPane pane = message(window);
                    if (pane != null) {
                        actual[0] = String.valueOf(pane.getMessage());
                        window.dispose();
                        ((Timer) event.getSource()).stop();
                    }
                }
            }
        });
        closer.start();
        try { start().doClick(0); } finally { closer.stop(); }
        if (actual[0] == null || !actual[0].contains(expected)) throw new AssertionError("Missing selection warning");
        if (!form.isDisplayable()) throw new AssertionError("Blocked exam still navigated away");
        for (Window window : Window.getWindows())
            if (window instanceof LamBaiThi && window.isShowing()) throw new AssertionError("Blocked exam opened");
    }

    interface Check { void run() throws Exception; }
    static void edt(Check check) throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            try { check.run(); } catch (Exception e) { throw new RuntimeException(e); }
        });
    }

    public static void main(String[] args) {
        int status = 0;
        try {
            student = new sessionSinhVien();
            student.setMaSV("001"); student.setMaLop("TH04");
            student.setHo("Test"); student.setTen("Selection");
            openChooser();
            edt(() -> {
                fixtures();
                select("AVCB", true);
                select("MMTCB", false);
                checkStart("MMTCB", "Mạng máy tính", 2, "B", 25, 40);
            });
            System.out.println("PASS: keyboard selection replaces the mouse selection and carries MAMH");

            openChooser();
            edt(() -> {
                for (int row = 0; row < table().getModel().getRowCount(); row++) {
                    Object subject = table().getModel().getValueAt(row, 0);
                    if (!(subject instanceof MonHoc) || ((MonHoc) subject).getMaMH().isBlank())
                        throw new AssertionError("Loaded SQL rows must carry MAMH while displaying TENMH");
                }
                fixtures();
                table().getRowSorter().setSortKeys(java.util.List.of(new RowSorter.SortKey(0, SortOrder.DESCENDING)));
                table().moveColumn(0, 5);
                select("AVCB", false);
                checkStart("AVCB", "Anh văn", 1, "A", 20, 30);
            });
            System.out.println("PASS: SQL retains MAMH; sorting and moved columns preserve exam details");

            openChooser();
            edt(() -> {
                fixtures();
                checkBlocked("Vui lòng chọn ca thi");
                select("CSDL", false);
                checkBlocked("Ca thi không hợp lệ");
                select("AVCB", true);
                form.loadCaThiTable();
                if (table().getSelectedRow() != -1 || field("cathi") != null || start().isEnabled())
                    throw new AssertionError("Reload must clear stale selection and block Start while loading");
            });
            long deadline = System.nanoTime() + 15_000_000_000L;
            boolean[] ready = {false};
            while (!ready[0] && System.nanoTime() < deadline) {
                edt(() -> ready[0] = start().isEnabled());
                Thread.sleep(40);
            }
            if (!ready[0]) throw new AssertionError("Start was not re-enabled after reload");
            edt(() -> {
                fixtures();
                select("MMTCB", false);
                checkStart("MMTCB", "Mạng máy tính", 2, "B", 25, 40);
            });
            System.out.println("PASS: no selection and future exams blocked; reload clears the old exam");
        } catch (Throwable e) { e.printStackTrace(); status = 1; }
        finally {
            try { SwingUtilities.invokeAndWait(() -> { for (Window window : Window.getWindows()) window.dispose(); }); }
            catch (Exception e) { status = 1; }
        }
        System.exit(status);
    }
}
