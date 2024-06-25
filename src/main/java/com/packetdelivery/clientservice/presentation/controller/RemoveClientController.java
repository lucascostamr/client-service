package com.packetdelivery.clientservice;

import org.springframework.stereotype.Controller;

@Controller
public class RemoveClientController implements IController {
    private IRemoveClient removeClientRepository;

    public RemoveClientController(IRemoveClient removeClientRepository) {
        this.removeClientRepository = removeClientRepository;
    }

    public HttpRes handle(HttpReq httpRequest) {
        try {
            String token = (String) httpRequest.getBody();
            if (token == null) {
                return HttpHelper.badRequest(new InvalidParamException("token"));
            }
            removeClientRepository.remove(token);
            return HttpHelper.noContent();
        } catch (NotFoundException e) {
            return HttpHelper.notFound(e);
        } catch (Exception e) {
            return HttpHelper.serverError(e);
        }
    }
}
