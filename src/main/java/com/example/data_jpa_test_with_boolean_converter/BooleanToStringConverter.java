package com.example.data_jpa_test_with_boolean_converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class BooleanToStringConverter implements AttributeConverter<Boolean, String> {

    public static final String TRUE = "Y";
    public static final String FALSE = "N";

    @Override
    public String convertToDatabaseColumn(Boolean value) {
        if (value == null) {
            return null;
        }
        return value ? TRUE : FALSE;
    }

    @Override
    public Boolean convertToEntityAttribute(String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
        return TRUE.equals(value);
    }
}
