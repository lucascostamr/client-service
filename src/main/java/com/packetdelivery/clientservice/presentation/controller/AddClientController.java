package com.packetdelivery.clientservice.presentation.controller;

import static com.packetdelivery.clientservice.presentation.helpers.HttpHelper.*;
import static com.packetdelivery.clientservice.presentation.validations.ParamValidator.validate;
import com.packetdelivery.clientservice.presentation.errors.InvalidParamException;
import com.packetdelivery.clientservice.model.domain.IAddClientModel;
import com.packetdelivery.clientservice.protocols.IController;
import com.packetdelivery.clientservice.protocols.HttpReq;
import com.packetdelivery.clientservice.protocols.HttpRes;

public class AddClientController implements IController {
    public HttpRes handle(HttpReq httpRequest) {
        IAddClientModel client = (IAddClientModel) httpRequest.getBody();
        String validationError = validate(client);
        if (validationError != null) {
            return badRequest(new InvalidParamException(validationError));
        }
        return ok(client);
    }
}
