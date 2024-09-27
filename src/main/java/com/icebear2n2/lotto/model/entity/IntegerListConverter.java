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
        if (attribute == null || attribute.isEmpty()) {
            return ""; // 빈 리스트는 빈 문자열로 저장
        }
        return attribute.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));
    }

    @Override
    public List<Integer> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) {
            return List.of(); // 빈 문자열일 경우 빈 리스트 반환
        }

        try {
            // 숫자로 변환할 수 없는 값이 들어올 가능성을 대비한 처리
            return Arrays.stream(dbData.split(","))
//                    .map(String::trim) // 혹시 모를 공백 제거
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("DB에서 가져온 데이터가 올바른 숫자 형식이 아닙니다: " + dbData, e);
        }
    }
}
