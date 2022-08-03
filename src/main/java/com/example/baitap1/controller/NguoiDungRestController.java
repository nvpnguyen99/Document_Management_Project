package com.example.baitap1.controller;

import com.example.baitap1.entity.NguoiDung;
import com.example.baitap1.entity.ResponseObject;
import com.example.baitap1.exception.NotFoundException;
import com.example.baitap1.model.dto.NguoiDungRequest;
import com.example.baitap1.service.INguoiDungService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/nguoi-dung")
public class NguoiDungRestController {

    @Lazy
    @Autowired
    private INguoiDungService iNguoiDungService;

    @GetMapping(value = "")
    public ResponseEntity<?> findAllNguoiDung () throws NotFoundException {
        List<NguoiDung> nguoiDungList = iNguoiDungService.findAllNguoiDung();
        ResponseObject<NguoiDung> responseObject = new ResponseObject(true,"find successfully",nguoiDungList);
        return new ResponseEntity(responseObject, HttpStatus.OK);
    }

    @GetMapping(value = "/find")
    public ResponseEntity<?> findNguoiDungById (@RequestBody Map<String,Integer> idMap) throws NotFoundException {
        NguoiDung nguoiDung = iNguoiDungService.findNguoiDungById(idMap.get("id"));
        ResponseObject<NguoiDung> responseObject = new ResponseObject(true,"find successfully",nguoiDung);
        return new ResponseEntity(responseObject, HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<?> insertNguoiDung (@Valid @RequestBody NguoiDungRequest nguoiDungRequest) {
        NguoiDung nguoiDung = new NguoiDung();
        BeanUtils.copyProperties(nguoiDungRequest,nguoiDung);
        iNguoiDungService.insertNguoiDung(nguoiDung);
        ResponseObject<?> responseObject = new ResponseObject(true,"insert successfully");
        return new ResponseEntity(responseObject,HttpStatus.OK);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<?> updateNguoiDung (@Valid @RequestBody NguoiDungRequest nguoiDungRequest) throws NotFoundException {
        NguoiDung nguoiDung = new NguoiDung();
        BeanUtils.copyProperties(nguoiDungRequest,nguoiDung);
        iNguoiDungService.updateNguoiDung(nguoiDung);
        ResponseObject<?> responseObject = new ResponseObject(true,"update successfully");
        return new ResponseEntity(responseObject,HttpStatus.OK);
    }

    @PostMapping(value = "/delete")
    public ResponseEntity<?> deleteNguoiDung (@RequestBody Map<String, Integer> idMap) throws NotFoundException {
        iNguoiDungService.deleteNguoiDung(idMap.get("id"));
        ResponseObject<?> responseObject = new ResponseObject(true,"delete successfully");
        return new ResponseEntity(responseObject,HttpStatus.OK);
    }
}
