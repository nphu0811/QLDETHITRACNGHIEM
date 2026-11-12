package connectionSQLServer;

import com.microsoft.sqlserver.jdbc.SQLServerDataTable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import models.MonHoc;
import session.sessionGiaoVien;

public final class QuanLyDAO {
    private QuanLyDAO() {
    }

    public static sessionGiaoVien dangNhap(String username, String password) throws SQLException {
        sessionGiaoVien giaoVien = new sessionGiaoVien(username, () -> connection.getConnectionGV(username, password));
        try (Connection ketNoi = giaoVien.moKetNoi();
                CallableStatement lenh = ketNoi.prepareCall("{call dbo.SP_LayThongTinGiaoVien()}")) {
            lenh.setQueryTimeout(15);
            try (ResultSet dong = lenh.executeQuery()) {
                if (!dong.next()) return null;
                giaoVien.setMaGV(dong.getString("MAGV").trim());
                giaoVien.setHo(dong.getString("HO"));
                giaoVien.setTen(dong.getString("TEN"));
                giaoVien.setSoDTLL(dong.getString("SODTLL"));
                giaoVien.setDiaChi(dong.getString("DIACHI"));
                return giaoVien;
            }
        }
    }

    public static ArrayList<MonHoc> layMonHoc(sessionGiaoVien giaoVien) throws SQLException {
        ArrayList<MonHoc> danhSach = new ArrayList<>();
        for (Object[] dong : layDanhMucMonHoc(giaoVien)) {
            danhSach.add(new MonHoc((String) dong[0], (String) dong[1]));
        }
        return danhSach;
    }

    public static ArrayList<Object[]> layDanhMucMonHoc(sessionGiaoVien giaoVien)
            throws SQLException {
        return doc(giaoVien, "{call dbo.SP_LayMonHoc}");
    }

    public static ArrayList<Object[]> layLop(sessionGiaoVien giaoVien)
            throws SQLException {
        return doc(giaoVien, "{call dbo.SP_LayLopQuanLy}");
    }

    public static ArrayList<Object[]> laySinhVien(sessionGiaoVien giaoVien, String maLop)
            throws SQLException {
        return doc(giaoVien, "{call dbo.SP_LaySinhVienQuanLy(?)}", maLop);
    }

    public static ArrayList<Object[]> layGiaoVien(sessionGiaoVien giaoVien)
            throws SQLException {
        return doc(giaoVien, "{call dbo.SP_LayGiaoVienQuanLy}");
    }

    public static ArrayList<Object[]> layBoDe(sessionGiaoVien giaoVien)
            throws SQLException {
        return doc(giaoVien, "{call dbo.SP_LayBoDeQuanLy}");
    }

    public static Object[] demCauHoiThi(sessionGiaoVien giaoVien, String maMH, String trinhDo)
            throws SQLException {
        return doc(giaoVien, "{call dbo.SP_DemCauHoiThi(?, ?)}", maMH, trinhDo).get(0);
    }

    public static ArrayList<Object[]> layCaThi(sessionGiaoVien giaoVien)
            throws SQLException {
        return doc(giaoVien, "{call dbo.SP_LayCaThiQuanLy}");
    }

    public static ArrayList<Object[]> layBangDiemMonHoc(sessionGiaoVien giaoVien, String maLop, String maMH, int lan)
            throws SQLException {
        return doc(giaoVien, "{call dbo.SP_BangDiemMonHocQuanLy(?, ?, ?)}", maLop, maMH, lan);
    }

    public static ArrayList<Object[]> layTatCaGiaoVienTaiKhoan(sessionGiaoVien giaoVien)
            throws SQLException {
        return doc(giaoVien, "{call dbo.SP_LayTatCaGiaoVienTaiKhoan}");
    }

    public static String layTenDangNhap(sessionGiaoVien giaoVien, byte[] sidTaiKhoan) throws SQLException {
        try (Connection ketNoi = giaoVien.moKetNoi();
                PreparedStatement lenh = ketNoi.prepareStatement("SELECT SUSER_SNAME(?)")) {
            lenh.setBytes(1, sidTaiKhoan);
            lenh.setQueryTimeout(15);
            try (ResultSet ketQua = lenh.executeQuery()) {
                return ketQua.next() ? ketQua.getString(1) : null;
            }
        }
    }

    public static void xoaMonHoc(sessionGiaoVien giaoVien, String maMH, String tenCu)
            throws SQLException {
        ghi(giaoVien, "{call dbo.SP_XoaMonHoc(?, ?)}", maMH, tenCu);
    }

    public static void xoaLop(sessionGiaoVien giaoVien, String maLop)
            throws SQLException {
        ghi(giaoVien, "{call dbo.SP_GhiLop(?, ?)}", "D", maLop);
    }

    public static void xoaSinhVien(sessionGiaoVien giaoVien, String maSV, String maLop)
            throws SQLException {
        ghi(giaoVien, "{call dbo.SP_GhiSinhVien(?, ?, ?)}", "D", maSV, maLop);
    }

