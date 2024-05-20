package com.packetdelivery.clientservice;

public class Mapper {
    private Mapper() {
    }

    public static ClientModel toClientModel(AddClientModel addClientModel) {
        return new ClientModel(null, addClientModel.getName(), addClientModel.getEmail(), addClientModel.getCnpj(),
                addClientModel.getPhone());
    }
}
