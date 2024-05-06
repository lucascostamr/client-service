package com.packetdelivery.clientservice.presentation.controller;

import com.packetdelivery.clientservice.protocols.IController;
import com.packetdelivery.clientservice.model.domain.AddClientModel;
import com.packetdelivery.clientservice.protocols.HttpReq;
import com.packetdelivery.clientservice.protocols.HttpRes;

public class AddClientController implements IController {
    public HttpRes handle(HttpReq httpRequest) {
        AddClientModel client = (AddClientModel) httpRequest.getBody();
        if (client.getName() == null)
            return new HttpRes(400, "name");
        return new HttpRes(200, client);
    }
}
