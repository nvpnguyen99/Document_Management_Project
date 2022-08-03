package com.example.baitap1.service.impl;

import com.example.baitap1.exception.NotFoundException;
import com.example.baitap1.model.dto.SearchMultiParamResponse;
import com.example.baitap1.model.dto.SearchResponse;
import com.example.baitap1.repository.ISearchMultiParamsRepository;
import com.example.baitap1.service.ISearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SearchServiceImpl implements ISearchService {

    @Autowired
    ISearchMultiParamsRepository iSearchRepository;

    @Override
    public List<SearchMultiParamResponse> SearchByMultipleParam(String trichYeu, String soKyHieu, String ngayKy, String nguoiKy, String nguoiNhan, String nguoiGui, String ngayChuyen) throws NotFoundException {
        List<SearchMultiParamResponse> searchMultiParamResponses = iSearchRepository.searchByMultiParam(trichYeu, soKyHieu, ngayKy, nguoiKy, nguoiNhan, nguoiGui, ngayChuyen);
       if(searchMultiParamResponses.isEmpty()){
           throw new NotFoundException("Not Result Available");
       }
        return searchMultiParamResponses;
    }
}
