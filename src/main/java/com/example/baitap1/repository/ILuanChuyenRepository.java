package com.example.baitap1.repository;

import com.example.baitap1.entity.LuanChuyen;
import com.example.baitap1.model.dto.SearchResponse;
import com.example.baitap1.model.dto.ThongKeResponse;

import java.time.LocalDate;
import java.util.List;

public interface ILuanChuyenRepository {

    List<LuanChuyen> findLuanChuyen(int offset, int limit, String trichYeu, String soKyHieu, String ngayKy, String nguoiKy, String nguoiNhan, String nguoiGui, String ngayChuyen, String fromDate, String toDate);

    LuanChuyen findLuanChuyenById(int id);

    List<SearchResponse> findLuanChuyenOfVanBanDen(int id);

    List<ThongKeResponse> thongKe();
    void insertLuanChuyen(LuanChuyen luanChuyen);

    void updateLuanChuyen(LuanChuyen luanChuyen);

    void deleteLuanChuyen(int id);

    int countLuanChuyen(String trichYeu, String soKyHieu, String ngayKy, String nguoiKy, String nguoiNhan, String nguoiGui, String ngayChuyen, String fromDate, String toDate);


    List<LuanChuyen> findAllLuanChuyen(int offset, int limit);
}

