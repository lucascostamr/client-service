package com.packetdelivery.clientservice;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class CnpjValidation implements IValidation {
    private IValidator validator;

    @Autowired
    public CnpjValidation(@Qualifier("CnpjValidator") IValidator validator) {
        this.validator = validator;
    }

    @Override
    public Object validate(Object obj) throws Exception {
        ICnpj client = (ICnpj) obj;
        String cnpj = client.getCnpj();
        boolean isValid = this.validator.isValid(cnpj);
        if (isValid)
            return null;
        return "cnpj";
    }
}
