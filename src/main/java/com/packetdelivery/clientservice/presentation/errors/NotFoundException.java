package com.packetdelivery.clientservice;

public class NotFoundException extends Exception {
    public NotFoundException(String param) {
        super("Not found: " + param);
    }
}
