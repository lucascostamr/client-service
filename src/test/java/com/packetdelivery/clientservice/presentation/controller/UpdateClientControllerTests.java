package com.packetdelivery.clientservice;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.doThrow;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class UpdateClientControllerTests {

    @Mock
    private IValidation validationStub;

    @Mock
    private IUpdateClient updateClientStub;

    @InjectMocks
    private UpdateClientController sut;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void return_400_if_invalid_params_is_provided() throws Exception {
        UpdateClientModel fakeUpdateClient = mock(UpdateClientModel.class);
        when(validationStub.validate(fakeUpdateClient)).thenReturn("invalid_param");
        HttpReq httpRequest = new HttpReq(fakeUpdateClient);
        HttpRes httpResponse = sut.handle(httpRequest);
        assertEquals(400, httpResponse.getStatusCode());
    }

    @Test
    void return_500_if_updateClient_throws() throws Exception {
        UpdateClientModel fakeUpdateClient = mock(UpdateClientModel.class);
        when(validationStub.validate(fakeUpdateClient)).thenReturn(null);
        doThrow(new RuntimeException()).when(updateClientStub).update(fakeUpdateClient);
        HttpReq httpRequest = new HttpReq(fakeUpdateClient);
        HttpRes httpResponse = sut.handle(httpRequest);
        assertEquals(500, httpResponse.getStatusCode());
    }

    @Test
    void return_404_if_client_not_found() throws Exception {
        UpdateClientModel fakeUpdateClient = mock(UpdateClientModel.class);
        when(validationStub.validate(fakeUpdateClient)).thenReturn(null);
        doThrow(new NotFoundException("client not found")).when(updateClientStub).update(fakeUpdateClient);
        HttpReq httpRequest = new HttpReq(fakeUpdateClient);
        HttpRes httpResponse = sut.handle(httpRequest);
        assertEquals(404, httpResponse.getStatusCode());
    }

    @Test
    void return_204_on_success() throws Exception {
        UpdateClientModel fakeUpdateClient = mock(UpdateClientModel.class);
        HttpReq httpRequest = new HttpReq(fakeUpdateClient);
        HttpRes httpResponse = sut.handle(httpRequest);
        assertEquals(204, httpResponse.getStatusCode());
    }
}