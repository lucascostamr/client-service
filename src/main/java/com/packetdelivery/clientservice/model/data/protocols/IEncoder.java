package com.packetdelivery.clientservice;

public interface IEncoder {
    public String encode(String value);

    public String decode(String token) throws InvalidParamException;
}
