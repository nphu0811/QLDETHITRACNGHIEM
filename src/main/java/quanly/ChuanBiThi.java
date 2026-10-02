package quanly;

import session.SessionDangNhap;

/**
 * Giao diện ChuanBiThi. Nghiệp vụ sẽ được bổ sung trong các event handler.
 * Chỉnh bố cục bằng tab Design của NetBeans và giữ file .form đi kèm.
 */
public class ChuanBiThi extends javax.swing.JFrame {
    private final SessionDangNhap nguoiDung;

    public ChuanBiThi() {
        this(null);
    }

    public ChuanBiThi(SessionDangNhap nguoiDung) {
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
        lblTieuDe.setText("CHUẨN BỊ THI");
        lblTieuDe.setFont(new java.awt.Font("Segoe UI", 1, 24));
        lblTieuDe.setForeground(new java.awt.Color(205, 32, 45));
        lblMoTa = new javax.swing.JLabel();
        lblMoTa.setText("Đăng ký môn thi và cấu hình bài thi cho lớp");
        lblMoTa.setFont(new java.awt.Font("Segoe UI", 0, 13));
        lblMoTa.setForeground(new java.awt.Color(107, 114, 128));
        pnlTxtMaGV = new javax.swing.JPanel();
        pnlTxtMaGV.setBackground(new java.awt.Color(255, 255, 255));
        lblTxtMaGV = new javax.swing.JLabel();
        lblTxtMaGV.setText("Mã giáo viên");
        lblTxtMaGV.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblTxtMaGV.setForeground(new java.awt.Color(36, 43, 54));
        txtMaGV = new javax.swing.JTextField();
        txtMaGV.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtMaGV.setForeground(new java.awt.Color(36, 43, 54));
        txtMaGV.setBackground(new java.awt.Color(255, 255, 255));
        txtMaGV.setEditable(true);
        javax.swing.GroupLayout pnlTxtMaGVLayout = new javax.swing.GroupLayout(pnlTxtMaGV);
        pnlTxtMaGV.setLayout(pnlTxtMaGVLayout);
        pnlTxtMaGVLayout.setHorizontalGroup(pnlTxtMaGVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtMaGVLayout.createSequentialGroup()
                .addComponent(lblTxtMaGV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlTxtMaGVLayout.createSequentialGroup()
                .addComponent(txtMaGV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        pnlTxtMaGVLayout.setVerticalGroup(pnlTxtMaGVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtMaGVLayout.createSequentialGroup()
                .addGroup(pnlTxtMaGVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTxtMaGV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlTxtMaGVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMaGV, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))));
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
        pnlCboTrinhDo = new javax.swing.JPanel();
        pnlCboTrinhDo.setBackground(new java.awt.Color(255, 255, 255));
        lblCboTrinhDo = new javax.swing.JLabel();
        lblCboTrinhDo.setText("Trình độ");
        lblCboTrinhDo.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblCboTrinhDo.setForeground(new java.awt.Color(36, 43, 54));
        cboTrinhDo = new javax.swing.JComboBox();
        cboTrinhDo.setFont(new java.awt.Font("Segoe UI", 0, 14));
        cboTrinhDo.setForeground(new java.awt.Color(36, 43, 54));
        cboTrinhDo.setBackground(new java.awt.Color(255, 255, 255));
        cboTrinhDo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"A", "B", "C"}));
        javax.swing.GroupLayout pnlCboTrinhDoLayout = new javax.swing.GroupLayout(pnlCboTrinhDo);
        pnlCboTrinhDo.setLayout(pnlCboTrinhDoLayout);
        pnlCboTrinhDoLayout.setHorizontalGroup(pnlCboTrinhDoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCboTrinhDoLayout.createSequentialGroup()
                .addComponent(lblCboTrinhDo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlCboTrinhDoLayout.createSequentialGroup()
                .addComponent(cboTrinhDo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        pnlCboTrinhDoLayout.setVerticalGroup(pnlCboTrinhDoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCboTrinhDoLayout.createSequentialGroup()
                .addGroup(pnlCboTrinhDoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCboTrinhDo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlCboTrinhDoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cboTrinhDo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))));
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
        pnlTxtNgayThi = new javax.swing.JPanel();
        pnlTxtNgayThi.setBackground(new java.awt.Color(255, 255, 255));
        lblTxtNgayThi = new javax.swing.JLabel();
        lblTxtNgayThi.setText("Ngày thi");
        lblTxtNgayThi.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblTxtNgayThi.setForeground(new java.awt.Color(36, 43, 54));
        txtNgayThi = new javax.swing.JFormattedTextField();
        txtNgayThi.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtNgayThi.setForeground(new java.awt.Color(36, 43, 54));
        txtNgayThi.setBackground(new java.awt.Color(255, 255, 255));
        txtNgayThi.setEditable(true);
        txtNgayThi.setToolTipText("dd/MM/yyyy");
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
        pnlTxtSoCauThi = new javax.swing.JPanel();
        pnlTxtSoCauThi.setBackground(new java.awt.Color(255, 255, 255));
        lblTxtSoCauThi = new javax.swing.JLabel();
        lblTxtSoCauThi.setText("Số câu thi (10–100)");
        lblTxtSoCauThi.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblTxtSoCauThi.setForeground(new java.awt.Color(36, 43, 54));
        txtSoCauThi = new javax.swing.JTextField();
        txtSoCauThi.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtSoCauThi.setForeground(new java.awt.Color(36, 43, 54));
        txtSoCauThi.setBackground(new java.awt.Color(255, 255, 255));
        txtSoCauThi.setEditable(true);
        javax.swing.GroupLayout pnlTxtSoCauThiLayout = new javax.swing.GroupLayout(pnlTxtSoCauThi);
        pnlTxtSoCauThi.setLayout(pnlTxtSoCauThiLayout);
        pnlTxtSoCauThiLayout.setHorizontalGroup(pnlTxtSoCauThiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtSoCauThiLayout.createSequentialGroup()
                .addComponent(lblTxtSoCauThi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlTxtSoCauThiLayout.createSequentialGroup()
                .addComponent(txtSoCauThi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        pnlTxtSoCauThiLayout.setVerticalGroup(pnlTxtSoCauThiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtSoCauThiLayout.createSequentialGroup()
                .addGroup(pnlTxtSoCauThiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTxtSoCauThi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlTxtSoCauThiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSoCauThi, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))));
        pnlTxtThoiGian = new javax.swing.JPanel();
        pnlTxtThoiGian.setBackground(new java.awt.Color(255, 255, 255));
        lblTxtThoiGian = new javax.swing.JLabel();
        lblTxtThoiGian.setText("Thời gian thi (5–60 phút)");
        lblTxtThoiGian.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblTxtThoiGian.setForeground(new java.awt.Color(36, 43, 54));
        txtThoiGian = new javax.swing.JTextField();
        txtThoiGian.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtThoiGian.setForeground(new java.awt.Color(36, 43, 54));
        txtThoiGian.setBackground(new java.awt.Color(255, 255, 255));
        txtThoiGian.setEditable(true);
        javax.swing.GroupLayout pnlTxtThoiGianLayout = new javax.swing.GroupLayout(pnlTxtThoiGian);
        pnlTxtThoiGian.setLayout(pnlTxtThoiGianLayout);
        pnlTxtThoiGianLayout.setHorizontalGroup(pnlTxtThoiGianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtThoiGianLayout.createSequentialGroup()
                .addComponent(lblTxtThoiGian, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlTxtThoiGianLayout.createSequentialGroup()
                .addComponent(txtThoiGian, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        pnlTxtThoiGianLayout.setVerticalGroup(pnlTxtThoiGianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtThoiGianLayout.createSequentialGroup()
                .addGroup(pnlTxtThoiGianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTxtThoiGian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlTxtThoiGianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtThoiGian, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))));
        pnlTxtSoCauSanCo = new javax.swing.JPanel();
        pnlTxtSoCauSanCo.setBackground(new java.awt.Color(255, 255, 255));
        lblTxtSoCauSanCo = new javax.swing.JLabel();
        lblTxtSoCauSanCo.setText("Số câu hỏi sẵn có");
        lblTxtSoCauSanCo.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblTxtSoCauSanCo.setForeground(new java.awt.Color(36, 43, 54));
        txtSoCauSanCo = new javax.swing.JTextField();
        txtSoCauSanCo.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtSoCauSanCo.setForeground(new java.awt.Color(36, 43, 54));
        txtSoCauSanCo.setBackground(new java.awt.Color(255, 255, 255));
        txtSoCauSanCo.setEditable(false);
        javax.swing.GroupLayout pnlTxtSoCauSanCoLayout = new javax.swing.GroupLayout(pnlTxtSoCauSanCo);
        pnlTxtSoCauSanCo.setLayout(pnlTxtSoCauSanCoLayout);
        pnlTxtSoCauSanCoLayout.setHorizontalGroup(pnlTxtSoCauSanCoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtSoCauSanCoLayout.createSequentialGroup()
                .addComponent(lblTxtSoCauSanCo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlTxtSoCauSanCoLayout.createSequentialGroup()
                .addComponent(txtSoCauSanCo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        pnlTxtSoCauSanCoLayout.setVerticalGroup(pnlTxtSoCauSanCoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtSoCauSanCoLayout.createSequentialGroup()
                .addGroup(pnlTxtSoCauSanCoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTxtSoCauSanCo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlTxtSoCauSanCoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSoCauSanCo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))));
        btnKiemTraCauHoi = new javax.swing.JButton();
        btnKiemTraCauHoi.setText("Kiểm tra đủ câu hỏi");
        btnKiemTraCauHoi.setFont(new java.awt.Font("Segoe UI", 0, 14));
        btnKiemTraCauHoi.setBackground(new java.awt.Color(245, 246, 248));
        btnKiemTraCauHoi.setForeground(new java.awt.Color(36, 43, 54));
        btnKiemTraCauHoi.setOpaque(true);
        btnKiemTraCauHoi.setContentAreaFilled(true);
        btnKiemTraCauHoi.setFocusPainted(false);
        btnKiemTraCauHoi.addActionListener(this::btnKiemTraCauHoiActionPerformed);
        lblTrangThaiCauHoi = new javax.swing.JLabel();
        lblTrangThaiCauHoi.setText("Chưa kiểm tra số câu hỏi");
        lblTrangThaiCauHoi.setFont(new java.awt.Font("Segoe UI", 0, 13));
        lblTrangThaiCauHoi.setForeground(new java.awt.Color(107, 114, 128));
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
        scrTblDangKyThi = new javax.swing.JScrollPane();
        tblDangKyThi = new javax.swing.JTable();
        tblDangKyThi.setFont(new java.awt.Font("Segoe UI", 0, 13));
        tblDangKyThi.setRowHeight(28);
        tblDangKyThi.setAutoCreateRowSorter(true);
        tblDangKyThi.setFillsViewportHeight(true);
        tblDangKyThi.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {}, new String[] {"Mã GV", "Mã lớp", "Môn học", "Trình độ", "Ngày thi", "Lần thi", "Số câu", "Thời gian (phút)"}) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        });
        scrTblDangKyThi.setViewportView(tblDangKyThi);
        javax.swing.GroupLayout pnlNoiDungLayout = new javax.swing.GroupLayout(pnlNoiDung);
        pnlNoiDung.setLayout(pnlNoiDungLayout);
        pnlNoiDungLayout.setHorizontalGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(lblTieuDe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(lblMoTa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(pnlTxtMaGV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(pnlCboLop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(pnlCboMonHoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(pnlCboTrinhDo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(pnlCboLanThi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(pnlTxtNgayThi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(pnlTxtSoCauThi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(pnlTxtThoiGian, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(pnlTxtSoCauSanCo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(btnKiemTraCauHoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(lblTrangThaiCauHoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addComponent(scrTblDangKyThi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        pnlNoiDungLayout.setVerticalGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTieuDe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMoTa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlTxtMaGV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlCboLop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlCboMonHoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlCboTrinhDo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlCboLanThi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlTxtNgayThi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlTxtSoCauThi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlTxtThoiGian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlTxtSoCauSanCo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnKiemTraCauHoi, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTrangThaiCauHoi, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addComponent(scrTblDangKyThi, javax.swing.GroupLayout.PREFERRED_SIZE, 240, Short.MAX_VALUE))));
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
        setTitle("PTIT | CHUẨN BỊ THI");
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/ptit-logo.png")).getImage());
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnKiemTraCauHoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKiemTraCauHoiActionPerformed
        // TODO: Bổ sung xử lý cho nút này sau khi hoàn thành giao diện.
    }//GEN-LAST:event_btnKiemTraCauHoiActionPerformed

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
            java.util.logging.Logger.getLogger(ChuanBiThi.class.getName()).log(java.util.logging.Level.WARNING, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> new ChuanBiThi().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel pnlNoiDung;
    private javax.swing.JLabel lblTieuDe;
    private javax.swing.JLabel lblMoTa;
    private javax.swing.JPanel pnlTxtMaGV;
    private javax.swing.JLabel lblTxtMaGV;
    private javax.swing.JTextField txtMaGV;
    private javax.swing.JPanel pnlCboLop;
    private javax.swing.JLabel lblCboLop;
    private javax.swing.JComboBox cboLop;
    private javax.swing.JPanel pnlCboMonHoc;
    private javax.swing.JLabel lblCboMonHoc;
    private javax.swing.JComboBox cboMonHoc;
    private javax.swing.JPanel pnlCboTrinhDo;
    private javax.swing.JLabel lblCboTrinhDo;
    private javax.swing.JComboBox cboTrinhDo;
    private javax.swing.JPanel pnlCboLanThi;
    private javax.swing.JLabel lblCboLanThi;
    private javax.swing.JComboBox cboLanThi;
    private javax.swing.JPanel pnlTxtNgayThi;
    private javax.swing.JLabel lblTxtNgayThi;
    private javax.swing.JFormattedTextField txtNgayThi;
    private javax.swing.JPanel pnlTxtSoCauThi;
    private javax.swing.JLabel lblTxtSoCauThi;
    private javax.swing.JTextField txtSoCauThi;
    private javax.swing.JPanel pnlTxtThoiGian;
    private javax.swing.JLabel lblTxtThoiGian;
    private javax.swing.JTextField txtThoiGian;
    private javax.swing.JPanel pnlTxtSoCauSanCo;
    private javax.swing.JLabel lblTxtSoCauSanCo;
    private javax.swing.JTextField txtSoCauSanCo;
    private javax.swing.JButton btnKiemTraCauHoi;
    private javax.swing.JLabel lblTrangThaiCauHoi;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnHieuChinh;
    private javax.swing.JButton btnPhucHoi;
    private javax.swing.JButton btnTim;
    private javax.swing.JButton btnGhi;
    private javax.swing.JScrollPane scrTblDangKyThi;
    private javax.swing.JTable tblDangKyThi;
    // End of variables declaration//GEN-END:variables
}
