package com.example.baitap1.service.impl;

import com.example.baitap1.entity.VanBanDen;
import com.example.baitap1.exception.NotFoundException;
import com.example.baitap1.model.dto.SearchMultiParamRequest;
import com.example.baitap1.repository.IVanBanDenRepository;
import com.example.baitap1.service.IVanBanDenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VanBanDenServiceImpl implements IVanBanDenService {

    @Autowired
    IVanBanDenRepository iVanBanDenRepository;

    @Override
    public List<VanBanDen> findAllVanBanDen(int offset, int limit ,SearchMultiParamRequest searchMultiParamRequest) throws NotFoundException {

        List<VanBanDen> vanBanDenList = iVanBanDenRepository.findAllVanBanDen( offset,  limit, searchMultiParamRequest.getTrichYeu(), searchMultiParamRequest.getSoKyHieu(), searchMultiParamRequest.getNgayKy(),searchMultiParamRequest.getNguoiKy(), searchMultiParamRequest.getNguoiNhan(), searchMultiParamRequest.getNguoiGui(), searchMultiParamRequest.getNgayChuyen(), searchMultiParamRequest.getFromDate(),searchMultiParamRequest.getToDate());
        return vanBanDenList;
    }

    @Override
    public VanBanDen findVanBanDenById(int id) throws NotFoundException {
        VanBanDen vanBanDen = iVanBanDenRepository.findVanBanDenById(id);
        if(vanBanDen == null){
            throw new NotFoundException("Not result available for keyword: "+id);
        }
        return vanBanDen;
    }

    @Override
    public List<VanBanDen> getVanBanDenByCri(String keyword) throws NotFoundException {
        List<VanBanDen> vanBanDenList = iVanBanDenRepository.getVanBanDenByCri(keyword);
        if(!vanBanDenList.isEmpty()){
            return vanBanDenList;
        }
    throw new NotFoundException("Not result available for keyword: " + keyword);
    }

    @Transactional
    @Override
    public void insertVanBanDen(VanBanDen vanBanDen) {
        Date date = new Date();
        vanBanDen.setNgayKy(date.toString());
        vanBanDen.setDaXoa(0);
        iVanBanDenRepository.insertVanBanDen(vanBanDen);
    }

    @Transactional
    @Override
    public void updateVanBanDen(VanBanDen vanBanDen) throws NotFoundException {
        VanBanDen vanBanDenCheck = iVanBanDenRepository.findVanBanDenById(vanBanDen.getId());
        if(vanBanDenCheck==null){
            throw new NotFoundException("Not result available: "+vanBanDen.getId());
        }
        iVanBanDenRepository.updateVanBanDen(vanBanDen);
    }

    @Transactional
    @Override
    public Map<String,String> deleteVanBanDen(int id) throws NotFoundException {
        VanBanDen vanBanDenCheck = iVanBanDenRepository.findVanBanDenById(id);
        if(vanBanDenCheck==null){
            throw new NotFoundException("Not result available: "+id);
        }
        Map<String,String> obj = new HashMap<String, String>();
        iVanBanDenRepository.deleteVanBanDen(id);
            obj.put("message","delete successfully");
            return obj;


    }
}
