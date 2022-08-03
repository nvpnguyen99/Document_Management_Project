package com.example.baitap1.service;

import com.example.baitap1.exception.NotFoundException;
import com.example.baitap1.model.dto.SearchMultiParamResponse;
import com.example.baitap1.model.dto.SearchResponse;

import java.util.Date;
import java.util.List;

public interface ISearchService {
    List<SearchMultiParamResponse> SearchByMultipleParam (String trichYeu, String soKyHieu, String ngayKy, String nguoiKy, String nguoiNhan, String nguoiGui, String ngayChuyen) throws NotFoundException;
}
