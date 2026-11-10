/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package session;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author admin
 */
public class sessionGiaoVien {
    @FunctionalInterface
    public interface KetNoi {
        Connection mo() throws SQLException;
    }

    private final KetNoi ketNoi;
    private final String tenDangNhap;
    public String maGV;
    public String ho;
    public String ten;
    public String soDTLL;
    public String diaChi;

    public String getMaGV() {
        return maGV;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setMaGV(String maGV) {
        this.maGV = maGV;
    }

    public String getHo() {
        return ho;
    }

    public void setHo(String ho) {
        this.ho = ho;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getSoDTLL() {
        return soDTLL;
    }

    public void setSoDTLL(String soDTLL) {
        this.soDTLL = soDTLL;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public sessionGiaoVien() {
        this("", null);
    }

    public sessionGiaoVien(String tenDangNhap, KetNoi ketNoi) {
        this.tenDangNhap = tenDangNhap;
        this.ketNoi = ketNoi;
    }

    public boolean coKetNoi() {
        return ketNoi != null;
    }

    public Connection moKetNoi() throws SQLException {
        if (!coKetNoi()) {
            throw new SQLException("Vui lòng đăng nhập bằng tài khoản giảng viên.");
        }
        return ketNoi.mo();
    }
}
