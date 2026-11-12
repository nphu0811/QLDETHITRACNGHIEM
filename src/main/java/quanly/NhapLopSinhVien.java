package quanly;

import com.github.lgooddatepicker.components.DatePickerSettings;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;
import connectionSQLServer.QuanLyDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.JTextField;
import java.sql.Date;

import session.sessionGiaoVien;

/**
 * Giao diện NhapLopSinhVien. Nghiệp vụ sẽ được bổ sung trong các event handler.
 * Chỉnh bố cục bằng tab Design của NetBeans và giữ file .form đi kèm.
 */
public class NhapLopSinhVien extends javax.swing.JFrame {
    private final sessionGiaoVien nguoiDung;
    private final DotChinhSua dotLop;
    private final DotChinhSua dotSV;
    private final JTextField txtNgaySinh;

    public NhapLopSinhVien() {
        this(null);
    }

    public NhapLopSinhVien(sessionGiaoVien nguoiDung) {
        initComponents();
        this.nguoiDung = nguoiDung;
        getContentPane().setBackground(new java.awt.Color(245, 246, 248));
        setLocationRelativeTo(null);
        tblLop.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblSinhVien.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        DatePickerSettings caiDatNgaySinh = new DatePickerSettings(Locale.forLanguageTag("vi-VN"));
        caiDatNgaySinh.setAllowKeyboardEditing(false);
        caiDatNgaySinh.setFormatForDatesCommonEra("dd/MM/uuuu");
        caiDatNgaySinh.setFontValidDate(new java.awt.Font("Segoe UI", 0, 14));
        caiDatNgaySinh.setTranslationClear("Xóa ngày");
        caiDatNgaySinh.setTranslationToday("Hôm nay");
        lichNgaySinh.setSettings(caiDatNgaySinh);
        caiDatNgaySinh.setDateRangeLimits(LocalDate.of(1, 1, 1), LocalDate.of(LocalDate.now().getYear() - 18, 12, 31));
        lichNgaySinh.getComponentToggleCalendarButton().setText("Lịch");
        lichNgaySinh.getComponentToggleCalendarButton().setToolTipText("Chọn ngày sinh");
        txtNgaySinh = lichNgaySinh.getComponentDateTextField();
        txtNgaySinh.setDisabledTextColor(new java.awt.Color(36, 43, 54));
        dotLop = new DotChinhSua(tblLop,
                new DotChinhSua.Nut(btnLopThem, btnLopHieuChinh, btnLopXoa, btnLopGhi, btnLopPhucHoi, btnLopTim),
                this::docLop, this::dienLop, this::baoLoi, List.of(txtMaLop, txtTenLop), List.of(txtMaLop), 0);
        dotSV = new DotChinhSua(tblSinhVien,
                new DotChinhSua.Nut(btnSVThem, btnSVHieuChinh, btnSVXoa, btnSVGhi, btnSVPhucHoi, btnSVTim),
                this::docSinhVien, this::dienSinhVien, this::baoLoi,
                List.of(txtMaSV, txtHoSV, txtTenSV, txtNgaySinh, txtDiaChiSV), List.of(txtMaSV), 0);
        if (nguoiDung != null && nguoiDung.coKetNoi()) taiLop();
        capNhatCheDo();
    }

    private ArrayList<Object[]> danhSachLop = new ArrayList<>();
    private ArrayList<Object[]> danhSachSinhVien = new ArrayList<>();
    private String lopDangChon = "";

    private void capNhatCheDo() {
        boolean daDangNhap = nguoiDung != null && nguoiDung.coKetNoi();
        dotLop.capNhatGiaoDien(daDangNhap && !dotSV.dangNhap());
        boolean choPhepSinhVien = daDangNhap && !dotLop.dangNhap() && tblLop.getRowCount() > 0 && !lopDangChon.isEmpty();
        dotSV.capNhatGiaoDien(choPhepSinhVien);
        lichNgaySinh.setEnabled(choPhepSinhVien && dotSV.dangNhap());
        txtNgaySinh.setEditable(false);
        txtNgaySinh.setEnabled(false);
    }

    private void taiLop() {
        try {
            danhSachLop = QuanLyDAO.layLop(nguoiDung);
            napBang(tblLop, danhSachLop);
            chonLopTrongBang();
        } catch (SQLException e) {
            baoLoi(e);
        }
    }

    private void chonLopTrongBang() {
        if (tblLop.getRowCount() == 0) {
            chonLop();
            return;
        }
        int viTri = 0;
        for (int i = 0; i < tblLop.getRowCount(); i++) {
            if (lopDangChon.equalsIgnoreCase(Objects.toString(tblLop.getValueAt(i, 0), ""))) {
                viTri = i;
                break;
            }
        }
        tblLop.setRowSelectionInterval(viTri, viTri);
        chonLop();
    }

