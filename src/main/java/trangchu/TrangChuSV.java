package trangchu;

import connectionSQLServer.ThiDAO;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import models.CaThi;
import session.sessionSinhVien;

/** Trang chủ thi và xem điểm của sinh viên. */
public class TrangChuSV extends javax.swing.JFrame {
    private final sessionSinhVien nguoiDung;
    private CaThi baiThiDangDo;

    public TrangChuSV() {
        this(null);
    }

    public TrangChuSV(sessionSinhVien nguoiDung) {
        initComponents();
        this.nguoiDung = nguoiDung;
        getContentPane().setBackground(new java.awt.Color(245, 246, 248));
        setLocationRelativeTo(null);

        if (nguoiDung != null) {
            txtMaNguoiDung.setText(nguoiDung.getMaSV());
            txtHoTen.setText(nguoiDung.getHo() + " " + nguoiDung.getTen());
            txtMaLop.setText(nguoiDung.getMaLop());
            txtTenLop.setText(nguoiDung.getTenLop());
            txtNgaySinh.setText(nguoiDung.getNgaySinh());
            txtDiaChi.setText(nguoiDung.getDiaChi());
            kiemTraBaiThiDangDo();
        } else {
            btnChonCaThi.setEnabled(false);
            btnKetQua.setEnabled(false);
            btnBangDiem.setEnabled(false);
        }
    }

