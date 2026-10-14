import java.awt.Window;
import java.io.File;
import java.util.concurrent.Callable;
import javax.swing.*;
import javax.swing.table.TableRowSorter;
import session.sessionSinhVien;
import trangchu.TrangChu;

/** Kiểm tra bảng điểm bằng Swing và SQL thật trong database kiểm thử riêng. */
public class VerifyBangDiemSinhVien {
    static <T> T edt(Callable<T> work) throws Exception { return VerifyBaiThi.edt(work); }
    static Object field(Object target, String name) throws Exception { return VerifyBaiThi.field(target, name); }
    static void await(Callable<Boolean> condition, String message) throws Exception { VerifyBaiThi.await(condition, message); }
    static void sql(String query) throws Exception { VerifyBaiThi.sql(query); }

    static JFrame showing(String className) {
        for (Window window : Window.getWindows())
            if (window instanceof JFrame && window.isShowing()
                    && window.getClass().getName().equals(className)) return (JFrame) window;
        return null;
    }

    static sessionSinhVien student(String maSV) {
        sessionSinhVien sv = new sessionSinhVien();
        sv.setMaSV(maSV); sv.setHo("Kiểm"); sv.setTen("Thử");
        sv.setMaLop("VFY_THI"); sv.setTenLop("Lớp kiểm thử");
        return sv;
    }

    static JFrame openBoard(sessionSinhVien sv) throws Exception {
        return edt(() -> {
            TrangChu home = new TrangChu(sv);
            home.setVisible(true);
            ((JButton) field(home, "btnBangDiem")).doClick();
            JFrame board = showing("baocao.BangDiemSinhVien");
            if (board == null) throw new AssertionError("Nút Bảng điểm của sinh viên phải mở form BangDiemSinhVien riêng");
            if (home.isDisplayable()) throw new AssertionError("Chuyển trang phải đóng TrangChu cũ");
            if (field(board, "nguoiDung") != sv) throw new AssertionError("Bảng điểm phải giữ session đăng nhập");
            return board;
        });
    }

    static void prepare() throws Exception {
        sql("""
            SET NOCOUNT ON;
            DELETE dbo.CHITIETBAITHI;
            DELETE dbo.BANGDIEM;
            DELETE dbo.TRANGTHAITHI;
            DELETE dbo.BODE WHERE MAMH='VFY02';
            DELETE dbo.GIAOVIEN_DANGKY WHERE MAMH='VFY02' AND LAN=2;
            DELETE dbo.SINHVIEN WHERE MASV='VFY00003';
            UPDATE dbo.GIAOVIEN_DANGKY SET NGAYTHI=CONVERT(date,DATEADD(hour,7,SYSUTCDATETIME()));
            INSERT dbo.BODE (CAUHOI,MAMH,TRINHDO,NOIDUNG,A,B,C,D,DAP_AN)
                SELECT CAUHOI+30,'VFY02',TRINHDO,NOIDUNG,A,B,C,D,DAP_AN
                FROM dbo.BODE WHERE MAMH='VFY01';
            INSERT dbo.GIAOVIEN_DANGKY (MAMH,MALOP,LAN,NGAYTHI,TRINHDO,SOCAUTHI,THOIGIAN)
                VALUES ('VFY02','VFY_THI',2,CONVERT(date,DATEADD(hour,7,SYSUTCDATETIME())),'C',10,30);
            EXEC dbo.SP_MoBaiThi 'VFY00001','VFY01',1;
            UPDATE dbo.TRANGTHAITHI SET CAUTRALOI=DAP_AN WHERE MASV='VFY00001' AND THUTU<=3;
            EXEC dbo.SP_NopBaiThi 'VFY00001','VFY01',1;
            EXEC dbo.SP_MoBaiThi 'VFY00001','VFY01',2;
            UPDATE dbo.TRANGTHAITHI SET CAUTRALOI=DAP_AN WHERE MASV='VFY00001' AND THUTU<=8;
            EXEC dbo.SP_NopBaiThi 'VFY00001','VFY01',2;
            EXEC dbo.SP_MoBaiThi 'VFY00001','VFY02',1;
            UPDATE dbo.TRANGTHAITHI SET CAUTRALOI=DAP_AN WHERE MASV='VFY00001' AND THUTU=1;
            EXEC dbo.SP_NopBaiThi 'VFY00001','VFY02',1;
            EXEC dbo.SP_MoBaiThi 'VFY00001','VFY02',2;
            EXEC dbo.SP_MoBaiThi 'VFY00002','VFY01',1;
            UPDATE dbo.TRANGTHAITHI SET CAUTRALOI=DAP_AN WHERE MASV='VFY00002';
            EXEC dbo.SP_NopBaiThi 'VFY00002','VFY01',1;
            INSERT dbo.SINHVIEN (MASV,HO,TEN,MALOP) VALUES ('VFY00003',N'Chưa',N'Thi','VFY_THI');
            """);
    }

