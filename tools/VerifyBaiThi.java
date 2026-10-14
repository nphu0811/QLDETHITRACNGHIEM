import java.awt.Window;
import java.lang.reflect.Field;
import java.util.concurrent.Callable;
import javax.swing.*;
import session.CaThi;
import session.sessionSinhVien;
import thi.LamBaiThi;

/** Real Swing/JDBC test. Run with -Dthitracnghiem.database=THITRACNGHIEM_VERIFY. */
public class VerifyBaiThi {
    static LamBaiThi form;
    static sessionSinhVien student;

    static void sql(String query) throws Exception {
        Process p = new ProcessBuilder("sqlcmd", "-S", "localhost\\SQLEXPRESS", "-d",
            "THITRACNGHIEM_VERIFY", "-E", "-C", "-b", "-Q", query).redirectErrorStream(true).start();
        String output = new String(p.getInputStream().readAllBytes());
        if (p.waitFor() != 0) throw new AssertionError(output);
    }

    @SuppressWarnings("unchecked")
    static <T> T edt(Callable<T> work) throws Exception {
        Object[] value = new Object[1];
        Throwable[] error = new Throwable[1];
        SwingUtilities.invokeAndWait(() -> {
            try { value[0] = work.call(); } catch (Throwable e) { error[0] = e; }
        });
        if (error[0] != null) throw new RuntimeException(error[0]);
        return (T) value[0];
    }

    static Object field(Object target, String name) throws Exception {
        Field f = target.getClass().getDeclaredField(name);
        f.setAccessible(true);
        return f.get(target);
    }

    static void await(Callable<Boolean> condition, String failure) throws Exception {
        long end = System.nanoTime() + 15_000_000_000L;
        while (System.nanoTime() < end) {
            if (edt(condition)) return;
            Thread.sleep(40);
        }
        throw new AssertionError(failure);
    }

    static void open() throws Exception {
        CaThi exam = new CaThi();
        exam.setMaMH("VFY01"); exam.setLan(2); exam.setThoiGianThi(30);
        edt(() -> {
            form = new LamBaiThi(student, exam); form.setVisible(true);
            // SQL đã trả dữ liệu nhưng EDT đang bận: không được cộng thời gian chờ vào đồng hồ.
            ((java.util.concurrent.ExecutorService) field(form, "hangDoiSQL"))
                .submit(() -> {}).get(12, java.util.concurrent.TimeUnit.SECONDS);
            Thread.sleep(1200);
            return null;
        });
        await(() -> ((JTable) field(form, "tblCauThi")).getRowCount() == 10,
                "MISSING: form phải tải đủ 10 câu từ SQL");
        boolean deadlinePreserved = edt(() -> {
            session.BaiThi loaded = (session.BaiThi) field(form, "baiThi");
            long remaining = ((Long) field(form, "ketThucBaiThi") - System.nanoTime()) / 1_000_000L;
            return remaining <= loaded.conLaiMillis - 1000;
        });
        if (!deadlinePreserved) throw new AssertionError("EDT chậm không được cộng dư thời gian thi");
    }

