package quanly;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Supplier;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

final class DotChinhSua {
    private enum CheDo { XEM, THEM, HIEU_CHINH }

    record Nut(JButton them, JButton hieuChinh, JButton xoa, JButton ghi, JButton phucHoi, JButton tim) {}
    private record Buoc(int dong, Object[] truoc, Object[] sau) {}

    @FunctionalInterface
    interface GhiDot {
        void ghi(String thaoTac, List<Object[]> truoc, List<Object[]> sau) throws SQLException;
    }

    @FunctionalInterface
    interface PhucHoiDaGhi {
        void phucHoi() throws SQLException;
    }

    private final JTable bang;
    private final DefaultTableModel model;
    private final Nut nut;
    private final List<JComponent> oNhap;
    private final List<JComponent> oKhoa;
    private final int[] cotKhoa;
    private final Supplier<Object[]> docO;
    private final Consumer<Object[]> dienO;
    private final Consumer<SQLException> baoLoi;
    private final Deque<Buoc> lichSu = new ArrayDeque<>();
    private final Set<Integer> dongDaSua = new LinkedHashSet<>();
    private CheDo cheDo = CheDo.XEM;
    private List<Object[]> bangGoc = List.of();
    private List<Object> giaTriBanDau = List.of();
    private int dongBanDau = -1;
    private int dongDangNhap = -1;
    private boolean dangCapNhat;

