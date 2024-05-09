package com.packetdelivery.clientservice.presentation.validations;

import com.packetdelivery.clientservice.presentation.validations.protocols.IValidation;
import com.packetdelivery.clientservice.presentation.validations.protocols.IValidator;
import com.packetdelivery.clientservice.model.domain.IEmail;

public class EmailValidation implements IValidation {
    private IValidator validator;

    public EmailValidation(IValidator validator) {
        this.validator = validator;
    }

    @Override
    public Object validate(Object obj) throws IllegalAccessException {
        IEmail client = (IEmail) obj;
        String email = client.getEmail();
        boolean isValid = validator.isValid(email);
        if (isValid)
            return null;
        return "email";
    }
}
