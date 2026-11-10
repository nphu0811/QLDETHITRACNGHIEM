package quanly;

import Helper.DateHelper;
import com.github.lgooddatepicker.components.DatePickerSettings;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import connectionSQLServer.QuanLyDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import java.sql.Date;

import session.sessionGiaoVien;

/**
 * Giao diện ChuanBiThi. Nghiệp vụ sẽ được bổ sung trong các event handler.
 * Chỉnh bố cục bằng tab Design của NetBeans và giữ file .form đi kèm.
 */
public class ChuanBiThi extends javax.swing.JFrame {
    private static final ZoneId MUI_GIO_VIET_NAM = ZoneId.of("Asia/Ho_Chi_Minh");
    private final sessionGiaoVien nguoiDung;
    private final DotChinhSua dot;

    public ChuanBiThi() {
        this(null);
    }

    public ChuanBiThi(sessionGiaoVien nguoiDung) {
        initComponents();
        this.nguoiDung = nguoiDung;
        getContentPane().setBackground(new java.awt.Color(245, 246, 248));
        setLocationRelativeTo(null);

        txtMaGV.setEditable(false);
        tblDangKyThi.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        DatePickerSettings caiDatNgayThi = new DatePickerSettings(Locale.forLanguageTag("vi-VN"));
        caiDatNgayThi.setVisibleDateTextField(false);
        caiDatNgayThi.setVisibleTodayButton(false);
        caiDatNgayThi.setVisibleClearButton(false);
        lichNgayThi.setSettings(caiDatNgayThi);
        caiDatNgayThi.setVetoPolicy(ChuanBiThi::ngayThiHopLe);
        lichNgayThi.getComponentToggleCalendarButton().setText("Lịch");
        lichNgayThi.getComponentToggleCalendarButton().setToolTipText("Chọn ngày thi từ ngày mai trở đi");
        lichNgayThi.addDateChangeListener(e -> {
            LocalDate ngay = e.getNewDate();
            txtNgayThi.setText(DateHelper.dinhDang(ngay == null ? null : Date.valueOf(ngay)));
        });
        txtNgayThi.setDisabledTextColor(txtNgayThi.getForeground());
        dot = new DotChinhSua(tblDangKyThi, new DotChinhSua.Nut(btnThem, btnHieuChinh, btnXoa, btnGhi, btnPhucHoi, null),
                this::docCaThi, this::dienCaThi, this::baoLoi,
                List.of(cboLop, cboMonHoc, cboTrinhDo, txtNgayThi, cboLanThi, txtSoCauThi, txtThoiGian),
                List.of(cboLop, cboMonHoc, cboLanThi), 1, 2, 5);
        boolean daDangNhap = nguoiDung != null && nguoiDung.coKetNoi();
        if (daDangNhap) {
            try {
                napCombo(cboLop, QuanLyDAO.layLop(nguoiDung), "Chọn lớp");
                napCombo(cboMonHoc, QuanLyDAO.layDanhMucMonHoc(nguoiDung), "Chọn môn học");
            } catch (SQLException e) {
                baoLoi(e);
            }
            taiCaThi();
        }
        capNhatCheDo();
    }

    private ArrayList<Object[]> danhSachCaThi = new ArrayList<>();

    private void capNhatCheDo() {
        boolean daDangNhap = nguoiDung != null && nguoiDung.coKetNoi();
        dot.capNhatGiaoDien(daDangNhap);
        txtNgayThi.setEditable(false);
        lichNgayThi.setEnabled(daDangNhap && dot.dangNhap());
        btnKiemTraCauHoi.setEnabled(daDangNhap && dot.dangNhap());
    }

    private void taiCaThi() {
        try {
            danhSachCaThi = QuanLyDAO.layCaThi(nguoiDung);
            napBang(tblDangKyThi, danhSachCaThi);
            dienCaThi(null);
            capNhatCheDo();
        } catch (SQLException e) {
            baoLoi(e);
        }
    }

