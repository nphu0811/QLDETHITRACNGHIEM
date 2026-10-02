package thi;

import session.SessionDangNhap;
import session.sessionSinhVien;

/**
 * Giao diện LamBaiThi. Nghiệp vụ sẽ được bổ sung trong các event handler.
 * Chỉnh bố cục bằng tab Design của NetBeans và giữ file .form đi kèm.
 */
public class LamBaiThi extends javax.swing.JFrame {
    private final SessionDangNhap nguoiDung;

    public LamBaiThi() {
        this(null);
    }

    public LamBaiThi(SessionDangNhap nguoiDung) {
        initComponents();
        this.nguoiDung = nguoiDung;
        if(nguoiDung instanceof sessionSinhVien){
            sessionSinhVien sinhVien = (sessionSinhVien) nguoiDung;
            txtHoTen.setText(sinhVien.getHo() + " " + sinhVien.getTen());
            txtMaSV.setText(sinhVien.getMaSV());
            txtLop.setText(sinhVien.getTenLop());
        }
        btnTroVe.setVisible(nguoiDung instanceof session.sessionSinhVien);
        getContentPane().setBackground(new java.awt.Color(245, 246, 248));
        setLocationRelativeTo(null);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        grpDapAn = new javax.swing.ButtonGroup();
        pnlNoiDung = new javax.swing.JPanel();
        pnlNoiDung.setBackground(new java.awt.Color(255, 255, 255));
        pnlNoiDung.setBorder(javax.swing.BorderFactory.createEmptyBorder(24, 24, 24, 24));
        lblTieuDe = new javax.swing.JLabel();
        lblTieuDe.setText("LÀM BÀI THI");
        lblTieuDe.setFont(new java.awt.Font("Segoe UI", 1, 24));
        lblTieuDe.setForeground(new java.awt.Color(205, 32, 45));
        lblMoTa = new javax.swing.JLabel();
        lblMoTa.setText("Bài thi trắc nghiệm");
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
        pnlTxtMonThi = new javax.swing.JPanel();
        pnlTxtMonThi.setBackground(new java.awt.Color(255, 255, 255));
        lblTxtMonThi = new javax.swing.JLabel();
        lblTxtMonThi.setText("Môn thi");
        lblTxtMonThi.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblTxtMonThi.setForeground(new java.awt.Color(36, 43, 54));
        txtMonThi = new javax.swing.JTextField();
        txtMonThi.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtMonThi.setForeground(new java.awt.Color(36, 43, 54));
        txtMonThi.setBackground(new java.awt.Color(255, 255, 255));
        txtMonThi.setEditable(false);
        javax.swing.GroupLayout pnlTxtMonThiLayout = new javax.swing.GroupLayout(pnlTxtMonThi);
        pnlTxtMonThi.setLayout(pnlTxtMonThiLayout);
        pnlTxtMonThiLayout.setHorizontalGroup(pnlTxtMonThiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtMonThiLayout.createSequentialGroup()
                .addComponent(lblTxtMonThi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlTxtMonThiLayout.createSequentialGroup()
                .addComponent(txtMonThi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        pnlTxtMonThiLayout.setVerticalGroup(pnlTxtMonThiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtMonThiLayout.createSequentialGroup()
                .addGroup(pnlTxtMonThiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTxtMonThi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlTxtMonThiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMonThi, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))));
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
        pnlTxtTrinhDo = new javax.swing.JPanel();
        pnlTxtTrinhDo.setBackground(new java.awt.Color(255, 255, 255));
        lblTxtTrinhDo = new javax.swing.JLabel();
        lblTxtTrinhDo.setText("Trình độ");
        lblTxtTrinhDo.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblTxtTrinhDo.setForeground(new java.awt.Color(36, 43, 54));
        txtTrinhDo = new javax.swing.JTextField();
        txtTrinhDo.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtTrinhDo.setForeground(new java.awt.Color(36, 43, 54));
        txtTrinhDo.setBackground(new java.awt.Color(255, 255, 255));
        txtTrinhDo.setEditable(false);
        javax.swing.GroupLayout pnlTxtTrinhDoLayout = new javax.swing.GroupLayout(pnlTxtTrinhDo);
        pnlTxtTrinhDo.setLayout(pnlTxtTrinhDoLayout);
        pnlTxtTrinhDoLayout.setHorizontalGroup(pnlTxtTrinhDoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtTrinhDoLayout.createSequentialGroup()
                .addComponent(lblTxtTrinhDo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlTxtTrinhDoLayout.createSequentialGroup()
                .addComponent(txtTrinhDo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        pnlTxtTrinhDoLayout.setVerticalGroup(pnlTxtTrinhDoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtTrinhDoLayout.createSequentialGroup()
                .addGroup(pnlTxtTrinhDoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTxtTrinhDo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlTxtTrinhDoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTrinhDo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))));
        lblThoiGianConLai = new javax.swing.JLabel();
        lblThoiGianConLai.setText("Thời gian còn lại: — : —");
        lblThoiGianConLai.setFont(new java.awt.Font("Segoe UI", 1, 18));
        lblThoiGianConLai.setForeground(new java.awt.Color(205, 32, 45));
        lblNgayThi = new javax.swing.JLabel();
        lblNgayThi.setText("Ngày thi: —");
        lblNgayThi.setFont(new java.awt.Font("Segoe UI", 0, 14));
        lblNgayThi.setForeground(new java.awt.Color(107, 114, 128));
        pnlCauHoi = new javax.swing.JPanel();
        pnlCauHoi.setBackground(new java.awt.Color(255, 255, 255));
        lblSoCau = new javax.swing.JLabel();
        lblSoCau.setText("Câu — / —");
        lblSoCau.setFont(new java.awt.Font("Segoe UI", 1, 16));
        lblSoCau.setForeground(new java.awt.Color(36, 43, 54));
        lblSoCauBoDe = new javax.swing.JLabel();
        lblSoCauBoDe.setText("Câu số trong bộ đề: —");
        lblSoCauBoDe.setFont(new java.awt.Font("Segoe UI", 0, 13));
        lblSoCauBoDe.setForeground(new java.awt.Color(107, 114, 128));
        pnlTxtNoiDungCauHoi = new javax.swing.JPanel();
        pnlTxtNoiDungCauHoi.setBackground(new java.awt.Color(255, 255, 255));
        lblTxtNoiDungCauHoi = new javax.swing.JLabel();
        lblTxtNoiDungCauHoi.setText("Nội dung câu hỏi");
        lblTxtNoiDungCauHoi.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblTxtNoiDungCauHoi.setForeground(new java.awt.Color(36, 43, 54));
        scrTxtNoiDungCauHoi = new javax.swing.JScrollPane();
        txtNoiDungCauHoi = new javax.swing.JTextArea();
        txtNoiDungCauHoi.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtNoiDungCauHoi.setLineWrap(true);
        txtNoiDungCauHoi.setWrapStyleWord(true);
        txtNoiDungCauHoi.setRows(3);
        txtNoiDungCauHoi.setColumns(24);
        txtNoiDungCauHoi.setEditable(false);
        scrTxtNoiDungCauHoi.setViewportView(txtNoiDungCauHoi);
        javax.swing.GroupLayout pnlTxtNoiDungCauHoiLayout = new javax.swing.GroupLayout(pnlTxtNoiDungCauHoi);
        pnlTxtNoiDungCauHoi.setLayout(pnlTxtNoiDungCauHoiLayout);
        pnlTxtNoiDungCauHoiLayout.setHorizontalGroup(pnlTxtNoiDungCauHoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtNoiDungCauHoiLayout.createSequentialGroup()
                .addComponent(lblTxtNoiDungCauHoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlTxtNoiDungCauHoiLayout.createSequentialGroup()
                .addComponent(scrTxtNoiDungCauHoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        pnlTxtNoiDungCauHoiLayout.setVerticalGroup(pnlTxtNoiDungCauHoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtNoiDungCauHoiLayout.createSequentialGroup()
                .addGroup(pnlTxtNoiDungCauHoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTxtNoiDungCauHoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlTxtNoiDungCauHoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrTxtNoiDungCauHoi, javax.swing.GroupLayout.PREFERRED_SIZE, 88, Short.MAX_VALUE))));
        rdoA = new javax.swing.JRadioButton();
        rdoA.setText("A. Phương án A");
        rdoA.setFont(new java.awt.Font("Segoe UI", 0, 14));
        rdoA.setBackground(new java.awt.Color(255, 255, 255));
        rdoB = new javax.swing.JRadioButton();
        rdoB.setText("B. Phương án B");
        rdoB.setFont(new java.awt.Font("Segoe UI", 0, 14));
        rdoB.setBackground(new java.awt.Color(255, 255, 255));
        rdoC = new javax.swing.JRadioButton();
        rdoC.setText("C. Phương án C");
        rdoC.setFont(new java.awt.Font("Segoe UI", 0, 14));
        rdoC.setBackground(new java.awt.Color(255, 255, 255));
        rdoD = new javax.swing.JRadioButton();
        rdoD.setText("D. Phương án D");
        rdoD.setFont(new java.awt.Font("Segoe UI", 0, 14));
        rdoD.setBackground(new java.awt.Color(255, 255, 255));
        btnCauTruoc = new javax.swing.JButton();
        btnCauTruoc.setText("Câu trước");
        btnCauTruoc.setFont(new java.awt.Font("Segoe UI", 0, 14));
        btnCauTruoc.setBackground(new java.awt.Color(245, 246, 248));
        btnCauTruoc.setForeground(new java.awt.Color(36, 43, 54));
        btnCauTruoc.setOpaque(true);
        btnCauTruoc.setContentAreaFilled(true);
        btnCauTruoc.setFocusPainted(false);
        btnCauTruoc.addActionListener(this::btnCauTruocActionPerformed);
        btnCauSau = new javax.swing.JButton();
        btnCauSau.setText("Câu tiếp");
        btnCauSau.setFont(new java.awt.Font("Segoe UI", 0, 14));
        btnCauSau.setBackground(new java.awt.Color(245, 246, 248));
        btnCauSau.setForeground(new java.awt.Color(36, 43, 54));
        btnCauSau.setOpaque(true);
        btnCauSau.setContentAreaFilled(true);
        btnCauSau.setFocusPainted(false);
        btnCauSau.addActionListener(this::btnCauSauActionPerformed);
        btnXoaLuaChon = new javax.swing.JButton();
        btnXoaLuaChon.setText("Bỏ chọn");
        btnXoaLuaChon.setFont(new java.awt.Font("Segoe UI", 0, 14));
        btnXoaLuaChon.setBackground(new java.awt.Color(245, 246, 248));
        btnXoaLuaChon.setForeground(new java.awt.Color(36, 43, 54));
        btnXoaLuaChon.setOpaque(true);
        btnXoaLuaChon.setContentAreaFilled(true);
        btnXoaLuaChon.setFocusPainted(false);
        btnXoaLuaChon.addActionListener(this::btnXoaLuaChonActionPerformed);
        javax.swing.GroupLayout pnlCauHoiLayout = new javax.swing.GroupLayout(pnlCauHoi);
        pnlCauHoi.setLayout(pnlCauHoiLayout);
        pnlCauHoiLayout.setHorizontalGroup(pnlCauHoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCauHoiLayout.createSequentialGroup()
                .addComponent(lblSoCau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(lblSoCauBoDe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlCauHoiLayout.createSequentialGroup()
                .addComponent(pnlTxtNoiDungCauHoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlCauHoiLayout.createSequentialGroup()
                .addComponent(rdoA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlCauHoiLayout.createSequentialGroup()
                .addComponent(rdoB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlCauHoiLayout.createSequentialGroup()
                .addComponent(rdoC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlCauHoiLayout.createSequentialGroup()
                .addComponent(rdoD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlCauHoiLayout.createSequentialGroup()
                .addComponent(btnCauTruoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(btnCauSau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(btnXoaLuaChon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        pnlCauHoiLayout.setVerticalGroup(pnlCauHoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCauHoiLayout.createSequentialGroup()
                .addGroup(pnlCauHoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSoCau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSoCauBoDe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlCauHoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlTxtNoiDungCauHoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlCauHoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rdoA, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlCauHoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rdoB, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlCauHoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rdoC, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlCauHoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rdoD, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlCauHoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCauTruoc, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCauSau, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoaLuaChon, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))));
        pnlDanhSachCau = new javax.swing.JPanel();
        pnlDanhSachCau.setBackground(new java.awt.Color(255, 255, 255));
        lblDanhSachCau = new javax.swing.JLabel();
        lblDanhSachCau.setText("Danh sách câu hỏi");
        lblDanhSachCau.setFont(new java.awt.Font("Segoe UI", 1, 16));
        lblDanhSachCau.setForeground(new java.awt.Color(205, 32, 45));
        scrTblCauThi = new javax.swing.JScrollPane();
        tblCauThi = new javax.swing.JTable();
        tblCauThi.setFont(new java.awt.Font("Segoe UI", 0, 13));
        tblCauThi.setRowHeight(28);
        tblCauThi.setAutoCreateRowSorter(true);
        tblCauThi.setFillsViewportHeight(true);
        tblCauThi.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {}, new String[] {"Câu", "Đã chọn"}) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        });
        scrTblCauThi.setViewportView(tblCauThi);
        lblDaTraLoi = new javax.swing.JLabel();
        lblDaTraLoi.setText("Đã trả lời: — / —");
        lblDaTraLoi.setFont(new java.awt.Font("Segoe UI", 0, 13));
        lblDaTraLoi.setForeground(new java.awt.Color(107, 114, 128));
        btnChonLaiCau = new javax.swing.JButton();
        btnChonLaiCau.setText("Chọn lại câu đã làm");
        btnChonLaiCau.setFont(new java.awt.Font("Segoe UI", 0, 14));
        btnChonLaiCau.setBackground(new java.awt.Color(245, 246, 248));
        btnChonLaiCau.setForeground(new java.awt.Color(36, 43, 54));
        btnChonLaiCau.setOpaque(true);
        btnChonLaiCau.setContentAreaFilled(true);
        btnChonLaiCau.setFocusPainted(false);
        btnChonLaiCau.addActionListener(this::btnChonLaiCauActionPerformed);
        javax.swing.GroupLayout pnlDanhSachCauLayout = new javax.swing.GroupLayout(pnlDanhSachCau);
        pnlDanhSachCau.setLayout(pnlDanhSachCauLayout);
        pnlDanhSachCauLayout.setHorizontalGroup(pnlDanhSachCauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDanhSachCauLayout.createSequentialGroup()
                .addComponent(lblDanhSachCau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlDanhSachCauLayout.createSequentialGroup()
                .addComponent(scrTblCauThi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlDanhSachCauLayout.createSequentialGroup()
                .addComponent(lblDaTraLoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlDanhSachCauLayout.createSequentialGroup()
                .addComponent(btnChonLaiCau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        pnlDanhSachCauLayout.setVerticalGroup(pnlDanhSachCauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDanhSachCauLayout.createSequentialGroup()
                .addGroup(pnlDanhSachCauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDanhSachCau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlDanhSachCauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrTblCauThi, javax.swing.GroupLayout.PREFERRED_SIZE, 280, Short.MAX_VALUE))
                .addGap(8, 8, 8)
                .addGroup(pnlDanhSachCauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDaTraLoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlDanhSachCauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnChonLaiCau, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))));
        btnNopBai = new javax.swing.JButton();
        btnNopBai.setText("Nộp bài");
        btnNopBai.setFont(new java.awt.Font("Segoe UI", 1, 14));
        btnNopBai.setBackground(new java.awt.Color(205, 32, 45));
        btnNopBai.setForeground(new java.awt.Color(255, 255, 255));
        btnNopBai.setOpaque(true);
        btnNopBai.setContentAreaFilled(true);
        btnNopBai.setFocusPainted(false);
        btnNopBai.addActionListener(this::btnNopBaiActionPerformed);
        btnXemLai = new javax.swing.JButton();
        btnXemLai.setText("Xem lại câu đã làm");
        btnXemLai.setFont(new java.awt.Font("Segoe UI", 0, 14));
        btnXemLai.setBackground(new java.awt.Color(245, 246, 248));
        btnXemLai.setForeground(new java.awt.Color(36, 43, 54));
        btnXemLai.setOpaque(true);
        btnXemLai.setContentAreaFilled(true);
        btnXemLai.setFocusPainted(false);
        btnXemLai.addActionListener(this::btnXemLaiActionPerformed);
        btnTroVe = new javax.swing.JButton("Trở về");
        btnTroVe.setFont(new java.awt.Font("Segoe UI", 0, 14));
        btnTroVe.setBackground(new java.awt.Color(245, 246, 248));
        btnTroVe.setFocusPainted(false);
        btnTroVe.addActionListener(this::btnTroVeActionPerformed);
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
                .addComponent(pnlTxtHoTen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(pnlTxtLop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(pnlTxtMonThi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(pnlTxtLanThi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(pnlTxtTrinhDo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(lblThoiGianConLai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(lblNgayThi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(pnlCauHoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(pnlDanhSachCau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(btnNopBai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(btnXemLai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(btnTroVe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
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
                    .addComponent(pnlTxtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlTxtLop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlTxtMonThi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlTxtLanThi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlTxtTrinhDo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblThoiGianConLai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNgayThi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlCauHoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlDanhSachCau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNopBai, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXemLai, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(btnTroVe, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)));
        grpDapAn.add(rdoA);
        grpDapAn.add(rdoB);
        grpDapAn.add(rdoC);
        grpDapAn.add(rdoD);
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(pnlNoiDung, javax.swing.GroupLayout.DEFAULT_SIZE, 1120, Short.MAX_VALUE)
                .addGap(16, 16, 16)));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlNoiDung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)));
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PTIT | LÀM BÀI THI");
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/ptit-logo.png")).getImage());
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCauTruocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCauTruocActionPerformed
        // TODO: Bổ sung xử lý cho nút này sau khi hoàn thành giao diện.
    }//GEN-LAST:event_btnCauTruocActionPerformed

    private void btnCauSauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCauSauActionPerformed
        // TODO: Bổ sung xử lý cho nút này sau khi hoàn thành giao diện.
    }//GEN-LAST:event_btnCauSauActionPerformed

    private void btnXoaLuaChonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaLuaChonActionPerformed
        // TODO: Bổ sung xử lý cho nút này sau khi hoàn thành giao diện.
    }//GEN-LAST:event_btnXoaLuaChonActionPerformed

    private void btnChonLaiCauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonLaiCauActionPerformed
        // TODO: Bổ sung xử lý cho nút này sau khi hoàn thành giao diện.
    }//GEN-LAST:event_btnChonLaiCauActionPerformed

    private void btnNopBaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNopBaiActionPerformed
        // TODO: Bổ sung xử lý cho nút này sau khi hoàn thành giao diện.
    }//GEN-LAST:event_btnNopBaiActionPerformed

    private void btnXemLaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemLaiActionPerformed
        // TODO: Bổ sung xử lý cho nút này sau khi hoàn thành giao diện.
    }//GEN-LAST:event_btnXemLaiActionPerformed

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
            java.util.logging.Logger.getLogger(LamBaiThi.class.getName()).log(java.util.logging.Level.WARNING, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> new LamBaiThi().setVisible(true));
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
    private javax.swing.JPanel pnlTxtLop;
    private javax.swing.JLabel lblTxtLop;
    private javax.swing.JTextField txtLop;
    private javax.swing.JPanel pnlTxtMonThi;
    private javax.swing.JLabel lblTxtMonThi;
    private javax.swing.JTextField txtMonThi;
    private javax.swing.JPanel pnlTxtLanThi;
    private javax.swing.JLabel lblTxtLanThi;
    private javax.swing.JTextField txtLanThi;
    private javax.swing.JPanel pnlTxtTrinhDo;
    private javax.swing.JLabel lblTxtTrinhDo;
    private javax.swing.JTextField txtTrinhDo;
    private javax.swing.JLabel lblThoiGianConLai;
    private javax.swing.JLabel lblNgayThi;
    private javax.swing.JPanel pnlCauHoi;
    private javax.swing.JLabel lblSoCau;
    private javax.swing.JLabel lblSoCauBoDe;
    private javax.swing.JPanel pnlTxtNoiDungCauHoi;
    private javax.swing.JLabel lblTxtNoiDungCauHoi;
    private javax.swing.JScrollPane scrTxtNoiDungCauHoi;
    private javax.swing.JTextArea txtNoiDungCauHoi;
    private javax.swing.JRadioButton rdoA;
    private javax.swing.JRadioButton rdoB;
    private javax.swing.JRadioButton rdoC;
    private javax.swing.JRadioButton rdoD;
    private javax.swing.JButton btnCauTruoc;
    private javax.swing.JButton btnCauSau;
    private javax.swing.JButton btnXoaLuaChon;
    private javax.swing.JPanel pnlDanhSachCau;
    private javax.swing.JLabel lblDanhSachCau;
    private javax.swing.JScrollPane scrTblCauThi;
    private javax.swing.JTable tblCauThi;
    private javax.swing.JLabel lblDaTraLoi;
    private javax.swing.JButton btnChonLaiCau;
    private javax.swing.JButton btnNopBai;
    private javax.swing.JButton btnXemLai;
    private javax.swing.ButtonGroup grpDapAn;
    private javax.swing.JButton btnTroVe;
    // End of variables declaration//GEN-END:variables
}
