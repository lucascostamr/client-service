package com.packetdelivery.clientservice;

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
