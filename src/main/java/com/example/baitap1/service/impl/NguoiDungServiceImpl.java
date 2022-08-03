package com.example.baitap1.service.impl;

import com.example.baitap1.entity.NguoiDung;
import com.example.baitap1.exception.NotFoundException;
import com.example.baitap1.repository.INguoiDungRepository;
import com.example.baitap1.service.INguoiDungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class NguoiDungServiceImpl implements INguoiDungService {

    @Autowired
    INguoiDungRepository iNguoiDungRepository;

    @Override
    public List<NguoiDung> findAllNguoiDung() throws NotFoundException {
        List<NguoiDung> nguoiDungList = iNguoiDungRepository.findAllNguoiDung();
        if(nguoiDungList.isEmpty()){
            throw new NotFoundException("there are no results");
        }
        return nguoiDungList;
    }

    @Override
    public NguoiDung findNguoiDungById(int id) throws NotFoundException {
        NguoiDung nguoiDung = iNguoiDungRepository.findNguoiDungById(id);
        if(nguoiDung == null){
            throw new NotFoundException("no results available with"+id);
        }
        return nguoiDung;
    }

    @Transactional
    @Override
    public void insertNguoiDung(NguoiDung nguoiDung){
        nguoiDung.setDaXoa(0);
        iNguoiDungRepository.insertNguoiDung(nguoiDung);
    }

    @Transactional
    @Override
    public void updateNguoiDung(NguoiDung nguoiDung) throws NotFoundException {
        NguoiDung nguoiDungCheck = iNguoiDungRepository.findNguoiDungById(nguoiDung.getId());
        if(nguoiDungCheck == null){
            throw new NotFoundException("Not result Available with "+nguoiDung.getId());
        }
        iNguoiDungRepository.updateNguoiDung(nguoiDung);
    }

    @Transactional
    @Override
    public void deleteNguoiDung(int id) throws NotFoundException {
        NguoiDung nguoiDungCheck = iNguoiDungRepository.findNguoiDungById(id);
        if(nguoiDungCheck == null){
            throw new NotFoundException("Not result Available with "+id);
        }
        iNguoiDungRepository.deleteNguoiDung(id);

    }
}
