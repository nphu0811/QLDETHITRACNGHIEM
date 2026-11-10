package models;

// Một dòng bảng điểm của sinh viên; chi tiết câu hỏi được đọc khi bấm xem.
public class DiemThi {
    private final String maMH;
    private final String tenMH;
    private final String ngayThi;
    private final int lan;
    private final double diem;

    public DiemThi(String maMH, String tenMH, String ngayThi, int lan, double diem) {
        this.maMH = maMH;
        this.tenMH = tenMH;
        this.ngayThi = ngayThi;
        this.lan = lan;
        this.diem = diem;
    }

    public String getMaMH() { return maMH; }
    public String getTenMH() { return tenMH; }
    public String getNgayThi() { return ngayThi; }
    public int getLan() { return lan; }
    public double getDiem() { return diem; }
}
