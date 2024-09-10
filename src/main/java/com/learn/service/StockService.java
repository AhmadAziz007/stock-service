package com.learn.service;

import com.learn.domain.StockDomain;
import com.learn.dto.*;
import com.learn.repository.StockRepository;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.apache.logging.log4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.learn.common.Validated.*;

@Service
public class StockService {
    @Autowired
    StockRepository sr;

    private Logger log = LogManager.getLogger(StockService.class);

    public GenericResponse create(SaveRequest req, String userId){
        startLog(JsonObject.mapFrom(req).encodePrettily(),"create",userId);
        StockDomain checkName = sr.findByNameBarang(req.getNamaBarang().toLowerCase(Locale.ROOT));
        if (checkName == null) {
            StockDomain newData = new StockDomain();
            newData.setNamaBarang(req.getNamaBarang());
            newData.setJumlahStockBarang(req.getJumlahStockBarang());
            newData.setNomorSeriBarang(req.getNomorSeriBarang());
            newData.setAdditionalInfo(JsonObject.mapFrom(req.getAdditionalInfo()).encode());
            newData.setGambarBarang(req.getGambarBarang());
            newData.setCreatedAt(new Date());
            newData.setCreatedBy(userId);

            StockDomain data = sr.save(newData);

            endLog(JsonObject.mapFrom(data).encodePrettily(),"create",userId);
            return new GenericResponse<>().successResponse(data.getStockId());
        }
        endLog(JsonObject.mapFrom(req).encodePrettily(),"create",userId);
        return new GenericResponse<>().noDataFoundResponse(req.getNamaBarang());
    }

    public GenericResponse list(String userId){
        startLog(null,"list",userId);
        List<StockDomain> listStock = sr.findAll();
        endLog(new JsonArray(listStock).encodePrettily(),"list",userId);
        return new GenericResponse<>().successResponse(listStock);
    }

    public GenericResponse detail(Integer id, String userId){
        startLog(id.toString(),"detail",userId);
        StockDomain stock = sr.findByStockId(id);
        if (stock == null) {
            endLog(id.toString(),"detail",userId);
            return new GenericResponse<>().noDataFoundResponse(id);
        }
        endLog(JsonObject.mapFrom(stock).encodePrettily(),"detail",userId);
        return new GenericResponse<>().successResponse(stock);
    }

    public GenericResponse update(SaveRequest req, String userId){
        startLog(JsonObject.mapFrom(req).encodePrettily(),"update",userId);
        StockDomain checkId = sr.findByStockId(req.getStockId());
        if (checkId != null) {
            if (strIsNulls(req.getNamaBarang())) {
                checkId.setNamaBarang(checkId.getNamaBarang());
            } else {
                checkId.setNamaBarang(req.getNamaBarang());
            }

            if (strIsNulls(req.getNomorSeriBarang())) {
                checkId.setNomorSeriBarang(checkId.getNomorSeriBarang());
            } else {
                checkId.setNomorSeriBarang(req.getNomorSeriBarang());
            }

            if (req.getAdditionalInfo() == null) {
                checkId.setAdditionalInfo(checkId.getAdditionalInfo());
            } else {
                checkId.setAdditionalInfo(JsonObject.mapFrom(req.getAdditionalInfo()).encode());
            }

            if (strIsNulls(req.getGambarBarang())) {
                checkId.setGambarBarang(checkId.getGambarBarang());
            } else {
                checkId.setGambarBarang(req.getGambarBarang());
            }

            checkId.setUpdatedAt(new Date());
            checkId.setUpdatedBy(userId);

            StockDomain data = sr.save(checkId);

            endLog(JsonObject.mapFrom(data).encodePrettily(),"create",userId);
            return new GenericResponse<>().successResponse(data.getStockId());
        }
        endLog(JsonObject.mapFrom(req).encodePrettily(),"create",userId);
        return new GenericResponse<>().errorResponse(204,"Data dengan StockId = "+req.getStockId()+" tidak bisa diupdate");
    }

    public GenericResponse delete(Integer id,String userId){
        startLog(id.toString(),"delete",userId);
        StockDomain stock = sr.findByStockId(id);
        if (stock == null) {
            endLog(id.toString(),"delete",userId);
            return new GenericResponse<>().noDataFoundResponse(id);
        }
        sr.delete(stock);
        endLog(JsonObject.mapFrom(stock).encodePrettily(),"delete",userId);
        return new GenericResponse<>().successResponse(id);
    }

    private void startLog(String req,String function, String userId){
        log.info(String.format("\n--------------------------------------------------------------\n| Start Service %s Stock by Bank DKI workerId : %s|\n--------------------------------------------------------------\n| Payload = %s\n--------------------------------------------------------------",function,userId,req == null ? "Null" : req));
    }

    private void endLog(String req,String function, String userId){
        log.info(String.format("\n------------------------------------------------------------\n| Payload = %s\n------------------------------------------------------------\n| End Service %s Stock by Bank DKI workerId : %s|\n------------------------------------------------------------",req == null ? "Null" : req,function,userId));

    }
}
