package com.example.baitap1.model.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchMultiParamRequest {
    private String trichYeu;

    private String soKyHieu;

    private String ngayKy;

    private String nguoiKy;

    private String nguoiNhan;

    private String nguoiGui;

    private String ngayChuyen;

    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "format date invalid")
    private String fromDate;

    private String toDate;
}