    public static void xoaGiaoVien(sessionGiaoVien giaoVien, String maGV)
            throws SQLException {
        ghi(giaoVien, "{call dbo.SP_GhiGiaoVien(?, ?)}", "D", maGV);
    }

    public static void xoaCauHoi(sessionGiaoVien giaoVien, int soCau)
            throws SQLException {
        ghi(giaoVien, "{call dbo.SP_GhiBoDe(?, ?)}", "D", soCau);
    }

    public static void xoaCaThi(sessionGiaoVien giaoVien, String maLop, String maMH, int lan)
            throws SQLException {
        ghi(giaoVien, "{call dbo.SP_GhiCaThiQuanLy(?, ?, ?, ?)}", "D", maLop, maMH, lan);
    }

    public static void ghiMonHoc(sessionGiaoVien giaoVien, String thaoTac,
            List<Object[]> truoc, List<Object[]> sau) throws SQLException {
        SQLServerDataTable bang = bangChoGhi("DongMonHocChoGhi", "MAMH", "TENMH", "TENCU");
        boolean them = "I".equals(thaoTac);
        for (int i = 0; i < sau.size(); i++) {
            Object[] mon = sau.get(i);
            bang.addRow(mon[0], mon[1], them ? null : truoc.get(i)[1]);
        }
        if (them) ghi(giaoVien, "{call dbo.SP_ThemMonHoc(?, ?, ?)}", null, null, bang);
        else ghi(giaoVien, "{call dbo.SP_HieuChinhMonHoc(?, ?, ?, ?)}", null, null, null, bang);
    }

    public static void ghiLop(sessionGiaoVien giaoVien, String thaoTac, List<Object[]> cacDong)
            throws SQLException {
        SQLServerDataTable bang = bangChoGhi("DongLopChoGhi", "MALOP", "TENLOP");
        for (Object[] dong : cacDong) bang.addRow(dong);
        ghi(giaoVien, "{call dbo.SP_GhiLop(?, ?, ?, ?)}", thaoTac, null, null, bang);
    }

    public static void ghiSinhVien(sessionGiaoVien giaoVien, String thaoTac, List<Object[]> cacDong)
            throws SQLException {
        SQLServerDataTable bang = bangChoGhi("DongSinhVienChoGhi", "MASV", "HO", "TEN");
        bang.addColumnMetadata("NGAYSINH", Types.DATE);
        bang.addColumnMetadata("DIACHI", Types.NVARCHAR);
        bang.addColumnMetadata("MALOP", Types.NVARCHAR);
        for (Object[] dong : cacDong) bang.addRow(dong);
        ghi(giaoVien, "{call dbo.SP_GhiSinhVien(?, ?, ?, ?, ?, ?, ?, ?)}",
                thaoTac, null, null, null, null, null, null, bang);
    }

    public static void ghiGiaoVien(sessionGiaoVien giaoVien, String thaoTac, List<Object[]> cacDong)
            throws SQLException {
        SQLServerDataTable bang = bangChoGhi("DongGiaoVienChoGhi", "MAGV", "HO", "TEN", "SODTLL", "DIACHI");
        for (Object[] dong : cacDong) bang.addRow(dong);
        ghi(giaoVien, "{call dbo.SP_GhiGiaoVien(?, ?, ?, ?, ?, ?, ?)}",
                thaoTac, null, null, null, null, null, bang);
    }

    public static void ghiBoDe(sessionGiaoVien giaoVien, String thaoTac, List<Object[]> cacDong)
            throws SQLException {
        SQLServerDataTable bang = bangChoGhi("DongCauHoiChoGhi");
        bang.addColumnMetadata("STT", Types.INTEGER);
        bang.addColumnMetadata("CAUHOI", Types.INTEGER);
        for (String cot : List.of("MAMH", "TRINHDO", "NOIDUNG", "A", "B", "C", "D", "DAP_AN", "MAGV")) {
            bang.addColumnMetadata(cot, Types.NVARCHAR);
        }
        for (int i = 0; i < cacDong.size(); i++) {
            Object[] dong = cacDong.get(i);
            bang.addRow(i + 1, dong[0], dong[1], dong[2], dong[3], dong[4], dong[5], dong[6], dong[7], dong[8], null);
        }
        ghi(giaoVien, "{call dbo.SP_GhiBoDe(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}",
                thaoTac, null, null, null, null, null, null, null, null, null, bang);
    }

    public static void ghiCaThi(sessionGiaoVien giaoVien, String thaoTac, List<Object[]> cacDong)
            throws SQLException {
        SQLServerDataTable bang = bangChoGhi("DongCaThiChoGhi", "MALOP", "MAMH");
        bang.addColumnMetadata("LAN", Types.SMALLINT);
        bang.addColumnMetadata("TRINHDO", Types.NVARCHAR);
        bang.addColumnMetadata("NGAYTHI", Types.DATE);
        bang.addColumnMetadata("SOCAUTHI", Types.SMALLINT);
        bang.addColumnMetadata("THOIGIAN", Types.SMALLINT);
        bang.addColumnMetadata("MAGV", Types.NVARCHAR);
        for (Object[] dong : cacDong) {
            bang.addRow(dong[1], dong[2], dong[5], dong[3], dong[4], dong[6], dong[7], null);
        }
        ghi(giaoVien, "{call dbo.SP_GhiCaThiQuanLy(?, ?, ?, ?, ?, ?, ?, ?, ?)}",
                thaoTac, null, null, null, null, null, null, null, bang);
    }

