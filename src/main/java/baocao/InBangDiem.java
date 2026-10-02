package baocao;

import session.SessionDangNhap;

/**
 * Giao diện InBangDiem. Nghiệp vụ sẽ được bổ sung trong các event handler.
 * Chỉnh bố cục bằng tab Design của NetBeans và giữ file .form đi kèm.
 */
public class InBangDiem extends javax.swing.JFrame {
    private final SessionDangNhap nguoiDung;

    public InBangDiem() {
        this(null);
    }

    public InBangDiem(SessionDangNhap nguoiDung) {
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
        lblTieuDe.setText("BẢNG ĐIỂM THI KẾT THÚC MÔN");
        lblTieuDe.setFont(new java.awt.Font("Segoe UI", 1, 24));
        lblTieuDe.setForeground(new java.awt.Color(205, 32, 45));
        lblMoTa = new javax.swing.JLabel();
        lblMoTa.setText("HỌC VIỆN CÔNG NGHỆ BƯU CHÍNH VIỄN THÔNG");
        lblMoTa.setFont(new java.awt.Font("Segoe UI", 0, 13));
        lblMoTa.setForeground(new java.awt.Color(107, 114, 128));
        pnlTxtLop = new javax.swing.JPanel();
        pnlTxtLop.setBackground(new java.awt.Color(255, 255, 255));
        lblTxtLop = new javax.swing.JLabel();
        lblTxtLop.setText("Lớp");
        lblTxtLop.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblTxtLop.setForeground(new java.awt.Color(36, 43, 54));
        txtLop = new javax.swing.JTextField();
        txtLop.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtLop.setForeground(new java.awt.Color(36, 43, 54));
        txtLop.setBackground(new java.awt.Color(255, 255, 255));
        txtLop.setEditable(false);
        javax.swing.GroupLayout pnlTxtLopLayout = new javax.swing.GroupLayout(pnlTxtLop);
        pnlTxtLop.setLayout(pnlTxtLopLayout);
        pnlTxtLopLayout.setHorizontalGroup(pnlTxtLopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtLopLayout.createSequentialGroup()
                .addComponent(lblTxtLop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlTxtLopLayout.createSequentialGroup()
                .addComponent(txtLop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        pnlTxtLopLayout.setVerticalGroup(pnlTxtLopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtLopLayout.createSequentialGroup()
                .addGroup(pnlTxtLopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTxtLop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlTxtLopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtLop, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))));
        pnlTxtMonHoc = new javax.swing.JPanel();
        pnlTxtMonHoc.setBackground(new java.awt.Color(255, 255, 255));
        lblTxtMonHoc = new javax.swing.JLabel();
        lblTxtMonHoc.setText("Môn học");
        lblTxtMonHoc.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblTxtMonHoc.setForeground(new java.awt.Color(36, 43, 54));
        txtMonHoc = new javax.swing.JTextField();
        txtMonHoc.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtMonHoc.setForeground(new java.awt.Color(36, 43, 54));
        txtMonHoc.setBackground(new java.awt.Color(255, 255, 255));
        txtMonHoc.setEditable(false);
        javax.swing.GroupLayout pnlTxtMonHocLayout = new javax.swing.GroupLayout(pnlTxtMonHoc);
        pnlTxtMonHoc.setLayout(pnlTxtMonHocLayout);
        pnlTxtMonHocLayout.setHorizontalGroup(pnlTxtMonHocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtMonHocLayout.createSequentialGroup()
                .addComponent(lblTxtMonHoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlTxtMonHocLayout.createSequentialGroup()
                .addComponent(txtMonHoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        pnlTxtMonHocLayout.setVerticalGroup(pnlTxtMonHocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtMonHocLayout.createSequentialGroup()
                .addGroup(pnlTxtMonHocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTxtMonHoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlTxtMonHocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMonHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))));
        pnlTxtLanThi = new javax.swing.JPanel();
        pnlTxtLanThi.setBackground(new java.awt.Color(255, 255, 255));
        lblTxtLanThi = new javax.swing.JLabel();
        lblTxtLanThi.setText("Lần thi");
        lblTxtLanThi.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblTxtLanThi.setForeground(new java.awt.Color(36, 43, 54));
        txtLanThi = new javax.swing.JTextField();
        txtLanThi.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtLanThi.setForeground(new java.awt.Color(36, 43, 54));
        txtLanThi.setBackground(new java.awt.Color(255, 255, 255));
        txtLanThi.setEditable(false);
        javax.swing.GroupLayout pnlTxtLanThiLayout = new javax.swing.GroupLayout(pnlTxtLanThi);
        pnlTxtLanThi.setLayout(pnlTxtLanThiLayout);
        pnlTxtLanThiLayout.setHorizontalGroup(pnlTxtLanThiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtLanThiLayout.createSequentialGroup()
                .addComponent(lblTxtLanThi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlTxtLanThiLayout.createSequentialGroup()
                .addComponent(txtLanThi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        pnlTxtLanThiLayout.setVerticalGroup(pnlTxtLanThiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtLanThiLayout.createSequentialGroup()
                .addGroup(pnlTxtLanThiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTxtLanThi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlTxtLanThiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtLanThi, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))));
        pnlTxtNgayThi = new javax.swing.JPanel();
        pnlTxtNgayThi.setBackground(new java.awt.Color(255, 255, 255));
        lblTxtNgayThi = new javax.swing.JLabel();
        lblTxtNgayThi.setText("Ngày thi");
        lblTxtNgayThi.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblTxtNgayThi.setForeground(new java.awt.Color(36, 43, 54));
        txtNgayThi = new javax.swing.JTextField();
        txtNgayThi.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtNgayThi.setForeground(new java.awt.Color(36, 43, 54));
        txtNgayThi.setBackground(new java.awt.Color(255, 255, 255));
        txtNgayThi.setEditable(false);
        javax.swing.GroupLayout pnlTxtNgayThiLayout = new javax.swing.GroupLayout(pnlTxtNgayThi);
        pnlTxtNgayThi.setLayout(pnlTxtNgayThiLayout);
        pnlTxtNgayThiLayout.setHorizontalGroup(pnlTxtNgayThiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtNgayThiLayout.createSequentialGroup()
                .addComponent(lblTxtNgayThi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlTxtNgayThiLayout.createSequentialGroup()
                .addComponent(txtNgayThi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        pnlTxtNgayThiLayout.setVerticalGroup(pnlTxtNgayThiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtNgayThiLayout.createSequentialGroup()
                .addGroup(pnlTxtNgayThiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTxtNgayThi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlTxtNgayThiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNgayThi, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))));
        scrTblBanInBangDiem = new javax.swing.JScrollPane();
        tblBanInBangDiem = new javax.swing.JTable();
        tblBanInBangDiem.setFont(new java.awt.Font("Segoe UI", 0, 13));
        tblBanInBangDiem.setRowHeight(28);
        tblBanInBangDiem.setAutoCreateRowSorter(true);
        tblBanInBangDiem.setFillsViewportHeight(true);
        tblBanInBangDiem.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {}, new String[] {"STT", "Mã sinh viên", "Họ và tên", "Ngày sinh", "Điểm", "Ghi chú"}) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        });
        scrTblBanInBangDiem.setViewportView(tblBanInBangDiem);
        lblNgayLap = new javax.swing.JLabel();
        lblNgayLap.setText("Ngày ........ tháng ........ năm ........");
        lblNgayLap.setFont(new java.awt.Font("Segoe UI", 0, 13));
        lblNgayLap.setForeground(new java.awt.Color(107, 114, 128));
        lblNguoiLap = new javax.swing.JLabel();
        lblNguoiLap.setText("Người lập bảng");
        lblNguoiLap.setFont(new java.awt.Font("Segoe UI", 1, 14));
        lblNguoiLap.setForeground(new java.awt.Color(36, 43, 54));
        lblGiaoVien = new javax.swing.JLabel();
        lblGiaoVien.setText("Giáo viên");
        lblGiaoVien.setFont(new java.awt.Font("Segoe UI", 1, 14));
        lblGiaoVien.setForeground(new java.awt.Color(36, 43, 54));
        lblKyNguoiLap = new javax.swing.JLabel();
        lblKyNguoiLap.setText("(Ký, ghi rõ họ tên)");
        lblKyNguoiLap.setFont(new java.awt.Font("Segoe UI", 0, 12));
        lblKyNguoiLap.setForeground(new java.awt.Color(107, 114, 128));
        lblKyGiaoVien = new javax.swing.JLabel();
        lblKyGiaoVien.setText("(Ký, ghi rõ họ tên)");
        lblKyGiaoVien.setFont(new java.awt.Font("Segoe UI", 0, 12));
        lblKyGiaoVien.setForeground(new java.awt.Color(107, 114, 128));
        btnIn = new javax.swing.JButton();
        btnIn.setText("In bảng điểm");
        btnIn.setFont(new java.awt.Font("Segoe UI", 1, 14));
        btnIn.setBackground(new java.awt.Color(205, 32, 45));
        btnIn.setForeground(new java.awt.Color(255, 255, 255));
        btnIn.setOpaque(true);
        btnIn.setContentAreaFilled(true);
        btnIn.setFocusPainted(false);
        btnIn.addActionListener(this::btnInActionPerformed);
        btnDong = new javax.swing.JButton();
        btnDong.setText("Đóng");
        btnDong.setFont(new java.awt.Font("Segoe UI", 0, 14));
        btnDong.setBackground(new java.awt.Color(245, 246, 248));
        btnDong.setForeground(new java.awt.Color(36, 43, 54));
        btnDong.setOpaque(true);
        btnDong.setContentAreaFilled(true);
        btnDong.setFocusPainted(false);
        btnDong.addActionListener(this::btnDongActionPerformed);
        javax.swing.GroupLayout pnlNoiDungLayout = new javax.swing.GroupLayout(pnlNoiDung);
        pnlNoiDung.setLayout(pnlNoiDungLayout);
        pnlNoiDungLayout.setHorizontalGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(lblTieuDe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(lblMoTa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(pnlTxtLop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(pnlTxtMonHoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(pnlTxtLanThi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(pnlTxtNgayThi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(scrTblBanInBangDiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(lblNgayLap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(lblNguoiLap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(lblGiaoVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(lblKyNguoiLap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(lblKyGiaoVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(btnIn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(btnDong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        pnlNoiDungLayout.setVerticalGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTieuDe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMoTa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlTxtLop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlTxtMonHoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlTxtLanThi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlTxtNgayThi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrTblBanInBangDiem, javax.swing.GroupLayout.PREFERRED_SIZE, 330, Short.MAX_VALUE))
                .addGap(8, 8, 8)
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNgayLap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNguoiLap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblGiaoVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblKyNguoiLap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblKyGiaoVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnIn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDong, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))));
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(pnlNoiDung, javax.swing.GroupLayout.DEFAULT_SIZE, 920, Short.MAX_VALUE)
                .addGap(16, 16, 16)));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlNoiDung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)));
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PTIT | BẢNG ĐIỂM THI KẾT THÚC MÔN");
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/ptit-logo.png")).getImage());
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInActionPerformed
        // TODO: Bổ sung xử lý cho nút này sau khi hoàn thành giao diện.
    }//GEN-LAST:event_btnInActionPerformed

    private void btnDongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDongActionPerformed
        trangchu.DieuHuong.moForm(this, new baocao.BangDiemMonHoc(nguoiDung), nguoiDung);
    }//GEN-LAST:event_btnDongActionPerformed

    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InBangDiem.class.getName()).log(java.util.logging.Level.WARNING, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> new InBangDiem().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel pnlNoiDung;
    private javax.swing.JLabel lblTieuDe;
    private javax.swing.JLabel lblMoTa;
    private javax.swing.JPanel pnlTxtLop;
    private javax.swing.JLabel lblTxtLop;
    private javax.swing.JTextField txtLop;
    private javax.swing.JPanel pnlTxtMonHoc;
    private javax.swing.JLabel lblTxtMonHoc;
    private javax.swing.JTextField txtMonHoc;
    private javax.swing.JPanel pnlTxtLanThi;
    private javax.swing.JLabel lblTxtLanThi;
    private javax.swing.JTextField txtLanThi;
    private javax.swing.JPanel pnlTxtNgayThi;
    private javax.swing.JLabel lblTxtNgayThi;
    private javax.swing.JTextField txtNgayThi;
    private javax.swing.JScrollPane scrTblBanInBangDiem;
    private javax.swing.JTable tblBanInBangDiem;
    private javax.swing.JLabel lblNgayLap;
    private javax.swing.JLabel lblNguoiLap;
    private javax.swing.JLabel lblGiaoVien;
    private javax.swing.JLabel lblKyNguoiLap;
    private javax.swing.JLabel lblKyGiaoVien;
    private javax.swing.JButton btnIn;
    private javax.swing.JButton btnDong;
    // End of variables declaration//GEN-END:variables
}
