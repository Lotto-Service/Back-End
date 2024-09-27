package com.icebear2n2.lotto.model.entity;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class IntegerListConverter implements AttributeConverter<List<Integer>, String> {

    @Override
    public String convertToDatabaseColumn(List<Integer> attribute) {
        return attribute != null ? attribute.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(",")) : null;
    }

    @Override
    public List<Integer> convertToEntityAttribute(String dbData) {
        return dbData != null ? Arrays.stream(dbData.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList()) : null;
    }
}
