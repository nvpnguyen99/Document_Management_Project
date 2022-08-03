package com.example.baitap1.repository.impl;

import com.example.baitap1.entity.VanBanDen;
import com.example.baitap1.exception.NotFoundException;
import com.example.baitap1.repository.IVanBanDenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class VanBanDenRepositoryImpl implements IVanBanDenRepository {
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<VanBanDen> findAllVanBanDen(int offset, int limit, String trichYeu, String soKyHieu, String ngayKy, String nguoiKy, String nguoiNhan, String nguoiGui, String ngayChuyen, String fromDate, String toDate) throws NotFoundException {
        StringBuilder queryString = new StringBuilder(

                "select vanbanden.id, vanbanden.so_ky_hieu, vanbanden.trich_yeu, vanbanden.ngay_ky, vanbanden.nguoi_ky, vanbanden.da_xoa\n" +
                "From van_ban_den as vanbanden\n" +
                "inner join luan_chuyen as luanchuyen on vanbanden.id = luanchuyen.van_ban_den_id\n" +
                "inner join nguoi_dung as nguoinhan on nguoinhan.id = luanchuyen.nguoi_nhan_id\n" +
                "inner join nguoi_dung as nguoigui on nguoigui.id = luanchuyen.nguoi_gui_id\n" +
                "where vanbanden.da_xoa = 0 AND luanchuyen.da_xoa = 0\n"
               );
        if(null != ngayChuyen){
            queryString.append( "AND edge_gram_tsvector(luanchuyen.ngay_chuyen :: text) @@ plainto_tsquery(?)\n") ;
        }
        if(null != nguoiNhan){
            queryString.append("AND edge_gram_tsvector(nguoinhan.ten) @@ plainto_tsquery(?)\n");
        }
        if(null != nguoiGui){
            queryString.append("AND edge_gram_tsvector(nguoigui.ten) @@ plainto_tsquery(?)\n");
        }
        if(null != soKyHieu){
            queryString.append("AND edge_gram_tsvector(vanbanden.so_ky_hieu) @@ plainto_tsquery(?)\n") ;
        }
        if(null != trichYeu){
            queryString.append(  "AND edge_gram_tsvector(vanbanden.trich_yeu) @@ plainto_tsquery(?)\n");
        }
        if(null != ngayKy){
            queryString.append( "AND edge_gram_tsvector(vanbanden.ngay_ky::text) @@ plainto_tsquery(?)\n") ;
        }
        if(null != nguoiKy){
            queryString.append("AND edge_gram_tsvector(vanbanden.nguoi_ky) @@ plainto_tsquery(?)\n");
        }
        if(null != fromDate && null != toDate){
            queryString.append("AND vanbanden.ngay_ky between to_date(?,'YYYY-MM-DD') and to_date(?,'YYYY-MM-DD')\n") ;
        }
        if(null != nguoiKy){
            queryString.append( "order by ts_rank(to_tsvector(vanbanden.nguoi_ky), to_tsquery(?)) desc");
        }
        queryString.append(" limit ? offset ?");

       return jdbcTemplate.query(queryString.toString(), new PreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement preparedStatement) throws SQLException {
                    int count = 0;
                    if (null != ngayChuyen) {
                        count++;
                        preparedStatement.setString(count,  ngayChuyen );
                    }
                    if (null != nguoiNhan) {
                        count++;
                        preparedStatement.setString(count,  nguoiNhan );
                    }
                    if (null != nguoiGui) {
                        count++;
                        preparedStatement.setString(count, nguoiGui );
                    }
                    if (null != soKyHieu) {
                        count++;
                        preparedStatement.setString(count,  soKyHieu );
                    }
                    if (null != trichYeu) {
                        count++;
                        preparedStatement.setString(count,  trichYeu );
                    }
                    if (null != ngayKy) {
                        count++;
                        preparedStatement.setString(count, ngayKy );
                    }
                    if (null != nguoiKy) {
                        count++;
                        preparedStatement.setString(count, nguoiKy );
                    }
                    if (null != fromDate && null != toDate) {
                        count++;
                        preparedStatement.setString(count, fromDate);
                        count++;
                        preparedStatement.setString(count, toDate);
                    }
                    if (null != nguoiKy) {
                        count++;
                        preparedStatement.setString(count, nguoiKy);
                    }
                count++;
                preparedStatement.setInt(count, limit);
                count++;
                preparedStatement.setInt(count, offset);
                }
            }, new RowMapper<VanBanDen>() {
                @Override
                public VanBanDen mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                    VanBanDen vanBanDen = new VanBanDen();
                    vanBanDen.setId(resultSet.getInt("id") );
                    vanBanDen.setSoKyHieu(resultSet.getString("so_ky_hieu"));
                    vanBanDen.setTrichYeu(resultSet.getString("trich_yeu"));
                    vanBanDen.setNgayKy(resultSet.getString("ngay_ky"));
                    vanBanDen.setNguoiKy(resultSet.getString("nguoi_ky"));
                    vanBanDen.setDaXoa(resultSet.getInt("da_xoa"));
                    return vanBanDen;
                }
            });


    }


    @Override
    public VanBanDen findVanBanDenById(int id) {
        try{
            String queryString = "select * from van_ban_den where id = ?1 and da_xoa=0";
            TypedQuery<VanBanDen> query = (TypedQuery<VanBanDen>) entityManager.createNativeQuery(queryString,VanBanDen.class);
            query.setParameter(1,id);
            return query.getSingleResult();
        } catch (NoResultException ex){
            return null;
        }
    }
    @Override
    public List<VanBanDen> getVanBanDenByCri(String keyword) {
        String queryString = "select * from van_ban_den v where v.so_ky_hieu=?1 or v.nguoi_ky=?1";
        TypedQuery<VanBanDen> query = (TypedQuery<VanBanDen>)entityManager.createNativeQuery(queryString,VanBanDen.class);
        query.setParameter(1,keyword);
        List<VanBanDen> vanBanDenList = query.getResultList();
        return vanBanDenList;
    }


    @Override
    public void insertVanBanDen(VanBanDen vanBanDen) {
    //    String queryString = "insert into van_ban_den(trich_yeu,so_ky_hieu,ngay_ky,nguoi_ky,da_xoa) values(?1,?2,to_date(?3,'YYYY-MM-DD'),?4,?5)";
    String queryString = "insert into van_ban_den(trich_yeu,so_ky_hieu,ngay_ky,nguoi_ky,da_xoa) values(?1,?2,?3,?4,?5)";
    TypedQuery<?> query = (TypedQuery<?>) entityManager.createNativeQuery(queryString);
    query.setParameter(1,vanBanDen.getTrichYeu());
    query.setParameter(2, vanBanDen.getSoKyHieu());
    query.setParameter(3,vanBanDen.getNgayKy());
    query.setParameter(4,vanBanDen.getNguoiKy());
    query.setParameter(5,vanBanDen.getDaXoa());
    query.executeUpdate();
    }


    @Override
    public void updateVanBanDen(VanBanDen vanBanDen) {
       String queryString ="update van_ban_den set trich_yeu = ?1, so_ky_hieu=?2, ngay_ky=?3, nguoi_ky=?4, da_xoa=?5 where id= ?6 ";
       TypedQuery<?> query = (TypedQuery<?>) entityManager.createNativeQuery(queryString);
        query.setParameter(1,vanBanDen.getTrichYeu());
        query.setParameter(2, vanBanDen.getSoKyHieu());
        query.setParameter(3,vanBanDen.getNgayKy());
        query.setParameter(4,vanBanDen.getNguoiKy());
        query.setParameter(5,vanBanDen.getDaXoa());
        query.setParameter(6,vanBanDen.getId());
        query.executeUpdate();
    }


    @Override
    public void deleteVanBanDen(int id) {
        String queryString ="update van_ban_den set da_xoa=?1  where id= ?2 ";
        TypedQuery<?> query = (TypedQuery<?>) entityManager.createNativeQuery(queryString);
        query.setParameter(1, 1);
        query.setParameter(2, id);
        query.executeUpdate();
    }
}
