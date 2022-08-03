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
public class SearchResponse {

    private String trichYeu;

    private String soKyHieu;

    private Date ngayKy;

    private String nguoiKy;

    private String nguoiNhan;

    private String nguoiGui;

    private Date ngayChuyen;

}