    private void taiSinhVien() {
        try {
            danhSachSinhVien = lopDangChon.isEmpty() ? new ArrayList<>()
                    : QuanLyDAO.laySinhVien(nguoiDung, lopDangChon);
        } catch (SQLException e) {
            danhSachSinhVien = new ArrayList<>();
            baoLoi(e);
        }
        napBang(tblSinhVien, danhSachSinhVien);
        if (tblSinhVien.getRowCount() > 0) tblSinhVien.setRowSelectionInterval(0, 0);
        dienSinhVien(dongDaChon(tblSinhVien));
        capNhatCheDo();
    }

    private void chonLop() {
        Object[] dong = dongDaChon(tblLop);
        lopDangChon = DotChinhSua.chuoi(dong, 0);
        dienLop(dong);
        taiSinhVien();
    }

    private void dienLop(Object[] dong) {
        txtMaLop.setText(DotChinhSua.chuoi(dong, 0));
        txtTenLop.setText(DotChinhSua.chuoi(dong, 1));
    }

    private void dienSinhVien(Object[] dong) {
        txtMaSV.setText(DotChinhSua.chuoi(dong, 0));
        txtHoSV.setText(DotChinhSua.chuoi(dong, 1));
        txtTenSV.setText(DotChinhSua.chuoi(dong, 2));
        Date ngaySinh = dong == null ? null : (Date) dong[3];
        lichNgaySinh.setDate(ngaySinh == null ? null : ngaySinh.toLocalDate());
        txtDiaChiSV.setText(DotChinhSua.chuoi(dong, 4));
        txtLopSV.setText(lopDangChon);
    }

    private Object[] docLop() {
        String ma = nhap(txtMaLop, "Mã lớp", 15, true);
        if (dotLop.dangThem() && coMa(danhSachLop, ma)) throw new IllegalArgumentException("Mã lớp đã có.");
        String ten = nhap(txtTenLop, "Tên lớp", 50, true);
        return new Object[]{ma, ten};
    }

    private Object[] docSinhVien() {
        String ma = nhap(txtMaSV, "Mã sinh viên", 8, true);
        if (dotSV.dangThem() && coMa(danhSachSinhVien, ma)) throw new IllegalArgumentException("Mã sinh viên đã có.");
        String ho = nhap(txtHoSV, "Họ", 50, true);
        String ten = nhap(txtTenSV, "Tên", 10, true);
        String diaChi = nhap(txtDiaChiSV, "Địa chỉ", 100, false);
        LocalDate ngayChon = lichNgaySinh.getDate();
        Date ngaySinh = ngayChon == null ? null : Date.valueOf(ngayChon);
        return new Object[]{ma, ho, ten, ngaySinh, diaChi, lopDangChon};
    }

    private void napBang(JTable bang, List<Object[]> danhSach) {
        DefaultTableModel model = (DefaultTableModel) bang.getModel();
        model.setRowCount(0);
        for (Object[] dong : danhSach) {
            model.addRow(dong);
        }
    }

    private String nhap(JTextComponent oNhap, String ten, int toiDa, boolean batBuoc) {
        String giaTri = oNhap.getText().strip();
        if (giaTri.length() > toiDa || (batBuoc && giaTri.isEmpty())) {
            oNhap.requestFocusInWindow();
            throw new IllegalArgumentException(ten + (batBuoc ? " phải có từ 1 đến " : " có tối đa ") + toiDa + " ký tự.");
        }
        return giaTri;
    }

    private boolean coMa(List<Object[]> danhSach, String ma) {
        for (Object[] dong : danhSach) {
            if (ma.equalsIgnoreCase(Objects.toString(dong[0], ""))) return true;
        }
        return false;
    }

    private Object[] dongDaChon(JTable bang) {
        int viTri = bang.getSelectedRow();
        if (viTri < 0) return null;
        int dongModel = bang.convertRowIndexToModel(viTri);
        Object[] dong = new Object[bang.getModel().getColumnCount()];
        for (int cot = 0; cot < dong.length; cot++) {
            dong[cot] = bang.getModel().getValueAt(dongModel, cot);
        }
        return dong;
    }

    private boolean khop(Object giaTri, String tuKhoa) {
        return Objects.toString(giaTri, "").toLowerCase(Locale.ROOT).contains(tuKhoa.toLowerCase(Locale.ROOT));
    }

