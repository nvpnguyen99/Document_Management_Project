package com.example.baitap1.entity;


import java.util.List;
import java.util.Map;


public class ResponseObject<T> {
    private boolean status;
    private String message;
    private List<T> data;
    private T entity ;

    private Map<String,String> errMap;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public ResponseObject() {
    }

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }

    public Map<String, String> getErrMap() {
        return errMap;
    }

    public void setErrMap(Map<String, String> errMap) {
        this.errMap = errMap;
    }

    public ResponseObject(boolean status, String message, List<T> data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
    public ResponseObject(boolean status, String message, T entity) {
        this.status = status;
        this.message = message;
        this.entity = entity;
    }

    public ResponseObject(boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    public ResponseObject(boolean status, String message, Map<String,String> errMap) {
        this.status = status;
        this.message = message;
        this.errMap = errMap;
    }

    public ResponseObject(boolean status, String message, List<T> data, T entity) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.entity = entity;
    }
}
