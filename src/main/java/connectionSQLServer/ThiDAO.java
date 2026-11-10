package connectionSQLServer;

import Helper.DateHelper;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import models.BaiThi;
import models.CaThi;
import models.CauHoi;
import models.DiemThi;
import models.MonHoc;
import session.sessionSinhVien;

// Chỉ gọi stored procedure. Ngày được thi, hạn giờ và điểm do SQL quyết định.
public class ThiDAO {
    public static sessionSinhVien dangNhap(String maSV) throws SQLException {
        sessionSinhVien sinhVien = new sessionSinhVien();
        sinhVien.setMaSV(maSV);
        try (Connection ketNoi = sinhVien.moKetNoi();
                CallableStatement lenh = ketNoi.prepareCall("{call dbo.SP_LoginSinhVien(?)}")) {
            lenh.setQueryTimeout(15);
            lenh.setString(1, maSV);
            try (ResultSet dong = lenh.executeQuery()) {
                if (!dong.next()) return null;
                sinhVien.setHo(dong.getString("HO"));
                sinhVien.setTen(dong.getString("TEN"));
                String maLop = dong.getString("MALOP");
                sinhVien.setMaLop(maLop == null ? null : maLop.stripTrailing());
                sinhVien.setNgaySinh(dong.getString("NGAYSINH"));
                sinhVien.setDiaChi(dong.getString("DIACHI"));
                sinhVien.setTenLop(dong.getString("TENLOP"));
                return sinhVien;
            }
        }
    }

