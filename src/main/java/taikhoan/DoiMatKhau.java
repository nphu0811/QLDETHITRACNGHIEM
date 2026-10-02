package taikhoan;

import session.SessionDangNhap;

/**
 * Giao diện DoiMatKhau. Nghiệp vụ sẽ được bổ sung trong các event handler.
 * Chỉnh bố cục bằng tab Design của NetBeans và giữ file .form đi kèm.
 */
public class DoiMatKhau extends javax.swing.JFrame {
    private final SessionDangNhap nguoiDung;

    public DoiMatKhau() {
        this(null);
    }

    public DoiMatKhau(SessionDangNhap nguoiDung) {
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
        lblTieuDe.setText("ĐỔI MẬT KHẨU");
        lblTieuDe.setFont(new java.awt.Font("Segoe UI", 1, 24));
        lblTieuDe.setForeground(new java.awt.Color(205, 32, 45));
        lblMoTa = new javax.swing.JLabel();
        lblMoTa.setText("Giáo viên đã được tạo tài khoản");
        lblMoTa.setFont(new java.awt.Font("Segoe UI", 0, 13));
        lblMoTa.setForeground(new java.awt.Color(107, 114, 128));
        pnlCboGiaoVien = new javax.swing.JPanel();
        pnlCboGiaoVien.setBackground(new java.awt.Color(255, 255, 255));
        lblCboGiaoVien = new javax.swing.JLabel();
        lblCboGiaoVien.setText("Giáo viên / tên đăng nhập");
        lblCboGiaoVien.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblCboGiaoVien.setForeground(new java.awt.Color(36, 43, 54));
        cboGiaoVien = new javax.swing.JComboBox();
        cboGiaoVien.setFont(new java.awt.Font("Segoe UI", 0, 14));
        cboGiaoVien.setForeground(new java.awt.Color(36, 43, 54));
        cboGiaoVien.setBackground(new java.awt.Color(255, 255, 255));
        cboGiaoVien.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"Chọn tài khoản giáo viên"}));
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
        pnlTxtMatKhauCu = new javax.swing.JPanel();
        pnlTxtMatKhauCu.setBackground(new java.awt.Color(255, 255, 255));
        lblTxtMatKhauCu = new javax.swing.JLabel();
        lblTxtMatKhauCu.setText("Mật khẩu hiện tại");
        lblTxtMatKhauCu.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblTxtMatKhauCu.setForeground(new java.awt.Color(36, 43, 54));
        txtMatKhauCu = new javax.swing.JPasswordField();
        txtMatKhauCu.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtMatKhauCu.setForeground(new java.awt.Color(36, 43, 54));
        txtMatKhauCu.setBackground(new java.awt.Color(255, 255, 255));
        txtMatKhauCu.setEditable(true);
        javax.swing.GroupLayout pnlTxtMatKhauCuLayout = new javax.swing.GroupLayout(pnlTxtMatKhauCu);
        pnlTxtMatKhauCu.setLayout(pnlTxtMatKhauCuLayout);
        pnlTxtMatKhauCuLayout.setHorizontalGroup(pnlTxtMatKhauCuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtMatKhauCuLayout.createSequentialGroup()
                .addComponent(lblTxtMatKhauCu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlTxtMatKhauCuLayout.createSequentialGroup()
                .addComponent(txtMatKhauCu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        pnlTxtMatKhauCuLayout.setVerticalGroup(pnlTxtMatKhauCuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtMatKhauCuLayout.createSequentialGroup()
                .addGroup(pnlTxtMatKhauCuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTxtMatKhauCu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlTxtMatKhauCuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMatKhauCu, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))));
        pnlTxtMatKhauMoi = new javax.swing.JPanel();
        pnlTxtMatKhauMoi.setBackground(new java.awt.Color(255, 255, 255));
        lblTxtMatKhauMoi = new javax.swing.JLabel();
        lblTxtMatKhauMoi.setText("Mật khẩu mới");
        lblTxtMatKhauMoi.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblTxtMatKhauMoi.setForeground(new java.awt.Color(36, 43, 54));
        txtMatKhauMoi = new javax.swing.JPasswordField();
        txtMatKhauMoi.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtMatKhauMoi.setForeground(new java.awt.Color(36, 43, 54));
        txtMatKhauMoi.setBackground(new java.awt.Color(255, 255, 255));
        txtMatKhauMoi.setEditable(true);
        javax.swing.GroupLayout pnlTxtMatKhauMoiLayout = new javax.swing.GroupLayout(pnlTxtMatKhauMoi);
        pnlTxtMatKhauMoi.setLayout(pnlTxtMatKhauMoiLayout);
        pnlTxtMatKhauMoiLayout.setHorizontalGroup(pnlTxtMatKhauMoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtMatKhauMoiLayout.createSequentialGroup()
                .addComponent(lblTxtMatKhauMoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlTxtMatKhauMoiLayout.createSequentialGroup()
                .addComponent(txtMatKhauMoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        pnlTxtMatKhauMoiLayout.setVerticalGroup(pnlTxtMatKhauMoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtMatKhauMoiLayout.createSequentialGroup()
                .addGroup(pnlTxtMatKhauMoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTxtMatKhauMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlTxtMatKhauMoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMatKhauMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))));
        pnlTxtXacNhanMatKhau = new javax.swing.JPanel();
        pnlTxtXacNhanMatKhau.setBackground(new java.awt.Color(255, 255, 255));
        lblTxtXacNhanMatKhau = new javax.swing.JLabel();
        lblTxtXacNhanMatKhau.setText("Nhập lại mật khẩu mới");
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
        btnDoiMatKhau = new javax.swing.JButton();
        btnDoiMatKhau.setText("Đổi mật khẩu");
        btnDoiMatKhau.setFont(new java.awt.Font("Segoe UI", 1, 14));
        btnDoiMatKhau.setBackground(new java.awt.Color(205, 32, 45));
        btnDoiMatKhau.setForeground(new java.awt.Color(255, 255, 255));
        btnDoiMatKhau.setOpaque(true);
        btnDoiMatKhau.setContentAreaFilled(true);
        btnDoiMatKhau.setFocusPainted(false);
        btnDoiMatKhau.addActionListener(this::btnDoiMatKhauActionPerformed);
        btnHuy = new javax.swing.JButton();
        btnHuy.setText("Hủy");
        btnHuy.setFont(new java.awt.Font("Segoe UI", 0, 14));
        btnHuy.setBackground(new java.awt.Color(245, 246, 248));
        btnHuy.setForeground(new java.awt.Color(36, 43, 54));
        btnHuy.setOpaque(true);
        btnHuy.setContentAreaFilled(true);
        btnHuy.setFocusPainted(false);
        btnHuy.addActionListener(this::btnHuyActionPerformed);
        javax.swing.GroupLayout pnlNoiDungLayout = new javax.swing.GroupLayout(pnlNoiDung);
        pnlNoiDung.setLayout(pnlNoiDungLayout);
        pnlNoiDungLayout.setHorizontalGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(lblTieuDe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(lblMoTa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(pnlCboGiaoVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(pnlTxtMatKhauCu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(pnlTxtMatKhauMoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(pnlTxtXacNhanMatKhau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(btnDoiMatKhau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(btnHuy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        pnlNoiDungLayout.setVerticalGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTieuDe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMoTa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlCboGiaoVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlTxtMatKhauCu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlTxtMatKhauMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlTxtXacNhanMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDoiMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))));
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(pnlNoiDung, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
                .addGap(16, 16, 16)));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlNoiDung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)));
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PTIT | ĐỔI MẬT KHẨU");
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/ptit-logo.png")).getImage());
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDoiMatKhauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoiMatKhauActionPerformed
        // TODO: Bổ sung xử lý cho nút này sau khi hoàn thành giao diện.
    }//GEN-LAST:event_btnDoiMatKhauActionPerformed

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
            java.util.logging.Logger.getLogger(DoiMatKhau.class.getName()).log(java.util.logging.Level.WARNING, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> new DoiMatKhau().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel pnlNoiDung;
    private javax.swing.JLabel lblTieuDe;
    private javax.swing.JLabel lblMoTa;
    private javax.swing.JPanel pnlCboGiaoVien;
    private javax.swing.JLabel lblCboGiaoVien;
    private javax.swing.JComboBox cboGiaoVien;
    private javax.swing.JPanel pnlTxtMatKhauCu;
    private javax.swing.JLabel lblTxtMatKhauCu;
    private javax.swing.JPasswordField txtMatKhauCu;
    private javax.swing.JPanel pnlTxtMatKhauMoi;
    private javax.swing.JLabel lblTxtMatKhauMoi;
    private javax.swing.JPasswordField txtMatKhauMoi;
    private javax.swing.JPanel pnlTxtXacNhanMatKhau;
    private javax.swing.JLabel lblTxtXacNhanMatKhau;
    private javax.swing.JPasswordField txtXacNhanMatKhau;
    private javax.swing.JButton btnDoiMatKhau;
    private javax.swing.JButton btnHuy;
    // End of variables declaration//GEN-END:variables
}
