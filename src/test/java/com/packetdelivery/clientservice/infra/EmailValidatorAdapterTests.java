package com.packetdelivery.clientservice;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EmailValidatorAdapterTests {

    @Test
    void return_false_if_validator_returns_false() {
        EmailValidatorAdapter sut = new EmailValidatorAdapter();
        boolean response = sut.isValid("invalid_email");
        assertEquals(response, false);
    }

    @Test
    void return_true_if_validator_returns_true() {
        EmailValidatorAdapter sut = new EmailValidatorAdapter();
        boolean response = sut.isValid("valid@mail.com");
        assertEquals(response, true);
    }
}
