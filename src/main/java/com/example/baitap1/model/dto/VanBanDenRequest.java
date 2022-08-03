package com.example.baitap1.model.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class VanBanDenRequest {
    private int id;

    @NotEmpty
    @Size(min = 2 , message = "user name should have at least 2 characters")
    private String trichYeu;

    @NotEmpty
    @Size(min = 2 , message = "user name should have at least 2 characters")
    private String soKyHieu;

    //@Pattern(regexp = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$", message = "invalid date format")
    @NotNull
    private Date ngayKy;
    @NotEmpty
    private String nguoiKy;


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

    public Date getNgayKy() {
        return ngayKy;
    }

    public void setNgayKy(Date ngayKy) {
        this.ngayKy = ngayKy;
    }

    public String getNguoiKy() {
        return nguoiKy;
    }

    public void setNguoiKy(String nguoiKy) {
        this.nguoiKy = nguoiKy;
    }


    public VanBanDenRequest() {
    }

    public VanBanDenRequest(int id, String trichYeu, String soKyHieu, Date ngayKy, String nguoiKy) {
        this.id = id;
        this.trichYeu = trichYeu;
        this.soKyHieu = soKyHieu;
        this.ngayKy = ngayKy;
        this.nguoiKy = nguoiKy;

    }
}
