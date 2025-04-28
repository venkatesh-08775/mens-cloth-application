package com.mens.cloth.mens_cloth.common.ApiResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class APIResponse<T> {

    private Boolean status;
    private T data;
    private String error;


    public static <T> APIResponse<T> success(){
        APIResponse<T> response = new APIResponse<>();
        response.setStatus(true);
        return response;
    }

    public static <T> APIResponse<T> success(T data){
        APIResponse<T> response = new APIResponse<T>();
        response.setStatus(true);
        response.setData(data);

        return response;
    }



    public static  <T>APIResponse<T> failure(String error){
        APIResponse<T> response = new APIResponse<T>();
        response.setStatus(false);
        response.setError(error);
        return response;
    }
}
