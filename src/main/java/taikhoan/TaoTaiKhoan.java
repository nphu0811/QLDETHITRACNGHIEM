package taikhoan;

import session.SessionDangNhap;

/**
 * Giao diện TaoTaiKhoan. Nghiệp vụ sẽ được bổ sung trong các event handler.
 * Chỉnh bố cục bằng tab Design của NetBeans và giữ file .form đi kèm.
 */
public class TaoTaiKhoan extends javax.swing.JFrame {
    private final SessionDangNhap nguoiDung;

    public TaoTaiKhoan() {
        this(null);
    }

    public TaoTaiKhoan(SessionDangNhap nguoiDung) {
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
        lblTieuDe.setText("TẠO TÀI KHOẢN GIÁO VIÊN");
        lblTieuDe.setFont(new java.awt.Font("Segoe UI", 1, 24));
        lblTieuDe.setForeground(new java.awt.Color(205, 32, 45));
        lblMoTa = new javax.swing.JLabel();
        lblMoTa.setText("Đăng ký tài khoản sử dụng chương trình cho giáo viên");
        lblMoTa.setFont(new java.awt.Font("Segoe UI", 0, 13));
        lblMoTa.setForeground(new java.awt.Color(107, 114, 128));
        pnlCboGiaoVien = new javax.swing.JPanel();
        pnlCboGiaoVien.setBackground(new java.awt.Color(255, 255, 255));
        lblCboGiaoVien = new javax.swing.JLabel();
        lblCboGiaoVien.setText("Giáo viên");
        lblCboGiaoVien.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblCboGiaoVien.setForeground(new java.awt.Color(36, 43, 54));
        cboGiaoVien = new javax.swing.JComboBox();
        cboGiaoVien.setFont(new java.awt.Font("Segoe UI", 0, 14));
        cboGiaoVien.setForeground(new java.awt.Color(36, 43, 54));
        cboGiaoVien.setBackground(new java.awt.Color(255, 255, 255));
        cboGiaoVien.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"Chọn giáo viên"}));
        javax.swing.GroupLayout pnlCboGiaoVienLayout = new javax.swing.GroupLayout(pnlCboGiaoVien);
        pnlCboGiaoVien.setLayout(pnlCboGiaoVienLayout);
        pnlCboGiaoVienLayout.setHorizontalGroup(pnlCboGiaoVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCboGiaoVienLayout.createSequentialGroup()
                .addComponent(lblCboGiaoVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlCboGiaoVienLayout.createSequentialGroup()
                .addComponent(cboGiaoVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        pnlCboGiaoVienLayout.setVerticalGroup(pnlCboGiaoVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCboGiaoVienLayout.createSequentialGroup()
                .addGroup(pnlCboGiaoVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCboGiaoVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlCboGiaoVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cboGiaoVien, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))));
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
        txtMaGV.setEditable(false);
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
        pnlTxtHoTen = new javax.swing.JPanel();
        pnlTxtHoTen.setBackground(new java.awt.Color(255, 255, 255));
        lblTxtHoTen = new javax.swing.JLabel();
        lblTxtHoTen.setText("Họ tên giáo viên");
        lblTxtHoTen.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblTxtHoTen.setForeground(new java.awt.Color(36, 43, 54));
        txtHoTen = new javax.swing.JTextField();
        txtHoTen.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtHoTen.setForeground(new java.awt.Color(36, 43, 54));
        txtHoTen.setBackground(new java.awt.Color(255, 255, 255));
        txtHoTen.setEditable(false);
        javax.swing.GroupLayout pnlTxtHoTenLayout = new javax.swing.GroupLayout(pnlTxtHoTen);
        pnlTxtHoTen.setLayout(pnlTxtHoTenLayout);
        pnlTxtHoTenLayout.setHorizontalGroup(pnlTxtHoTenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtHoTenLayout.createSequentialGroup()
                .addComponent(lblTxtHoTen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlTxtHoTenLayout.createSequentialGroup()
                .addComponent(txtHoTen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        pnlTxtHoTenLayout.setVerticalGroup(pnlTxtHoTenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtHoTenLayout.createSequentialGroup()
                .addGroup(pnlTxtHoTenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTxtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlTxtHoTenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))));
        pnlTxtLogin = new javax.swing.JPanel();
        pnlTxtLogin.setBackground(new java.awt.Color(255, 255, 255));
        lblTxtLogin = new javax.swing.JLabel();
        lblTxtLogin.setText("Tên đăng nhập");
        lblTxtLogin.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblTxtLogin.setForeground(new java.awt.Color(36, 43, 54));
        txtLogin = new javax.swing.JTextField();
        txtLogin.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtLogin.setForeground(new java.awt.Color(36, 43, 54));
        txtLogin.setBackground(new java.awt.Color(255, 255, 255));
        txtLogin.setEditable(true);
        javax.swing.GroupLayout pnlTxtLoginLayout = new javax.swing.GroupLayout(pnlTxtLogin);
        pnlTxtLogin.setLayout(pnlTxtLoginLayout);
        pnlTxtLoginLayout.setHorizontalGroup(pnlTxtLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtLoginLayout.createSequentialGroup()
                .addComponent(lblTxtLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlTxtLoginLayout.createSequentialGroup()
                .addComponent(txtLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        pnlTxtLoginLayout.setVerticalGroup(pnlTxtLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtLoginLayout.createSequentialGroup()
                .addGroup(pnlTxtLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTxtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlTxtLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))));
        pnlTxtMatKhau = new javax.swing.JPanel();
        pnlTxtMatKhau.setBackground(new java.awt.Color(255, 255, 255));
        lblTxtMatKhau = new javax.swing.JLabel();
        lblTxtMatKhau.setText("Mật khẩu");
        lblTxtMatKhau.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblTxtMatKhau.setForeground(new java.awt.Color(36, 43, 54));
        txtMatKhau = new javax.swing.JPasswordField();
        txtMatKhau.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtMatKhau.setForeground(new java.awt.Color(36, 43, 54));
        txtMatKhau.setBackground(new java.awt.Color(255, 255, 255));
        txtMatKhau.setEditable(true);
        javax.swing.GroupLayout pnlTxtMatKhauLayout = new javax.swing.GroupLayout(pnlTxtMatKhau);
        pnlTxtMatKhau.setLayout(pnlTxtMatKhauLayout);
        pnlTxtMatKhauLayout.setHorizontalGroup(pnlTxtMatKhauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtMatKhauLayout.createSequentialGroup()
                .addComponent(lblTxtMatKhau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlTxtMatKhauLayout.createSequentialGroup()
                .addComponent(txtMatKhau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        pnlTxtMatKhauLayout.setVerticalGroup(pnlTxtMatKhauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtMatKhauLayout.createSequentialGroup()
                .addGroup(pnlTxtMatKhauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTxtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlTxtMatKhauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))));
        pnlTxtXacNhanMatKhau = new javax.swing.JPanel();
        pnlTxtXacNhanMatKhau.setBackground(new java.awt.Color(255, 255, 255));
        lblTxtXacNhanMatKhau = new javax.swing.JLabel();
        lblTxtXacNhanMatKhau.setText("Nhập lại mật khẩu");
        lblTxtXacNhanMatKhau.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblTxtXacNhanMatKhau.setForeground(new java.awt.Color(36, 43, 54));
        txtXacNhanMatKhau = new javax.swing.JPasswordField();
        txtXacNhanMatKhau.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtXacNhanMatKhau.setForeground(new java.awt.Color(36, 43, 54));
        txtXacNhanMatKhau.setBackground(new java.awt.Color(255, 255, 255));
        txtXacNhanMatKhau.setEditable(true);
        javax.swing.GroupLayout pnlTxtXacNhanMatKhauLayout = new javax.swing.GroupLayout(pnlTxtXacNhanMatKhau);
        pnlTxtXacNhanMatKhau.setLayout(pnlTxtXacNhanMatKhauLayout);
        pnlTxtXacNhanMatKhauLayout.setHorizontalGroup(pnlTxtXacNhanMatKhauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtXacNhanMatKhauLayout.createSequentialGroup()
                .addComponent(lblTxtXacNhanMatKhau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlTxtXacNhanMatKhauLayout.createSequentialGroup()
                .addComponent(txtXacNhanMatKhau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        pnlTxtXacNhanMatKhauLayout.setVerticalGroup(pnlTxtXacNhanMatKhauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtXacNhanMatKhauLayout.createSequentialGroup()
                .addGroup(pnlTxtXacNhanMatKhauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTxtXacNhanMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlTxtXacNhanMatKhauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtXacNhanMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))));
        btnTaoTaiKhoan = new javax.swing.JButton();
        btnTaoTaiKhoan.setText("Tạo tài khoản");
        btnTaoTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 14));
        btnTaoTaiKhoan.setBackground(new java.awt.Color(205, 32, 45));
        btnTaoTaiKhoan.setForeground(new java.awt.Color(255, 255, 255));
        btnTaoTaiKhoan.setOpaque(true);
        btnTaoTaiKhoan.setContentAreaFilled(true);
        btnTaoTaiKhoan.setFocusPainted(false);
        btnTaoTaiKhoan.addActionListener(this::btnTaoTaiKhoanActionPerformed);
        btnLamMoi = new javax.swing.JButton();
        btnLamMoi.setText("Làm mới");
        btnLamMoi.setFont(new java.awt.Font("Segoe UI", 0, 14));
        btnLamMoi.setBackground(new java.awt.Color(245, 246, 248));
        btnLamMoi.setForeground(new java.awt.Color(36, 43, 54));
        btnLamMoi.setOpaque(true);
        btnLamMoi.setContentAreaFilled(true);
        btnLamMoi.setFocusPainted(false);
        btnLamMoi.addActionListener(this::btnLamMoiActionPerformed);
        btnHuy = new javax.swing.JButton();
        btnHuy.setText("Hủy");
        btnHuy.setFont(new java.awt.Font("Segoe UI", 0, 14));
        btnHuy.setBackground(new java.awt.Color(245, 246, 248));
        btnHuy.setForeground(new java.awt.Color(36, 43, 54));
        btnHuy.setOpaque(true);
        btnHuy.setContentAreaFilled(true);
        btnHuy.setFocusPainted(false);
        btnHuy.addActionListener(this::btnHuyActionPerformed);
        scrTblTaiKhoan = new javax.swing.JScrollPane();
        tblTaiKhoan = new javax.swing.JTable();
        tblTaiKhoan.setFont(new java.awt.Font("Segoe UI", 0, 13));
        tblTaiKhoan.setRowHeight(28);
        tblTaiKhoan.setAutoCreateRowSorter(true);
        tblTaiKhoan.setFillsViewportHeight(true);
        tblTaiKhoan.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {}, new String[] {"Mã giáo viên", "Họ tên", "Tên đăng nhập"}) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        });
        scrTblTaiKhoan.setViewportView(tblTaiKhoan);
        javax.swing.GroupLayout pnlNoiDungLayout = new javax.swing.GroupLayout(pnlNoiDung);
        pnlNoiDung.setLayout(pnlNoiDungLayout);
        pnlNoiDungLayout.setHorizontalGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(lblTieuDe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(lblMoTa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(pnlCboGiaoVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(pnlTxtMaGV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(pnlTxtHoTen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(pnlTxtLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(pnlTxtMatKhau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(pnlTxtXacNhanMatKhau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(btnTaoTaiKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(btnLamMoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(btnHuy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(scrTblTaiKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        pnlNoiDungLayout.setVerticalGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTieuDe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMoTa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlCboGiaoVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlTxtMaGV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlTxtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlTxtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlTxtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlTxtXacNhanMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTaoTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrTblTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 200, Short.MAX_VALUE))));
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(pnlNoiDung, javax.swing.GroupLayout.DEFAULT_SIZE, 850, Short.MAX_VALUE)
                .addGap(16, 16, 16)));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlNoiDung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)));
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PTIT | TẠO TÀI KHOẢN GIÁO VIÊN");
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/ptit-logo.png")).getImage());
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTaoTaiKhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoTaiKhoanActionPerformed
        // TODO: Bổ sung xử lý cho nút này sau khi hoàn thành giao diện.
    }//GEN-LAST:event_btnTaoTaiKhoanActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        // TODO: Bổ sung xử lý cho nút này sau khi hoàn thành giao diện.
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        trangchu.DieuHuong.moForm(this, new trangchu.TrangChu(nguoiDung), nguoiDung);
    }//GEN-LAST:event_btnHuyActionPerformed

    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TaoTaiKhoan.class.getName()).log(java.util.logging.Level.WARNING, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> new TaoTaiKhoan().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel pnlNoiDung;
    private javax.swing.JLabel lblTieuDe;
    private javax.swing.JLabel lblMoTa;
    private javax.swing.JPanel pnlCboGiaoVien;
    private javax.swing.JLabel lblCboGiaoVien;
    private javax.swing.JComboBox cboGiaoVien;
    private javax.swing.JPanel pnlTxtMaGV;
    private javax.swing.JLabel lblTxtMaGV;
    private javax.swing.JTextField txtMaGV;
    private javax.swing.JPanel pnlTxtHoTen;
    private javax.swing.JLabel lblTxtHoTen;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JPanel pnlTxtLogin;
    private javax.swing.JLabel lblTxtLogin;
    private javax.swing.JTextField txtLogin;
    private javax.swing.JPanel pnlTxtMatKhau;
    private javax.swing.JLabel lblTxtMatKhau;
    private javax.swing.JPasswordField txtMatKhau;
    private javax.swing.JPanel pnlTxtXacNhanMatKhau;
    private javax.swing.JLabel lblTxtXacNhanMatKhau;
    private javax.swing.JPasswordField txtXacNhanMatKhau;
    private javax.swing.JButton btnTaoTaiKhoan;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnHuy;
    private javax.swing.JScrollPane scrTblTaiKhoan;
    private javax.swing.JTable tblTaiKhoan;
    // End of variables declaration//GEN-END:variables
}
