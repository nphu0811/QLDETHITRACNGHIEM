package quanly;

import java.util.List;
import java.util.Locale;
import java.util.Objects;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;
import connectionSQLServer.QuanLyDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

import session.sessionGiaoVien;

/**
 * Giao diện NhapBoDe. Nghiệp vụ sẽ được bổ sung trong các event handler.
 * Chỉnh bố cục bằng tab Design của NetBeans và giữ file .form đi kèm.
 */
public class NhapBoDe extends javax.swing.JFrame {
    private final sessionGiaoVien nguoiDung;
    private final DotChinhSua dot;

    public NhapBoDe() {
        this(null);
    }

    public NhapBoDe(sessionGiaoVien nguoiDung) {
        initComponents();
        this.nguoiDung = nguoiDung;
        getContentPane().setBackground(new java.awt.Color(245, 246, 248));
        setLocationRelativeTo(null);

        txtMaGV.setEditable(false);
        tblBoDe.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        dot = new DotChinhSua(tblBoDe, new DotChinhSua.Nut(btnThem, btnHieuChinh, btnXoa, btnGhi, btnPhucHoi, btnTim),
                this::docCauHoi, this::dienCauHoi, this::baoLoi,
                List.of(cboMonHoc, cboTrinhDo, txtNoiDung, txtPhuongAnA, txtPhuongAnB, txtPhuongAnC, txtPhuongAnD, cboDapAn),
                List.of(), 0);
        boolean daDangNhap = nguoiDung != null && nguoiDung.coKetNoi();
        if (daDangNhap) {
            try {
                List<Object[]> duLieu = QuanLyDAO.layDanhMucMonHoc(nguoiDung);
                DefaultComboBoxModel<LuaChon> model = new DefaultComboBoxModel<>();
                model.addElement(new LuaChon("", "Chọn môn học"));
                for (Object[] dong : duLieu) {
                    String ma = Objects.toString(dong[0], "");
                    String ten = Objects.toString(dong[1], "");
                    model.addElement(new LuaChon(ma, ten.isEmpty() ? ma : ma + " - " + ten));
                }
                cboMonHoc.setModel(model);
            } catch (SQLException e) {
                baoLoi(e);
            }
            taiBoDe();
        }
        capNhatCheDo();
    }

    private ArrayList<Object[]> danhSachCauHoi = new ArrayList<>();

    private void capNhatCheDo() {
        boolean daDangNhap = nguoiDung != null && nguoiDung.coKetNoi();
        dot.capNhatGiaoDien(daDangNhap);
        txtTuKhoa.setEditable(daDangNhap && !dot.dangNhap());
    }

    private void taiBoDe() {
        try {
            danhSachCauHoi = QuanLyDAO.layBoDe(nguoiDung);
            napBang(tblBoDe, danhSachCauHoi);
            dienCauHoi(null);
            capNhatCheDo();
        } catch (SQLException e) {
            baoLoi(e);
        }
    }

    private void dienCauHoi(Object[] dong) {
        txtCauHoi.setText(DotChinhSua.chuoi(dong, 0));
        chonMa(cboMonHoc, DotChinhSua.chuoi(dong, 1));
        cboTrinhDo.setSelectedItem(dong == null || dong[2] == null ? "A" : dong[2]);
        txtNoiDung.setText(DotChinhSua.chuoi(dong, 3));
        txtPhuongAnA.setText(DotChinhSua.chuoi(dong, 4));
        txtPhuongAnB.setText(DotChinhSua.chuoi(dong, 5));
        txtPhuongAnC.setText(DotChinhSua.chuoi(dong, 6));
        txtPhuongAnD.setText(DotChinhSua.chuoi(dong, 7));
        cboDapAn.setSelectedItem(dong == null || dong[8] == null ? "A" : dong[8]);
        txtMaGV.setText(dong == null ? nguoiDung.getMaGV() : DotChinhSua.chuoi(dong, 9));
    }

    private Object[] docCauHoi() {
        String maMH = maDaChon(cboMonHoc);
        if (maMH.isEmpty()) throw new IllegalArgumentException("Vui lòng chọn môn học.");
        Integer soCau = dot.dangThem() ? null : Integer.valueOf(txtCauHoi.getText());
        String noiDung = nhap(txtNoiDung, "Nội dung câu hỏi", 200, true);
        String a = nhap(txtPhuongAnA, "Phương án A", 200, true);
        String b = nhap(txtPhuongAnB, "Phương án B", 200, true);
        String c = nhap(txtPhuongAnC, "Phương án C", 200, true);
        String d = nhap(txtPhuongAnD, "Phương án D", 200, true);
        return new Object[]{soCau, maMH, cboTrinhDo.getSelectedItem(), noiDung,
            a, b, c, d, cboDapAn.getSelectedItem(), nguoiDung.getMaGV()};
    }

