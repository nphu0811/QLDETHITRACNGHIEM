import java.awt.Component;
import java.awt.Container;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.lang.reflect.Field;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import javax.swing.*;
import javax.swing.event.TableModelListener;
import session.MonHoc;
import session.sessionSinhVien;
import thi.ChonCaThi;

/** Integration check using local SQL and sql/test-data/seed-ca-thi-20261009.sql. */
public class VerifyCaThiFilters {
    static ChonCaThi form;

    static Object field(String name) throws Exception {
        Field field = ChonCaThi.class.getDeclaredField(name);
        field.setAccessible(true);
        return field.get(form);
    }

    static JTable table() throws Exception { return (JTable) field("tblCaThi"); }
    static JComboBox<?> subjects() throws Exception { return (JComboBox<?>) field("cboMonHoc"); }
    static JComboBox<?> attempts() throws Exception { return (JComboBox<?>) field("cboLanThi"); }
    static JFormattedTextField date() throws Exception { return (JFormattedTextField) field("txtNgayThi"); }

    static void typeDateDigits(String digits) throws Exception {
        date().selectAll();
        date().replaceSelection("");
        date().setCaretPosition(0);
        for (char digit : digits.toCharArray()) date().replaceSelection(String.valueOf(digit));
    }

    static void pressDateEnter() throws Exception {
        Object key = date().getInputMap().get(KeyStroke.getKeyStroke("ENTER"));
        javax.swing.Action action = date().getActionMap().get(key);
        action.actionPerformed(new ActionEvent(date(), ActionEvent.ACTION_PERFORMED, String.valueOf(key)));
    }

    static void selectSubject(String code) throws Exception {
        if (code == null) { subjects().setSelectedIndex(-1); return; }
        for (int i = 0; i < subjects().getItemCount(); i++) {
            if (((MonHoc) subjects().getItemAt(i)).getMaMH().equals(code)) {
                subjects().setSelectedIndex(i);
                return;
            }
        }
        throw new AssertionError("Missing subject: " + code);
    }

    interface Action { void run() throws Exception; }

    static void checkRows(String name, int expected, Action action) throws Exception {
        CountDownLatch changed = new CountDownLatch(1);
        TableModelListener listener = event -> changed.countDown();
        SwingUtilities.invokeAndWait(() -> {
            try { table().getModel().addTableModelListener(listener); action.run(); }
            catch (Exception e) { throw new RuntimeException(e); }
        });
        boolean completed = changed.await(12, TimeUnit.SECONDS);
        SwingUtilities.invokeAndWait(() -> {
            try {
                table().getModel().removeTableModelListener(listener);
                int actual = table().getRowCount();
                if (!completed || actual != expected)
                    throw new AssertionError(name + ": expected " + expected + " rows, got " + actual);
            } catch (Exception e) { throw new RuntimeException(e); }
        });
        System.out.println("PASS: " + name + " -> " + expected + " rows");
    }

    static JOptionPane findMessage(Component component) {
        if (component instanceof JOptionPane) return (JOptionPane) component;
        if (component instanceof Container) {
            for (Component child : ((Container) component).getComponents()) {
                JOptionPane message = findMessage(child);
                if (message != null) return message;
            }
        }
        return null;
    }

    static void checkInvalidDate(String input, String expectedMessage) throws Exception {
        checkInvalidDate(input, expectedMessage, () -> date().setText(input));
    }

    static void checkInvalidDate(String input, String expectedMessage, Action fillInput) throws Exception {
        CountDownLatch finished = new CountDownLatch(1);
        String[] message = {null};
        int[] previousRows = {0};
        SwingUtilities.invokeAndWait(() -> {
            try {
                previousRows[0] = table().getRowCount();
                long deadline = System.nanoTime() + TimeUnit.SECONDS.toNanos(3);
                Timer timer = new Timer(40, event -> {
                    for (Window window : Window.getWindows()) {
                        if (window instanceof JDialog && window.getOwner() == form && window.isShowing()) {
                            JOptionPane pane = findMessage(window);
                            if (pane == null) continue;
                            message[0] = String.valueOf(pane.getMessage());
                            ((Timer) event.getSource()).stop();
                            window.dispose();
                            finished.countDown();
                            return;
                        }
                    }
                    if (System.nanoTime() >= deadline) {
                        ((Timer) event.getSource()).stop();
                        finished.countDown();
                    }
                });
                timer.start();
                fillInput.run();
                pressDateEnter();
            } catch (Exception e) { throw new RuntimeException(e); }
        });
        if (!finished.await(4, TimeUnit.SECONDS) || message[0] == null
                || !message[0].contains(expectedMessage)) {
            throw new AssertionError("Invalid date " + input + " must show '"
                    + expectedMessage + "', got: " + message[0]);
        }
        SwingUtilities.invokeAndWait(() -> {
            try {
                if (table().getRowCount() != previousRows[0])
                    throw new AssertionError("Invalid date must not replace the current exam list");
            } catch (Exception e) { throw new RuntimeException(e); }
        });
        System.out.println("PASS: rejects " + input + " and preserves the current exam list");
    }

