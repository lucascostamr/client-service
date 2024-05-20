package com.packetdelivery.clientservice;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import lombok.Getter;
import lombok.AllArgsConstructor;

public class AddClientControllerTests {

    class AddClientStub implements IAddClient {
        public ClientModel add(AddClientModel client) {
            return null;
        }
    }

    class ValidatorStub implements IValidation {
        public String validate(Object obj) throws IllegalAccessException {
            return null;
        }
    }

    public AddClientModel makeFakeAddClientModel() {
        return new AddClientModel("any_name", "any_email", "any_cnpj", "any_phone");
    }

    @Getter
    @AllArgsConstructor
    class SutTypes {
        AddClientController sut;
        ValidatorStub validatorStub;
        AddClientStub addClientStub;
    }

    public SutTypes makeSut() {
        AddClientStub addClientStub = mock(AddClientStub.class);
        ValidatorStub validatorStub = mock(ValidatorStub.class);
        AddClientController sut = new AddClientController(validatorStub, addClientStub);
        return new SutTypes(sut, validatorStub, addClientStub);
    }

    @Test
    void return_400_if_validator_returns_param() {
        try {
            SutTypes sutTypes = makeSut();
            AddClientController sut = sutTypes.getSut();
            ValidatorStub validatorStub = sutTypes.getValidatorStub();
            AddClientModel fakeAddClientModel = makeFakeAddClientModel();
            when(validatorStub.validate(fakeAddClientModel)).thenReturn("name");
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
            ValidatorStub validatorStub = sutTypes.getValidatorStub();
            AddClientModel fakeAddClientModel = makeFakeAddClientModel();
            when(validatorStub.validate(fakeAddClientModel)).thenThrow(new RuntimeException("error"));
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
            AddClientStub addClientStub = sutTypes.getAddClientStub();
            AddClientModel fakeAddClientModel = makeFakeAddClientModel();
            HttpReq fakeRequest = new HttpReq(fakeAddClientModel);
            sut.handle(fakeRequest);
            verify(addClientStub).add(fakeAddClientModel);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void return_500_if_AddClientRepository_throws() {
        try {
            SutTypes sutTypes = makeSut();
            AddClientController sut = sutTypes.getSut();
            AddClientStub addClientStub = sutTypes.getAddClientStub();
            AddClientModel fakeAddClientModel = makeFakeAddClientModel();
            HttpReq fakeRequest = new HttpReq(fakeAddClientModel);
            when(addClientStub.add(fakeAddClientModel)).thenThrow(new RuntimeException("error"));
            HttpRes response = sut.handle(fakeRequest);
            Exception responseException = (Exception) response.getBody();
            assertEquals(response.getStatusCode(), 500);
            assertEquals(responseException.getMessage(), "error");
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void return_ClientModel_on_AddClientRepository_success() {
        try {
            SutTypes sutTypes = makeSut();
            AddClientController sut = sutTypes.getSut();
            AddClientStub addClientStub = sutTypes.getAddClientStub();
            AddClientModel fakeAddClientModel = makeFakeAddClientModel();
            ClientModel fakeClientModel = mock(ClientModel.class);
            when(fakeClientModel.getId()).thenReturn("any_id");
            HttpReq fakeRequest = new HttpReq(fakeAddClientModel);
            when(addClientStub.add(fakeAddClientModel)).thenReturn(fakeClientModel);
            HttpRes response = sut.handle(fakeRequest);
            ClientModel responseBody = (ClientModel) response.getBody();
            assertEquals(response.getStatusCode(), 200);
            assertEquals(responseBody.getId(), "any_id");
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}
