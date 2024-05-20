package com.packetdelivery.clientservice;

import java.lang.reflect.Field;
import org.springframework.stereotype.Component;

@Component
public class ParamValidation implements IValidation {
    public String validate(Object obj) throws IllegalAccessException {
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object value = field.get(obj);
            if ((value instanceof String && ((String) value).isEmpty()) || value == null) {
                return field.getName();
            }
        }
        return null;
    }
}
