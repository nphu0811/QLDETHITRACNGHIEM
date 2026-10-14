package baocao;

import session.SessionDangNhap;
import session.sessionSinhVien;

/**
 * Giao diện XemKetQua. Nghiệp vụ sẽ được bổ sung trong các event handler.
 * Chỉnh bố cục bằng tab Design của NetBeans và giữ file .form đi kèm.
 */
public class XemKetQua extends javax.swing.JFrame {
    private final SessionDangNhap nguoiDung;

    public XemKetQua() {
        this(null);
    }

    public XemKetQua(SessionDangNhap nguoiDung) {
        initComponents();
        this.nguoiDung = nguoiDung;
        // Đây là màn hình chi tiết, không cho nhập thông tin của người khác.
        txtLogin.setEditable(false);
        txtNgayThi.setEditable(false);
        txtLop.setEditable(false);
        txtMonHoc.setEditable(false);
        txtTrinhDo.setEditable(false);
        txtLanThi.setEditable(false);
        btnTraCuu.setVisible(false);
        btnBaiLanTruoc.setVisible(false);
        boolean coSinhVien = nguoiDung instanceof sessionSinhVien;
        if (coSinhVien) {
            sessionSinhVien sinhVien = (sessionSinhVien) nguoiDung;
            txtLogin.setText(sinhVien.getMaSV());
            txtHoTen.setText(sinhVien.getHo() + " " + sinhVien.getTen());
            String tenLop = sinhVien.getTenLop();
            if (tenLop == null || tenLop.trim().isEmpty()) {
                tenLop = sinhVien.getMaLop();
            }
            txtLop.setText(tenLop);
        }
        btnTroVe.setVisible(nguoiDung instanceof session.sessionSinhVien);
        getContentPane().setBackground(new java.awt.Color(245, 246, 248));
        setLocationRelativeTo(null);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlNoiDung = new javax.swing.JPanel();
        lblTieuDe = new javax.swing.JLabel();
        lblMoTa = new javax.swing.JLabel();
        pnlTxtLogin = new javax.swing.JPanel();
        lblTxtLogin = new javax.swing.JLabel();
        txtLogin = new javax.swing.JTextField();
        pnlTxtLop = new javax.swing.JPanel();
        lblTxtLop = new javax.swing.JLabel();
        txtLop = new javax.swing.JTextField();
        pnlTxtHoTen = new javax.swing.JPanel();
        lblTxtHoTen = new javax.swing.JLabel();
        txtHoTen = new javax.swing.JTextField();
        pnlTxtMonHoc = new javax.swing.JPanel();
        lblTxtMonHoc = new javax.swing.JLabel();
        txtMonHoc = new javax.swing.JTextField();
        pnlTxtTrinhDo = new javax.swing.JPanel();
        lblTxtTrinhDo = new javax.swing.JLabel();
        txtTrinhDo = new javax.swing.JTextField();
        pnlTxtLanThi = new javax.swing.JPanel();
        lblTxtLanThi = new javax.swing.JLabel();
        txtLanThi = new javax.swing.JTextField();
        pnlTxtNgayThi = new javax.swing.JPanel();
        lblTxtNgayThi = new javax.swing.JLabel();
        txtNgayThi = new javax.swing.JTextField();
        btnTraCuu = new javax.swing.JButton();
        btnBaiLanTruoc = new javax.swing.JButton();
        scrTblChiTietBaiThi = new javax.swing.JScrollPane();
        tblChiTietBaiThi = new javax.swing.JTable();
        pnlTxtDiem = new javax.swing.JPanel();
        lblTxtDiem = new javax.swing.JLabel();
        txtDiem = new javax.swing.JTextField();
        pnlTxtSoCauDung = new javax.swing.JPanel();
        lblTxtSoCauDung = new javax.swing.JLabel();
        txtSoCauDung = new javax.swing.JTextField();
        pnlTxtTongSoCau = new javax.swing.JPanel();
        lblTxtTongSoCau = new javax.swing.JLabel();
        txtTongSoCau = new javax.swing.JTextField();
        btnTroVe = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PTIT | XEM LẠI BÀI THI");
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/ptit-logo.png")).getImage());

        pnlNoiDung.setBackground(new java.awt.Color(255, 255, 255));
        pnlNoiDung.setBorder(javax.swing.BorderFactory.createEmptyBorder(24, 24, 24, 24));

        lblTieuDe.setText("XEM LẠI BÀI THI");
        lblTieuDe.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTieuDe.setForeground(new java.awt.Color(205, 32, 45));

        lblMoTa.setText("Tra cứu các câu đã thi và đối chiếu đáp án");
        lblMoTa.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblMoTa.setForeground(new java.awt.Color(107, 114, 128));

        pnlTxtLogin.setBackground(new java.awt.Color(255, 255, 255));

        lblTxtLogin.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblTxtLogin.setForeground(new java.awt.Color(36, 43, 54));
        lblTxtLogin.setText("Mã sinh viên");

        txtLogin.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtLogin.setForeground(new java.awt.Color(36, 43, 54));
        txtLogin.setEditable(false);

        javax.swing.GroupLayout pnlTxtLoginLayout = new javax.swing.GroupLayout(pnlTxtLogin);
        pnlTxtLogin.setLayout(pnlTxtLoginLayout);
        pnlTxtLoginLayout.setHorizontalGroup(
            pnlTxtLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtLogin)
            .addGroup(pnlTxtLoginLayout.createSequentialGroup()
                .addComponent(lblTxtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 31, Short.MAX_VALUE))
        );
        pnlTxtLoginLayout.setVerticalGroup(
            pnlTxtLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtLoginLayout.createSequentialGroup()
                .addComponent(lblTxtLogin)
                .addGap(8, 8, 8)
                .addComponent(txtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

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
            .addComponent(txtLop, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlTxtLopLayout.setVerticalGroup(
            pnlTxtLopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtLopLayout.createSequentialGroup()
                .addComponent(lblTxtLop)
                .addGap(8, 8, 8)
                .addComponent(txtLop, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlTxtHoTen.setBackground(new java.awt.Color(255, 255, 255));

        lblTxtHoTen.setText("Họ tên");
        lblTxtHoTen.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblTxtHoTen.setForeground(new java.awt.Color(36, 43, 54));

        txtHoTen.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtHoTen.setForeground(new java.awt.Color(36, 43, 54));
        txtHoTen.setEditable(false);

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

        pnlTxtMonHoc.setBackground(new java.awt.Color(255, 255, 255));

        lblTxtMonHoc.setText("Môn thi");
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
            .addComponent(txtMonHoc, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlTxtMonHocLayout.setVerticalGroup(
            pnlTxtMonHocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtMonHocLayout.createSequentialGroup()
                .addComponent(lblTxtMonHoc)
                .addGap(8, 8, 8)
                .addComponent(txtMonHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlTxtTrinhDo.setBackground(new java.awt.Color(255, 255, 255));

        lblTxtTrinhDo.setText("Trình độ");
        lblTxtTrinhDo.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblTxtTrinhDo.setForeground(new java.awt.Color(36, 43, 54));

        txtTrinhDo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTrinhDo.setForeground(new java.awt.Color(36, 43, 54));
        txtTrinhDo.setEditable(false);

        javax.swing.GroupLayout pnlTxtTrinhDoLayout = new javax.swing.GroupLayout(pnlTxtTrinhDo);
        pnlTxtTrinhDo.setLayout(pnlTxtTrinhDoLayout);
        pnlTxtTrinhDoLayout.setHorizontalGroup(
            pnlTxtTrinhDoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTxtTrinhDo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(txtTrinhDo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlTxtTrinhDoLayout.setVerticalGroup(
            pnlTxtTrinhDoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtTrinhDoLayout.createSequentialGroup()
                .addComponent(lblTxtTrinhDo)
                .addGap(8, 8, 8)
                .addComponent(txtTrinhDo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
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
            .addComponent(txtLanThi, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        txtNgayThi.setToolTipText("dd/MM/yyyy");
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

        btnTraCuu.setText("Tra cứu");
        btnTraCuu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnTraCuu.setBackground(new java.awt.Color(205, 32, 45));
        btnTraCuu.setForeground(new java.awt.Color(255, 255, 255));
        btnTraCuu.setOpaque(true);
        btnTraCuu.setFocusPainted(false);
        btnTraCuu.setVisible(false);
        btnTraCuu.addActionListener(this::btnTraCuuActionPerformed);

        btnBaiLanTruoc.setText("Bài thi lần trước");
        btnBaiLanTruoc.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnBaiLanTruoc.setBackground(new java.awt.Color(245, 246, 248));
        btnBaiLanTruoc.setForeground(new java.awt.Color(36, 43, 54));
        btnBaiLanTruoc.setOpaque(true);
        btnBaiLanTruoc.setFocusPainted(false);
        btnBaiLanTruoc.setVisible(false);
        btnBaiLanTruoc.addActionListener(this::btnBaiLanTruocActionPerformed);

        tblChiTietBaiThi.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        tblChiTietBaiThi.setRowHeight(28);
        tblChiTietBaiThi.setAutoCreateRowSorter(true);
        tblChiTietBaiThi.setFillsViewportHeight(true);
        tblChiTietBaiThi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Câu số (trong bộ đề)", "Đã chọn", "Đáp án"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrTblChiTietBaiThi.setViewportView(tblChiTietBaiThi);

        pnlTxtDiem.setBackground(new java.awt.Color(255, 255, 255));

        lblTxtDiem.setText("Điểm");
        lblTxtDiem.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblTxtDiem.setForeground(new java.awt.Color(36, 43, 54));

        txtDiem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtDiem.setForeground(new java.awt.Color(36, 43, 54));
        txtDiem.setEditable(false);

        javax.swing.GroupLayout pnlTxtDiemLayout = new javax.swing.GroupLayout(pnlTxtDiem);
        pnlTxtDiem.setLayout(pnlTxtDiemLayout);
        pnlTxtDiemLayout.setHorizontalGroup(
            pnlTxtDiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTxtDiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(txtDiem)
        );
        pnlTxtDiemLayout.setVerticalGroup(
            pnlTxtDiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtDiemLayout.createSequentialGroup()
                .addComponent(lblTxtDiem)
                .addGap(8, 8, 8)
                .addComponent(txtDiem, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlTxtSoCauDung.setBackground(new java.awt.Color(255, 255, 255));

        lblTxtSoCauDung.setText("Số câu đúng");
        lblTxtSoCauDung.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblTxtSoCauDung.setForeground(new java.awt.Color(36, 43, 54));

        txtSoCauDung.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSoCauDung.setForeground(new java.awt.Color(36, 43, 54));
        txtSoCauDung.setEditable(false);

        javax.swing.GroupLayout pnlTxtSoCauDungLayout = new javax.swing.GroupLayout(pnlTxtSoCauDung);
        pnlTxtSoCauDung.setLayout(pnlTxtSoCauDungLayout);
        pnlTxtSoCauDungLayout.setHorizontalGroup(
            pnlTxtSoCauDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTxtSoCauDung, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(txtSoCauDung)
        );
        pnlTxtSoCauDungLayout.setVerticalGroup(
            pnlTxtSoCauDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtSoCauDungLayout.createSequentialGroup()
                .addComponent(lblTxtSoCauDung)
                .addGap(8, 8, 8)
                .addComponent(txtSoCauDung, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlTxtTongSoCau.setBackground(new java.awt.Color(255, 255, 255));

        lblTxtTongSoCau.setText("Tổng số câu");
        lblTxtTongSoCau.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblTxtTongSoCau.setForeground(new java.awt.Color(36, 43, 54));

        txtTongSoCau.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTongSoCau.setForeground(new java.awt.Color(36, 43, 54));
        txtTongSoCau.setEditable(false);

        javax.swing.GroupLayout pnlTxtTongSoCauLayout = new javax.swing.GroupLayout(pnlTxtTongSoCau);
        pnlTxtTongSoCau.setLayout(pnlTxtTongSoCauLayout);
        pnlTxtTongSoCauLayout.setHorizontalGroup(
            pnlTxtTongSoCauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTxtTongSoCau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(txtTongSoCau)
        );
        pnlTxtTongSoCauLayout.setVerticalGroup(
            pnlTxtTongSoCauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtTongSoCauLayout.createSequentialGroup()
                .addComponent(lblTxtTongSoCau)
                .addGap(8, 8, 8)
                .addComponent(txtTongSoCau, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        btnTroVe.setText("Trở về");
        btnTroVe.addActionListener(this::btnTroVeActionPerformed);

        javax.swing.GroupLayout pnlNoiDungLayout = new javax.swing.GroupLayout(pnlNoiDung);
        pnlNoiDung.setLayout(pnlNoiDungLayout);
        pnlNoiDungLayout.setHorizontalGroup(
            pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTieuDe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblMoTa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(pnlTxtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlTxtLop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(pnlTxtHoTen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(pnlTxtMonHoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(pnlTxtTrinhDo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(pnlTxtLanThi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(pnlTxtNgayThi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(btnTraCuu, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(btnBaiLanTruoc, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(444, 444, 444))
            .addComponent(scrTblChiTietBaiThi)
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(pnlTxtDiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(pnlTxtSoCauDung, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(pnlTxtTongSoCau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(btnTroVe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlNoiDungLayout.setVerticalGroup(
            pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(lblTieuDe)
                .addGap(8, 8, 8)
                .addComponent(lblMoTa)
                .addGap(8, 8, 8)
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlTxtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlTxtLop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlTxtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlTxtMonHoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlTxtTrinhDo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlTxtLanThi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlTxtNgayThi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTraCuu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBaiLanTruoc, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(scrTblChiTietBaiThi, javax.swing.GroupLayout.PREFERRED_SIZE, 270, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlTxtDiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlTxtSoCauDung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlTxtTongSoCau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(btnTroVe, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void btnTraCuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTraCuuActionPerformed
        // TODO: Bổ sung xử lý cho nút này sau khi hoàn thành giao diện.
    }//GEN-LAST:event_btnTraCuuActionPerformed

    private void btnBaiLanTruocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBaiLanTruocActionPerformed
        // TODO: Bổ sung xử lý cho nút này sau khi hoàn thành giao diện.
    }//GEN-LAST:event_btnBaiLanTruocActionPerformed

    private void btnTroVeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTroVeActionPerformed
        // TODO: Bổ sung xử lý nút Trở về.
        trangchu.DieuHuong.moForm(this, new trangchu.TrangChu(nguoiDung), nguoiDung);
    }//GEN-LAST:event_btnTroVeActionPerformed

    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(XemKetQua.class.getName()).log(java.util.logging.Level.WARNING, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> new XemKetQua().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBaiLanTruoc;
    private javax.swing.JButton btnTraCuu;
    private javax.swing.JButton btnTroVe;
    private javax.swing.JTextField txtLanThi;
    private javax.swing.JTextField txtLop;
    private javax.swing.JTextField txtMonHoc;
    private javax.swing.JTextField txtTrinhDo;
    private javax.swing.JLabel lblTxtLanThi;
    private javax.swing.JLabel lblTxtLop;
    private javax.swing.JLabel lblTxtMonHoc;
    private javax.swing.JLabel lblTxtTrinhDo;
    private javax.swing.JLabel lblMoTa;
    private javax.swing.JLabel lblTieuDe;
    private javax.swing.JLabel lblTxtDiem;
    private javax.swing.JLabel lblTxtHoTen;
    private javax.swing.JLabel lblTxtLogin;
    private javax.swing.JLabel lblTxtNgayThi;
    private javax.swing.JLabel lblTxtSoCauDung;
    private javax.swing.JLabel lblTxtTongSoCau;
    private javax.swing.JPanel pnlTxtLanThi;
    private javax.swing.JPanel pnlTxtLop;
    private javax.swing.JPanel pnlTxtMonHoc;
    private javax.swing.JPanel pnlTxtTrinhDo;
    private javax.swing.JPanel pnlNoiDung;
    private javax.swing.JPanel pnlTxtDiem;
    private javax.swing.JPanel pnlTxtHoTen;
    private javax.swing.JPanel pnlTxtLogin;
    private javax.swing.JPanel pnlTxtNgayThi;
    private javax.swing.JPanel pnlTxtSoCauDung;
    private javax.swing.JPanel pnlTxtTongSoCau;
    private javax.swing.JScrollPane scrTblChiTietBaiThi;
    private javax.swing.JTable tblChiTietBaiThi;
    private javax.swing.JTextField txtDiem;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtLogin;
    private javax.swing.JTextField txtNgayThi;
    private javax.swing.JTextField txtSoCauDung;
    private javax.swing.JTextField txtTongSoCau;
    // End of variables declaration//GEN-END:variables
}
