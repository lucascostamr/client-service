package com.packetdelivery.clientservice.presentation.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mock.*;
import static org.junit.jupiter.api.Assertions.*;

import lombok.Getter;
import lombok.AllArgsConstructor;

import com.packetdelivery.clientservice.presentation.controller.AddClientController;
import com.packetdelivery.clientservice.protocols.HttpRes;
import com.packetdelivery.clientservice.protocols.HttpReq;
import com.packetdelivery.clientservice.model.domain.AddClientModel;

public class AddClientControllerTests {
    @Getter
    @AllArgsConstructor
    class FakeAddClient implements AddClientModel {
        private String name;
        private String email;
        private String cnpj;
        private String phone;
    }

    @Test
    void return_400_if_no_name_is_provided() {
        AddClientController sut = new AddClientController();
        FakeAddClient fakeAddClient = new FakeAddClient(null, "any_email", "any_cnpj", "any_phone");
        HttpReq fakeRequest = new HttpReq(fakeAddClient);
        HttpRes response = sut.handle(fakeRequest);
        assertEquals(response.getStatusCode(), 400);
    }
}
