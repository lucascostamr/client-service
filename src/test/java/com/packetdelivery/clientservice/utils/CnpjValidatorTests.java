package com.packetdelivery.clientservice;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import lombok.Getter;
import lombok.AllArgsConstructor;

public class CnpjValidatorTests {

    @Test
    void return_false_if_invalid_cnpj_provided() {
        try {
            CnpjValidator sut = new CnpjValidator();
            boolean response = sut.isValid("invalid_cnpj");
            assertEquals(response, false);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    void return_true_if_valid_cnpj_provided() {
        try {
            CnpjValidator sut = new CnpjValidator();
            boolean response = sut.isValid("19.009.518/0001-14");
            assertEquals(response, true);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}