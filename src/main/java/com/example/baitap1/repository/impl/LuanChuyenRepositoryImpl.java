package com.example.baitap1.repository.impl;

import com.example.baitap1.entity.LuanChuyen;
import com.example.baitap1.model.dto.SearchResponse;
import com.example.baitap1.model.dto.ThongKeResponse;
import com.example.baitap1.repository.ILuanChuyenRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public class LuanChuyenRepositoryImpl implements ILuanChuyenRepository {

    @Autowired
    EntityManager entityManager;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<LuanChuyen> findLuanChuyen(int offset, int limit, String trichYeu, String soKyHieu, String ngayKy, String nguoiKy, String nguoiNhan, String nguoiGui, String ngayChuyen, String fromDate, String toDate) {

       StringBuilder queryString = new StringBuilder( "Select luanchuyen.id, luanchuyen.van_ban_den_id, luanchuyen.nguoi_nhan_id, luanchuyen.nguoi_gui_id, luanchuyen.ngay_chuyen" +
               " From van_ban_den as vanbanden\n" +
               "Inner Join luan_chuyen as luanchuyen on vanbanden.id = luanchuyen.van_ban_den_id\n" +
               "Inner Join nguoi_dung as nguoinhan on nguoinhan.id = luanchuyen.nguoi_nhan_id\n" +
               "Inner Join nguoi_dung as nguoigui on nguoigui.id = luanchuyen.nguoi_gui_id\n" +
               "Where vanbanden.da_xoa = 0 \n");


        if(null != ngayChuyen){
            queryString.append("AND luanchuyen.ngay_chuyen like ? \n ") ;
        }
        if(null != nguoiNhan){
            queryString.append("AND nguoinhan.ten like ? \n ");
        }
        if(null != nguoiGui){
            queryString.append("AND nguoigui.ten like ? \n");
        }
        if(null != soKyHieu){
            queryString.append("AND vanbanden.so_ky_hieu like ?\n") ;
        }
        if(null != trichYeu){
            queryString.append("AND vanbanden.trich_yeu like ? \n");
        }
        if(null != ngayKy){
            queryString.append("AND vanbanden.ngay_ky like ? \n") ;
        }
        if(null != nguoiKy){
            queryString.append("AND vanbanden.nguoi_ky like ?");
        }
        if(null != fromDate && null != toDate){
            queryString.append("AND luanchuyen.ngay_chuyen between to_date(?,'YYYY-MM-DD') and to_date(?,'YYYY-MM-DD')") ;
        }
        queryString.append(" order by luanchuyen.id limit ? offset ?");
        return jdbcTemplate.query(queryString.toString(),
                new PreparedStatementSetter() {

                    @Override
                    public void setValues(PreparedStatement preparedStatement) throws SQLException {

                        int count =0;
                        if(null != ngayChuyen){
                            count++;
                            preparedStatement.setString(count, "%"+ngayChuyen+"%");
                        }
                        if(null != nguoiNhan){
                            count++;
                            preparedStatement.setString(count, "%"+nguoiNhan+"%");
                        }
                        if(null != nguoiGui){
                            count++;
                            preparedStatement.setString(count, "%"+nguoiGui+"%");
                        }
                        if(null != soKyHieu){
                            count++;
                            preparedStatement.setString(count, "%"+soKyHieu+"%");
                        }
                        if(null != trichYeu){
                            count++;
                            preparedStatement.setString(count, "%"+trichYeu+"%");
                        }
                        if(null != ngayKy){
                            count++;
                            preparedStatement.setString(count, "%"+ngayKy+"%");
                        }
                        if(null != nguoiKy){
                            count++;
                            preparedStatement.setString(count, "%"+nguoiKy+"%");
                        }
                        if(null != fromDate && null != toDate){
                            count++;
                            preparedStatement.setString(count,fromDate);
                            count++;
                            preparedStatement.setString(count,toDate);
                        }
                        count++;
                        preparedStatement.setInt(count,limit);
                        count++;
                        preparedStatement.setInt(count,offset);
                    }
                }
                , new RowMapper<LuanChuyen>() {
                    @Override
                    public LuanChuyen mapRow(ResultSet resultSet, int i) throws SQLException {
                        LuanChuyen luanChuyen = new LuanChuyen();
                        luanChuyen.setId(resultSet.getInt("id"));
                        luanChuyen.setVanBanDenId(resultSet.getInt("van_ban_den_id"));
                        luanChuyen.setNguoiNhanId(resultSet.getInt("nguoi_nhan_id"));
                        luanChuyen.setNguoiGuiId(resultSet.getInt("nguoi_gui_id"));
                        luanChuyen.setNgayChuyen(resultSet.getString("ngay_chuyen"));
                        return luanChuyen;
                    }
                } );
    }

    @Override
    public int countLuanChuyen(String trichYeu, String soKyHieu, String ngayKy, String nguoiKy, String nguoiNhan, String nguoiGui, String ngayChuyen, String fromDate, String toDate) {
        StringBuilder queryString = new StringBuilder("Select count(*)" +
                " From van_ban_den as vanbanden\n" +
                "Inner Join luan_chuyen as luanchuyen on vanbanden.id = luanchuyen.van_ban_den_id\n" +
                "Inner Join nguoi_dung as nguoinhan on nguoinhan.id = luanchuyen.nguoi_nhan_id\n" +
                "Inner Join nguoi_dung as nguoigui on nguoigui.id = luanchuyen.nguoi_gui_id\n" +
                "Where 1=1 \n");

        if(null != ngayChuyen){
            queryString.append("AND luanchuyen.ngay_chuyen::text like :ngayChuyen \n ");
        }
        if(null != nguoiNhan){
            queryString.append("AND nguoinhan.ten like ? \n ");
        }
        if(null != nguoiGui){
            queryString.append("AND nguoigui.ten like ? \n");
        }
        if(null != soKyHieu){
            queryString.append("AND vanbanden.so_ky_hieu like ?\n");
        }
        if(null != trichYeu){
            queryString.append("AND vanbanden.trich_yeu like ? \n");
        }
        if(null != ngayKy){
            queryString.append("AND vanbanden.ngay_ky::text like ? \n");
        }
        if(null != nguoiKy){
            queryString.append("AND vanbanden.nguoi_ky like ?");
        }

        if(null != fromDate && null != toDate  ){
            queryString.append("AND luanchuyen.ngay_chuyen between to_date(?,'YYYY-MM-DD') and to_date(?,'YYYY-MM-DD')");
        }

        int row[] = {0};
        jdbcTemplate.query(queryString.toString(),
                new PreparedStatementSetter() {

                    @Override
                    public void setValues(PreparedStatement preparedStatement) throws SQLException {

                        int count =0;
                        if(null != ngayChuyen){
                            count++;
                            preparedStatement.setString(count, "%"+ngayChuyen+"%");
                        }
                        if(null != nguoiNhan){
                            count++;
                            preparedStatement.setString(count, "%"+nguoiNhan+"%");
                        }
                        if(null != nguoiGui){
                            count++;
                            preparedStatement.setString(count, "%"+nguoiGui+"%");
                        }
                        if(null != soKyHieu){
                            count++;
                            preparedStatement.setString(count, "%"+soKyHieu+"%");
                        }
                        if(null != trichYeu){
                            count++;
                            preparedStatement.setString(count, "%"+trichYeu+"%");
                        }
                        if(null != ngayKy){
                            count++;
                            preparedStatement.setString(count, "%"+ngayKy+"%");
                        }
                        if(null != nguoiKy){
                            count++;
                            preparedStatement.setString(count, "%"+nguoiKy+"%");
                        }
                        if(null != fromDate && null != toDate  ){
                            count++;
                            preparedStatement.setString(count,fromDate);
                            count++;
                            preparedStatement.setString(count,toDate);
                        }

                    }
                }
                , new RowMapper<LuanChuyen>() {
                    @Override
                    public LuanChuyen mapRow(ResultSet resultSet, int i) throws SQLException {

                        row[0] = resultSet.getInt(1);
                        return null;
                    }
                } );
        return row[0];
    }

    @Override
    public LuanChuyen findLuanChuyenById(int id) {
        try {
            String queryString = "select * from luan_chuyen where da_xoa = 0 and id = ?1";
            TypedQuery<LuanChuyen> query = (TypedQuery<LuanChuyen>) entityManager.createNativeQuery(queryString, LuanChuyen.class);
            query.setParameter(1, id);
            LuanChuyen luanChuyen = query.getSingleResult();
            return luanChuyen;
        } catch (NoResultException ex){
            return null;
        }

    }




    @Override
    public List<SearchResponse> findLuanChuyenOfVanBanDen(int id) {
        String queryString = "Select vanbanden.trich_yeu, vanbanden.so_ky_hieu, vanbanden.ngay_ky,\n" +
                "vanbanden.nguoi_ky, nguoinhan.ten as nguoi_nhan, nguoigui.ten as nguoi_gui, \n" +
                "luanchuyen.ngay_chuyen\n" +
                "From van_ban_den as vanbanden\n" +
                "Inner Join luan_chuyen as luanchuyen on vanbanden.id = luanchuyen.van_ban_den_id\n" +
                "Inner Join nguoi_dung as nguoinhan on nguoinhan.id = luanchuyen.nguoi_nhan_id\n" +
                "Inner Join nguoi_dung as nguoigui on nguoigui.id = luanchuyen.nguoi_gui_id\n" +
                "Where vanbanden.id=?  and vanbanden.da_xoa=0 and luanchuyen.da_xoa=0";
        return jdbcTemplate.query(queryString,
                new PreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement preparedStatement) throws SQLException {
                        preparedStatement.setInt(1, id);
                    }
                }, new RowMapper<SearchResponse>() {
                    @Override
                    public SearchResponse mapRow(ResultSet resultSet, int i) throws SQLException {
                        SearchResponse searchResponse = new SearchResponse();
                        searchResponse.setTrichYeu(resultSet.getString("trich_yeu"));
                        searchResponse.setSoKyHieu(resultSet.getString("so_ky_hieu"));
                        searchResponse.setNgayKy(resultSet.getDate("ngay_ky"));
                        searchResponse.setNguoiKy(resultSet.getString("nguoi_ky"));
                        searchResponse.setNguoiNhan(resultSet.getString("nguoi_nhan"));
                        searchResponse.setNguoiGui(resultSet.getString("nguoi_gui"));
                        searchResponse.setNgayChuyen(resultSet.getDate("ngay_chuyen"));
                        return searchResponse;
                    }
                } );
    }

    @Override
    public List<ThongKeResponse> thongKe() {
        String queryString = "Select count(vanbanden.id) as soluongvanbanden, nguoi_dung.ten\n" +
                "From van_ban_den as vanbanden\n" +
                "Inner Join luan_chuyen as luanchuyen on vanbanden.id = luanchuyen.van_ban_den_id\n" +
                "Inner Join nguoi_dung on nguoi_dung.id = luanchuyen.nguoi_nhan_id\n" +
                "Where vanbanden.da_xoa=0 and luanchuyen.da_xoa=0 and nguoi_dung.da_xoa = 0\n" +
                "Group By nguoi_dung.ten ";
        return jdbcTemplate.query(queryString
                , new RowMapper<ThongKeResponse>() {
                    @Override
                    public ThongKeResponse mapRow(ResultSet resultSet, int i) throws SQLException {
                        ThongKeResponse thongKeResponse = new ThongKeResponse();
                        thongKeResponse.setTenNguoiNhan(resultSet.getString("ten"));
                        thongKeResponse.setSoLuongVanBanDen(resultSet.getInt("soluongvanbanden"));
                        return thongKeResponse;
                    }
                } );
    }


    @Override
    public void insertLuanChuyen(LuanChuyen luanChuyen) {
        String queryString = "insert into luan_chuyen(van_ban_den_id,nguoi_nhan_id,nguoi_gui_id,ngay_chuyen,da_xoa) values(?1,?2,?3,?4,?5)";
        TypedQuery<?> query = (TypedQuery<?>) entityManager.createNativeQuery(queryString);
        query.setParameter(1,luanChuyen.getVanBanDenId());
        query.setParameter(2, luanChuyen.getNguoiNhanId());
        query.setParameter(3,luanChuyen.getNguoiGuiId());
        query.setParameter(4,new Date());
        query.setParameter(5,luanChuyen.getDaXoa());
        query.executeUpdate();
    }

    @Override
    public void updateLuanChuyen(LuanChuyen luanChuyen) {
        String queryString ="update luan_chuyen set van_ban_den_id = ?1, nguoi_nhan_id=?2, nguoi_gui_id=?3, ngay_chuyen=?4 where id= ?5 ";
        TypedQuery<?> query = (TypedQuery<?>) entityManager.createNativeQuery(queryString);
        query.setParameter(1,luanChuyen.getVanBanDenId());
        query.setParameter(2, luanChuyen.getNguoiNhanId());
        query.setParameter(3,luanChuyen.getNguoiGuiId());
        query.setParameter(4,luanChuyen.getNgayChuyen());
        query.setParameter(5,luanChuyen.getId());
        query.executeUpdate();
    }

    @Override
    public void deleteLuanChuyen(int id) {
        String queryString ="update luan_chuyen set da_xoa=?1  where id= ?2 ";
        TypedQuery<?> query = (TypedQuery<?>) entityManager.createNativeQuery(queryString);
        query.setParameter(1, 1);
        query.setParameter(2, id);
        query.executeUpdate();
    }

    @Override
    public List<LuanChuyen> findAllLuanChuyen(int offset, int limit) {
        String queryString = "select * from luan_chuyen where da_xoa=0 order by id limit ? offset ?";
        TypedQuery<LuanChuyen> query = (TypedQuery<LuanChuyen>) entityManager.createNativeQuery(queryString,LuanChuyen.class);
        query.setParameter(1,limit);
        query.setParameter(2,offset);
        List<LuanChuyen> luanChuyenList = query.getResultList();
        return luanChuyenList;
    }
}