    private record LuaChon(String ma, String ten) {
        @Override
        public String toString() {
            return ten;
        }
    }

    private String maDaChon(JComboBox<?> combo) {
        return combo.getSelectedItem() instanceof LuaChon muc ? muc.ma() : "";
    }

    private void chonMa(JComboBox<?> combo, String ma) {
        for (int i = 0; i < combo.getItemCount(); i++) {
            if (combo.getItemAt(i) instanceof LuaChon muc && muc.ma().equalsIgnoreCase(ma)) {
                combo.setSelectedIndex(i);
                return;
            }
        }
        combo.setSelectedIndex(combo.getItemCount() == 0 ? -1 : 0);
    }

    private void napBang(JTable bang, List<Object[]> danhSach) {
        DefaultTableModel model = (DefaultTableModel) bang.getModel();
        model.setRowCount(0);
        for (Object[] dong : danhSach) {
            model.addRow(dong);
        }
    }

    private String nhap(JTextComponent oNhap, String ten, int toiDa, boolean batBuoc) {
        String giaTri = oNhap.getText().strip();
        if (giaTri.length() > toiDa || (batBuoc && giaTri.isEmpty())) {
            oNhap.requestFocusInWindow();
            throw new IllegalArgumentException(ten + (batBuoc ? " phải có từ 1 đến " : " có tối đa ") + toiDa + " ký tự.");
        }
        return giaTri;
    }

    private Object[] dongDaChon(JTable bang) {
        int viTri = bang.getSelectedRow();
        if (viTri < 0) return null;
        int dongModel = bang.convertRowIndexToModel(viTri);
        Object[] dong = new Object[bang.getModel().getColumnCount()];
        for (int cot = 0; cot < dong.length; cot++) {
            dong[cot] = bang.getModel().getValueAt(dongModel, cot);
        }
        return dong;
    }

    private boolean khop(Object giaTri, String tuKhoa) {
        return Objects.toString(giaTri, "").toLowerCase(Locale.ROOT).contains(tuKhoa.toLowerCase(Locale.ROOT));
    }

