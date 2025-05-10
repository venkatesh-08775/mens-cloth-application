package com.mens.cloth.mens_cloth.common.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class CurrentUserDetail {

    public  static String getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Object principle = authentication.getPrincipal();
        if(principle instanceof UserDetails){
            return ((UserDetails)principle).getUsername();
        }
        return principle.toString();
    }
}
