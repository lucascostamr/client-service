package com.packetdelivery.clientservice;

import org.springframework.stereotype.Repository;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

@Repository
public class DbClientRepository implements IAddClient, IUpdateClient, IRemoveClient {
    private IAddClientRepository addClientRepository;
    private IUpdateClientRepository updateClientRepository;
    private IRemoveClientRepository removeClientRepository;
    private IEncoder encoder;

    @Autowired
    public DbClientRepository(IAddClientRepository addClientRepository, IUpdateClientRepository updateClientRepository,
            IEncoder encoder, IRemoveClientRepository removeClientRepository) {
        this.addClientRepository = addClientRepository;
        this.updateClientRepository = updateClientRepository;
        this.removeClientRepository = removeClientRepository;
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

    @Override
    public void remove(String token) throws Exception {
        String clientDecodedId = encoder.decode(token);
        UUID clientId = UUID.fromString(clientDecodedId);
        removeClientRepository.remove(clientId);
    }
}
