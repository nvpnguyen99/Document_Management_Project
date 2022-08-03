package com.example.baitap1.repository.impl;

import com.example.baitap1.entity.NguoiDung;
import com.example.baitap1.repository.INguoiDungRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class NguoiDungRepositoryImpl implements INguoiDungRepository {

    @Autowired
    EntityManager entityManager;

    @Override
    public List<NguoiDung> findAllNguoiDung() {
        String queryString = "select * from nguoi_dung where da_xoa=0";
        TypedQuery<NguoiDung> query = (TypedQuery<NguoiDung>) entityManager.createNativeQuery(queryString,NguoiDung.class);
        List<NguoiDung> nguoiDungList = query.getResultList();
        return nguoiDungList;
    }

    @Override
    public NguoiDung findNguoiDungById(int id) {
        try {
            String queryString = "select * from nguoi_dung where da_xoa = 0 and id = ?1";
            TypedQuery<NguoiDung> query = (TypedQuery<NguoiDung>) entityManager.createNativeQuery(queryString, NguoiDung.class);
            query.setParameter(1, id);
            NguoiDung nguoiDung = query.getSingleResult();
            return nguoiDung;
        } catch (NoResultException ex){
            return null;
        }
    }


    @Override
    public void insertNguoiDung(NguoiDung nguoiDung) {
        String queryString = "insert into nguoi_dung (ten,da_xoa) values (?1,?2)";
        TypedQuery<?> query = (TypedQuery<?>) entityManager.createNativeQuery(queryString);
        query.setParameter(1,nguoiDung.getTen());
        query.setParameter(2,nguoiDung.getDaXoa());
        query.executeUpdate();
    }


    @Override
    public void updateNguoiDung(NguoiDung nguoiDung) {
        String queryString = "update nguoi_dung set ten = ?1 where id = ?2";
        TypedQuery<?> query = (TypedQuery<?>) entityManager.createNativeQuery(queryString);
        query.setParameter(1,nguoiDung.getTen());
        query.setParameter(2,nguoiDung.getId());
        query.executeUpdate();
    }


    @Override
    public void deleteNguoiDung(int id) {
        String queryString = "delete from nguoi_dung where id = ?1";
        TypedQuery<?> query = (TypedQuery<?>) entityManager.createNativeQuery(queryString);
        query.setParameter(1,id);
        query.executeUpdate();
    }
}
