package thi;

import javax.swing.Timer;
import session.SessionDangNhap;
import session.sessionSinhVien;
import session.CaThi;
import connectionSQLServer.connection;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import session.CauHoi;
/**
 * Giao diện LamBaiThi. Nghiệp vụ sẽ được bổ sung trong các event handler.
 * Chỉnh bố cục bằng tab Design của NetBeans và giữ file .form đi kèm.
 */
public class LamBaiThi extends javax.swing.JFrame {
    private final SessionDangNhap nguoiDung;
    private CaThi cathi;
    private ArrayList<CauHoi> danhSachCauHoi = new ArrayList<>();

    private int viTriCauHoi = 0;
    private boolean daKetThuc = false;

    private int soCauDung;
    private double diem;
    private javax.swing.Timer timer;
    private long ketThucBaiThi;
    public LamBaiThi() {
        this(null,null);
    }
    public void demNguocThoiGian(int soPhut){
        ketThucBaiThi = System.currentTimeMillis() + soPhut * 60_000L;
        
        if(timer!=null){
            timer.stop();
        }
        timer = new Timer(1000, e-> {
            long giayConLai = Math.max(0,
                    (ketThucBaiThi - System.currentTimeMillis() + 999)/1000);
            
            long phut = giayConLai / 60;
            long giay = giayConLai % 60;
            
            lblThoiGianConLai.setText("Thời gian còn lại :" + phut + " : " + giay);
            
            if(giayConLai == 0){
                timer.stop();
            }
        }); 
    }
    public LamBaiThi(SessionDangNhap nguoiDung, CaThi cathi) {
        initComponents();
        this.nguoiDung = nguoiDung;
        this.cathi = cathi;
        if(nguoiDung instanceof sessionSinhVien && cathi != null ){
            sessionSinhVien sinhVien = (sessionSinhVien) nguoiDung;
            txtHoTen.setText(sinhVien.getHo() + " " + sinhVien.getTen());
            txtMaSV.setText(sinhVien.getMaSV());
            txtLop.setText(sinhVien.getTenLop());
            txtMonThi.setText(cathi.getTenMH());
            txtLanThi.setText(String.valueOf(cathi.getLan()));
            txtTrinhDo.setText(cathi.getTrinhDo());
            lblNgayThi.setText("Ngày Thi : " + cathi.getNgaythi());
        }
        btnTroVe.setVisible(nguoiDung instanceof session.sessionSinhVien);
        getContentPane().setBackground(new java.awt.Color(245, 246, 248));
        setLocationRelativeTo(null);
        demNguocThoiGian(cathi.getThoiGianThi());
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
        btnChonLaiCau = new javax.swing.JButton();
        btnNopBai = new javax.swing.JButton();
        btnXemLai = new javax.swing.JButton();
        btnTroVe = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PTIT | LÀM BÀI THI");
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/ptit-logo.png")).getImage());

        pnlNoiDung.setBackground(new java.awt.Color(255, 255, 255));
        pnlNoiDung.setBorder(javax.swing.BorderFactory.createEmptyBorder(24, 24, 24, 24));

        lblTieuDe.setText("LÀM BÀI THI");
        lblTieuDe.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTieuDe.setForeground(new java.awt.Color(205, 32, 45));

        lblMoTa.setText("Bài thi trắc nghiệm");
        lblMoTa.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblMoTa.setForeground(new java.awt.Color(107, 114, 128));

        pnlTxtMaSV.setBackground(new java.awt.Color(255, 255, 255));

        lblTxtMaSV.setText("Mã sinh viên");
        lblTxtMaSV.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblTxtMaSV.setForeground(new java.awt.Color(36, 43, 54));

        txtMaSV.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMaSV.setForeground(new java.awt.Color(36, 43, 54));
        txtMaSV.setEditable(false);

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

        lblTxtMonThi.setText("Môn thi");
        lblTxtMonThi.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblTxtMonThi.setForeground(new java.awt.Color(36, 43, 54));

        txtMonThi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMonThi.setForeground(new java.awt.Color(36, 43, 54));
        txtMonThi.setEditable(false);

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
            .addComponent(txtTrinhDo)
        );
        pnlTxtTrinhDoLayout.setVerticalGroup(
            pnlTxtTrinhDoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtTrinhDoLayout.createSequentialGroup()
                .addComponent(lblTxtTrinhDo)
                .addGap(8, 8, 8)
                .addComponent(txtTrinhDo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        lblThoiGianConLai.setText("Thời gian còn lại: — : —");
        lblThoiGianConLai.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblThoiGianConLai.setForeground(new java.awt.Color(205, 32, 45));

        lblNgayThi.setText("Ngày thi: —");
        lblNgayThi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNgayThi.setForeground(new java.awt.Color(107, 114, 128));

        pnlCauHoi.setBackground(new java.awt.Color(255, 255, 255));

        lblSoCau.setText("Câu — / —");
        lblSoCau.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblSoCau.setForeground(new java.awt.Color(36, 43, 54));

        pnlTxtNoiDungCauHoi.setBackground(new java.awt.Color(255, 255, 255));

        lblTxtNoiDungCauHoi.setText("Nội dung câu hỏi");
        lblTxtNoiDungCauHoi.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblTxtNoiDungCauHoi.setForeground(new java.awt.Color(36, 43, 54));

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
            .addComponent(scrTxtNoiDungCauHoi)
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

        btnCauTruoc.setText("Câu trước");
        btnCauTruoc.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnCauTruoc.setBackground(new java.awt.Color(245, 246, 248));
        btnCauTruoc.setForeground(new java.awt.Color(36, 43, 54));
        btnCauTruoc.setOpaque(true);
        btnCauTruoc.setFocusPainted(false);
        btnCauTruoc.addActionListener(this::btnCauTruocActionPerformed);

        btnCauSau.setText("Câu tiếp");
        btnCauSau.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnCauSau.setBackground(new java.awt.Color(245, 246, 248));
        btnCauSau.setForeground(new java.awt.Color(36, 43, 54));
        btnCauSau.setOpaque(true);
        btnCauSau.setFocusPainted(false);
        btnCauSau.addActionListener(this::btnCauSauActionPerformed);

        btnXoaLuaChon.setText("Bỏ chọn");
        btnXoaLuaChon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnXoaLuaChon.setBackground(new java.awt.Color(245, 246, 248));
        btnXoaLuaChon.setForeground(new java.awt.Color(36, 43, 54));
        btnXoaLuaChon.setOpaque(true);
        btnXoaLuaChon.setFocusPainted(false);
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

        lblDanhSachCau.setText("Danh sách câu hỏi");
        lblDanhSachCau.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblDanhSachCau.setForeground(new java.awt.Color(205, 32, 45));

        tblCauThi.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        tblCauThi.setRowHeight(28);
        tblCauThi.setAutoCreateRowSorter(true);
        tblCauThi.setFillsViewportHeight(true);
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
        scrTblCauThi.setViewportView(tblCauThi);

        lblDaTraLoi.setText("Đã trả lời: — / —");
        lblDaTraLoi.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblDaTraLoi.setForeground(new java.awt.Color(107, 114, 128));

        btnChonLaiCau.setText("Chọn lại câu đã làm");
        btnChonLaiCau.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnChonLaiCau.setBackground(new java.awt.Color(245, 246, 248));
        btnChonLaiCau.setForeground(new java.awt.Color(36, 43, 54));
        btnChonLaiCau.setOpaque(true);
        btnChonLaiCau.setFocusPainted(false);
        btnChonLaiCau.addActionListener(this::btnChonLaiCauActionPerformed);

        javax.swing.GroupLayout pnlDanhSachCauLayout = new javax.swing.GroupLayout(pnlDanhSachCau);
        pnlDanhSachCau.setLayout(pnlDanhSachCauLayout);
        pnlDanhSachCauLayout.setHorizontalGroup(
            pnlDanhSachCauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblDanhSachCau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(scrTblCauThi)
            .addComponent(lblDaTraLoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnChonLaiCau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlDanhSachCauLayout.setVerticalGroup(
            pnlDanhSachCauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDanhSachCauLayout.createSequentialGroup()
                .addComponent(lblDanhSachCau)
                .addGap(8, 8, 8)
                .addComponent(scrTblCauThi, javax.swing.GroupLayout.PREFERRED_SIZE, 280, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(lblDaTraLoi)
                .addGap(8, 8, 8)
                .addComponent(btnChonLaiCau, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        btnNopBai.setText("Nộp bài");
        btnNopBai.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNopBai.setBackground(new java.awt.Color(205, 32, 45));
        btnNopBai.setForeground(new java.awt.Color(255, 255, 255));
        btnNopBai.setOpaque(true);
        btnNopBai.setFocusPainted(false);
        btnNopBai.addActionListener(this::btnNopBaiActionPerformed);

        btnXemLai.setText("Xem lại câu đã làm");
        btnXemLai.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnXemLai.setBackground(new java.awt.Color(245, 246, 248));
        btnXemLai.setForeground(new java.awt.Color(36, 43, 54));
        btnXemLai.setOpaque(true);
        btnXemLai.setFocusPainted(false);
        btnXemLai.addActionListener(this::btnXemLaiActionPerformed);

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
                .addGap(8, 8, 8)
                .addComponent(pnlDanhSachCau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(btnNopBai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(btnXemLai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addGroup(pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNopBai, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXemLai, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
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
    private javax.swing.JButton btnCauSau;
    private javax.swing.JButton btnCauTruoc;
    private javax.swing.JButton btnChonLaiCau;
    private javax.swing.JButton btnNopBai;
    private javax.swing.JButton btnTroVe;
    private javax.swing.JButton btnXemLai;
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
