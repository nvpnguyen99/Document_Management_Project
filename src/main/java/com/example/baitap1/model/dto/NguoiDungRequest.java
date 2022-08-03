package com.example.baitap1.model.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class NguoiDungRequest {

    private int id;

    @NotEmpty
    @Size(min = 2 , message = "user name should have at least 2 characters")
    private String ten;

    private int daXoa;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getDaXoa() {
        return daXoa;
    }

    public void setDaXoa(int daXoa) {
        this.daXoa = daXoa;
    }
}
