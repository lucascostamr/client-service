package com.packetdelivery.clientservice.presentation.validations;

import com.packetdelivery.clientservice.presentation.validations.protocols.IValidation;
import com.packetdelivery.clientservice.presentation.validations.protocols.IValidator;
import com.packetdelivery.clientservice.model.domain.ICnpj;

public class CnpjValidation implements IValidation {
    private IValidator validator;

    public CnpjValidation(IValidator validator) {
        this.validator = validator;
    }

    @Override
    public Object validate(Object obj) throws IllegalAccessException {
        ICnpj client = (ICnpj) obj;
        String cnpj = client.getCnpj();
        boolean isValid = this.validator.isValid(cnpj);
        if(isValid) return null;
        return "cnpj";
    }
}
