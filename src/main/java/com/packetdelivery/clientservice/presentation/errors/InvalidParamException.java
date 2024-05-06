package com.packetdelivery.clientservice.presentation.errors;

public class InvalidParamException extends Exception{
    public InvalidParamException(String param){
        super("Invalid Param: " + param);
    }
}