    private void dienCaThi(Object[] dong) {
        txtMaGV.setText(dong == null ? nguoiDung.getMaGV() : DotChinhSua.chuoi(dong, 0));
        chonMa(cboLop, DotChinhSua.chuoi(dong, 1));
        chonMa(cboMonHoc, DotChinhSua.chuoi(dong, 2));
        cboTrinhDo.setSelectedItem(dong == null || dong[3] == null ? "A" : dong[3]);
        Date ngayThi = dong == null ? null : (Date) dong[4];
        LocalDate ngay = ngayThi == null ? null : ngayThi.toLocalDate();
        lichNgayThi.setDate(ngayThiHopLe(ngay) ? ngay : null);
        txtNgayThi.setText(DateHelper.dinhDang(ngayThi));
        cboLanThi.setSelectedItem(dong == null ? "1" : DotChinhSua.chuoi(dong, 5));
        txtSoCauThi.setText(DotChinhSua.chuoi(dong, 6));
        txtThoiGian.setText(DotChinhSua.chuoi(dong, 7));
        txtSoCauSanCo.setText("");
        lblTrangThaiCauHoi.setText("Chưa kiểm tra số câu hỏi");
    }

    private int nhapSo(javax.swing.JTextField oNhap, String ten, int nhoNhat, int lonNhat) {
        try {
            int so = Integer.parseInt(oNhap.getText().strip());
            if (so >= nhoNhat && so <= lonNhat) return so;
        } catch (NumberFormatException e) {
            // Cùng thông báo cho số sai định dạng và số ngoài giới hạn.
        }
        throw new IllegalArgumentException(ten + " phải từ " + nhoNhat + " đến " + lonNhat + ".");
    }

    private Object[] docCaThi() {
        String maLop = maDaChon(cboLop);
        String maMH = maDaChon(cboMonHoc);
        if (maLop.isEmpty() || maMH.isEmpty()) throw new IllegalArgumentException("Vui lòng chọn lớp và môn học.");
        short lan = Short.parseShort((String) cboLanThi.getSelectedItem());
        if (dot.dangThem()) {
            for (Object[] dong : danhSachCaThi) {
                if (maLop.equalsIgnoreCase(Objects.toString(dong[1], ""))
                        && maMH.equalsIgnoreCase(Objects.toString(dong[2], ""))
                        && lan == ((Number) dong[5]).intValue()) {
                    throw new IllegalArgumentException("Ca thi đã có.");
                }
            }
        }
        LocalDate ngayChon = lichNgayThi.getDate();
        kiemTraNgayThi(ngayChon);
        Date ngay = Date.valueOf(ngayChon);
        short soCau = (short) nhapSo(txtSoCauThi, "Số câu thi", 10, 100);
        short thoiGian = (short) nhapSo(txtThoiGian, "Thời gian thi", 15, 60);
        return new Object[]{nguoiDung.getMaGV(), maLop, maMH, cboTrinhDo.getSelectedItem(), ngay, lan, soCau, thoiGian};
    }

    private static boolean ngayThiHopLe(LocalDate ngay) {
        return ngay != null && ngay.getYear() <= 9999 && ngay.isAfter(LocalDate.now(MUI_GIO_VIET_NAM));
    }

    private static void kiemTraNgayThi(LocalDate ngay) {
        if (!ngayThiHopLe(ngay)) throw new IllegalArgumentException("Chọn ngày thi từ ngày mai trở đi.");
    }

