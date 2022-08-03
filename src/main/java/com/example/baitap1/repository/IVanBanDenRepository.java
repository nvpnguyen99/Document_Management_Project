package com.example.baitap1.repository;

import com.example.baitap1.entity.VanBanDen;
import com.example.baitap1.exception.NotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface IVanBanDenRepository {


    List<VanBanDen> findAllVanBanDen(int offset, int limit, String trichYeu, String soKyHieu, String ngayKy, String nguoiKy, String nguoiNhan, String nguoiGui, String ngayChuyen, String fromDate, String toDate) throws NotFoundException;

      VanBanDen findVanBanDenById(int id);

    List<VanBanDen> getVanBanDenByCri(String keyword);


    void insertVanBanDen(VanBanDen vanBanDen);

    void updateVanBanDen(VanBanDen vanBanDen);

    void deleteVanBanDen(int id);


}
