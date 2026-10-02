package hethong;

import session.SessionDangNhap;

/**
 * Giao diện SaoLuuPhucHoi. Nghiệp vụ sẽ được bổ sung trong các event handler.
 * Chỉnh bố cục bằng tab Design của NetBeans và giữ file .form đi kèm.
 */
public class SaoLuuPhucHoi extends javax.swing.JFrame {
    private final SessionDangNhap nguoiDung;

    public SaoLuuPhucHoi() {
        this(null);
    }

    public SaoLuuPhucHoi(SessionDangNhap nguoiDung) {
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
        lblTieuDe.setText("SAO LƯU VÀ PHỤC HỒI");
        lblTieuDe.setFont(new java.awt.Font("Segoe UI", 1, 24));
        lblTieuDe.setForeground(new java.awt.Color(205, 32, 45));
        lblMoTa = new javax.swing.JLabel();
        lblMoTa.setText("Cơ sở dữ liệu TRAC_NGHIEM");
        lblMoTa.setFont(new java.awt.Font("Segoe UI", 0, 13));
        lblMoTa.setForeground(new java.awt.Color(107, 114, 128));
        pnlTxtMayChu = new javax.swing.JPanel();
        pnlTxtMayChu.setBackground(new java.awt.Color(255, 255, 255));
        lblTxtMayChu = new javax.swing.JLabel();
        lblTxtMayChu.setText("Máy chủ SQL Server");
        lblTxtMayChu.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblTxtMayChu.setForeground(new java.awt.Color(36, 43, 54));
        txtMayChu = new javax.swing.JTextField();
        txtMayChu.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtMayChu.setForeground(new java.awt.Color(36, 43, 54));
        txtMayChu.setBackground(new java.awt.Color(255, 255, 255));
        txtMayChu.setEditable(true);
        javax.swing.GroupLayout pnlTxtMayChuLayout = new javax.swing.GroupLayout(pnlTxtMayChu);
        pnlTxtMayChu.setLayout(pnlTxtMayChuLayout);
        pnlTxtMayChuLayout.setHorizontalGroup(pnlTxtMayChuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtMayChuLayout.createSequentialGroup()
                .addComponent(lblTxtMayChu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlTxtMayChuLayout.createSequentialGroup()
                .addComponent(txtMayChu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        pnlTxtMayChuLayout.setVerticalGroup(pnlTxtMayChuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtMayChuLayout.createSequentialGroup()
                .addGroup(pnlTxtMayChuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTxtMayChu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlTxtMayChuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMayChu, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))));
        pnlTxtCoSoDuLieu = new javax.swing.JPanel();
        pnlTxtCoSoDuLieu.setBackground(new java.awt.Color(255, 255, 255));
        lblTxtCoSoDuLieu = new javax.swing.JLabel();
        lblTxtCoSoDuLieu.setText("Cơ sở dữ liệu");
        lblTxtCoSoDuLieu.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblTxtCoSoDuLieu.setForeground(new java.awt.Color(36, 43, 54));
        txtCoSoDuLieu = new javax.swing.JTextField();
        txtCoSoDuLieu.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtCoSoDuLieu.setForeground(new java.awt.Color(36, 43, 54));
        txtCoSoDuLieu.setBackground(new java.awt.Color(255, 255, 255));
        txtCoSoDuLieu.setEditable(false);
        txtCoSoDuLieu.setText("TRAC_NGHIEM");
        javax.swing.GroupLayout pnlTxtCoSoDuLieuLayout = new javax.swing.GroupLayout(pnlTxtCoSoDuLieu);
        pnlTxtCoSoDuLieu.setLayout(pnlTxtCoSoDuLieuLayout);
        pnlTxtCoSoDuLieuLayout.setHorizontalGroup(pnlTxtCoSoDuLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtCoSoDuLieuLayout.createSequentialGroup()
                .addComponent(lblTxtCoSoDuLieu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlTxtCoSoDuLieuLayout.createSequentialGroup()
                .addComponent(txtCoSoDuLieu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        pnlTxtCoSoDuLieuLayout.setVerticalGroup(pnlTxtCoSoDuLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtCoSoDuLieuLayout.createSequentialGroup()
                .addGroup(pnlTxtCoSoDuLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTxtCoSoDuLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlTxtCoSoDuLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCoSoDuLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))));
        pnlSaoLuu = new javax.swing.JPanel();
        pnlSaoLuu.setBackground(new java.awt.Color(255, 255, 255));
        lblSaoLuu = new javax.swing.JLabel();
        lblSaoLuu.setText("Sao lưu dữ liệu");
        lblSaoLuu.setFont(new java.awt.Font("Segoe UI", 1, 16));
        lblSaoLuu.setForeground(new java.awt.Color(205, 32, 45));
        pnlTxtTepSaoLuu = new javax.swing.JPanel();
        pnlTxtTepSaoLuu.setBackground(new java.awt.Color(255, 255, 255));
        lblTxtTepSaoLuu = new javax.swing.JLabel();
        lblTxtTepSaoLuu.setText("Tệp sao lưu (.bak)");
        lblTxtTepSaoLuu.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblTxtTepSaoLuu.setForeground(new java.awt.Color(36, 43, 54));
        txtTepSaoLuu = new javax.swing.JTextField();
        txtTepSaoLuu.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtTepSaoLuu.setForeground(new java.awt.Color(36, 43, 54));
        txtTepSaoLuu.setBackground(new java.awt.Color(255, 255, 255));
        txtTepSaoLuu.setEditable(true);
        javax.swing.GroupLayout pnlTxtTepSaoLuuLayout = new javax.swing.GroupLayout(pnlTxtTepSaoLuu);
        pnlTxtTepSaoLuu.setLayout(pnlTxtTepSaoLuuLayout);
        pnlTxtTepSaoLuuLayout.setHorizontalGroup(pnlTxtTepSaoLuuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtTepSaoLuuLayout.createSequentialGroup()
                .addComponent(lblTxtTepSaoLuu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlTxtTepSaoLuuLayout.createSequentialGroup()
                .addComponent(txtTepSaoLuu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        pnlTxtTepSaoLuuLayout.setVerticalGroup(pnlTxtTepSaoLuuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtTepSaoLuuLayout.createSequentialGroup()
                .addGroup(pnlTxtTepSaoLuuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTxtTepSaoLuu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlTxtTepSaoLuuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTepSaoLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))));
        btnChonTepSaoLuu = new javax.swing.JButton();
        btnChonTepSaoLuu.setText("Chọn đường dẫn");
        btnChonTepSaoLuu.setFont(new java.awt.Font("Segoe UI", 0, 14));
        btnChonTepSaoLuu.setBackground(new java.awt.Color(245, 246, 248));
        btnChonTepSaoLuu.setForeground(new java.awt.Color(36, 43, 54));
        btnChonTepSaoLuu.setOpaque(true);
        btnChonTepSaoLuu.setContentAreaFilled(true);
        btnChonTepSaoLuu.setFocusPainted(false);
        btnChonTepSaoLuu.addActionListener(this::btnChonTepSaoLuuActionPerformed);
        chkGhiDeBanSaoLuu = new javax.swing.JCheckBox();
        chkGhiDeBanSaoLuu.setText("Ghi đè tệp sao lưu đã có");
        chkGhiDeBanSaoLuu.setBackground(new java.awt.Color(255, 255, 255));
        chkGhiDeBanSaoLuu.setFont(new java.awt.Font("Segoe UI", 0, 13));
        btnSaoLuu = new javax.swing.JButton();
        btnSaoLuu.setText("Sao lưu");
        btnSaoLuu.setFont(new java.awt.Font("Segoe UI", 1, 14));
        btnSaoLuu.setBackground(new java.awt.Color(205, 32, 45));
        btnSaoLuu.setForeground(new java.awt.Color(255, 255, 255));
        btnSaoLuu.setOpaque(true);
        btnSaoLuu.setContentAreaFilled(true);
        btnSaoLuu.setFocusPainted(false);
        btnSaoLuu.addActionListener(this::btnSaoLuuActionPerformed);
        prgSaoLuu = new javax.swing.JProgressBar();
        prgSaoLuu.setStringPainted(true);
        lblTrangThaiSaoLuu = new javax.swing.JLabel();
        lblTrangThaiSaoLuu.setText("Chưa thực hiện sao lưu");
        lblTrangThaiSaoLuu.setFont(new java.awt.Font("Segoe UI", 0, 13));
        lblTrangThaiSaoLuu.setForeground(new java.awt.Color(107, 114, 128));
        javax.swing.GroupLayout pnlSaoLuuLayout = new javax.swing.GroupLayout(pnlSaoLuu);
        pnlSaoLuu.setLayout(pnlSaoLuuLayout);
        pnlSaoLuuLayout.setHorizontalGroup(pnlSaoLuuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSaoLuuLayout.createSequentialGroup()
                .addComponent(lblSaoLuu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlSaoLuuLayout.createSequentialGroup()
                .addComponent(pnlTxtTepSaoLuu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(btnChonTepSaoLuu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlSaoLuuLayout.createSequentialGroup()
                .addComponent(chkGhiDeBanSaoLuu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlSaoLuuLayout.createSequentialGroup()
                .addComponent(btnSaoLuu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlSaoLuuLayout.createSequentialGroup()
                .addComponent(prgSaoLuu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlSaoLuuLayout.createSequentialGroup()
                .addComponent(lblTrangThaiSaoLuu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        pnlSaoLuuLayout.setVerticalGroup(pnlSaoLuuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSaoLuuLayout.createSequentialGroup()
                .addGroup(pnlSaoLuuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSaoLuu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlSaoLuuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlTxtTepSaoLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnChonTepSaoLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlSaoLuuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkGhiDeBanSaoLuu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlSaoLuuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSaoLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlSaoLuuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(prgSaoLuu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlSaoLuuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTrangThaiSaoLuu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))));
        pnlPhucHoi = new javax.swing.JPanel();
        pnlPhucHoi.setBackground(new java.awt.Color(255, 255, 255));
        lblPhucHoi = new javax.swing.JLabel();
        lblPhucHoi.setText("Phục hồi dữ liệu");
        lblPhucHoi.setFont(new java.awt.Font("Segoe UI", 1, 16));
        lblPhucHoi.setForeground(new java.awt.Color(205, 32, 45));
        pnlTxtTepPhucHoi = new javax.swing.JPanel();
        pnlTxtTepPhucHoi.setBackground(new java.awt.Color(255, 255, 255));
        lblTxtTepPhucHoi = new javax.swing.JLabel();
        lblTxtTepPhucHoi.setText("Tệp phục hồi (.bak)");
        lblTxtTepPhucHoi.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblTxtTepPhucHoi.setForeground(new java.awt.Color(36, 43, 54));
        txtTepPhucHoi = new javax.swing.JTextField();
        txtTepPhucHoi.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtTepPhucHoi.setForeground(new java.awt.Color(36, 43, 54));
        txtTepPhucHoi.setBackground(new java.awt.Color(255, 255, 255));
        txtTepPhucHoi.setEditable(true);
        javax.swing.GroupLayout pnlTxtTepPhucHoiLayout = new javax.swing.GroupLayout(pnlTxtTepPhucHoi);
        pnlTxtTepPhucHoi.setLayout(pnlTxtTepPhucHoiLayout);
        pnlTxtTepPhucHoiLayout.setHorizontalGroup(pnlTxtTepPhucHoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtTepPhucHoiLayout.createSequentialGroup()
                .addComponent(lblTxtTepPhucHoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlTxtTepPhucHoiLayout.createSequentialGroup()
                .addComponent(txtTepPhucHoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        pnlTxtTepPhucHoiLayout.setVerticalGroup(pnlTxtTepPhucHoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtTepPhucHoiLayout.createSequentialGroup()
                .addGroup(pnlTxtTepPhucHoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTxtTepPhucHoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlTxtTepPhucHoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTepPhucHoi, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))));
        btnChonTepPhucHoi = new javax.swing.JButton();
        btnChonTepPhucHoi.setText("Chọn tệp");
        btnChonTepPhucHoi.setFont(new java.awt.Font("Segoe UI", 0, 14));
        btnChonTepPhucHoi.setBackground(new java.awt.Color(245, 246, 248));
        btnChonTepPhucHoi.setForeground(new java.awt.Color(36, 43, 54));
        btnChonTepPhucHoi.setOpaque(true);
        btnChonTepPhucHoi.setContentAreaFilled(true);
        btnChonTepPhucHoi.setFocusPainted(false);
        btnChonTepPhucHoi.addActionListener(this::btnChonTepPhucHoiActionPerformed);
        chkXacNhanPhucHoi = new javax.swing.JCheckBox();
        chkXacNhanPhucHoi.setText("Xác nhận thay thế dữ liệu hiện tại");
        chkXacNhanPhucHoi.setBackground(new java.awt.Color(255, 255, 255));
        chkXacNhanPhucHoi.setFont(new java.awt.Font("Segoe UI", 0, 13));
        btnPhucHoi = new javax.swing.JButton();
        btnPhucHoi.setText("Phục hồi");
        btnPhucHoi.setFont(new java.awt.Font("Segoe UI", 1, 14));
        btnPhucHoi.setBackground(new java.awt.Color(205, 32, 45));
        btnPhucHoi.setForeground(new java.awt.Color(255, 255, 255));
        btnPhucHoi.setOpaque(true);
        btnPhucHoi.setContentAreaFilled(true);
        btnPhucHoi.setFocusPainted(false);
        btnPhucHoi.addActionListener(this::btnPhucHoiActionPerformed);
        prgPhucHoi = new javax.swing.JProgressBar();
        prgPhucHoi.setStringPainted(true);
        lblTrangThaiPhucHoi = new javax.swing.JLabel();
        lblTrangThaiPhucHoi.setText("Chưa thực hiện phục hồi");
        lblTrangThaiPhucHoi.setFont(new java.awt.Font("Segoe UI", 0, 13));
        lblTrangThaiPhucHoi.setForeground(new java.awt.Color(107, 114, 128));
        javax.swing.GroupLayout pnlPhucHoiLayout = new javax.swing.GroupLayout(pnlPhucHoi);
        pnlPhucHoi.setLayout(pnlPhucHoiLayout);
        pnlPhucHoiLayout.setHorizontalGroup(pnlPhucHoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPhucHoiLayout.createSequentialGroup()
                .addComponent(lblPhucHoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlPhucHoiLayout.createSequentialGroup()
                .addComponent(pnlTxtTepPhucHoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(btnChonTepPhucHoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlPhucHoiLayout.createSequentialGroup()
                .addComponent(chkXacNhanPhucHoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlPhucHoiLayout.createSequentialGroup()
                .addComponent(btnPhucHoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlPhucHoiLayout.createSequentialGroup()
                .addComponent(prgPhucHoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlPhucHoiLayout.createSequentialGroup()
                .addComponent(lblTrangThaiPhucHoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        pnlPhucHoiLayout.setVerticalGroup(pnlPhucHoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPhucHoiLayout.createSequentialGroup()
                .addGroup(pnlPhucHoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPhucHoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlPhucHoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlTxtTepPhucHoi, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnChonTepPhucHoi, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlPhucHoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkXacNhanPhucHoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlPhucHoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPhucHoi, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlPhucHoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(prgPhucHoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlPhucHoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTrangThaiPhucHoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))));
        pnlTxtNhatKy = new javax.swing.JPanel();
        pnlTxtNhatKy.setBackground(new java.awt.Color(255, 255, 255));
        lblTxtNhatKy = new javax.swing.JLabel();
        lblTxtNhatKy.setText("Nhật ký thực hiện");
        lblTxtNhatKy.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblTxtNhatKy.setForeground(new java.awt.Color(36, 43, 54));
        scrTxtNhatKy = new javax.swing.JScrollPane();
        txtNhatKy = new javax.swing.JTextArea();
        txtNhatKy.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtNhatKy.setLineWrap(true);
        txtNhatKy.setWrapStyleWord(true);
        txtNhatKy.setRows(3);
        txtNhatKy.setColumns(24);
        txtNhatKy.setEditable(false);
        scrTxtNhatKy.setViewportView(txtNhatKy);
        javax.swing.GroupLayout pnlTxtNhatKyLayout = new javax.swing.GroupLayout(pnlTxtNhatKy);
        pnlTxtNhatKy.setLayout(pnlTxtNhatKyLayout);
        pnlTxtNhatKyLayout.setHorizontalGroup(pnlTxtNhatKyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtNhatKyLayout.createSequentialGroup()
                .addComponent(lblTxtNhatKy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlTxtNhatKyLayout.createSequentialGroup()
                .addComponent(scrTxtNhatKy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        pnlTxtNhatKyLayout.setVerticalGroup(pnlTxtNhatKyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtNhatKyLayout.createSequentialGroup()
                .addGroup(pnlTxtNhatKyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTxtNhatKy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlTxtNhatKyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrTxtNhatKy, javax.swing.GroupLayout.PREFERRED_SIZE, 80, Short.MAX_VALUE))));
        javax.swing.GroupLayout pnlNoiDungLayout = new javax.swing.GroupLayout(pnlNoiDung);
        pnlNoiDung.setLayout(pnlNoiDungLayout);
        pnlNoiDungLayout.setHorizontalGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(lblTieuDe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(lblMoTa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(pnlTxtMayChu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(pnlTxtCoSoDuLieu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(pnlSaoLuu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(pnlPhucHoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(pnlTxtNhatKy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        pnlNoiDungLayout.setVerticalGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTieuDe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMoTa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlTxtMayChu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlTxtCoSoDuLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlSaoLuu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlPhucHoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlTxtNhatKy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))));
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(pnlNoiDung, javax.swing.GroupLayout.DEFAULT_SIZE, 950, Short.MAX_VALUE)
                .addGap(16, 16, 16)));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlNoiDung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)));
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PTIT | SAO LƯU VÀ PHỤC HỒI");
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/ptit-logo.png")).getImage());
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnChonTepSaoLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonTepSaoLuuActionPerformed
        // TODO: Bổ sung xử lý cho nút này sau khi hoàn thành giao diện.
    }//GEN-LAST:event_btnChonTepSaoLuuActionPerformed

    private void btnSaoLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaoLuuActionPerformed
        // TODO: Bổ sung xử lý cho nút này sau khi hoàn thành giao diện.
    }//GEN-LAST:event_btnSaoLuuActionPerformed

    private void btnChonTepPhucHoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonTepPhucHoiActionPerformed
        // TODO: Bổ sung xử lý cho nút này sau khi hoàn thành giao diện.
    }//GEN-LAST:event_btnChonTepPhucHoiActionPerformed

    private void btnPhucHoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPhucHoiActionPerformed
        // TODO: Bổ sung xử lý cho nút này sau khi hoàn thành giao diện.
    }//GEN-LAST:event_btnPhucHoiActionPerformed

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
    private javax.swing.JPanel pnlNoiDung;
    private javax.swing.JLabel lblTieuDe;
    private javax.swing.JLabel lblMoTa;
    private javax.swing.JPanel pnlTxtMayChu;
    private javax.swing.JLabel lblTxtMayChu;
    private javax.swing.JTextField txtMayChu;
    private javax.swing.JPanel pnlTxtCoSoDuLieu;
    private javax.swing.JLabel lblTxtCoSoDuLieu;
    private javax.swing.JTextField txtCoSoDuLieu;
    private javax.swing.JPanel pnlSaoLuu;
    private javax.swing.JLabel lblSaoLuu;
    private javax.swing.JPanel pnlTxtTepSaoLuu;
    private javax.swing.JLabel lblTxtTepSaoLuu;
    private javax.swing.JTextField txtTepSaoLuu;
    private javax.swing.JButton btnChonTepSaoLuu;
    private javax.swing.JCheckBox chkGhiDeBanSaoLuu;
    private javax.swing.JButton btnSaoLuu;
    private javax.swing.JProgressBar prgSaoLuu;
    private javax.swing.JLabel lblTrangThaiSaoLuu;
    private javax.swing.JPanel pnlPhucHoi;
    private javax.swing.JLabel lblPhucHoi;
    private javax.swing.JPanel pnlTxtTepPhucHoi;
    private javax.swing.JLabel lblTxtTepPhucHoi;
    private javax.swing.JTextField txtTepPhucHoi;
    private javax.swing.JButton btnChonTepPhucHoi;
    private javax.swing.JCheckBox chkXacNhanPhucHoi;
    private javax.swing.JButton btnPhucHoi;
    private javax.swing.JProgressBar prgPhucHoi;
    private javax.swing.JLabel lblTrangThaiPhucHoi;
    private javax.swing.JPanel pnlTxtNhatKy;
    private javax.swing.JLabel lblTxtNhatKy;
    private javax.swing.JScrollPane scrTxtNhatKy;
    private javax.swing.JTextArea txtNhatKy;
    // End of variables declaration//GEN-END:variables
}