    static void selectAndReview(JFrame board, sessionSinhVien sv, String maMH, int lan, String diem) throws Exception {
        edt(() -> {
            JTable table = (JTable) field(board, "tblBangDiem");
            TableRowSorter<?> sorter = (TableRowSorter<?>) table.getRowSorter();
            sorter.setSortKeys(java.util.List.of(new RowSorter.SortKey(4, SortOrder.ASCENDING)));
            int chosen = -1;
            for (int view = 0; view < table.getRowCount(); view++) {
                if (maMH.equals(table.getValueAt(view, 0))
                        && Integer.parseInt(table.getValueAt(view, 3).toString()) == lan) chosen = view;
            }
            if (chosen < 0) throw new AssertionError("Thiếu bài thi cần xem");
            table.setRowSelectionInterval(chosen, chosen);
            JButton detail = (JButton) field(board, "btnXemChiTiet");
            if (!detail.isEnabled()) throw new AssertionError("Chọn dòng phải cho xem chi tiết");
            detail.doClick();
            return null;
        });
        await(() -> showing("baocao.XemKetQua") != null, "Phải mở chi tiết bài đã chọn");
        edt(() -> {
            JFrame detail = showing("baocao.XemKetQua");
            if (field(detail, "nguoiDung") != sv) throw new AssertionError("Chi tiết phải giữ session");
            if (!String.valueOf(lan).equals(((JTextField) field(detail, "txtLanThi")).getText())
                    || !diem.equals(((JTextField) field(detail, "txtDiem")).getText()))
                throw new AssertionError("Sau sắp xếp vẫn phải mở đúng môn/lần thi được chọn, không lấy bài gần nhất");
            String mon = ((JTextField) field(detail, "txtMonHoc")).getText();
            if (!(maMH.equals("VFY01") ? "Môn kiểm thử" : "Môn chưa có câu").equals(mon))
                throw new AssertionError("Chi tiết phải đúng mã môn đã chọn");
            JTable answers = (JTable) field(detail, "tblChiTietBaiThi");
            if (answers.getRowCount() != 10) throw new AssertionError("Phải đọc đủ chi tiết đã lưu");
            for (int row = 0; row < answers.getRowCount(); row++)
                for (int column = 0; column < answers.getColumnCount(); column++)
                    if (answers.isCellEditable(row, column)) throw new AssertionError("Không được sửa đáp án đã nộp");
            if (((JTextField) field(detail, "txtDiem")).isEditable()) throw new AssertionError("Không được sửa điểm");
            ((JButton) field(detail, "btnTroVe")).doClick();
            return null;
        });
        await(() -> {
            JFrame back = showing("baocao.BangDiemSinhVien");
            return back != null && ((JTable) field(back, "tblBangDiem")).getRowCount() == 3;
        }, "Chi tiết phải trở về bảng điểm sinh viên");
    }

    public static void main(String[] args) throws Exception {
        if (!"THITRACNGHIEM_VERIFY".equals(System.getProperty("thitracnghiem.database")))
            throw new AssertionError("Chỉ chạy với database kiểm thử riêng");
        try {
            prepare();
            sessionSinhVien sv = student("VFY00001");
            JFrame board = openBoard(sv);
            await(() -> ((JTable) field(board, "tblBangDiem")).getRowCount() == 3,
                    "Chỉ hiện 3 bài đã hoàn thành của sinh viên này, không hiện bài dở hay điểm người khác");
            edt(() -> {
                JTable table = (JTable) field(board, "tblBangDiem");
                if (((JButton) field(board, "btnXemChiTiet")).isEnabled())
                    throw new AssertionError("Chưa chọn bài thì chưa cho xem chi tiết");
                for (int row = 0; row < table.getRowCount(); row++)
                    for (int col = 0; col < table.getColumnCount(); col++)
                        if (table.isCellEditable(row, col)) throw new AssertionError("Bảng điểm phải chỉ đọc");
                if (!"VFY00001".equals(((JTextField) field(board, "txtMaSV")).getText()))
                    throw new AssertionError("Thông tin phải lấy từ session");
                new File("target/verify-bangdiem").mkdirs();
                VerifyAppLayout.save(board, "target/verify-bangdiem", "bang-diem-sinh-vien");
                return null;
            });
            selectAndReview(board, sv, "VFY01", 1, "3.00");
            JFrame returned = edt(() -> showing("baocao.BangDiemSinhVien"));
            selectAndReview(returned, sv, "VFY02", 1, "1.00");
            edt(() -> {
                JFrame back = showing("baocao.BangDiemSinhVien");
                ((JButton) field(back, "btnTroVe")).doClick();
                if (showing("trangchu.TrangChu") == null) throw new AssertionError("Bảng điểm phải về TrangChu");
                for (Window w : Window.getWindows()) w.dispose();
                return null;
            });
            JFrame empty = openBoard(student("VFY00003"));
            await(() -> ((JLabel) field(empty, "lblTrangThai")).getText().contains("chưa có bài thi"),
                    "Sinh viên chưa thi phải thấy thông báo trống");
            JFrame designer = edt(() -> {
                JFrame preview = (JFrame) Class.forName("baocao.BangDiemSinhVien").getConstructor().newInstance();
                preview.setVisible(true); return preview;
            });
            if (!edt(() -> ((JTable) field(designer, "tblBangDiem")).getRowCount() == 0
                    && !((JButton) field(designer, "btnXemChiTiet")).isEnabled()))
                throw new AssertionError("Form Design không có session phải để bảng trống");
            sql("IF (SELECT COUNT(*) FROM BANGDIEM)<>4 THROW 51001,N'Xem điểm không được ghi hoặc sửa kết quả',1;");
            System.out.println("PASS: đúng session, bảng chỉ đọc, không hiện bài dở, chọn bài sau sắp xếp, đúng môn/lần, chi tiết và trở về.");
        } finally {
            edt(() -> { for (Window window : Window.getWindows()) window.dispose(); return null; });
        }
    }
}
