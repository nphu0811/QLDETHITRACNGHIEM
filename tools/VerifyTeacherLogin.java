import dangNhapdangKy.DangNhap;
import dangNhapdangKy.DangNhapSV;
import java.awt.Window;
import java.lang.reflect.*;
import java.sql.*;
import java.util.Properties;
import java.util.concurrent.*;
import java.util.logging.Logger;
import javax.swing.*;

/** Isolated fake JDBC test: no database credentials or live database are used. */
public class VerifyTeacherLogin {
    static final CountDownLatch entered = new CountDownLatch(1);
    static final CountDownLatch release = new CountDownLatch(1);
    static volatile int closed;
    static volatile int queryTimeout;
    static String mode;
    static Object field(Object instance, String name) throws Exception {
        Field f = instance.getClass().getDeclaredField(name);
        f.setAccessible(true);
        return f.get(instance);
    }
    static void pause() throws SQLException {
        entered.countDown();
        try { release.await(); } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); throw new SQLException(e);
        }
    }
    static Object proxy(Class<?> type) {
        return Proxy.newProxyInstance(type.getClassLoader(), new Class<?>[]{type}, (p, method, args) -> {
            switch (method.getName()) {
                case "prepareCall": return proxy(CallableStatement.class);
                case "setQueryTimeout": queryTimeout = (Integer) args[0]; return null;
                case "executeQuery": pause(); return proxy(ResultSet.class);
                case "next": return mode.equals("success");
                case "getString":
                    return switch ((String) args[0]) {
                        case "MAGV" -> "GV001";
                        case "HO" -> "Nguyen";
                        case "TEN" -> "An";
                        case "MALOP" -> "D21";
                        default -> null;
                    };
                case "close": closed++; return null;
                case "isClosed": return false;
                default: return null;
            }
        });
    }
    public static void main(String[] args) throws Exception {
        mode = args.length == 0 ? "connect" : args[0];
        DriverManager.registerDriver(new Driver() {
            public Connection connect(String url, Properties props) throws SQLException {
                if (!acceptsURL(url)) return null;
                if (mode.equals("connect")) {
                    pause(); throw new SQLException("Simulated connection failure");
                }
                return (Connection) proxy(Connection.class);
            }
            public boolean acceptsURL(String url) { return url.startsWith("jdbc:sqlserver:"); }
            public DriverPropertyInfo[] getPropertyInfo(String u, Properties p) { return new DriverPropertyInfo[0]; }
            public int getMajorVersion() { return 1; }
            public int getMinorVersion() { return 0; }
            public boolean jdbcCompliant() { return false; }
            public Logger getParentLogger() { return Logger.getGlobal(); }
        });
        boolean student = args.length > 1 && args[1].equals("student");
        JFrame[] frame = new JFrame[1];
        JButton[] button = new JButton[1];
        Timer[] closer = new Timer[1];
        SwingUtilities.invokeAndWait(() -> {
            try {
                frame[0] = student ? new DangNhapSV() : new DangNhap();
                ((JTextField) field(frame[0], student ? "txtMaSV" : "gvLogin")).setText("fake-user");
                if (!student) {
                    ((JPasswordField) field(frame[0], "gvPassword")).setText("fake-password");
                }
                button[0] = (JButton) field(frame[0], "btnLogin");
                closer[0] = new Timer(50, e -> {
                    for (Window w : Window.getWindows()) if (w instanceof JDialog) w.dispose();
                });
                closer[0].start();
            } catch (Exception e) { throw new RuntimeException(e); }
        });
        SwingUtilities.invokeLater(() -> button[0].doClick());
        boolean started = entered.await(3, TimeUnit.SECONDS);
        CountDownLatch responsive = new CountDownLatch(1);
        boolean[] disabled = new boolean[1];
        SwingUtilities.invokeLater(() -> { disabled[0] = !button[0].isEnabled(); responsive.countDown(); });
        boolean responds = responsive.await(500, TimeUnit.MILLISECONDS);
        release.countDown();
        Thread.sleep(800);
        boolean[] restored = new boolean[1];
        boolean[] homeCorrect = new boolean[]{!mode.equals("success")};
        SwingUtilities.invokeAndWait(() -> {
            restored[0] = button[0].isEnabled();
            try {
                for (Window w : Window.getWindows()) {
                    if (mode.equals("success") && w instanceof trangchu.TrangChu) {
                        String ma = ((JTextField) field(w, "txtMaNguoiDung")).getText();
                        String ten = ((JTextField) field(w, "txtHoTen")).getText();
                        Object session = field(w, "nguoiDung");
                        homeCorrect[0] = ma.equals(student ? "fake-user" : "GV001")
                                && ten.equals("Nguyen An") && !frame[0].isDisplayable()
                                && (student ? session instanceof session.sessionSinhVien
                                            : session instanceof session.sessionGiaoVien);
                    }
                    w.dispose();
                }
            } catch (Exception e) { throw new RuntimeException(e); }
            closer[0].stop();
        });
        boolean passed = started && responds && disabled[0] && restored[0]
                && homeCorrect[0] && (mode.equals("connect") || (closed == 3 && queryTimeout == 15));
        System.out.println("mode=" + mode + ", EDT responsive=" + responds
                + ", duplicate login prevented=" + disabled[0] + ", button restored=" + restored[0]
                + ", resources closed=" + closed + ", query timeout=" + queryTimeout
                + ", home/session correct=" + homeCorrect[0]);
        System.exit(passed ? 0 : 1);
    }
}
