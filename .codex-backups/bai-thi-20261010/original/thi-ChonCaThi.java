package thi;

import connectionSQLServer.connection;
import java.awt.Component;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Types;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import session.MonHoc;
import session.SessionDangNhap;
import session.sessionSinhVien;
import session.CaThi;

/**
 * Chọn và lọc ca thi của sinh viên đang đăng nhập.
 * Chỉnh bố cục bằng tab Design của NetBeans và giữ file .form đi kèm.
 */
public class ChonCaThi extends javax.swing.JFrame {

    private final SessionDangNhap nguoiDung;
    private boolean dangNapMonHoc = true;
    private boolean dangThongBaoNgayThi;
    private int lanTaiCaThi;
    private CaThi cathi;
    private final DateTimeFormatter dinhDangNgay = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // Chữ gợi ý chỉ hiện trên ô khi chưa chọn, không nằm trong danh sách.
    private void hienThiGoiY(JComboBox<?> comboBox, String goiY) {
        comboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value,
                    int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (index == -1 && value == null) {
                    setText(goiY);
                }
                return this;
            }
        });
    }

    // Trả về thông báo nếu ngày sai; trả về null nếu ngày hợp lệ.
    private String kiemTraNgayThi(String ngayNhap) {
        if (!ngayNhap.matches("[0-9]{2}/[0-9]{2}/[0-9]{4}")) {
            return "Nhập ngày theo dạng dd/MM/yyyy. Ví dụ: 09/10/2026.";
        }

        int ngay = Integer.parseInt(ngayNhap.substring(0, 2));
        int thang = Integer.parseInt(ngayNhap.substring(3, 5));
        int nam = Integer.parseInt(ngayNhap.substring(6, 10));

        if (nam < 1 || nam > 9999) {
            return "Năm phải từ 0001 đến 9999.";
        }
        if (thang < 1 || thang > 12) {
            return "Tháng phải từ 1 đến 12.";
        }

        int soNgayTrongThang;
        if (thang == 2) {
            boolean namNhuan = (nam % 4 == 0 && nam % 100 != 0) || nam % 400 == 0;
            if (namNhuan) {
                soNgayTrongThang = 29;
            } else {
                soNgayTrongThang = 28;
            }
        } else if (thang == 4 || thang == 6 || thang == 9 || thang == 11) {
            soNgayTrongThang = 30;
        } else {
            soNgayTrongThang = 31;
        }

        if (ngay < 1 || ngay > soNgayTrongThang) {
            return "Tháng " + thang + " năm " + nam
                    + " chỉ có ngày từ 1 đến " + soNgayTrongThang + ".";
        }
        return null;
    }

    public void loadCaThiTable() {
        if (!(nguoiDung instanceof sessionSinhVien) || dangThongBaoNgayThi) {
            return;
        }

        // Đọc các ô trên giao diện trước khi chạy SQL ở nền.
        String maSV = nguoiDung.getMaNguoiDung();
        MonHoc monHoc = (MonHoc) cboMonHoc.getSelectedItem();
        String maMH;
        if (monHoc == null) {
            maMH = null;
        } else {
            maMH = monHoc.getMaMH();
        }

        String ngayNhap = txtNgayThi.getText().trim();
        String lanNhap = (String) cboLanThi.getSelectedItem();
        Short lanThi;
        if (lanNhap == null) {
            lanThi = null;
        } else {
            lanThi = Short.valueOf(lanNhap);
        }

        Date ngayThi;
        if (ngayNhap.isEmpty() || ngayNhap.equals("__/__/____")) {
            ngayThi = null;
        } else {
            String loi = kiemTraNgayThi(ngayNhap);
            if (loi != null) {
                dangThongBaoNgayThi = true;
                JOptionPane.showMessageDialog(this, loi);
                dangThongBaoNgayThi = false;
                return;
            }

            int ngay = Integer.parseInt(ngayNhap.substring(0, 2));
            int thang = Integer.parseInt(ngayNhap.substring(3, 5));
            int nam = Integer.parseInt(ngayNhap.substring(6, 10));
            ngayThi = Date.valueOf(LocalDate.of(nam, thang, ngay));
        }

        // Khi tải lại danh sách, bỏ ca đã chọn và chờ dữ liệu mới.
        int lanTaiHienTai = ++lanTaiCaThi;
        tblCaThi.clearSelection();
        cathi = null;
        btnBatDauThi.setEnabled(false);

        new SwingWorker<Void, Void>() {
            ArrayList<Object[]> danhSachCaThi = new ArrayList<>();
            String thongBao;

            @Override
            protected Void doInBackground() {
                try (Connection ketnoi = connection.getConnectionSV()) {
                    if (ketnoi == null) {
                        thongBao = "Không thể kết nối tới SQL Server!";
                        return null;
                    }
                    try (CallableStatement cs = ketnoi.prepareCall("{call dbo.SP_LayCaThiSinhVien(?,?,?,?)}")) {
                        cs.setQueryTimeout(15);
                        cs.setString(1, maSV);

                        if (maMH == null) {
                            cs.setNull(2, Types.NCHAR);
                        } else {
                            cs.setString(2, maMH);
                        }

                        if (ngayThi == null) {
                            cs.setNull(3, Types.DATE);
                        } else {
                            cs.setDate(3, ngayThi);
                        }

                        if (lanThi == null) {
                            cs.setNull(4, Types.SMALLINT);
                        } else {
                            cs.setShort(4, lanThi);
                        }

                        try (ResultSet rs = cs.executeQuery()) {
                            while (rs.next()) {
                                Date ngay = rs.getDate("NGAYTHI");
                                String ngayHienThi = "";
                                if (ngay == null || ngay.toLocalDate().isBefore(LocalDate.now())) {
                                    continue;
                                }
                                ngayHienThi = ngay.toLocalDate().format(dinhDangNgay);
                                Object[] row = {
                                    new MonHoc(rs.getString("MAMH").trim(), rs.getString("TENMH")),
                                    ngayHienThi,
                                    rs.getString("LAN"),
                                    rs.getString("TRINHDO"),
                                    rs.getString("SOCAUTHI"),
                                    rs.getString("THOIGIAN")
                                };
                                danhSachCaThi.add(row);
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    thongBao = "Không thể tải danh sách ca thi: " + e.getMessage();
                }
                return null;
            }

            @Override
            protected void done() {
                // Chỉ hiện kết quả của bộ lọc mới nhất nếu người dùng đổi liên tiếp.
                if (!isDisplayable() || lanTaiHienTai != lanTaiCaThi) {
                    return;
                }
                DefaultTableModel model = (DefaultTableModel) tblCaThi.getModel();
                tblCaThi.clearSelection();
                model.setRowCount(0);
                btnBatDauThi.setEnabled(true);
                if (thongBao != null) {
                    JOptionPane.showMessageDialog(ChonCaThi.this, thongBao);
                    return;
                }
                for (Object[] row : danhSachCaThi) {
                    model.addRow(row);
                }
            }
        }.execute();
    }

    public void loadComboBox() {
        dangNapMonHoc = true;
        cboMonHoc.removeAllItems();
        cboMonHoc.setEnabled(false);

        // Tải môn ở nền để form không bị đơ khi chờ SQL.
        new SwingWorker<Void, Void>() {
            ArrayList<MonHoc> danhSachMonHoc = new ArrayList<>();
            String thongBao;

            @Override
            protected Void doInBackground() {
                try (Connection ketnoi = connection.getConnectionSV()) {
                    if (ketnoi == null) {
                        thongBao = "Không thể kết nối tới SQL Server!";
                        return null;
                    }
                    try (CallableStatement cs = ketnoi.prepareCall("{call dbo.SP_LayMonHoc}")) {
                        cs.setQueryTimeout(15);
                        try (ResultSet rs = cs.executeQuery()) {
                            while (rs.next()) {
                                String maMH = rs.getString("MAMH").trim();
                                String tenMH = rs.getString("TENMH");
                                if (tenMH == null) {
                                    tenMH = maMH;
                                }
                                danhSachMonHoc.add(new MonHoc(maMH, tenMH.trim()));
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    thongBao = "Không thể tải danh sách môn học: " + e.getMessage();
                }
                return null;
            }

            @Override
            protected void done() {
                if (!isDisplayable()) {
                    return;
                }
                cboMonHoc.setEnabled(true);
                if (thongBao != null) {
                    dangNapMonHoc = false;
                    JOptionPane.showMessageDialog(ChonCaThi.this, thongBao);
                    return;
                }
                for (MonHoc monHoc : danhSachMonHoc) {
                    cboMonHoc.addItem(monHoc);
                }
                cboMonHoc.setSelectedIndex(-1);
                dangNapMonHoc = false;
            }
        }.execute();
    }

    public ChonCaThi() {
        this(null);
    }

    public ChonCaThi(SessionDangNhap nguoiDung) {
        initComponents();
        this.nguoiDung = nguoiDung;
        // Enter vẫn lọc được khi ô ngày trống hoặc chưa nhập đủ.
        txtNgayThi.getActionMap().put(javax.swing.JTextField.notifyAction, new javax.swing.AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadCaThiTable();
            }
        });
        hienThiGoiY(cboMonHoc, "Chọn môn thi");
        hienThiGoiY(cboLanThi, "Chọn lần thi");
        if (nguoiDung instanceof sessionSinhVien) {
            sessionSinhVien sinhVien = (sessionSinhVien) nguoiDung;
            txtMaSV.setText(sinhVien.getMaSV());
            txtHoTen.setText(sinhVien.getHo() + " " + sinhVien.getTen());
            txtMaLop.setText(sinhVien.getMaLop());
            txtTenLop.setText(sinhVien.getTenLop());
        }
        getContentPane().setBackground(new java.awt.Color(245, 246, 248));
        setLocationRelativeTo(null);
        loadComboBox();
        loadCaThiTable();
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlNoiDung = new javax.swing.JPanel();
        lblTieuDe = new javax.swing.JLabel();
        lblMoTa = new javax.swing.JLabel();
        pnlTxtMaSV = new javax.swing.JPanel();
        lblTxtMaSV = new javax.swing.JLabel();
        txtMaSV = new javax.swing.JTextField();
        pnlTxtHoTen = new javax.swing.JPanel();
        lblTxtHoTen = new javax.swing.JLabel();
        txtHoTen = new javax.swing.JTextField();
        pnlTxtMaLop = new javax.swing.JPanel();
        lblTxtMaLop = new javax.swing.JLabel();
        txtMaLop = new javax.swing.JTextField();
        pnlTxtTenLop = new javax.swing.JPanel();
        lblTxtTenLop = new javax.swing.JLabel();
        txtTenLop = new javax.swing.JTextField();
        pnlCboMonHoc = new javax.swing.JPanel();
        lblCboMonHoc = new javax.swing.JLabel();
        cboMonHoc = new javax.swing.JComboBox();
        pnlTxtNgayThi = new javax.swing.JPanel();
        lblTxtNgayThi = new javax.swing.JLabel();
        txtNgayThi = new javax.swing.JFormattedTextField();
        pnlCboLanThi = new javax.swing.JPanel();
        lblCboLanThi = new javax.swing.JLabel();
        cboLanThi = new javax.swing.JComboBox();
        scrTblCaThi = new javax.swing.JScrollPane();
        tblCaThi = new javax.swing.JTable();
        btnBatDauThi = new javax.swing.JButton();
        btnTroVe = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PTIT | CHỌN CA THI");
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/ptit-logo.png")).getImage());

        pnlNoiDung.setBackground(new java.awt.Color(255, 255, 255));
        pnlNoiDung.setBorder(javax.swing.BorderFactory.createEmptyBorder(24, 24, 24, 24));

        lblTieuDe.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTieuDe.setForeground(new java.awt.Color(205, 32, 45));
        lblTieuDe.setText("CHỌN CA THI");

        lblMoTa.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblMoTa.setForeground(new java.awt.Color(107, 114, 128));
        lblMoTa.setText("Thông tin sinh viên và ca thi đã được chuẩn bị");

        pnlTxtMaSV.setBackground(new java.awt.Color(255, 255, 255));

        lblTxtMaSV.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblTxtMaSV.setForeground(new java.awt.Color(36, 43, 54));
        lblTxtMaSV.setText("Mã sinh viên");

        txtMaSV.setEditable(false);
        txtMaSV.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMaSV.setForeground(new java.awt.Color(36, 43, 54));

        javax.swing.GroupLayout pnlTxtMaSVLayout = new javax.swing.GroupLayout(pnlTxtMaSV);
        pnlTxtMaSV.setLayout(pnlTxtMaSVLayout);
        pnlTxtMaSVLayout.setHorizontalGroup(
            pnlTxtMaSVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTxtMaSV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(txtMaSV)
        );
        pnlTxtMaSVLayout.setVerticalGroup(
            pnlTxtMaSVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtMaSVLayout.createSequentialGroup()
                .addComponent(lblTxtMaSV)
                .addGap(8, 8, 8)
                .addComponent(txtMaSV, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlTxtHoTen.setBackground(new java.awt.Color(255, 255, 255));

        lblTxtHoTen.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblTxtHoTen.setForeground(new java.awt.Color(36, 43, 54));
        lblTxtHoTen.setText("Họ tên");

        txtHoTen.setEditable(false);
        txtHoTen.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtHoTen.setForeground(new java.awt.Color(36, 43, 54));

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

        pnlTxtMaLop.setBackground(new java.awt.Color(255, 255, 255));

        lblTxtMaLop.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblTxtMaLop.setForeground(new java.awt.Color(36, 43, 54));
        lblTxtMaLop.setText("Mã lớp");

        txtMaLop.setEditable(false);
        txtMaLop.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMaLop.setForeground(new java.awt.Color(36, 43, 54));

        javax.swing.GroupLayout pnlTxtMaLopLayout = new javax.swing.GroupLayout(pnlTxtMaLop);
        pnlTxtMaLop.setLayout(pnlTxtMaLopLayout);
        pnlTxtMaLopLayout.setHorizontalGroup(
            pnlTxtMaLopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTxtMaLop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(txtMaLop)
        );
        pnlTxtMaLopLayout.setVerticalGroup(
            pnlTxtMaLopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtMaLopLayout.createSequentialGroup()
                .addComponent(lblTxtMaLop)
                .addGap(8, 8, 8)
                .addComponent(txtMaLop, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlTxtTenLop.setBackground(new java.awt.Color(255, 255, 255));

        lblTxtTenLop.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblTxtTenLop.setForeground(new java.awt.Color(36, 43, 54));
        lblTxtTenLop.setText("Tên lớp");

        txtTenLop.setEditable(false);
        txtTenLop.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTenLop.setForeground(new java.awt.Color(36, 43, 54));

        javax.swing.GroupLayout pnlTxtTenLopLayout = new javax.swing.GroupLayout(pnlTxtTenLop);
        pnlTxtTenLop.setLayout(pnlTxtTenLopLayout);
        pnlTxtTenLopLayout.setHorizontalGroup(
            pnlTxtTenLopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTxtTenLop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(txtTenLop)
        );
        pnlTxtTenLopLayout.setVerticalGroup(
            pnlTxtTenLopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtTenLopLayout.createSequentialGroup()
                .addComponent(lblTxtTenLop)
                .addGap(8, 8, 8)
                .addComponent(txtTenLop, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlCboMonHoc.setBackground(new java.awt.Color(255, 255, 255));

        lblCboMonHoc.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblCboMonHoc.setForeground(new java.awt.Color(36, 43, 54));
        lblCboMonHoc.setText("Môn thi");

        cboMonHoc.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cboMonHoc.setForeground(new java.awt.Color(36, 43, 54));
        cboMonHoc.addActionListener(this::cboMonHocActionPerformed);

        javax.swing.GroupLayout pnlCboMonHocLayout = new javax.swing.GroupLayout(pnlCboMonHoc);
        pnlCboMonHoc.setLayout(pnlCboMonHocLayout);
        pnlCboMonHocLayout.setHorizontalGroup(
            pnlCboMonHocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblCboMonHoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlCboMonHocLayout.createSequentialGroup()
                .addComponent(cboMonHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlCboMonHocLayout.setVerticalGroup(
            pnlCboMonHocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCboMonHocLayout.createSequentialGroup()
                .addComponent(lblCboMonHoc)
                .addGap(8, 8, 8)
                .addComponent(cboMonHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlTxtNgayThi.setBackground(new java.awt.Color(255, 255, 255));

        lblTxtNgayThi.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblTxtNgayThi.setForeground(new java.awt.Color(36, 43, 54));
        lblTxtNgayThi.setText("Ngày thi");

        txtNgayThi.setForeground(new java.awt.Color(36, 43, 54));
        try {
            txtNgayThi.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtNgayThi.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        txtNgayThi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ((javax.swing.text.MaskFormatter) txtNgayThi.getFormatter()).setPlaceholderCharacter('_');
        txtNgayThi.setValue(null);
        txtNgayThi.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNgayThiFocusLost(evt);
            }
        });
        txtNgayThi.addActionListener(this::txtNgayThiActionPerformed);

        javax.swing.GroupLayout pnlTxtNgayThiLayout = new javax.swing.GroupLayout(pnlTxtNgayThi);
        pnlTxtNgayThi.setLayout(pnlTxtNgayThiLayout);
        pnlTxtNgayThiLayout.setHorizontalGroup(
            pnlTxtNgayThiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTxtNgayThi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(txtNgayThi, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
        );
        pnlTxtNgayThiLayout.setVerticalGroup(
            pnlTxtNgayThiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtNgayThiLayout.createSequentialGroup()
                .addComponent(lblTxtNgayThi)
                .addGap(8, 8, 8)
                .addComponent(txtNgayThi, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlCboLanThi.setBackground(new java.awt.Color(255, 255, 255));

        lblCboLanThi.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblCboLanThi.setForeground(new java.awt.Color(36, 43, 54));
        lblCboLanThi.setText("Lần thi");

        cboLanThi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cboLanThi.setForeground(new java.awt.Color(36, 43, 54));
        cboLanThi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2" }));
        cboLanThi.setSelectedIndex(-1);
        cboLanThi.addActionListener(this::cboLanThiActionPerformed);

        javax.swing.GroupLayout pnlCboLanThiLayout = new javax.swing.GroupLayout(pnlCboLanThi);
        pnlCboLanThi.setLayout(pnlCboLanThiLayout);
        pnlCboLanThiLayout.setHorizontalGroup(
            pnlCboLanThiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblCboLanThi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(cboLanThi, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlCboLanThiLayout.setVerticalGroup(
            pnlCboLanThiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCboLanThiLayout.createSequentialGroup()
                .addComponent(lblCboLanThi)
                .addGap(8, 8, 8)
                .addComponent(cboLanThi, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        tblCaThi.setAutoCreateRowSorter(true);
        tblCaThi.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        tblCaThi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Môn thi", "Ngày thi", "Lần thi", "Trình độ", "Số câu", "Thời gian"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCaThi.setFillsViewportHeight(true);
        tblCaThi.setRowHeight(28);
        scrTblCaThi.setViewportView(tblCaThi);

        btnBatDauThi.setBackground(new java.awt.Color(205, 32, 45));
        btnBatDauThi.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnBatDauThi.setForeground(new java.awt.Color(255, 255, 255));
        btnBatDauThi.setText("Bắt đầu thi");
        btnBatDauThi.setFocusPainted(false);
        btnBatDauThi.setOpaque(true);
        btnBatDauThi.addActionListener(this::btnBatDauThiActionPerformed);

        btnTroVe.setBackground(new java.awt.Color(245, 246, 248));
        btnTroVe.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnTroVe.setForeground(new java.awt.Color(36, 43, 54));
        btnTroVe.setText("Trở về");
        btnTroVe.setFocusPainted(false);
        btnTroVe.setOpaque(true);
        btnTroVe.addActionListener(this::btnTroVeActionPerformed);

        javax.swing.GroupLayout pnlNoiDungLayout = new javax.swing.GroupLayout(pnlNoiDung);
        pnlNoiDung.setLayout(pnlNoiDungLayout);
        pnlNoiDungLayout.setHorizontalGroup(
            pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTieuDe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblMoTa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(pnlTxtMaSV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(pnlTxtHoTen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(pnlTxtMaLop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(pnlTxtTenLop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(pnlCboMonHoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlTxtNgayThi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(pnlCboLanThi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(scrTblCaThi)
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(btnBatDauThi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTroVe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlNoiDungLayout.setVerticalGroup(
            pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(lblTieuDe)
                .addGap(8, 8, 8)
                .addComponent(lblMoTa)
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
                .addComponent(scrTblCaThi, javax.swing.GroupLayout.PREFERRED_SIZE, 180, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBatDauThi, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTroVe, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
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

    private void cboMonHocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboMonHocActionPerformed
        if (!dangNapMonHoc) {
            loadCaThiTable();
        }
    }//GEN-LAST:event_cboMonHocActionPerformed

    private void cboLanThiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboLanThiActionPerformed
        loadCaThiTable();
    }//GEN-LAST:event_cboLanThiActionPerformed

    private void txtNgayThiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNgayThiActionPerformed
        loadCaThiTable();
    }//GEN-LAST:event_txtNgayThiActionPerformed

    private void txtNgayThiFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNgayThiFocusLost
        if (!evt.isTemporary()) {
            loadCaThiTable();
        }
    }//GEN-LAST:event_txtNgayThiFocusLost

    private void btnBatDauThiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatDauThiActionPerformed
        int row = tblCaThi.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ca thi !");
            return;
        }

        // Lấy dòng đang chọn, kể cả khi bảng đã được sắp xếp.
        row = tblCaThi.convertRowIndexToModel(row);
        DefaultTableModel model = (DefaultTableModel) tblCaThi.getModel();
        MonHoc monHoc = (MonHoc) model.getValueAt(row, 0);

        cathi = new CaThi();
        cathi.setMaMH(monHoc.getMaMH());
        cathi.setTenMH(monHoc.getTenMH());
        cathi.setNgaythi(model.getValueAt(row, 1).toString());
        cathi.setLan(Integer.parseInt(model.getValueAt(row, 2).toString()));
        cathi.setTrinhDo(model.getValueAt(row, 3).toString());
        cathi.setSoCauThi(Integer.parseInt(model.getValueAt(row, 4).toString()));
        cathi.setThoiGianThi(Integer.parseInt(model.getValueAt(row, 5).toString()));

        LocalDate ngay = LocalDate.parse(cathi.getNgaythi(), dinhDangNgay);
        if (ngay.isAfter(LocalDate.now())) {
            JOptionPane.showMessageDialog(this, "Ca thi không hợp lệ, Vui lòng chọn lại!");
            return;
        }

        trangchu.DieuHuong.moForm(this, new thi.LamBaiThi(nguoiDung, cathi), nguoiDung);
    }//GEN-LAST:event_btnBatDauThiActionPerformed

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
    private javax.swing.JButton btnBatDauThi;
    private javax.swing.JButton btnTroVe;
    private javax.swing.JComboBox cboLanThi;
    private javax.swing.JComboBox cboMonHoc;
    private javax.swing.JLabel lblCboLanThi;
    private javax.swing.JLabel lblCboMonHoc;
    private javax.swing.JLabel lblMoTa;
    private javax.swing.JLabel lblTieuDe;
    private javax.swing.JLabel lblTxtHoTen;
    private javax.swing.JLabel lblTxtMaLop;
    private javax.swing.JLabel lblTxtMaSV;
    private javax.swing.JLabel lblTxtNgayThi;
    private javax.swing.JLabel lblTxtTenLop;
    private javax.swing.JPanel pnlCboLanThi;
    private javax.swing.JPanel pnlCboMonHoc;
    private javax.swing.JPanel pnlNoiDung;
    private javax.swing.JPanel pnlTxtHoTen;
    private javax.swing.JPanel pnlTxtMaLop;
    private javax.swing.JPanel pnlTxtMaSV;
    private javax.swing.JPanel pnlTxtNgayThi;
    private javax.swing.JPanel pnlTxtTenLop;
    private javax.swing.JScrollPane scrTblCaThi;
    private javax.swing.JTable tblCaThi;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtMaLop;
    private javax.swing.JTextField txtMaSV;
    private javax.swing.JFormattedTextField txtNgayThi;
    private javax.swing.JTextField txtTenLop;
    // End of variables declaration//GEN-END:variables
}
