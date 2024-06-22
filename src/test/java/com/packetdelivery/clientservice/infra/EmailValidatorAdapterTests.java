package com.packetdelivery.clientservice;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import static org.mockito.Mockito.when;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

public class EmailValidatorAdapterTests {

    @InjectMocks
    private EmailValidatorAdapter sut;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void return_false_if_validator_returns_false() {
        boolean response = sut.isValid("invalid_email");
        assertFalse(response);
    }

    @Test
    void return_true_if_validator_returns_true() {
        boolean response = sut.isValid("valid@email.com");
        assertTrue(response);
    }
}
