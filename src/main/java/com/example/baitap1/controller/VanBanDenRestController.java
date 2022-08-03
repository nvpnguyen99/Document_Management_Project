package com.example.baitap1.controller;

import com.example.baitap1.entity.ResponseObject;
import com.example.baitap1.entity.VanBanDen;
import com.example.baitap1.exception.NotFoundException;
import com.example.baitap1.model.dto.SearchMultiParamRequest;
import com.example.baitap1.model.dto.VanBanDenRequest;
import com.example.baitap1.service.IVanBanDenService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/van-ban-den")
public class VanBanDenRestController {

    @Lazy
    @Autowired
    private IVanBanDenService iVanBanDenService;

    @GetMapping(value = "")
    public ResponseEntity<?> findVanBanDen(@RequestBody SearchMultiParamRequest searchMultiParamRequest) throws NotFoundException {
        List<VanBanDen> vanBanDenList= iVanBanDenService.findAllVanBanDen(0,5, searchMultiParamRequest);
        ResponseObject<?> responseObject = new ResponseObject(true, "find successfully", vanBanDenList);
        return new ResponseEntity(responseObject, HttpStatus.OK);
    }

    @GetMapping(value = "/find")
    public ResponseEntity<?> findVanBanDenById(@RequestBody Map<String,Integer> idMap) throws NotFoundException {
        VanBanDen vanBanDen = iVanBanDenService.findVanBanDenById(idMap.get("id"));
        ResponseObject<VanBanDen> responseObject = new ResponseObject(true,"find successfully",vanBanDen);
        return new ResponseEntity(responseObject,HttpStatus.OK);
    }

    @GetMapping(value = "/{keyword}")
    public ResponseEntity<?> getVanBanDenByCri(@PathVariable String keyword) throws NotFoundException {
        List<VanBanDen> vanBanDenList = iVanBanDenService.getVanBanDenByCri(keyword);
        ResponseObject<VanBanDen> responseObject = new ResponseObject(true,"find successfully",vanBanDenList);
        return ResponseEntity.status(HttpStatus.OK).body(responseObject);

    }

    @PostMapping(value = "/create")
    public ResponseEntity<?> insertVanBanDen(@Valid @RequestBody VanBanDenRequest vanBanDenRequest){
            VanBanDen vanBanDen = new VanBanDen();
            BeanUtils.copyProperties(vanBanDenRequest, vanBanDen);
            iVanBanDenService.insertVanBanDen(vanBanDen);
            ResponseObject<?> responseObject = new ResponseObject(true,"insert successfully");
            return ResponseEntity.status(HttpStatus.CREATED).body(responseObject);
    }

    @PostMapping(value="/update")
    public ResponseEntity<?> updateVanBanDen (@Valid @RequestBody VanBanDenRequest vanBanDenRequest) throws NotFoundException {
       VanBanDen vanBanDen = new VanBanDen();
        BeanUtils.copyProperties(vanBanDenRequest,vanBanDen);
        iVanBanDenService.updateVanBanDen(vanBanDen);
        ResponseObject<?> responseObject = new ResponseObject(true,"update successfully");
        return new ResponseEntity(responseObject,HttpStatus.OK);
    }

    @PostMapping(value = "/delete")
    public ResponseEntity<?> deleteVanBanDen (@RequestBody Map<String, Integer> idMap) throws NotFoundException {
        Map<String,String> obj = iVanBanDenService.deleteVanBanDen(idMap.get("id"));
        ResponseObject<?> responseObject = new ResponseObject(true,"delete successfully");
        return new ResponseEntity(responseObject,HttpStatus.OK);
    }
}
