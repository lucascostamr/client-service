package com.packetdelivery.clientservice.presentation.validations;

import java.lang.reflect.Field;

import com.packetdelivery.clientservice.model.domain.IAddClientModel;

public class ParamValidator {
    private ParamValidator() {
    }

    public static String validate(Object obj) throws IllegalAccessException {
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.get(obj) == null) {
                return field.getName();
            }
        }
        return null;
    }
}
