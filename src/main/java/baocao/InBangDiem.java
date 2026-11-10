package baocao;

import Helper.DateHelper;
import java.awt.Component;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

import session.sessionGiaoVien;
import trangchu.TrangChuGV;

/**
 * Giao diện InBangDiem. Nghiệp vụ sẽ được bổ sung trong các event handler.
 * Chỉnh bố cục bằng tab Design của NetBeans và giữ file .form đi kèm.
 */
public class InBangDiem extends javax.swing.JFrame {
    private final sessionGiaoVien nguoiDung;

    public InBangDiem() {
        this(null);
    }

    public InBangDiem(sessionGiaoVien nguoiDung) {
        initComponents();
        this.nguoiDung = nguoiDung;
        getContentPane().setBackground(new java.awt.Color(245, 246, 248));
        setLocationRelativeTo(null);

        btnIn.setEnabled(false);
    }

    private BangDiemMonHoc.BaoCao baoCao;

    InBangDiem(sessionGiaoVien nguoiDung, BangDiemMonHoc.BaoCao baoCao) {
        this(nguoiDung);
        this.baoCao = baoCao;
        if (baoCao == null) return;
        txtLop.setText(baoCao.lop());
        txtMonHoc.setText(baoCao.mon());
        txtLanThi.setText(baoCao.lan());
        ArrayList<Object[]> danhSach = new ArrayList<>();
        String ngayThi = null;
        for (Object[] dong : baoCao.diem()) {
            danhSach.add(new Object[]{danhSach.size() + 1, dong[0],
                (Objects.toString(dong[1], "") + " " + Objects.toString(dong[2], "")).strip(),
                DateHelper.dinhDang((Date) dong[3]), dong[6], ""});
            String ngay = DateHelper.dinhDang((Date) dong[4]);
            if (ngayThi == null) ngayThi = ngay;
            else if (!ngayThi.equals(ngay)) ngayThi = "Nhiều ngày";
        }
        txtNgayThi.setText(ngayThi);
        DefaultTableModel model = (DefaultTableModel) tblBanInBangDiem.getModel();
        model.setRowCount(0);
        for (Object[] dong : danhSach) {
            model.addRow(dong);
        }
        btnIn.setEnabled(!danhSach.isEmpty());
    }

    static void inBang(JTable bang, Component form, BangDiemMonHoc.BaoCao baoCao) {
        inBang(bang, form, null, baoCao);
    }

