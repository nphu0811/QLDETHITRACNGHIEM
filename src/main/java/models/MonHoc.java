package models;

// Mỗi mục trong combobox giữ cả mã môn và tên môn.
public class MonHoc {

    private String maMH;
    private String tenMH;

    public MonHoc(String maMH, String tenMH) {
        this.maMH = maMH;
        this.tenMH = tenMH;
    }

    public String getMaMH() {
        return maMH;
    }

    public String getTenMH() {
        return tenMH;
    }

    @Override
    public String toString() {
        // Combobox dùng giá trị này làm chữ hiển thị.
        return tenMH;
    }
}
