package com.packetdelivery.clientservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;

public class CnpjValidationTests {

    @Mock
    private IValidator validatorStub;

    @InjectMocks
    private CnpjValidation sut;

    private ICnpj fakeObject;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        fakeObject = mock(ICnpj.class);
    }

    @Test
    void should_return_param_cpnj_on_fail() {
        try {
            when(validatorStub.isValid("invalid_cnpj")).thenReturn(false);
            when(fakeObject.getCnpj()).thenReturn("invalid_cnpj");
            String response = (String) sut.validate(fakeObject);
            assertEquals("cnpj", response);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    void should_return_null_on_success() {
        try {
            when(validatorStub.isValid("valid_cnpj")).thenReturn(true);
            when(fakeObject.getCnpj()).thenReturn("valid_cnpj");
            String response = (String) sut.validate(fakeObject);
            assertNull(response);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}
