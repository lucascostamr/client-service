package com.packetdelivery.clientservice;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ClientRepository implements IAddClientRepository, IUpdateClientRepository, IRemoveClientRepository {
    private IJpaRepository repository;

    @Autowired
    public ClientRepository(IJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public ClientModel add(AddClientModel client) throws Exception, EmailException, CnpjException {
        this.checkEmailInUse(client.getEmail());
        this.checkCnpjInUse(client.getCnpj());
        ClientModel clientModel = Mapper.addClientModelToClientModel(client);
        ClientModel clientData = repository.save(clientModel);
        return clientData;
    }

    @Override
    public void update(ClientModel updatedClient) throws Exception, EmailException, CnpjException {
        ClientModel currentClient = repository.findById(updatedClient.getUUID())
                .orElseThrow(() -> new NotFoundException("No client found"));
        if (!(currentClient.getEmail()).equals(updatedClient.getEmail())) {
            this.checkEmailInUse(updatedClient.getEmail());
        }
        if (!(currentClient.getCnpj()).equals(updatedClient.getCnpj())) {
            this.checkCnpjInUse(updatedClient.getCnpj());
        }
        ClientModel updatedClientModel = Mapper.setValuesToClientModel(currentClient, updatedClient);
        repository.save(updatedClientModel);
    }

    @Override
    public void remove(UUID clientId) throws Exception {
        repository.findById(clientId).orElseThrow(() -> new NotFoundException("No client found"));
        repository.deleteById(clientId);
    }

    private void checkEmailInUse(String email) throws EmailException {
        List<ClientModel> isClient;
        isClient = repository.findByEmail(email);
        if (!isClient.isEmpty()) {
            throw new EmailException();
        }
    }

    private void checkCnpjInUse(String cnpj) throws CnpjException {
        List<ClientModel> isClient;
        isClient = repository.findByCnpj(cnpj);
        if (!isClient.isEmpty()) {
            throw new CnpjException();
        }
    }
}