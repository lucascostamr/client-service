package com.packetdelivery.clientservice.presentation.validations;

import java.lang.reflect.Field;

import com.packetdelivery.clientservice.model.domain.IAddClientModel;

public class ParamValidator {
    private ParamValidator() {
    }

    public static String validate(Object obj) {
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                if (field.get(obj) == null) {
                    return field.getName();
                }
            } catch (IllegalAccessException e) {
                return e.toString();
            }
        }
        return null;
    }
}
