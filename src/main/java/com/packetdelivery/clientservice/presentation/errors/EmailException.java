package com.packetdelivery.clientservice;

public class EmailException extends Exception {
    public EmailException() {
        super("Email already in use");
    }
}
