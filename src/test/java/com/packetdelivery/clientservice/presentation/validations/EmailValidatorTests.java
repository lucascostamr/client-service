package com.packetdelivery.clientservice.presentation.validations;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import lombok.Getter;
import lombok.AllArgsConstructor;

import com.packetdelivery.clientservice.presentation.validations.EmailValidator;

public class EmailValidatorTests {

    @Getter
    @AllArgsConstructor
    class SutTypes {
        EmailValidator sut;
    }

    @Getter
    @AllArgsConstructor
    class FakeObject {
        private String email;
    }
    
    public SutTypes makeSut(){
        EmailValidator sut = new EmailValidator();
        return new SutTypes(sut);
    }
    
    @Test
    void return_false_if_invalid_email_is_provided(){
        try {
            SutTypes sutTypes = makeSut();
            EmailValidator sut = sutTypes.getSut();
            FakeObject fakeObject = new FakeObject("invalid_email");
            boolean response = (boolean) sut.validate(fakeObject);
            assertEquals(response, false);
        } catch (Exception e) {
            fail();
        }
    }
}
