package com.mens.cloth.mens_cloth.common.exception;

import com.mens.cloth.mens_cloth.common.ApiResponse.APIResponse;
import com.mens.cloth.mens_cloth.common.error.DuplicateRecord;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class GlobalException {


    @ExceptionHandler(DuplicateRecord.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public APIResponse<?> handleException(DuplicateRecord e){
        return APIResponse.failure(e.getMessage());
    }


    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public APIResponse<?> handleNotFound(EntityNotFoundException e){
        return APIResponse.failure(e.getMessage());
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public APIResponse<?> handleBadCredential(BadCredentialsException e){
        return APIResponse.failure(e.getMessage());
    }

}
