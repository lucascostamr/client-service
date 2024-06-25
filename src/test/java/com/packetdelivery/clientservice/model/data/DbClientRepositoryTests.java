package com.packetdelivery.clientservice;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.mock;
import static org.mockito.ArgumentMatchers.any;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class DbClientRepositoryTests {

    @Mock
    private IAddClientRepository addClientRepositoryStub;

    @Mock
    private IEncoder encoderStub;

    @InjectMocks
    private DbClientRepository sut;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void return_token_on_AddClientRepository_success() throws Exception {
        AddClientModel fakeAddClientModel = mock(AddClientModel.class);
        ClientModel fakeClientModel = mock(ClientModel.class);
        when(fakeClientModel.getId()).thenReturn("any_id");
        when(encoderStub.encode("any_id")).thenReturn("any_token");
        when(addClientRepositoryStub.add(fakeAddClientModel)).thenReturn(fakeClientModel);
        String response = sut.add(fakeAddClientModel);
        assertEquals(response, "any_token");
        verify(addClientRepositoryStub).add(fakeAddClientModel);
    }
}