    private void baoLoi(SQLException e) {
        String noiDung = switch (e.getErrorCode()) {
            case 2601, 2627 -> "Mã đã tồn tại.";
            case 547 -> "Dữ liệu đang được sử dụng hoặc thông tin liên quan không hợp lệ.";
            default -> e.getMessage();
        };
        JOptionPane.showMessageDialog(pnlNoiDung, noiDung, "Thông báo", JOptionPane.ERROR_MESSAGE);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlNoiDung = new javax.swing.JPanel();
        lblTieuDe = new javax.swing.JLabel();
        lblMoTa = new javax.swing.JLabel();
        pnlLop = new javax.swing.JPanel();
        lblLop = new javax.swing.JLabel();
        pnlTxtMaLop = new javax.swing.JPanel();
        lblTxtMaLop = new javax.swing.JLabel();
        txtMaLop = new javax.swing.JTextField();
        pnlTxtTenLop = new javax.swing.JPanel();
        lblTxtTenLop = new javax.swing.JLabel();
        txtTenLop = new javax.swing.JTextField();
        btnLopThem = new javax.swing.JButton();
        btnLopXoa = new javax.swing.JButton();
        btnLopHieuChinh = new javax.swing.JButton();
        btnLopPhucHoi = new javax.swing.JButton();
        btnLopTim = new javax.swing.JButton();
        btnLopGhi = new javax.swing.JButton();
        scrTblLop = new javax.swing.JScrollPane();
        tblLop = new javax.swing.JTable();
        pnlSinhVien = new javax.swing.JPanel();
        lblSinhVien = new javax.swing.JLabel();
        pnlTxtMaSV = new javax.swing.JPanel();
        lblTxtMaSV = new javax.swing.JLabel();
        txtMaSV = new javax.swing.JTextField();
        pnlTxtLopSV = new javax.swing.JPanel();
        lblTxtLopSV = new javax.swing.JLabel();
        txtLopSV = new javax.swing.JTextField();
        pnlTxtHoSV = new javax.swing.JPanel();
        lblTxtHoSV = new javax.swing.JLabel();
        txtHoSV = new javax.swing.JTextField();
        pnlTxtTenSV = new javax.swing.JPanel();
        lblTxtTenSV = new javax.swing.JLabel();
        txtTenSV = new javax.swing.JTextField();
        pnlTxtNgaySinh = new javax.swing.JPanel();
        lblTxtNgaySinh = new javax.swing.JLabel();
        lichNgaySinh = new com.github.lgooddatepicker.components.DatePicker();
        pnlTxtDiaChiSV = new javax.swing.JPanel();
        lblTxtDiaChiSV = new javax.swing.JLabel();
        txtDiaChiSV = new javax.swing.JTextField();
        btnSVThem = new javax.swing.JButton();
        btnSVXoa = new javax.swing.JButton();
        btnSVHieuChinh = new javax.swing.JButton();
        btnSVPhucHoi = new javax.swing.JButton();
        btnSVTim = new javax.swing.JButton();
        btnSVGhi = new javax.swing.JButton();
        scrTblSinhVien = new javax.swing.JScrollPane();
        tblSinhVien = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PTIT | QUẢN LÝ LỚP VÀ SINH VIÊN");
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/ptit-logo.png")).getImage());

        pnlNoiDung.setBackground(new java.awt.Color(255, 255, 255));
        pnlNoiDung.setBorder(javax.swing.BorderFactory.createEmptyBorder(24, 24, 24, 24));

        lblTieuDe.setText("QUẢN LÝ LỚP VÀ SINH VIÊN");
        lblTieuDe.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTieuDe.setForeground(new java.awt.Color(205, 32, 45));

        lblMoTa.setText("Thông tin lớp học và danh sách sinh viên của lớp");
        lblMoTa.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblMoTa.setForeground(new java.awt.Color(107, 114, 128));

        pnlLop.setBackground(new java.awt.Color(255, 255, 255));

        lblLop.setText("Danh sách lớp");
        lblLop.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblLop.setForeground(new java.awt.Color(205, 32, 45));

        pnlTxtMaLop.setBackground(new java.awt.Color(255, 255, 255));

        lblTxtMaLop.setText("Mã lớp");
        lblTxtMaLop.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblTxtMaLop.setForeground(new java.awt.Color(36, 43, 54));

        txtMaLop.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMaLop.setForeground(new java.awt.Color(36, 43, 54));
        txtMaLop.setToolTipText("MALOP · tối đa 15 ký tự");

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

        lblTxtTenLop.setText("Tên lớp");
        lblTxtTenLop.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblTxtTenLop.setForeground(new java.awt.Color(36, 43, 54));

        txtTenLop.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTenLop.setForeground(new java.awt.Color(36, 43, 54));
        txtTenLop.setToolTipText("TENLOP · tối đa 50 ký tự");

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

        btnLopThem.setText("Thêm");
        btnLopThem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnLopThem.setBackground(new java.awt.Color(245, 246, 248));
        btnLopThem.setForeground(new java.awt.Color(36, 43, 54));
        btnLopThem.setOpaque(true);
        btnLopThem.setFocusPainted(false);
        btnLopThem.addActionListener(this::btnLopThemActionPerformed);

        btnLopXoa.setText("Xóa");
        btnLopXoa.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnLopXoa.setBackground(new java.awt.Color(245, 246, 248));
        btnLopXoa.setForeground(new java.awt.Color(36, 43, 54));
        btnLopXoa.setOpaque(true);
        btnLopXoa.setFocusPainted(false);
        btnLopXoa.addActionListener(this::btnLopXoaActionPerformed);

        btnLopHieuChinh.setText("Hiệu chỉnh");
        btnLopHieuChinh.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnLopHieuChinh.setBackground(new java.awt.Color(245, 246, 248));
        btnLopHieuChinh.setForeground(new java.awt.Color(36, 43, 54));
        btnLopHieuChinh.setOpaque(true);
        btnLopHieuChinh.setFocusPainted(false);
        btnLopHieuChinh.addActionListener(this::btnLopHieuChinhActionPerformed);

        btnLopPhucHoi.setText("Phục hồi");
        btnLopPhucHoi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnLopPhucHoi.setBackground(new java.awt.Color(245, 246, 248));
        btnLopPhucHoi.setForeground(new java.awt.Color(36, 43, 54));
        btnLopPhucHoi.setOpaque(true);
        btnLopPhucHoi.setFocusPainted(false);
        btnLopPhucHoi.addActionListener(this::btnLopPhucHoiActionPerformed);

        btnLopTim.setText("Tìm");
        btnLopTim.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnLopTim.setBackground(new java.awt.Color(245, 246, 248));
        btnLopTim.setForeground(new java.awt.Color(36, 43, 54));
        btnLopTim.setOpaque(true);
        btnLopTim.setFocusPainted(false);
        btnLopTim.addActionListener(this::btnLopTimActionPerformed);

        btnLopGhi.setText("Ghi");
        btnLopGhi.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLopGhi.setBackground(new java.awt.Color(205, 32, 45));
        btnLopGhi.setForeground(new java.awt.Color(255, 255, 255));
        btnLopGhi.setOpaque(true);
        btnLopGhi.setFocusPainted(false);
        btnLopGhi.addActionListener(this::btnLopGhiActionPerformed);

        tblLop.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        tblLop.setRowHeight(28);
        tblLop.setAutoCreateRowSorter(true);
        tblLop.setFillsViewportHeight(true);
        tblLop.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã lớp", "Tên lớp"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblLop.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblLopMouseClicked(evt);
            }
        });
        scrTblLop.setViewportView(tblLop);

        javax.swing.GroupLayout pnlLopLayout = new javax.swing.GroupLayout(pnlLop);
        pnlLop.setLayout(pnlLopLayout);
        pnlLopLayout.setHorizontalGroup(
            pnlLopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblLop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlLopLayout.createSequentialGroup()
                .addComponent(pnlTxtMaLop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(pnlTxtTenLop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            .addComponent(scrTblLop)
        );
        pnlLopLayout.setVerticalGroup(
            pnlLopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLopLayout.createSequentialGroup()
                .addComponent(lblLop)
                .addGap(8, 8, 8)
                .addGroup(pnlLopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlTxtMaLop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlTxtTenLop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlLopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLopThem, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLopXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLopHieuChinh, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLopPhucHoi, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLopTim, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLopGhi, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(scrTblLop, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pnlSinhVien.setBackground(new java.awt.Color(255, 255, 255));

        lblSinhVien.setText("Sinh viên của lớp");
        lblSinhVien.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblSinhVien.setForeground(new java.awt.Color(205, 32, 45));

        pnlTxtMaSV.setBackground(new java.awt.Color(255, 255, 255));

        lblTxtMaSV.setText("Mã sinh viên");
        lblTxtMaSV.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblTxtMaSV.setForeground(new java.awt.Color(36, 43, 54));

        txtMaSV.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMaSV.setForeground(new java.awt.Color(36, 43, 54));
        txtMaSV.setToolTipText("MASV · tối đa 8 ký tự");

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

        pnlTxtLopSV.setBackground(new java.awt.Color(255, 255, 255));

        lblTxtLopSV.setText("Mã lớp");
        lblTxtLopSV.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblTxtLopSV.setForeground(new java.awt.Color(36, 43, 54));

        txtLopSV.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtLopSV.setForeground(new java.awt.Color(36, 43, 54));
        txtLopSV.setEditable(false);

        javax.swing.GroupLayout pnlTxtLopSVLayout = new javax.swing.GroupLayout(pnlTxtLopSV);
        pnlTxtLopSV.setLayout(pnlTxtLopSVLayout);
        pnlTxtLopSVLayout.setHorizontalGroup(
            pnlTxtLopSVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTxtLopSV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(txtLopSV)
        );
        pnlTxtLopSVLayout.setVerticalGroup(
            pnlTxtLopSVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtLopSVLayout.createSequentialGroup()
                .addComponent(lblTxtLopSV)
                .addGap(8, 8, 8)
                .addComponent(txtLopSV, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlTxtHoSV.setBackground(new java.awt.Color(255, 255, 255));

        lblTxtHoSV.setText("Họ");
        lblTxtHoSV.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblTxtHoSV.setForeground(new java.awt.Color(36, 43, 54));

        txtHoSV.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtHoSV.setForeground(new java.awt.Color(36, 43, 54));
        txtHoSV.setToolTipText("HO · tối đa 50 ký tự");

        javax.swing.GroupLayout pnlTxtHoSVLayout = new javax.swing.GroupLayout(pnlTxtHoSV);
        pnlTxtHoSV.setLayout(pnlTxtHoSVLayout);
        pnlTxtHoSVLayout.setHorizontalGroup(
            pnlTxtHoSVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTxtHoSV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(txtHoSV)
        );
        pnlTxtHoSVLayout.setVerticalGroup(
            pnlTxtHoSVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtHoSVLayout.createSequentialGroup()
                .addComponent(lblTxtHoSV)
                .addGap(8, 8, 8)
                .addComponent(txtHoSV, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlTxtTenSV.setBackground(new java.awt.Color(255, 255, 255));

        lblTxtTenSV.setText("Tên");
        lblTxtTenSV.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblTxtTenSV.setForeground(new java.awt.Color(36, 43, 54));

        txtTenSV.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTenSV.setForeground(new java.awt.Color(36, 43, 54));
        txtTenSV.setToolTipText("TEN · tối đa 10 ký tự");

        javax.swing.GroupLayout pnlTxtTenSVLayout = new javax.swing.GroupLayout(pnlTxtTenSV);
        pnlTxtTenSV.setLayout(pnlTxtTenSVLayout);
        pnlTxtTenSVLayout.setHorizontalGroup(
            pnlTxtTenSVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTxtTenSV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(txtTenSV)
        );
        pnlTxtTenSVLayout.setVerticalGroup(
            pnlTxtTenSVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtTenSVLayout.createSequentialGroup()
                .addComponent(lblTxtTenSV)
                .addGap(8, 8, 8)
                .addComponent(txtTenSV, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlTxtNgaySinh.setBackground(new java.awt.Color(255, 255, 255));

        lblTxtNgaySinh.setText("Ngày sinh");
        lblTxtNgaySinh.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblTxtNgaySinh.setForeground(new java.awt.Color(36, 43, 54));

        lichNgaySinh.setOpaque(false);

        javax.swing.GroupLayout pnlTxtNgaySinhLayout = new javax.swing.GroupLayout(pnlTxtNgaySinh);
        pnlTxtNgaySinh.setLayout(pnlTxtNgaySinhLayout);
        pnlTxtNgaySinhLayout.setHorizontalGroup(
            pnlTxtNgaySinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTxtNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lichNgaySinh)
        );
        pnlTxtNgaySinhLayout.setVerticalGroup(
            pnlTxtNgaySinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtNgaySinhLayout.createSequentialGroup()
                .addComponent(lblTxtNgaySinh)
                .addGap(8, 8, 8)
                .addComponent(lichNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlTxtDiaChiSV.setBackground(new java.awt.Color(255, 255, 255));

        lblTxtDiaChiSV.setText("Địa chỉ");
        lblTxtDiaChiSV.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblTxtDiaChiSV.setForeground(new java.awt.Color(36, 43, 54));

        txtDiaChiSV.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtDiaChiSV.setForeground(new java.awt.Color(36, 43, 54));
        txtDiaChiSV.setToolTipText("DIACHI · tối đa 100 ký tự");

        javax.swing.GroupLayout pnlTxtDiaChiSVLayout = new javax.swing.GroupLayout(pnlTxtDiaChiSV);
        pnlTxtDiaChiSV.setLayout(pnlTxtDiaChiSVLayout);
        pnlTxtDiaChiSVLayout.setHorizontalGroup(
            pnlTxtDiaChiSVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTxtDiaChiSV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(txtDiaChiSV)
        );
        pnlTxtDiaChiSVLayout.setVerticalGroup(
            pnlTxtDiaChiSVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtDiaChiSVLayout.createSequentialGroup()
                .addComponent(lblTxtDiaChiSV)
                .addGap(8, 8, 8)
                .addComponent(txtDiaChiSV, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        btnSVThem.setText("Thêm");
        btnSVThem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSVThem.setBackground(new java.awt.Color(245, 246, 248));
        btnSVThem.setForeground(new java.awt.Color(36, 43, 54));
        btnSVThem.setOpaque(true);
        btnSVThem.setFocusPainted(false);
        btnSVThem.addActionListener(this::btnSVThemActionPerformed);

        btnSVXoa.setText("Xóa");
        btnSVXoa.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSVXoa.setBackground(new java.awt.Color(245, 246, 248));
        btnSVXoa.setForeground(new java.awt.Color(36, 43, 54));
        btnSVXoa.setOpaque(true);
        btnSVXoa.setFocusPainted(false);
        btnSVXoa.addActionListener(this::btnSVXoaActionPerformed);

        btnSVHieuChinh.setText("Hiệu chỉnh");
        btnSVHieuChinh.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSVHieuChinh.setBackground(new java.awt.Color(245, 246, 248));
        btnSVHieuChinh.setForeground(new java.awt.Color(36, 43, 54));
        btnSVHieuChinh.setOpaque(true);
        btnSVHieuChinh.setFocusPainted(false);
        btnSVHieuChinh.addActionListener(this::btnSVHieuChinhActionPerformed);

        btnSVPhucHoi.setText("Phục hồi");
        btnSVPhucHoi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSVPhucHoi.setBackground(new java.awt.Color(245, 246, 248));
        btnSVPhucHoi.setForeground(new java.awt.Color(36, 43, 54));
        btnSVPhucHoi.setOpaque(true);
        btnSVPhucHoi.setFocusPainted(false);
        btnSVPhucHoi.addActionListener(this::btnSVPhucHoiActionPerformed);

        btnSVTim.setText("Tìm");
        btnSVTim.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSVTim.setBackground(new java.awt.Color(245, 246, 248));
        btnSVTim.setForeground(new java.awt.Color(36, 43, 54));
        btnSVTim.setOpaque(true);
        btnSVTim.setFocusPainted(false);
        btnSVTim.addActionListener(this::btnSVTimActionPerformed);

        btnSVGhi.setText("Ghi");
        btnSVGhi.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSVGhi.setBackground(new java.awt.Color(205, 32, 45));
        btnSVGhi.setForeground(new java.awt.Color(255, 255, 255));
        btnSVGhi.setOpaque(true);
        btnSVGhi.setFocusPainted(false);
        btnSVGhi.addActionListener(this::btnSVGhiActionPerformed);

        tblSinhVien.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        tblSinhVien.setRowHeight(28);
        tblSinhVien.setAutoCreateRowSorter(true);
        tblSinhVien.setFillsViewportHeight(true);
        tblSinhVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SV", "Họ", "Tên", "Ngày sinh", "Địa chỉ", "Mã lớp"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSinhVien.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSinhVienMouseClicked(evt);
            }
        });
        scrTblSinhVien.setViewportView(tblSinhVien);

        javax.swing.GroupLayout pnlSinhVienLayout = new javax.swing.GroupLayout(pnlSinhVien);
        pnlSinhVien.setLayout(pnlSinhVienLayout);
        pnlSinhVienLayout.setHorizontalGroup(
            pnlSinhVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblSinhVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addComponent(btnSVThem, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(btnSVXoa, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(btnSVHieuChinh, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(btnSVPhucHoi, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(btnSVTim, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(btnSVGhi, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE))
            .addComponent(scrTblSinhVien)
        );
        pnlSinhVienLayout.setVerticalGroup(
            pnlSinhVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSinhVienLayout.createSequentialGroup()
                .addComponent(lblSinhVien)
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
                    .addComponent(btnSVThem, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSVXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSVHieuChinh, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSVPhucHoi, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSVTim, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSVGhi, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addComponent(scrTblSinhVien, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlNoiDungLayout = new javax.swing.GroupLayout(pnlNoiDung);
        pnlNoiDung.setLayout(pnlNoiDungLayout);
        pnlNoiDungLayout.setHorizontalGroup(
            pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTieuDe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblMoTa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(pnlLop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlSinhVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlNoiDungLayout.setVerticalGroup(
            pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(lblTieuDe)
                .addGap(8, 8, 8)
                .addComponent(lblMoTa)
                .addGap(8, 8, 8)
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlLop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlSinhVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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

    private void btnLopThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLopThemActionPerformed
        dotLop.them();
        capNhatCheDo();
        txtMaLop.requestFocusInWindow();
    }//GEN-LAST:event_btnLopThemActionPerformed

    private void btnLopXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLopXoaActionPerformed
        Object[] dong = dongDaChon(tblLop);
        if (dong == null) {
            JOptionPane.showMessageDialog(pnlNoiDung, "Vui lòng chọn lớp để xóa.");
            return;
        }
        String ma = Objects.toString(dong[0], "");
        int xacNhan = JOptionPane.showConfirmDialog(pnlNoiDung, "Xóa " + "lớp " + ma + "?",
                "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
        if (xacNhan != JOptionPane.YES_OPTION) return;
        try {
            QuanLyDAO.xoaLop(nguoiDung, ma);
            txtMaLop.setText("");
            txtTenLop.setText("");
            taiLop();
        } catch (SQLException e) {
            if (e.getErrorCode() == 547) {
                JOptionPane.showMessageDialog(pnlNoiDung,
                        "Không thể xóa lớp vì vẫn còn sinh viên hoặc ca thi đã đăng ký.");
            } else {
                baoLoi(e);
            }
        }
    }//GEN-LAST:event_btnLopXoaActionPerformed

    private void btnLopHieuChinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLopHieuChinhActionPerformed
        dotLop.hieuChinh();
        capNhatCheDo();
        txtTenLop.requestFocusInWindow();
    }//GEN-LAST:event_btnLopHieuChinhActionPerformed

    private void btnLopPhucHoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLopPhucHoiActionPerformed
        dotLop.phucHoi(() -> QuanLyDAO.phucHoiLop(nguoiDung), this::taiLop);
        capNhatCheDo();
    }//GEN-LAST:event_btnLopPhucHoiActionPerformed

    private void btnLopTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLopTimActionPerformed
        if (dotLop.dangNhap()) {
            dotLop.huy();
            capNhatCheDo();
            return;
        }
        String[] tuKhoa = DotChinhSua.tuKhoa(pnlNoiDung, "Mã lớp", "Tên lớp");
        if (tuKhoa == null) return;
        ArrayList<Object[]> ketQua = new ArrayList<>();
        for (Object[] dong : danhSachLop) {
            if (khop(dong[0], tuKhoa[0]) && khop(dong[1], tuKhoa[1])) {
                ketQua.add(dong);
            }
        }
        napBang(tblLop, ketQua);
        chonLopTrongBang();
    }//GEN-LAST:event_btnLopTimActionPerformed

    private void btnLopGhiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLopGhiActionPerformed
        dotLop.ghi((thaoTac, truoc, sau) -> QuanLyDAO.ghiLop(nguoiDung, thaoTac, sau), this::taiLop);
        capNhatCheDo();
    }//GEN-LAST:event_btnLopGhiActionPerformed

    private void btnSVThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSVThemActionPerformed
        dotSV.them();
        capNhatCheDo();
        txtMaSV.requestFocusInWindow();
    }//GEN-LAST:event_btnSVThemActionPerformed

    private void btnSVXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSVXoaActionPerformed
        Object[] dong = dongDaChon(tblSinhVien);
        if (dong == null) {
            JOptionPane.showMessageDialog(pnlNoiDung, "Vui lòng chọn sinh viên để xóa.");
            return;
        }
        String ma = Objects.toString(dong[0], "");
        int xacNhan = JOptionPane.showConfirmDialog(pnlNoiDung, "Xóa " + "sinh viên " + ma + "?",
                "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
        if (xacNhan != JOptionPane.YES_OPTION) return;
        try {
            QuanLyDAO.xoaSinhVien(nguoiDung, ma, lopDangChon);
            taiSinhVien();
        } catch (SQLException e) {
            if (e.getErrorCode() == 547) {
                JOptionPane.showMessageDialog(pnlNoiDung,
                        "Không thể xóa sinh viên vì đã có điểm thi hoặc còn bài thi đang làm.");
            } else {
                baoLoi(e);
            }
        }
    }//GEN-LAST:event_btnSVXoaActionPerformed

    private void btnSVHieuChinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSVHieuChinhActionPerformed
        dotSV.hieuChinh();
        capNhatCheDo();
        txtHoSV.requestFocusInWindow();
    }//GEN-LAST:event_btnSVHieuChinhActionPerformed

    private void btnSVPhucHoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSVPhucHoiActionPerformed
        dotSV.phucHoi(() -> QuanLyDAO.phucHoiSinhVien(nguoiDung, lopDangChon), this::taiSinhVien);
        capNhatCheDo();
    }//GEN-LAST:event_btnSVPhucHoiActionPerformed

    private void btnSVTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSVTimActionPerformed
        if (dotSV.dangNhap()) {
            dotSV.huy();
            capNhatCheDo();
            return;
        }
        String[] tuKhoa = DotChinhSua.tuKhoa(pnlNoiDung, "Mã sinh viên", "Họ", "Tên");
        if (tuKhoa == null) return;
        ArrayList<Object[]> ketQua = new ArrayList<>();
        for (Object[] dong : danhSachSinhVien) {
            if (khop(dong[0], tuKhoa[0]) && khop(dong[1], tuKhoa[1]) && khop(dong[2], tuKhoa[2])) {
                ketQua.add(dong);
            }
        }
        napBang(tblSinhVien, ketQua);
        dienSinhVien(null);
        capNhatCheDo();
    }//GEN-LAST:event_btnSVTimActionPerformed

    private void btnSVGhiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSVGhiActionPerformed
        dotSV.ghi((thaoTac, truoc, sau) -> QuanLyDAO.ghiSinhVien(nguoiDung, thaoTac, sau), this::taiSinhVien);
        capNhatCheDo();
    }//GEN-LAST:event_btnSVGhiActionPerformed

    private void tblLopMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblLopMouseClicked
        if (!dotLop.dangNhap() && !dotSV.dangNhap() && tblLop.rowAtPoint(evt.getPoint()) >= 0) {
            chonLop();
        }
    }//GEN-LAST:event_tblLopMouseClicked

    private void tblSinhVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSinhVienMouseClicked
        if (!dotLop.dangNhap() && !dotSV.dangNhap() && tblSinhVien.rowAtPoint(evt.getPoint()) >= 0) {
            dienSinhVien(dongDaChon(tblSinhVien));
        }
    }//GEN-LAST:event_tblSinhVienMouseClicked

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
    private javax.swing.JButton btnLopGhi;
    private javax.swing.JButton btnLopHieuChinh;
    private javax.swing.JButton btnLopPhucHoi;
    private javax.swing.JButton btnLopThem;
    private javax.swing.JButton btnLopTim;
    private javax.swing.JButton btnLopXoa;
    private javax.swing.JButton btnSVGhi;
    private javax.swing.JButton btnSVHieuChinh;
    private javax.swing.JButton btnSVPhucHoi;
    private javax.swing.JButton btnSVThem;
    private javax.swing.JButton btnSVTim;
    private javax.swing.JButton btnSVXoa;
    private javax.swing.JLabel lblLop;
    private javax.swing.JLabel lblMoTa;
    private javax.swing.JLabel lblSinhVien;
    private javax.swing.JLabel lblTieuDe;
    private javax.swing.JLabel lblTxtDiaChiSV;
    private javax.swing.JLabel lblTxtHoSV;
    private javax.swing.JLabel lblTxtLopSV;
    private javax.swing.JLabel lblTxtMaLop;
    private javax.swing.JLabel lblTxtMaSV;
    private javax.swing.JLabel lblTxtNgaySinh;
    private javax.swing.JLabel lblTxtTenLop;
    private javax.swing.JLabel lblTxtTenSV;
    private javax.swing.JPanel pnlLop;
    private javax.swing.JPanel pnlNoiDung;
    private javax.swing.JPanel pnlSinhVien;
    private javax.swing.JPanel pnlTxtDiaChiSV;
    private javax.swing.JPanel pnlTxtHoSV;
    private javax.swing.JPanel pnlTxtLopSV;
    private javax.swing.JPanel pnlTxtMaLop;
    private javax.swing.JPanel pnlTxtMaSV;
    private javax.swing.JPanel pnlTxtNgaySinh;
    private javax.swing.JPanel pnlTxtTenLop;
    private javax.swing.JPanel pnlTxtTenSV;
    private javax.swing.JScrollPane scrTblLop;
    private javax.swing.JScrollPane scrTblSinhVien;
    private javax.swing.JTable tblLop;
    private javax.swing.JTable tblSinhVien;
    private javax.swing.JTextField txtDiaChiSV;
    private javax.swing.JTextField txtHoSV;
    private javax.swing.JTextField txtLopSV;
    private javax.swing.JTextField txtMaLop;
    private javax.swing.JTextField txtMaSV;
    private com.github.lgooddatepicker.components.DatePicker lichNgaySinh;
    private javax.swing.JTextField txtTenLop;
    private javax.swing.JTextField txtTenSV;
    // End of variables declaration//GEN-END:variables
}
