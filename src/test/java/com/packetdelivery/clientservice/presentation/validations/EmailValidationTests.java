package com.packetdelivery.clientservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EmailValidationTests {

    @Mock
    private IValidator validatorStub;

    @InjectMocks
    private EmailValidation sut;

    private IEmail fakeObject;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        fakeObject = mock(IEmail.class);
    }

    @Test
    void return_param_email_if_invalid_email_is_provided() throws Exception {
        when(validatorStub.isValid("invalid_email")).thenReturn(false);
        when(fakeObject.getEmail()).thenReturn("invalid_email");
        String response = (String) sut.validate(fakeObject);
        assertEquals("email", response);
    }

    @Test
    void return_null_on_success() throws Exception {
        when(validatorStub.isValid("valid_email")).thenReturn(true);
        when(fakeObject.getEmail()).thenReturn("valid_email");
        String response = (String) sut.validate(fakeObject);
        assertNull(response);
    }
}
