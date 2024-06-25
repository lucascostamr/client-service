package com.packetdelivery.clientservice;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.mock;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class AddClientControllerTests {

    @Mock
    private IValidation validationStub;

    @Mock
    private IAddClient addClientStub;

    @InjectMocks
    private AddClientController sut;

    private AddClientModel fakeAddClientModel;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        fakeAddClientModel = mock(AddClientModel.class);
    }

    @Test
    void return_400_if_validator_returns_param() throws Exception {
        when(validationStub.validate(fakeAddClientModel)).thenReturn("name");
        HttpReq fakeRequest = new HttpReq(fakeAddClientModel);
        HttpRes response = sut.handle(fakeRequest);
        InvalidParamException responseException = (InvalidParamException) response.getBody();
        InvalidParamException fakeException = new InvalidParamException("name");
        assertEquals(response.getStatusCode(), 400);
        assertEquals(responseException.getMessage(), fakeException.getMessage());
    }

    @Test
    void return_500_if_validator_throws() throws Exception {
        when(validationStub.validate(fakeAddClientModel)).thenThrow(new RuntimeException("error"));
        HttpReq fakeRequest = new HttpReq(fakeAddClientModel);
        HttpRes response = sut.handle(fakeRequest);
        Exception responseException = (Exception) response.getBody();
        assertEquals(response.getStatusCode(), 500);
        assertEquals(responseException.getMessage(), "error");
    }

    @Test
    void call_AddClientRepository_with_correct_values() throws Exception {
        HttpReq fakeRequest = new HttpReq(fakeAddClientModel);
        sut.handle(fakeRequest);
        verify(addClientStub).add(fakeAddClientModel);
    }

    @Test
    void return_500_if_AddClientRepository_throws() throws Exception {
        HttpReq fakeRequest = new HttpReq(fakeAddClientModel);
        when(addClientStub.add(fakeAddClientModel)).thenThrow(new RuntimeException("error"));
        HttpRes response = sut.handle(fakeRequest);
        Exception responseException = (Exception) response.getBody();
        assertEquals(response.getStatusCode(), 500);
        assertEquals(responseException.getMessage(), "error");
    }

    @Test
    void return_ClientModel_on_AddClientRepository_success() throws Exception {
        ClientModel fakeClientModel = mock(ClientModel.class);
        when(fakeClientModel.getId()).thenReturn("any_id");
        HttpReq fakeRequest = new HttpReq(fakeAddClientModel);
        when(addClientStub.add(fakeAddClientModel)).thenReturn("any_token");
        HttpRes response = sut.handle(fakeRequest);
        String responseBody = (String) response.getBody();
        assertEquals(response.getStatusCode(), 201);
        assertEquals(responseBody, "any_token");
    }

}
