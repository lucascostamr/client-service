package com.packetdelivery.clientservice;

public class InvalidParamException extends Exception {
    public InvalidParamException(String param) {
        super("Invalid Param: " + param);
    }
}
