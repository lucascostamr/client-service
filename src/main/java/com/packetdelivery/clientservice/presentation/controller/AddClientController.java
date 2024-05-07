package com.packetdelivery.clientservice.presentation.controller;

import static com.packetdelivery.clientservice.presentation.helpers.HttpHelper.*;
import com.packetdelivery.clientservice.presentation.errors.InvalidParamException;
import com.packetdelivery.clientservice.presentation.validations.IValidator;
import com.packetdelivery.clientservice.model.domain.IAddClientModel;
import com.packetdelivery.clientservice.protocols.IController;
import com.packetdelivery.clientservice.protocols.HttpReq;
import com.packetdelivery.clientservice.protocols.HttpRes;

public class AddClientController implements IController {
    private IValidator validator;

    public AddClientController(IValidator validator) {
        this.validator = validator;
    }

    public HttpRes handle(HttpReq httpRequest) {
        try {
            IAddClientModel client = (IAddClientModel) httpRequest.getBody();
            String validationError = (String) this.validator.validate(client);
            if (validationError != null) {
                return badRequest(new InvalidParamException(validationError));
            }
            return ok(client);
        } catch (Exception e) {
            return serverError(e);
        }
    }
}
