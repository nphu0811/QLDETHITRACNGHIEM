package quanly;

import java.util.List;
import java.util.Locale;
import java.util.Objects;
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
 * Giao diện NhapGiaoVien. Nghiệp vụ sẽ được bổ sung trong các event handler.
 * Chỉnh bố cục bằng tab Design của NetBeans và giữ file .form đi kèm.
 */
public class NhapGiaoVien extends javax.swing.JFrame {
    private final sessionGiaoVien nguoiDung;
    private final DotChinhSua dot;

    public NhapGiaoVien() {
        this(null);
    }

    public NhapGiaoVien(sessionGiaoVien nguoiDung) {
        initComponents();
        this.nguoiDung = nguoiDung;
        getContentPane().setBackground(new java.awt.Color(245, 246, 248));
        setLocationRelativeTo(null);
        tblGiaoVien.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        dot = new DotChinhSua(tblGiaoVien, new DotChinhSua.Nut(btnThem, btnHieuChinh, btnXoa, btnGhi, btnPhucHoi, btnTim),
                this::docGiaoVien, this::dienGiaoVien, this::baoLoi,
                List.of(txtMaGV, txtHoGV, txtTenGV, txtSoDT, txtDiaChiGV), List.of(txtMaGV), 0);
        if (nguoiDung != null && nguoiDung.coKetNoi()) taiGiaoVien();
        capNhatCheDo();
    }

    private ArrayList<Object[]> danhSachGiaoVien = new ArrayList<>();

    private void capNhatCheDo() {
        dot.capNhatGiaoDien(nguoiDung != null && nguoiDung.coKetNoi());
    }

    private void taiGiaoVien() {
        try {
            danhSachGiaoVien = QuanLyDAO.layGiaoVien(nguoiDung);
            napBang(tblGiaoVien, danhSachGiaoVien);
            dienGiaoVien(null);
            capNhatCheDo();
        } catch (SQLException e) {
            baoLoi(e);
        }
    }

    private void dienGiaoVien(Object[] dong) {
        txtMaGV.setText(DotChinhSua.chuoi(dong, 0));
        txtHoGV.setText(DotChinhSua.chuoi(dong, 1));
        txtTenGV.setText(DotChinhSua.chuoi(dong, 2));
        txtSoDT.setText(DotChinhSua.chuoi(dong, 3));
        txtDiaChiGV.setText(DotChinhSua.chuoi(dong, 4));
    }