    DotChinhSua(JTable bang, Nut nut, Supplier<Object[]> docO, Consumer<Object[]> dienO,
            Consumer<SQLException> baoLoi, List<JComponent> oNhap, List<JComponent> oKhoa, int... cotKhoa) {
        this.bang = bang;
        this.model = (DefaultTableModel) bang.getModel();
        this.nut = nut;
        this.docO = docO;
        this.dienO = dienO;
        this.baoLoi = baoLoi;
        this.oNhap = oNhap;
        this.oKhoa = oKhoa;
        this.cotKhoa = cotKhoa;
        nut.ghi.setToolTipText("Lưu cả đợt xuống cơ sở dữ liệu.");

        bang.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && dangNhap() && !dangCapNhat) doiDong();
        });
        bang.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), "boDongCho");
        bang.getActionMap().put("boDongCho", new AbstractAction() {
            @Override public void actionPerformed(ActionEvent e) {
                if (dangThem() && dongDangNhap >= 0) boDongCho();
            }
        });

        for (JComponent o : oNhap) {
            if (o instanceof JTextComponent text) {
                text.getDocument().addDocumentListener(new DocumentListener() {
                    @Override public void insertUpdate(DocumentEvent e) { capNhatNutNhap(); }
                    @Override public void removeUpdate(DocumentEvent e) { capNhatNutNhap(); }
                    @Override public void changedUpdate(DocumentEvent e) { capNhatNutNhap(); }
                });
            } else if (o instanceof JComboBox<?> combo) {
                combo.addActionListener(e -> capNhatNutNhap());
            }
        }
    }

    boolean dangNhap() {
        return cheDo != CheDo.XEM;
    }

    boolean dangThem() {
        return cheDo == CheDo.THEM;
    }

    void capNhatGiaoDien(boolean choPhep) {
        boolean xem = choPhep && !dangNhap();
        bang.setEnabled(choPhep);
        nut.them.setEnabled(choPhep && cheDo != CheDo.HIEU_CHINH);
        nut.them.setToolTipText(dangThem() ? "Đưa dòng đang nhập vào bảng chờ." : "Mở chế độ thêm.");
        nut.hieuChinh.setEnabled(choPhep && !dangThem() && model.getRowCount() > 0);
        nut.hieuChinh.setToolTipText(cheDo == CheDo.HIEU_CHINH
                ? "Chốt thay đổi của dòng đang sửa." : "Mở chế độ hiệu chỉnh.");
        nut.xoa.setEnabled(xem);
        if (nut.tim != null) {
            nut.tim.setEnabled(choPhep);
            nut.tim.setText(dangNhap() ? "Hủy" : "Tìm");
        }
        nut.phucHoi.setEnabled(xem);
        nut.ghi.setEnabled(false);
        for (JComponent o : oNhap) {
            boolean sua = choPhep && dangNhap() && (dangThem() || !oKhoa.contains(o));
            if (o instanceof JTextComponent text) text.setEditable(sua);
            else o.setEnabled(sua);
        }
        capNhatNutNhap();
    }

    void them() {
        if (!dangThem()) {
            batDau(CheDo.THEM);
            return;
        }
        capNhatBang(() -> {
            if (dongDangNhap < 0 && !coBanNhap()) {
                throw new IllegalArgumentException("Vui lòng nhập thông tin của dòng cần thêm.");
            }
            chotDong();
            hienDong(-1);
        });
    }

    void hieuChinh() {
        if (cheDo == CheDo.HIEU_CHINH) {
            capNhatBang(this::chotDong);
        } else if (model.getRowCount() > 0) {
            batDau(CheDo.HIEU_CHINH);
        }
    }

    private void batDau(CheDo cheDoMoi) {
        if (dangNhap()) return;
        bangGoc = new ArrayList<>();
        for (int i = 0; i < model.getRowCount(); i++) bangGoc.add(docDong(i));
        dongBanDau = dongDaChon();
        capNhatBang(() -> {
            cheDo = cheDoMoi;
            hienDong(dangThem() ? -1 : Math.max(dongBanDau, 0));
        });
    }

    void huy() {
        if ((coBanNhap() || coDongChoGhi()) && JOptionPane.showConfirmDialog(bang,
                "Bỏ các thay đổi chưa ghi?", "Hủy", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) return;
        capNhatBang(() -> {
            lamRongBang();
            for (Object[] dong : bangGoc) model.addRow(dong);
            hienDong(dongBanDau);
            ketThuc();
        });
    }

    void ghi(GhiDot ghiDot, Runnable taiLai) {
        if (!dangNhap()) return;
        capNhatBang(() -> {
            kiemTraDaChotDong();
            List<Object[]> truoc = new ArrayList<>();
            List<Object[]> sau = new ArrayList<>();
            if (dangThem()) {
                for (int i = 0; i < model.getRowCount(); i++) sau.add(docDong(i));
            } else {
                for (int dong : dongDaSua) {
                    truoc.add(bangGoc.get(dong));
                    sau.add(docDong(dong));
                }
            }
            if (sau.isEmpty()) throw new IllegalArgumentException("Chưa có thay đổi để ghi.");

            String thaoTac = dangThem() ? "thêm" : "cập nhật";
            String[] luaChon = {"Có", "Không"};
            int xacNhan = JOptionPane.showOptionDialog(bang,
                    "Xác nhận " + thaoTac + " " + sau.size() + " dòng?", "Xác nhận ghi",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, luaChon, luaChon[1]);
            if (xacNhan != JOptionPane.YES_OPTION) return;

            try {
                ghiDot.ghi(dangThem() ? "I" : "U", truoc, sau);
            } catch (SQLException e) {
                baoLoi.accept(e);
                return;
            }
            ketThuc();
            lamRongBang();
            hienDong(-1);
            taiLai.run();
        });
    }

    void phucHoi(PhucHoiDaGhi phucHoiDaGhi, Runnable taiLai) {
        if (dangNhap()) {
            capNhatBang(this::luiMotBuoc);
            return;
        }
        try {
            phucHoiDaGhi.phucHoi();
            lamRongBang();
            hienDong(-1);
            taiLai.run();
        } catch (SQLException e) {
            baoLoi.accept(e);
        }
    }

    private void ketThuc() {
        cheDo = CheDo.XEM;
        lichSu.clear();
        dongDaSua.clear();
        bangGoc = List.of();
        giaTriBanDau = List.of();
    }

    private void doiDong() {
        int dongMoi = dongDaChon();
        if (dongMoi == dongDangNhap) return;
        capNhatBang(() -> {
            if (!dangThem() && dongMoi < 0) {
                chonDong(dongDangNhap);
                return;
            }
            try {
                kiemTraDaChotDong();
            } catch (IllegalArgumentException e) {
                chonDong(dongDangNhap);
                throw e;
            }
            hienDong(dongMoi);
        });
    }

    private void kiemTraDaChotDong() {
        if (coBanNhap()) {
            String nutChot = dangThem() ? "Thêm" : "Hiệu chỉnh";
            throw new IllegalArgumentException("Còn dòng chưa chốt. Bấm " + nutChot
                    + " để chốt dòng hoặc Phục hồi để bỏ phần đang nhập.");
        }
    }

    private void chotDong() {
        if (!coBanNhap()) return;
        Object[] truoc = dongDangNhap < 0 ? null : docDong(dongDangNhap);
        if (!dangThem() && truoc == null) throw new IllegalArgumentException("Vui lòng chọn dòng cần hiệu chỉnh.");
        Object[] sau = docO.get();
        if (dangThem()) {
            kiemTraTrungDong(sau);
        } else {
            for (int cot : cotKhoa) sau[cot] = truoc[cot];
        }

        if (truoc == null) {
            dongDangNhap = model.getRowCount();
            model.addRow(sau);
            lichSu.push(new Buoc(dongDangNhap, null, sau));
            chonDong(dongDangNhap);
        } else if (!Arrays.equals(truoc, sau)) {
            ghiDong(dongDangNhap, sau);
            lichSu.push(new Buoc(dongDangNhap, truoc, sau));
        }
        giaTriBanDau = giaTriO();
    }

    private void kiemTraTrungDong(Object[] dong) {
        for (int cot : cotKhoa) {
            if (dong[cot] == null) return;
        }
        for (int i = 0; i < model.getRowCount(); i++) {
            if (i == dongDangNhap) continue;
            boolean trung = true;
            for (int cot : cotKhoa) {
                if (!chuoi(dong, cot).equalsIgnoreCase(Objects.toString(model.getValueAt(i, cot), ""))) {
                    trung = false;
                    break;
                }
            }
            if (trung) throw new IllegalArgumentException("Dòng này đã có trong bảng chờ.");
        }
    }

    private void boDongCho() {
        capNhatBang(() -> {
            lichSu.push(new Buoc(dongDangNhap, docDong(dongDangNhap), null));
            chonDong(-1);
            model.removeRow(dongDangNhap);
            hienDong(-1);
        });
    }

    private void luiMotBuoc() {
        if (coBanNhap()) {
            hienDong(dongDangNhap);
            return;
        }
        Buoc buoc = lichSu.pollFirst();
        if (buoc == null) {
            if (nut.tim == null) huy();
            return;
        }
        if (buoc.truoc == null) {
            chonDong(-1);
            model.removeRow(buoc.dong);
            hienDong(-1);
        } else {
            if (buoc.sau == null) model.insertRow(buoc.dong, buoc.truoc);
            else ghiDong(buoc.dong, buoc.truoc);
            hienDong(buoc.dong);
        }
    }

    private void capNhatBang(Runnable viec) {
        dangCapNhat = true;
        try {
            viec.run();
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(bang, e.getMessage());
        } finally {
            dangCapNhat = false;
            capNhatNutNhap();
        }
    }

    private void capNhatNutNhap() {
        if (!dangNhap() || dangCapNhat) return;
        nut.ghi.setEnabled(coDongChoGhi());
        nut.phucHoi.setEnabled(nut.tim == null || !lichSu.isEmpty() || coBanNhap());
    }

    private boolean coDongChoGhi() {
        return dangThem() ? model.getRowCount() > 0 : !dongDaSua.isEmpty();
    }

    private boolean coBanNhap() {
        return !giaTriBanDau.equals(giaTriO());
    }

    private List<Object> giaTriO() {
        List<Object> giaTri = new ArrayList<>();
        for (JComponent o : oNhap) {
            giaTri.add(o instanceof JTextComponent text ? text.getText() : ((JComboBox<?>) o).getSelectedItem());
        }
        return giaTri;
    }

    private int dongDaChon() {
        int dong = bang.getSelectedRow();
        return dong < 0 ? -1 : bang.convertRowIndexToModel(dong);
    }

    private void chonDong(int dong) {
        if (dong < 0) {
            bang.clearSelection();
            bang.getSelectionModel().setAnchorSelectionIndex(-1);
            bang.getSelectionModel().setLeadSelectionIndex(-1);
        } else {
            int viTri = bang.convertRowIndexToView(dong);
            bang.setRowSelectionInterval(viTri, viTri);
        }
    }

    private void hienDong(int dong) {
        dongDangNhap = dong;
        chonDong(dong);
        dienO.accept(dong < 0 ? null : docDong(dong));
        giaTriBanDau = giaTriO();
    }

    private Object[] docDong(int dong) {
        Object[] giaTri = new Object[model.getColumnCount()];
        for (int cot = 0; cot < giaTri.length; cot++) giaTri[cot] = model.getValueAt(dong, cot);
        return giaTri;
    }

    private void ghiDong(int dong, Object[] giaTri) {
        for (int cot = 0; cot < giaTri.length; cot++) model.setValueAt(giaTri[cot], dong, cot);
        if (cheDo == CheDo.HIEU_CHINH) {
            if (Arrays.equals(bangGoc.get(dong), giaTri)) dongDaSua.remove(dong);
            else dongDaSua.add(dong);
        }
    }

    static String chuoi(Object[] dong, int cot) {
        return dong == null ? "" : Objects.toString(dong[cot], "");
    }

    static String[] tuKhoa(Component cha, String... nhan) {
        JPanel panel = new JPanel(new GridLayout(0, 2, 8, 6));
        JTextField[] oNhap = new JTextField[nhan.length];
        for (int i = 0; i < nhan.length; i++) {
            panel.add(new JLabel(nhan[i]));
            oNhap[i] = new JTextField(16);
            panel.add(oNhap[i]);
        }
        if (JOptionPane.showConfirmDialog(cha, panel, "Tìm (để trống để xem tất cả)",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE) != JOptionPane.OK_OPTION) return null;
        String[] giaTri = new String[nhan.length];
        for (int i = 0; i < nhan.length; i++) giaTri[i] = oNhap[i].getText().strip();
        return giaTri;
    }
}
