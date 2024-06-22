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
}
