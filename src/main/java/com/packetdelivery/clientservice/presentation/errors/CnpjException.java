package com.packetdelivery.clientservice;

public class CnpjException extends Exception {
    public CnpjException() {
        super("Cnpj already in use");
    }
}
