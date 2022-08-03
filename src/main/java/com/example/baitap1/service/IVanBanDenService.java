package com.example.baitap1.service;

import com.example.baitap1.entity.VanBanDen;
import com.example.baitap1.exception.NotFoundException;
import com.example.baitap1.model.dto.SearchMultiParamRequest;

import java.util.List;
import java.util.Map;


public interface IVanBanDenService {
    List<VanBanDen> findAllVanBanDen(int offset, int limit , SearchMultiParamRequest searchMultiParamRequest) throws NotFoundException;

    VanBanDen findVanBanDenById(int id) throws NotFoundException;

    List<VanBanDen> getVanBanDenByCri(String keyword) throws NotFoundException;

    void insertVanBanDen(VanBanDen vanBanDen);

    void updateVanBanDen(VanBanDen vanBanDen) throws NotFoundException;

    Map<String,String> deleteVanBanDen(int id) throws NotFoundException;
}
