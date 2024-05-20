package com.packetdelivery.clientservice;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class EmailValidation implements IValidation {
    private IValidator validator;

    @Autowired
    public EmailValidation(@Qualifier("EmailValidator") IValidator validator) {
        this.validator = validator;
    }

    @Override
    public Object validate(Object obj) throws Exception {
        IEmail client = (IEmail) obj;
        String email = client.getEmail();
        boolean isValid = validator.isValid(email);
        if (isValid)
            return null;
        return "email";
    }
}
