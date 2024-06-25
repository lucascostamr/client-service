package com.packetdelivery.clientservice;

import java.util.UUID;

public class Mapper {
    private Mapper() {
    }

    public static ClientModel addClientModelToClientModel(AddClientModel addClientModel) {
        return new ClientModel(null, addClientModel.getName(), addClientModel.getEmail(), addClientModel.getCnpj(),
                addClientModel.getPhone());
    }

    public static ClientModel updateClientModelToClientModel(UpdateClientModel updateClientModel) {
        UUID id = UUID.fromString(updateClientModel.getToken());
        return new ClientModel(id, updateClientModel.getName(), updateClientModel.getEmail(),
                updateClientModel.getCnpj(), updateClientModel.getPhone());
    }

    public static ClientModel setValuesToClientModel(ClientModel clientModel, ClientModel newClientModel) {
        clientModel.setName(newClientModel.getName());
        clientModel.setEmail(newClientModel.getEmail());
        clientModel.setCnpj(newClientModel.getCnpj());
        clientModel.setPhone(newClientModel.getPhone());
        return clientModel;
    }
}
