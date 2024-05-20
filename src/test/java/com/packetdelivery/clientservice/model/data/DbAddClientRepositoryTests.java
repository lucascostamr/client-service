package com.packetdelivery.clientservice;

import org.junit.jupiter.api.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import lombok.Getter;
import lombok.AllArgsConstructor;

public class DbAddClientRepositoryTests {

    class AddClientRepositoryStub implements IAddClientRepository {
        public ClientModel add(AddClientModel client) {
            return null;
        }
    }

    public AddClientModel makeFakeAddClientModel() {
        return new AddClientModel("any_name", "any_email", "any_cnpj", "any_phone");
    }

    @Getter
    @AllArgsConstructor
    class SutTypes {
        DbAddClientRepository sut;
        AddClientRepositoryStub addClientRepositoryStub;
    }

    public SutTypes makeSut() {
        AddClientRepositoryStub addClientRepositoryStub = mock(AddClientRepositoryStub.class);
        DbAddClientRepository sut = new DbAddClientRepository(addClientRepositoryStub);
        return new SutTypes(sut, addClientRepositoryStub);
    }

    @Test
    void return_client_on_AddClientRepository_success() {
        try {
            SutTypes sutTypes = makeSut();
            DbAddClientRepository sut = sutTypes.getSut();
            AddClientRepositoryStub addClientRepositoryStub = sutTypes.getAddClientRepositoryStub();
            AddClientModel fakeAddClientModel = makeFakeAddClientModel();
            ClientModel fakeClientModel = mock(ClientModel.class);
            when(fakeClientModel.getId()).thenReturn("any_id");
            when(addClientRepositoryStub.add(fakeAddClientModel)).thenReturn(fakeClientModel);
            ClientModel response = sut.add(fakeAddClientModel);
            assertEquals(response.getId(), "any_id");
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}
