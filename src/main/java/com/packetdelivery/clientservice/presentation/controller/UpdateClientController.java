package com.packetdelivery.clientservice;

import org.springframework.stereotype.Controller;

@Controller
public class UpdateClientController implements IController {
    private IValidation validation;
    private IUpdateClient updateClientRepository;

    public UpdateClientController(IValidation validation, IUpdateClient updateClientRepository) {
        this.validation = validation;
        this.updateClientRepository = updateClientRepository;
    }

    public HttpRes handle(HttpReq httpRequest) {
        try {
            UpdateClientModel client = (UpdateClientModel) httpRequest.getBody();
            String validationError = (String) this.validation.validate(client);
            if (validationError != null) {
                return HttpHelper.badRequest(new InvalidParamException(validationError));
            }
            updateClientRepository.update(client);
            return HttpHelper.ok("");
        } catch (NotFoundException e) {
            return HttpHelper.notFound(e);
        } catch (Exception e) {
            return HttpHelper.serverError(e);
        }
    }
}
