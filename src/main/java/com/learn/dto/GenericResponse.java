package com.learn.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

public class GenericResponse<T> implements Serializable {
    private String status;
    private int code;
    private String message;
    private T data;

    @JsonIgnore
    public GenericResponse<T> successResponse() {
        GenericResponse<T> data = new GenericResponse();
        data.setStatus("S");
        data.setCode(201);
        data.setMessage("Process Successed");
        return data;
    }

    @JsonIgnore
    public GenericResponse<T> successResponse(T t) {
        GenericResponse<T> data = new GenericResponse();
        data.setStatus("S");
        data.setCode(201);
        data.setData(t);
        data.setMessage("Process Successed");
        return data;
    }

    @JsonIgnore
    public GenericResponse<T> noDataFoundResponse(T t) {
        GenericResponse<T> data = new GenericResponse();
        data.setStatus("S");
        data.setCode(204);
        data.setData(t);
        data.setMessage("No Data Found");
        return data;
    }

    @JsonIgnore
    public GenericResponse<T> noDataFoundResponse() {
        GenericResponse<T> data = new GenericResponse();
        data.setStatus("S");
        data.setCode(204);
        data.setMessage("No Data Found");
        return data;
    }

    @JsonIgnore
    public GenericResponse<T> errorResponse(int code, String message) {
        GenericResponse<T> data = new GenericResponse();
        data.setStatus("F");
        data.setCode(code);
        data.setMessage(message);
        return data;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getStatus() {
        return this.status;
    }


    public String getMessage() {
        return this.message;
    }

    public T getData() {
        return this.data;
    }

    public GenericResponse() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public GenericResponse(String status, int code, String message, T data) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
