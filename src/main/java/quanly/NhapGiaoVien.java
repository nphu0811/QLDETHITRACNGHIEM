package quanly;

import session.SessionDangNhap;

/**
 * Giao diện NhapGiaoVien. Nghiệp vụ sẽ được bổ sung trong các event handler.
 * Chỉnh bố cục bằng tab Design của NetBeans và giữ file .form đi kèm.
 */
public class NhapGiaoVien extends javax.swing.JFrame {
    private final SessionDangNhap nguoiDung;

    public NhapGiaoVien() {
        this(null);
    }

    public NhapGiaoVien(SessionDangNhap nguoiDung) {
        initComponents();
        this.nguoiDung = nguoiDung;
        getContentPane().setBackground(new java.awt.Color(245, 246, 248));
        setLocationRelativeTo(null);
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
        pnlTxtTuKhoa = new javax.swing.JPanel();
        pnlTxtTuKhoa.setBackground(new java.awt.Color(255, 255, 255));
        lblTxtTuKhoa = new javax.swing.JLabel();
        lblTxtTuKhoa.setText("Tìm giáo viên");
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
                .addComponent(pnlTxtTuKhoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(btnHieuChinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                    .addComponent(pnlTxtTuKhoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHieuChinh, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        // TODO: Bổ sung xử lý cho nút này sau khi hoàn thành giao diện.
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO: Bổ sung xử lý cho nút này sau khi hoàn thành giao diện.
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnHieuChinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHieuChinhActionPerformed
        // TODO: Bổ sung xử lý cho nút này sau khi hoàn thành giao diện.
    }//GEN-LAST:event_btnHieuChinhActionPerformed

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        // TODO: Bổ sung xử lý cho nút này sau khi hoàn thành giao diện.
    }//GEN-LAST:event_btnTimActionPerformed

    private void btnGhiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGhiActionPerformed
        // TODO: Bổ sung xử lý cho nút này sau khi hoàn thành giao diện.
    }//GEN-LAST:event_btnGhiActionPerformed

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
    private javax.swing.JPanel pnlTxtTuKhoa;
    private javax.swing.JLabel lblTxtTuKhoa;
    private javax.swing.JTextField txtTuKhoa;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnHieuChinh;
    private javax.swing.JButton btnTim;
    private javax.swing.JButton btnGhi;
    private javax.swing.JScrollPane scrTblGiaoVien;
    private javax.swing.JTable tblGiaoVien;
    // End of variables declaration//GEN-END:variables
}