    static void inBang(JTable bang, Component form, sessionGiaoVien nguoiDung, BangDiemMonHoc.BaoCao baoCao) {
        if (baoCao == null || baoCao.diem().isEmpty() || (bang != null && bang.getRowCount() == 0)) return;

        String ngayThi = null;
        for (Object[] dong : baoCao.diem()) {
            String ngay = DateHelper.dinhDang((Date) dong[4]);
            if (ngayThi == null) ngayThi = ngay;
            else if (!ngayThi.equals(ngay)) ngayThi = "Nhiều ngày";
        }
        String tenNguoiLap = nguoiDung != null
                ? (Objects.toString(nguoiDung.getHo(), "") + " " + Objects.toString(nguoiDung.getTen(), "")).strip()
                : "";

        Map<String, Object> thamSo = new HashMap<>();
        thamSo.put("MA_LOP", baoCao.maLop());
        thamSo.put("MA_MH", baoCao.maMon());
        thamSo.put("LAN", Integer.parseInt(baoCao.lan()));
        thamSo.put("TEN_LOP", baoCao.lop());
        thamSo.put("TEN_MON", baoCao.mon());
        thamSo.put("NGAY_THI", ngayThi);
        thamSo.put("NGUOI_LAP", tenNguoiLap);
        thamSo.put("GIAO_VIEN", tenNguoiLap);

        try (InputStream mauBaoCao = InBangDiem.class.getResourceAsStream("/Helper/BangDiemMonHoc.jrxml")) {
            if (mauBaoCao == null) {
                JOptionPane.showMessageDialog(form, "Không tìm thấy mẫu báo cáo /Helper/BangDiemMonHoc.jrxml");
                return;
            }
            JasperReport report = JasperCompileManager.compileReport(mauBaoCao);
            JasperPrint banIn;
            if (nguoiDung != null && nguoiDung.coKetNoi()) {
                try (Connection ketNoi = nguoiDung.moKetNoi()) {
                    banIn = JasperFillManager.fillReport(report, thamSo, ketNoi);
                }
            } else {
                Collection<Map<String, ?>> duLieu = new ArrayList<>();
                for (Object[] dong : baoCao.diem()) {
                    Map<String, Object> banGhi = new HashMap<>();
                    banGhi.put("MASV", dong[0]);
                    banGhi.put("HO", dong[1]);
                    banGhi.put("TEN", dong[2]);
                    banGhi.put("NGAYSINH", dong[3]);
                    banGhi.put("NGAYTHI", dong[4]);
                    banGhi.put("LAN", dong[5] instanceof Number n ? n.intValue() : null);
                    banGhi.put("DIEM", dong[6] instanceof Number n ? n.doubleValue() : null);
                    duLieu.add(banGhi);
                }
                banIn = JasperFillManager.fillReport(report, thamSo, new JRMapCollectionDataSource(duLieu));
            }
            JasperViewer viewer = new JasperViewer(banIn, false);
            viewer.setTitle("PTIT | BẢNG ĐIỂM THI KẾT THÚC MÔN");
            viewer.setSize(950, 750);
            viewer.setLocationRelativeTo(form);
            viewer.setFitPageZoomRatio();
            viewer.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(form, "Không in được bảng điểm: " + e.getMessage());
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlNoiDung = new javax.swing.JPanel();
        lblTieuDe = new javax.swing.JLabel();
        lblMoTa = new javax.swing.JLabel();
        pnlTxtLop = new javax.swing.JPanel();
        lblTxtLop = new javax.swing.JLabel();
        txtLop = new javax.swing.JTextField();
        pnlTxtMonHoc = new javax.swing.JPanel();
        lblTxtMonHoc = new javax.swing.JLabel();
        txtMonHoc = new javax.swing.JTextField();
        pnlTxtLanThi = new javax.swing.JPanel();
        lblTxtLanThi = new javax.swing.JLabel();
        txtLanThi = new javax.swing.JTextField();
        pnlTxtNgayThi = new javax.swing.JPanel();
        lblTxtNgayThi = new javax.swing.JLabel();
        txtNgayThi = new javax.swing.JTextField();
        scrTblBanInBangDiem = new javax.swing.JScrollPane();
        tblBanInBangDiem = new javax.swing.JTable();
        lblNgayLap = new javax.swing.JLabel();
        lblNguoiLap = new javax.swing.JLabel();
        lblGiaoVien = new javax.swing.JLabel();
        lblKyNguoiLap = new javax.swing.JLabel();
        lblKyGiaoVien = new javax.swing.JLabel();
        btnIn = new javax.swing.JButton();
        btnDong = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PTIT | BẢNG ĐIỂM THI KẾT THÚC MÔN");
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/ptit-logo.png")).getImage());

        pnlNoiDung.setBackground(new java.awt.Color(255, 255, 255));
        pnlNoiDung.setBorder(javax.swing.BorderFactory.createEmptyBorder(24, 24, 24, 24));

        lblTieuDe.setText("BẢNG ĐIỂM THI KẾT THÚC MÔN");
        lblTieuDe.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTieuDe.setForeground(new java.awt.Color(205, 32, 45));

        lblMoTa.setText("HỌC VIỆN CÔNG NGHỆ BƯU CHÍNH VIỄN THÔNG");
        lblMoTa.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblMoTa.setForeground(new java.awt.Color(107, 114, 128));

        pnlTxtLop.setBackground(new java.awt.Color(255, 255, 255));

        lblTxtLop.setText("Lớp");
        lblTxtLop.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblTxtLop.setForeground(new java.awt.Color(36, 43, 54));

        txtLop.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtLop.setForeground(new java.awt.Color(36, 43, 54));
        txtLop.setEditable(false);

        javax.swing.GroupLayout pnlTxtLopLayout = new javax.swing.GroupLayout(pnlTxtLop);
        pnlTxtLop.setLayout(pnlTxtLopLayout);
        pnlTxtLopLayout.setHorizontalGroup(
            pnlTxtLopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTxtLop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(txtLop)
        );
        pnlTxtLopLayout.setVerticalGroup(
            pnlTxtLopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtLopLayout.createSequentialGroup()
                .addComponent(lblTxtLop)
                .addGap(8, 8, 8)
                .addComponent(txtLop, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlTxtMonHoc.setBackground(new java.awt.Color(255, 255, 255));

        lblTxtMonHoc.setText("Môn học");
        lblTxtMonHoc.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblTxtMonHoc.setForeground(new java.awt.Color(36, 43, 54));

        txtMonHoc.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMonHoc.setForeground(new java.awt.Color(36, 43, 54));
        txtMonHoc.setEditable(false);

        javax.swing.GroupLayout pnlTxtMonHocLayout = new javax.swing.GroupLayout(pnlTxtMonHoc);
        pnlTxtMonHoc.setLayout(pnlTxtMonHocLayout);
        pnlTxtMonHocLayout.setHorizontalGroup(
            pnlTxtMonHocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTxtMonHoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(txtMonHoc)
        );
        pnlTxtMonHocLayout.setVerticalGroup(
            pnlTxtMonHocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtMonHocLayout.createSequentialGroup()
                .addComponent(lblTxtMonHoc)
                .addGap(8, 8, 8)
                .addComponent(txtMonHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlTxtLanThi.setBackground(new java.awt.Color(255, 255, 255));

        lblTxtLanThi.setText("Lần thi");
        lblTxtLanThi.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblTxtLanThi.setForeground(new java.awt.Color(36, 43, 54));

        txtLanThi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtLanThi.setForeground(new java.awt.Color(36, 43, 54));
        txtLanThi.setEditable(false);

        javax.swing.GroupLayout pnlTxtLanThiLayout = new javax.swing.GroupLayout(pnlTxtLanThi);
        pnlTxtLanThi.setLayout(pnlTxtLanThiLayout);
        pnlTxtLanThiLayout.setHorizontalGroup(
            pnlTxtLanThiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTxtLanThi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(txtLanThi)
        );
        pnlTxtLanThiLayout.setVerticalGroup(
            pnlTxtLanThiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtLanThiLayout.createSequentialGroup()
                .addComponent(lblTxtLanThi)
                .addGap(8, 8, 8)
                .addComponent(txtLanThi, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlTxtNgayThi.setBackground(new java.awt.Color(255, 255, 255));

        lblTxtNgayThi.setText("Ngày thi");
        lblTxtNgayThi.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblTxtNgayThi.setForeground(new java.awt.Color(36, 43, 54));

        txtNgayThi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtNgayThi.setForeground(new java.awt.Color(36, 43, 54));
        txtNgayThi.setEditable(false);

        javax.swing.GroupLayout pnlTxtNgayThiLayout = new javax.swing.GroupLayout(pnlTxtNgayThi);
        pnlTxtNgayThi.setLayout(pnlTxtNgayThiLayout);
        pnlTxtNgayThiLayout.setHorizontalGroup(
            pnlTxtNgayThiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTxtNgayThi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(txtNgayThi)
        );
        pnlTxtNgayThiLayout.setVerticalGroup(
            pnlTxtNgayThiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtNgayThiLayout.createSequentialGroup()
                .addComponent(lblTxtNgayThi)
                .addGap(8, 8, 8)
                .addComponent(txtNgayThi, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        tblBanInBangDiem.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        tblBanInBangDiem.setRowHeight(28);
        tblBanInBangDiem.setAutoCreateRowSorter(true);
        tblBanInBangDiem.setFillsViewportHeight(true);
        tblBanInBangDiem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã sinh viên", "Họ và tên", "Ngày sinh", "Điểm", "Ghi chú"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrTblBanInBangDiem.setViewportView(tblBanInBangDiem);

        lblNgayLap.setText("Ngày ........ tháng ........ năm ........");
        lblNgayLap.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblNgayLap.setForeground(new java.awt.Color(107, 114, 128));

        lblNguoiLap.setText("Người lập bảng");
        lblNguoiLap.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblNguoiLap.setForeground(new java.awt.Color(36, 43, 54));

        lblGiaoVien.setText("Giáo viên");
        lblGiaoVien.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblGiaoVien.setForeground(new java.awt.Color(36, 43, 54));

        lblKyNguoiLap.setText("(Ký, ghi rõ họ tên)");
        lblKyNguoiLap.setForeground(new java.awt.Color(107, 114, 128));

        lblKyGiaoVien.setText("(Ký, ghi rõ họ tên)");
        lblKyGiaoVien.setForeground(new java.awt.Color(107, 114, 128));

        btnIn.setText("In bảng điểm");
        btnIn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnIn.setBackground(new java.awt.Color(205, 32, 45));
        btnIn.setForeground(new java.awt.Color(255, 255, 255));
        btnIn.setOpaque(true);
        btnIn.setFocusPainted(false);
        btnIn.addActionListener(this::btnInActionPerformed);

        btnDong.setText("Đóng");
        btnDong.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnDong.setBackground(new java.awt.Color(245, 246, 248));
        btnDong.setForeground(new java.awt.Color(36, 43, 54));
        btnDong.setOpaque(true);
        btnDong.setFocusPainted(false);
        btnDong.addActionListener(this::btnDongActionPerformed);

        javax.swing.GroupLayout pnlNoiDungLayout = new javax.swing.GroupLayout(pnlNoiDung);
        pnlNoiDung.setLayout(pnlNoiDungLayout);
        pnlNoiDungLayout.setHorizontalGroup(
            pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTieuDe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblMoTa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(pnlTxtLop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(pnlTxtMonHoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(pnlTxtLanThi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(pnlTxtNgayThi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(scrTblBanInBangDiem)
            .addComponent(lblNgayLap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addComponent(btnDong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlNoiDungLayout.setVerticalGroup(
            pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(lblTieuDe)
                .addGap(8, 8, 8)
                .addComponent(lblMoTa)
                .addGap(8, 8, 8)
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlTxtLop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlTxtMonHoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlTxtLanThi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlTxtNgayThi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addComponent(scrTblBanInBangDiem, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(lblNgayLap)
                .addGap(8, 8, 8)
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNguoiLap)
                    .addComponent(lblGiaoVien))
                .addGap(8, 8, 8)
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblKyNguoiLap)
                    .addComponent(lblKyGiaoVien))
                .addGap(8, 8, 8)
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnIn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDong, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
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

    private void btnInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInActionPerformed
        inBang(tblBanInBangDiem, pnlNoiDung, nguoiDung, baoCao);
    }//GEN-LAST:event_btnInActionPerformed

    private void btnDongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDongActionPerformed
        TrangChuGV.moFormCon(this, new BangDiemMonHoc(nguoiDung, baoCao));
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
    private javax.swing.JButton btnDong;
    private javax.swing.JButton btnIn;
    private javax.swing.JLabel lblGiaoVien;
    private javax.swing.JLabel lblKyGiaoVien;
    private javax.swing.JLabel lblKyNguoiLap;
    private javax.swing.JLabel lblMoTa;
    private javax.swing.JLabel lblNgayLap;
    private javax.swing.JLabel lblNguoiLap;
    private javax.swing.JLabel lblTieuDe;
    private javax.swing.JLabel lblTxtLanThi;
    private javax.swing.JLabel lblTxtLop;
    private javax.swing.JLabel lblTxtMonHoc;
    private javax.swing.JLabel lblTxtNgayThi;
    private javax.swing.JPanel pnlNoiDung;
    private javax.swing.JPanel pnlTxtLanThi;
    private javax.swing.JPanel pnlTxtLop;
    private javax.swing.JPanel pnlTxtMonHoc;
    private javax.swing.JPanel pnlTxtNgayThi;
    private javax.swing.JScrollPane scrTblBanInBangDiem;
    private javax.swing.JTable tblBanInBangDiem;
    private javax.swing.JTextField txtLanThi;
    private javax.swing.JTextField txtLop;
    private javax.swing.JTextField txtMonHoc;
    private javax.swing.JTextField txtNgayThi;
    // End of variables declaration//GEN-END:variables
}
