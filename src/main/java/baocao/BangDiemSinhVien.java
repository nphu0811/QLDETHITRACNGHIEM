package baocao;

import connectionSQLServer.ThiDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import session.BaiThi;
import session.DiemThi;
import session.SessionDangNhap;
import session.sessionSinhVien;

/**
 * Bảng điểm và các bài thi đã hoàn thành của sinh viên đang đăng nhập.
 * Chỉnh bố cục bằng tab Design của NetBeans và giữ file .form đi kèm.
 */
public class BangDiemSinhVien extends javax.swing.JFrame {
    private final SessionDangNhap nguoiDung;
    private boolean dangTaiChiTiet;

    public BangDiemSinhVien() {
        this(null);
    }

    public BangDiemSinhVien(SessionDangNhap nguoiDung) {
        initComponents();
        this.nguoiDung = nguoiDung;
        if (nguoiDung instanceof sessionSinhVien) {
            sessionSinhVien sinhVien = (sessionSinhVien) nguoiDung;
            txtMaSV.setText(sinhVien.getMaSV());
            txtHoTen.setText(sinhVien.getHo() + " " + sinhVien.getTen());
            txtMaLop.setText(sinhVien.getMaLop());
            txtTenLop.setText(sinhVien.getTenLop());
        }
        tblBangDiem.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                btnXemChiTiet.setEnabled(!dangTaiChiTiet && tblBangDiem.getSelectedRow() >= 0);
            }
        });
        getContentPane().setBackground(new java.awt.Color(245, 246, 248));
        setLocationRelativeTo(null);
        if (nguoiDung instanceof sessionSinhVien) taiBangDiem();
    }

    private void taiBangDiem() {
        lblTrangThai.setText("Đang tải bảng điểm...");
        new SwingWorker<ArrayList<DiemThi>, Void>() {
            @Override
            protected ArrayList<DiemThi> doInBackground() throws SQLException {
                return ThiDAO.layBangDiem(nguoiDung.getMaNguoiDung());
            }

            @Override
            protected void done() {
                if (!isDisplayable()) return;
                try {
                    ArrayList<DiemThi> danhSach = get();
                    DefaultTableModel model = (DefaultTableModel) tblBangDiem.getModel();
                    model.setRowCount(0);
                    for (DiemThi diem : danhSach) {
                        model.addRow(new Object[]{diem.getMaMH(), diem.getTenMH(),
                            diem.getNgayThi(), diem.getLan(), diem.getDiem()});
                    }
                    if (danhSach.isEmpty()) {
                        lblTrangThai.setText("Bạn chưa có bài thi nào đã hoàn thành.");
                    } else {
                        lblTrangThai.setText("Có " + danhSach.size() + " bài thi. Chọn một bài để xem chi tiết.");
                    }
                } catch (Exception e) {
                    lblTrangThai.setText("Không tải được bảng điểm.");
                    hienThiLoi(e);
                }
            }
        }.execute();
    }

    private void hienThiLoi(Exception e) {
        Throwable loi = e.getCause() == null ? e : e.getCause();
        JOptionPane.showMessageDialog(this, loi.getMessage());
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        pnlNoiDung = new javax.swing.JPanel();
        pnlNoiDung.setBackground(new java.awt.Color(255, 255, 255));
        pnlNoiDung.setBorder(javax.swing.BorderFactory.createEmptyBorder(24, 24, 24, 24));
        lblTieuDe = new javax.swing.JLabel();
        lblTieuDe.setText("BẢNG ĐIỂM SINH VIÊN");
        lblTieuDe.setFont(new java.awt.Font("Segoe UI", 1, 24));
        lblTieuDe.setForeground(new java.awt.Color(205, 32, 45));
        lblMoTa = new javax.swing.JLabel();
        lblMoTa.setText("Các bài thi đã hoàn thành của sinh viên đang đăng nhập");
        lblMoTa.setFont(new java.awt.Font("Segoe UI", 0, 13));
        lblMoTa.setForeground(new java.awt.Color(107, 114, 128));
        pnlTxtMaSV = new javax.swing.JPanel();
        pnlTxtMaSV.setBackground(new java.awt.Color(255, 255, 255));
        lblTxtMaSV = new javax.swing.JLabel();
        lblTxtMaSV.setText("Mã sinh viên");
        lblTxtMaSV.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblTxtMaSV.setForeground(new java.awt.Color(36, 43, 54));
        txtMaSV = new javax.swing.JTextField();
        txtMaSV.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtMaSV.setForeground(new java.awt.Color(36, 43, 54));
        txtMaSV.setBackground(new java.awt.Color(255, 255, 255));
        txtMaSV.setEditable(false);
        javax.swing.GroupLayout pnlTxtMaSVLayout = new javax.swing.GroupLayout(pnlTxtMaSV);
        pnlTxtMaSV.setLayout(pnlTxtMaSVLayout);
        pnlTxtMaSVLayout.setHorizontalGroup(pnlTxtMaSVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtMaSVLayout.createSequentialGroup()
                .addComponent(lblTxtMaSV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlTxtMaSVLayout.createSequentialGroup()
                .addComponent(txtMaSV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        pnlTxtMaSVLayout.setVerticalGroup(pnlTxtMaSVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtMaSVLayout.createSequentialGroup()
                .addGroup(pnlTxtMaSVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTxtMaSV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlTxtMaSVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMaSV, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))));
        pnlTxtHoTen = new javax.swing.JPanel();
        pnlTxtHoTen.setBackground(new java.awt.Color(255, 255, 255));
        lblTxtHoTen = new javax.swing.JLabel();
        lblTxtHoTen.setText("Họ tên");
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
        pnlTxtMaLop = new javax.swing.JPanel();
        pnlTxtMaLop.setBackground(new java.awt.Color(255, 255, 255));
        lblTxtMaLop = new javax.swing.JLabel();
        lblTxtMaLop.setText("Mã lớp");
        lblTxtMaLop.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblTxtMaLop.setForeground(new java.awt.Color(36, 43, 54));
        txtMaLop = new javax.swing.JTextField();
        txtMaLop.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtMaLop.setForeground(new java.awt.Color(36, 43, 54));
        txtMaLop.setBackground(new java.awt.Color(255, 255, 255));
        txtMaLop.setEditable(false);
        javax.swing.GroupLayout pnlTxtMaLopLayout = new javax.swing.GroupLayout(pnlTxtMaLop);
        pnlTxtMaLop.setLayout(pnlTxtMaLopLayout);
        pnlTxtMaLopLayout.setHorizontalGroup(pnlTxtMaLopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtMaLopLayout.createSequentialGroup()
                .addComponent(lblTxtMaLop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlTxtMaLopLayout.createSequentialGroup()
                .addComponent(txtMaLop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        pnlTxtMaLopLayout.setVerticalGroup(pnlTxtMaLopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtMaLopLayout.createSequentialGroup()
                .addGroup(pnlTxtMaLopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTxtMaLop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlTxtMaLopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMaLop, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))));
        pnlTxtTenLop = new javax.swing.JPanel();
        pnlTxtTenLop.setBackground(new java.awt.Color(255, 255, 255));
        lblTxtTenLop = new javax.swing.JLabel();
        lblTxtTenLop.setText("Tên lớp");
        lblTxtTenLop.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblTxtTenLop.setForeground(new java.awt.Color(36, 43, 54));
        txtTenLop = new javax.swing.JTextField();
        txtTenLop.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtTenLop.setForeground(new java.awt.Color(36, 43, 54));
        txtTenLop.setBackground(new java.awt.Color(255, 255, 255));
        txtTenLop.setEditable(false);
        javax.swing.GroupLayout pnlTxtTenLopLayout = new javax.swing.GroupLayout(pnlTxtTenLop);
        pnlTxtTenLop.setLayout(pnlTxtTenLopLayout);
        pnlTxtTenLopLayout.setHorizontalGroup(pnlTxtTenLopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtTenLopLayout.createSequentialGroup()
                .addComponent(lblTxtTenLop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlTxtTenLopLayout.createSequentialGroup()
                .addComponent(txtTenLop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        pnlTxtTenLopLayout.setVerticalGroup(pnlTxtTenLopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtTenLopLayout.createSequentialGroup()
                .addGroup(pnlTxtTenLopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTxtTenLop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlTxtTenLopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTenLop, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))));
        scrTblBangDiem = new javax.swing.JScrollPane();
        tblBangDiem = new javax.swing.JTable();
        tblBangDiem.setFont(new java.awt.Font("Segoe UI", 0, 13));
        tblBangDiem.setRowHeight(28);
        tblBangDiem.setAutoCreateRowSorter(true);
        tblBangDiem.setFillsViewportHeight(true);
        tblBangDiem.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblBangDiem.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {}, new String[] {"Mã môn", "Tên môn học", "Ngày thi", "Lần thi", "Điểm"}) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
            @Override public Class<?> getColumnClass(int column) {
                if (column == 3) return Integer.class;
                if (column == 4) return Double.class;
                return String.class;
            }
        });
        scrTblBangDiem.setViewportView(tblBangDiem);
        lblTrangThai = new javax.swing.JLabel();
        lblTrangThai.setText("Đăng nhập sinh viên để xem bảng điểm.");
        lblTrangThai.setFont(new java.awt.Font("Segoe UI", 0, 13));
        lblTrangThai.setForeground(new java.awt.Color(107, 114, 128));
        btnTroVe = new javax.swing.JButton();
        btnTroVe.setText("Trở về");
        btnTroVe.setFont(new java.awt.Font("Segoe UI", 0, 14));
        btnTroVe.setBackground(new java.awt.Color(245, 246, 248));
        btnTroVe.setForeground(new java.awt.Color(36, 43, 54));
        btnTroVe.setOpaque(true);
        btnTroVe.setContentAreaFilled(true);
        btnTroVe.setFocusPainted(false);
        btnTroVe.addActionListener(this::btnTroVeActionPerformed);
        btnXemChiTiet = new javax.swing.JButton();
        btnXemChiTiet.setText("Xem chi tiết bài làm");
        btnXemChiTiet.setFont(new java.awt.Font("Segoe UI", 1, 14));
        btnXemChiTiet.setBackground(new java.awt.Color(205, 32, 45));
        btnXemChiTiet.setForeground(new java.awt.Color(255, 255, 255));
        btnXemChiTiet.setOpaque(true);
        btnXemChiTiet.setContentAreaFilled(true);
        btnXemChiTiet.setFocusPainted(false);
        btnXemChiTiet.setEnabled(false);
        btnXemChiTiet.addActionListener(this::btnXemChiTietActionPerformed);
        javax.swing.GroupLayout pnlNoiDungLayout = new javax.swing.GroupLayout(pnlNoiDung);
        pnlNoiDung.setLayout(pnlNoiDungLayout);
        pnlNoiDungLayout.setHorizontalGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(lblTieuDe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(lblMoTa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(pnlTxtMaSV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(pnlTxtHoTen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(pnlTxtMaLop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(pnlTxtTenLop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(scrTblBangDiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(lblTrangThai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(btnTroVe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(btnXemChiTiet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        pnlNoiDungLayout.setVerticalGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTieuDe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMoTa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlTxtMaSV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlTxtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlTxtMaLop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlTxtTenLop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrTblBangDiem, javax.swing.GroupLayout.PREFERRED_SIZE, 280, Short.MAX_VALUE))
                .addGap(8, 8, 8)
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTroVe, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXemChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))));
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
        setTitle("PTIT | BẢNG ĐIỂM SINH VIÊN");
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/ptit-logo.png")).getImage());
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTroVeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTroVeActionPerformed
        trangchu.DieuHuong.moForm(this, new trangchu.TrangChu(nguoiDung), nguoiDung);
    }//GEN-LAST:event_btnTroVeActionPerformed

    private void btnXemChiTietActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemChiTietActionPerformed
        if (dangTaiChiTiet || !(nguoiDung instanceof sessionSinhVien)) return;
        int dong = tblBangDiem.getSelectedRow();
        if (dong < 0) {
            JOptionPane.showMessageDialog(this, "Chọn một bài thi để xem chi tiết.");
            return;
        }
        // Sau khi sắp xếp, dòng trên màn hình có thể khác dòng trong model.
        dong = tblBangDiem.convertRowIndexToModel(dong);
        String maMH = tblBangDiem.getModel().getValueAt(dong, 0).toString();
        int lan = ((Number) tblBangDiem.getModel().getValueAt(dong, 3)).intValue();
        dangTaiChiTiet = true;
        btnXemChiTiet.setEnabled(false);
        lblTrangThai.setText("Đang mở chi tiết bài thi...");
        new SwingWorker<BaiThi, Void>() {
            @Override
            protected BaiThi doInBackground() throws SQLException {
                return ThiDAO.xem(nguoiDung.getMaNguoiDung(), maMH, lan);
            }

            @Override
            protected void done() {
                if (!isDisplayable()) return;
                try {
                    BaiThi baiThi = get();
                    trangchu.DieuHuong.moForm(BangDiemSinhVien.this,
                            new XemKetQua(nguoiDung, baiThi, true), nguoiDung);
                } catch (Exception e) {
                    dangTaiChiTiet = false;
                    btnXemChiTiet.setEnabled(tblBangDiem.getSelectedRow() >= 0);
                    lblTrangThai.setText("Không mở được bài thi đã chọn. Bạn có thể thử lại.");
                    hienThiLoi(e);
                }
            }
        }.execute();
    }//GEN-LAST:event_btnXemChiTietActionPerformed

    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BangDiemSinhVien.class.getName()).log(java.util.logging.Level.WARNING, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> new BangDiemSinhVien().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel pnlNoiDung;
    private javax.swing.JLabel lblTieuDe;
    private javax.swing.JLabel lblMoTa;
    private javax.swing.JPanel pnlTxtMaSV;
    private javax.swing.JLabel lblTxtMaSV;
    private javax.swing.JTextField txtMaSV;
    private javax.swing.JPanel pnlTxtHoTen;
    private javax.swing.JLabel lblTxtHoTen;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JPanel pnlTxtMaLop;
    private javax.swing.JLabel lblTxtMaLop;
    private javax.swing.JTextField txtMaLop;
    private javax.swing.JPanel pnlTxtTenLop;
    private javax.swing.JLabel lblTxtTenLop;
    private javax.swing.JTextField txtTenLop;
    private javax.swing.JScrollPane scrTblBangDiem;
    private javax.swing.JTable tblBangDiem;
    private javax.swing.JLabel lblTrangThai;
    private javax.swing.JButton btnTroVe;
    private javax.swing.JButton btnXemChiTiet;
    // End of variables declaration//GEN-END:variables
}