    public static ArrayList<MonHoc> layMonHoc(sessionSinhVien sinhVien) throws SQLException {
        ArrayList<MonHoc> danhSach = new ArrayList<>();
        try (Connection ketNoi = sinhVien.moKetNoi();
                CallableStatement cs = ketNoi.prepareCall("{call dbo.SP_LayMonHoc}")) {
            cs.setQueryTimeout(15);
            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    String maMH = rs.getString("MAMH").trim();
                    String tenMH = rs.getString("TENMH");
                    danhSach.add(new MonHoc(maMH, tenMH == null ? maMH : tenMH.trim()));
                }
            }
        }
        return danhSach;
    }

    public static ArrayList<Object[]> layCaThi(sessionSinhVien sinhVien, String maMH, Date ngayThi, Short lanThi)
            throws SQLException {
        ArrayList<Object[]> danhSach = new ArrayList<>();
        try (Connection ketNoi = sinhVien.moKetNoi();
                CallableStatement cs = ketNoi.prepareCall("{call dbo.SP_LayCaThiSinhVien(?,?,?,?)}")) {
            cs.setQueryTimeout(15);
            cs.setString(1, sinhVien.getMaSV());
            if (maMH == null) cs.setNull(2, Types.NCHAR);
            else cs.setString(2, maMH);
            if (ngayThi == null) cs.setNull(3, Types.DATE);
            else cs.setDate(3, ngayThi);
            if (lanThi == null) cs.setNull(4, Types.SMALLINT);
            else cs.setShort(4, lanThi);

            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    Date ngay = rs.getDate("NGAYTHI");
                    if (ngay == null) continue;
                    danhSach.add(new Object[]{
                        new MonHoc(rs.getString("MAMH").trim(), rs.getString("TENMH")),
                        DateHelper.dinhDang(ngay), rs.getString("LAN"), rs.getString("TRINHDO"),
                        rs.getString("SOCAUTHI"), rs.getString("THOIGIAN")
                    });
                }
            }
        }
        return danhSach;
    }

    private static Connection ketNoi(sessionSinhVien sinhVien) throws SQLException {
        Connection ketNoi = sinhVien.moKetNoi();
        // Có thể chọn database khác khi chạy bản kiểm thử, mặc định vẫn là database app.
        String database = System.getProperty("thitracnghiem.database");
        if (database != null) {
            try {
                ketNoi.setCatalog(database);
            } catch (SQLException e) {
                ketNoi.close();
                throw e;
            }
        }
        return ketNoi;
    }

    private static CaThi docCaThi(ResultSet rs) throws SQLException {
        CaThi caThi = new CaThi();
        caThi.setMaMH(rs.getString("MAMH").trim());
        caThi.setTenMH(rs.getString("TENMH"));
        caThi.setNgaythi(DateHelper.dinhDang(rs.getDate("NGAYTHI")));
        caThi.setLan(rs.getInt("LAN"));
        caThi.setTrinhDo(rs.getString("TRINHDO").trim());
        caThi.setSoCauThi(rs.getInt("SOCAUTHI"));
        caThi.setThoiGianThi(rs.getInt("THOIGIAN"));
        return caThi;
    }

    private static BaiThi doc(String lenh, sessionSinhVien sinhVien, String maMH, Integer lan) throws SQLException {
        try (Connection ketNoi = ketNoi(sinhVien); CallableStatement cs = ketNoi.prepareCall(lenh)) {
            cs.setQueryTimeout(15);
            cs.setString(1, sinhVien.getMaSV());
            if (maMH == null) cs.setNull(2, Types.NCHAR);
            else cs.setString(2, maMH);
            if (lan == null) cs.setNull(3, Types.SMALLINT);
            else cs.setInt(3, lan);

            BaiThi baiThi = new BaiThi();
            // Trừ cả thời gian chờ SQL/truyền dữ liệu/hiển thị khỏi đồng hồ.
            baiThi.mocDocThoiGian = System.nanoTime();
            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    if (baiThi.caThi == null) {
                        baiThi.caThi = docCaThi(rs);
                        baiThi.viTriCau = rs.getInt("CAUDANGXEM") - 1;
                        baiThi.conLaiMillis = Math.max(0, rs.getLong("CONLAI_MS"));
                        baiThi.daNop = rs.getBoolean("DANOP");
                        baiThi.soCauDung = rs.getInt("SOCAUDUNG");
                        baiThi.diem = rs.getDouble("DIEM");
                    }
                    CauHoi cau = new CauHoi();
                    cau.setMaCauHoi(String.valueOf(rs.getInt("CAUHOI")));
                    cau.setNoiDung(rs.getString("NOIDUNG"));
                    cau.setNoiDungA(rs.getString("A"));
                    cau.setNoiDungB(rs.getString("B"));
                    cau.setNoiDungC(rs.getString("C"));
                    cau.setNoiDungD(rs.getString("D"));
                    cau.setDapAn(rs.getString("DAP_AN"));
                    cau.setCauTraLoi(rs.getString("CAUTRALOI").trim());
                    baiThi.cauHoi.add(cau);
                }
            }
            if (baiThi.caThi == null) throw new SQLException("Chưa có bài thi để hiển thị.");
            return baiThi;
        }
    }

    public static BaiThi mo(sessionSinhVien sinhVien, CaThi caThi) throws SQLException {
        return doc("{call dbo.SP_MoBaiThi(?,?,?)}", sinhVien, caThi.getMaMH(), caThi.getLan());
    }

    public static BaiThi nop(sessionSinhVien sinhVien, CaThi caThi) throws SQLException {
        return doc("{call dbo.SP_NopBaiThi(?,?,?)}", sinhVien, caThi.getMaMH(), caThi.getLan());
    }

    public static BaiThi xem(sessionSinhVien sinhVien) throws SQLException {
        BaiThi baiThi = doc("{call dbo.SP_DocBaiThi(?,?,?)}", sinhVien, null, null);
        if (!baiThi.daNop) throw new SQLException("Bài thi chưa được nộp.");
        return baiThi;
    }

    public static BaiThi xem(sessionSinhVien sinhVien, String maMH, int lan) throws SQLException {
        BaiThi baiThi = doc("{call dbo.SP_DocBaiThi(?,?,?)}", sinhVien, maMH, lan);
        if (!baiThi.daNop) throw new SQLException("Bài thi chưa được nộp.");
        return baiThi;
    }

    public static ArrayList<DiemThi> layBangDiem(sessionSinhVien sinhVien) throws SQLException {
        ArrayList<DiemThi> danhSach = new ArrayList<>();
        try (Connection ketNoi = ketNoi(sinhVien);
                CallableStatement cs = ketNoi.prepareCall("{call dbo.SP_LayBangDiemSinhVien(?)}")) {
            cs.setQueryTimeout(15);
            cs.setString(1, sinhVien.getMaSV());
            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    danhSach.add(new DiemThi(rs.getString("MAMH").trim(), rs.getString("TENMH"),
                            DateHelper.dinhDang(rs.getDate("NGAYTHI")),
                            rs.getInt("LAN"), rs.getDouble("DIEM")));
                }
            }
        }
        return danhSach;
    }

    public static void luu(sessionSinhVien sinhVien, CaThi caThi, int viTri, String luaChon) throws SQLException {
        try (Connection ketNoi = ketNoi(sinhVien);
                CallableStatement cs = ketNoi.prepareCall("{call dbo.SP_LuuTienDoThi(?,?,?,?,?,?)}")) {
            cs.setQueryTimeout(15);
            cs.setString(1, sinhVien.getMaSV());
            cs.setString(2, caThi.getMaMH());
            cs.setInt(3, caThi.getLan());
            cs.setInt(4, viTri + 1);
            if (luaChon == null) cs.setNull(5, Types.NVARCHAR);
            else cs.setString(5, luaChon);
            cs.setInt(6, viTri + 1);
            cs.execute();
        }
    }

    public static CaThi dangDo(sessionSinhVien sinhVien) throws SQLException {
        try (Connection ketNoi = ketNoi(sinhVien);
                CallableStatement cs = ketNoi.prepareCall("{call dbo.SP_LayBaiThiDangDo(?)}")) {
            cs.setQueryTimeout(15);
            cs.setString(1, sinhVien.getMaSV());
            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) return docCaThi(rs);
                return null;
            }
        }
    }
}
