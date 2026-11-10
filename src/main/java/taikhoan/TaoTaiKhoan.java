package taikhoan;

import java.util.List;
import java.util.Objects;
import javax.swing.DefaultComboBoxModel;
import connectionSQLServer.QuanLyDAO;
import java.sql.SQLException;
import java.util.Arrays;
import javax.swing.JOptionPane;

import session.sessionGiaoVien;

/**
 * Giao diện TaoTaiKhoan. Nghiệp vụ sẽ được bổ sung trong các event handler.
 * Chỉnh bố cục bằng tab Design của NetBeans và giữ file .form đi kèm.
 */
public class TaoTaiKhoan extends javax.swing.JFrame {
    private final sessionGiaoVien nguoiDung;

    public TaoTaiKhoan() {
        this(null);
    }

    public TaoTaiKhoan(sessionGiaoVien nguoiDung) {
        initComponents();
        this.nguoiDung = nguoiDung;
        getContentPane().setBackground(new java.awt.Color(245, 246, 248));
        setLocationRelativeTo(null);
        txtLogin.setDisabledTextColor(txtLogin.getForeground());

        capNhatThongTinTaiKhoan();
        if (nguoiDung != null && nguoiDung.coKetNoi()) taiGiaoVien("");
    }

    private void taiGiaoVien(String maCanChon) {
        try {
            List<Object[]> duLieu = QuanLyDAO.layTatCaGiaoVienTaiKhoan(nguoiDung);
            DefaultComboBoxModel<LuaChon> model = new DefaultComboBoxModel<>();
            LuaChon mucCanChon = new LuaChon("", "Chọn giáo viên", null);
            model.addElement(mucCanChon);
            for (Object[] dong : duLieu) {
                String ma = Objects.toString(dong[0], "");
                String ten = Objects.toString(dong[1], "");
                LuaChon muc = new LuaChon(ma, ten.isEmpty() ? ma : ma + " - " + ten, (byte[]) dong[2]);
                model.addElement(muc);
                if (ma.equalsIgnoreCase(maCanChon)) mucCanChon = muc;
            }
            model.setSelectedItem(mucCanChon);
            cboGiaoVien.setModel(model);
            capNhatThongTinTaiKhoan();
        } catch (SQLException e) {
            baoLoi(e);
        }
    }

    private record LuaChon(String ma, String ten, byte[] sidTaiKhoan) {
        @Override
        public String toString() {
            return ten;
        }
    }

    private void capNhatThongTinTaiKhoan() {
        boolean daDangNhap = nguoiDung != null && nguoiDung.coKetNoi();
        LuaChon muc = cboGiaoVien.getSelectedItem() instanceof LuaChon luaChon ? luaChon : null;
        boolean daChon = muc != null && !muc.ma().isEmpty();
        boolean daCoTaiKhoan = daChon && muc.sidTaiKhoan() != null;
        boolean choNhap = daDangNhap && daChon && !daCoTaiKhoan;
        btnTaoTaiKhoan.setEnabled(choNhap);
        btnXoaTaiKhoan.setEnabled(daDangNhap && daCoTaiKhoan);
        txtLogin.setEnabled(choNhap);
        txtMatKhau.setEnabled(choNhap);
        txtXacNhanMatKhau.setEnabled(choNhap);
        txtLogin.setText("");
        txtMatKhau.setText("");
        txtXacNhanMatKhau.setText("");
        if (daDangNhap && daCoTaiKhoan) {
            try {
                txtLogin.setText(QuanLyDAO.layTenDangNhap(nguoiDung, muc.sidTaiKhoan()));
            } catch (SQLException e) {
                baoLoi(e);
            }
        }
    }

