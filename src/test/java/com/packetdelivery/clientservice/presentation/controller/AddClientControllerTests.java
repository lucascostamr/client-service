package com.packetdelivery.clientservice.presentation.controller;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import lombok.Getter;
import lombok.AllArgsConstructor;

import com.packetdelivery.clientservice.presentation.controller.AddClientController;
import com.packetdelivery.clientservice.presentation.errors.InvalidParamException;
import com.packetdelivery.clientservice.presentation.validations.IValidator;
import com.packetdelivery.clientservice.presentation.validations.ParamValidator;
import com.packetdelivery.clientservice.protocols.HttpRes;
import com.packetdelivery.clientservice.protocols.HttpReq;
import com.packetdelivery.clientservice.model.domain.IAddClientModel;

public class AddClientControllerTests {
    @Getter
    @AllArgsConstructor
    class FakeAddClient implements IAddClientModel {
        private String name;
        private String email;
        private String cnpj;
        private String phone;
    }

    class ValidatorStub implements IValidator {
        public String validate(Object obj) {
            return null;
        }
    }

    @Test
    void return_400_if_no_name_is_provided() {
        try {
            ValidatorStub validatorMock = mock(ValidatorStub.class);
            AddClientController sut = new AddClientController(validatorMock);
            FakeAddClient fakeAddClient = new FakeAddClient(null, "any_email", "any_cnpj", "any_phone");
            when(validatorMock.validate(fakeAddClient)).thenReturn("name");
            HttpReq fakeRequest = new HttpReq(fakeAddClient);
            HttpRes response = sut.handle(fakeRequest);
            InvalidParamException responseException = (InvalidParamException) response.getBody();
            InvalidParamException fakeException = new InvalidParamException("name");
            assertEquals(response.getStatusCode(), 400);
            assertEquals(responseException.getMessage(), fakeException.getMessage());
        } catch (Exception e) {
            assertEquals(e.getMessage(), 400);
        }
    }
}
