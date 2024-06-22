package com.packetdelivery.clientservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ClientRepository implements IAddClientRepository, IUpdateClientRepository {
    private IJpaRepository repository;

    @Autowired
    public ClientRepository(IJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public ClientModel add(AddClientModel client) throws Exception, EmailException, CnpjException {
        ClientModel isClient;
        isClient = repository.findByEmail(client.getEmail());
        if (isClient != null) {
            throw new EmailException();
        }
        isClient = repository.findByCnpj(client.getCnpj());
        if (isClient != null) {
            throw new CnpjException();
        }
        ClientModel clientModel = Mapper.addClientModelToClientModel(client);
        ClientModel clientData = repository.save(clientModel);
        return clientData;
    }

    @Override
    public void update(ClientModel newClient) throws Exception {
        try {
            ClientModel currentClient = repository.getOne(newClient.getUUID());
            currentClient.setName(newClient.getName());
            currentClient.setEmail(newClient.getEmail());
            currentClient.setCnpj(newClient.getCnpj());
            currentClient.setPhone(newClient.getPhone());
            repository.save(currentClient);
        } catch (Exception e) {
            throw new NotFoundException("No client found");
        }

    }
}