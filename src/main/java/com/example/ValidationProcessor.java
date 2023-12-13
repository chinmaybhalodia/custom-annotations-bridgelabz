package com.example;

import com.annotations.*;
import com.exceptions.*;
import java.lang.reflect.Field;

public class ValidationProcessor {

    public static void validate(User user) throws ValidationException {
        Field[] fields = user.getClass().getDeclaredFields();
        for (Field field : fields) {
            validateNotBlank(user, field);
            validatePositive(user, field);
        }
    }

    // validation method for fields with annotation @NotBlank
    private static void validateNotBlank(User user, Field field) throws ValidationException {
        if (field.isAnnotationPresent(NotBlank.class)) {
            try {
                field.setAccessible(true);
                Object value = field.get(user);
                if (value == null
                        || (value instanceof String && (((String) value).isBlank()) || ((String) value).isEmpty())) {
                    throw new ValidationException("Field '" + field.getName() + "' must not be blank.");
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    // validation method for fields with annotation @Positive
    private static void validatePositive(User user, Field field) throws ValidationException {
        if (field.isAnnotationPresent(Positive.class)) {
            try {
                field.setAccessible(true);
                Object value = field.get(user);
                if (value instanceof Number && ((Number) value).doubleValue() <= 0) {
                    throw new ValidationException("Field '" + field.getName() + "' must be positive.");
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