    private void baoLoi(SQLException e) {
        String noiDung = switch (e.getErrorCode()) {
            case 2601, 2627 -> "Mã đã tồn tại.";
            case 547 -> "Dữ liệu đang được sử dụng hoặc thông tin liên quan không hợp lệ.";
            default -> e.getMessage();
        };
        JOptionPane.showMessageDialog(pnlNoiDung, noiDung, "Thông báo", JOptionPane.ERROR_MESSAGE);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        pnlNoiDung = new javax.swing.JPanel();
        pnlNoiDung.setBackground(new java.awt.Color(255, 255, 255));
        pnlNoiDung.setBorder(javax.swing.BorderFactory.createEmptyBorder(24, 24, 24, 24));
        lblTieuDe = new javax.swing.JLabel();
        lblTieuDe.setText("BỘ ĐỀ TRẮC NGHIỆM");
        lblTieuDe.setFont(new java.awt.Font("Segoe UI", 1, 24));
        lblTieuDe.setForeground(new java.awt.Color(205, 32, 45));
        lblMoTa = new javax.swing.JLabel();
        lblMoTa.setText("Biên soạn câu hỏi theo môn học và trình độ");
        lblMoTa.setFont(new java.awt.Font("Segoe UI", 0, 13));
        lblMoTa.setForeground(new java.awt.Color(107, 114, 128));
        pnlCboMonHoc = new javax.swing.JPanel();
        pnlCboMonHoc.setBackground(new java.awt.Color(255, 255, 255));
        lblCboMonHoc = new javax.swing.JLabel();
        lblCboMonHoc.setText("Môn học");
        lblCboMonHoc.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblCboMonHoc.setForeground(new java.awt.Color(36, 43, 54));
        cboMonHoc = new javax.swing.JComboBox();
        cboMonHoc.setFont(new java.awt.Font("Segoe UI", 0, 14));
        cboMonHoc.setForeground(new java.awt.Color(36, 43, 54));
        cboMonHoc.setBackground(new java.awt.Color(255, 255, 255));
        cboMonHoc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"Chọn môn học"}));
        javax.swing.GroupLayout pnlCboMonHocLayout = new javax.swing.GroupLayout(pnlCboMonHoc);
        pnlCboMonHoc.setLayout(pnlCboMonHocLayout);
        pnlCboMonHocLayout.setHorizontalGroup(pnlCboMonHocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCboMonHocLayout.createSequentialGroup()
                .addComponent(lblCboMonHoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlCboMonHocLayout.createSequentialGroup()
                .addComponent(cboMonHoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        pnlCboMonHocLayout.setVerticalGroup(pnlCboMonHocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCboMonHocLayout.createSequentialGroup()
                .addGroup(pnlCboMonHocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCboMonHoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlCboMonHocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cboMonHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))));
        pnlCboTrinhDo = new javax.swing.JPanel();
        pnlCboTrinhDo.setBackground(new java.awt.Color(255, 255, 255));
        lblCboTrinhDo = new javax.swing.JLabel();
        lblCboTrinhDo.setText("Trình độ");
        lblCboTrinhDo.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblCboTrinhDo.setForeground(new java.awt.Color(36, 43, 54));
        cboTrinhDo = new javax.swing.JComboBox();
        cboTrinhDo.setFont(new java.awt.Font("Segoe UI", 0, 14));
        cboTrinhDo.setForeground(new java.awt.Color(36, 43, 54));
        cboTrinhDo.setBackground(new java.awt.Color(255, 255, 255));
        cboTrinhDo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"A", "B", "C"}));
        javax.swing.GroupLayout pnlCboTrinhDoLayout = new javax.swing.GroupLayout(pnlCboTrinhDo);
        pnlCboTrinhDo.setLayout(pnlCboTrinhDoLayout);
        pnlCboTrinhDoLayout.setHorizontalGroup(pnlCboTrinhDoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCboTrinhDoLayout.createSequentialGroup()
                .addComponent(lblCboTrinhDo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlCboTrinhDoLayout.createSequentialGroup()
                .addComponent(cboTrinhDo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        pnlCboTrinhDoLayout.setVerticalGroup(pnlCboTrinhDoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCboTrinhDoLayout.createSequentialGroup()
                .addGroup(pnlCboTrinhDoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCboTrinhDo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlCboTrinhDoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cboTrinhDo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))));
        pnlTxtMaGV = new javax.swing.JPanel();
        pnlTxtMaGV.setBackground(new java.awt.Color(255, 255, 255));
        lblTxtMaGV = new javax.swing.JLabel();
        lblTxtMaGV.setText("Mã giáo viên");
        lblTxtMaGV.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblTxtMaGV.setForeground(new java.awt.Color(36, 43, 54));
        txtMaGV = new javax.swing.JTextField();
        txtMaGV.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtMaGV.setForeground(new java.awt.Color(36, 43, 54));
        txtMaGV.setBackground(new java.awt.Color(255, 255, 255));
        txtMaGV.setEditable(true);
        javax.swing.GroupLayout pnlTxtMaGVLayout = new javax.swing.GroupLayout(pnlTxtMaGV);
        pnlTxtMaGV.setLayout(pnlTxtMaGVLayout);
        pnlTxtMaGVLayout.setHorizontalGroup(pnlTxtMaGVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtMaGVLayout.createSequentialGroup()
                .addComponent(lblTxtMaGV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlTxtMaGVLayout.createSequentialGroup()
                .addComponent(txtMaGV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        pnlTxtMaGVLayout.setVerticalGroup(pnlTxtMaGVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtMaGVLayout.createSequentialGroup()
                .addGroup(pnlTxtMaGVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTxtMaGV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlTxtMaGVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMaGV, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))));
        pnlTxtCauHoi = new javax.swing.JPanel();
        pnlTxtCauHoi.setBackground(new java.awt.Color(255, 255, 255));
        lblTxtCauHoi = new javax.swing.JLabel();
        lblTxtCauHoi.setText("Số câu trong bộ đề");
        lblTxtCauHoi.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblTxtCauHoi.setForeground(new java.awt.Color(36, 43, 54));
        txtCauHoi = new javax.swing.JTextField();
        txtCauHoi.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtCauHoi.setForeground(new java.awt.Color(36, 43, 54));
        txtCauHoi.setBackground(new java.awt.Color(255, 255, 255));
        txtCauHoi.setEditable(false);
        javax.swing.GroupLayout pnlTxtCauHoiLayout = new javax.swing.GroupLayout(pnlTxtCauHoi);
        pnlTxtCauHoi.setLayout(pnlTxtCauHoiLayout);
        pnlTxtCauHoiLayout.setHorizontalGroup(pnlTxtCauHoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtCauHoiLayout.createSequentialGroup()
                .addComponent(lblTxtCauHoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlTxtCauHoiLayout.createSequentialGroup()
                .addComponent(txtCauHoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        pnlTxtCauHoiLayout.setVerticalGroup(pnlTxtCauHoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtCauHoiLayout.createSequentialGroup()
                .addGroup(pnlTxtCauHoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTxtCauHoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlTxtCauHoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCauHoi, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))));
        pnlCboDapAn = new javax.swing.JPanel();
        pnlCboDapAn.setBackground(new java.awt.Color(255, 255, 255));
        lblCboDapAn = new javax.swing.JLabel();
        lblCboDapAn.setText("Đáp án đúng");
        lblCboDapAn.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblCboDapAn.setForeground(new java.awt.Color(36, 43, 54));
        cboDapAn = new javax.swing.JComboBox();
        cboDapAn.setFont(new java.awt.Font("Segoe UI", 0, 14));
        cboDapAn.setForeground(new java.awt.Color(36, 43, 54));
        cboDapAn.setBackground(new java.awt.Color(255, 255, 255));
        cboDapAn.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"A", "B", "C", "D"}));
        javax.swing.GroupLayout pnlCboDapAnLayout = new javax.swing.GroupLayout(pnlCboDapAn);
        pnlCboDapAn.setLayout(pnlCboDapAnLayout);
        pnlCboDapAnLayout.setHorizontalGroup(pnlCboDapAnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCboDapAnLayout.createSequentialGroup()
                .addComponent(lblCboDapAn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlCboDapAnLayout.createSequentialGroup()
                .addComponent(cboDapAn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        pnlCboDapAnLayout.setVerticalGroup(pnlCboDapAnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCboDapAnLayout.createSequentialGroup()
                .addGroup(pnlCboDapAnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCboDapAn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlCboDapAnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cboDapAn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))));
        pnlTxtTuKhoa = new javax.swing.JPanel();
        pnlTxtTuKhoa.setBackground(new java.awt.Color(255, 255, 255));
        lblTxtTuKhoa = new javax.swing.JLabel();
        lblTxtTuKhoa.setText("Tìm câu hỏi");
        lblTxtTuKhoa.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblTxtTuKhoa.setForeground(new java.awt.Color(36, 43, 54));
        txtTuKhoa = new javax.swing.JTextField();
        txtTuKhoa.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtTuKhoa.setForeground(new java.awt.Color(36, 43, 54));
        txtTuKhoa.setBackground(new java.awt.Color(255, 255, 255));
        txtTuKhoa.setEditable(true);
        javax.swing.GroupLayout pnlTxtTuKhoaLayout = new javax.swing.GroupLayout(pnlTxtTuKhoa);
        pnlTxtTuKhoa.setLayout(pnlTxtTuKhoaLayout);
        pnlTxtTuKhoaLayout.setHorizontalGroup(pnlTxtTuKhoaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtTuKhoaLayout.createSequentialGroup()
                .addComponent(lblTxtTuKhoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlTxtTuKhoaLayout.createSequentialGroup()
                .addComponent(txtTuKhoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        pnlTxtTuKhoaLayout.setVerticalGroup(pnlTxtTuKhoaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtTuKhoaLayout.createSequentialGroup()
                .addGroup(pnlTxtTuKhoaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTxtTuKhoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlTxtTuKhoaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTuKhoa, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))));
        pnlTxtNoiDung = new javax.swing.JPanel();
        pnlTxtNoiDung.setBackground(new java.awt.Color(255, 255, 255));
        lblTxtNoiDung = new javax.swing.JLabel();
        lblTxtNoiDung.setText("Nội dung câu hỏi (tối đa 200 ký tự)");
        lblTxtNoiDung.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblTxtNoiDung.setForeground(new java.awt.Color(36, 43, 54));
        scrTxtNoiDung = new javax.swing.JScrollPane();
        txtNoiDung = new javax.swing.JTextArea();
        txtNoiDung.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtNoiDung.setLineWrap(true);
        txtNoiDung.setWrapStyleWord(true);
        txtNoiDung.setRows(3);
        txtNoiDung.setColumns(24);
        txtNoiDung.setEditable(true);
        scrTxtNoiDung.setViewportView(txtNoiDung);
        javax.swing.GroupLayout pnlTxtNoiDungLayout = new javax.swing.GroupLayout(pnlTxtNoiDung);
        pnlTxtNoiDung.setLayout(pnlTxtNoiDungLayout);
        pnlTxtNoiDungLayout.setHorizontalGroup(pnlTxtNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtNoiDungLayout.createSequentialGroup()
                .addComponent(lblTxtNoiDung, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlTxtNoiDungLayout.createSequentialGroup()
                .addComponent(scrTxtNoiDung, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        pnlTxtNoiDungLayout.setVerticalGroup(pnlTxtNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtNoiDungLayout.createSequentialGroup()
                .addGroup(pnlTxtNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTxtNoiDung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlTxtNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrTxtNoiDung, javax.swing.GroupLayout.PREFERRED_SIZE, 70, Short.MAX_VALUE))));
        pnlTxtPhuongAnA = new javax.swing.JPanel();
        pnlTxtPhuongAnA.setBackground(new java.awt.Color(255, 255, 255));
        lblTxtPhuongAnA = new javax.swing.JLabel();
        lblTxtPhuongAnA.setText("Phương án A");
        lblTxtPhuongAnA.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblTxtPhuongAnA.setForeground(new java.awt.Color(36, 43, 54));
        txtPhuongAnA = new javax.swing.JTextField();
        txtPhuongAnA.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtPhuongAnA.setForeground(new java.awt.Color(36, 43, 54));
        txtPhuongAnA.setBackground(new java.awt.Color(255, 255, 255));
        txtPhuongAnA.setEditable(true);
        javax.swing.GroupLayout pnlTxtPhuongAnALayout = new javax.swing.GroupLayout(pnlTxtPhuongAnA);
        pnlTxtPhuongAnA.setLayout(pnlTxtPhuongAnALayout);
        pnlTxtPhuongAnALayout.setHorizontalGroup(pnlTxtPhuongAnALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtPhuongAnALayout.createSequentialGroup()
                .addComponent(lblTxtPhuongAnA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlTxtPhuongAnALayout.createSequentialGroup()
                .addComponent(txtPhuongAnA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        pnlTxtPhuongAnALayout.setVerticalGroup(pnlTxtPhuongAnALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtPhuongAnALayout.createSequentialGroup()
                .addGroup(pnlTxtPhuongAnALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTxtPhuongAnA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlTxtPhuongAnALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPhuongAnA, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))));
        pnlTxtPhuongAnB = new javax.swing.JPanel();
        pnlTxtPhuongAnB.setBackground(new java.awt.Color(255, 255, 255));
        lblTxtPhuongAnB = new javax.swing.JLabel();
        lblTxtPhuongAnB.setText("Phương án B");
        lblTxtPhuongAnB.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblTxtPhuongAnB.setForeground(new java.awt.Color(36, 43, 54));
        txtPhuongAnB = new javax.swing.JTextField();
        txtPhuongAnB.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtPhuongAnB.setForeground(new java.awt.Color(36, 43, 54));
        txtPhuongAnB.setBackground(new java.awt.Color(255, 255, 255));
        txtPhuongAnB.setEditable(true);
        javax.swing.GroupLayout pnlTxtPhuongAnBLayout = new javax.swing.GroupLayout(pnlTxtPhuongAnB);
        pnlTxtPhuongAnB.setLayout(pnlTxtPhuongAnBLayout);
        pnlTxtPhuongAnBLayout.setHorizontalGroup(pnlTxtPhuongAnBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtPhuongAnBLayout.createSequentialGroup()
                .addComponent(lblTxtPhuongAnB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlTxtPhuongAnBLayout.createSequentialGroup()
                .addComponent(txtPhuongAnB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        pnlTxtPhuongAnBLayout.setVerticalGroup(pnlTxtPhuongAnBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtPhuongAnBLayout.createSequentialGroup()
                .addGroup(pnlTxtPhuongAnBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTxtPhuongAnB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlTxtPhuongAnBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPhuongAnB, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))));
        pnlTxtPhuongAnC = new javax.swing.JPanel();
        pnlTxtPhuongAnC.setBackground(new java.awt.Color(255, 255, 255));
        lblTxtPhuongAnC = new javax.swing.JLabel();
        lblTxtPhuongAnC.setText("Phương án C");
        lblTxtPhuongAnC.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblTxtPhuongAnC.setForeground(new java.awt.Color(36, 43, 54));
        txtPhuongAnC = new javax.swing.JTextField();
        txtPhuongAnC.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtPhuongAnC.setForeground(new java.awt.Color(36, 43, 54));
        txtPhuongAnC.setBackground(new java.awt.Color(255, 255, 255));
        txtPhuongAnC.setEditable(true);
        javax.swing.GroupLayout pnlTxtPhuongAnCLayout = new javax.swing.GroupLayout(pnlTxtPhuongAnC);
        pnlTxtPhuongAnC.setLayout(pnlTxtPhuongAnCLayout);
        pnlTxtPhuongAnCLayout.setHorizontalGroup(pnlTxtPhuongAnCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtPhuongAnCLayout.createSequentialGroup()
                .addComponent(lblTxtPhuongAnC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlTxtPhuongAnCLayout.createSequentialGroup()
                .addComponent(txtPhuongAnC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        pnlTxtPhuongAnCLayout.setVerticalGroup(pnlTxtPhuongAnCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtPhuongAnCLayout.createSequentialGroup()
                .addGroup(pnlTxtPhuongAnCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTxtPhuongAnC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlTxtPhuongAnCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPhuongAnC, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))));
        pnlTxtPhuongAnD = new javax.swing.JPanel();
        pnlTxtPhuongAnD.setBackground(new java.awt.Color(255, 255, 255));
        lblTxtPhuongAnD = new javax.swing.JLabel();
        lblTxtPhuongAnD.setText("Phương án D");
        lblTxtPhuongAnD.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblTxtPhuongAnD.setForeground(new java.awt.Color(36, 43, 54));
        txtPhuongAnD = new javax.swing.JTextField();
        txtPhuongAnD.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtPhuongAnD.setForeground(new java.awt.Color(36, 43, 54));
        txtPhuongAnD.setBackground(new java.awt.Color(255, 255, 255));
        txtPhuongAnD.setEditable(true);
        javax.swing.GroupLayout pnlTxtPhuongAnDLayout = new javax.swing.GroupLayout(pnlTxtPhuongAnD);
        pnlTxtPhuongAnD.setLayout(pnlTxtPhuongAnDLayout);
        pnlTxtPhuongAnDLayout.setHorizontalGroup(pnlTxtPhuongAnDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtPhuongAnDLayout.createSequentialGroup()
                .addComponent(lblTxtPhuongAnD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlTxtPhuongAnDLayout.createSequentialGroup()
                .addComponent(txtPhuongAnD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        pnlTxtPhuongAnDLayout.setVerticalGroup(pnlTxtPhuongAnDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtPhuongAnDLayout.createSequentialGroup()
                .addGroup(pnlTxtPhuongAnDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTxtPhuongAnD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlTxtPhuongAnDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPhuongAnD, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))));
        btnThem = new javax.swing.JButton();
        btnThem.setText("Thêm");
        btnThem.setFont(new java.awt.Font("Segoe UI", 0, 14));
        btnThem.setBackground(new java.awt.Color(245, 246, 248));
        btnThem.setForeground(new java.awt.Color(36, 43, 54));
        btnThem.setOpaque(true);
        btnThem.setContentAreaFilled(true);
        btnThem.setFocusPainted(false);
        btnThem.addActionListener(this::btnThemActionPerformed);
        btnXoa = new javax.swing.JButton();
        btnXoa.setText("Xóa");
        btnXoa.setFont(new java.awt.Font("Segoe UI", 0, 14));
        btnXoa.setBackground(new java.awt.Color(245, 246, 248));
        btnXoa.setForeground(new java.awt.Color(36, 43, 54));
        btnXoa.setOpaque(true);
        btnXoa.setContentAreaFilled(true);
        btnXoa.setFocusPainted(false);
        btnXoa.addActionListener(this::btnXoaActionPerformed);
        btnHieuChinh = new javax.swing.JButton();
        btnHieuChinh.setText("Hiệu chỉnh");
        btnHieuChinh.setFont(new java.awt.Font("Segoe UI", 0, 14));
        btnHieuChinh.setBackground(new java.awt.Color(245, 246, 248));
        btnHieuChinh.setForeground(new java.awt.Color(36, 43, 54));
        btnHieuChinh.setOpaque(true);
        btnHieuChinh.setContentAreaFilled(true);
        btnHieuChinh.setFocusPainted(false);
        btnHieuChinh.addActionListener(this::btnHieuChinhActionPerformed);
        btnPhucHoi = new javax.swing.JButton();
        btnPhucHoi.setText("Phục hồi");
        btnPhucHoi.setFont(new java.awt.Font("Segoe UI", 0, 14));
        btnPhucHoi.setBackground(new java.awt.Color(245, 246, 248));
        btnPhucHoi.setForeground(new java.awt.Color(36, 43, 54));
        btnPhucHoi.setOpaque(true);
        btnPhucHoi.setContentAreaFilled(true);
        btnPhucHoi.setFocusPainted(false);
        btnPhucHoi.addActionListener(this::btnPhucHoiActionPerformed);
        btnTim = new javax.swing.JButton();
        btnTim.setText("Tìm");
        btnTim.setFont(new java.awt.Font("Segoe UI", 0, 14));
        btnTim.setBackground(new java.awt.Color(245, 246, 248));
        btnTim.setForeground(new java.awt.Color(36, 43, 54));
        btnTim.setOpaque(true);
        btnTim.setContentAreaFilled(true);
        btnTim.setFocusPainted(false);
        btnTim.addActionListener(this::btnTimActionPerformed);
        btnGhi = new javax.swing.JButton();
        btnGhi.setText("Ghi");
        btnGhi.setFont(new java.awt.Font("Segoe UI", 1, 14));
        btnGhi.setBackground(new java.awt.Color(205, 32, 45));
        btnGhi.setForeground(new java.awt.Color(255, 255, 255));
        btnGhi.setOpaque(true);
        btnGhi.setContentAreaFilled(true);
        btnGhi.setFocusPainted(false);
        btnGhi.addActionListener(this::btnGhiActionPerformed);
        scrTblBoDe = new javax.swing.JScrollPane();
        tblBoDe = new javax.swing.JTable();
        tblBoDe.setFont(new java.awt.Font("Segoe UI", 0, 13));
        tblBoDe.setRowHeight(28);
        tblBoDe.setAutoCreateRowSorter(true);
        tblBoDe.setFillsViewportHeight(true);
        tblBoDe.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {}, new String[] {"Câu số", "Môn học", "Trình độ", "Nội dung", "A", "B", "C", "D", "Đáp án", "Mã GV"}) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        });
        tblBoDe.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBoDeMouseClicked(evt);
            }
        });
        scrTblBoDe.setViewportView(tblBoDe);
        lblTrinhDo = new javax.swing.JLabel();
        lblTrinhDo.setText("A: Đại học chuyên ngành · B: Đại học không chuyên ngành · C: Cao đẳng");
        lblTrinhDo.setFont(new java.awt.Font("Segoe UI", 0, 12));
        lblTrinhDo.setForeground(new java.awt.Color(107, 114, 128));
        javax.swing.GroupLayout pnlNoiDungLayout = new javax.swing.GroupLayout(pnlNoiDung);
        pnlNoiDung.setLayout(pnlNoiDungLayout);
        pnlNoiDungLayout.setHorizontalGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(lblTieuDe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(lblMoTa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(pnlCboMonHoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(pnlCboTrinhDo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(pnlTxtMaGV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(pnlTxtCauHoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(pnlCboDapAn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(pnlTxtTuKhoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(pnlTxtNoiDung, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(pnlTxtPhuongAnA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(pnlTxtPhuongAnB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(pnlTxtPhuongAnC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(pnlTxtPhuongAnD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(btnHieuChinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(btnPhucHoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(btnTim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(btnGhi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(scrTblBoDe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(lblTrinhDo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        pnlNoiDungLayout.setVerticalGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTieuDe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMoTa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlCboMonHoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlCboTrinhDo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlTxtMaGV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlTxtCauHoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlCboDapAn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlTxtTuKhoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlTxtNoiDung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlTxtPhuongAnA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlTxtPhuongAnB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlTxtPhuongAnC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlTxtPhuongAnD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHieuChinh, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPhucHoi, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTim, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGhi, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrTblBoDe, javax.swing.GroupLayout.PREFERRED_SIZE, 140, Short.MAX_VALUE))
                .addGap(8, 8, 8)
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTrinhDo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))));
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(pnlNoiDung, javax.swing.GroupLayout.DEFAULT_SIZE, 1040, Short.MAX_VALUE)
                .addGap(16, 16, 16)));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlNoiDung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)));
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PTIT | BỘ ĐỀ TRẮC NGHIỆM");
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/ptit-logo.png")).getImage());
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        dot.them();
        capNhatCheDo();
        cboMonHoc.requestFocusInWindow();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        Object[] dong = dongDaChon(tblBoDe);
        if (dong == null) {
            JOptionPane.showMessageDialog(pnlNoiDung, "Vui lòng chọn câu hỏi để xóa.");
            return;
        }
        int xacNhan = JOptionPane.showConfirmDialog(pnlNoiDung, "Xóa " + "câu hỏi " + dong[0] + "?",
                "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
        if (xacNhan != JOptionPane.YES_OPTION) return;
        try {
            QuanLyDAO.xoaCauHoi(nguoiDung, ((Number) dong[0]).intValue());
            taiBoDe();
        } catch (SQLException e) {
            baoLoi(e);
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnHieuChinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHieuChinhActionPerformed
        dot.hieuChinh();
        capNhatCheDo();
        txtNoiDung.requestFocusInWindow();
    }//GEN-LAST:event_btnHieuChinhActionPerformed

    private void btnPhucHoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPhucHoiActionPerformed
        dot.phucHoi(() -> QuanLyDAO.phucHoiBoDe(nguoiDung), this::taiBoDe);
        capNhatCheDo();
    }//GEN-LAST:event_btnPhucHoiActionPerformed

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        if (dot.dangNhap()) {
            dot.huy();
            capNhatCheDo();
            return;
        }
        String maNhap = JOptionPane.showInputDialog(pnlNoiDung, "Mã môn học (để trống để tìm tất cả môn)");
        if (maNhap == null) return;
        String maMH = maNhap.strip();
        String tuKhoa = txtTuKhoa.getText().strip();
        ArrayList<Object[]> ketQua = new ArrayList<>();
        for (Object[] dong : danhSachCauHoi) {
            if ((maMH.isEmpty() || maMH.equalsIgnoreCase(Objects.toString(dong[1], "")))
                    && (khop(dong[0], tuKhoa) || khop(dong[3], tuKhoa))) {
                ketQua.add(dong);
            }
        }
        napBang(tblBoDe, ketQua);
        dienCauHoi(null);
        capNhatCheDo();
    }//GEN-LAST:event_btnTimActionPerformed

    private void btnGhiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGhiActionPerformed
        dot.ghi((thaoTac, truoc, sau) -> QuanLyDAO.ghiBoDe(nguoiDung, thaoTac, sau), this::taiBoDe);
        capNhatCheDo();
    }//GEN-LAST:event_btnGhiActionPerformed

    private void tblBoDeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBoDeMouseClicked
        if (!dot.dangNhap() && tblBoDe.rowAtPoint(evt.getPoint()) >= 0) {
            dienCauHoi(dongDaChon(tblBoDe));
        }
    }//GEN-LAST:event_tblBoDeMouseClicked

    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NhapBoDe.class.getName()).log(java.util.logging.Level.WARNING, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> new NhapBoDe().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel pnlNoiDung;
    private javax.swing.JLabel lblTieuDe;
    private javax.swing.JLabel lblMoTa;
    private javax.swing.JPanel pnlCboMonHoc;
    private javax.swing.JLabel lblCboMonHoc;
    private javax.swing.JComboBox cboMonHoc;
    private javax.swing.JPanel pnlCboTrinhDo;
    private javax.swing.JLabel lblCboTrinhDo;
    private javax.swing.JComboBox cboTrinhDo;
    private javax.swing.JPanel pnlTxtMaGV;
    private javax.swing.JLabel lblTxtMaGV;
    private javax.swing.JTextField txtMaGV;
    private javax.swing.JPanel pnlTxtCauHoi;
    private javax.swing.JLabel lblTxtCauHoi;
    private javax.swing.JTextField txtCauHoi;
    private javax.swing.JPanel pnlCboDapAn;
    private javax.swing.JLabel lblCboDapAn;
    private javax.swing.JComboBox cboDapAn;
    private javax.swing.JPanel pnlTxtTuKhoa;
    private javax.swing.JLabel lblTxtTuKhoa;
    private javax.swing.JTextField txtTuKhoa;
    private javax.swing.JPanel pnlTxtNoiDung;
    private javax.swing.JLabel lblTxtNoiDung;
    private javax.swing.JScrollPane scrTxtNoiDung;
    private javax.swing.JTextArea txtNoiDung;
    private javax.swing.JPanel pnlTxtPhuongAnA;
    private javax.swing.JLabel lblTxtPhuongAnA;
    private javax.swing.JTextField txtPhuongAnA;
    private javax.swing.JPanel pnlTxtPhuongAnB;
    private javax.swing.JLabel lblTxtPhuongAnB;
    private javax.swing.JTextField txtPhuongAnB;
    private javax.swing.JPanel pnlTxtPhuongAnC;
    private javax.swing.JLabel lblTxtPhuongAnC;
    private javax.swing.JTextField txtPhuongAnC;
    private javax.swing.JPanel pnlTxtPhuongAnD;
    private javax.swing.JLabel lblTxtPhuongAnD;
    private javax.swing.JTextField txtPhuongAnD;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnHieuChinh;
    private javax.swing.JButton btnPhucHoi;
    private javax.swing.JButton btnTim;
    private javax.swing.JButton btnGhi;
    private javax.swing.JScrollPane scrTblBoDe;
    private javax.swing.JTable tblBoDe;
    private javax.swing.JLabel lblTrinhDo;
    // End of variables declaration//GEN-END:variables
}
