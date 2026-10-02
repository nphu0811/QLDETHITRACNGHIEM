package thi;

import session.SessionDangNhap;
import session.sessionSinhVien;
/**
 * Giao diện ChonCaThi. Nghiệp vụ sẽ được bổ sung trong các event handler.
 * Chỉnh bố cục bằng tab Design của NetBeans và giữ file .form đi kèm.
 */
public class ChonCaThi extends javax.swing.JFrame {
    private final SessionDangNhap nguoiDung;

    public ChonCaThi() {
        this(null);
    }

    public ChonCaThi(SessionDangNhap nguoiDung) {
        initComponents();
        this.nguoiDung = nguoiDung;
        if(nguoiDung instanceof sessionSinhVien){
            sessionSinhVien sinhVien = (sessionSinhVien) nguoiDung;
            txtMaSV.setText(sinhVien.getMaSV());
            txtHoTen.setText(sinhVien.getHo() + " " + sinhVien.getTen());
            txtMaLop.setText(sinhVien.getMaLop());
            txtTenLop.setText(sinhVien.getTenLop());
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
        lblTieuDe.setText("CHỌN CA THI");
        lblTieuDe.setFont(new java.awt.Font("Segoe UI", 1, 24));
        lblTieuDe.setForeground(new java.awt.Color(205, 32, 45));
        lblMoTa = new javax.swing.JLabel();
        lblMoTa.setText("Thông tin sinh viên và ca thi đã được chuẩn bị");
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
        pnlCboMonHoc = new javax.swing.JPanel();
        pnlCboMonHoc.setBackground(new java.awt.Color(255, 255, 255));
        lblCboMonHoc = new javax.swing.JLabel();
        lblCboMonHoc.setText("Môn thi");
        lblCboMonHoc.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblCboMonHoc.setForeground(new java.awt.Color(36, 43, 54));
        cboMonHoc = new javax.swing.JComboBox();
        cboMonHoc.setFont(new java.awt.Font("Segoe UI", 0, 14));
        cboMonHoc.setForeground(new java.awt.Color(36, 43, 54));
        cboMonHoc.setBackground(new java.awt.Color(255, 255, 255));
        cboMonHoc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"Chọn môn thi"}));
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
        pnlTxtSoCauThi = new javax.swing.JPanel();
        pnlTxtSoCauThi.setBackground(new java.awt.Color(255, 255, 255));
        lblTxtSoCauThi = new javax.swing.JLabel();
        lblTxtSoCauThi.setText("Số câu thi");
        lblTxtSoCauThi.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblTxtSoCauThi.setForeground(new java.awt.Color(36, 43, 54));
        txtSoCauThi = new javax.swing.JTextField();
        txtSoCauThi.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtSoCauThi.setForeground(new java.awt.Color(36, 43, 54));
        txtSoCauThi.setBackground(new java.awt.Color(255, 255, 255));
        txtSoCauThi.setEditable(false);
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
        lblTxtThoiGian.setText("Thời gian (phút)");
        lblTxtThoiGian.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblTxtThoiGian.setForeground(new java.awt.Color(36, 43, 54));
        txtThoiGian = new javax.swing.JTextField();
        txtThoiGian.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtThoiGian.setForeground(new java.awt.Color(36, 43, 54));
        txtThoiGian.setBackground(new java.awt.Color(255, 255, 255));
        txtThoiGian.setEditable(false);
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
        scrTblCaThi = new javax.swing.JScrollPane();
        tblCaThi = new javax.swing.JTable();
        tblCaThi.setFont(new java.awt.Font("Segoe UI", 0, 13));
        tblCaThi.setRowHeight(28);
        tblCaThi.setAutoCreateRowSorter(true);
        tblCaThi.setFillsViewportHeight(true);
        tblCaThi.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {}, new String[] {"Môn thi", "Ngày thi", "Lần thi", "Trình độ", "Số câu", "Thời gian"}) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        });
        scrTblCaThi.setViewportView(tblCaThi);
        btnBatDauThi = new javax.swing.JButton();
        btnBatDauThi.setText("Bắt đầu thi");
        btnBatDauThi.setFont(new java.awt.Font("Segoe UI", 1, 14));
        btnBatDauThi.setBackground(new java.awt.Color(205, 32, 45));
        btnBatDauThi.setForeground(new java.awt.Color(255, 255, 255));
        btnBatDauThi.setOpaque(true);
        btnBatDauThi.setContentAreaFilled(true);
        btnBatDauThi.setFocusPainted(false);
        btnBatDauThi.addActionListener(this::btnBatDauThiActionPerformed);
        btnXemBaiLanTruoc = new javax.swing.JButton();
        btnXemBaiLanTruoc.setText("Xem bài thi lần trước");
        btnXemBaiLanTruoc.setFont(new java.awt.Font("Segoe UI", 0, 14));
        btnXemBaiLanTruoc.setBackground(new java.awt.Color(245, 246, 248));
        btnXemBaiLanTruoc.setForeground(new java.awt.Color(36, 43, 54));
        btnXemBaiLanTruoc.setOpaque(true);
        btnXemBaiLanTruoc.setContentAreaFilled(true);
        btnXemBaiLanTruoc.setFocusPainted(false);
        btnXemBaiLanTruoc.addActionListener(this::btnXemBaiLanTruocActionPerformed);
        btnTroVe = new javax.swing.JButton();
        btnTroVe.setText("Trở về");
        btnTroVe.setFont(new java.awt.Font("Segoe UI", 0, 14));
        btnTroVe.setBackground(new java.awt.Color(245, 246, 248));
        btnTroVe.setForeground(new java.awt.Color(36, 43, 54));
        btnTroVe.setOpaque(true);
        btnTroVe.setContentAreaFilled(true);
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
                .addComponent(pnlTxtHoTen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(pnlTxtMaLop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(pnlTxtTenLop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(pnlCboMonHoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(pnlTxtNgayThi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(pnlCboLanThi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(pnlTxtTrinhDo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(pnlTxtSoCauThi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(pnlTxtThoiGian, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(scrTblCaThi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(btnBatDauThi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(btnXemBaiLanTruoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(btnTroVe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
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
                    .addComponent(pnlCboMonHoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlTxtNgayThi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlCboLanThi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlTxtTrinhDo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlTxtSoCauThi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlTxtThoiGian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrTblCaThi, javax.swing.GroupLayout.PREFERRED_SIZE, 180, Short.MAX_VALUE))
                .addGap(8, 8, 8)
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBatDauThi, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXemBaiLanTruoc, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTroVe, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))));
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
        setTitle("PTIT | CHỌN CA THI");
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/ptit-logo.png")).getImage());
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBatDauThiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatDauThiActionPerformed
        trangchu.DieuHuong.moForm(this, new thi.LamBaiThi(nguoiDung), nguoiDung);
    }//GEN-LAST:event_btnBatDauThiActionPerformed

    private void btnXemBaiLanTruocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemBaiLanTruocActionPerformed
        trangchu.DieuHuong.moForm(this, new baocao.XemKetQua(nguoiDung), nguoiDung);
    }//GEN-LAST:event_btnXemBaiLanTruocActionPerformed

    private void btnTroVeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTroVeActionPerformed
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
            java.util.logging.Logger.getLogger(ChonCaThi.class.getName()).log(java.util.logging.Level.WARNING, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> new ChonCaThi().setVisible(true));
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
    private javax.swing.JPanel pnlCboMonHoc;
    private javax.swing.JLabel lblCboMonHoc;
    private javax.swing.JComboBox cboMonHoc;
    private javax.swing.JPanel pnlTxtNgayThi;
    private javax.swing.JLabel lblTxtNgayThi;
    private javax.swing.JFormattedTextField txtNgayThi;
    private javax.swing.JPanel pnlCboLanThi;
    private javax.swing.JLabel lblCboLanThi;
    private javax.swing.JComboBox cboLanThi;
    private javax.swing.JPanel pnlTxtTrinhDo;
    private javax.swing.JLabel lblTxtTrinhDo;
    private javax.swing.JTextField txtTrinhDo;
    private javax.swing.JPanel pnlTxtSoCauThi;
    private javax.swing.JLabel lblTxtSoCauThi;
    private javax.swing.JTextField txtSoCauThi;
    private javax.swing.JPanel pnlTxtThoiGian;
    private javax.swing.JLabel lblTxtThoiGian;
    private javax.swing.JTextField txtThoiGian;
    private javax.swing.JScrollPane scrTblCaThi;
    private javax.swing.JTable tblCaThi;
    private javax.swing.JButton btnBatDauThi;
    private javax.swing.JButton btnXemBaiLanTruoc;
    private javax.swing.JButton btnTroVe;
    // End of variables declaration//GEN-END:variables
}
