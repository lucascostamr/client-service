package com.packetdelivery.clientservice;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.when;

import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

public class JwtAdapterTests {

    @InjectMocks
    private JwtAdapter sut;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void return_token_on_success() {
        String token = sut.encode("any_value");
        assertNotNull(token);
    }

    @Test
    void should_decode_token_on_success() {
        String token = sut.encode("any_value");
        String value = sut.decode(token);
        assertEquals("any_value", value);
    }

    @Test
    void should_throw_if_invalid_token_provided() {
        try {
            String value = sut.decode("invalid_token");
        } catch (Exception e) {
            assertEquals("Invalid JWT token", e.getMessage());
        }
    }
}
