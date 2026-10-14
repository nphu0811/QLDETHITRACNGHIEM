package connectionSQLServer;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import session.BaiThi;
import session.CaThi;
import session.CauHoi;
import session.DiemThi;

// Chỉ gọi stored procedure. Ngày được thi, hạn giờ và điểm do SQL quyết định.
public class ThiDAO {
    private static final DateTimeFormatter DINH_DANG_NGAY = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private static Connection ketNoi() throws SQLException {
        Connection ketNoi = connection.getConnectionSV();
        if (ketNoi == null) throw new SQLException("Không thể kết nối tới SQL Server.");
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
        caThi.setNgaythi(rs.getDate("NGAYTHI").toLocalDate().format(DINH_DANG_NGAY));
        caThi.setLan(rs.getInt("LAN"));
        caThi.setTrinhDo(rs.getString("TRINHDO").trim());
        caThi.setSoCauThi(rs.getInt("SOCAUTHI"));
        caThi.setThoiGianThi(rs.getInt("THOIGIAN"));
        return caThi;
    }

    private static BaiThi doc(String lenh, String maSV, String maMH, Integer lan) throws SQLException {
        try (Connection ketNoi = ketNoi(); CallableStatement cs = ketNoi.prepareCall(lenh)) {
            cs.setQueryTimeout(15);
            cs.setString(1, maSV);
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

    public static BaiThi mo(String maSV, CaThi caThi) throws SQLException {
        return doc("{call dbo.SP_MoBaiThi(?,?,?)}", maSV, caThi.getMaMH(), caThi.getLan());
    }

    public static BaiThi nop(String maSV, CaThi caThi) throws SQLException {
        return doc("{call dbo.SP_NopBaiThi(?,?,?)}", maSV, caThi.getMaMH(), caThi.getLan());
    }

    public static BaiThi xem(String maSV) throws SQLException {
        BaiThi baiThi = doc("{call dbo.SP_DocBaiThi(?,?,?)}", maSV, null, null);
        if (!baiThi.daNop) throw new SQLException("Bài thi chưa được nộp.");
        return baiThi;
    }

    public static BaiThi xem(String maSV, String maMH, int lan) throws SQLException {
        BaiThi baiThi = doc("{call dbo.SP_DocBaiThi(?,?,?)}", maSV, maMH, lan);
        if (!baiThi.daNop) throw new SQLException("Bài thi chưa được nộp.");
        return baiThi;
    }

    public static ArrayList<DiemThi> layBangDiem(String maSV) throws SQLException {
        ArrayList<DiemThi> danhSach = new ArrayList<>();
        try (Connection ketNoi = ketNoi();
                CallableStatement cs = ketNoi.prepareCall("{call dbo.SP_LayBangDiemSinhVien(?)}")) {
            cs.setQueryTimeout(15);
            cs.setString(1, maSV);
            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    danhSach.add(new DiemThi(rs.getString("MAMH").trim(), rs.getString("TENMH"),
                            rs.getDate("NGAYTHI").toLocalDate().format(DINH_DANG_NGAY),
                            rs.getInt("LAN"), rs.getDouble("DIEM")));
                }
            }
        }
        return danhSach;
    }

    public static void luu(String maSV, CaThi caThi, int viTri, String luaChon) throws SQLException {
        try (Connection ketNoi = ketNoi();
                CallableStatement cs = ketNoi.prepareCall("{call dbo.SP_LuuTienDoThi(?,?,?,?,?,?)}")) {
            cs.setQueryTimeout(15);
            cs.setString(1, maSV);
            cs.setString(2, caThi.getMaMH());
            cs.setInt(3, caThi.getLan());
            cs.setInt(4, viTri + 1);
            if (luaChon == null) cs.setNull(5, Types.NVARCHAR);
            else cs.setString(5, luaChon);
            cs.setInt(6, viTri + 1);
            cs.execute();
        }
    }

    public static CaThi dangDo(String maSV) throws SQLException {
        try (Connection ketNoi = ketNoi();
                CallableStatement cs = ketNoi.prepareCall("{call dbo.SP_LayBaiThiDangDo(?)}")) {
            cs.setQueryTimeout(15);
            cs.setString(1, maSV);
            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) return docCaThi(rs);
                return null;
            }
        }
    }
}
