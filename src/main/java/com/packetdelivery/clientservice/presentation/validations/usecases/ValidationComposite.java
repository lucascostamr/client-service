package com.packetdelivery.clientservice;

import java.util.List;

public class ValidationComposite implements IValidation {
    private List<IValidation> validationList;

    public ValidationComposite(List<IValidation> validationList) {
        this.validationList = validationList;
    }

    @Override
    public Object validate(Object obj) throws IllegalAccessException {
        for (IValidation validation : this.validationList) {
            String error = (String) validation.validate(obj);
            if (error != null)
                return error;
        }
        return null;
    }
}
