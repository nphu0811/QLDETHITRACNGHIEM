package quanly;

import session.SessionDangNhap;

/**
 * Giao diện NhapLopSinhVien. Nghiệp vụ sẽ được bổ sung trong các event handler.
 * Chỉnh bố cục bằng tab Design của NetBeans và giữ file .form đi kèm.
 */
public class NhapLopSinhVien extends javax.swing.JFrame {
    private final SessionDangNhap nguoiDung;

    public NhapLopSinhVien() {
        this(null);
    }

    public NhapLopSinhVien(SessionDangNhap nguoiDung) {
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
        lblTieuDe.setText("QUẢN LÝ LỚP VÀ SINH VIÊN");
        lblTieuDe.setFont(new java.awt.Font("Segoe UI", 1, 24));
        lblTieuDe.setForeground(new java.awt.Color(205, 32, 45));
        lblMoTa = new javax.swing.JLabel();
        lblMoTa.setText("Thông tin lớp học và danh sách sinh viên của lớp");
        lblMoTa.setFont(new java.awt.Font("Segoe UI", 0, 13));
        lblMoTa.setForeground(new java.awt.Color(107, 114, 128));
        pnlLop = new javax.swing.JPanel();
        pnlLop.setBackground(new java.awt.Color(255, 255, 255));
        lblLop = new javax.swing.JLabel();
        lblLop.setText("Danh sách lớp");
        lblLop.setFont(new java.awt.Font("Segoe UI", 1, 16));
        lblLop.setForeground(new java.awt.Color(205, 32, 45));
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
        txtMaLop.setEditable(true);
        txtMaLop.setToolTipText("MALOP · tối đa 8 ký tự");
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
        txtTenLop.setEditable(true);
        txtTenLop.setToolTipText("TENLOP · tối đa 40 ký tự");
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
        pnlTxtTimLop = new javax.swing.JPanel();
        pnlTxtTimLop.setBackground(new java.awt.Color(255, 255, 255));
        lblTxtTimLop = new javax.swing.JLabel();
        lblTxtTimLop.setText("Tìm lớp");
        lblTxtTimLop.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblTxtTimLop.setForeground(new java.awt.Color(36, 43, 54));
        txtTimLop = new javax.swing.JTextField();
        txtTimLop.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtTimLop.setForeground(new java.awt.Color(36, 43, 54));
        txtTimLop.setBackground(new java.awt.Color(255, 255, 255));
        txtTimLop.setEditable(true);
        javax.swing.GroupLayout pnlTxtTimLopLayout = new javax.swing.GroupLayout(pnlTxtTimLop);
        pnlTxtTimLop.setLayout(pnlTxtTimLopLayout);
        pnlTxtTimLopLayout.setHorizontalGroup(pnlTxtTimLopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtTimLopLayout.createSequentialGroup()
                .addComponent(lblTxtTimLop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlTxtTimLopLayout.createSequentialGroup()
                .addComponent(txtTimLop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        pnlTxtTimLopLayout.setVerticalGroup(pnlTxtTimLopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtTimLopLayout.createSequentialGroup()
                .addGroup(pnlTxtTimLopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTxtTimLop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlTxtTimLopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTimLop, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))));
        btnLopThem = new javax.swing.JButton();
        btnLopThem.setText("Thêm");
        btnLopThem.setFont(new java.awt.Font("Segoe UI", 0, 14));
        btnLopThem.setBackground(new java.awt.Color(245, 246, 248));
        btnLopThem.setForeground(new java.awt.Color(36, 43, 54));
        btnLopThem.setOpaque(true);
        btnLopThem.setContentAreaFilled(true);
        btnLopThem.setFocusPainted(false);
        btnLopThem.addActionListener(this::btnLopThemActionPerformed);
        btnLopXoa = new javax.swing.JButton();
        btnLopXoa.setText("Xóa");
        btnLopXoa.setFont(new java.awt.Font("Segoe UI", 0, 14));
        btnLopXoa.setBackground(new java.awt.Color(245, 246, 248));
        btnLopXoa.setForeground(new java.awt.Color(36, 43, 54));
        btnLopXoa.setOpaque(true);
        btnLopXoa.setContentAreaFilled(true);
        btnLopXoa.setFocusPainted(false);
        btnLopXoa.addActionListener(this::btnLopXoaActionPerformed);
        btnLopHieuChinh = new javax.swing.JButton();
        btnLopHieuChinh.setText("Hiệu chỉnh");
        btnLopHieuChinh.setFont(new java.awt.Font("Segoe UI", 0, 14));
        btnLopHieuChinh.setBackground(new java.awt.Color(245, 246, 248));
        btnLopHieuChinh.setForeground(new java.awt.Color(36, 43, 54));
        btnLopHieuChinh.setOpaque(true);
        btnLopHieuChinh.setContentAreaFilled(true);
        btnLopHieuChinh.setFocusPainted(false);
        btnLopHieuChinh.addActionListener(this::btnLopHieuChinhActionPerformed);
        btnLopPhucHoi = new javax.swing.JButton();
        btnLopPhucHoi.setText("Phục hồi");
        btnLopPhucHoi.setFont(new java.awt.Font("Segoe UI", 0, 14));
        btnLopPhucHoi.setBackground(new java.awt.Color(245, 246, 248));
        btnLopPhucHoi.setForeground(new java.awt.Color(36, 43, 54));
        btnLopPhucHoi.setOpaque(true);
        btnLopPhucHoi.setContentAreaFilled(true);
        btnLopPhucHoi.setFocusPainted(false);
        btnLopPhucHoi.addActionListener(this::btnLopPhucHoiActionPerformed);
        btnLopTim = new javax.swing.JButton();
        btnLopTim.setText("Tìm");
        btnLopTim.setFont(new java.awt.Font("Segoe UI", 0, 14));
        btnLopTim.setBackground(new java.awt.Color(245, 246, 248));
        btnLopTim.setForeground(new java.awt.Color(36, 43, 54));
        btnLopTim.setOpaque(true);
        btnLopTim.setContentAreaFilled(true);
        btnLopTim.setFocusPainted(false);
        btnLopTim.addActionListener(this::btnLopTimActionPerformed);
        btnLopGhi = new javax.swing.JButton();
        btnLopGhi.setText("Ghi");
        btnLopGhi.setFont(new java.awt.Font("Segoe UI", 1, 14));
        btnLopGhi.setBackground(new java.awt.Color(205, 32, 45));
        btnLopGhi.setForeground(new java.awt.Color(255, 255, 255));
        btnLopGhi.setOpaque(true);
        btnLopGhi.setContentAreaFilled(true);
        btnLopGhi.setFocusPainted(false);
        btnLopGhi.addActionListener(this::btnLopGhiActionPerformed);
        scrTblLop = new javax.swing.JScrollPane();
        tblLop = new javax.swing.JTable();
        tblLop.setFont(new java.awt.Font("Segoe UI", 0, 13));
        tblLop.setRowHeight(28);
        tblLop.setAutoCreateRowSorter(true);
        tblLop.setFillsViewportHeight(true);
        tblLop.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {}, new String[] {"Mã lớp", "Tên lớp"}) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        });
        scrTblLop.setViewportView(tblLop);
        javax.swing.GroupLayout pnlLopLayout = new javax.swing.GroupLayout(pnlLop);
        pnlLop.setLayout(pnlLopLayout);
        pnlLopLayout.setHorizontalGroup(pnlLopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLopLayout.createSequentialGroup()
                .addComponent(lblLop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlLopLayout.createSequentialGroup()
                .addComponent(pnlTxtMaLop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(pnlTxtTenLop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlLopLayout.createSequentialGroup()
                .addComponent(pnlTxtTimLop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlLopLayout.createSequentialGroup()
                .addComponent(btnLopThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(btnLopXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(btnLopHieuChinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(btnLopPhucHoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(btnLopTim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(btnLopGhi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlLopLayout.createSequentialGroup()
                .addComponent(scrTblLop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        pnlLopLayout.setVerticalGroup(pnlLopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLopLayout.createSequentialGroup()
                .addGroup(pnlLopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblLop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlLopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlTxtMaLop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlTxtTenLop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlLopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlTxtTimLop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlLopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLopThem, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLopXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLopHieuChinh, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLopPhucHoi, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLopTim, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLopGhi, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlLopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrTblLop, javax.swing.GroupLayout.PREFERRED_SIZE, 240, Short.MAX_VALUE))));
        pnlSinhVien = new javax.swing.JPanel();
        pnlSinhVien.setBackground(new java.awt.Color(255, 255, 255));
        lblSinhVien = new javax.swing.JLabel();
        lblSinhVien.setText("Sinh viên của lớp");
        lblSinhVien.setFont(new java.awt.Font("Segoe UI", 1, 16));
        lblSinhVien.setForeground(new java.awt.Color(205, 32, 45));
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
        txtMaSV.setEditable(true);
        txtMaSV.setToolTipText("MASV · tối đa 8 ký tự");
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
        pnlTxtLopSV = new javax.swing.JPanel();
        pnlTxtLopSV.setBackground(new java.awt.Color(255, 255, 255));
        lblTxtLopSV = new javax.swing.JLabel();
        lblTxtLopSV.setText("Mã lớp");
        lblTxtLopSV.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblTxtLopSV.setForeground(new java.awt.Color(36, 43, 54));
        txtLopSV = new javax.swing.JTextField();
        txtLopSV.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtLopSV.setForeground(new java.awt.Color(36, 43, 54));
        txtLopSV.setBackground(new java.awt.Color(255, 255, 255));
        txtLopSV.setEditable(false);
        javax.swing.GroupLayout pnlTxtLopSVLayout = new javax.swing.GroupLayout(pnlTxtLopSV);
        pnlTxtLopSV.setLayout(pnlTxtLopSVLayout);
        pnlTxtLopSVLayout.setHorizontalGroup(pnlTxtLopSVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtLopSVLayout.createSequentialGroup()
                .addComponent(lblTxtLopSV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlTxtLopSVLayout.createSequentialGroup()
                .addComponent(txtLopSV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        pnlTxtLopSVLayout.setVerticalGroup(pnlTxtLopSVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtLopSVLayout.createSequentialGroup()
                .addGroup(pnlTxtLopSVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTxtLopSV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlTxtLopSVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtLopSV, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))));
        pnlTxtHoSV = new javax.swing.JPanel();
        pnlTxtHoSV.setBackground(new java.awt.Color(255, 255, 255));
        lblTxtHoSV = new javax.swing.JLabel();
        lblTxtHoSV.setText("Họ");
        lblTxtHoSV.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblTxtHoSV.setForeground(new java.awt.Color(36, 43, 54));
        txtHoSV = new javax.swing.JTextField();
        txtHoSV.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtHoSV.setForeground(new java.awt.Color(36, 43, 54));
        txtHoSV.setBackground(new java.awt.Color(255, 255, 255));
        txtHoSV.setEditable(true);
        txtHoSV.setToolTipText("HO · tối đa 40 ký tự");
        javax.swing.GroupLayout pnlTxtHoSVLayout = new javax.swing.GroupLayout(pnlTxtHoSV);
        pnlTxtHoSV.setLayout(pnlTxtHoSVLayout);
        pnlTxtHoSVLayout.setHorizontalGroup(pnlTxtHoSVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtHoSVLayout.createSequentialGroup()
                .addComponent(lblTxtHoSV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlTxtHoSVLayout.createSequentialGroup()
                .addComponent(txtHoSV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        pnlTxtHoSVLayout.setVerticalGroup(pnlTxtHoSVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtHoSVLayout.createSequentialGroup()
                .addGroup(pnlTxtHoSVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTxtHoSV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlTxtHoSVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtHoSV, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))));
        pnlTxtTenSV = new javax.swing.JPanel();
        pnlTxtTenSV.setBackground(new java.awt.Color(255, 255, 255));
        lblTxtTenSV = new javax.swing.JLabel();
        lblTxtTenSV.setText("Tên");
        lblTxtTenSV.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblTxtTenSV.setForeground(new java.awt.Color(36, 43, 54));
        txtTenSV = new javax.swing.JTextField();
        txtTenSV.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtTenSV.setForeground(new java.awt.Color(36, 43, 54));
        txtTenSV.setBackground(new java.awt.Color(255, 255, 255));
        txtTenSV.setEditable(true);
        txtTenSV.setToolTipText("TEN · tối đa 10 ký tự");
        javax.swing.GroupLayout pnlTxtTenSVLayout = new javax.swing.GroupLayout(pnlTxtTenSV);
        pnlTxtTenSV.setLayout(pnlTxtTenSVLayout);
        pnlTxtTenSVLayout.setHorizontalGroup(pnlTxtTenSVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtTenSVLayout.createSequentialGroup()
                .addComponent(lblTxtTenSV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlTxtTenSVLayout.createSequentialGroup()
                .addComponent(txtTenSV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        pnlTxtTenSVLayout.setVerticalGroup(pnlTxtTenSVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtTenSVLayout.createSequentialGroup()
                .addGroup(pnlTxtTenSVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTxtTenSV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlTxtTenSVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTenSV, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))));
        pnlTxtNgaySinh = new javax.swing.JPanel();
        pnlTxtNgaySinh.setBackground(new java.awt.Color(255, 255, 255));
        lblTxtNgaySinh = new javax.swing.JLabel();
        lblTxtNgaySinh.setText("Ngày sinh");
        lblTxtNgaySinh.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblTxtNgaySinh.setForeground(new java.awt.Color(36, 43, 54));
        txtNgaySinh = new javax.swing.JFormattedTextField();
        txtNgaySinh.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtNgaySinh.setForeground(new java.awt.Color(36, 43, 54));
        txtNgaySinh.setBackground(new java.awt.Color(255, 255, 255));
        txtNgaySinh.setEditable(true);
        txtNgaySinh.setToolTipText("dd/MM/yyyy");
        javax.swing.GroupLayout pnlTxtNgaySinhLayout = new javax.swing.GroupLayout(pnlTxtNgaySinh);
        pnlTxtNgaySinh.setLayout(pnlTxtNgaySinhLayout);
        pnlTxtNgaySinhLayout.setHorizontalGroup(pnlTxtNgaySinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtNgaySinhLayout.createSequentialGroup()
                .addComponent(lblTxtNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlTxtNgaySinhLayout.createSequentialGroup()
                .addComponent(txtNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        pnlTxtNgaySinhLayout.setVerticalGroup(pnlTxtNgaySinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtNgaySinhLayout.createSequentialGroup()
                .addGroup(pnlTxtNgaySinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTxtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlTxtNgaySinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))));
        pnlTxtDiaChiSV = new javax.swing.JPanel();
        pnlTxtDiaChiSV.setBackground(new java.awt.Color(255, 255, 255));
        lblTxtDiaChiSV = new javax.swing.JLabel();
        lblTxtDiaChiSV.setText("Địa chỉ");
        lblTxtDiaChiSV.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblTxtDiaChiSV.setForeground(new java.awt.Color(36, 43, 54));
        txtDiaChiSV = new javax.swing.JTextField();
        txtDiaChiSV.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtDiaChiSV.setForeground(new java.awt.Color(36, 43, 54));
        txtDiaChiSV.setBackground(new java.awt.Color(255, 255, 255));
        txtDiaChiSV.setEditable(true);
        txtDiaChiSV.setToolTipText("DIACHI · tối đa 40 ký tự");
        javax.swing.GroupLayout pnlTxtDiaChiSVLayout = new javax.swing.GroupLayout(pnlTxtDiaChiSV);
        pnlTxtDiaChiSV.setLayout(pnlTxtDiaChiSVLayout);
        pnlTxtDiaChiSVLayout.setHorizontalGroup(pnlTxtDiaChiSVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtDiaChiSVLayout.createSequentialGroup()
                .addComponent(lblTxtDiaChiSV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlTxtDiaChiSVLayout.createSequentialGroup()
                .addComponent(txtDiaChiSV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        pnlTxtDiaChiSVLayout.setVerticalGroup(pnlTxtDiaChiSVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtDiaChiSVLayout.createSequentialGroup()
                .addGroup(pnlTxtDiaChiSVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTxtDiaChiSV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlTxtDiaChiSVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDiaChiSV, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))));
        pnlTxtTimSV = new javax.swing.JPanel();
        pnlTxtTimSV.setBackground(new java.awt.Color(255, 255, 255));
        lblTxtTimSV = new javax.swing.JLabel();
        lblTxtTimSV.setText("Tìm sinh viên");
        lblTxtTimSV.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblTxtTimSV.setForeground(new java.awt.Color(36, 43, 54));
        txtTimSV = new javax.swing.JTextField();
        txtTimSV.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtTimSV.setForeground(new java.awt.Color(36, 43, 54));
        txtTimSV.setBackground(new java.awt.Color(255, 255, 255));
        txtTimSV.setEditable(true);
        javax.swing.GroupLayout pnlTxtTimSVLayout = new javax.swing.GroupLayout(pnlTxtTimSV);
        pnlTxtTimSV.setLayout(pnlTxtTimSVLayout);
        pnlTxtTimSVLayout.setHorizontalGroup(pnlTxtTimSVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtTimSVLayout.createSequentialGroup()
                .addComponent(lblTxtTimSV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlTxtTimSVLayout.createSequentialGroup()
                .addComponent(txtTimSV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        pnlTxtTimSVLayout.setVerticalGroup(pnlTxtTimSVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtTimSVLayout.createSequentialGroup()
                .addGroup(pnlTxtTimSVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTxtTimSV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlTxtTimSVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTimSV, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))));
        btnSVThem = new javax.swing.JButton();
        btnSVThem.setText("Thêm");
        btnSVThem.setFont(new java.awt.Font("Segoe UI", 0, 14));
        btnSVThem.setBackground(new java.awt.Color(245, 246, 248));
        btnSVThem.setForeground(new java.awt.Color(36, 43, 54));
        btnSVThem.setOpaque(true);
        btnSVThem.setContentAreaFilled(true);
        btnSVThem.setFocusPainted(false);
        btnSVThem.addActionListener(this::btnSVThemActionPerformed);
        btnSVXoa = new javax.swing.JButton();
        btnSVXoa.setText("Xóa");
        btnSVXoa.setFont(new java.awt.Font("Segoe UI", 0, 14));
        btnSVXoa.setBackground(new java.awt.Color(245, 246, 248));
        btnSVXoa.setForeground(new java.awt.Color(36, 43, 54));
        btnSVXoa.setOpaque(true);
        btnSVXoa.setContentAreaFilled(true);
        btnSVXoa.setFocusPainted(false);
        btnSVXoa.addActionListener(this::btnSVXoaActionPerformed);
        btnSVHieuChinh = new javax.swing.JButton();
        btnSVHieuChinh.setText("Hiệu chỉnh");
        btnSVHieuChinh.setFont(new java.awt.Font("Segoe UI", 0, 14));
        btnSVHieuChinh.setBackground(new java.awt.Color(245, 246, 248));
        btnSVHieuChinh.setForeground(new java.awt.Color(36, 43, 54));
        btnSVHieuChinh.setOpaque(true);
        btnSVHieuChinh.setContentAreaFilled(true);
        btnSVHieuChinh.setFocusPainted(false);
        btnSVHieuChinh.addActionListener(this::btnSVHieuChinhActionPerformed);
        btnSVPhucHoi = new javax.swing.JButton();
        btnSVPhucHoi.setText("Phục hồi");
        btnSVPhucHoi.setFont(new java.awt.Font("Segoe UI", 0, 14));
        btnSVPhucHoi.setBackground(new java.awt.Color(245, 246, 248));
        btnSVPhucHoi.setForeground(new java.awt.Color(36, 43, 54));
        btnSVPhucHoi.setOpaque(true);
        btnSVPhucHoi.setContentAreaFilled(true);
        btnSVPhucHoi.setFocusPainted(false);
        btnSVPhucHoi.addActionListener(this::btnSVPhucHoiActionPerformed);
        btnSVTim = new javax.swing.JButton();
        btnSVTim.setText("Tìm");
        btnSVTim.setFont(new java.awt.Font("Segoe UI", 0, 14));
        btnSVTim.setBackground(new java.awt.Color(245, 246, 248));
        btnSVTim.setForeground(new java.awt.Color(36, 43, 54));
        btnSVTim.setOpaque(true);
        btnSVTim.setContentAreaFilled(true);
        btnSVTim.setFocusPainted(false);
        btnSVTim.addActionListener(this::btnSVTimActionPerformed);
        btnSVGhi = new javax.swing.JButton();
        btnSVGhi.setText("Ghi");
        btnSVGhi.setFont(new java.awt.Font("Segoe UI", 1, 14));
        btnSVGhi.setBackground(new java.awt.Color(205, 32, 45));
        btnSVGhi.setForeground(new java.awt.Color(255, 255, 255));
        btnSVGhi.setOpaque(true);
        btnSVGhi.setContentAreaFilled(true);
        btnSVGhi.setFocusPainted(false);
        btnSVGhi.addActionListener(this::btnSVGhiActionPerformed);
        scrTblSinhVien = new javax.swing.JScrollPane();
        tblSinhVien = new javax.swing.JTable();
        tblSinhVien.setFont(new java.awt.Font("Segoe UI", 0, 13));
        tblSinhVien.setRowHeight(28);
        tblSinhVien.setAutoCreateRowSorter(true);
        tblSinhVien.setFillsViewportHeight(true);
        tblSinhVien.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {}, new String[] {"Mã SV", "Họ", "Tên", "Ngày sinh", "Địa chỉ", "Mã lớp"}) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        });
        scrTblSinhVien.setViewportView(tblSinhVien);
        javax.swing.GroupLayout pnlSinhVienLayout = new javax.swing.GroupLayout(pnlSinhVien);
        pnlSinhVien.setLayout(pnlSinhVienLayout);
        pnlSinhVienLayout.setHorizontalGroup(pnlSinhVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSinhVienLayout.createSequentialGroup()
                .addComponent(lblSinhVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlSinhVienLayout.createSequentialGroup()
                .addComponent(pnlTxtMaSV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(pnlTxtLopSV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlSinhVienLayout.createSequentialGroup()
                .addComponent(pnlTxtHoSV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(pnlTxtTenSV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlSinhVienLayout.createSequentialGroup()
                .addComponent(pnlTxtNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(pnlTxtDiaChiSV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlSinhVienLayout.createSequentialGroup()
                .addComponent(pnlTxtTimSV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlSinhVienLayout.createSequentialGroup()
                .addComponent(btnSVThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(btnSVXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(btnSVHieuChinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(btnSVPhucHoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(btnSVTim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(btnSVGhi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlSinhVienLayout.createSequentialGroup()
                .addComponent(scrTblSinhVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        pnlSinhVienLayout.setVerticalGroup(pnlSinhVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSinhVienLayout.createSequentialGroup()
                .addGroup(pnlSinhVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSinhVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlSinhVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlTxtMaSV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlTxtLopSV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlSinhVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlTxtHoSV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlTxtTenSV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlSinhVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlTxtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlTxtDiaChiSV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlSinhVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlTxtTimSV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlSinhVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSVThem, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSVXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSVHieuChinh, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSVPhucHoi, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSVTim, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSVGhi, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlSinhVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrTblSinhVien, javax.swing.GroupLayout.PREFERRED_SIZE, 160, Short.MAX_VALUE))));
        javax.swing.GroupLayout pnlNoiDungLayout = new javax.swing.GroupLayout(pnlNoiDung);
        pnlNoiDung.setLayout(pnlNoiDungLayout);
        pnlNoiDungLayout.setHorizontalGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(lblTieuDe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(lblMoTa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(pnlLop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(pnlSinhVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        pnlNoiDungLayout.setVerticalGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTieuDe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMoTa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlLop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlSinhVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))));
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(pnlNoiDung, javax.swing.GroupLayout.DEFAULT_SIZE, 1160, Short.MAX_VALUE)
                .addGap(16, 16, 16)));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlNoiDung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)));
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PTIT | QUẢN LÝ LỚP VÀ SINH VIÊN");
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/ptit-logo.png")).getImage());
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLopThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLopThemActionPerformed
        // TODO: Bổ sung xử lý cho nút này sau khi hoàn thành giao diện.
    }//GEN-LAST:event_btnLopThemActionPerformed

    private void btnLopXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLopXoaActionPerformed
        // TODO: Bổ sung xử lý cho nút này sau khi hoàn thành giao diện.
    }//GEN-LAST:event_btnLopXoaActionPerformed

    private void btnLopHieuChinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLopHieuChinhActionPerformed
        // TODO: Bổ sung xử lý cho nút này sau khi hoàn thành giao diện.
    }//GEN-LAST:event_btnLopHieuChinhActionPerformed

    private void btnLopPhucHoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLopPhucHoiActionPerformed
        // TODO: Bổ sung xử lý cho nút này sau khi hoàn thành giao diện.
    }//GEN-LAST:event_btnLopPhucHoiActionPerformed

    private void btnLopTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLopTimActionPerformed
        // TODO: Bổ sung xử lý cho nút này sau khi hoàn thành giao diện.
    }//GEN-LAST:event_btnLopTimActionPerformed

    private void btnLopGhiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLopGhiActionPerformed
        // TODO: Bổ sung xử lý cho nút này sau khi hoàn thành giao diện.
    }//GEN-LAST:event_btnLopGhiActionPerformed

    private void btnSVThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSVThemActionPerformed
        // TODO: Bổ sung xử lý cho nút này sau khi hoàn thành giao diện.
    }//GEN-LAST:event_btnSVThemActionPerformed

    private void btnSVXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSVXoaActionPerformed
        // TODO: Bổ sung xử lý cho nút này sau khi hoàn thành giao diện.
    }//GEN-LAST:event_btnSVXoaActionPerformed

    private void btnSVHieuChinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSVHieuChinhActionPerformed
        // TODO: Bổ sung xử lý cho nút này sau khi hoàn thành giao diện.
    }//GEN-LAST:event_btnSVHieuChinhActionPerformed

    private void btnSVPhucHoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSVPhucHoiActionPerformed
        // TODO: Bổ sung xử lý cho nút này sau khi hoàn thành giao diện.
    }//GEN-LAST:event_btnSVPhucHoiActionPerformed

    private void btnSVTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSVTimActionPerformed
        // TODO: Bổ sung xử lý cho nút này sau khi hoàn thành giao diện.
    }//GEN-LAST:event_btnSVTimActionPerformed

    private void btnSVGhiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSVGhiActionPerformed
        // TODO: Bổ sung xử lý cho nút này sau khi hoàn thành giao diện.
    }//GEN-LAST:event_btnSVGhiActionPerformed

    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NhapLopSinhVien.class.getName()).log(java.util.logging.Level.WARNING, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> new NhapLopSinhVien().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel pnlNoiDung;
    private javax.swing.JLabel lblTieuDe;
    private javax.swing.JLabel lblMoTa;
    private javax.swing.JPanel pnlLop;
    private javax.swing.JLabel lblLop;
    private javax.swing.JPanel pnlTxtMaLop;
    private javax.swing.JLabel lblTxtMaLop;
    private javax.swing.JTextField txtMaLop;
    private javax.swing.JPanel pnlTxtTenLop;
    private javax.swing.JLabel lblTxtTenLop;
    private javax.swing.JTextField txtTenLop;
    private javax.swing.JPanel pnlTxtTimLop;
    private javax.swing.JLabel lblTxtTimLop;
    private javax.swing.JTextField txtTimLop;
    private javax.swing.JButton btnLopThem;
    private javax.swing.JButton btnLopXoa;
    private javax.swing.JButton btnLopHieuChinh;
    private javax.swing.JButton btnLopPhucHoi;
    private javax.swing.JButton btnLopTim;
    private javax.swing.JButton btnLopGhi;
    private javax.swing.JScrollPane scrTblLop;
    private javax.swing.JTable tblLop;
    private javax.swing.JPanel pnlSinhVien;
    private javax.swing.JLabel lblSinhVien;
    private javax.swing.JPanel pnlTxtMaSV;
    private javax.swing.JLabel lblTxtMaSV;
    private javax.swing.JTextField txtMaSV;
    private javax.swing.JPanel pnlTxtLopSV;
    private javax.swing.JLabel lblTxtLopSV;
    private javax.swing.JTextField txtLopSV;
    private javax.swing.JPanel pnlTxtHoSV;
    private javax.swing.JLabel lblTxtHoSV;
    private javax.swing.JTextField txtHoSV;
    private javax.swing.JPanel pnlTxtTenSV;
    private javax.swing.JLabel lblTxtTenSV;
    private javax.swing.JTextField txtTenSV;
    private javax.swing.JPanel pnlTxtNgaySinh;
    private javax.swing.JLabel lblTxtNgaySinh;
    private javax.swing.JFormattedTextField txtNgaySinh;
    private javax.swing.JPanel pnlTxtDiaChiSV;
    private javax.swing.JLabel lblTxtDiaChiSV;
    private javax.swing.JTextField txtDiaChiSV;
    private javax.swing.JPanel pnlTxtTimSV;
    private javax.swing.JLabel lblTxtTimSV;
    private javax.swing.JTextField txtTimSV;
    private javax.swing.JButton btnSVThem;
    private javax.swing.JButton btnSVXoa;
    private javax.swing.JButton btnSVHieuChinh;
    private javax.swing.JButton btnSVPhucHoi;
    private javax.swing.JButton btnSVTim;
    private javax.swing.JButton btnSVGhi;
    private javax.swing.JScrollPane scrTblSinhVien;
    private javax.swing.JTable tblSinhVien;
    // End of variables declaration//GEN-END:variables
}
