package com.example.baitap1.service;

import com.example.baitap1.entity.NguoiDung;
import com.example.baitap1.exception.NotFoundException;

import java.util.List;

public interface INguoiDungService {

    List<NguoiDung> findAllNguoiDung() throws NotFoundException;

    NguoiDung findNguoiDungById (int id) throws NotFoundException;

    void insertNguoiDung(NguoiDung nguoiDung);

    void updateNguoiDung(NguoiDung nguoiDung) throws NotFoundException;

    void deleteNguoiDung(int id) throws NotFoundException;
}
