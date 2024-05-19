package com.packetdelivery.clientservice;

import java.lang.reflect.Field;

public class ParamValidation implements IValidation {
    public String validate(Object obj) throws IllegalAccessException {
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
