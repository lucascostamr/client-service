package com.packetdelivery.clientservice;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import lombok.Getter;
import lombok.AllArgsConstructor;

public class AddClientControllerTests {
    @Getter
    @AllArgsConstructor
    class ClientModel implements IClientModel {
        private String id;
        private String name;
        private String email;
        private String cnpj;
        private String phone;
    }

    class AddClientRepositoryStub implements IAddClientRepository {
        public ClientModel add(IAddClientModel client) {
            return new ClientModel("any_id", "any_name", "any_email", "any_cnpj", "any_phone");
        }
    }

    @Getter
    @AllArgsConstructor
    class FakeAddClientModel implements IAddClientModel, IEmail {
        private String name;
        private String email;
        private String cnpj;
        private String phone;
    }

    class ValidatorStub implements IValidation {
        public String validate(Object obj) throws IllegalAccessException {
            return null;
        }
    }

    public FakeAddClientModel makeFakeAddClientModel() {
        return new FakeAddClientModel(null, "any_email", "any_cnpj", "any_phone");
    }

    @Getter
    @AllArgsConstructor
    class SutTypes {
        AddClientController sut;
        ValidatorStub validatorMock;
        AddClientRepositoryStub addClientRepositoryStub;
    }

    public SutTypes makeSut() {
        AddClientRepositoryStub addClientRepositoryStub = mock(AddClientRepositoryStub.class);
        ValidatorStub validatorMock = mock(ValidatorStub.class);
        AddClientController sut = new AddClientController(validatorMock, addClientRepositoryStub);
        return new SutTypes(sut, validatorMock, addClientRepositoryStub);
    }

    @Test
    void return_400_if_no_name_is_provided() {
        try {
            SutTypes sutTypes = makeSut();
            AddClientController sut = sutTypes.getSut();
            ValidatorStub validatorMock = sutTypes.getValidatorMock();
            FakeAddClientModel fakeAddClientModel = makeFakeAddClientModel();
            when(validatorMock.validate(fakeAddClientModel)).thenReturn("name");
            HttpReq fakeRequest = new HttpReq(fakeAddClientModel);
            HttpRes response = sut.handle(fakeRequest);
            InvalidParamException responseException = (InvalidParamException) response.getBody();
            InvalidParamException fakeException = new InvalidParamException("name");
            assertEquals(response.getStatusCode(), 400);
            assertEquals(responseException.getMessage(), fakeException.getMessage());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void return_500_if_validator_throws() {
        try {
            SutTypes sutTypes = makeSut();
            AddClientController sut = sutTypes.getSut();
            ValidatorStub validatorMock = sutTypes.getValidatorMock();
            FakeAddClientModel fakeAddClientModel = makeFakeAddClientModel();
            when(validatorMock.validate(fakeAddClientModel)).thenThrow(new RuntimeException("error"));
            HttpReq fakeRequest = new HttpReq(fakeAddClientModel);
            HttpRes response = sut.handle(fakeRequest);
            Exception responseException = (Exception) response.getBody();
            assertEquals(response.getStatusCode(), 500);
            assertEquals(responseException.getMessage(), "error");
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void call_AddClientRepository_with_correct_values() {
        try {
            SutTypes sutTypes = makeSut();
            AddClientController sut = sutTypes.getSut();
            AddClientRepositoryStub addClientRepositoryStub = sutTypes.getAddClientRepositoryStub();
            FakeAddClientModel fakeAddClientModel = makeFakeAddClientModel();
            HttpReq fakeRequest = new HttpReq(fakeAddClientModel);
            sut.handle(fakeRequest);
            verify(addClientRepositoryStub).add(fakeAddClientModel);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void return_500_if_AddClientRepository_throws() {
        try {
            SutTypes sutTypes = makeSut();
            AddClientController sut = sutTypes.getSut();
            AddClientRepositoryStub addClientRepositoryStub = sutTypes.getAddClientRepositoryStub();
            FakeAddClientModel fakeAddClientModel = makeFakeAddClientModel();
            HttpReq fakeRequest = new HttpReq(fakeAddClientModel);
            when(addClientRepositoryStub.add(fakeAddClientModel)).thenThrow(new RuntimeException("error"));
            HttpRes response = sut.handle(fakeRequest);
            Exception responseException = (Exception) response.getBody();
            assertEquals(response.getStatusCode(), 500);
            assertEquals(responseException.getMessage(), "error");
        } catch (Exception e) {
            fail();
        }
    }
}
