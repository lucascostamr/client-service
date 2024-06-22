package com.packetdelivery.clientservice;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

public class ValidationCompositeTests {

    @Mock
    private IValidation validationStub;

    private ValidationComposite sut;

    private AddClientModel fakeAddClientModel;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        List<IValidation> validationsList = new ArrayList<>();
        validationsList.add(validationStub);
        sut = new ValidationComposite(validationsList);
        fakeAddClientModel = mock(AddClientModel.class);
    }

    @Test
    void return_invalid_param_if_validations_fail() {
        try {
            when(validationStub.validate(fakeAddClientModel)).thenReturn("any_param");
            String response = (String) sut.validate(fakeAddClientModel);
            assertEquals(response, "any_param");
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    void return_null_on_success() {
        try {
            String response = (String) sut.validate(fakeAddClientModel);
            assertNull(response);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}
