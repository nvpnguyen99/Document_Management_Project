package com.example.baitap1.repository;

import com.example.baitap1.entity.NguoiDung;

import java.util.List;

public interface INguoiDungRepository {
    List<NguoiDung> findAllNguoiDung();

    NguoiDung findNguoiDungById(int id);

    void insertNguoiDung(NguoiDung nguoiDung);

    void updateNguoiDung(NguoiDung nguoiDung);

    void deleteNguoiDung(int id);
}
