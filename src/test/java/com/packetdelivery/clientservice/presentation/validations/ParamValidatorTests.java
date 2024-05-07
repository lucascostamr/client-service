package com.packetdelivery.clientservice.presentation.validations;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import lombok.Getter;
import lombok.AllArgsConstructor;

import com.packetdelivery.clientservice.presentation.validations.ParamValidator;

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
