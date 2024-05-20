package com.packetdelivery.clientservice;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

@Repository
public class DbAddClientRepository implements IAddClient {
    private final IAddClientRepository addClientRepository;

    @Autowired
    public DbAddClientRepository(IAddClientRepository addClientRepository) {
        this.addClientRepository = addClientRepository;
    }

    @Override
    public ClientModel add(AddClientModel client) throws Exception {
        return this.addClientRepository.add(client);
    }
}
