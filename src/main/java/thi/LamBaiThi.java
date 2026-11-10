package thi;

import connectionSQLServer.ThiDAO;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import models.BaiThi;
import models.CaThi;
import models.CauHoi;
import session.sessionSinhVien;
import trangchu.TrangChuSV;
/**
 * Làm bài và lưu mỗi lựa chọn xuống SQL để có thể tiếp tục sau khi đóng app.
 * Chỉnh bố cục bằng tab Design của NetBeans và giữ file .form đi kèm.
 */
public class LamBaiThi extends javax.swing.JFrame {
    private final sessionSinhVien nguoiDung;
    private CaThi cathi;
    private BaiThi baiThi;
    private int viTriCauHoi;
    private boolean dangLuu;
    private String luaChonDangLuu;
    private boolean dangNop;
    private boolean daKetThuc;
    private Timer timer;
    private long ketThucBaiThi;
    // Một hàng đợi: các lần lưu phải hoàn thành trước khi nộp bài.
    private final ExecutorService hangDoiSQL = Executors.newSingleThreadExecutor();

    public LamBaiThi() {
        this(null, null);
    }

    public LamBaiThi(sessionSinhVien nguoiDung, CaThi cathi) {
        initComponents();
        this.nguoiDung = nguoiDung;
        this.cathi = cathi;
        if (nguoiDung != null) {
            txtHoTen.setText(nguoiDung.getHo() + " " + nguoiDung.getTen());
            txtMaSV.setText(nguoiDung.getMaSV());
            txtLop.setText(nguoiDung.getTenLop());
        }
        btnTroVe.setVisible(nguoiDung != null);
        btnTroVe.setEnabled(false);
        choPhepLamBai(false);
        rdoA.addActionListener(e -> luuTienDo("A"));
        rdoB.addActionListener(e -> luuTienDo("B"));
        rdoC.addActionListener(e -> luuTienDo("C"));
        rdoD.addActionListener(e -> luuTienDo("D"));
        tblCauThi.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) moCauDangChon();
        });
        getContentPane().setBackground(new java.awt.Color(245, 246, 248));
        setLocationRelativeTo(null);
        if (nguoiDung != null && cathi != null) moBaiThi();
        else lblMoTa.setText("Chọn ca thi sau khi đăng nhập sinh viên.");
    }

    private void choPhepLamBai(boolean choPhep) {
        rdoA.setEnabled(choPhep);
        rdoB.setEnabled(choPhep);
        rdoC.setEnabled(choPhep);
        rdoD.setEnabled(choPhep);
        tblCauThi.setEnabled(choPhep);
        btnCauTruoc.setEnabled(choPhep && viTriCauHoi > 0);
        btnCauSau.setEnabled(choPhep && baiThi != null && viTriCauHoi < baiThi.cauHoi.size() - 1);
        btnXoaLuaChon.setEnabled(choPhep);
        btnNopBai.setEnabled(choPhep);
        btnTroVe.setEnabled(choPhep);
    }

    private void moBaiThi() {
        lblMoTa.setText("Đang tải bài thi...");
        hangDoiSQL.execute(() -> {
            try {
                BaiThi duLieu = ThiDAO.mo(nguoiDung, cathi);
                SwingUtilities.invokeLater(() -> hienThiBaiThi(duLieu));
            } catch (SQLException e) {
                SwingUtilities.invokeLater(() -> {
                    if (!isDisplayable()) return;
                    JOptionPane.showMessageDialog(this, e.getMessage());
                    new ChonCaThi(nguoiDung).setVisible(true);
                    dispose();
                });
            }
        });
    }

    private void hienThiBaiThi(BaiThi duLieu) {
        if (!isDisplayable()) return;
        baiThi = duLieu;
        cathi = baiThi.caThi;
        if (baiThi.daNop) {
            moKetQua(baiThi);
            return;
        }
        txtMonThi.setText(cathi.getTenMH());
        txtLanThi.setText(String.valueOf(cathi.getLan()));
        txtTrinhDo.setText(cathi.getTrinhDo());
        lblNgayThi.setText("Ngày thi: " + cathi.getNgaythi());
        DefaultTableModel model = (DefaultTableModel) tblCauThi.getModel();
        model.setRowCount(0);
        for (int i = 0; i < baiThi.cauHoi.size(); i++)
            model.addRow(new Object[]{i + 1, baiThi.cauHoi.get(i).getCauTraLoi()});
        capNhatSoCauDaLam();
        choPhepLamBai(true);
        hienThiCauHoi(baiThi.viTriCau);
        lblMoTa.setText("Tiến độ được lưu sau mỗi lựa chọn.");
        batDauDongHo(baiThi.conLaiMillis, baiThi.mocDocThoiGian);
    }

    private void hienThiCauHoi(int viTri) {
        if (baiThi == null || viTri < 0 || viTri >= baiThi.cauHoi.size()) return;
        viTriCauHoi = viTri;
        CauHoi cau = baiThi.cauHoi.get(viTri);
        lblSoCau.setText("Câu " + (viTri + 1) + " / " + baiThi.cauHoi.size());
        txtNoiDungCauHoi.setText(cau.getNoiDung());
        txtNoiDungCauHoi.setCaretPosition(0);
        rdoA.setText("A. " + cau.getNoiDungA());
        rdoB.setText("B. " + cau.getNoiDungB());
        rdoC.setText("C. " + cau.getNoiDungC());
        rdoD.setText("D. " + cau.getNoiDungD());
        hienThiLuaChon(cau.getCauTraLoi());
        int dong = tblCauThi.convertRowIndexToView(viTri);
        tblCauThi.setRowSelectionInterval(dong, dong);
        choPhepLamBai(!dangLuu && !daKetThuc);
    }

    private void hienThiLuaChon(String daChon) {
        if ("A".equals(daChon)) rdoA.setSelected(true);
        else if ("B".equals(daChon)) rdoB.setSelected(true);
        else if ("C".equals(daChon)) rdoC.setSelected(true);
        else if ("D".equals(daChon)) rdoD.setSelected(true);
        else grpDapAn.clearSelection();
    }

    private void capNhatSoCauDaLam() {
        int daLam = 0;
        for (CauHoi cau : baiThi.cauHoi) {
            if (!cau.getCauTraLoi().isEmpty()) daLam++;
        }
        lblDaTraLoi.setText("Đã trả lời: " + daLam + " / " + baiThi.cauHoi.size());
    }

    private void chuyenCau(int viTri) {
        if (dangLuu || daKetThuc || baiThi == null || viTri == viTriCauHoi
                || viTri < 0 || viTri >= baiThi.cauHoi.size()) return;
        hienThiCauHoi(viTri);
        luuTienDo(null);
    }

    private void moCauDangChon() {
        int dong = tblCauThi.getSelectedRow();
        if (dangLuu) {
            // Bảng giữ màu bình thường; chưa đổi dòng khi vị trí cũ đang được lưu.
            int dongHienTai = tblCauThi.convertRowIndexToView(viTriCauHoi);
            if (dong != dongHienTai) tblCauThi.setRowSelectionInterval(dongHienTai, dongHienTai);
            return;
        }
        if (dong >= 0) chuyenCau(tblCauThi.convertRowIndexToModel(dong));
    }

    private void luuTienDo(String luaChon) {
        if (daKetThuc || baiThi == null) return;
        if (dangLuu) {
            // RadioButton đổi lựa chọn trước khi gọi handler, nên giữ lựa chọn đang lưu.
            hienThiLuaChon(luaChonDangLuu);
            return;
        }
        dangLuu = true;
        if (luaChon == null) luaChonDangLuu = baiThi.cauHoi.get(viTriCauHoi).getCauTraLoi();
        else luaChonDangLuu = luaChon;
        hienThiLuaChon(luaChonDangLuu);
        lblMoTa.setText("Đang lưu tiến độ...");
        int viTri = viTriCauHoi;
        hangDoiSQL.execute(() -> {
            try {
                ThiDAO.luu(nguoiDung, cathi, viTri, luaChon);
                SwingUtilities.invokeLater(() -> {
                    if (!isDisplayable()) return;
                    baiThi.viTriCau = viTri;
                    if (luaChon != null) {
                        baiThi.cauHoi.get(viTri).setCauTraLoi(luaChon);
                        tblCauThi.getModel().setValueAt(luaChon, viTri, 1);
                    }
                    dangLuu = false;
                    capNhatSoCauDaLam();
                    if (!daKetThuc) {
                        lblMoTa.setText("Đã lưu tiến độ.");
                    }
                });
            } catch (SQLException e) {
                SwingUtilities.invokeLater(() -> {
                    if (!isDisplayable()) return;
                    dangLuu = false;
                    if (e.getErrorCode() == 50003 || e.getErrorCode() == 50004) {
                        ketThucBaiThi();
                    } else if (!daKetThuc) {
                        hienThiCauHoi(baiThi.viTriCau);
                        lblMoTa.setText("Chưa lưu được. Vui lòng chọn lại.");
                        JOptionPane.showMessageDialog(this, "Không lưu được tiến độ: " + e.getMessage());
                    }
                });
            }
        });
    }

    private void batDauDongHo(long conLaiMillis, long mocDocThoiGian) {
        // nanoTime đo khoảng thời gian, không phụ thuộc việc đổi giờ máy Java.
        ketThucBaiThi = mocDocThoiGian + conLaiMillis * 1_000_000L;
        if (timer != null) timer.stop();
        timer = new Timer(1000, e -> {
            long millis = Math.max(0, (ketThucBaiThi - System.nanoTime()) / 1_000_000L);
            long giay = (millis + 999) / 1000;
            lblThoiGianConLai.setText(String.format("Thời gian còn lại: %02d:%02d", giay / 60, giay % 60));
            if (giay == 0) ketThucBaiThi();
        });
        timer.setInitialDelay(0);
        timer.start();
    }

    private void ketThucBaiThi() {
        if (dangNop || baiThi == null) return;
        daKetThuc = true;
        dangNop = true;
        if (timer != null) timer.stop();
        choPhepLamBai(false);
        lblMoTa.setText("Đang nộp bài...");
        hangDoiSQL.execute(() -> {
            try {
                BaiThi ketQua = ThiDAO.nop(nguoiDung, cathi);
                SwingUtilities.invokeLater(() -> { if (isDisplayable()) moKetQua(ketQua); });
            } catch (SQLException e) {
                SwingUtilities.invokeLater(() -> {
                    if (!isDisplayable()) return;
                    dangNop = false;
                    btnNopBai.setEnabled(true);
                    lblMoTa.setText("Chưa nộp được. Bấm Nộp bài để thử lại.");
                    JOptionPane.showMessageDialog(this, e.getMessage());
                });
            }
        });
    }

    private void moKetQua(BaiThi ketQua) {
        new KetQuaThi(nguoiDung, ketQua).setVisible(true);
        dispose();
    }

    @Override
    public void dispose() {
        if (timer != null) timer.stop();
        hangDoiSQL.shutdown();
        super.dispose();
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grpDapAn = new javax.swing.ButtonGroup();
        pnlNoiDung = new javax.swing.JPanel();
        lblTieuDe = new javax.swing.JLabel();
        lblMoTa = new javax.swing.JLabel();
        pnlTxtMaSV = new javax.swing.JPanel();
        lblTxtMaSV = new javax.swing.JLabel();
        txtMaSV = new javax.swing.JTextField();
        pnlTxtHoTen = new javax.swing.JPanel();
        lblTxtHoTen = new javax.swing.JLabel();
        txtHoTen = new javax.swing.JTextField();
        pnlTxtLop = new javax.swing.JPanel();
        lblTxtLop = new javax.swing.JLabel();
        txtLop = new javax.swing.JTextField();
        pnlTxtMonThi = new javax.swing.JPanel();
        lblTxtMonThi = new javax.swing.JLabel();
        txtMonThi = new javax.swing.JTextField();
        pnlTxtLanThi = new javax.swing.JPanel();
        lblTxtLanThi = new javax.swing.JLabel();
        txtLanThi = new javax.swing.JTextField();
        pnlTxtTrinhDo = new javax.swing.JPanel();
        lblTxtTrinhDo = new javax.swing.JLabel();
        txtTrinhDo = new javax.swing.JTextField();
        lblThoiGianConLai = new javax.swing.JLabel();
        lblNgayThi = new javax.swing.JLabel();
        pnlCauHoi = new javax.swing.JPanel();
        lblSoCau = new javax.swing.JLabel();
        pnlTxtNoiDungCauHoi = new javax.swing.JPanel();
        lblTxtNoiDungCauHoi = new javax.swing.JLabel();
        scrTxtNoiDungCauHoi = new javax.swing.JScrollPane();
        txtNoiDungCauHoi = new javax.swing.JTextArea();
        rdoA = new javax.swing.JRadioButton();
        rdoB = new javax.swing.JRadioButton();
        rdoC = new javax.swing.JRadioButton();
        rdoD = new javax.swing.JRadioButton();
        btnCauTruoc = new javax.swing.JButton();
        btnCauSau = new javax.swing.JButton();
        btnXoaLuaChon = new javax.swing.JButton();
        pnlDanhSachCau = new javax.swing.JPanel();
        lblDanhSachCau = new javax.swing.JLabel();
        scrTblCauThi = new javax.swing.JScrollPane();
        tblCauThi = new javax.swing.JTable();
        lblDaTraLoi = new javax.swing.JLabel();
        btnNopBai = new javax.swing.JButton();
        btnTroVe = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PTIT | LÀM BÀI THI");
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/ptit-logo.png")).getImage());

        pnlNoiDung.setBackground(new java.awt.Color(255, 255, 255));
        pnlNoiDung.setBorder(javax.swing.BorderFactory.createEmptyBorder(24, 24, 24, 24));

        lblTieuDe.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTieuDe.setForeground(new java.awt.Color(205, 32, 45));
        lblTieuDe.setText("LÀM BÀI THI");

        lblMoTa.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblMoTa.setForeground(new java.awt.Color(107, 114, 128));
        lblMoTa.setText("Bài thi trắc nghiệm");

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

        pnlTxtLop.setBackground(new java.awt.Color(255, 255, 255));

        lblTxtLop.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblTxtLop.setForeground(new java.awt.Color(36, 43, 54));
        lblTxtLop.setText("Lớp");

        txtLop.setEditable(false);
        txtLop.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtLop.setForeground(new java.awt.Color(36, 43, 54));

        javax.swing.GroupLayout pnlTxtLopLayout = new javax.swing.GroupLayout(pnlTxtLop);
        pnlTxtLop.setLayout(pnlTxtLopLayout);
        pnlTxtLopLayout.setHorizontalGroup(
            pnlTxtLopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTxtLop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(txtLop)
        );
        pnlTxtLopLayout.setVerticalGroup(
            pnlTxtLopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtLopLayout.createSequentialGroup()
                .addComponent(lblTxtLop)
                .addGap(8, 8, 8)
                .addComponent(txtLop, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlTxtMonThi.setBackground(new java.awt.Color(255, 255, 255));

        lblTxtMonThi.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblTxtMonThi.setForeground(new java.awt.Color(36, 43, 54));
        lblTxtMonThi.setText("Môn thi");

        txtMonThi.setEditable(false);
        txtMonThi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMonThi.setForeground(new java.awt.Color(36, 43, 54));

        javax.swing.GroupLayout pnlTxtMonThiLayout = new javax.swing.GroupLayout(pnlTxtMonThi);
        pnlTxtMonThi.setLayout(pnlTxtMonThiLayout);
        pnlTxtMonThiLayout.setHorizontalGroup(
            pnlTxtMonThiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTxtMonThi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(txtMonThi)
        );
        pnlTxtMonThiLayout.setVerticalGroup(
            pnlTxtMonThiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtMonThiLayout.createSequentialGroup()
                .addComponent(lblTxtMonThi)
                .addGap(8, 8, 8)
                .addComponent(txtMonThi, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlTxtLanThi.setBackground(new java.awt.Color(255, 255, 255));

        lblTxtLanThi.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblTxtLanThi.setForeground(new java.awt.Color(36, 43, 54));
        lblTxtLanThi.setText("Lần thi");

        txtLanThi.setEditable(false);
        txtLanThi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtLanThi.setForeground(new java.awt.Color(36, 43, 54));

        javax.swing.GroupLayout pnlTxtLanThiLayout = new javax.swing.GroupLayout(pnlTxtLanThi);
        pnlTxtLanThi.setLayout(pnlTxtLanThiLayout);
        pnlTxtLanThiLayout.setHorizontalGroup(
            pnlTxtLanThiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTxtLanThi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(txtLanThi)
        );
        pnlTxtLanThiLayout.setVerticalGroup(
            pnlTxtLanThiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtLanThiLayout.createSequentialGroup()
                .addComponent(lblTxtLanThi)
                .addGap(8, 8, 8)
                .addComponent(txtLanThi, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlTxtTrinhDo.setBackground(new java.awt.Color(255, 255, 255));

        lblTxtTrinhDo.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblTxtTrinhDo.setForeground(new java.awt.Color(36, 43, 54));
        lblTxtTrinhDo.setText("Trình độ");

        txtTrinhDo.setEditable(false);
        txtTrinhDo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTrinhDo.setForeground(new java.awt.Color(36, 43, 54));

        javax.swing.GroupLayout pnlTxtTrinhDoLayout = new javax.swing.GroupLayout(pnlTxtTrinhDo);
        pnlTxtTrinhDo.setLayout(pnlTxtTrinhDoLayout);
        pnlTxtTrinhDoLayout.setHorizontalGroup(
            pnlTxtTrinhDoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTxtTrinhDo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(txtTrinhDo)
        );
        pnlTxtTrinhDoLayout.setVerticalGroup(
            pnlTxtTrinhDoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtTrinhDoLayout.createSequentialGroup()
                .addComponent(lblTxtTrinhDo)
                .addGap(8, 8, 8)
                .addComponent(txtTrinhDo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        lblThoiGianConLai.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblThoiGianConLai.setForeground(new java.awt.Color(205, 32, 45));
        lblThoiGianConLai.setText("Thời gian còn lại: — : —");

        lblNgayThi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNgayThi.setForeground(new java.awt.Color(107, 114, 128));
        lblNgayThi.setText("Ngày thi: —");

        pnlCauHoi.setBackground(new java.awt.Color(255, 255, 255));

        lblSoCau.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblSoCau.setForeground(new java.awt.Color(36, 43, 54));
        lblSoCau.setText("Câu — / —");

        pnlTxtNoiDungCauHoi.setBackground(new java.awt.Color(255, 255, 255));

        lblTxtNoiDungCauHoi.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblTxtNoiDungCauHoi.setForeground(new java.awt.Color(36, 43, 54));
        lblTxtNoiDungCauHoi.setText("Nội dung câu hỏi");

        txtNoiDungCauHoi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtNoiDungCauHoi.setLineWrap(true);
        txtNoiDungCauHoi.setWrapStyleWord(true);
        txtNoiDungCauHoi.setRows(3);
        txtNoiDungCauHoi.setColumns(24);
        txtNoiDungCauHoi.setEditable(false);
        scrTxtNoiDungCauHoi.setViewportView(txtNoiDungCauHoi);

        javax.swing.GroupLayout pnlTxtNoiDungCauHoiLayout = new javax.swing.GroupLayout(pnlTxtNoiDungCauHoi);
        pnlTxtNoiDungCauHoi.setLayout(pnlTxtNoiDungCauHoiLayout);
        pnlTxtNoiDungCauHoiLayout.setHorizontalGroup(
            pnlTxtNoiDungCauHoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTxtNoiDungCauHoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlTxtNoiDungCauHoiLayout.createSequentialGroup()
                .addComponent(scrTxtNoiDungCauHoi, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlTxtNoiDungCauHoiLayout.setVerticalGroup(
            pnlTxtNoiDungCauHoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtNoiDungCauHoiLayout.createSequentialGroup()
                .addComponent(lblTxtNoiDungCauHoi)
                .addGap(8, 8, 8)
                .addComponent(scrTxtNoiDungCauHoi, javax.swing.GroupLayout.PREFERRED_SIZE, 88, Short.MAX_VALUE))
        );

        rdoA.setBackground(new java.awt.Color(255, 255, 255));
        grpDapAn.add(rdoA);
        rdoA.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rdoA.setText("A. Phương án A");

        rdoB.setBackground(new java.awt.Color(255, 255, 255));
        grpDapAn.add(rdoB);
        rdoB.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rdoB.setText("B. Phương án B");

        rdoC.setBackground(new java.awt.Color(255, 255, 255));
        grpDapAn.add(rdoC);
        rdoC.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rdoC.setText("C. Phương án C");

        rdoD.setBackground(new java.awt.Color(255, 255, 255));
        grpDapAn.add(rdoD);
        rdoD.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rdoD.setText("D. Phương án D");

        btnCauTruoc.setBackground(new java.awt.Color(245, 246, 248));
        btnCauTruoc.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnCauTruoc.setForeground(new java.awt.Color(36, 43, 54));
        btnCauTruoc.setText("Câu trước");
        btnCauTruoc.setFocusPainted(false);
        btnCauTruoc.setOpaque(true);
        btnCauTruoc.addActionListener(this::btnCauTruocActionPerformed);

        btnCauSau.setBackground(new java.awt.Color(245, 246, 248));
        btnCauSau.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnCauSau.setForeground(new java.awt.Color(36, 43, 54));
        btnCauSau.setText("Câu tiếp");
        btnCauSau.setFocusPainted(false);
        btnCauSau.setOpaque(true);
        btnCauSau.addActionListener(this::btnCauSauActionPerformed);

        btnXoaLuaChon.setBackground(new java.awt.Color(245, 246, 248));
        btnXoaLuaChon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnXoaLuaChon.setForeground(new java.awt.Color(36, 43, 54));
        btnXoaLuaChon.setText("Bỏ chọn");
        btnXoaLuaChon.setFocusPainted(false);
        btnXoaLuaChon.setOpaque(true);
        btnXoaLuaChon.addActionListener(this::btnXoaLuaChonActionPerformed);

        javax.swing.GroupLayout pnlCauHoiLayout = new javax.swing.GroupLayout(pnlCauHoi);
        pnlCauHoi.setLayout(pnlCauHoiLayout);
        pnlCauHoiLayout.setHorizontalGroup(
            pnlCauHoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCauHoiLayout.createSequentialGroup()
                .addComponent(lblSoCau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(176, 176, 176))
            .addComponent(pnlTxtNoiDungCauHoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(rdoA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(rdoB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(rdoC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(rdoD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlCauHoiLayout.createSequentialGroup()
                .addComponent(btnCauTruoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(btnCauSau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(btnXoaLuaChon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlCauHoiLayout.setVerticalGroup(
            pnlCauHoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCauHoiLayout.createSequentialGroup()
                .addComponent(lblSoCau)
                .addGap(8, 8, 8)
                .addComponent(pnlTxtNoiDungCauHoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(rdoA, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(rdoB, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(rdoC, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(rdoD, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addGroup(pnlCauHoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCauTruoc, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCauSau, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoaLuaChon, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pnlDanhSachCau.setBackground(new java.awt.Color(255, 255, 255));

        lblDanhSachCau.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblDanhSachCau.setForeground(new java.awt.Color(205, 32, 45));
        lblDanhSachCau.setText("Danh sách câu hỏi");

        tblCauThi.setAutoCreateRowSorter(true);
        tblCauThi.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        tblCauThi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Câu", "Đã chọn"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCauThi.setFillsViewportHeight(true);
        tblCauThi.setRowHeight(28);
        scrTblCauThi.setViewportView(tblCauThi);

        lblDaTraLoi.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblDaTraLoi.setForeground(new java.awt.Color(107, 114, 128));
        lblDaTraLoi.setText("Đã trả lời: — / —");

        javax.swing.GroupLayout pnlDanhSachCauLayout = new javax.swing.GroupLayout(pnlDanhSachCau);
        pnlDanhSachCau.setLayout(pnlDanhSachCauLayout);
        pnlDanhSachCauLayout.setHorizontalGroup(
            pnlDanhSachCauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblDaTraLoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlDanhSachCauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addComponent(scrTblCauThi, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addComponent(lblDanhSachCau, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlDanhSachCauLayout.setVerticalGroup(
            pnlDanhSachCauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDanhSachCauLayout.createSequentialGroup()
                .addComponent(lblDanhSachCau)
                .addGap(8, 8, 8)
                .addComponent(scrTblCauThi, javax.swing.GroupLayout.PREFERRED_SIZE, 280, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(lblDaTraLoi)
                .addGap(46, 46, 46))
        );

        btnNopBai.setBackground(new java.awt.Color(205, 32, 45));
        btnNopBai.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNopBai.setForeground(new java.awt.Color(255, 255, 255));
        btnNopBai.setText("Nộp bài");
        btnNopBai.setFocusPainted(false);
        btnNopBai.setOpaque(true);
        btnNopBai.addActionListener(this::btnNopBaiActionPerformed);

        btnTroVe.setText("Trở về");
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlDanhSachCau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(btnTroVe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnNopBai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                    .addComponent(pnlTxtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlTxtLop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlTxtMonThi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlTxtLanThi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlTxtTrinhDo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblThoiGianConLai)
                    .addComponent(lblNgayThi))
                .addGap(8, 8, 8)
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlCauHoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlDanhSachCau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addComponent(btnNopBai, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void btnCauTruocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCauTruocActionPerformed
        chuyenCau(viTriCauHoi - 1);
    }//GEN-LAST:event_btnCauTruocActionPerformed

    private void btnCauSauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCauSauActionPerformed
        chuyenCau(viTriCauHoi + 1);
    }//GEN-LAST:event_btnCauSauActionPerformed

    private void btnXoaLuaChonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaLuaChonActionPerformed
        luuTienDo("");
    }//GEN-LAST:event_btnXoaLuaChonActionPerformed

    private void btnNopBaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNopBaiActionPerformed
        ketThucBaiThi();
    }//GEN-LAST:event_btnNopBaiActionPerformed

    private void btnTroVeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTroVeActionPerformed
        if (dangLuu || dangNop) return;
        new TrangChuSV(nguoiDung).setVisible(true);
        dispose();
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
    private javax.swing.JButton btnCauSau;
    private javax.swing.JButton btnCauTruoc;
    private javax.swing.JButton btnNopBai;
    private javax.swing.JButton btnTroVe;
    private javax.swing.JButton btnXoaLuaChon;
    private javax.swing.ButtonGroup grpDapAn;
    private javax.swing.JLabel lblDaTraLoi;
    private javax.swing.JLabel lblDanhSachCau;
    private javax.swing.JLabel lblMoTa;
    private javax.swing.JLabel lblNgayThi;
    private javax.swing.JLabel lblSoCau;
    private javax.swing.JLabel lblThoiGianConLai;
    private javax.swing.JLabel lblTieuDe;
    private javax.swing.JLabel lblTxtHoTen;
    private javax.swing.JLabel lblTxtLanThi;
    private javax.swing.JLabel lblTxtLop;
    private javax.swing.JLabel lblTxtMaSV;
    private javax.swing.JLabel lblTxtMonThi;
    private javax.swing.JLabel lblTxtNoiDungCauHoi;
    private javax.swing.JLabel lblTxtTrinhDo;
    private javax.swing.JPanel pnlCauHoi;
    private javax.swing.JPanel pnlDanhSachCau;
    private javax.swing.JPanel pnlNoiDung;
    private javax.swing.JPanel pnlTxtHoTen;
    private javax.swing.JPanel pnlTxtLanThi;
    private javax.swing.JPanel pnlTxtLop;
    private javax.swing.JPanel pnlTxtMaSV;
    private javax.swing.JPanel pnlTxtMonThi;
    private javax.swing.JPanel pnlTxtNoiDungCauHoi;
    private javax.swing.JPanel pnlTxtTrinhDo;
    private javax.swing.JRadioButton rdoA;
    private javax.swing.JRadioButton rdoB;
    private javax.swing.JRadioButton rdoC;
    private javax.swing.JRadioButton rdoD;
    private javax.swing.JScrollPane scrTblCauThi;
    private javax.swing.JScrollPane scrTxtNoiDungCauHoi;
    private javax.swing.JTable tblCauThi;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtLanThi;
    private javax.swing.JTextField txtLop;
    private javax.swing.JTextField txtMaSV;
    private javax.swing.JTextField txtMonThi;
    private javax.swing.JTextArea txtNoiDungCauHoi;
    private javax.swing.JTextField txtTrinhDo;
    // End of variables declaration//GEN-END:variables
}
