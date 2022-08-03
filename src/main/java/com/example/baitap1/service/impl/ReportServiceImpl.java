package com.example.baitap1.service.impl;

import com.example.baitap1.entity.LuanChuyen;
import com.example.baitap1.exception.NotFoundException;
import com.example.baitap1.model.dto.SearchMultiParamRequest;
import com.example.baitap1.repository.ILuanChuyenRepository;
import com.example.baitap1.service.ILuanChuyenService;
import com.example.baitap1.service.IReportService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements IReportService {

    @Autowired
    private ILuanChuyenRepository iLuanChuyenRepository;

    @Override
    public byte[] exportReport() throws FileNotFoundException, JRException, NotFoundException {
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(iLuanChuyenRepository.findAllLuanChuyen(0,5));
        JasperReport compileReport =JasperCompileManager.compileReport(new FileInputStream("src/main/resources/LuanChuyen.jrxml"));
        HashMap<String, Object> map = new HashMap<>();
        JasperPrint report =JasperFillManager.fillReport(compileReport, map, beanCollectionDataSource);
        //JasperExportManager.exportReportToPdfFile(report,"LuanChuyen.pdf");
        byte[] exportReport = JasperExportManager.exportReportToPdf(report) ;
        return exportReport;
    }
}