    private void baoLoi(SQLException e) {
        String noiDung = switch (e.getErrorCode()) {
            case 2601, 2627 -> "Mã đã tồn tại.";
            case 15434 -> "Tài khoản đang kết nối SQL Server. Hãy đăng xuất tài khoản đó rồi thử lại.";
            case 547 -> "Dữ liệu đang được sử dụng hoặc thông tin liên quan không hợp lệ.";
            default -> e.getMessage();
        };
        JOptionPane.showMessageDialog(pnlNoiDung, noiDung, "Thông báo", JOptionPane.ERROR_MESSAGE);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlNoiDung = new javax.swing.JPanel();
        lblTieuDe = new javax.swing.JLabel();
        lblMoTa = new javax.swing.JLabel();
        pnlCboGiaoVien = new javax.swing.JPanel();
        lblCboGiaoVien = new javax.swing.JLabel();
        cboGiaoVien = new javax.swing.JComboBox();
        pnlTxtLogin = new javax.swing.JPanel();
        lblTxtLogin = new javax.swing.JLabel();
        txtLogin = new javax.swing.JTextField();
        pnlTxtMatKhau = new javax.swing.JPanel();
        lblTxtMatKhau = new javax.swing.JLabel();
        txtMatKhau = new javax.swing.JPasswordField();
        pnlTxtXacNhanMatKhau = new javax.swing.JPanel();
        lblTxtXacNhanMatKhau = new javax.swing.JLabel();
        txtXacNhanMatKhau = new javax.swing.JPasswordField();
        btnTaoTaiKhoan = new javax.swing.JButton();
        btnXoaTaiKhoan = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PTIT | TẠO TÀI KHOẢN GIÁO VIÊN");
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/ptit-logo.png")).getImage());

        pnlNoiDung.setBackground(new java.awt.Color(255, 255, 255));
        pnlNoiDung.setBorder(javax.swing.BorderFactory.createEmptyBorder(24, 24, 24, 24));

        lblTieuDe.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTieuDe.setForeground(new java.awt.Color(205, 32, 45));
        lblTieuDe.setText("TẠO TÀI KHOẢN GIÁO VIÊN");

        lblMoTa.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblMoTa.setForeground(new java.awt.Color(107, 114, 128));
        lblMoTa.setText("Đăng ký tài khoản sử dụng chương trình cho giáo viên");

        pnlCboGiaoVien.setBackground(new java.awt.Color(255, 255, 255));

        lblCboGiaoVien.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblCboGiaoVien.setForeground(new java.awt.Color(36, 43, 54));
        lblCboGiaoVien.setText("Giáo viên");

        cboGiaoVien.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cboGiaoVien.setForeground(new java.awt.Color(36, 43, 54));
        cboGiaoVien.setBackground(new java.awt.Color(255, 255, 255));
        cboGiaoVien.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Chọn giáo viên" }));
        cboGiaoVien.addActionListener(this::cboGiaoVienActionPerformed);

        javax.swing.GroupLayout pnlCboGiaoVienLayout = new javax.swing.GroupLayout(pnlCboGiaoVien);
        pnlCboGiaoVien.setLayout(pnlCboGiaoVienLayout);
        pnlCboGiaoVienLayout.setHorizontalGroup(
            pnlCboGiaoVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblCboGiaoVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(cboGiaoVien, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlCboGiaoVienLayout.setVerticalGroup(
            pnlCboGiaoVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCboGiaoVienLayout.createSequentialGroup()
                .addComponent(lblCboGiaoVien)
                .addGap(8, 8, 8)
                .addComponent(cboGiaoVien, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlTxtLogin.setBackground(new java.awt.Color(255, 255, 255));

        lblTxtLogin.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblTxtLogin.setForeground(new java.awt.Color(36, 43, 54));
        lblTxtLogin.setText("Tên đăng nhập");

        txtLogin.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtLogin.setForeground(new java.awt.Color(36, 43, 54));

        javax.swing.GroupLayout pnlTxtLoginLayout = new javax.swing.GroupLayout(pnlTxtLogin);
        pnlTxtLogin.setLayout(pnlTxtLoginLayout);
        pnlTxtLoginLayout.setHorizontalGroup(
            pnlTxtLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTxtLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(txtLogin)
        );
        pnlTxtLoginLayout.setVerticalGroup(
            pnlTxtLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtLoginLayout.createSequentialGroup()
                .addComponent(lblTxtLogin)
                .addGap(8, 8, 8)
                .addComponent(txtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlTxtMatKhau.setBackground(new java.awt.Color(255, 255, 255));

        lblTxtMatKhau.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblTxtMatKhau.setForeground(new java.awt.Color(36, 43, 54));
        lblTxtMatKhau.setText("Mật khẩu");

        txtMatKhau.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMatKhau.setForeground(new java.awt.Color(36, 43, 54));

        javax.swing.GroupLayout pnlTxtMatKhauLayout = new javax.swing.GroupLayout(pnlTxtMatKhau);
        pnlTxtMatKhau.setLayout(pnlTxtMatKhauLayout);
        pnlTxtMatKhauLayout.setHorizontalGroup(
            pnlTxtMatKhauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTxtMatKhau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(txtMatKhau)
        );
        pnlTxtMatKhauLayout.setVerticalGroup(
            pnlTxtMatKhauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtMatKhauLayout.createSequentialGroup()
                .addComponent(lblTxtMatKhau)
                .addGap(8, 8, 8)
                .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlTxtXacNhanMatKhau.setBackground(new java.awt.Color(255, 255, 255));

        lblTxtXacNhanMatKhau.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblTxtXacNhanMatKhau.setForeground(new java.awt.Color(36, 43, 54));
        lblTxtXacNhanMatKhau.setText("Nhập lại mật khẩu");

        txtXacNhanMatKhau.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtXacNhanMatKhau.setForeground(new java.awt.Color(36, 43, 54));

        javax.swing.GroupLayout pnlTxtXacNhanMatKhauLayout = new javax.swing.GroupLayout(pnlTxtXacNhanMatKhau);
        pnlTxtXacNhanMatKhau.setLayout(pnlTxtXacNhanMatKhauLayout);
        pnlTxtXacNhanMatKhauLayout.setHorizontalGroup(
            pnlTxtXacNhanMatKhauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTxtXacNhanMatKhau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(txtXacNhanMatKhau)
        );
        pnlTxtXacNhanMatKhauLayout.setVerticalGroup(
            pnlTxtXacNhanMatKhauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtXacNhanMatKhauLayout.createSequentialGroup()
                .addComponent(lblTxtXacNhanMatKhau)
                .addGap(8, 8, 8)
                .addComponent(txtXacNhanMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        btnTaoTaiKhoan.setBackground(new java.awt.Color(205, 32, 45));
        btnTaoTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnTaoTaiKhoan.setForeground(new java.awt.Color(255, 255, 255));
        btnTaoTaiKhoan.setText("Tạo tài khoản");
        btnTaoTaiKhoan.setFocusPainted(false);
        btnTaoTaiKhoan.setOpaque(true);
        btnTaoTaiKhoan.addActionListener(this::btnTaoTaiKhoanActionPerformed);

        btnXoaTaiKhoan.setBackground(new java.awt.Color(245, 246, 248));
        btnXoaTaiKhoan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnXoaTaiKhoan.setForeground(new java.awt.Color(36, 43, 54));
        btnXoaTaiKhoan.setText("Xóa tài khoản");
        btnXoaTaiKhoan.setFocusPainted(false);
        btnXoaTaiKhoan.setOpaque(true);
        btnXoaTaiKhoan.addActionListener(this::btnXoaTaiKhoanActionPerformed);

        javax.swing.GroupLayout pnlNoiDungLayout = new javax.swing.GroupLayout(pnlNoiDung);
        pnlNoiDung.setLayout(pnlNoiDungLayout);
        pnlNoiDungLayout.setHorizontalGroup(
            pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTieuDe, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
            .addComponent(lblMoTa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlCboGiaoVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlTxtLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlTxtMatKhau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlTxtXacNhanMatKhau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(btnTaoTaiKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(btnXoaTaiKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlNoiDungLayout.setVerticalGroup(
            pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(lblTieuDe)
                .addGap(8, 8, 8)
                .addComponent(lblMoTa)
                .addGap(8, 8, 8)
                .addComponent(pnlCboGiaoVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(pnlTxtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(pnlTxtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(pnlTxtXacNhanMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTaoTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoaTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(pnlNoiDung, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(16, 16, 16))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(pnlNoiDung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTaoTaiKhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoTaiKhoanActionPerformed
        char[] matKhau = txtMatKhau.getPassword();
        char[] xacNhan = txtXacNhanMatKhau.getPassword();
        try {
            if (!(cboGiaoVien.getSelectedItem() instanceof LuaChon muc) || muc.ma().isEmpty()) {
                throw new IllegalArgumentException("Vui lòng chọn giáo viên.");
            }
            if (muc.sidTaiKhoan() != null) throw new IllegalArgumentException("Giáo viên đã có tài khoản.");
            String login = txtLogin.getText().strip();
            if (login.isEmpty() || login.length() > 128) {
                txtLogin.requestFocusInWindow();
                throw new IllegalArgumentException("Tên đăng nhập phải có từ 1 đến 128 ký tự.");
            }
            if (matKhau.length == 0 || matKhau.length > 128) {
                throw new IllegalArgumentException("Mật khẩu phải có từ 1 đến 128 ký tự.");
            }
            if (!Arrays.equals(matKhau, xacNhan)) throw new IllegalArgumentException("Mật khẩu xác nhận không khớp.");
            QuanLyDAO.taoTaiKhoan(nguoiDung, muc.ma(), login, new String(matKhau));
            cboGiaoVien.setSelectedIndex(0);
            taiGiaoVien(muc.ma());
            JOptionPane.showMessageDialog(pnlNoiDung, "Đã tạo tài khoản giáo viên.");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(pnlNoiDung, e.getMessage());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(pnlNoiDung,
                    "Tạo tài khoản thất bại. Kiểm tra tên đăng nhập, chính sách mật khẩu và quyền SQL. Mã lỗi: "
                    + e.getErrorCode());
        } finally {
            Arrays.fill(matKhau, '\0');
            Arrays.fill(xacNhan, '\0');
        }
    }//GEN-LAST:event_btnTaoTaiKhoanActionPerformed

    private void cboGiaoVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboGiaoVienActionPerformed
        capNhatThongTinTaiKhoan();
    }//GEN-LAST:event_cboGiaoVienActionPerformed

    private void btnXoaTaiKhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaTaiKhoanActionPerformed
        if (!(cboGiaoVien.getSelectedItem() instanceof LuaChon muc) || muc.sidTaiKhoan() == null) return;

        int xacNhan = JOptionPane.showConfirmDialog(pnlNoiDung,
                "Xóa tài khoản của " + muc + "?", "Xóa tài khoản", JOptionPane.YES_NO_OPTION);
        if (xacNhan != JOptionPane.YES_OPTION) return;

        try {
            QuanLyDAO.xoaTaiKhoan(nguoiDung, muc.ma(), muc.sidTaiKhoan());
            cboGiaoVien.setSelectedIndex(0);
            taiGiaoVien(muc.ma());
            JOptionPane.showMessageDialog(pnlNoiDung, "Đã xóa tài khoản giáo viên.");
        } catch (SQLException e) {
            baoLoi(e);
        }
    }//GEN-LAST:event_btnXoaTaiKhoanActionPerformed

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
    private javax.swing.JButton btnXoaTaiKhoan;
    private javax.swing.JButton btnTaoTaiKhoan;
    private javax.swing.JComboBox cboGiaoVien;
    private javax.swing.JLabel lblCboGiaoVien;
    private javax.swing.JLabel lblMoTa;
    private javax.swing.JLabel lblTieuDe;
    private javax.swing.JLabel lblTxtLogin;
    private javax.swing.JLabel lblTxtMatKhau;
    private javax.swing.JLabel lblTxtXacNhanMatKhau;
    private javax.swing.JPanel pnlCboGiaoVien;
    private javax.swing.JPanel pnlNoiDung;
    private javax.swing.JPanel pnlTxtLogin;
    private javax.swing.JPanel pnlTxtMatKhau;
    private javax.swing.JPanel pnlTxtXacNhanMatKhau;
    private javax.swing.JTextField txtLogin;
    private javax.swing.JPasswordField txtMatKhau;
    private javax.swing.JPasswordField txtXacNhanMatKhau;
    // End of variables declaration//GEN-END:variables
}
