package trangchu;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Rectangle;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Scrollable;
import javax.swing.SwingConstants;
import session.sessionGiaoVien;


public class TrangChuGV extends javax.swing.JFrame {
    private static final String MENU_GIAO_VIEN = "menuGiaoVien";

    private final sessionGiaoVien nguoiDung;
    private JFrame formDangMo;

    public TrangChuGV() {
        this(null);
    }

    public TrangChuGV(sessionGiaoVien nguoiDung) {
        initComponents();
        this.nguoiDung = nguoiDung;
        if (nguoiDung != null) {
            txtMaNguoiDung.setText(nguoiDung.getMaGV());
            txtHoTen.setText(nguoiDung.getHo() + " " + nguoiDung.getTen());
            String soDienThoai = nguoiDung.getSoDTLL();
            txtSoDTLL.setText(soDienThoai == null ? "Chưa có thông tin !" : soDienThoai);
            String diaChi = nguoiDung.getDiaChi();
            txtDiaChi.setText(diaChi == null ? "Chưa có thông tin !" : diaChi);
        } else {
            for (java.awt.Component chucNang : new java.awt.Component[]{
                lblQuanLy, btnMonHoc, btnLopSinhVien, btnGiaoVien, btnBoDe, btnChuanBiThi,
                lblThi, btnBangDiem, btnTaoTaiKhoan, btnDoiMatKhau, btnSaoLuuPhucHoi
            }) {
                chucNang.setVisible(false);
            }
        }

        chonMenu(btnTrangChu);
        Rectangle manHinh = java.awt.GraphicsEnvironment
                .getLocalGraphicsEnvironment().getMaximumWindowBounds();
        setSize(Math.min(1400, manHinh.width - 48), Math.min(850, manHinh.height - 48));
        getContentPane().setBackground(new java.awt.Color(245, 246, 248));
        setLocationRelativeTo(null);
    }

    public static void moFormCon(JFrame formHienTai, JFrame formMoi) {
        TrangChuGV trangChu = timTrangChu(formHienTai);
        if (trangChu != null) {
            trangChu.hienThiForm(formMoi);
        } else {
            formMoi.setVisible(true);
            formHienTai.dispose();
        }
    }

    public static void veTrangChuTu(JFrame formHienTai, sessionGiaoVien nguoiDung) {
        TrangChuGV trangChu = timTrangChu(formHienTai);
        if (trangChu != null) {
            trangChu.hienThiTrangChu();
        } else {
            new TrangChuGV(nguoiDung).setVisible(true);
            formHienTai.dispose();
        }
    }

    public static void dangXuatTu(JFrame form) {
        TrangChuGV trangChu = timTrangChu(form);
        new dangNhapdangKy.DangNhap().setVisible(true);
        if (trangChu != null) {
            trangChu.dispose();
        } else {
            form.dispose();
        }
    }

    private static TrangChuGV timTrangChu(JFrame form) {
        Object menu = form.getRootPane().getClientProperty(MENU_GIAO_VIEN);
        return menu instanceof TrangChuGV trangChu ? trangChu : null;
    }

    private void hienThiForm(JFrame form) {
        dongFormDangMo();
        formDangMo = form;
        form.getRootPane().putClientProperty(MENU_GIAO_VIEN, this);

        Container noiDung = form.getContentPane();
        form.setContentPane(new JPanel());
        JScrollPane cuon = new JScrollPane(new NoiDungForm(noiDung));
        cuon.setBorder(null);
        cuon.getVerticalScrollBar().setUnitIncrement(20);
        hienThiNoiDung(cuon, menuCuaForm(form));
    }

    private void hienThiTrangChu() {
        dongFormDangMo();
        hienThiNoiDung(pnlThongTin, btnTrangChu);
    }

    private void hienThiNoiDung(JComponent noiDung, JButton nutMenu) {
        pnlManHinh.removeAll();
        pnlManHinh.setLayout(new BorderLayout());
        pnlManHinh.add(noiDung, BorderLayout.CENTER);
        chonMenu(nutMenu);
        pnlManHinh.revalidate();
        pnlManHinh.repaint();
    }

