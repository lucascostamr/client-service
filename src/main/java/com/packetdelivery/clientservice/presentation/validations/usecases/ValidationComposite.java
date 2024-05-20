package com.packetdelivery.clientservice;

import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;

@Primary
@Component
public class ValidationComposite implements IValidation {
    private final List<IValidation> validationList;

    @Autowired
    public ValidationComposite(List<IValidation> validationList) {
        this.validationList = validationList;
    }

    @Override
    public Object validate(Object obj) throws Exception {
        for (IValidation validation : this.validationList) {
            String error = (String) validation.validate(obj);
            if (error != null)
                return error;
        }
        return null;
    }
}