    private Object[] docGiaoVien() {
        String ma = nhap(txtMaGV, "Mã giáo viên", 8, true);
        if (dot.dangThem() && coMa(danhSachGiaoVien, ma)) throw new IllegalArgumentException("Mã giáo viên đã có.");
        String ho = nhap(txtHoGV, "Họ", 50, true);
        String ten = nhap(txtTenGV, "Tên", 10, true);
        String soDT = nhap(txtSoDT, "Số điện thoại", 15, false);
        String diaChi = nhap(txtDiaChiGV, "Địa chỉ", 50, false);
        return new Object[]{ma, ho, ten, soDT, diaChi};
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

    private boolean coMa(List<Object[]> danhSach, String ma) {
        for (Object[] dong : danhSach) {
            if (ma.equalsIgnoreCase(Objects.toString(dong[0], ""))) return true;
        }
        return false;
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
        lblTieuDe.setText("QUẢN LÝ GIÁO VIÊN");
        lblTieuDe.setFont(new java.awt.Font("Segoe UI", 1, 24));
        lblTieuDe.setForeground(new java.awt.Color(205, 32, 45));
        lblMoTa = new javax.swing.JLabel();
        lblMoTa.setText("Thông tin giáo viên tham gia biên soạn và tổ chức thi");
        lblMoTa.setFont(new java.awt.Font("Segoe UI", 0, 13));
        lblMoTa.setForeground(new java.awt.Color(107, 114, 128));
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
        txtMaGV.setToolTipText("MAGV · tối đa 8 ký tự");
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
        pnlTxtHoGV = new javax.swing.JPanel();
        pnlTxtHoGV.setBackground(new java.awt.Color(255, 255, 255));
        lblTxtHoGV = new javax.swing.JLabel();
        lblTxtHoGV.setText("Họ");
        lblTxtHoGV.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblTxtHoGV.setForeground(new java.awt.Color(36, 43, 54));
        txtHoGV = new javax.swing.JTextField();
        txtHoGV.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtHoGV.setForeground(new java.awt.Color(36, 43, 54));
        txtHoGV.setBackground(new java.awt.Color(255, 255, 255));
        txtHoGV.setEditable(true);
        javax.swing.GroupLayout pnlTxtHoGVLayout = new javax.swing.GroupLayout(pnlTxtHoGV);
        pnlTxtHoGV.setLayout(pnlTxtHoGVLayout);
        pnlTxtHoGVLayout.setHorizontalGroup(pnlTxtHoGVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtHoGVLayout.createSequentialGroup()
                .addComponent(lblTxtHoGV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlTxtHoGVLayout.createSequentialGroup()
                .addComponent(txtHoGV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        pnlTxtHoGVLayout.setVerticalGroup(pnlTxtHoGVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtHoGVLayout.createSequentialGroup()
                .addGroup(pnlTxtHoGVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTxtHoGV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlTxtHoGVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtHoGV, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))));
        pnlTxtTenGV = new javax.swing.JPanel();
        pnlTxtTenGV.setBackground(new java.awt.Color(255, 255, 255));
        lblTxtTenGV = new javax.swing.JLabel();
        lblTxtTenGV.setText("Tên");
        lblTxtTenGV.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblTxtTenGV.setForeground(new java.awt.Color(36, 43, 54));
        txtTenGV = new javax.swing.JTextField();
        txtTenGV.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtTenGV.setForeground(new java.awt.Color(36, 43, 54));
        txtTenGV.setBackground(new java.awt.Color(255, 255, 255));
        txtTenGV.setEditable(true);
        javax.swing.GroupLayout pnlTxtTenGVLayout = new javax.swing.GroupLayout(pnlTxtTenGV);
        pnlTxtTenGV.setLayout(pnlTxtTenGVLayout);
        pnlTxtTenGVLayout.setHorizontalGroup(pnlTxtTenGVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtTenGVLayout.createSequentialGroup()
                .addComponent(lblTxtTenGV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlTxtTenGVLayout.createSequentialGroup()
                .addComponent(txtTenGV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        pnlTxtTenGVLayout.setVerticalGroup(pnlTxtTenGVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtTenGVLayout.createSequentialGroup()
                .addGroup(pnlTxtTenGVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTxtTenGV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlTxtTenGVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTenGV, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))));
        pnlTxtSoDT = new javax.swing.JPanel();
        pnlTxtSoDT.setBackground(new java.awt.Color(255, 255, 255));
        lblTxtSoDT = new javax.swing.JLabel();
        lblTxtSoDT.setText("Số điện thoại liên lạc");
        lblTxtSoDT.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblTxtSoDT.setForeground(new java.awt.Color(36, 43, 54));
        txtSoDT = new javax.swing.JTextField();
        txtSoDT.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtSoDT.setForeground(new java.awt.Color(36, 43, 54));
        txtSoDT.setBackground(new java.awt.Color(255, 255, 255));
        txtSoDT.setEditable(true);
        txtSoDT.setToolTipText("SODTLL · tối đa 12 ký tự");
        javax.swing.GroupLayout pnlTxtSoDTLayout = new javax.swing.GroupLayout(pnlTxtSoDT);
        pnlTxtSoDT.setLayout(pnlTxtSoDTLayout);
        pnlTxtSoDTLayout.setHorizontalGroup(pnlTxtSoDTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtSoDTLayout.createSequentialGroup()
                .addComponent(lblTxtSoDT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlTxtSoDTLayout.createSequentialGroup()
                .addComponent(txtSoDT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        pnlTxtSoDTLayout.setVerticalGroup(pnlTxtSoDTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtSoDTLayout.createSequentialGroup()
                .addGroup(pnlTxtSoDTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTxtSoDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlTxtSoDTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSoDT, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))));
        pnlTxtDiaChiGV = new javax.swing.JPanel();
        pnlTxtDiaChiGV.setBackground(new java.awt.Color(255, 255, 255));
        lblTxtDiaChiGV = new javax.swing.JLabel();
        lblTxtDiaChiGV.setText("Địa chỉ");
        lblTxtDiaChiGV.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblTxtDiaChiGV.setForeground(new java.awt.Color(36, 43, 54));
        txtDiaChiGV = new javax.swing.JTextField();
        txtDiaChiGV.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtDiaChiGV.setForeground(new java.awt.Color(36, 43, 54));
        txtDiaChiGV.setBackground(new java.awt.Color(255, 255, 255));
        txtDiaChiGV.setEditable(true);
        txtDiaChiGV.setToolTipText("DIACHI · tối đa 50 ký tự");
        javax.swing.GroupLayout pnlTxtDiaChiGVLayout = new javax.swing.GroupLayout(pnlTxtDiaChiGV);
        pnlTxtDiaChiGV.setLayout(pnlTxtDiaChiGVLayout);
        pnlTxtDiaChiGVLayout.setHorizontalGroup(pnlTxtDiaChiGVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtDiaChiGVLayout.createSequentialGroup()
                .addComponent(lblTxtDiaChiGV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlTxtDiaChiGVLayout.createSequentialGroup()
                .addComponent(txtDiaChiGV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        pnlTxtDiaChiGVLayout.setVerticalGroup(pnlTxtDiaChiGVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtDiaChiGVLayout.createSequentialGroup()
                .addGroup(pnlTxtDiaChiGVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTxtDiaChiGV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlTxtDiaChiGVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDiaChiGV, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))));
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
        scrTblGiaoVien = new javax.swing.JScrollPane();
        tblGiaoVien = new javax.swing.JTable();
        tblGiaoVien.setFont(new java.awt.Font("Segoe UI", 0, 13));
        tblGiaoVien.setRowHeight(28);
        tblGiaoVien.setAutoCreateRowSorter(true);
        tblGiaoVien.setFillsViewportHeight(true);
        tblGiaoVien.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {}, new String[] {"Mã GV", "Họ", "Tên", "Số điện thoại", "Địa chỉ"}) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        });
        tblGiaoVien.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblGiaoVienMouseClicked(evt);
            }
        });
        scrTblGiaoVien.setViewportView(tblGiaoVien);
        javax.swing.GroupLayout pnlNoiDungLayout = new javax.swing.GroupLayout(pnlNoiDung);
        pnlNoiDung.setLayout(pnlNoiDungLayout);
        pnlNoiDungLayout.setHorizontalGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(lblTieuDe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(lblMoTa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(pnlTxtMaGV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(pnlTxtHoGV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(pnlTxtTenGV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(pnlTxtSoDT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(pnlTxtDiaChiGV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(btnHieuChinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8)
                .addComponent(btnPhucHoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(btnTim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(btnGhi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(scrTblGiaoVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        pnlNoiDungLayout.setVerticalGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTieuDe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMoTa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlTxtMaGV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlTxtHoGV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlTxtTenGV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlTxtSoDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlTxtDiaChiGV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addComponent(scrTblGiaoVien, javax.swing.GroupLayout.PREFERRED_SIZE, 245, Short.MAX_VALUE))));
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
        setTitle("PTIT | QUẢN LÝ GIÁO VIÊN");
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/ptit-logo.png")).getImage());
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        dot.them();
        capNhatCheDo();
        txtMaGV.requestFocusInWindow();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        Object[] dong = dongDaChon(tblGiaoVien);
        if (dong == null) {
            JOptionPane.showMessageDialog(pnlNoiDung, "Vui lòng chọn giáo viên để xóa.");
            return;
        }
        String ma = Objects.toString(dong[0], "");
        int xacNhan = JOptionPane.showConfirmDialog(pnlNoiDung, "Xóa " + "giáo viên " + ma + "?",
                "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
        if (xacNhan != JOptionPane.YES_OPTION) return;
        try {
            QuanLyDAO.xoaGiaoVien(nguoiDung, ma);
            taiGiaoVien();
        } catch (SQLException e) {
            if (e.getErrorCode() == 547) {
                JOptionPane.showMessageDialog(pnlNoiDung,
                        "Không thể xóa giáo viên vì vẫn còn câu hỏi trong bộ đề hoặc ca thi đã đăng ký.");
            } else {
                baoLoi(e);
            }
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnHieuChinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHieuChinhActionPerformed
        dot.hieuChinh();
        capNhatCheDo();
        txtHoGV.requestFocusInWindow();
    }//GEN-LAST:event_btnHieuChinhActionPerformed

    private void btnPhucHoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPhucHoiActionPerformed
        dot.phucHoi(() -> QuanLyDAO.phucHoiGiaoVien(nguoiDung), this::taiGiaoVien);
        capNhatCheDo();
    }//GEN-LAST:event_btnPhucHoiActionPerformed

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        if (dot.dangNhap()) {
            dot.huy();
            capNhatCheDo();
            return;
        }
        String[] tuKhoa = DotChinhSua.tuKhoa(pnlNoiDung, "Mã giáo viên", "Họ", "Tên");
        if (tuKhoa == null) return;
        ArrayList<Object[]> ketQua = new ArrayList<>();
        for (Object[] dong : danhSachGiaoVien) {
            if (khop(dong[0], tuKhoa[0]) && khop(dong[1], tuKhoa[1]) && khop(dong[2], tuKhoa[2])) {
                ketQua.add(dong);
            }
        }
        napBang(tblGiaoVien, ketQua);
        dienGiaoVien(null);
        capNhatCheDo();
    }//GEN-LAST:event_btnTimActionPerformed

    private void btnGhiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGhiActionPerformed
        dot.ghi((thaoTac, truoc, sau) -> QuanLyDAO.ghiGiaoVien(nguoiDung, thaoTac, sau), this::taiGiaoVien);
        capNhatCheDo();
    }//GEN-LAST:event_btnGhiActionPerformed

    private void tblGiaoVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGiaoVienMouseClicked
        if (!dot.dangNhap() && tblGiaoVien.rowAtPoint(evt.getPoint()) >= 0) {
            dienGiaoVien(dongDaChon(tblGiaoVien));
        }
    }//GEN-LAST:event_tblGiaoVienMouseClicked

    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NhapGiaoVien.class.getName()).log(java.util.logging.Level.WARNING, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> new NhapGiaoVien().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel pnlNoiDung;
    private javax.swing.JLabel lblTieuDe;
    private javax.swing.JLabel lblMoTa;
    private javax.swing.JPanel pnlTxtMaGV;
    private javax.swing.JLabel lblTxtMaGV;
    private javax.swing.JTextField txtMaGV;
    private javax.swing.JPanel pnlTxtHoGV;
    private javax.swing.JLabel lblTxtHoGV;
    private javax.swing.JTextField txtHoGV;
    private javax.swing.JPanel pnlTxtTenGV;
    private javax.swing.JLabel lblTxtTenGV;
    private javax.swing.JTextField txtTenGV;
    private javax.swing.JPanel pnlTxtSoDT;
    private javax.swing.JLabel lblTxtSoDT;
    private javax.swing.JTextField txtSoDT;
    private javax.swing.JPanel pnlTxtDiaChiGV;
    private javax.swing.JLabel lblTxtDiaChiGV;
    private javax.swing.JTextField txtDiaChiGV;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnHieuChinh;
    private javax.swing.JButton btnPhucHoi;
    private javax.swing.JButton btnTim;
    private javax.swing.JButton btnGhi;
    private javax.swing.JScrollPane scrTblGiaoVien;
    private javax.swing.JTable tblGiaoVien;
    // End of variables declaration//GEN-END:variables
}
