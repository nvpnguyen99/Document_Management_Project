package com.example.baitap1.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class LuanChuyenRequest {
    private int id;

    @NotNull
    private int vanBanDenId;

    @NotNull
    private int nguoiNhanId;

    @NotNull
    private int nguoiGuiId;


    private Date ngayChuyen;

    private int daXoa;


}
