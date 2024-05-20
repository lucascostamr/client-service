package com.packetdelivery.clientservice;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Qualifier;

@Qualifier("EmailValidator")
@Component
public class EmailValidatorAdapter implements IValidator {
    @Override
    public boolean isValid(String data) {
        EmailValidator validator = EmailValidator.getInstance();
        return validator.isValid(data);
    }
}
