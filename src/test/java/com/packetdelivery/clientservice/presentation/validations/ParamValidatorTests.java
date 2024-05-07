package com.packetdelivery.clientservice.presentation.validations;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import lombok.Getter;
import lombok.AllArgsConstructor;

import com.packetdelivery.clientservice.presentation.controller.AddClientController;
import com.packetdelivery.clientservice.presentation.errors.InvalidParamException;
import com.packetdelivery.clientservice.presentation.validations.ParamValidator;
import com.packetdelivery.clientservice.protocols.HttpRes;
import com.packetdelivery.clientservice.protocols.HttpReq;
import com.packetdelivery.clientservice.model.domain.IAddClientModel;

public class ParamValidatorTests {
    @Getter
    @AllArgsConstructor
    class FakeObject {
        private String name;
        private String email;
        private String cnpj;
        private String phone;
    }

    @Test
    void return_param_name_if_no_name_is_provided() {
        try {
            ParamValidator sut = new ParamValidator();
            FakeObject fakeObject = new FakeObject(null, "any_email", "any_cnpj", "any_phone");
            String response = sut.validate(fakeObject);
            assertEquals(response, "name");
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void return_null_on_success() {
        try {
            ParamValidator sut = new ParamValidator();
            FakeObject fakeObject = new FakeObject("any_name", "any_email", "any_cnpj", "any_phone");
            String response = sut.validate(fakeObject);
            assertEquals(response, null);
        } catch (Exception e) {
            fail();
        }
    }
}
