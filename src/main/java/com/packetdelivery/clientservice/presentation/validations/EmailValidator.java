package com.packetdelivery.clientservice.presentation.validations;

import com.packetdelivery.clientservice.presentation.validations.IValidator;

public class EmailValidator implements IValidator{
    @Override
    public Object validate(Object obj) throws IllegalAccessException {
        return false;
    }
}
