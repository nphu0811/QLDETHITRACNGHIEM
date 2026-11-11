package hethong;

import connectionSQLServer.SaoLuuPhucHoiDAO;
import connectionSQLServer.SaoLuuPhucHoiDAO.BanSaoLuu;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Window;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.WindowConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import session.sessionGiaoVien;

/**
 * Giao diện sao lưu và phục hồi dữ liệu.
 * Chỉnh bố cục bằng tab Design của NetBeans và giữ file .form đi kèm.
 */
public class SaoLuuPhucHoi extends javax.swing.JFrame {
    private final sessionGiaoVien nguoiDung;
    private boolean dangXuLy = false;
    private boolean dangCapNhatChonBanSaoLuu = false;

    @FunctionalInterface
    private interface CongViecSQL {
        void thucHien() throws SQLException;
    }

    public SaoLuuPhucHoi() {
        this(null);
    }

    public SaoLuuPhucHoi(sessionGiaoVien nguoiDung) {
        initComponents();
        this.nguoiDung = nguoiDung;
        getContentPane().setBackground(new Color(245, 246, 248));
        setLocationRelativeTo(null);

        boolean daDangNhap = nguoiDung != null && nguoiDung.coKetNoi();
        capNhatTrangThaiDieuKhien(daDangNhap);
        if (daDangNhap) {
            napBangSaoLuu();
        }
    }

    private void capNhatTrangThaiDieuKhien(boolean choPhep) {
        boolean bat = choPhep && !dangXuLy && nguoiDung != null && nguoiDung.coKetNoi();
        btnSaoLuu.setEnabled(bat);
        btnPhucHoi.setEnabled(bat);
        btnXoaLichSu.setEnabled(bat);
        chkResetFileSaoLuu.setEnabled(bat);
        tblBanSaoLuu.setEnabled(bat);
    }

    private void dungChinhSuaBang() {
        if (tblBanSaoLuu.isEditing() && tblBanSaoLuu.getCellEditor() != null) {
            tblBanSaoLuu.getCellEditor().stopCellEditing();
        }
    }

    private void doDuLieuVaoBang(List<BanSaoLuu> danhSach) {
        DefaultTableModel moHinh = (DefaultTableModel) tblBanSaoLuu.getModel();
        moHinh.setRowCount(0);
        for (BanSaoLuu ban : danhSach) {
            moHinh.addRow(new Object[]{ban, Boolean.FALSE, ban.ngayGio()});
        }
    }

    private JDialog taoHopThoaiTienTrinh(String thongBao) {
        Window cuaSoCha = SwingUtilities.getWindowAncestor(pnlNoiDung);
        JDialog hopThoai = new JDialog(cuaSoCha, "Đang xử lý", Dialog.ModalityType.APPLICATION_MODAL);
        hopThoai.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        hopThoai.setResizable(false);

        JPanel pnl = new JPanel(new BorderLayout(0, 12));
        pnl.setBackground(Color.WHITE);
        pnl.setBorder(BorderFactory.createEmptyBorder(20, 28, 20, 28));

        JLabel lbl = new JLabel(thongBao, SwingConstants.CENTER);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lbl.setForeground(new Color(36, 43, 54));

        JProgressBar thanhTienTrinh = new JProgressBar();
        thanhTienTrinh.setIndeterminate(true);
        thanhTienTrinh.setPreferredSize(new Dimension(280, 18));

        pnl.add(lbl, BorderLayout.NORTH);
        pnl.add(thanhTienTrinh, BorderLayout.CENTER);

        hopThoai.setContentPane(pnl);
        hopThoai.pack();
        hopThoai.setLocationRelativeTo(pnlNoiDung);
        return hopThoai;
    }

