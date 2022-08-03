package com.example.baitap1.repository;

import com.example.baitap1.model.dto.SearchMultiParamResponse;
import com.example.baitap1.model.dto.SearchResponse;

import java.util.Date;
import java.util.List;

public interface ISearchMultiParamsRepository {
    List<SearchMultiParamResponse> searchByMultiParam(String trichYeu, String soKyHieu, String ngayKy, String nguoiKy, String nguoiNhan, String nguoiGui, String ngayChuyen);
}
