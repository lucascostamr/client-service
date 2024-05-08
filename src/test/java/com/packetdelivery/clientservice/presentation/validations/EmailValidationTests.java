package com.packetdelivery.clientservice.presentation.validations;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import lombok.Getter;
import lombok.AllArgsConstructor;

import com.packetdelivery.clientservice.presentation.protocols.IEmail;
import com.packetdelivery.clientservice.presentation.validations.EmailValidation;
import com.packetdelivery.clientservice.presentation.validations.IValidator;

public class EmailValidationTests {

    class ValidatorStub implements IValidator {
        @Override
        public boolean isValid(Object obj) {
            return true;
        }
    }

    @Getter
    @AllArgsConstructor
    class FakeObject implements IEmail{
        private String email;
    }
    
    @Getter
    @AllArgsConstructor
    class SutTypes {
        EmailValidation sut;
        ValidatorStub validatorStub;
    }
    
    public SutTypes makeSut(){
        ValidatorStub validatorStub = mock(ValidatorStub.class);
        EmailValidation sut = new EmailValidation(validatorStub);
        return new SutTypes(sut, validatorStub);
    }
    
    @Test
    void return_false_if_invalid_email_is_provided(){
        try {
            SutTypes sutTypes = makeSut();
            EmailValidation sut = sutTypes.getSut();
            ValidatorStub validatorStub = sutTypes.getValidatorStub();
            FakeObject fakeObject = new FakeObject("invalid_email");
            when(validatorStub.isValid("invalid_email")).thenReturn(false);
            boolean response = (boolean) sut.validate(fakeObject);
            assertEquals(response, false);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    void return_true_on_success(){
        try {
            SutTypes sutTypes = makeSut();
            EmailValidation sut = sutTypes.getSut();
            ValidatorStub validatorStub = sutTypes.getValidatorStub();
            FakeObject fakeObject = new FakeObject("valid_email");
            when(validatorStub.isValid("valid_email")).thenReturn(true);
            boolean response = (boolean) sut.validate(fakeObject);
            assertEquals(response, true);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}