    public static void phucHoiMonHoc(sessionGiaoVien giaoVien) throws SQLException {
        ghi(giaoVien, "{call dbo.SP_PhucHoiMonHoc()}");
    }

    public static void phucHoiLop(sessionGiaoVien giaoVien) throws SQLException {
        ghi(giaoVien, "{call dbo.SP_GhiLop(?)}", "R");
    }

    public static void phucHoiSinhVien(sessionGiaoVien giaoVien, String maLop) throws SQLException {
        ghi(giaoVien, "{call dbo.SP_GhiSinhVien(?, ?, ?)}", "R", null, maLop);
    }

    public static void phucHoiGiaoVien(sessionGiaoVien giaoVien) throws SQLException {
        ghi(giaoVien, "{call dbo.SP_GhiGiaoVien(?)}", "R");
    }

    public static void phucHoiBoDe(sessionGiaoVien giaoVien) throws SQLException {
        ghi(giaoVien, "{call dbo.SP_GhiBoDe(?)}", "R");
    }

    public static void phucHoiCaThi(sessionGiaoVien giaoVien) throws SQLException {
        ghi(giaoVien, "{call dbo.SP_GhiCaThiQuanLy(?)}", "R");
    }

    public static void xoaLichSuPhucHoi(sessionGiaoVien giaoVien) {
        if (giaoVien == null || !giaoVien.coKetNoi()) return;
        try (Connection ketNoi = giaoVien.moKetNoi();
             PreparedStatement lenh = ketNoi.prepareStatement(
                     "DELETE FROM dbo.LichSuQuanLy WHERE TaiKhoan = SUSER_SID(ORIGINAL_LOGIN())")) {
            lenh.executeUpdate();
        } catch (SQLException ignored) {
        }
    }

    private static SQLServerDataTable bangChoGhi(String tenKieu, String... cotChuoi) throws SQLException {
        SQLServerDataTable bang = new SQLServerDataTable();
        bang.setTvpName("dbo." + tenKieu);
        for (String cot : cotChuoi) bang.addColumnMetadata(cot, Types.NVARCHAR);
        return bang;
    }

    public static void taoTaiKhoan(sessionGiaoVien giaoVien, String maGV, String login, String matKhau)
            throws SQLException {
        ghi(giaoVien, "{call dbo.SP_TaoTaiKhoanQuanLy(?, ?, ?)}", maGV, login, matKhau);
    }

    public static void xoaTaiKhoan(sessionGiaoVien giaoVien, String maGV, byte[] sidTaiKhoan)
            throws SQLException {
        ghi(giaoVien, "{call dbo.SP_XoaTaiKhoanGiaoVien(?, ?)}", maGV, sidTaiKhoan);
    }

    public static void doiMatKhau(sessionGiaoVien giaoVien, String matKhauMoi) throws SQLException {
        ghi(giaoVien, "{call dbo.SP_DoiMatKhauQuanLy(?)}", matKhauMoi);
    }

    private static ArrayList<Object[]> doc(sessionGiaoVien giaoVien, String sql, Object... thamSo)
            throws SQLException {
        ArrayList<Object[]> danhSach = new ArrayList<>();
        try (Connection ketNoi = giaoVien.moKetNoi();
                CallableStatement lenh = ketNoi.prepareCall(sql)) {
            ganThamSo(lenh, thamSo);
            try (ResultSet ketQua = lenh.executeQuery()) {
                int soCot = ketQua.getMetaData().getColumnCount();
                while (ketQua.next()) {
                    Object[] dong = new Object[soCot];
                    for (int cot = 0; cot < soCot; cot++) {
                        dong[cot] = ketQua.getObject(cot + 1);
                    }
                    danhSach.add(dong);
                }
            }
        }
        return danhSach;
    }

    private static void ghi(sessionGiaoVien giaoVien, String sql, Object... thamSo) throws SQLException {
        try (Connection ketNoi = giaoVien.moKetNoi();
                CallableStatement lenh = ketNoi.prepareCall(sql)) {
            ganThamSo(lenh, thamSo);
            lenh.execute();
        }
    }

    private static void ganThamSo(CallableStatement lenh, Object[] thamSo) throws SQLException {
        lenh.setQueryTimeout(15);
        for (int i = 0; i < thamSo.length; i++) {
            if (thamSo[i] instanceof String chuoi) {
                lenh.setNString(i + 1, chuoi);
            } else {
                lenh.setObject(i + 1, thamSo[i]);
            }
        }
    }
}
