package com.packetdelivery.clientservice;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

public class ResponseEntityAdapter {
    private ResponseEntityAdapter() {
    }

    public static ResponseEntity adapt(IController controller, Object data) {
        HttpReq request = new HttpReq(data);
        HttpRes response = controller.handle(request);
        switch (response.getStatusCode()) {
        case 500:
            Exception serverError = (Exception) response.getBody();
            return new ResponseEntity(serverError.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        case 400:
            Exception badRequest = (Exception) response.getBody();
            return new ResponseEntity(badRequest.getMessage(), HttpStatus.BAD_REQUEST);
        case 404:
            Exception notFound = (Exception) response.getBody();
            return new ResponseEntity(notFound.getMessage(), HttpStatus.NOT_FOUND);
        case 201:
            return new ResponseEntity(response.getBody(), HttpStatus.CREATED);
        case 204:
            return new ResponseEntity(response.getBody(), HttpStatus.NO_CONTENT);
        default:
            return new ResponseEntity(response.getBody(), HttpStatus.OK);
        }
    }
}
