package com.packetdelivery.clientservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ParamValidationTests {

    @InjectMocks
    private ParamValidation sut;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void return_param_name_if_no_name_is_provided() throws Exception {
        String response = sut.validate(new AddClientModel(null, "any_email", "any_cnpj", "any_phone"));
        assertEquals(response, "name");
    }

    @Test
    void return_null_on_success() throws Exception {
        String response = sut.validate(new AddClientModel("any_name", "any_email", "any_cnpj", "any_phone"));
        assertNull(response);
    }
}
