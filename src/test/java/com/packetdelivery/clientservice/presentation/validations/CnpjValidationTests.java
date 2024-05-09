package com.packetdelivery.clientservice;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import lombok.Getter;
import lombok.AllArgsConstructor;

public class CnpjValidationTests {

    class ValidatorStub implements IValidator {
        @Override
        public boolean isValid(Object obj) {
            return true;
        }
    }

    @Getter
    @AllArgsConstructor
    class FakeObject implements ICnpj {
        private String cnpj;
    }

    @Getter
    @AllArgsConstructor
    class SutTypes {
        CnpjValidation sut;
        ValidatorStub validatorStub;
    }

    public SutTypes makeSut() {
        ValidatorStub validatorStub = mock(ValidatorStub.class);
        CnpjValidation sut = new CnpjValidation(validatorStub);
        return new SutTypes(sut, validatorStub);
    }

    @Test
    void return_param_cpnj_on_fail() {
        try {
            SutTypes sutTypes = makeSut();
            CnpjValidation sut = sutTypes.getSut();
            ValidatorStub validatorStub = sutTypes.getValidatorStub();
            FakeObject fakeObject = new FakeObject("invalid_cnpj");
            when(validatorStub.isValid("invalid_cnpj")).thenReturn(false);
            String response = (String) sut.validate(fakeObject);
            assertEquals(response, "cnpj");
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    void return_null_on_success() {
        try {
            SutTypes sutTypes = makeSut();
            CnpjValidation sut = sutTypes.getSut();
            ValidatorStub validatorStub = sutTypes.getValidatorStub();
            FakeObject fakeObject = new FakeObject("valid_cnpj");
            when(validatorStub.isValid("valid_cnpj")).thenReturn(true);
            String response = (String) sut.validate(fakeObject);
            assertEquals(response, null);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}
