package com.packetdelivery.clientservice;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

@Repository
public class DbClientRepository implements IAddClient, IUpdateClient {
    private IAddClientRepository addClientRepository;
    private IUpdateClientRepository updateClientRepository;
    private IEncoder encoder;

    @Autowired
    public DbClientRepository(IAddClientRepository addClientRepository, IUpdateClientRepository updateClientRepository,
            IEncoder encoder) {
        this.addClientRepository = addClientRepository;
        this.updateClientRepository = updateClientRepository;
        this.encoder = encoder;
    }

    @Override
    public String add(AddClientModel client) throws Exception {
        ClientModel clientData = this.addClientRepository.add(client);
        String token = encoder.encode(clientData.getId());
        return token;
    }

    @Override
    public void update(UpdateClientModel client) throws Exception {
        String clientDecodedId = encoder.decode(client.getToken());
        client.setToken(clientDecodedId);
        ClientModel clientModel = Mapper.updateClientModelToClientModel(client);
        updateClientRepository.update(clientModel);
    }
}
