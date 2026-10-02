/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package session;

/**
 *
 * @author admin
 */
public class sessionGiaoVien implements SessionDangNhap {
    public String maGV;
    public String ho;
    public String ten;
    public String soDTLL;
    public String diaChi;

    public String getMaGV() {
        return maGV;
    }

    @Override
    public String getMaNguoiDung() {
        return maGV;
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
    }
    
    
}
