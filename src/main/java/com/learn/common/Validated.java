package com.learn.common;

import com.learn.dto.SaveRequest;

public class Validated {
    public static Boolean createCheck(SaveRequest req){
        if (req.getNamaBarang() == null && req.getJumlahStockBarang() == null && req.getNomorSeriBarang() == null
                && req.getAdditionalInfo() == null && req.getGambarBarang() == null){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public static Boolean updateCheck(SaveRequest req){
        if (req.getStockId() == null && req.getNamaBarang() == null && req.getJumlahStockBarang() == null
                && req.getNomorSeriBarang() == null && req.getAdditionalInfo() == null && req.getGambarBarang() == null){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public static Boolean strIsNulls(String val){
        if (val == null || val.isEmpty() || val.isBlank()){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public static Boolean intIsNulls(Integer val){
        if (val == null || val == 0){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
