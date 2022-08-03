package com.example.baitap1.controller;

import com.example.baitap1.entity.LuanChuyen;
import com.example.baitap1.entity.ResponseObject;
import com.example.baitap1.exception.NotFoundException;
import com.example.baitap1.model.dto.*;
import com.example.baitap1.service.ILuanChuyenService;
import com.example.baitap1.service.IReportService;
import com.example.baitap1.service.ISearchService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/luan-chuyen")
public class LuanChuyenRestController {

    @Lazy
    @Autowired
    private ILuanChuyenService iLuanChuyenService;

    @Lazy
    @Autowired
    private ISearchService iSearchService;

    @Lazy
    @Autowired
    private IReportService iReportService;

    @GetMapping(value = "")
    public ResponseEntity<?> findLuanChuyen (@RequestParam(required = false,defaultValue = "0")int page,@Valid @RequestBody SearchMultiParamRequest searchMultiParamRequest) throws NotFoundException {
        int offset = page * 5;
        List<LuanChuyen> luanChuyenList = iLuanChuyenService.findLuanChuyen(offset, 5,searchMultiParamRequest.getTrichYeu(), searchMultiParamRequest.getSoKyHieu(), searchMultiParamRequest.getNgayKy(),searchMultiParamRequest.getNguoiKy(), searchMultiParamRequest.getNguoiNhan(), searchMultiParamRequest.getNguoiGui(), searchMultiParamRequest.getNgayChuyen(), searchMultiParamRequest.getFromDate(),searchMultiParamRequest.getToDate());
        PageResponse pageResponse = iLuanChuyenService.calculateNumOfPage(iLuanChuyenService.countAllLuanChuyen(searchMultiParamRequest),5,page);
        ResponseObject<?> responseObject = new ResponseObject(true,"find successfully",luanChuyenList,pageResponse);
        return new ResponseEntity(responseObject,HttpStatus.OK);
    }
    @GetMapping(value = "/find")
    public ResponseEntity<?> findLuanChuyenById (@RequestBody Map<String, Integer> idMap) throws NotFoundException {
        LuanChuyen luanChuyen = iLuanChuyenService.findLuanChuyenById(idMap.get("id"));
        ResponseObject<LuanChuyen> responseObject = new ResponseObject(true,"find successfully", luanChuyen);
        return new ResponseEntity(responseObject,HttpStatus.OK);
    }

    @GetMapping(value = "/find-of-van-ban-den")
    public ResponseEntity<?> findLuanChuyenOfVanBanDen (@RequestBody Map<String, Integer> idMap) throws NotFoundException {
        List<SearchResponse> searchResponseList = iLuanChuyenService.findLuanChuyenOfVanBanDen(idMap.get("id"));
        ResponseObject<SearchResponse> responseObject = new ResponseObject(true,"find successfully", searchResponseList);
        return new ResponseEntity(responseObject,HttpStatus.OK);
    }

    @GetMapping(value = "/statistics")
    public ResponseEntity<?> thongKe () throws NotFoundException {
        List<ThongKeResponse> thongKeResponses = iLuanChuyenService.thongKe();
        ResponseObject<ThongKeResponse> responseObject = new ResponseObject(true,"search successfully", thongKeResponses);
        return new ResponseEntity(responseObject,HttpStatus.OK);
    }

    @PostMapping(value="/search")
    public ResponseEntity<?> searchByMultiParams(@RequestBody SearchMultiParamRequest searchMultiParamRequest) throws NotFoundException {
        List<SearchMultiParamResponse> searchResponseList = iSearchService.SearchByMultipleParam(searchMultiParamRequest.getTrichYeu(), searchMultiParamRequest.getSoKyHieu(), searchMultiParamRequest.getNgayKy(), searchMultiParamRequest.getNguoiKy(), searchMultiParamRequest.getNguoiNhan(), searchMultiParamRequest.getNguoiGui(), searchMultiParamRequest.getNgayChuyen());
        ResponseObject<SearchMultiParamResponse> responseObject = new ResponseObject(true,"search successfully",searchResponseList);
        return new ResponseEntity(responseObject,HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<?> insertLuanChuyen(@Valid @RequestBody LuanChuyenRequest luanChuyenRequest) throws NotFoundException {
        LuanChuyen luanChuyen = new LuanChuyen();
        BeanUtils.copyProperties(luanChuyenRequest,luanChuyen);
        iLuanChuyenService.insertLuanChuyen(luanChuyen);
        ResponseObject<?> responseObject = new ResponseObject(true,"insert successfully");
        return new ResponseEntity(responseObject,HttpStatus.OK);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<?> updateLuanChuyen(@Valid @RequestBody LuanChuyenRequest luanChuyenRequest) throws NotFoundException {
        LuanChuyen luanChuyen = new LuanChuyen();
        BeanUtils.copyProperties(luanChuyenRequest,luanChuyen);
        iLuanChuyenService.updateLuanChuyen(luanChuyen);
        ResponseObject<?> responseObject = new ResponseObject(true,"update successfully");
        return new ResponseEntity(responseObject,HttpStatus.OK);
    }

    @PostMapping(value = "/delete")
    public ResponseEntity<?> deleteVanBanDen (@RequestBody Map<String, Integer> idMap) throws NotFoundException {
        iLuanChuyenService.deleteLuanChuyen(idMap.get("id"));
        ResponseObject<?> responseObject = new ResponseObject(true,"delete successfully");
        return new ResponseEntity(responseObject,HttpStatus.OK);
    }


    @GetMapping(value = "/report/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> generateReport () throws FileNotFoundException, JRException, NotFoundException {

       return ResponseEntity.ok().body(iReportService.exportReport());
    }

    @PostMapping(value = "/clear-cache")
    public ResponseEntity<?> clearCache () {
    iLuanChuyenService.clearCache();
        return new ResponseEntity(HttpStatus.OK);
    }
    @Scheduled(fixedDelay = 1000*60*5)
    public void clearAllCache() {
        iLuanChuyenService.clearCache();
        System.out.println("Clear all Cache");
    }

}
