package com.example.baitap1.repository.impl;

import com.example.baitap1.model.dto.SearchMultiParamResponse;
import com.example.baitap1.repository.ISearchMultiParamsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SearchMultiParamsRepositoryImpl implements ISearchMultiParamsRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<SearchMultiParamResponse> searchByMultiParam(String trichYeu, String soKyHieu, String ngayKy, String nguoiKy, String nguoiNhan, String nguoiGui, String ngayChuyen) {
        String queryString = "Select vanbanden.trich_yeu, vanbanden.so_ky_hieu, vanbanden.ngay_ky,\n" +
                "vanbanden.nguoi_ky, nguoinhan.ten as nguoi_nhan, nguoigui.ten as nguoi_gui, \n" +
                "luanchuyen.ngay_chuyen\n" +
                "From van_ban_den as vanbanden\n" +
                "Inner Join luan_chuyen as luanchuyen on vanbanden.id = luanchuyen.van_ban_den_id\n" +
                "Inner Join nguoi_dung as nguoinhan on nguoinhan.id = luanchuyen.nguoi_nhan_id\n" +
                "Inner Join nguoi_dung as nguoigui on nguoigui.id = luanchuyen.nguoi_gui_id\n" +
                "Where vanbanden.da_xoa = 0 \n";

        if(ngayChuyen!=null){
            queryString+=    "AND luanchuyen.ngay_chuyen::text like :ngayChuyen \n ";
            System.out.println("******** ngayChuyen != null *************");
        }
        if(nguoiNhan!=null){
            queryString+=   "AND nguoinhan.ten like ? \n ";
            System.out.println("******** nguoiNhan != null *************");
        }
        if(nguoiGui!=null){
            queryString+=      "AND nguoigui.ten like ? \n";
            System.out.println("******** nguoiGui != null *************");
        }
        if(soKyHieu!=null){
            queryString+=    "AND vanbanden.so_ky_hieu like ?\n";
            System.out.println("******** soKyHieu != null *************");
        }
        if(trichYeu!=null){
            queryString+=     "AND vanbanden.trich_yeu like ? \n";
            System.out.println("******** trichYeu != null *************");
        }
        if(ngayKy!=null){
            queryString+=   "AND vanbanden.ngay_ky::text like ? \n";
            System.out.println("******** ngayKy != null *************");
        }
        if(nguoiKy!=null){
            queryString+=   "AND vanbanden.nguoi_ky like ?";
            System.out.println("******** nguoiKy != null *************");
        }


        return jdbcTemplate.query(queryString,
                new PreparedStatementSetter() {

                    @Override
                    public void setValues(PreparedStatement preparedStatement) throws SQLException {

                    int count =0;
                        if(ngayChuyen!=null){
                            count++;
                            preparedStatement.setString(count, "%"+ngayChuyen+"%");
                        }
                        if(nguoiNhan!=null){
                            count++;
                            preparedStatement.setString(count, "%"+nguoiNhan+"%");
                        }
                        if(nguoiGui!=null){
                            count++;
                            preparedStatement.setString(count, "%"+nguoiGui+"%");
                        }
                        if(soKyHieu!=null){
                            count++;
                            preparedStatement.setString(count, "%"+soKyHieu+"%");
                        }
                        if(trichYeu!=null){
                            count++;
                            preparedStatement.setString(count, "%"+trichYeu+"%");
                        }
                        if(ngayKy!=null){
                            count++;
                            preparedStatement.setString(count, "%"+ngayKy+"%");
                        }
                        if(nguoiKy!=null){
                            count++;
                            preparedStatement.setString(count, "%"+nguoiKy+"%");
                        }
                    }
                }
                , new RowMapper<SearchMultiParamResponse>() {
                    @Override
                    public SearchMultiParamResponse mapRow(ResultSet resultSet, int i) throws SQLException {
                        SearchMultiParamResponse searchMultiParamResponse = new SearchMultiParamResponse();
                        searchMultiParamResponse.setTrichYeu(resultSet.getString("trich_yeu"));
                        searchMultiParamResponse.setSoKyHieu(resultSet.getString("so_ky_hieu"));
                        searchMultiParamResponse.setNgayKy(resultSet.getString("ngay_ky"));
                        searchMultiParamResponse.setNguoiKy(resultSet.getString("nguoi_ky"));
                        searchMultiParamResponse.setNguoiNhan(resultSet.getString("nguoi_nhan"));
                        searchMultiParamResponse.setNguoiGui(resultSet.getString("nguoi_gui"));
                        searchMultiParamResponse.setNgayChuyen(resultSet.getString("ngay_chuyen"));
                        return searchMultiParamResponse;
                    }
                } );
    }
}
