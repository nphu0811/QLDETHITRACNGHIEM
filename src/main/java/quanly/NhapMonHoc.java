package quanly;

import session.SessionDangNhap;

/**
 * Giao diện NhapMonHoc. Nghiệp vụ sẽ được bổ sung trong các event handler.
 * Chỉnh bố cục bằng tab Design của NetBeans và giữ file .form đi kèm.
 */
public class NhapMonHoc extends javax.swing.JFrame {
    private final SessionDangNhap nguoiDung;

    public NhapMonHoc() {
        this(null);
    }

    public NhapMonHoc(SessionDangNhap nguoiDung) {
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
        lblTieuDe.setText("QUẢN LÝ MÔN HỌC");
        lblTieuDe.setFont(new java.awt.Font("Segoe UI", 1, 24));
        lblTieuDe.setForeground(new java.awt.Color(205, 32, 45));
        lblMoTa = new javax.swing.JLabel();
        lblMoTa.setText("Danh mục môn học dùng trong bộ đề và các kỳ thi");
        lblMoTa.setFont(new java.awt.Font("Segoe UI", 0, 13));
        lblMoTa.setForeground(new java.awt.Color(107, 114, 128));
        pnlTxtMaMH = new javax.swing.JPanel();
        pnlTxtMaMH.setBackground(new java.awt.Color(255, 255, 255));
        lblTxtMaMH = new javax.swing.JLabel();
        lblTxtMaMH.setText("Mã môn học");
        lblTxtMaMH.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblTxtMaMH.setForeground(new java.awt.Color(36, 43, 54));
        txtMaMH = new javax.swing.JTextField();
        txtMaMH.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtMaMH.setForeground(new java.awt.Color(36, 43, 54));
        txtMaMH.setBackground(new java.awt.Color(255, 255, 255));
        txtMaMH.setEditable(true);
        txtMaMH.setToolTipText("MAMH · tối đa 5 ký tự");
        javax.swing.GroupLayout pnlTxtMaMHLayout = new javax.swing.GroupLayout(pnlTxtMaMH);
        pnlTxtMaMH.setLayout(pnlTxtMaMHLayout);
        pnlTxtMaMHLayout.setHorizontalGroup(pnlTxtMaMHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtMaMHLayout.createSequentialGroup()
                .addComponent(lblTxtMaMH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlTxtMaMHLayout.createSequentialGroup()
                .addComponent(txtMaMH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        pnlTxtMaMHLayout.setVerticalGroup(pnlTxtMaMHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtMaMHLayout.createSequentialGroup()
                .addGroup(pnlTxtMaMHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTxtMaMH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlTxtMaMHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMaMH, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))));
        pnlTxtTenMH = new javax.swing.JPanel();
        pnlTxtTenMH.setBackground(new java.awt.Color(255, 255, 255));
        lblTxtTenMH = new javax.swing.JLabel();
        lblTxtTenMH.setText("Tên môn học");
        lblTxtTenMH.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblTxtTenMH.setForeground(new java.awt.Color(36, 43, 54));
        txtTenMH = new javax.swing.JTextField();
        txtTenMH.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtTenMH.setForeground(new java.awt.Color(36, 43, 54));
        txtTenMH.setBackground(new java.awt.Color(255, 255, 255));
        txtTenMH.setEditable(true);
        txtTenMH.setToolTipText("TENMH · tối đa 40 ký tự");
        javax.swing.GroupLayout pnlTxtTenMHLayout = new javax.swing.GroupLayout(pnlTxtTenMH);
        pnlTxtTenMH.setLayout(pnlTxtTenMHLayout);
        pnlTxtTenMHLayout.setHorizontalGroup(pnlTxtTenMHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtTenMHLayout.createSequentialGroup()
                .addComponent(lblTxtTenMH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlTxtTenMHLayout.createSequentialGroup()
                .addComponent(txtTenMH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        pnlTxtTenMHLayout.setVerticalGroup(pnlTxtTenMHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtTenMHLayout.createSequentialGroup()
                .addGroup(pnlTxtTenMHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTxtTenMH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlTxtTenMHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTenMH, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))));
        pnlTxtTuKhoa = new javax.swing.JPanel();
        pnlTxtTuKhoa.setBackground(new java.awt.Color(255, 255, 255));
        lblTxtTuKhoa = new javax.swing.JLabel();
        lblTxtTuKhoa.setText("Tìm môn học");
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
        btnTimKiem = new javax.swing.JButton();
        btnTimKiem.setText("Tìm kiếm");
        btnTimKiem.setFont(new java.awt.Font("Segoe UI", 0, 14));
        btnTimKiem.setBackground(new java.awt.Color(245, 246, 248));
        btnTimKiem.setForeground(new java.awt.Color(36, 43, 54));
        btnTimKiem.setOpaque(true);
        btnTimKiem.setContentAreaFilled(true);
        btnTimKiem.setFocusPainted(false);
        btnTimKiem.addActionListener(this::btnTimKiemActionPerformed);
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
        lblDanhSach = new javax.swing.JLabel();
        lblDanhSach.setText("Danh sách môn học");
        lblDanhSach.setFont(new java.awt.Font("Segoe UI", 1, 16));
        lblDanhSach.setForeground(new java.awt.Color(205, 32, 45));
        scrTblMonHoc = new javax.swing.JScrollPane();
        tblMonHoc = new javax.swing.JTable();
        tblMonHoc.setFont(new java.awt.Font("Segoe UI", 0, 13));
        tblMonHoc.setRowHeight(28);
        tblMonHoc.setAutoCreateRowSorter(true);
        tblMonHoc.setFillsViewportHeight(true);
        tblMonHoc.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {}, new String[] {"Mã môn học", "Tên môn học"}) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        });
        scrTblMonHoc.setViewportView(tblMonHoc);
        javax.swing.GroupLayout pnlNoiDungLayout = new javax.swing.GroupLayout(pnlNoiDung);
        pnlNoiDung.setLayout(pnlNoiDungLayout);
        pnlNoiDungLayout.setHorizontalGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(lblTieuDe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(lblMoTa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(pnlTxtMaMH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(pnlTxtTenMH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(pnlTxtTuKhoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(btnTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addComponent(lblDanhSach, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(scrTblMonHoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        pnlNoiDungLayout.setVerticalGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTieuDe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMoTa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlTxtMaMH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlTxtTenMH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlTxtTuKhoa, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addComponent(lblDanhSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrTblMonHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 270, Short.MAX_VALUE))));
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
        setTitle("PTIT | QUẢN LÝ MÔN HỌC");
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/ptit-logo.png")).getImage());
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        // TODO: Bổ sung xử lý cho nút này sau khi hoàn thành giao diện.
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO: Bổ sung xử lý cho nút này sau khi hoàn thành giao diện.
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO: Bổ sung xử lý cho nút này sau khi hoàn thành giao diện.
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnHieuChinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHieuChinhActionPerformed
        // TODO: Bổ sung xử lý cho nút này sau khi hoàn thành giao diện.
    }//GEN-LAST:event_btnHieuChinhActionPerformed

    private void btnPhucHoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPhucHoiActionPerformed
        // TODO: Bổ sung xử lý cho nút này sau khi hoàn thành giao diện.
    }//GEN-LAST:event_btnPhucHoiActionPerformed

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
            java.util.logging.Logger.getLogger(NhapMonHoc.class.getName()).log(java.util.logging.Level.WARNING, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> new NhapMonHoc().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel pnlNoiDung;
    private javax.swing.JLabel lblTieuDe;
    private javax.swing.JLabel lblMoTa;
    private javax.swing.JPanel pnlTxtMaMH;
    private javax.swing.JLabel lblTxtMaMH;
    private javax.swing.JTextField txtMaMH;
    private javax.swing.JPanel pnlTxtTenMH;
    private javax.swing.JLabel lblTxtTenMH;
    private javax.swing.JTextField txtTenMH;
    private javax.swing.JPanel pnlTxtTuKhoa;
    private javax.swing.JLabel lblTxtTuKhoa;
    private javax.swing.JTextField txtTuKhoa;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnHieuChinh;
    private javax.swing.JButton btnPhucHoi;
    private javax.swing.JButton btnTim;
    private javax.swing.JButton btnGhi;
    private javax.swing.JLabel lblDanhSach;
    private javax.swing.JScrollPane scrTblMonHoc;
    private javax.swing.JTable tblMonHoc;
    // End of variables declaration//GEN-END:variables
}
