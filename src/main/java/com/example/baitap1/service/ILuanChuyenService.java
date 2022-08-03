package com.example.baitap1.service;

import com.example.baitap1.entity.LuanChuyen;
import com.example.baitap1.exception.NotFoundException;
import com.example.baitap1.model.dto.PageResponse;
import com.example.baitap1.model.dto.SearchMultiParamRequest;
import com.example.baitap1.model.dto.SearchResponse;
import com.example.baitap1.model.dto.ThongKeResponse;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface ILuanChuyenService {

    List<LuanChuyen> findLuanChuyen(int offset, int limit, String trichYeu, String soKyHieu, String ngayKy, String nguoiKy, String nguoiNhan, String nguoiGui, String ngayChuyen, String fromDate, String toDate) throws NotFoundException;

    int countAllLuanChuyen(SearchMultiParamRequest searchMultiParamRequest);

    LuanChuyen findLuanChuyenById(int id) throws NotFoundException;

    List<SearchResponse> findLuanChuyenOfVanBanDen(int id) throws NotFoundException;

    List<ThongKeResponse> thongKe() throws NotFoundException;

    void insertLuanChuyen(LuanChuyen luanChuyen) throws NotFoundException;

    void updateLuanChuyen(LuanChuyen luanChuyen) throws NotFoundException;

    void deleteLuanChuyen(int id) throws NotFoundException;

    PageResponse calculateNumOfPage(int numberOfElements, int limit, int page);

    List<LuanChuyen> findAllLuanChuyen(int offset, int limit);

    void clearCache();

}
