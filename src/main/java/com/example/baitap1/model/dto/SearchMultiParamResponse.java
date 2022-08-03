package com.example.baitap1.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchMultiParamResponse {
    private String trichYeu;

    private String soKyHieu;

    private String ngayKy;

    private String nguoiKy;

    private String nguoiNhan;

    private String nguoiGui;

    private String ngayChuyen;
}
