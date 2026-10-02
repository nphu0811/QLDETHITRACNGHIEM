package thi;

import session.SessionDangNhap;
import session.sessionSinhVien;

/**
 * Giao diện KetQuaThi. Nghiệp vụ sẽ được bổ sung trong các event handler.
 * Chỉnh bố cục bằng tab Design của NetBeans và giữ file .form đi kèm.
 */
public class KetQuaThi extends javax.swing.JFrame {
    private final SessionDangNhap nguoiDung;

    public KetQuaThi() {
        this(null);
    }

    public KetQuaThi(SessionDangNhap nguoiDung) {
        initComponents();
        this.nguoiDung = nguoiDung;
        if(nguoiDung instanceof sessionSinhVien){
            sessionSinhVien sinhVien = (sessionSinhVien) nguoiDung;
            txtHoTen.setText(sinhVien.getHo() + " " + sinhVien.getTen());
            txtMaSV.setText(sinhVien.getMaSV());
            txtLop.setText(sinhVien.getTenLop());
        }
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
        lblTieuDe.setText("KẾT QUẢ THI");
        lblTieuDe.setFont(new java.awt.Font("Segoe UI", 1, 24));
        lblTieuDe.setForeground(new java.awt.Color(205, 32, 45));
        lblMoTa = new javax.swing.JLabel();
        lblMoTa.setText("Thông báo kết quả sau khi hoàn thành bài thi");
        lblMoTa.setFont(new java.awt.Font("Segoe UI", 0, 13));
        lblMoTa.setForeground(new java.awt.Color(107, 114, 128));
        lblDiem = new javax.swing.JLabel();
        lblDiem.setText("— / 10");
        lblDiem.setFont(new java.awt.Font("Segoe UI", 1, 38));
        lblDiem.setForeground(new java.awt.Color(205, 32, 45));
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
        pnlTxtSoCauDung = new javax.swing.JPanel();
        pnlTxtSoCauDung.setBackground(new java.awt.Color(255, 255, 255));
        lblTxtSoCauDung = new javax.swing.JLabel();
        lblTxtSoCauDung.setText("Số câu đúng");
        lblTxtSoCauDung.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblTxtSoCauDung.setForeground(new java.awt.Color(36, 43, 54));
        txtSoCauDung = new javax.swing.JTextField();
        txtSoCauDung.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtSoCauDung.setForeground(new java.awt.Color(36, 43, 54));
        txtSoCauDung.setBackground(new java.awt.Color(255, 255, 255));
        txtSoCauDung.setEditable(false);
        javax.swing.GroupLayout pnlTxtSoCauDungLayout = new javax.swing.GroupLayout(pnlTxtSoCauDung);
        pnlTxtSoCauDung.setLayout(pnlTxtSoCauDungLayout);
        pnlTxtSoCauDungLayout.setHorizontalGroup(pnlTxtSoCauDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtSoCauDungLayout.createSequentialGroup()
                .addComponent(lblTxtSoCauDung, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlTxtSoCauDungLayout.createSequentialGroup()
                .addComponent(txtSoCauDung, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        pnlTxtSoCauDungLayout.setVerticalGroup(pnlTxtSoCauDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtSoCauDungLayout.createSequentialGroup()
                .addGroup(pnlTxtSoCauDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTxtSoCauDung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlTxtSoCauDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSoCauDung, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))));
        pnlTxtTongSoCau = new javax.swing.JPanel();
        pnlTxtTongSoCau.setBackground(new java.awt.Color(255, 255, 255));
        lblTxtTongSoCau = new javax.swing.JLabel();
        lblTxtTongSoCau.setText("Tổng số câu");
        lblTxtTongSoCau.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblTxtTongSoCau.setForeground(new java.awt.Color(36, 43, 54));
        txtTongSoCau = new javax.swing.JTextField();
        txtTongSoCau.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtTongSoCau.setForeground(new java.awt.Color(36, 43, 54));
        txtTongSoCau.setBackground(new java.awt.Color(255, 255, 255));
        txtTongSoCau.setEditable(false);
        javax.swing.GroupLayout pnlTxtTongSoCauLayout = new javax.swing.GroupLayout(pnlTxtTongSoCau);
        pnlTxtTongSoCau.setLayout(pnlTxtTongSoCauLayout);
        pnlTxtTongSoCauLayout.setHorizontalGroup(pnlTxtTongSoCauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtTongSoCauLayout.createSequentialGroup()
                .addComponent(lblTxtTongSoCau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlTxtTongSoCauLayout.createSequentialGroup()
                .addComponent(txtTongSoCau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        pnlTxtTongSoCauLayout.setVerticalGroup(pnlTxtTongSoCauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtTongSoCauLayout.createSequentialGroup()
                .addGroup(pnlTxtTongSoCauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTxtTongSoCau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlTxtTongSoCauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTongSoCau, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))));
        btnXemChiTiet = new javax.swing.JButton();
        btnXemChiTiet.setText("Xem chi tiết bài thi");
        btnXemChiTiet.setFont(new java.awt.Font("Segoe UI", 1, 14));
        btnXemChiTiet.setBackground(new java.awt.Color(205, 32, 45));
        btnXemChiTiet.setForeground(new java.awt.Color(255, 255, 255));
        btnXemChiTiet.setOpaque(true);
        btnXemChiTiet.setContentAreaFilled(true);
        btnXemChiTiet.setFocusPainted(false);
        btnXemChiTiet.addActionListener(this::btnXemChiTietActionPerformed);
        btnVeTrangChu = new javax.swing.JButton();
        btnVeTrangChu.setText("Về trang chủ");
        btnVeTrangChu.setFont(new java.awt.Font("Segoe UI", 0, 14));
        btnVeTrangChu.setBackground(new java.awt.Color(245, 246, 248));
        btnVeTrangChu.setForeground(new java.awt.Color(36, 43, 54));
        btnVeTrangChu.setOpaque(true);
        btnVeTrangChu.setContentAreaFilled(true);
        btnVeTrangChu.setFocusPainted(false);
        btnVeTrangChu.addActionListener(this::btnVeTrangChuActionPerformed);
        javax.swing.GroupLayout pnlNoiDungLayout = new javax.swing.GroupLayout(pnlNoiDung);
        pnlNoiDung.setLayout(pnlNoiDungLayout);
        pnlNoiDungLayout.setHorizontalGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(lblTieuDe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(lblMoTa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(lblDiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(pnlTxtMaSV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(pnlTxtHoTen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(pnlTxtLop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(pnlTxtMonThi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(pnlTxtNgayThi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(pnlTxtLanThi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(pnlTxtSoCauDung, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(pnlTxtTongSoCau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(btnXemChiTiet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(btnVeTrangChu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        pnlNoiDungLayout.setVerticalGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTieuDe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMoTa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlTxtMaSV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlTxtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlTxtLop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlTxtMonThi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlTxtNgayThi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlTxtLanThi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlTxtSoCauDung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlTxtTongSoCau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnXemChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVeTrangChu, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))));
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(pnlNoiDung, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
                .addGap(16, 16, 16)));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlNoiDung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)));
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PTIT | KẾT QUẢ THI");
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/ptit-logo.png")).getImage());
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnXemChiTietActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemChiTietActionPerformed
        trangchu.DieuHuong.moForm(this, new baocao.XemKetQua(nguoiDung), nguoiDung);
    }//GEN-LAST:event_btnXemChiTietActionPerformed

    private void btnVeTrangChuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVeTrangChuActionPerformed
        trangchu.DieuHuong.moForm(this, new trangchu.TrangChu(nguoiDung), nguoiDung);
    }//GEN-LAST:event_btnVeTrangChuActionPerformed

    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(KetQuaThi.class.getName()).log(java.util.logging.Level.WARNING, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> new KetQuaThi().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel pnlNoiDung;
    private javax.swing.JLabel lblTieuDe;
    private javax.swing.JLabel lblMoTa;
    private javax.swing.JLabel lblDiem;
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
    private javax.swing.JPanel pnlTxtNgayThi;
    private javax.swing.JLabel lblTxtNgayThi;
    private javax.swing.JTextField txtNgayThi;
    private javax.swing.JPanel pnlTxtLanThi;
    private javax.swing.JLabel lblTxtLanThi;
    private javax.swing.JTextField txtLanThi;
    private javax.swing.JPanel pnlTxtSoCauDung;
    private javax.swing.JLabel lblTxtSoCauDung;
    private javax.swing.JTextField txtSoCauDung;
    private javax.swing.JPanel pnlTxtTongSoCau;
    private javax.swing.JLabel lblTxtTongSoCau;
    private javax.swing.JTextField txtTongSoCau;
    private javax.swing.JButton btnXemChiTiet;
    private javax.swing.JButton btnVeTrangChu;
    // End of variables declaration//GEN-END:variables
}
