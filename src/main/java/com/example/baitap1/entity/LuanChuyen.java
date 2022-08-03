package com.example.baitap1.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "luan_chuyen")
public class LuanChuyen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    @Column(name = "van_ban_den_id")
    private int vanBanDenId;


    @Column(name = "nguoi_nhan_id")
    private int nguoiNhanId;


    @Column(name = "nguoi_gui_id")
    private int nguoiGuiId;

    @Column(name = "ngay_chuyen")
    private String ngayChuyen;

    @Column(name ="da_xoa")
    private int daXoa;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVanBanDenId() {
        return vanBanDenId;
    }

    public void setVanBanDenId(int vanBanDenId) {
        this.vanBanDenId = vanBanDenId;
    }

    public int getNguoiNhanId() {
        return nguoiNhanId;
    }

    public void setNguoiNhanId(int nguoiNhanId) {
        this.nguoiNhanId = nguoiNhanId;
    }

    public int getNguoiGuiId() {
        return nguoiGuiId;
    }

    public void setNguoiGuiId(int nguoiGuiId) {
        this.nguoiGuiId = nguoiGuiId;
    }

    public String getNgayChuyen() {
        return ngayChuyen;
    }

    public void setNgayChuyen(String ngayChuyen) {
        this.ngayChuyen = ngayChuyen;
    }

    public int getDaXoa() {
        return daXoa;
    }

    public void setDaXoa(int daXoa) {
        this.daXoa = daXoa;
    }

    public LuanChuyen() {
    }

    public LuanChuyen(int id, int vanBanDenId, int nguoiNhanId, int nguoiGuiId, String ngayChuyen, int daXoa) {
        this.id = id;
        this.vanBanDenId = vanBanDenId;
        this.nguoiNhanId = nguoiNhanId;
        this.nguoiGuiId = nguoiGuiId;
        this.ngayChuyen = ngayChuyen;
        this.daXoa = daXoa;
    }
}
