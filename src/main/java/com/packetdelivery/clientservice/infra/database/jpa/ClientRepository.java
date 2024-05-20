package com.packetdelivery.clientservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ClientRepository implements IAddClientRepository {
    private final IJpaRepository repository;

    @Autowired
    public ClientRepository(IJpaRepository repository) {
        this.repository = repository;
    }

    public ClientModel add(AddClientModel client) throws Exception, EmailException {
        ClientModel isClient = repository.findByEmail(client.getEmail());
        if(isClient != null) {
            throw new EmailException();
        }
        ClientModel clientModel = Mapper.toClientModel(client);
        ClientModel clientData = repository.save(clientModel);
        return clientData;
    }
}