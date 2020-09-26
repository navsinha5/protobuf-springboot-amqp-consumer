package com.assignment.storage.model;

import com.assignment.storage.service.StorageService;

public class StorageException extends RuntimeException {

    private Integer statusCode;

    public StorageException(){
        super("Internal Server Error");
        this.statusCode = 500;
    }

    public StorageException(Integer statusCode, String message){
        super(message);
        this.statusCode = statusCode;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }
}
