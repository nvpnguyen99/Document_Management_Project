package com.example.baitap1.service;

import com.example.baitap1.exception.NotFoundException;
import com.example.baitap1.model.dto.SearchMultiParamRequest;
import net.sf.jasperreports.engine.JRException;

import java.io.FileNotFoundException;

public interface IReportService {

    byte[] exportReport( ) throws FileNotFoundException, JRException, NotFoundException;
}
