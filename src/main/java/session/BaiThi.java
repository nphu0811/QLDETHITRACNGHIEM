package session;

import java.util.ArrayList;

// Dữ liệu một bài thi đọc từ SQL; không thay thế session người đăng nhập.
public class BaiThi {
    public CaThi caThi;
    public ArrayList<CauHoi> cauHoi = new ArrayList<>();
    public int viTriCau; // Java bắt đầu từ 0, THUTU trong SQL bắt đầu từ 1.
    public long conLaiMillis;
    public long mocDocThoiGian; // nanoTime ngay trước khi yêu cầu SQL trả thời gian.
    public boolean daNop;
    public int soCauDung;
    public double diem;
}
