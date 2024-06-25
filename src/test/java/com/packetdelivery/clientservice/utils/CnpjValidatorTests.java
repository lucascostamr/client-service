package com.packetdelivery.clientservice;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

public class CnpjValidatorTests {

    @InjectMocks
    private CnpjValidator sut;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void return_false_if_invalid_cnpj_provided() throws Exception {
        boolean response = sut.isValid("invalid_cnpj");
        assertFalse(response);
    }

    @Test
    void return_true_if_valid_cnpj_provided() throws Exception {
        boolean response = sut.isValid("19.009.518/0001-14");
        assertTrue(response);
    }
}