    private void chayTrongSwingWorker(CongViecSQL congViec, String thongBaoDangXuLy, String thongBaoThanhCong) {
        if (dangXuLy || nguoiDung == null || !nguoiDung.coKetNoi()) {
            return;
        }
        dungChinhSuaBang();
        dangXuLy = true;
        capNhatTrangThaiDieuKhien(false);
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

        JDialog hopThoai = (thongBaoDangXuLy != null && !thongBaoDangXuLy.isEmpty())
                ? taoHopThoaiTienTrinh(thongBaoDangXuLy)
                : null;

        SwingWorker<List<BanSaoLuu>, Void> worker = new SwingWorker<>() {
            @Override
            protected List<BanSaoLuu> doInBackground() throws Exception {
                if (congViec != null) {
                    congViec.thucHien();
                }
                return SaoLuuPhucHoiDAO.layDanhSachSaoLuu(nguoiDung);
            }

            @Override
            protected void done() {
                try {
                    if (hopThoai != null) {
                        hopThoai.dispose();
                    }
                    List<BanSaoLuu> danhSach = get();
                    doDuLieuVaoBang(danhSach);
                    if (thongBaoThanhCong != null && !thongBaoThanhCong.isEmpty()) {
                        JOptionPane.showMessageDialog(
                                pnlNoiDung,
                                thongBaoThanhCong,
                                "Thông báo",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } catch (ExecutionException e) {
                    Throwable nguyenNhan = e.getCause() != null ? e.getCause() : e;
                    JOptionPane.showMessageDialog(
                            pnlNoiDung,
                            nguyenNhan.getMessage(),
                            "Thông báo",
                            JOptionPane.ERROR_MESSAGE
                    );
                } finally {
                    if (hopThoai != null && hopThoai.isDisplayable()) {
                        hopThoai.dispose();
                    }
                    dangXuLy = false;
                    setCursor(Cursor.getDefaultCursor());
                    capNhatTrangThaiDieuKhien(true);
                }
            }
        };

        worker.execute();
        if (hopThoai != null) {
            hopThoai.setVisible(true);
        }
    }

    private void napBangSaoLuu() {
        chayTrongSwingWorker(null, null, null);
    }

    private void dinhDangBangSaoLuu() {
        tblBanSaoLuu.setIntercellSpacing(new Dimension(1, 1));

        JTableHeader dauBang = tblBanSaoLuu.getTableHeader();
        dauBang.setPreferredSize(new Dimension(0, 42));

        JLabel tieuDeCot = new JLabel("", SwingConstants.CENTER);
        tieuDeCot.setFont(tblBanSaoLuu.getFont().deriveFont(Font.BOLD));
        tieuDeCot.setForeground(tblBanSaoLuu.getForeground());
        tieuDeCot.setBackground(new Color(245, 246, 248));
        tieuDeCot.setOpaque(true);
        tieuDeCot.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, tblBanSaoLuu.getGridColor()));
        dauBang.setDefaultRenderer((bang, giaTri, duocChon, coFocus, dong, cot) -> {
            tieuDeCot.setText(giaTri.toString());
            return tieuDeCot;
        });

        JLabel dauDong = new JLabel();
        dauDong.setOpaque(true);
        dauDong.setBackground(new Color(245, 246, 248));
        dauDong.setForeground(tblBanSaoLuu.getForeground());
        dauDong.setBorder(BorderFactory.createEmptyBorder(0, 6, 0, 0));
        tblBanSaoLuu.getColumnModel().getColumn(0).setCellRenderer((bang, giaTri, duocChon, coFocus, dong, cot) -> {
            dauDong.setText(bang.isRowSelected(dong) ? "▸" : "");
            return dauDong;
        });

        DefaultTableModel moHinh = (DefaultTableModel) tblBanSaoLuu.getModel();
        moHinh.addTableModelListener(e -> {
            if (dangCapNhatChonBanSaoLuu
                    || e.getType() != TableModelEvent.UPDATE
                    || e.getColumn() != 1
                    || e.getFirstRow() < 0
                    || e.getFirstRow() >= moHinh.getRowCount()) {
                return;
            }
            int dongDuocChon = e.getFirstRow();
            if (!Boolean.TRUE.equals(moHinh.getValueAt(dongDuocChon, 1))) {
                return;
            }
            dangCapNhatChonBanSaoLuu = true;
            try {
                for (int i = 0; i < moHinh.getRowCount(); i++) {
                    if (i != dongDuocChon && Boolean.TRUE.equals(moHinh.getValueAt(i, 1))) {
                        moHinh.setValueAt(Boolean.FALSE, i, 1);
                    }
                }
            } finally {
                dangCapNhatChonBanSaoLuu = false;
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlNoiDung = new javax.swing.JPanel();
        lblTieuDe = new javax.swing.JLabel();
        btnSaoLuu = new javax.swing.JButton();
        btnPhucHoi = new javax.swing.JButton();
        btnXoaLichSu = new javax.swing.JButton();
        scrBanSaoLuu = new javax.swing.JScrollPane();
        tblBanSaoLuu = new javax.swing.JTable();
        chkResetFileSaoLuu = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PTIT | SAO LƯU VÀ PHỤC HỒI");
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/ptit-logo.png")).getImage());

        pnlNoiDung.setBackground(new java.awt.Color(255, 255, 255));
        pnlNoiDung.setBorder(javax.swing.BorderFactory.createEmptyBorder(24, 24, 24, 24));

        lblTieuDe.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTieuDe.setForeground(new java.awt.Color(205, 32, 45));
        lblTieuDe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTieuDe.setText("SAO LƯU - PHỤC HỒI DỮ LIỆU");

        btnSaoLuu.setBackground(new java.awt.Color(205, 32, 45));
        btnSaoLuu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSaoLuu.setForeground(new java.awt.Color(255, 255, 255));
        btnSaoLuu.setText("Sao lưu");
        btnSaoLuu.addActionListener(this::btnSaoLuuActionPerformed);

        btnPhucHoi.setBackground(new java.awt.Color(245, 246, 248));
        btnPhucHoi.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnPhucHoi.setForeground(new java.awt.Color(36, 43, 54));
        btnPhucHoi.setText("Phục hồi");
        btnPhucHoi.addActionListener(this::btnPhucHoiActionPerformed);

        btnXoaLichSu.setBackground(new java.awt.Color(245, 246, 248));
        btnXoaLichSu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnXoaLichSu.setForeground(new java.awt.Color(36, 43, 54));
        btnXoaLichSu.setText("Xoá lịch sử");
        btnXoaLichSu.addActionListener(this::btnXoaLichSuActionPerformed);

        scrBanSaoLuu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(198, 204, 213)));

        tblBanSaoLuu.setBackground(new java.awt.Color(255, 255, 255));
        tblBanSaoLuu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblBanSaoLuu.setForeground(new java.awt.Color(36, 43, 54));
        tblBanSaoLuu.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][] {},
            new String[] {"", "Chọn bản backup", "Ngày giờ backup"}
        ) {
            private final Class<?>[] types = {Object.class, Boolean.class, String.class};

            @Override
            public Class<?> getColumnClass(int column) {
                return types[column];
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 1;
            }
        });
        tblBanSaoLuu.setCellSelectionEnabled(true);
        tblBanSaoLuu.setFillsViewportHeight(true);
        tblBanSaoLuu.setGridColor(new java.awt.Color(226, 229, 234));
        tblBanSaoLuu.setRowHeight(26);
        tblBanSaoLuu.setSelectionBackground(new java.awt.Color(253, 232, 234));
        tblBanSaoLuu.setSelectionForeground(new java.awt.Color(36, 43, 54));
        tblBanSaoLuu.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblBanSaoLuu.setShowHorizontalLines(true);
        tblBanSaoLuu.setShowVerticalLines(true);
        tblBanSaoLuu.getTableHeader().setReorderingAllowed(false);
        tblBanSaoLuu.getTableHeader().setResizingAllowed(false);
        dinhDangBangSaoLuu();
        scrBanSaoLuu.setViewportView(tblBanSaoLuu);
        if (tblBanSaoLuu.getColumnModel().getColumnCount() > 0) {
            tblBanSaoLuu.getColumnModel().getColumn(0).setMinWidth(48);
            tblBanSaoLuu.getColumnModel().getColumn(0).setPreferredWidth(48);
            tblBanSaoLuu.getColumnModel().getColumn(0).setMaxWidth(48);
            tblBanSaoLuu.getColumnModel().getColumn(1).setMinWidth(128);
            tblBanSaoLuu.getColumnModel().getColumn(1).setPreferredWidth(150);
            tblBanSaoLuu.getColumnModel().getColumn(1).setHeaderValue("<html><center>Chọn bản<br>backup</center></html>");
            tblBanSaoLuu.getColumnModel().getColumn(2).setMinWidth(160);
            tblBanSaoLuu.getColumnModel().getColumn(2).setPreferredWidth(210);
        }

        chkResetFileSaoLuu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        chkResetFileSaoLuu.setForeground(new java.awt.Color(36, 43, 54));
        chkResetFileSaoLuu.setText("Reset file sao lưu");
        chkResetFileSaoLuu.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        chkResetFileSaoLuu.setIconTextGap(8);
        chkResetFileSaoLuu.setOpaque(false);

        javax.swing.GroupLayout pnlNoiDungLayout = new javax.swing.GroupLayout(pnlNoiDung);
        pnlNoiDung.setLayout(pnlNoiDungLayout);
        pnlNoiDungLayout.setHorizontalGroup(
            pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTieuDe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addGap(12, 42, Short.MAX_VALUE)
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSaoLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPhucHoi, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoaLichSu, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 58, 58)
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(scrBanSaoLuu, 365, 410, 550)
                    .addComponent(chkResetFileSaoLuu))
                .addGap(12, 52, Short.MAX_VALUE))
        );
        pnlNoiDungLayout.setVerticalGroup(
            pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(lblTieuDe)
                .addGap(32, 32, 32)
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlNoiDungLayout.createSequentialGroup()
                        .addComponent(btnSaoLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(btnPhucHoi, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(btnXoaLichSu, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlNoiDungLayout.createSequentialGroup()
                        .addComponent(scrBanSaoLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(chkResetFileSaoLuu)))
                .addGap(24, 24, 24))
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
                .addGap(16, 16, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaoLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaoLuuActionPerformed
        boolean resetFile = chkResetFileSaoLuu.isSelected();
        chayTrongSwingWorker(
                () -> SaoLuuPhucHoiDAO.saoLuuDuLieu(nguoiDung, resetFile),
                "Đang sao lưu dữ liệu, vui lòng đợi...",
                "Sao lưu dữ liệu thành công."
        );
    }//GEN-LAST:event_btnSaoLuuActionPerformed

    private void btnPhucHoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPhucHoiActionPerformed
        dungChinhSuaBang();
        DefaultTableModel moHinh = (DefaultTableModel) tblBanSaoLuu.getModel();
        BanSaoLuu banDuocChon = null;
        int soDongTich = 0;

        for (int dong = 0; dong < moHinh.getRowCount(); dong++) {
            if (Boolean.TRUE.equals(moHinh.getValueAt(dong, 1))) {
                soDongTich++;
                if (moHinh.getValueAt(dong, 0) instanceof BanSaoLuu ban) {
                    banDuocChon = ban;
                }
            }
        }

        if (soDongTich != 1 || banDuocChon == null) {
            JOptionPane.showMessageDialog(
                    pnlNoiDung,
                    "Vui lòng tích chọn đúng một bản sao lưu để phục hồi.",
                    "Thông báo",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        int xacNhan = JOptionPane.showConfirmDialog(
                pnlNoiDung,
                "Bạn có chắc chắn muốn phục hồi cơ sở dữ liệu về bản sao lưu lúc "
                        + banDuocChon.ngayGio() + "?\n"
                        + "Dữ liệu hiện tại sẽ bị ghi đè và các kết nối đang dùng sẽ bị ngắt.",
                "Xác nhận phục hồi",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );
        if (xacNhan != JOptionPane.YES_OPTION) {
            return;
        }

        int viTri = banDuocChon.position();
        chayTrongSwingWorker(
                () -> SaoLuuPhucHoiDAO.phucHoiDuLieu(nguoiDung, viTri),
                "Đang phục hồi dữ liệu, vui lòng đợi...",
                "Phục hồi dữ liệu thành công."
        );
    }//GEN-LAST:event_btnPhucHoiActionPerformed

    private void btnXoaLichSuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaLichSuActionPerformed
        chayTrongSwingWorker(
                () -> SaoLuuPhucHoiDAO.xoaLichSuSaoLuu(nguoiDung),
                "Đang xoá lịch sử sao lưu, vui lòng đợi...",
                "Đã xoá lịch sử sao lưu."
        );
    }//GEN-LAST:event_btnXoaLichSuActionPerformed

    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SaoLuuPhucHoi.class.getName()).log(java.util.logging.Level.WARNING, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> new SaoLuuPhucHoi().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPhucHoi;
    private javax.swing.JButton btnSaoLuu;
    private javax.swing.JButton btnXoaLichSu;
    private javax.swing.JCheckBox chkResetFileSaoLuu;
    private javax.swing.JLabel lblTieuDe;
    private javax.swing.JPanel pnlNoiDung;
    private javax.swing.JScrollPane scrBanSaoLuu;
    private javax.swing.JTable tblBanSaoLuu;
    // End of variables declaration//GEN-END:variables
}
