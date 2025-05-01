package com.mens.cloth.mens_cloth.common.error;

public class DuplicateRecord extends  RuntimeException{

    public DuplicateRecord(String message){

        super(message);
        System.out.println("called");
    }
}
