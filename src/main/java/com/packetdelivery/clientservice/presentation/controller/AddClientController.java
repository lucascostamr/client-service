package com.packetdelivery.clientservice;

import org.springframework.stereotype.Controller;

@Controller
public class AddClientController implements IController {
    private IValidation validation;
    private IAddClient addClientRepository;

    public AddClientController(IValidation validation, IAddClient addClientRepository) {
        this.validation = validation;
        this.addClientRepository = addClientRepository;
    }

    public HttpRes handle(HttpReq httpRequest) {
        try {
            AddClientModel client = (AddClientModel) httpRequest.getBody();
            String validationError = (String) this.validation.validate(client);
            if (validationError != null) {
                return HttpHelper.badRequest(new InvalidParamException(validationError));
            }
            ClientModel clientData = this.addClientRepository.add(client);
            return HttpHelper.ok(clientData);
        } catch (EmailException e) {
            return HttpHelper.badRequest(e);
        } catch (Exception e) {
            return HttpHelper.serverError(e);
        }
    }
}
