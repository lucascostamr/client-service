package com.packetdelivery.clientservice.presentation.validations;

import java.util.List;

public interface IValidation {
    public Object validate(Object obj) throws IllegalAccessException;
}