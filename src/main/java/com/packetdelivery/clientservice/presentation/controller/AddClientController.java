package com.packetdelivery.clientservice.presentation.controller;

import com.packetdelivery.clientservice.protocols.IController;

import com.packetdelivery.clientservice.presentation.errors.InvalidParamException;
import com.packetdelivery.clientservice.model.domain.AddClientModel;
import com.packetdelivery.clientservice.protocols.HttpReq;
import com.packetdelivery.clientservice.protocols.HttpRes;
import com.packetdelivery.clientservice.helpers.HttpHelper;

public class AddClientController implements IController {
    public HttpRes handle(HttpReq httpRequest) {
        AddClientModel client = (AddClientModel) httpRequest.getBody();
        if (client.getName() == null)
            return HttpHelper.badRequest(new InvalidParamException("name"));
        return HttpHelper.ok(client);
    }
}
