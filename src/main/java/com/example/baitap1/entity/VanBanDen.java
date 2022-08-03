package com.example.baitap1.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "van_ban_den")
public class VanBanDen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "trich_yeu")
    private String trichYeu;

    @Column(name = "so_ky_hieu")
    private String soKyHieu;

    @Column(name = "ngay_ky")
    private String ngayKy;

    @Column(name = "nguoi_ky")
    private String nguoiKy;

    @Column(name ="da_xoa")
    private int daXoa;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTrichYeu() {
        return trichYeu;
    }

    public void setTrichYeu(String trichYeu) {
        this.trichYeu = trichYeu;
    }

    public String getSoKyHieu() {
        return soKyHieu;
    }

    public void setSoKyHieu(String soKyHieu) {
        this.soKyHieu = soKyHieu;
    }

    public String getNgayKy() {
        return ngayKy;
    }

    public void setNgayKy(String ngayKy) {
        this.ngayKy = ngayKy;
    }

    public String getNguoiKy() {
        return nguoiKy;
    }

    public void setNguoiKy(String nguoiKy) {
        this.nguoiKy = nguoiKy;
    }

    public int getDaXoa() {
        return daXoa;
    }

    public void setDaXoa(int daXoa) {
        this.daXoa = daXoa;
    }

    public VanBanDen() {
    }

    public VanBanDen(int id, String trichYeu, String soKyHieu, String ngayKy, String nguoiKy, int daXoa) {
        this.id = id;
        this.trichYeu = trichYeu;
        this.soKyHieu = soKyHieu;
        this.ngayKy = ngayKy;
        this.nguoiKy = nguoiKy;
        this.daXoa = daXoa;
    }
}
