package com.mens.cloth.mens_cloth.common.exception;

import com.mens.cloth.mens_cloth.common.ApiResponse.APIResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.rmi.AlreadyBoundException;


@ControllerAdvice
public class GlobalException {


    @ExceptionHandler(Exception.class)
    public APIResponse<?> handleException(Exception e){
        return APIResponse.failure(e.getLocalizedMessage());
    }

//    @ExceptionHandler(.class)
}
