package com.learn.controller;

import com.learn.dto.*;
import com.learn.service.StockService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import static com.learn.common.Validated.*;

@RestController
@RequestMapping("/api/v1/stock")
public class StockController {
    @Autowired
    StockService ss;

    @PostMapping("/create")
    public GenericResponse createStock(@RequestBody SaveRequest req,@RequestHeader("userId")String userId){
        if (createCheck(req)) {
            return new GenericResponse<>().errorResponse(204,"Payload tidak boleh kosong");
        }
        return ss.create(req,userId);
    }

    @GetMapping("/list")
    public GenericResponse listStock(@RequestHeader("userId")String userId){
        System.out.println("userId "+userId);
        return ss.list(userId);
    }

    @GetMapping("/detail/{id}")
    public GenericResponse detailStock(@PathVariable("id") Integer id,@RequestHeader("userId")String userId){
        System.out.println("userId "+userId);
        return ss.detail(id,userId);
    }

    @PutMapping("/update")
    public GenericResponse updateStock(@RequestBody SaveRequest req,@RequestHeader("userId")String userId) {
        System.out.println("userId "+userId);
        if (updateCheck(req)) {
            return new GenericResponse<>().errorResponse(204, "Payload tidak boleh kosong");
        }
        return ss.update(req,userId);
    }

    @DeleteMapping("/delete/{id}")
    public GenericResponse deleteStock(@PathVariable("id") Integer id,@RequestHeader("userId")String userId){
        System.out.println("userId "+userId);
        return ss.delete(id,userId);
    }
}