    public static void main(String[] args) {
        int status = 0;
        try {
            sessionSinhVien sv = new sessionSinhVien();
            sv.setMaSV("001"); sv.setHo("Test"); sv.setTen("Filters");
            sv.setMaLop("TH04"); sv.setTenLop("TIN HOC 2004");
            SwingUtilities.invokeAndWait(() -> form = new ChonCaThi(sv));
            long deadline = System.nanoTime() + TimeUnit.SECONDS.toNanos(5);
            boolean[] ready = {false};
            while (!ready[0] && System.nanoTime() < deadline) {
                SwingUtilities.invokeAndWait(() -> {
                    try { ready[0] = subjects().isEnabled() && table().getRowCount() == 4; }
                    catch (Exception e) { throw new RuntimeException(e); }
                });
                Thread.sleep(40);
            }
            if (!ready[0]) throw new AssertionError("Opening with blank filters must show 4 seeded exams");
            System.out.println("PASS: initial blank filters -> 4 rows");

            SwingUtilities.invokeAndWait(() -> {
                try {
                    if (!date().getText().equals("__/__/____"))
                        throw new AssertionError("Empty date must display __/__/____, got: " + date().getText());
                    date().setCaretPosition(0);
                    date().replaceSelection("a");
                    if (!date().getText().equals("__/__/____"))
                        throw new AssertionError("Date mask must reject letters");
                } catch (Exception e) { throw new RuntimeException(e); }
            });
            System.out.println("PASS: date placeholder and numeric-only editing");
            checkRows("typing eight digits inserts separators and filters on Enter", 2, () -> {
                typeDateDigits("09102026");
                if (!date().getText().equals("09/10/2026"))
                    throw new AssertionError("Eight typed digits must become 09/10/2026");
                pressDateEnter();
            });
            checkRows("clearing the masked date reloads all exams on Enter", 4, () -> {
                typeDateDigits(""); pressDateEnter();
                if (!date().getText().equals("__/__/____"))
                    throw new AssertionError("Clearing must restore the date placeholder");
            });

            checkRows("subject only", 2, () -> selectSubject("AVCB"));
            checkRows("date only", 2, () -> {
                selectSubject(null); date().setText("09/10/2026"); date().postActionEvent();
            });
            checkRows("attempt only", 2, () -> {
                date().setText(""); attempts().setSelectedItem("2");
            });
            checkRows("three filters changed rapidly", 1, () -> {
                selectSubject("MMTCB"); date().setText("11/10/2026"); date().postActionEvent();
            });
            SwingUtilities.invokeAndWait(() -> {
                try {
                    if (!table().getValueAt(0, 1).equals("11/10/2026")
                            || !table().getValueAt(0, 2).toString().equals("2"))
                        throw new AssertionError("A previous filter result overwrote the latest result");
                } catch (Exception e) { throw new RuntimeException(e); }
            });
            checkRows("clear all filters", 4, () -> {
                date().setText(""); selectSubject(null); attempts().setSelectedIndex(-1);
                date().postActionEvent();
            });
            checkRows("date without matches clears old rows", 0, () -> {
                date().setText("12/10/2026"); date().postActionEvent();
            });
            checkRows("date focus loss reloads", 2, () -> {
                date().setText("09/10/2026");
                FocusEvent event = new FocusEvent(date(), FocusEvent.FOCUS_LOST, false);
                for (var listener : date().getFocusListeners()) listener.focusLost(event);
            });

            checkInvalidDate("01/01/0000", "Năm");
            checkInvalidDate("09/00/2026", "Tháng");
            checkInvalidDate("09/13/2026", "Tháng");
            checkInvalidDate("00/10/2026", "Tháng");
            checkInvalidDate("32/10/2026", "Tháng");
            checkInvalidDate("31/04/2026", "30");
            checkInvalidDate("30/02/2024", "29");
            checkInvalidDate("29/02/2026", "28");
            checkInvalidDate("29/02/1900", "28");
            checkInvalidDate("29/02/2100", "28");
            checkInvalidDate("09/10/____", "dd/MM/yyyy", () -> typeDateDigits("0910"));
            checkRows("valid leap day", 0, () -> {
                date().setText("29/02/2024"); date().postActionEvent();
            });
            checkRows("clear date after leap day", 4, () -> {
                date().setText(""); date().postActionEvent();
            });
            checkRows("century divisible by 400 is a leap year", 0, () -> {
                date().setText("29/02/2000"); date().postActionEvent();
            });
            checkRows("clear date after century leap day", 4, () -> {
                date().setText(""); date().postActionEvent();
            });
            checkRows("valid 31-day month", 0, () -> {
                date().setText("31/10/2026"); date().postActionEvent();
            });
            checkRows("clear date after validation", 4, () -> {
                date().setText(""); date().postActionEvent();
            });

        } catch (Throwable e) { e.printStackTrace(); status = 1; }
        finally {
            try { SwingUtilities.invokeAndWait(() -> { for (Window window : Window.getWindows()) window.dispose(); }); }
            catch (Exception e) { status = 1; }
        }
        System.exit(status);
    }
}
