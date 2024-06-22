package com.packetdelivery.clientservice;

public interface IAddClientRepository {
    public ClientModel add(AddClientModel client) throws Exception, EmailException, CnpjException;
}