    private void kiemTraBaiThiDangDo() {
        btnChonCaThi.setEnabled(false);
        new SwingWorker<CaThi, Void>() {
            @Override
            protected CaThi doInBackground() throws SQLException {
                return ThiDAO.dangDo(nguoiDung);
            }

            @Override
            protected void done() {
                if (!isDisplayable()) return;
                try {
                    baiThiDangDo = get();
                    if (baiThiDangDo != null) {
                        btnChonCaThi.setText("Làm bài tiếp");
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(TrangChuSV.this,
                            "Không kiểm tra được bài đang làm: " + e.getMessage());
                }
                btnChonCaThi.setEnabled(true);
            }
        }.execute();
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlNoiDung = new javax.swing.JPanel();
        lblThi = new javax.swing.JLabel();
        btnChonCaThi = new javax.swing.JButton();
        btnKetQua = new javax.swing.JButton();
        btnBangDiem = new javax.swing.JButton();
        lblTaiKhoan = new javax.swing.JLabel();
        btnDangXuat = new javax.swing.JButton();
        btnThoat = new javax.swing.JButton();
        lblTieuDe = new javax.swing.JLabel();
        lblMoTa = new javax.swing.JLabel();
        pnlTxtMaNguoiDung = new javax.swing.JPanel();
        lblTxtMaNguoiDung = new javax.swing.JLabel();
        txtMaNguoiDung = new javax.swing.JTextField();
        pnlTxtHoTen = new javax.swing.JPanel();
        lblTxtHoTen = new javax.swing.JLabel();
        txtHoTen = new javax.swing.JTextField();
        pnlTxtDiaChi = new javax.swing.JPanel();
        lblTxtDiaChi = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        pnlTxtMaLop = new javax.swing.JPanel();
        lblTxtMaLop = new javax.swing.JLabel();
        txtMaLop = new javax.swing.JTextField();
        pnlTxtTenLop = new javax.swing.JPanel();
        lblTxtTenLop = new javax.swing.JLabel();
        txtTenLop = new javax.swing.JTextField();
        pnlTxtNgaySinh = new javax.swing.JPanel();
        lblTxtNgaySinh = new javax.swing.JLabel();
        txtNgaySinh = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PTIT | TRANG CHỦ");
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/ptit-logo.png")).getImage());

        pnlNoiDung.setBackground(new java.awt.Color(255, 255, 255));
        pnlNoiDung.setBorder(javax.swing.BorderFactory.createEmptyBorder(24, 24, 24, 24));

        lblTieuDe.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTieuDe.setForeground(new java.awt.Color(205, 32, 45));
        lblTieuDe.setText("TRANG CHỦ SINH VIÊN");

        lblMoTa.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblMoTa.setForeground(new java.awt.Color(107, 114, 128));
        lblMoTa.setText("Hệ thống thi trắc nghiệm · Học viện Công nghệ Bưu chính Viễn thông");

        pnlTxtMaNguoiDung.setBackground(new java.awt.Color(255, 255, 255));

        lblTxtMaNguoiDung.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblTxtMaNguoiDung.setForeground(new java.awt.Color(36, 43, 54));
        lblTxtMaNguoiDung.setText("Mã Sinh Viên");

        txtMaNguoiDung.setEditable(false);
        txtMaNguoiDung.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMaNguoiDung.setForeground(new java.awt.Color(36, 43, 54));

        javax.swing.GroupLayout pnlTxtMaNguoiDungLayout = new javax.swing.GroupLayout(pnlTxtMaNguoiDung);
        pnlTxtMaNguoiDung.setLayout(pnlTxtMaNguoiDungLayout);
        pnlTxtMaNguoiDungLayout.setHorizontalGroup(
            pnlTxtMaNguoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTxtMaNguoiDung, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(txtMaNguoiDung)
        );
        pnlTxtMaNguoiDungLayout.setVerticalGroup(
            pnlTxtMaNguoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtMaNguoiDungLayout.createSequentialGroup()
                .addComponent(lblTxtMaNguoiDung)
                .addGap(8, 8, 8)
                .addComponent(txtMaNguoiDung, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlTxtHoTen.setBackground(new java.awt.Color(255, 255, 255));

        lblTxtHoTen.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblTxtHoTen.setForeground(new java.awt.Color(36, 43, 54));
        lblTxtHoTen.setText("Họ tên");

        txtHoTen.setEditable(false);
        txtHoTen.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtHoTen.setForeground(new java.awt.Color(36, 43, 54));

        javax.swing.GroupLayout pnlTxtHoTenLayout = new javax.swing.GroupLayout(pnlTxtHoTen);
        pnlTxtHoTen.setLayout(pnlTxtHoTenLayout);
        pnlTxtHoTenLayout.setHorizontalGroup(
            pnlTxtHoTenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTxtHoTen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(txtHoTen)
        );
        pnlTxtHoTenLayout.setVerticalGroup(
            pnlTxtHoTenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtHoTenLayout.createSequentialGroup()
                .addComponent(lblTxtHoTen)
                .addGap(8, 8, 8)
                .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlTxtDiaChi.setBackground(new java.awt.Color(255, 255, 255));

        lblTxtDiaChi.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblTxtDiaChi.setForeground(new java.awt.Color(36, 43, 54));
        lblTxtDiaChi.setText("Địa Chỉ");

        txtDiaChi.setEditable(false);
        txtDiaChi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtDiaChi.setForeground(new java.awt.Color(36, 43, 54));

        javax.swing.GroupLayout pnlTxtDiaChiLayout = new javax.swing.GroupLayout(pnlTxtDiaChi);
        pnlTxtDiaChi.setLayout(pnlTxtDiaChiLayout);
        pnlTxtDiaChiLayout.setHorizontalGroup(
            pnlTxtDiaChiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTxtDiaChi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(txtDiaChi)
        );
        pnlTxtDiaChiLayout.setVerticalGroup(
            pnlTxtDiaChiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtDiaChiLayout.createSequentialGroup()
                .addComponent(lblTxtDiaChi)
                .addGap(8, 8, 8)
                .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlTxtMaLop.setBackground(new java.awt.Color(255, 255, 255));

        lblTxtMaLop.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblTxtMaLop.setForeground(new java.awt.Color(36, 43, 54));
        lblTxtMaLop.setText("Mã lớp");

        txtMaLop.setEditable(false);
        txtMaLop.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMaLop.setForeground(new java.awt.Color(36, 43, 54));

        javax.swing.GroupLayout pnlTxtMaLopLayout = new javax.swing.GroupLayout(pnlTxtMaLop);
        pnlTxtMaLop.setLayout(pnlTxtMaLopLayout);
        pnlTxtMaLopLayout.setHorizontalGroup(
            pnlTxtMaLopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTxtMaLop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(txtMaLop)
        );
        pnlTxtMaLopLayout.setVerticalGroup(
            pnlTxtMaLopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtMaLopLayout.createSequentialGroup()
                .addComponent(lblTxtMaLop)
                .addGap(8, 8, 8)
                .addComponent(txtMaLop, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlTxtTenLop.setBackground(new java.awt.Color(255, 255, 255));

        lblTxtTenLop.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblTxtTenLop.setForeground(new java.awt.Color(36, 43, 54));
        lblTxtTenLop.setText("Tên lớp");

        txtTenLop.setEditable(false);
        txtTenLop.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTenLop.setForeground(new java.awt.Color(36, 43, 54));

        javax.swing.GroupLayout pnlTxtTenLopLayout = new javax.swing.GroupLayout(pnlTxtTenLop);
        pnlTxtTenLop.setLayout(pnlTxtTenLopLayout);
        pnlTxtTenLopLayout.setHorizontalGroup(
            pnlTxtTenLopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTxtTenLop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(txtTenLop)
        );
        pnlTxtTenLopLayout.setVerticalGroup(
            pnlTxtTenLopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtTenLopLayout.createSequentialGroup()
                .addComponent(lblTxtTenLop)
                .addGap(8, 8, 8)
                .addComponent(txtTenLop, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlTxtNgaySinh.setBackground(new java.awt.Color(255, 255, 255));

        lblTxtNgaySinh.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblTxtNgaySinh.setForeground(new java.awt.Color(36, 43, 54));
        lblTxtNgaySinh.setText("Ngày sinh");

        txtNgaySinh.setEditable(false);
        txtNgaySinh.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtNgaySinh.setForeground(new java.awt.Color(36, 43, 54));

        javax.swing.GroupLayout pnlTxtNgaySinhLayout = new javax.swing.GroupLayout(pnlTxtNgaySinh);
        pnlTxtNgaySinh.setLayout(pnlTxtNgaySinhLayout);
        pnlTxtNgaySinhLayout.setHorizontalGroup(
            pnlTxtNgaySinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTxtNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(txtNgaySinh)
        );
        pnlTxtNgaySinhLayout.setVerticalGroup(
            pnlTxtNgaySinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtNgaySinhLayout.createSequentialGroup()
                .addComponent(lblTxtNgaySinh)
                .addGap(8, 8, 8)
                .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        lblThi.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblThi.setForeground(new java.awt.Color(205, 32, 45));
        lblThi.setText("Thi và kết quả");

        btnChonCaThi.setBackground(new java.awt.Color(205, 32, 45));
        btnChonCaThi.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnChonCaThi.setForeground(new java.awt.Color(255, 255, 255));
        btnChonCaThi.setText("Chọn ca thi");
        btnChonCaThi.setFocusPainted(false);
        btnChonCaThi.setOpaque(true);
        btnChonCaThi.addActionListener(this::btnChonCaThiActionPerformed);

        btnKetQua.setBackground(new java.awt.Color(245, 246, 248));
        btnKetQua.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnKetQua.setForeground(new java.awt.Color(36, 43, 54));
        btnKetQua.setText("Kết quả thi");
        btnKetQua.setFocusPainted(false);
        btnKetQua.setOpaque(true);
        btnKetQua.addActionListener(this::btnKetQuaActionPerformed);

        btnBangDiem.setBackground(new java.awt.Color(245, 246, 248));
        btnBangDiem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnBangDiem.setForeground(new java.awt.Color(36, 43, 54));
        btnBangDiem.setText("Bảng điểm môn học");
        btnBangDiem.setFocusPainted(false);
        btnBangDiem.setOpaque(true);
        btnBangDiem.addActionListener(this::btnBangDiemActionPerformed);

        lblTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblTaiKhoan.setForeground(new java.awt.Color(205, 32, 45));
        lblTaiKhoan.setText("Phiên đăng nhập");

        btnDangXuat.setBackground(new java.awt.Color(245, 246, 248));
        btnDangXuat.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnDangXuat.setForeground(new java.awt.Color(36, 43, 54));
        btnDangXuat.setText("Đăng xuất");
        btnDangXuat.setFocusPainted(false);
        btnDangXuat.setOpaque(true);
        btnDangXuat.addActionListener(this::btnDangXuatActionPerformed);

        btnThoat.setBackground(new java.awt.Color(245, 246, 248));
        btnThoat.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnThoat.setForeground(new java.awt.Color(36, 43, 54));
        btnThoat.setText("Thoát");
        btnThoat.setFocusPainted(false);
        btnThoat.setOpaque(true);
        btnThoat.addActionListener(this::btnThoatActionPerformed);

        javax.swing.GroupLayout pnlNoiDungLayout = new javax.swing.GroupLayout(pnlNoiDung);
        pnlNoiDung.setLayout(pnlNoiDungLayout);
        pnlNoiDungLayout.setHorizontalGroup(
            pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTieuDe, 0, 740, Short.MAX_VALUE)
            .addComponent(lblMoTa, 0, 740, Short.MAX_VALUE)
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(pnlTxtMaNguoiDung, 0, 364, Short.MAX_VALUE)
                .addGap(12)
                .addComponent(pnlTxtHoTen, 0, 364, Short.MAX_VALUE))
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(pnlTxtMaLop, 0, 364, Short.MAX_VALUE)
                .addGap(12)
                .addComponent(pnlTxtTenLop, 0, 364, Short.MAX_VALUE))
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(pnlTxtNgaySinh, 0, 364, Short.MAX_VALUE)
                .addGap(12)
                .addComponent(pnlTxtDiaChi, 0, 364, Short.MAX_VALUE))
            .addComponent(lblThi)
            .addComponent(btnChonCaThi, 0, 740, Short.MAX_VALUE)
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(btnKetQua, 0, 364, Short.MAX_VALUE)
                .addGap(12)
                .addComponent(btnBangDiem, 0, 364, Short.MAX_VALUE))
            .addComponent(lblTaiKhoan)
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(btnDangXuat, 0, 364, Short.MAX_VALUE)
                .addGap(12)
                .addComponent(btnThoat, 0, 364, Short.MAX_VALUE))
        );
        pnlNoiDungLayout.setVerticalGroup(
            pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(lblTieuDe)
                .addGap(10)
                .addComponent(lblMoTa, 44, 44, 44)
                .addGap(22)
                .addGroup(pnlNoiDungLayout.createParallelGroup()
                    .addComponent(pnlTxtMaNguoiDung)
                    .addComponent(pnlTxtHoTen))
                .addGap(12)
                .addGroup(pnlNoiDungLayout.createParallelGroup()
                    .addComponent(pnlTxtMaLop)
                    .addComponent(pnlTxtTenLop))
                .addGap(12)
                .addGroup(pnlNoiDungLayout.createParallelGroup()
                    .addComponent(pnlTxtNgaySinh)
                    .addComponent(pnlTxtDiaChi))
                .addGap(24)
                .addComponent(lblThi)
                .addGap(12)
                .addComponent(btnChonCaThi, 48, 48, 48)
                .addGap(12)
                .addGroup(pnlNoiDungLayout.createParallelGroup()
                    .addComponent(btnKetQua, 48, 48, 48)
                    .addComponent(btnBangDiem, 48, 48, 48))
                .addGap(24)
                .addComponent(lblTaiKhoan)
                .addGap(12)
                .addGroup(pnlNoiDungLayout.createParallelGroup()
                    .addComponent(btnDangXuat, 42, 42, 42)
                    .addComponent(btnThoat, 42, 42, 42)))
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

    private void btnChonCaThiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonCaThiActionPerformed
        if (baiThiDangDo != null) {
            new thi.LamBaiThi(nguoiDung, baiThiDangDo).setVisible(true);
        } else {
            new thi.ChonCaThi(nguoiDung).setVisible(true);
        }
        dispose();
    }//GEN-LAST:event_btnChonCaThiActionPerformed

    private void btnKetQuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKetQuaActionPerformed
        new thi.KetQuaThi(nguoiDung).setVisible(true);
        dispose();
    }//GEN-LAST:event_btnKetQuaActionPerformed

    private void btnBangDiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBangDiemActionPerformed
        new baocao.BangDiemSinhVien(nguoiDung).setVisible(true);
        dispose();
    }//GEN-LAST:event_btnBangDiemActionPerformed

    private void btnDangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangXuatActionPerformed
        new dangNhapdangKy.DangNhapSV().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnDangXuatActionPerformed

    private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnThoatActionPerformed

    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TrangChuSV.class.getName()).log(java.util.logging.Level.WARNING, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> new TrangChuSV().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBangDiem;
    private javax.swing.JButton btnChonCaThi;
    private javax.swing.JButton btnDangXuat;
    private javax.swing.JButton btnKetQua;
    private javax.swing.JButton btnThoat;
    private javax.swing.JLabel lblMoTa;
    private javax.swing.JLabel lblTaiKhoan;
    private javax.swing.JLabel lblThi;
    private javax.swing.JLabel lblTieuDe;
    private javax.swing.JLabel lblTxtDiaChi;
    private javax.swing.JLabel lblTxtHoTen;
    private javax.swing.JLabel lblTxtMaLop;
    private javax.swing.JLabel lblTxtMaNguoiDung;
    private javax.swing.JLabel lblTxtNgaySinh;
    private javax.swing.JLabel lblTxtTenLop;
    private javax.swing.JPanel pnlNoiDung;
    private javax.swing.JPanel pnlTxtDiaChi;
    private javax.swing.JPanel pnlTxtHoTen;
    private javax.swing.JPanel pnlTxtMaLop;
    private javax.swing.JPanel pnlTxtMaNguoiDung;
    private javax.swing.JPanel pnlTxtNgaySinh;
    private javax.swing.JPanel pnlTxtTenLop;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtMaLop;
    private javax.swing.JTextField txtMaNguoiDung;
    private javax.swing.JTextField txtNgaySinh;
    private javax.swing.JTextField txtTenLop;
    // End of variables declaration//GEN-END:variables
}
