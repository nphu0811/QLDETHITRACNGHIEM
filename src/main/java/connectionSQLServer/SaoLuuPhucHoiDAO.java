package connectionSQLServer;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import session.sessionGiaoVien;

public final class SaoLuuPhucHoiDAO {
    private static final String TEN_CSDL = "THITRACNGHIEM";
    private static final String DUONG_DAN_SAO_LUU = "C:\\SQLBackup\\THITRACNGHIEM.bak";
    private static final DateTimeFormatter DINH_DANG_NGAY_GIO =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public record BanSaoLuu(UUID backupSetUuid, int position, String ngayGio) {
    }

    private SaoLuuPhucHoiDAO() {
    }

    public static List<BanSaoLuu> layDanhSachSaoLuu(sessionGiaoVien giaoVien) throws SQLException {
        List<BanSaoLuu> tuMsdb = new ArrayList<>();
        try (Connection ketNoi = giaoVien.moKetNoi()) {
            try (CallableStatement lenh = ketNoi.prepareCall("{call dbo.SP_LayLichSuSaoLuu(?)}")) {
                lenh.setQueryTimeout(60);
                lenh.setNString(1, DUONG_DAN_SAO_LUU);
                try (ResultSet rs = lenh.executeQuery()) {
                    while (rs.next()) {
                        String chuoiUuid = rs.getString("backup_set_uuid");
                        int position = rs.getInt("position");
                        Timestamp ngayGio = rs.getTimestamp("backup_finish_date");
                        if (chuoiUuid != null && ngayGio != null) {
                            UUID uuid = UUID.fromString(chuoiUuid.trim());
                            String hienThi = ngayGio.toLocalDateTime().format(DINH_DANG_NGAY_GIO);
                            tuMsdb.add(new BanSaoLuu(uuid, position, hienThi));
                        }
                    }
                }
            }

            if (tuMsdb.isEmpty()) {
                return List.of();
            }

            Set<UUID> uuidTrongFile = new HashSet<>();
            try (CallableStatement lenh = ketNoi.prepareCall("{call dbo.SP_DocHeaderSaoLuu(?)}")) {
                lenh.setQueryTimeout(60);
                lenh.setNString(1, DUONG_DAN_SAO_LUU);
                try (ResultSet rs = lenh.executeQuery()) {
                    while (rs.next()) {
                        String guid = rs.getString("BackupSetGUID");
                        if (guid != null && !guid.isBlank()) {
                            uuidTrongFile.add(UUID.fromString(guid.trim()));
                        }
                    }
                }
            } catch (SQLException e) {
                // Nếu file .bak chưa tồn tại trên đĩa (mã lỗi 3201) thì danh sách hiển thị rỗng.
                if (e.getErrorCode() == 3201) {
                    return List.of();
                }
                throw e;
            }

            List<BanSaoLuu> ketQua = new ArrayList<>();
            for (BanSaoLuu ban : tuMsdb) {
                if (uuidTrongFile.contains(ban.backupSetUuid())) {
                    ketQua.add(ban);
                }
            }
            return ketQua;
        }
    }

    public static void saoLuuDuLieu(sessionGiaoVien giaoVien, boolean resetFile) throws SQLException {
        try (Connection ketNoi = giaoVien.moKetNoi();
                CallableStatement lenh = ketNoi.prepareCall("{call dbo.SP_SaoLuuDuLieu(?, ?)}")) {
            ketNoi.setAutoCommit(true);
            lenh.setQueryTimeout(300);
            lenh.setNString(1, DUONG_DAN_SAO_LUU);
            lenh.setBoolean(2, resetFile);
            lenh.execute();
        }
    }

    public static void phucHoiDuLieu(sessionGiaoVien giaoVien, int viTri) throws SQLException {
        try (Connection ketNoi = giaoVien.moKetNoi()) {
            ketNoi.setAutoCommit(true);
            ketNoi.setCatalog("master");
            try (CallableStatement lenh = ketNoi.prepareCall("{call dbo.SP_PhucHoiDuLieu(?, ?)}")) {
                lenh.setQueryTimeout(300);
                lenh.setNString(1, DUONG_DAN_SAO_LUU);
                lenh.setInt(2, viTri);
                lenh.execute();
            }
        }
    }

    public static void xoaLichSuSaoLuu(sessionGiaoVien giaoVien) throws SQLException {
        try (Connection ketNoi = giaoVien.moKetNoi();
                CallableStatement lenh = ketNoi.prepareCall("{call dbo.SP_XoaLichSuSaoLuu(?)}")) {
            ketNoi.setAutoCommit(true);
            lenh.setQueryTimeout(60);
            lenh.setNString(1, TEN_CSDL);
            lenh.execute();
        }
    }
}

