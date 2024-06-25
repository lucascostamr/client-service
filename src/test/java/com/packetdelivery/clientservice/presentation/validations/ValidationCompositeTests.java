package com.packetdelivery.clientservice;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
    void return_invalid_param_if_validations_fail() throws Exception {
        when(validationStub.validate(fakeAddClientModel)).thenReturn("any_param");
        String response = (String) sut.validate(fakeAddClientModel);
        assertEquals(response, "any_param");
    }

    @Test
    void return_null_on_success() throws Exception {
        String response = (String) sut.validate(fakeAddClientModel);
        assertNull(response);
    }
}