    private void ghiCaThi(String thaoTac, List<Object[]> cacDong) throws SQLException {
        for (Object[] dong : cacDong) kiemTraNgayThi(((Date) dong[4]).toLocalDate());
        QuanLyDAO.ghiCaThi(nguoiDung, thaoTac, cacDong);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private void napCombo(JComboBox combo, List<Object[]> danhSach, String goiY) {
        DefaultComboBoxModel<LuaChon> model = new DefaultComboBoxModel<>();
        model.addElement(new LuaChon("", goiY));
        for (Object[] dong : danhSach) {
            String ma = Objects.toString(dong[0], "");
            String ten = Objects.toString(dong[1], "");
            model.addElement(new LuaChon(ma, ten.isEmpty() ? ma : ma + " - " + ten));
        }
        combo.setModel(model);
    }

    private record LuaChon(String ma, String ten) {
        @Override
        public String toString() {
            return ten;
        }
    }

    private String maDaChon(JComboBox<?> combo) {
        return combo.getSelectedItem() instanceof LuaChon muc ? muc.ma() : "";
    }

    private void chonMa(JComboBox<?> combo, String ma) {
        for (int i = 0; i < combo.getItemCount(); i++) {
            if (combo.getItemAt(i) instanceof LuaChon muc && muc.ma().equalsIgnoreCase(ma)) {
                combo.setSelectedIndex(i);
                return;
            }
        }
        combo.setSelectedIndex(combo.getItemCount() == 0 ? -1 : 0);
    }

    private void napBang(JTable bang, List<Object[]> danhSach) {
        DefaultTableModel model = (DefaultTableModel) bang.getModel();
        model.setRowCount(0);
        for (Object[] dong : danhSach) {
            model.addRow(dong);
        }
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
        pnlTxtMaGV = new javax.swing.JPanel();
        lblTxtMaGV = new javax.swing.JLabel();
        txtMaGV = new javax.swing.JTextField();
        pnlCboLop = new javax.swing.JPanel();
        lblCboLop = new javax.swing.JLabel();
        cboLop = new javax.swing.JComboBox();
        pnlCboMonHoc = new javax.swing.JPanel();
        lblCboMonHoc = new javax.swing.JLabel();
        cboMonHoc = new javax.swing.JComboBox();
        pnlCboTrinhDo = new javax.swing.JPanel();
        lblCboTrinhDo = new javax.swing.JLabel();
        cboTrinhDo = new javax.swing.JComboBox();
        pnlCboLanThi = new javax.swing.JPanel();
        lblCboLanThi = new javax.swing.JLabel();
        cboLanThi = new javax.swing.JComboBox();
        pnlTxtNgayThi = new javax.swing.JPanel();
        lblTxtNgayThi = new javax.swing.JLabel();
        txtNgayThi = new javax.swing.JFormattedTextField();
        lichNgayThi = new com.github.lgooddatepicker.components.DatePicker();
        pnlTxtSoCauThi = new javax.swing.JPanel();
        lblTxtSoCauThi = new javax.swing.JLabel();
        txtSoCauThi = new javax.swing.JTextField();
        pnlTxtThoiGian = new javax.swing.JPanel();
        lblTxtThoiGian = new javax.swing.JLabel();
        txtThoiGian = new javax.swing.JTextField();
        pnlTxtSoCauSanCo = new javax.swing.JPanel();
        lblTxtSoCauSanCo = new javax.swing.JLabel();
        txtSoCauSanCo = new javax.swing.JTextField();
        btnKiemTraCauHoi = new javax.swing.JButton();
        lblTrangThaiCauHoi = new javax.swing.JLabel();
        btnThem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnHieuChinh = new javax.swing.JButton();
        btnPhucHoi = new javax.swing.JButton();
        btnGhi = new javax.swing.JButton();
        scrTblDangKyThi = new javax.swing.JScrollPane();
        tblDangKyThi = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PTIT | CHUẨN BỊ THI");
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/ptit-logo.png")).getImage());

        pnlNoiDung.setBackground(new java.awt.Color(255, 255, 255));
        pnlNoiDung.setBorder(javax.swing.BorderFactory.createEmptyBorder(24, 24, 24, 24));

        lblTieuDe.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTieuDe.setForeground(new java.awt.Color(205, 32, 45));
        lblTieuDe.setText("CHUẨN BỊ THI");

        lblMoTa.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblMoTa.setForeground(new java.awt.Color(107, 114, 128));
        lblMoTa.setText("Đăng ký môn thi và cấu hình bài thi cho lớp");

        pnlTxtMaGV.setBackground(new java.awt.Color(255, 255, 255));

        lblTxtMaGV.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblTxtMaGV.setForeground(new java.awt.Color(36, 43, 54));
        lblTxtMaGV.setText("Mã giáo viên");

        txtMaGV.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMaGV.setForeground(new java.awt.Color(36, 43, 54));

        javax.swing.GroupLayout pnlTxtMaGVLayout = new javax.swing.GroupLayout(pnlTxtMaGV);
        pnlTxtMaGV.setLayout(pnlTxtMaGVLayout);
        pnlTxtMaGVLayout.setHorizontalGroup(
            pnlTxtMaGVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTxtMaGV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(txtMaGV)
        );
        pnlTxtMaGVLayout.setVerticalGroup(
            pnlTxtMaGVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtMaGVLayout.createSequentialGroup()
                .addComponent(lblTxtMaGV)
                .addGap(8, 8, 8)
                .addComponent(txtMaGV, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlCboLop.setBackground(new java.awt.Color(255, 255, 255));

        lblCboLop.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblCboLop.setForeground(new java.awt.Color(36, 43, 54));
        lblCboLop.setText("Lớp");

        cboLop.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cboLop.setForeground(new java.awt.Color(36, 43, 54));
        cboLop.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Chọn lớp" }));

        javax.swing.GroupLayout pnlCboLopLayout = new javax.swing.GroupLayout(pnlCboLop);
        pnlCboLop.setLayout(pnlCboLopLayout);
        pnlCboLopLayout.setHorizontalGroup(
            pnlCboLopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblCboLop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(cboLop, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlCboLopLayout.setVerticalGroup(
            pnlCboLopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCboLopLayout.createSequentialGroup()
                .addComponent(lblCboLop)
                .addGap(8, 8, 8)
                .addComponent(cboLop, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlCboMonHoc.setBackground(new java.awt.Color(255, 255, 255));

        lblCboMonHoc.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblCboMonHoc.setForeground(new java.awt.Color(36, 43, 54));
        lblCboMonHoc.setText("Môn học");

        cboMonHoc.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cboMonHoc.setForeground(new java.awt.Color(36, 43, 54));
        cboMonHoc.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Chọn môn học" }));

        javax.swing.GroupLayout pnlCboMonHocLayout = new javax.swing.GroupLayout(pnlCboMonHoc);
        pnlCboMonHoc.setLayout(pnlCboMonHocLayout);
        pnlCboMonHocLayout.setHorizontalGroup(
            pnlCboMonHocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblCboMonHoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(cboMonHoc, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlCboMonHocLayout.setVerticalGroup(
            pnlCboMonHocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCboMonHocLayout.createSequentialGroup()
                .addComponent(lblCboMonHoc)
                .addGap(8, 8, 8)
                .addComponent(cboMonHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlCboTrinhDo.setBackground(new java.awt.Color(255, 255, 255));

        lblCboTrinhDo.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblCboTrinhDo.setForeground(new java.awt.Color(36, 43, 54));
        lblCboTrinhDo.setText("Trình độ");

        cboTrinhDo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cboTrinhDo.setForeground(new java.awt.Color(36, 43, 54));
        cboTrinhDo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "A", "B", "C" }));

        javax.swing.GroupLayout pnlCboTrinhDoLayout = new javax.swing.GroupLayout(pnlCboTrinhDo);
        pnlCboTrinhDo.setLayout(pnlCboTrinhDoLayout);
        pnlCboTrinhDoLayout.setHorizontalGroup(
            pnlCboTrinhDoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblCboTrinhDo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(cboTrinhDo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlCboTrinhDoLayout.setVerticalGroup(
            pnlCboTrinhDoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCboTrinhDoLayout.createSequentialGroup()
                .addComponent(lblCboTrinhDo)
                .addGap(8, 8, 8)
                .addComponent(cboTrinhDo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlCboLanThi.setBackground(new java.awt.Color(255, 255, 255));

        lblCboLanThi.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblCboLanThi.setForeground(new java.awt.Color(36, 43, 54));
        lblCboLanThi.setText("Lần thi");

        cboLanThi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cboLanThi.setForeground(new java.awt.Color(36, 43, 54));
        cboLanThi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2" }));

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

        pnlTxtNgayThi.setBackground(new java.awt.Color(255, 255, 255));

        lblTxtNgayThi.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblTxtNgayThi.setForeground(new java.awt.Color(36, 43, 54));
        lblTxtNgayThi.setText("Ngày thi");

        txtNgayThi.setForeground(new java.awt.Color(36, 43, 54));
        txtNgayThi.setToolTipText("dd/MM/yyyy");
        txtNgayThi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtNgayThi.setEditable(false);
        txtNgayThi.setEnabled(false);

        lichNgayThi.setOpaque(false);

        javax.swing.GroupLayout pnlTxtNgayThiLayout = new javax.swing.GroupLayout(pnlTxtNgayThi);
        pnlTxtNgayThi.setLayout(pnlTxtNgayThiLayout);
        pnlTxtNgayThiLayout.setHorizontalGroup(
            pnlTxtNgayThiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTxtNgayThi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlTxtNgayThiLayout.createSequentialGroup()
                .addComponent(txtNgayThi)
                .addGap(6, 6, 6)
                .addComponent(lichNgayThi, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlTxtNgayThiLayout.setVerticalGroup(
            pnlTxtNgayThiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtNgayThiLayout.createSequentialGroup()
                .addComponent(lblTxtNgayThi)
                .addGap(8, 8, 8)
                .addGroup(pnlTxtNgayThiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNgayThi, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lichNgayThi, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pnlTxtSoCauThi.setBackground(new java.awt.Color(255, 255, 255));

        lblTxtSoCauThi.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblTxtSoCauThi.setForeground(new java.awt.Color(36, 43, 54));
        lblTxtSoCauThi.setText("Số câu thi (10–100)");

        txtSoCauThi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSoCauThi.setForeground(new java.awt.Color(36, 43, 54));

        javax.swing.GroupLayout pnlTxtSoCauThiLayout = new javax.swing.GroupLayout(pnlTxtSoCauThi);
        pnlTxtSoCauThi.setLayout(pnlTxtSoCauThiLayout);
        pnlTxtSoCauThiLayout.setHorizontalGroup(
            pnlTxtSoCauThiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTxtSoCauThi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(txtSoCauThi)
        );
        pnlTxtSoCauThiLayout.setVerticalGroup(
            pnlTxtSoCauThiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtSoCauThiLayout.createSequentialGroup()
                .addComponent(lblTxtSoCauThi)
                .addGap(8, 8, 8)
                .addComponent(txtSoCauThi, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlTxtThoiGian.setBackground(new java.awt.Color(255, 255, 255));

        lblTxtThoiGian.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblTxtThoiGian.setForeground(new java.awt.Color(36, 43, 54));
        lblTxtThoiGian.setText("Thời gian thi (15–60 phút)");

        txtThoiGian.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtThoiGian.setForeground(new java.awt.Color(36, 43, 54));

        javax.swing.GroupLayout pnlTxtThoiGianLayout = new javax.swing.GroupLayout(pnlTxtThoiGian);
        pnlTxtThoiGian.setLayout(pnlTxtThoiGianLayout);
        pnlTxtThoiGianLayout.setHorizontalGroup(
            pnlTxtThoiGianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTxtThoiGian, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(txtThoiGian)
        );
        pnlTxtThoiGianLayout.setVerticalGroup(
            pnlTxtThoiGianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtThoiGianLayout.createSequentialGroup()
                .addComponent(lblTxtThoiGian)
                .addGap(8, 8, 8)
                .addComponent(txtThoiGian, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlTxtSoCauSanCo.setBackground(new java.awt.Color(255, 255, 255));

        lblTxtSoCauSanCo.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblTxtSoCauSanCo.setForeground(new java.awt.Color(36, 43, 54));
        lblTxtSoCauSanCo.setText("Số câu hỏi sẵn có");

        txtSoCauSanCo.setEditable(false);
        txtSoCauSanCo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSoCauSanCo.setForeground(new java.awt.Color(36, 43, 54));

        javax.swing.GroupLayout pnlTxtSoCauSanCoLayout = new javax.swing.GroupLayout(pnlTxtSoCauSanCo);
        pnlTxtSoCauSanCo.setLayout(pnlTxtSoCauSanCoLayout);
        pnlTxtSoCauSanCoLayout.setHorizontalGroup(
            pnlTxtSoCauSanCoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTxtSoCauSanCo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(txtSoCauSanCo)
        );
        pnlTxtSoCauSanCoLayout.setVerticalGroup(
            pnlTxtSoCauSanCoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtSoCauSanCoLayout.createSequentialGroup()
                .addComponent(lblTxtSoCauSanCo)
                .addGap(8, 8, 8)
                .addComponent(txtSoCauSanCo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        btnKiemTraCauHoi.setBackground(new java.awt.Color(245, 246, 248));
        btnKiemTraCauHoi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnKiemTraCauHoi.setForeground(new java.awt.Color(36, 43, 54));
        btnKiemTraCauHoi.setText("Kiểm tra đủ câu hỏi");
        btnKiemTraCauHoi.setFocusPainted(false);
        btnKiemTraCauHoi.setOpaque(true);
        btnKiemTraCauHoi.addActionListener(this::btnKiemTraCauHoiActionPerformed);

        lblTrangThaiCauHoi.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblTrangThaiCauHoi.setForeground(new java.awt.Color(107, 114, 128));
        lblTrangThaiCauHoi.setText("Chưa kiểm tra số câu hỏi");

        btnThem.setBackground(new java.awt.Color(245, 246, 248));
        btnThem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnThem.setForeground(new java.awt.Color(36, 43, 54));
        btnThem.setText("Thêm");
        btnThem.setFocusPainted(false);
        btnThem.setOpaque(true);
        btnThem.addActionListener(this::btnThemActionPerformed);

        btnXoa.setBackground(new java.awt.Color(245, 246, 248));
        btnXoa.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnXoa.setForeground(new java.awt.Color(36, 43, 54));
        btnXoa.setText("Xóa");
        btnXoa.setFocusPainted(false);
        btnXoa.setOpaque(true);
        btnXoa.addActionListener(this::btnXoaActionPerformed);

        btnHieuChinh.setBackground(new java.awt.Color(245, 246, 248));
        btnHieuChinh.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnHieuChinh.setForeground(new java.awt.Color(36, 43, 54));
        btnHieuChinh.setText("Hiệu chỉnh");
        btnHieuChinh.setFocusPainted(false);
        btnHieuChinh.setOpaque(true);
        btnHieuChinh.addActionListener(this::btnHieuChinhActionPerformed);

        btnPhucHoi.setBackground(new java.awt.Color(245, 246, 248));
        btnPhucHoi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnPhucHoi.setForeground(new java.awt.Color(36, 43, 54));
        btnPhucHoi.setText("Phục hồi");
        btnPhucHoi.setFocusPainted(false);
        btnPhucHoi.setOpaque(true);
        btnPhucHoi.addActionListener(this::btnPhucHoiActionPerformed);

        btnGhi.setBackground(new java.awt.Color(205, 32, 45));
        btnGhi.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnGhi.setForeground(new java.awt.Color(255, 255, 255));
        btnGhi.setText("Ghi");
        btnGhi.setFocusPainted(false);
        btnGhi.setOpaque(true);
        btnGhi.addActionListener(this::btnGhiActionPerformed);

        tblDangKyThi.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        tblDangKyThi.setRowHeight(28);
        tblDangKyThi.setAutoCreateRowSorter(true);
        tblDangKyThi.setFillsViewportHeight(true);
        tblDangKyThi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã GV", "Mã lớp", "Môn học", "Trình độ", "Ngày thi", "Lần thi", "Số câu", "Thời gian (phút)"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDangKyThi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDangKyThiMouseClicked(evt);
            }
        });
        scrTblDangKyThi.setViewportView(tblDangKyThi);

        javax.swing.GroupLayout pnlNoiDungLayout = new javax.swing.GroupLayout(pnlNoiDung);
        pnlNoiDung.setLayout(pnlNoiDungLayout);
        pnlNoiDungLayout.setHorizontalGroup(
            pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTieuDe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblMoTa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addComponent(btnGhi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(scrTblDangKyThi)
        );
        pnlNoiDungLayout.setVerticalGroup(
            pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(lblTieuDe)
                .addGap(8, 8, 8)
                .addComponent(lblMoTa)
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
                    .addComponent(btnGhi, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addComponent(scrTblDangKyThi, javax.swing.GroupLayout.PREFERRED_SIZE, 240, Short.MAX_VALUE))
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

    private void btnKiemTraCauHoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKiemTraCauHoiActionPerformed
        txtSoCauSanCo.setText("");
        lblTrangThaiCauHoi.setText("Chưa kiểm tra số câu hỏi");
        try {
            String maMH = maDaChon(cboMonHoc);
            if (maMH.isEmpty()) throw new IllegalArgumentException("Vui lòng chọn môn học.");
            int soCau = nhapSo(txtSoCauThi, "Số câu thi", 10, 100);
            String trinhDo = (String) cboTrinhDo.getSelectedItem();
            Object[] dem = QuanLyDAO.demCauHoiThi(nguoiDung, maMH, trinhDo);
            int cungMuc = ((Number) dem[0]).intValue();
            int thapHon = ((Number) dem[1]).intValue();
            int canCungMuc = "C".equals(trinhDo) ? soCau : (int) Math.round(soCau * 0.7);
            int canThapHon = soCau - canCungMuc;
            txtSoCauSanCo.setText(cungMuc + " đúng trình độ, " + thapHon + " thấp hơn");
            lblTrangThaiCauHoi.setText(cungMuc >= canCungMuc && thapHon >= canThapHon
                    ? "Đủ câu hỏi" : "Cần " + canCungMuc + " câu đúng trình độ và " + canThapHon + " câu thấp hơn");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(pnlNoiDung, e.getMessage());
        } catch (SQLException e) {
            baoLoi(e);
        }
    }//GEN-LAST:event_btnKiemTraCauHoiActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        dot.them();
        capNhatCheDo();
        cboLop.requestFocusInWindow();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        Object[] dong = dongDaChon(tblDangKyThi);
        if (dong == null) {
            JOptionPane.showMessageDialog(pnlNoiDung, "Vui lòng chọn ca thi để xóa.");
            return;
        }
        int xacNhan = JOptionPane.showConfirmDialog(pnlNoiDung, "Xóa " + "ca thi của lớp " + dong[1] + "?",
                "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
        if (xacNhan != JOptionPane.YES_OPTION) return;
        try {
            QuanLyDAO.xoaCaThi(nguoiDung, (String) dong[1], (String) dong[2], ((Number) dong[5]).intValue());
            taiCaThi();
        } catch (SQLException e) {
            baoLoi(e);
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnHieuChinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHieuChinhActionPerformed
        dot.hieuChinh();
        capNhatCheDo();
        cboTrinhDo.requestFocusInWindow();
    }//GEN-LAST:event_btnHieuChinhActionPerformed

    private void btnPhucHoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPhucHoiActionPerformed
        dot.phucHoi(() -> QuanLyDAO.phucHoiCaThi(nguoiDung), this::taiCaThi);
        capNhatCheDo();
    }//GEN-LAST:event_btnPhucHoiActionPerformed

    private void btnGhiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGhiActionPerformed
        dot.ghi((thaoTac, truoc, sau) -> ghiCaThi(thaoTac, sau), this::taiCaThi);
        capNhatCheDo();
    }//GEN-LAST:event_btnGhiActionPerformed

    private void tblDangKyThiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDangKyThiMouseClicked
        if (!dot.dangNhap() && tblDangKyThi.rowAtPoint(evt.getPoint()) >= 0) {
            dienCaThi(dongDaChon(tblDangKyThi));
        }
    }//GEN-LAST:event_tblDangKyThiMouseClicked

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
    private javax.swing.JButton btnGhi;
    private javax.swing.JButton btnHieuChinh;
    private javax.swing.JButton btnKiemTraCauHoi;
    private javax.swing.JButton btnPhucHoi;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox cboLanThi;
    private javax.swing.JComboBox cboLop;
    private javax.swing.JComboBox cboMonHoc;
    private javax.swing.JComboBox cboTrinhDo;
    private javax.swing.JLabel lblCboLanThi;
    private javax.swing.JLabel lblCboLop;
    private javax.swing.JLabel lblCboMonHoc;
    private javax.swing.JLabel lblCboTrinhDo;
    private javax.swing.JLabel lblMoTa;
    private javax.swing.JLabel lblTieuDe;
    private javax.swing.JLabel lblTrangThaiCauHoi;
    private javax.swing.JLabel lblTxtMaGV;
    private javax.swing.JLabel lblTxtNgayThi;
    private javax.swing.JLabel lblTxtSoCauSanCo;
    private javax.swing.JLabel lblTxtSoCauThi;
    private javax.swing.JLabel lblTxtThoiGian;
    private com.github.lgooddatepicker.components.DatePicker lichNgayThi;
    private javax.swing.JPanel pnlCboLanThi;
    private javax.swing.JPanel pnlCboLop;
    private javax.swing.JPanel pnlCboMonHoc;
    private javax.swing.JPanel pnlCboTrinhDo;
    private javax.swing.JPanel pnlNoiDung;
    private javax.swing.JPanel pnlTxtMaGV;
    private javax.swing.JPanel pnlTxtNgayThi;
    private javax.swing.JPanel pnlTxtSoCauSanCo;
    private javax.swing.JPanel pnlTxtSoCauThi;
    private javax.swing.JPanel pnlTxtThoiGian;
    private javax.swing.JScrollPane scrTblDangKyThi;
    private javax.swing.JTable tblDangKyThi;
    private javax.swing.JTextField txtMaGV;
    private javax.swing.JFormattedTextField txtNgayThi;
    private javax.swing.JTextField txtSoCauSanCo;
    private javax.swing.JTextField txtSoCauThi;
    private javax.swing.JTextField txtThoiGian;
    // End of variables declaration//GEN-END:variables
}
