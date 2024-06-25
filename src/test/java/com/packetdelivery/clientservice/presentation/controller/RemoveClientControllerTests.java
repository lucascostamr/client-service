package com.packetdelivery.clientservice;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.doThrow;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class RemoveClientControllerTests {

    @Mock
    private IRemoveClient removeClientStub;

    @InjectMocks
    private RemoveClientController sut;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void return_400_if_no_token_is_provided() throws Exception {
        HttpReq fakeRequest = new HttpReq(null);
        HttpRes response = sut.handle(fakeRequest);
        InvalidParamException responseException = (InvalidParamException) response.getBody();
        assertEquals(response.getStatusCode(), 400);
        assertEquals("Invalid Param: token", responseException.getMessage());
    }

    @Test
    void return_500_if_removeClientRepository_throws() throws Exception {
        doThrow(new Exception()).when(removeClientStub).remove("any_token");
        HttpReq fakeRequest = new HttpReq("any_token");
        HttpRes response = sut.handle(fakeRequest);
        assertEquals(response.getStatusCode(), 500);
    }

    @Test
    void return_204_on_success() throws Exception {
        HttpReq fakeRequest = new HttpReq("any_token");
        HttpRes response = sut.handle(fakeRequest);
        assertEquals(response.getStatusCode(), 204);
    }
}
