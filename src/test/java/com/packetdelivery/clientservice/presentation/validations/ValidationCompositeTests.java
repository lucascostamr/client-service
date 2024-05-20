package com.packetdelivery.clientservice;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.AllArgsConstructor;

public class ValidationCompositeTests {

    class ValidationStub implements IValidation {
        @Override
        public Object validate(Object obj) throws IllegalAccessException {
            return null;
        }
    }

    public AddClientModel makeFakeAddClientModel() {
        return new AddClientModel("any_name", "any_email", "any_cnpj", "any_phone");
    }

    @Getter
    @AllArgsConstructor
    class SutTypes {
        private ValidationComposite sut;
        private ValidationStub validationStub;
    }

    private SutTypes makeSut() {
        ValidationStub validationStub = mock(ValidationStub.class);
        List<IValidation> validationsList = new ArrayList<IValidation>();
        validationsList.add(validationStub);
        validationsList.add(new ValidationStub());
        ValidationComposite sut = new ValidationComposite(validationsList);
        return new SutTypes(sut, validationStub);
    }

    @Test
    void return_invalid_param_if_validations_fail() {
        try {
            SutTypes sutTypes = makeSut();
            ValidationComposite sut = sutTypes.getSut();
            ValidationStub validationStub = sutTypes.getValidationStub();
            AddClientModel fakeAddClientModel = makeFakeAddClientModel();
            when(validationStub.validate(fakeAddClientModel)).thenReturn("name");
            String response = (String) sut.validate(fakeAddClientModel);
            assertEquals(response, "name");
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    void return_null_on_success() {
        try {
            SutTypes sutTypes = makeSut();
            ValidationComposite sut = sutTypes.getSut();
            ValidationStub validationStub = sutTypes.getValidationStub();
            String response = (String) sut.validate(makeFakeAddClientModel());
            assertEquals(response, null);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}