    private void dongFormDangMo() {
        if (formDangMo != null) {
            formDangMo.getRootPane().putClientProperty(MENU_GIAO_VIEN, null);
            formDangMo.dispose();
            formDangMo = null;
        }
    }

    @Override
    public void dispose() {
        dongFormDangMo();
        super.dispose();
    }

    private JButton menuCuaForm(JFrame form) {
        if (form instanceof quanly.NhapMonHoc) return btnMonHoc;
        if (form instanceof quanly.NhapLopSinhVien) return btnLopSinhVien;
        if (form instanceof quanly.NhapGiaoVien) return btnGiaoVien;
        if (form instanceof quanly.NhapBoDe) return btnBoDe;
        if (form instanceof quanly.ChuanBiThi) return btnChuanBiThi;
        if (form instanceof baocao.BangDiemMonHoc || form instanceof baocao.InBangDiem) return btnBangDiem;
        if (form instanceof taikhoan.TaoTaiKhoan) return btnTaoTaiKhoan;
        if (form instanceof taikhoan.DoiMatKhau) return btnDoiMatKhau;
        if (form instanceof hethong.SaoLuuPhucHoi) return btnSaoLuuPhucHoi;
        return btnTrangChu;
    }

    private void chonMenu(JButton duocChon) {
        javax.swing.JButton[] menu = {btnTrangChu, btnMonHoc, btnLopSinhVien, btnGiaoVien,
            btnBoDe, btnChuanBiThi, btnBangDiem, btnTaoTaiKhoan, btnDoiMatKhau,
            btnSaoLuuPhucHoi, btnDangXuat, btnThoat};
        for (javax.swing.JButton nut : menu) {
            boolean chon = nut == duocChon;
            nut.setBackground(chon ? new java.awt.Color(205, 32, 45) : new java.awt.Color(245, 246, 248));
            nut.setForeground(chon ? java.awt.Color.WHITE : new java.awt.Color(36, 43, 54));
            nut.setFont(new java.awt.Font("Segoe UI", chon ? 1 : 0, 14));
            nut.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
            nut.setBorder(javax.swing.BorderFactory.createEmptyBorder(6, 14, 6, 14));
            nut.setOpaque(true);
            nut.setContentAreaFilled(true);
            nut.setFocusPainted(false);
            nut.putClientProperty("selected", chon);
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlNoiDung = new javax.swing.JPanel();
        pnlMenu = new javax.swing.JPanel();
        lblMenuBrand = new javax.swing.JLabel();
        btnTrangChu = new javax.swing.JButton();
        lblQuanLy = new javax.swing.JLabel();
        btnMonHoc = new javax.swing.JButton();
        btnLopSinhVien = new javax.swing.JButton();
        btnGiaoVien = new javax.swing.JButton();
        btnBoDe = new javax.swing.JButton();
        btnChuanBiThi = new javax.swing.JButton();
        lblThi = new javax.swing.JLabel();
        btnBangDiem = new javax.swing.JButton();
        lblTaiKhoan = new javax.swing.JLabel();
        btnTaoTaiKhoan = new javax.swing.JButton();
        btnDoiMatKhau = new javax.swing.JButton();
        btnSaoLuuPhucHoi = new javax.swing.JButton();
        btnDangXuat = new javax.swing.JButton();
        btnThoat = new javax.swing.JButton();
        pnlManHinh = new javax.swing.JPanel();
        pnlThongTin = new javax.swing.JPanel();
        lblTieuDe = new javax.swing.JLabel();
        lblMoTa = new javax.swing.JLabel();
        lblVaiTro = new javax.swing.JLabel();
        pnlTxtMaNguoiDung = new javax.swing.JPanel();
        lblTxtMaNguoiDung = new javax.swing.JLabel();
        txtMaNguoiDung = new javax.swing.JTextField();
        pnlTxtHoTen = new javax.swing.JPanel();
        lblTxtHoTen = new javax.swing.JLabel();
        txtHoTen = new javax.swing.JTextField();
        pnlTxtSoDTLL = new javax.swing.JPanel();
        lblTxtSoDTLL = new javax.swing.JLabel();
        txtSoDTLL = new javax.swing.JTextField();
        pnlTxtDiaChi = new javax.swing.JPanel();
        lblTxtDiaChi = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PTIT | TRANG CHỦ");
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/ptit-logo.png")).getImage());

        pnlNoiDung.setBackground(new java.awt.Color(255, 255, 255));
        pnlNoiDung.setBorder(javax.swing.BorderFactory.createEmptyBorder(24, 24, 24, 24));

        pnlMenu.setBackground(new java.awt.Color(245, 246, 248));
        pnlMenu.setBorder(javax.swing.BorderFactory.createEmptyBorder(16, 16, 16, 16));

        lblMenuBrand.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        lblMenuBrand.setText("PTIT  ·  THI TRẮC NGHIỆM");

        btnTrangChu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnTrangChu.setText("Trang chủ");
        btnTrangChu.addActionListener(this::btnTrangChuActionPerformed);

        lblQuanLy.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblQuanLy.setForeground(new java.awt.Color(205, 32, 45));
        lblQuanLy.setText("QUẢN LÝ");

        btnMonHoc.setBackground(new java.awt.Color(245, 246, 248));
        btnMonHoc.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnMonHoc.setForeground(new java.awt.Color(36, 43, 54));
        btnMonHoc.setText("Môn học");
        btnMonHoc.setFocusPainted(false);
        btnMonHoc.setOpaque(true);
        btnMonHoc.addActionListener(this::btnMonHocActionPerformed);

        btnLopSinhVien.setBackground(new java.awt.Color(245, 246, 248));
        btnLopSinhVien.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnLopSinhVien.setForeground(new java.awt.Color(36, 43, 54));
        btnLopSinhVien.setText("Lớp và sinh viên");
        btnLopSinhVien.setFocusPainted(false);
        btnLopSinhVien.setOpaque(true);
        btnLopSinhVien.addActionListener(this::btnLopSinhVienActionPerformed);

        btnGiaoVien.setBackground(new java.awt.Color(245, 246, 248));
        btnGiaoVien.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnGiaoVien.setForeground(new java.awt.Color(36, 43, 54));
        btnGiaoVien.setText("Giáo viên");
        btnGiaoVien.setFocusPainted(false);
        btnGiaoVien.setOpaque(true);
        btnGiaoVien.addActionListener(this::btnGiaoVienActionPerformed);

        btnBoDe.setBackground(new java.awt.Color(245, 246, 248));
        btnBoDe.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnBoDe.setForeground(new java.awt.Color(36, 43, 54));
        btnBoDe.setText("Bộ đề trắc nghiệm");
        btnBoDe.setFocusPainted(false);
        btnBoDe.setOpaque(true);
        btnBoDe.addActionListener(this::btnBoDeActionPerformed);

        btnChuanBiThi.setBackground(new java.awt.Color(245, 246, 248));
        btnChuanBiThi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnChuanBiThi.setForeground(new java.awt.Color(36, 43, 54));
        btnChuanBiThi.setText("Chuẩn bị thi");
        btnChuanBiThi.setFocusPainted(false);
        btnChuanBiThi.setOpaque(true);
        btnChuanBiThi.addActionListener(this::btnChuanBiThiActionPerformed);

        lblThi.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblThi.setForeground(new java.awt.Color(205, 32, 45));
        lblThi.setText("BÁO CÁO");

        btnBangDiem.setBackground(new java.awt.Color(245, 246, 248));
        btnBangDiem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnBangDiem.setForeground(new java.awt.Color(36, 43, 54));
        btnBangDiem.setText("Bảng điểm môn học");
        btnBangDiem.setFocusPainted(false);
        btnBangDiem.setOpaque(true);
        btnBangDiem.addActionListener(this::btnBangDiemActionPerformed);

        lblTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblTaiKhoan.setForeground(new java.awt.Color(205, 32, 45));
        lblTaiKhoan.setText("TÀI KHOẢN VÀ HỆ THỐNG");

        btnTaoTaiKhoan.setBackground(new java.awt.Color(245, 246, 248));
        btnTaoTaiKhoan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnTaoTaiKhoan.setForeground(new java.awt.Color(36, 43, 54));
        btnTaoTaiKhoan.setText("Tạo tài khoản giáo viên");
        btnTaoTaiKhoan.setFocusPainted(false);
        btnTaoTaiKhoan.setOpaque(true);
        btnTaoTaiKhoan.addActionListener(this::btnTaoTaiKhoanActionPerformed);

        btnDoiMatKhau.setBackground(new java.awt.Color(245, 246, 248));
        btnDoiMatKhau.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnDoiMatKhau.setForeground(new java.awt.Color(36, 43, 54));
        btnDoiMatKhau.setText("Đổi mật khẩu");
        btnDoiMatKhau.setFocusPainted(false);
        btnDoiMatKhau.setOpaque(true);
        btnDoiMatKhau.addActionListener(this::btnDoiMatKhauActionPerformed);

        btnSaoLuuPhucHoi.setBackground(new java.awt.Color(245, 246, 248));
        btnSaoLuuPhucHoi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSaoLuuPhucHoi.setForeground(new java.awt.Color(36, 43, 54));
        btnSaoLuuPhucHoi.setText("Sao lưu / Phục hồi");
        btnSaoLuuPhucHoi.setFocusPainted(false);
        btnSaoLuuPhucHoi.setOpaque(true);
        btnSaoLuuPhucHoi.addActionListener(this::btnSaoLuuPhucHoiActionPerformed);

        btnDangXuat.setBackground(new java.awt.Color(245, 246, 248));
        btnDangXuat.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnDangXuat.setForeground(new java.awt.Color(36, 43, 54));
        btnDangXuat.setText("Đăng xuất");
        btnDangXuat.setFocusPainted(false);
        btnDangXuat.setOpaque(true);
        btnDangXuat.addActionListener(this::btnDangXuatActionPerformed);

        btnThoat.setBackground(new java.awt.Color(245, 246, 248));
        btnThoat.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnThoat.setForeground(new java.awt.Color(36, 43, 54));
        btnThoat.setText("Thoát");
        btnThoat.setFocusPainted(false);
        btnThoat.setOpaque(true);
        btnThoat.addActionListener(this::btnThoatActionPerformed);

        javax.swing.GroupLayout pnlMenuLayout = new javax.swing.GroupLayout(pnlMenu);
        pnlMenu.setLayout(pnlMenuLayout);
        pnlMenuLayout.setHorizontalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblMenuBrand, javax.swing.GroupLayout.PREFERRED_SIZE, 230, Short.MAX_VALUE)
            .addComponent(btnTrangChu, javax.swing.GroupLayout.PREFERRED_SIZE, 230, Short.MAX_VALUE)
            .addComponent(lblQuanLy, javax.swing.GroupLayout.PREFERRED_SIZE, 230, Short.MAX_VALUE)
            .addComponent(btnMonHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 230, Short.MAX_VALUE)
            .addComponent(btnLopSinhVien, javax.swing.GroupLayout.PREFERRED_SIZE, 230, Short.MAX_VALUE)
            .addComponent(btnGiaoVien, javax.swing.GroupLayout.PREFERRED_SIZE, 230, Short.MAX_VALUE)
            .addComponent(btnBoDe, javax.swing.GroupLayout.PREFERRED_SIZE, 230, Short.MAX_VALUE)
            .addComponent(btnChuanBiThi, javax.swing.GroupLayout.PREFERRED_SIZE, 230, Short.MAX_VALUE)
            .addComponent(lblThi, javax.swing.GroupLayout.PREFERRED_SIZE, 230, Short.MAX_VALUE)
            .addComponent(btnBangDiem, javax.swing.GroupLayout.PREFERRED_SIZE, 230, Short.MAX_VALUE)
            .addComponent(lblTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 230, Short.MAX_VALUE)
            .addComponent(btnTaoTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 230, Short.MAX_VALUE)
            .addComponent(btnDoiMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 230, Short.MAX_VALUE)
            .addComponent(btnSaoLuuPhucHoi, javax.swing.GroupLayout.PREFERRED_SIZE, 230, Short.MAX_VALUE)
            .addComponent(btnDangXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 230, Short.MAX_VALUE)
            .addComponent(btnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, 230, Short.MAX_VALUE)
        );
        pnlMenuLayout.setVerticalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMenuLayout.createSequentialGroup()
                .addComponent(lblMenuBrand, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(btnTrangChu, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(lblQuanLy, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(btnMonHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(btnLopSinhVien, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(btnGiaoVien, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(btnBoDe, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(btnChuanBiThi, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(lblThi, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(btnBangDiem, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(lblTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(btnTaoTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(btnDoiMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(btnSaoLuuPhucHoi, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(btnDangXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(btnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4))
        );

        pnlManHinh.setBackground(new java.awt.Color(255, 255, 255));
        pnlManHinh.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));

        pnlThongTin.setBackground(new java.awt.Color(255, 255, 255));
        pnlThongTin.setBorder(javax.swing.BorderFactory.createEmptyBorder(28, 28, 28, 28));

        lblTieuDe.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTieuDe.setForeground(new java.awt.Color(205, 32, 45));
        lblTieuDe.setText("TRANG CHỦ");

        lblMoTa.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblMoTa.setForeground(new java.awt.Color(107, 114, 128));
        lblMoTa.setText("Hệ thống thi trắc nghiệm · Học viện Công nghệ Bưu chính Viễn thông");

        lblVaiTro.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblVaiTro.setText("THÔNG TIN GIẢNG VIÊN");

        pnlTxtMaNguoiDung.setBackground(new java.awt.Color(255, 255, 255));

        lblTxtMaNguoiDung.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblTxtMaNguoiDung.setForeground(new java.awt.Color(36, 43, 54));
        lblTxtMaNguoiDung.setText("Mã Giảng Viên");

        txtMaNguoiDung.setEditable(false);
        txtMaNguoiDung.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMaNguoiDung.setForeground(new java.awt.Color(36, 43, 54));

        javax.swing.GroupLayout pnlTxtMaNguoiDungLayout = new javax.swing.GroupLayout(pnlTxtMaNguoiDung);
        pnlTxtMaNguoiDung.setLayout(pnlTxtMaNguoiDungLayout);
        pnlTxtMaNguoiDungLayout.setHorizontalGroup(
            pnlTxtMaNguoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTxtMaNguoiDung, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(txtMaNguoiDung)
        );
        pnlTxtMaNguoiDungLayout.setVerticalGroup(
            pnlTxtMaNguoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtMaNguoiDungLayout.createSequentialGroup()
                .addComponent(lblTxtMaNguoiDung)
                .addGap(8, 8, 8)
                .addComponent(txtMaNguoiDung, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        pnlTxtSoDTLL.setBackground(new java.awt.Color(255, 255, 255));

        lblTxtSoDTLL.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblTxtSoDTLL.setForeground(new java.awt.Color(36, 43, 54));
        lblTxtSoDTLL.setText("Số Điện Thoại Liên Lạc");

        txtSoDTLL.setEditable(false);
        txtSoDTLL.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSoDTLL.setForeground(new java.awt.Color(36, 43, 54));

        javax.swing.GroupLayout pnlTxtSoDTLLLayout = new javax.swing.GroupLayout(pnlTxtSoDTLL);
        pnlTxtSoDTLL.setLayout(pnlTxtSoDTLLLayout);
        pnlTxtSoDTLLLayout.setHorizontalGroup(
            pnlTxtSoDTLLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTxtSoDTLL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(txtSoDTLL)
        );
        pnlTxtSoDTLLLayout.setVerticalGroup(
            pnlTxtSoDTLLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtSoDTLLLayout.createSequentialGroup()
                .addComponent(lblTxtSoDTLL)
                .addGap(8, 8, 8)
                .addComponent(txtSoDTLL, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlTxtDiaChi.setBackground(new java.awt.Color(255, 255, 255));

        lblTxtDiaChi.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblTxtDiaChi.setForeground(new java.awt.Color(36, 43, 54));
        lblTxtDiaChi.setText("Địa Chỉ");

        txtDiaChi.setEditable(false);
        txtDiaChi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtDiaChi.setForeground(new java.awt.Color(36, 43, 54));

        javax.swing.GroupLayout pnlTxtDiaChiLayout = new javax.swing.GroupLayout(pnlTxtDiaChi);
        pnlTxtDiaChi.setLayout(pnlTxtDiaChiLayout);
        pnlTxtDiaChiLayout.setHorizontalGroup(
            pnlTxtDiaChiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTxtDiaChi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(txtDiaChi)
        );
        pnlTxtDiaChiLayout.setVerticalGroup(
            pnlTxtDiaChiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTxtDiaChiLayout.createSequentialGroup()
                .addComponent(lblTxtDiaChi)
                .addGap(8, 8, 8)
                .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout pnlThongTinLayout = new javax.swing.GroupLayout(pnlThongTin);
        pnlThongTin.setLayout(pnlThongTinLayout);
        pnlThongTinLayout.setHorizontalGroup(
            pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTieuDe, javax.swing.GroupLayout.PREFERRED_SIZE, 620, Short.MAX_VALUE)
            .addComponent(lblMoTa, javax.swing.GroupLayout.PREFERRED_SIZE, 620, Short.MAX_VALUE)
            .addComponent(lblVaiTro, javax.swing.GroupLayout.PREFERRED_SIZE, 620, Short.MAX_VALUE)
            .addComponent(pnlTxtMaNguoiDung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlTxtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlTxtSoDTLL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlTxtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlThongTinLayout.setVerticalGroup(
            pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThongTinLayout.createSequentialGroup()
                .addComponent(lblTieuDe)
                .addGap(12, 12, 12)
                .addComponent(lblMoTa, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(lblVaiTro, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(pnlTxtMaNguoiDung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(pnlTxtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGap(20, 20, 20)
                .addComponent(pnlTxtSoDTLL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(pnlTxtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(180, 180, 180))
        );

        javax.swing.GroupLayout pnlManHinhLayout = new javax.swing.GroupLayout(pnlManHinh);
        pnlManHinh.setLayout(pnlManHinhLayout);
        pnlManHinhLayout.setHorizontalGroup(
            pnlManHinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlThongTin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlManHinhLayout.setVerticalGroup(
            pnlManHinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlThongTin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlNoiDungLayout = new javax.swing.GroupLayout(pnlNoiDung);
        pnlNoiDung.setLayout(pnlNoiDungLayout);
        pnlNoiDungLayout.setHorizontalGroup(
            pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNoiDungLayout.createSequentialGroup()
                .addComponent(pnlMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(pnlManHinh, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlNoiDungLayout.setVerticalGroup(
            pnlNoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMenu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlManHinh, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addComponent(pnlNoiDung, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(16, 16, 16))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMonHocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMonHocActionPerformed
        hienThiForm(new quanly.NhapMonHoc(nguoiDung));
    }//GEN-LAST:event_btnMonHocActionPerformed

    private void btnLopSinhVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLopSinhVienActionPerformed
        hienThiForm(new quanly.NhapLopSinhVien(nguoiDung));
    }//GEN-LAST:event_btnLopSinhVienActionPerformed

    private void btnGiaoVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGiaoVienActionPerformed
        hienThiForm(new quanly.NhapGiaoVien(nguoiDung));
    }//GEN-LAST:event_btnGiaoVienActionPerformed

    private void btnBoDeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBoDeActionPerformed
        hienThiForm(new quanly.NhapBoDe(nguoiDung));
    }//GEN-LAST:event_btnBoDeActionPerformed

    private void btnChuanBiThiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChuanBiThiActionPerformed
        hienThiForm(new quanly.ChuanBiThi(nguoiDung));
    }//GEN-LAST:event_btnChuanBiThiActionPerformed

    private void btnBangDiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBangDiemActionPerformed
        hienThiForm(new baocao.BangDiemMonHoc(nguoiDung));
    }//GEN-LAST:event_btnBangDiemActionPerformed

    private void btnTaoTaiKhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoTaiKhoanActionPerformed
        hienThiForm(new taikhoan.TaoTaiKhoan(nguoiDung));
    }//GEN-LAST:event_btnTaoTaiKhoanActionPerformed

    private void btnDoiMatKhauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoiMatKhauActionPerformed
        hienThiForm(new taikhoan.DoiMatKhau(nguoiDung));
    }//GEN-LAST:event_btnDoiMatKhauActionPerformed

    private void btnSaoLuuPhucHoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaoLuuPhucHoiActionPerformed
        hienThiForm(new hethong.SaoLuuPhucHoi(nguoiDung));
    }//GEN-LAST:event_btnSaoLuuPhucHoiActionPerformed

    private void btnDangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangXuatActionPerformed
        dangXuatTu(this);
    }//GEN-LAST:event_btnDangXuatActionPerformed

    private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnThoatActionPerformed

    private void btnTrangChuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrangChuActionPerformed
        hienThiTrangChu();
    }//GEN-LAST:event_btnTrangChuActionPerformed

    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TrangChuGV.class.getName()).log(java.util.logging.Level.WARNING, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> new TrangChuGV().setVisible(true));
    }

    // Co giãn nội dung theo khung bên phải; form quá rộng/cao vẫn có thể cuộn.
    private static final class NoiDungForm extends JPanel implements Scrollable {
        private NoiDungForm(Container noiDung) {
            super(new BorderLayout());
            add(noiDung, BorderLayout.CENTER);
        }

        @Override
        public Dimension getPreferredScrollableViewportSize() {
            return getPreferredSize();
        }

        @Override
        public int getScrollableUnitIncrement(Rectangle rect, int direction, int sign) {
            return 20;
        }

        @Override
        public int getScrollableBlockIncrement(Rectangle rect, int direction, int sign) {
            return direction == SwingConstants.VERTICAL ? rect.height : rect.width;
        }

        @Override
        public boolean getScrollableTracksViewportWidth() {
            return getParent() != null && getParent().getWidth() >= getMinimumSize().width;
        }

        @Override
        public boolean getScrollableTracksViewportHeight() {
            return getParent() != null && getParent().getHeight() >= getPreferredSize().height;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBangDiem;
    private javax.swing.JButton btnBoDe;
    private javax.swing.JButton btnChuanBiThi;
    private javax.swing.JButton btnDangXuat;
    private javax.swing.JButton btnDoiMatKhau;
    private javax.swing.JButton btnGiaoVien;
    private javax.swing.JButton btnLopSinhVien;
    private javax.swing.JButton btnMonHoc;
    private javax.swing.JButton btnSaoLuuPhucHoi;
    private javax.swing.JButton btnTaoTaiKhoan;
    private javax.swing.JButton btnThoat;
    private javax.swing.JButton btnTrangChu;
    private javax.swing.JLabel lblMenuBrand;
    private javax.swing.JLabel lblMoTa;
    private javax.swing.JLabel lblQuanLy;
    private javax.swing.JLabel lblTaiKhoan;
    private javax.swing.JLabel lblThi;
    private javax.swing.JLabel lblTieuDe;
    private javax.swing.JLabel lblTxtDiaChi;
    private javax.swing.JLabel lblTxtHoTen;
    private javax.swing.JLabel lblTxtMaNguoiDung;
    private javax.swing.JLabel lblTxtSoDTLL;
    private javax.swing.JLabel lblVaiTro;
    private javax.swing.JPanel pnlManHinh;
    private javax.swing.JPanel pnlMenu;
    private javax.swing.JPanel pnlNoiDung;
    private javax.swing.JPanel pnlThongTin;
    private javax.swing.JPanel pnlTxtDiaChi;
    private javax.swing.JPanel pnlTxtHoTen;
    private javax.swing.JPanel pnlTxtMaNguoiDung;
    private javax.swing.JPanel pnlTxtSoDTLL;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtMaNguoiDung;
    private javax.swing.JTextField txtSoDTLL;
    // End of variables declaration//GEN-END:variables
}
