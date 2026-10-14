package baocao;

import session.SessionDangNhap;

/**
 * Giao diện BangDiemMonHoc. Nghiệp vụ sẽ được bổ sung trong các event handler.
 * Chỉnh bố cục bằng tab Design của NetBeans và giữ file .form đi kèm.
 */
public class BangDiemMonHoc extends javax.swing.JFrame {
    private final SessionDangNhap nguoiDung;

    public BangDiemMonHoc() {
        this(null);
    }

    public BangDiemMonHoc(SessionDangNhap nguoiDung) {
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
        lblTieuDe.setText("BẢNG ĐIỂM MÔN HỌC");
        lblTieuDe.setFont(new java.awt.Font("Segoe UI", 1, 24));
        lblTieuDe.setForeground(new java.awt.Color(205, 32, 45));
        lblMoTa = new javax.swing.JLabel();
        lblMoTa.setText("Tra cứu điểm thi hết môn của lớp");
        lblMoTa.setFont(new java.awt.Font("Segoe UI", 0, 13));
        lblMoTa.setForeground(new java.awt.Color(107, 114, 128));
        pnlCboLop = new javax.swing.JPanel();
        pnlCboLop.setBackground(new java.awt.Color(255, 255, 255));
        lblCboLop = new javax.swing.JLabel();
        lblCboLop.setText("Lớp");
        lblCboLop.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblCboLop.setForeground(new java.awt.Color(36, 43, 54));
        cboLop = new javax.swing.JComboBox();
        cboLop.setFont(new java.awt.Font("Segoe UI", 0, 14));
        cboLop.setForeground(new java.awt.Color(36, 43, 54));
        cboLop.setBackground(new java.awt.Color(255, 255, 255));
        cboLop.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"Chọn lớp"}));
        javax.swing.GroupLayout pnlCboLopLayout = new javax.swing.GroupLayout(pnlCboLop);
        pnlCboLop.setLayout(pnlCboLopLayout);
        pnlCboLopLayout.setHorizontalGroup(pnlCboLopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCboLopLayout.createSequentialGroup()
                .addComponent(lblCboLop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlCboLopLayout.createSequentialGroup()
                .addComponent(cboLop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        pnlCboLopLayout.setVerticalGroup(pnlCboLopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCboLopLayout.createSequentialGroup()
                .addGroup(pnlCboLopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCboLop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlCboLopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cboLop, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))));
        pnlCboMonHoc = new javax.swing.JPanel();
        pnlCboMonHoc.setBackground(new java.awt.Color(255, 255, 255));
        lblCboMonHoc = new javax.swing.JLabel();
        lblCboMonHoc.setText("Môn học");
        lblCboMonHoc.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblCboMonHoc.setForeground(new java.awt.Color(36, 43, 54));
        cboMonHoc = new javax.swing.JComboBox();
        cboMonHoc.setFont(new java.awt.Font("Segoe UI", 0, 14));
        cboMonHoc.setForeground(new java.awt.Color(36, 43, 54));
        cboMonHoc.setBackground(new java.awt.Color(255, 255, 255));
        cboMonHoc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"Chọn môn học"}));
        javax.swing.GroupLayout pnlCboMonHocLayout = new javax.swing.GroupLayout(pnlCboMonHoc);
        pnlCboMonHoc.setLayout(pnlCboMonHocLayout);
        pnlCboMonHocLayout.setHorizontalGroup(pnlCboMonHocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCboMonHocLayout.createSequentialGroup()
                .addComponent(lblCboMonHoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlCboMonHocLayout.createSequentialGroup()
                .addComponent(cboMonHoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        pnlCboMonHocLayout.setVerticalGroup(pnlCboMonHocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCboMonHocLayout.createSequentialGroup()
                .addGroup(pnlCboMonHocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCboMonHoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlCboMonHocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cboMonHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))));
        pnlCboLanThi = new javax.swing.JPanel();
        pnlCboLanThi.setBackground(new java.awt.Color(255, 255, 255));
        lblCboLanThi = new javax.swing.JLabel();
        lblCboLanThi.setText("Lần thi");
        lblCboLanThi.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblCboLanThi.setForeground(new java.awt.Color(36, 43, 54));
        cboLanThi = new javax.swing.JComboBox();
        cboLanThi.setFont(new java.awt.Font("Segoe UI", 0, 14));
        cboLanThi.setForeground(new java.awt.Color(36, 43, 54));
        cboLanThi.setBackground(new java.awt.Color(255, 255, 255));
        cboLanThi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"1", "2"}));
        javax.swing.GroupLayout pnlCboLanThiLayout = new javax.swing.GroupLayout(pnlCboLanThi);
        pnlCboLanThi.setLayout(pnlCboLanThiLayout);
        pnlCboLanThiLayout.setHorizontalGroup(pnlCboLanThiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCboLanThiLayout.createSequentialGroup()
                .addComponent(lblCboLanThi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlCboLanThiLayout.createSequentialGroup()
                .addComponent(cboLanThi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        pnlCboLanThiLayout.setVerticalGroup(pnlCboLanThiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCboLanThiLayout.createSequentialGroup()
                .addGroup(pnlCboLanThiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCboLanThi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlCboLanThiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cboLanThi, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))));
        btnXemBangDiem = new javax.swing.JButton();
        btnXemBangDiem.setText("Xem bảng điểm");
        btnXemBangDiem.setFont(new java.awt.Font("Segoe UI", 1, 14));
        btnXemBangDiem.setBackground(new java.awt.Color(205, 32, 45));
        btnXemBangDiem.setForeground(new java.awt.Color(255, 255, 255));
        btnXemBangDiem.setOpaque(true);
        btnXemBangDiem.setContentAreaFilled(true);
        btnXemBangDiem.setFocusPainted(false);
        btnXemBangDiem.addActionListener(this::btnXemBangDiemActionPerformed);
        btnXemBanIn = new javax.swing.JButton();
        btnXemBanIn.setText("Xem bản in");
        btnXemBanIn.setFont(new java.awt.Font("Segoe UI", 0, 14));
        btnXemBanIn.setBackground(new java.awt.Color(245, 246, 248));
        btnXemBanIn.setForeground(new java.awt.Color(36, 43, 54));
        btnXemBanIn.setOpaque(true);
        btnXemBanIn.setContentAreaFilled(true);
        btnXemBanIn.setFocusPainted(false);
        btnXemBanIn.addActionListener(this::btnXemBanInActionPerformed);
        btnIn = new javax.swing.JButton();
        btnIn.setText("In bảng điểm");
        btnIn.setFont(new java.awt.Font("Segoe UI", 0, 14));
        btnIn.setBackground(new java.awt.Color(245, 246, 248));
        btnIn.setForeground(new java.awt.Color(36, 43, 54));
        btnIn.setOpaque(true);
        btnIn.setContentAreaFilled(true);
        btnIn.setFocusPainted(false);
        btnIn.addActionListener(this::btnInActionPerformed);
        scrTblBangDiem = new javax.swing.JScrollPane();
        tblBangDiem = new javax.swing.JTable();
        tblBangDiem.setFont(new java.awt.Font("Segoe UI", 0, 13));
        tblBangDiem.setRowHeight(28);
        tblBangDiem.setAutoCreateRowSorter(true);
        tblBangDiem.setFillsViewportHeight(true);
        tblBangDiem.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {}, new String[] {"STT", "Mã sinh viên", "Họ", "Tên", "Ngày thi", "Lần thi", "Điểm"}) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        });
        scrTblBangDiem.setViewportView(tblBangDiem);
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
        pnlTxtTenMonHoc = new javax.swing.JPanel();
        pnlTxtTenMonHoc.setBackground(new java.awt.Color(255, 255, 255));
        lblTxtTenMonHoc = new javax.swing.JLabel();
        lblTxtTenMonHoc.setText("Tên môn học");
        lblTxtTenMonHoc.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblTxtTenMonHoc.setForeground(new java.awt.Color(36, 43, 54));
        txtTenMonHoc = new javax.swing.JTextField();
        txtTenMonHoc.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtTenMonHoc.setForeground(new java.awt.Color(36, 43, 54));
        txtTenMonHoc.setBackground(new java.awt.Color(255, 255, 255));
        txtTenMonHoc.setEditable(false);
        javax.swing.GroupLayout pnlTxtTenMonHocLayout = new javax.swing.GroupLayout(pnlTxtTenMonHoc);
        pnlTxtTenMonHoc.setLayout(pnlTxtTenMonHocLayout);
        pnlTxtTenMonHocLayout.setHorizontalGroup(pnlTxtTenMonHocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtTenMonHocLayout.createSequentialGroup()
                .addComponent(lblTxtTenMonHoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlTxtTenMonHocLayout.createSequentialGroup()
                .addComponent(txtTenMonHoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        pnlTxtTenMonHocLayout.setVerticalGroup(pnlTxtTenMonHocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtTenMonHocLayout.createSequentialGroup()
                .addGroup(pnlTxtTenMonHocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTxtTenMonHoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlTxtTenMonHocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTenMonHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))));
        pnlTxtSoSinhVien = new javax.swing.JPanel();
        pnlTxtSoSinhVien.setBackground(new java.awt.Color(255, 255, 255));
        lblTxtSoSinhVien = new javax.swing.JLabel();
        lblTxtSoSinhVien.setText("Số sinh viên");
        lblTxtSoSinhVien.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblTxtSoSinhVien.setForeground(new java.awt.Color(36, 43, 54));
        txtSoSinhVien = new javax.swing.JTextField();
        txtSoSinhVien.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtSoSinhVien.setForeground(new java.awt.Color(36, 43, 54));
        txtSoSinhVien.setBackground(new java.awt.Color(255, 255, 255));
        txtSoSinhVien.setEditable(false);
        javax.swing.GroupLayout pnlTxtSoSinhVienLayout = new javax.swing.GroupLayout(pnlTxtSoSinhVien);
        pnlTxtSoSinhVien.setLayout(pnlTxtSoSinhVienLayout);
        pnlTxtSoSinhVienLayout.setHorizontalGroup(pnlTxtSoSinhVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtSoSinhVienLayout.createSequentialGroup()
                .addComponent(lblTxtSoSinhVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlTxtSoSinhVienLayout.createSequentialGroup()
                .addComponent(txtSoSinhVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        pnlTxtSoSinhVienLayout.setVerticalGroup(pnlTxtSoSinhVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtSoSinhVienLayout.createSequentialGroup()
                .addGroup(pnlTxtSoSinhVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTxtSoSinhVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlTxtSoSinhVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSoSinhVien, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))));
        javax.swing.GroupLayout pnlNoiDungLayout = new javax.swing.GroupLayout(pnlNoiDung);
        pnlNoiDung.setLayout(pnlNoiDungLayout);
        pnlNoiDungLayout.setHorizontalGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(lblTieuDe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(lblMoTa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(pnlCboLop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(pnlCboMonHoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(pnlCboLanThi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(btnXemBangDiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(btnXemBanIn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(btnIn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(scrTblBangDiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(pnlTxtTenLop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(pnlTxtTenMonHoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(pnlTxtSoSinhVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        pnlNoiDungLayout.setVerticalGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTieuDe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMoTa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlCboLop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlCboMonHoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlCboLanThi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnXemBangDiem, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXemBanIn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnIn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrTblBangDiem, javax.swing.GroupLayout.PREFERRED_SIZE, 350, Short.MAX_VALUE))
                .addGap(8, 8, 8)
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlTxtTenLop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlTxtTenMonHoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlTxtSoSinhVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))));
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
        setTitle("PTIT | BẢNG ĐIỂM MÔN HỌC");
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/ptit-logo.png")).getImage());
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnXemBangDiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemBangDiemActionPerformed
        // TODO: Bổ sung xử lý cho nút này sau khi hoàn thành giao diện.
    }//GEN-LAST:event_btnXemBangDiemActionPerformed

    private void btnXemBanInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemBanInActionPerformed
        trangchu.DieuHuong.moForm(this, new baocao.InBangDiem(nguoiDung), nguoiDung);
    }//GEN-LAST:event_btnXemBanInActionPerformed

    private void btnInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInActionPerformed
        // TODO: Bổ sung xử lý cho nút này sau khi hoàn thành giao diện.
    }//GEN-LAST:event_btnInActionPerformed

    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BangDiemMonHoc.class.getName()).log(java.util.logging.Level.WARNING, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> new BangDiemMonHoc().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel pnlNoiDung;
    private javax.swing.JLabel lblTieuDe;
    private javax.swing.JLabel lblMoTa;
    private javax.swing.JPanel pnlCboLop;
    private javax.swing.JLabel lblCboLop;
    private javax.swing.JComboBox cboLop;
    private javax.swing.JPanel pnlCboMonHoc;
    private javax.swing.JLabel lblCboMonHoc;
    private javax.swing.JComboBox cboMonHoc;
    private javax.swing.JPanel pnlCboLanThi;
    private javax.swing.JLabel lblCboLanThi;
    private javax.swing.JComboBox cboLanThi;
    private javax.swing.JButton btnXemBangDiem;
    private javax.swing.JButton btnXemBanIn;
    private javax.swing.JButton btnIn;
    private javax.swing.JScrollPane scrTblBangDiem;
    private javax.swing.JTable tblBangDiem;
    private javax.swing.JPanel pnlTxtTenLop;
    private javax.swing.JLabel lblTxtTenLop;
    private javax.swing.JTextField txtTenLop;
    private javax.swing.JPanel pnlTxtTenMonHoc;
    private javax.swing.JLabel lblTxtTenMonHoc;
    private javax.swing.JTextField txtTenMonHoc;
    private javax.swing.JPanel pnlTxtSoSinhVien;
    private javax.swing.JLabel lblTxtSoSinhVien;
    private javax.swing.JTextField txtSoSinhVien;
    // End of variables declaration//GEN-END:variables
}
