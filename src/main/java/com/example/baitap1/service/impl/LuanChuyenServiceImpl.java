package com.example.baitap1.service.impl;

import com.example.baitap1.entity.LuanChuyen;
import com.example.baitap1.entity.NguoiDung;
import com.example.baitap1.entity.VanBanDen;
import com.example.baitap1.exception.NotFoundException;
import com.example.baitap1.model.dto.PageResponse;
import com.example.baitap1.model.dto.SearchMultiParamRequest;
import com.example.baitap1.model.dto.SearchResponse;
import com.example.baitap1.model.dto.ThongKeResponse;
import com.example.baitap1.repository.ILuanChuyenRepository;
import com.example.baitap1.repository.INguoiDungRepository;
import com.example.baitap1.repository.IVanBanDenRepository;
import com.example.baitap1.service.ILuanChuyenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class LuanChuyenServiceImpl implements ILuanChuyenService {

    @Autowired
    ILuanChuyenRepository iLuanChuyenRepository;

    @Autowired
    IVanBanDenRepository iVanBanDenRepository;

    @Autowired
    INguoiDungRepository iNguoiDungRepository;



    @Cacheable(value = "luanChuyen")
    @Override
    public List<LuanChuyen> findLuanChuyen(int offset, int limit, String trichYeu, String soKyHieu, String ngayKy, String nguoiKy, String nguoiNhan, String nguoiGui, String ngayChuyen, String fromDate, String toDate) throws NotFoundException {

        List<LuanChuyen> luanChuyenList = iLuanChuyenRepository.findLuanChuyen(offset, limit, trichYeu, soKyHieu, ngayKy, nguoiKy, nguoiNhan, nguoiGui, ngayChuyen, fromDate, toDate);
        if(luanChuyenList.isEmpty()){
            throw new NotFoundException("Not Result Available");
        }
        try{
            Thread.sleep(1000*5);
        } catch (InterruptedException ignored) {

        }
        return luanChuyenList;
    }


    @Override
    public int countAllLuanChuyen(SearchMultiParamRequest searchMultiParamRequest) {
        return iLuanChuyenRepository.countLuanChuyen(searchMultiParamRequest.getTrichYeu(), searchMultiParamRequest.getSoKyHieu(), searchMultiParamRequest.getNgayKy(),searchMultiParamRequest.getNguoiKy(), searchMultiParamRequest.getNguoiNhan(), searchMultiParamRequest.getNguoiGui(), searchMultiParamRequest.getNgayChuyen(), searchMultiParamRequest.getFromDate(),searchMultiParamRequest.getToDate());
    }


    @Override
    @Caching(evict = {
            @CacheEvict(value = "luanChuyen",allEntries = true),
            @CacheEvict(value = "luanChuyenById",allEntries = true)
    })
    public void clearCache() {
  //      cacheManager.getCache("luanchuyen").clear();
    }

    @Cacheable(value = "luanChuyenById")
    @Override
    public LuanChuyen findLuanChuyenById(int id) throws NotFoundException {

        LuanChuyen luanChuyen = iLuanChuyenRepository.findLuanChuyenById(id);
        if(luanChuyen==null){
            throw new NotFoundException("Not Result Available");
        }
        return luanChuyen;
    }

    @Override
    public List<SearchResponse> findLuanChuyenOfVanBanDen(int id) throws NotFoundException {
        VanBanDen vanBanDenCheck = iVanBanDenRepository.findVanBanDenById(id);
        if(vanBanDenCheck == null){
            throw new NotFoundException("Not Result Available: "+id);
        }
        List<SearchResponse> searchResponseList = iLuanChuyenRepository.findLuanChuyenOfVanBanDen(id);
        if(searchResponseList.isEmpty()){
            throw new NotFoundException("This document hasn't been transfered");
        }
        return searchResponseList;
    }

    @Override
    public List<ThongKeResponse> thongKe() throws NotFoundException {
        List<ThongKeResponse> thongKeResponses = iLuanChuyenRepository.thongKe();
        if(thongKeResponses.isEmpty()){
            throw new NotFoundException("Not Result Available");
        }
        return thongKeResponses;
    }



    @Transactional
    @Override
    public void insertLuanChuyen(LuanChuyen luanChuyen) throws NotFoundException {
        VanBanDen vanBanDenCheck = iVanBanDenRepository.findVanBanDenById(luanChuyen.getVanBanDenId());
        NguoiDung nguoiNhan     = iNguoiDungRepository.findNguoiDungById(luanChuyen.getNguoiNhanId()) ;
        NguoiDung nguoiGui     = iNguoiDungRepository.findNguoiDungById(luanChuyen.getNguoiGuiId()) ;
        if(vanBanDenCheck == null || nguoiNhan == null || nguoiGui == null){
            throw new NotFoundException("Not Result Available");
        }
        Date date = new Date();
        luanChuyen.setNgayChuyen(date.toString());
        luanChuyen.setDaXoa(0);
        iLuanChuyenRepository.insertLuanChuyen(luanChuyen);
    }

    @Transactional
    @Override
    public void updateLuanChuyen(LuanChuyen luanChuyen) throws NotFoundException {
        LuanChuyen luanChuyenCheck = iLuanChuyenRepository.findLuanChuyenById(luanChuyen.getId());
        VanBanDen VanBanDenCheck = iVanBanDenRepository.findVanBanDenById(luanChuyen.getVanBanDenId());
        NguoiDung nguoiNhan     = iNguoiDungRepository.findNguoiDungById(luanChuyen.getNguoiNhanId()) ;
        NguoiDung nguoiGui     = iNguoiDungRepository.findNguoiDungById(luanChuyen.getNguoiGuiId()) ;
        if(luanChuyenCheck==null || VanBanDenCheck == null || nguoiNhan == null || nguoiGui == null){
            throw new NotFoundException("Not Result Available");
        }
        iLuanChuyenRepository.updateLuanChuyen(luanChuyen);

    }

    @Transactional
    @Override
    public void deleteLuanChuyen(int id) throws NotFoundException {
        LuanChuyen luanChuyenCheck = iLuanChuyenRepository.findLuanChuyenById(id);
        if (luanChuyenCheck == null){
            throw new NotFoundException("Not Result Available");
        }
        iLuanChuyenRepository.deleteLuanChuyen(id);
    }

    public LuanChuyenServiceImpl() {
        super();
    }

    @Override
    public PageResponse calculateNumOfPage(int numberOfElements, int limit, int page) {
        int numOfPage = numberOfElements/limit;
        PageResponse pageResponse = new PageResponse(numberOfElements,numOfPage, page);
        if(numberOfElements% limit !=0 ){
            pageResponse.setNumberOfPages(numOfPage+1);
        }
        return pageResponse;
    }

    @Override
    public List<LuanChuyen> findAllLuanChuyen(int offset, int limit) {
        return  iLuanChuyenRepository.findAllLuanChuyen(offset, limit);
    }
}
