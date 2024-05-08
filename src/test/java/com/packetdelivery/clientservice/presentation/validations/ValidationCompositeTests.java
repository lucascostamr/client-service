package com.packetdelivery.clientservice.presentation.validations;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.AllArgsConstructor;

import com.packetdelivery.clientservice.model.domain.IAddClientModel;
import com.packetdelivery.clientservice.presentation.protocols.IEmail;
import com.packetdelivery.clientservice.presentation.validations.IValidation;
import com.packetdelivery.clientservice.presentation.validations.ValidationComposite;

public class ValidationCompositeTests {

    class ValidationStub implements IValidation {
        @Override
        public Object validate(Object obj) throws IllegalAccessException {
            return null;
        }
    }
    
    @Getter
    @AllArgsConstructor
    class FakeAddClient implements IAddClientModel, IEmail {
        private String name;
        private String email;
        private String cnpj;
        private String phone;
    }
    
    @Getter
    @AllArgsConstructor
    class SutTypes {
        private ValidationComposite sut;
        private ValidationStub validationStub;
    }
    
    private SutTypes makeSut(){
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
            FakeAddClient fakeAddClient = new FakeAddClient("any_name", "any_email", "any_cnpj", "any_phone");
            when(validationStub.validate(fakeAddClient)).thenReturn("name");
            String response = (String) sut.validate(fakeAddClient);
            assertEquals(response, "name");
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}
