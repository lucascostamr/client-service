package com.packetdelivery.clientservice;

public class AddClientController implements IController {
    private IValidation validator;
    private IAddClientRepository addClientRepository;

    public AddClientController(IValidation validator, IAddClientRepository addClientRepository) {
        this.validator = validator;
        this.addClientRepository = addClientRepository;
    }

    public HttpRes handle(HttpReq httpRequest) {
        try {
            IAddClientModel client = (IAddClientModel) httpRequest.getBody();
            String validationError = (String) this.validator.validate(client);
            if (validationError != null) {
                return HttpHelper.badRequest(new InvalidParamException(validationError));
            }
            this.addClientRepository.add(client);
            return HttpHelper.ok(client);
        } catch (Exception e) {
            return HttpHelper.serverError(e);
        }
    }
}