    static void clickWithoutFlashing(String button, String answer) throws Exception {
        int[] questionChanges = {0};
        boolean[] disabled = {false};
        javax.swing.event.DocumentListener textListener = new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) { questionChanges[0]++; }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { questionChanges[0]++; }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { questionChanges[0]++; }
        };
        java.beans.PropertyChangeListener enabledListener = e -> {
            if (Boolean.FALSE.equals(e.getNewValue())) disabled[0] = true;
        };
        edt(() -> {
            ((JTextArea) field(form, "txtNoiDungCauHoi")).getDocument().addDocumentListener(textListener);
            ((JRadioButton) field(form, "rdoA")).addPropertyChangeListener("enabled", enabledListener);
            ((AbstractButton) field(form, button)).doClick();
            if (!((JRadioButton) field(form, "rdoA")).isEnabled())
                throw new AssertionError("Lưu tiến độ không được làm các đáp án chuyển màu xám");
            if (button.equals("rdoB")) {
                // Các control giữ màu bình thường nhưng chưa nhận thêm thao tác khi đang lưu.
                ((JRadioButton) field(form, "rdoC")).doClick();
                if (!((JRadioButton) field(form, "rdoB")).isSelected())
                    throw new AssertionError("Bấm thêm lúc đang lưu không được hiện đáp án chưa ghi nhận");
                JTable table = (JTable) field(form, "tblCauThi");
                table.setRowSelectionInterval(1, 1);
                if (table.getSelectedRow() != 0)
                    throw new AssertionError("Bấm thêm lúc đang lưu không được chọn nhầm dòng câu hỏi");
                ((JButton) field(form, "btnCauSau")).doClick();
                if (!((JLabel) field(form, "lblSoCau")).getText().contains("1 / 10"))
                    throw new AssertionError("Đang lưu chưa nhận thao tác chuyển câu tiếp theo");
            }
            return null;
        });
        await(() -> !((Boolean) field(form, "dangLuu")) && answer.equals(
            ((JTable) field(form, "tblCauThi")).getModel().getValueAt(0, 1)), "Lựa chọn phải được lưu SQL");
        edt(() -> {
            ((JTextArea) field(form, "txtNoiDungCauHoi")).getDocument().removeDocumentListener(textListener);
            ((JRadioButton) field(form, "rdoA")).removePropertyChangeListener("enabled", enabledListener);
            return null;
        });
        if (disabled[0] || questionChanges[0] != 0)
            throw new AssertionError("Lưu thành công không được khóa màu nút hoặc hiển thị lại nội dung câu");
    }

    static void moveWithoutFlashing(String button, int question) throws Exception {
        int[] textChanges = {0};
        javax.swing.event.DocumentListener listener = new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) { textChanges[0]++; }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { textChanges[0]++; }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { textChanges[0]++; }
        };
        edt(() -> {
            ((JTextArea) field(form, "txtNoiDungCauHoi")).getDocument().addDocumentListener(listener);
            ((JButton) field(form, button)).doClick();
            if (!((JRadioButton) field(form, "rdoA")).isEnabled())
                throw new AssertionError("Chuyển câu không được khóa màu đáp án khi lưu vị trí");
            return null;
        });
        await(() -> !((Boolean) field(form, "dangLuu")) && ((JLabel) field(form, "lblSoCau"))
            .getText().contains(question + " / 10"), "Vị trí câu phải lưu xong");
        edt(() -> {
            ((JTextArea) field(form, "txtNoiDungCauHoi")).getDocument().removeDocumentListener(listener);
            return null;
        });
        if (textChanges[0] != 2) throw new AssertionError("Chuyển câu chỉ hiển thị nội dung một lần");
    }

    public static void main(String[] args) throws Exception {
        if (!"THITRACNGHIEM_VERIFY".equals(System.getProperty("thitracnghiem.database")))
            throw new AssertionError("Test requires isolated database");
        try {
            sql("DELETE CHITIETBAITHI WHERE MASV IN ('VFY00001','VFY00002') AND LAN=2; DELETE BANGDIEM WHERE MASV IN ('VFY00001','VFY00002') AND LAN=2; DELETE TRANGTHAITHI WHERE MASV IN ('VFY00001','VFY00002') AND LAN=2; UPDATE GIAOVIEN_DANGKY SET NGAYTHI=CONVERT(date,DATEADD(hour,7,SYSUTCDATETIME())) WHERE MAMH='VFY01' AND LAN=2;");
            student = new sessionSinhVien(); student.setMaSV("VFY00002");
            student.setHo("Kiểm"); student.setTen("Thử"); student.setTenLop("Lớp thử");
            open();
            String first = edt(() -> ((JTextArea) field(form, "txtNoiDungCauHoi")).getText());
            sql("ALTER TABLE TRANGTHAITHI WITH NOCHECK ADD CONSTRAINT VFY_LuuLoi CHECK(CAUTRALOI<>N'B');");
            boolean[] showedSaveError = {false};
            edt(() -> {
                Timer closeError = new Timer(40, e -> {
                    for (Window w : Window.getWindows()) if (w instanceof JDialog && w.isShowing()
                            && w.getOwner() == form) {
                        showedSaveError[0] = true; w.dispose(); ((Timer) e.getSource()).stop();
                    }
                });
                closeError.start();
                ((JRadioButton) field(form, "rdoB")).doClick();
                return null;
            });
            await(() -> showedSaveError[0] && !((JRadioButton) field(form, "rdoB")).isSelected()
                && "".equals(((JTable) field(form, "tblCauThi")).getModel().getValueAt(0, 1))
                && ((JButton) field(form, "btnCauSau")).isEnabled(),
                "Lưu thất bại phải báo và khôi phục đáp án được xác nhận");
            sql("ALTER TABLE TRANGTHAITHI DROP CONSTRAINT VFY_LuuLoi;");
            clickWithoutFlashing("rdoB", "B");
            await(() -> "B".equals(((JTable) field(form, "tblCauThi")).getModel().getValueAt(0, 1)),
                "Chọn đáp án phải lưu và cập nhật bảng");
            moveWithoutFlashing("btnCauSau", 2);
            await(() -> ((JLabel) field(form, "lblSoCau")).getText().contains("2 / 10")
                && ((JButton) field(form, "btnCauSau")).isEnabled(), "Chuyển câu phải lưu vị trí");
            edt(() -> { form.dispose(); return null; });
            Thread.sleep(1200);
            open();
            if (!edt(() -> ((JLabel) field(form, "lblSoCau")).getText().contains("2 / 10")))
                throw new AssertionError("Resume phải giữ câu đang xem");
            moveWithoutFlashing("btnCauTruoc", 1);
            await(() -> ((JRadioButton) field(form, "rdoB")).isSelected()
                && ((JButton) field(form, "btnCauSau")).isEnabled(), "Resume phải giữ lựa chọn");
            if (!first.equals(edt(() -> ((JTextArea) field(form, "txtNoiDungCauHoi")).getText())))
                throw new AssertionError("Resume không được đổi câu hỏi");
            if (!edt(() -> ((javax.swing.Timer) field(form, "timer")).isRunning()))
                throw new AssertionError("Đồng hồ phải chạy");
            clickWithoutFlashing("btnXoaLuaChon", "");
            await(() -> "".equals(((JTable) field(form, "tblCauThi")).getModel().getValueAt(0, 1))
                && ((JButton) field(form, "btnCauSau")).isEnabled(), "Xóa lựa chọn phải lưu SQL");
            edt(() -> { ((JRadioButton) field(form, "rdoD")).doClick(); return null; });
            await(() -> "D".equals(((JTable) field(form, "tblCauThi")).getModel().getValueAt(0, 1)), "Lưu trước nộp");
            edt(() -> { ((JButton) field(form, "btnNopBai")).doClick(); return null; });
            await(() -> {
                for (Window w : Window.getWindows()) if (w instanceof thi.KetQuaThi && w.isShowing())
                    return ((JLabel) field(w, "lblDiem")).getText().contains("1.00");
                return false;
            }, "Nộp phải mở kết quả đúng điểm 1/10");
            edt(() -> {
                for (Window w : Window.getWindows()) if (w instanceof thi.KetQuaThi && w.isShowing())
                    ((JButton) field(w, "btnXemChiTiet")).doClick();
                return null;
            });
            await(() -> {
                for (Window w : Window.getWindows()) if (w instanceof baocao.XemKetQua && w.isShowing()) {
                    if (((JTable) field(w, "tblChiTietBaiThi")).getRowCount() != 10) return false;
                    for (String name : new String[]{"txtLogin", "txtHoTen", "txtLop", "txtDiem"})
                        if (((JTextField) field(w, name)).isEditable()) throw new AssertionError("Readonly " + name);
                    return "VFY00002".equals(((JTextField) field(w, "txtLogin")).getText());
                }
                return false;
            }, "Chi tiết phải đủ 10 câu, đúng session, readonly");

            // Không có bài dở: giữ nút Chọn ca thi và ở lại trang chủ.
            edt(() -> { for (Window w : Window.getWindows()) w.dispose(); return null; });
            trangchu.TrangChu homeWithoutExam = edt(() -> {
                trangchu.TrangChu home = new trangchu.TrangChu(student);
                home.setVisible(true);
                return home;
            });
            await(() -> ((JButton) field(homeWithoutExam, "btnChonCaThi")).isEnabled(),
                "Trang chủ phải kiểm tra xong bài dở");
            if (!edt(() -> homeWithoutExam.isShowing()
                    && "Chọn ca thi".equals(((JButton) field(homeWithoutExam, "btnChonCaThi")).getText())))
                throw new AssertionError("Không có bài dở phải giữ nút Chọn ca thi");
            edt(() -> { homeWithoutExam.dispose(); return null; });

            // Có bài dở: đổi nút, chỉ mở bài khi sinh viên bấm Làm bài tiếp.
            sql("SET NOCOUNT ON; UPDATE GIAOVIEN_DANGKY SET NGAYTHI=CONVERT(date,DATEADD(hour,7,SYSUTCDATETIME())) WHERE MAMH='VFY01' AND LAN=2; EXEC SP_MoBaiThi 'VFY00001','VFY01',2; EXEC SP_LuuTienDoThi 'VFY00001','VFY01',2,3,N'B',3;");
            student.setMaSV("VFY00001");
            trangchu.TrangChu homeWithExam = edt(() -> {
                trangchu.TrangChu home = new trangchu.TrangChu(student);
                home.setVisible(true);
                return home;
            });
            await(() -> {
                if (!homeWithExam.isShowing())
                    throw new AssertionError("Có bài dở vẫn phải ở trang chủ, chưa bấm thì không được mở bài");
                JButton button = (JButton) field(homeWithExam, "btnChonCaThi");
                return button.isEnabled() && "Làm bài tiếp".equals(button.getText());
            }, "Có bài dở phải đổi nút thành Làm bài tiếp");
            edt(() -> {
                for (Window w : Window.getWindows()) if (w instanceof LamBaiThi && w.isShowing())
                    throw new AssertionError("Chưa bấm Làm bài tiếp thì không được mở bài");
                ((JButton) field(homeWithExam, "btnChonCaThi")).doClick();
                return null;
            });
            await(() -> {
                for (Window w : Window.getWindows()) if (w instanceof LamBaiThi && w.isShowing()) {
                    form = (LamBaiThi) w;
                    return ((JTable) field(w, "tblCauThi")).getRowCount() == 10
                        && ((JLabel) field(w, "lblSoCau")).getText().contains("3 / 10")
                        && ((JRadioButton) field(w, "rdoB")).isSelected();
                }
                return false;
            }, "Bấm Làm bài tiếp phải mở đúng bài, vị trí và đáp án đang lưu");
            edt(() -> { ((JButton) field(form, "btnTroVe")).doClick(); return null; });
            await(() -> {
                for (Window w : Window.getWindows()) if (w instanceof trangchu.TrangChu && w.isShowing()) {
                    JButton button = (JButton) field(w, "btnChonCaThi");
                    return button.isEnabled() && "Làm bài tiếp".equals(button.getText());
                }
                return false;
            }, "Trở về từ bài đang làm vẫn phải có nút Làm bài tiếp");
            edt(() -> { for (Window w : Window.getWindows()) w.dispose(); return null; });
            sql("UPDATE TRANGTHAITHI SET HANKETTHUC=DATEADD(second,5,SYSUTCDATETIME()) WHERE MASV='VFY00001';");
            open();
            await(() -> {
                for (Window w : Window.getWindows()) if (w instanceof thi.KetQuaThi && w.isShowing())
                    return ((JLabel) field(w, "lblDiem")).getText().contains("0.00");
                return false;
            }, "Hết giờ phải tự nộp trắng với điểm 0");
            System.out.println("PASS Swing: TrangChu đổi nút theo bài dở, bấm mới tiếp tục, tự nộp khi hết giờ.");
            System.out.println("PASS Swing: tải đề, lưu/xóa đáp án, đổi câu, đóng/mở, đồng hồ, điểm, chi tiết.");
        } finally {
            edt(() -> { for (Window w : Window.getWindows()) w.dispose(); return null; });
            sql("IF OBJECT_ID('VFY_LuuLoi','C') IS NOT NULL ALTER TABLE TRANGTHAITHI DROP CONSTRAINT VFY_LuuLoi;");
        }
    }
}
