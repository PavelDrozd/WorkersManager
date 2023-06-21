package com.example.store.entity.converter;

import com.example.store.entity.Company;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class FieldOfActivityConverter implements AttributeConverter<Company.FieldOfActivity, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Company.FieldOfActivity attribute) {
        return switch (attribute) {
            case OTHER -> 0;
            case SERVICE -> 1;
            case SALES -> 2;
            case EDUCATION -> 3;
            case TRADING -> 4;
            case LOGISTICS -> 5;
            case TRANSPORT -> 6;
            case FINANCIAL -> 7;
            case RAW_MATERIALS -> 8;
            case CHEMICAL -> 9;
        };
    }

    @Override
    public Company.FieldOfActivity convertToEntityAttribute(Integer dbData) {
        return switch (dbData) {
            case 1 -> Company.FieldOfActivity.SERVICE;
            case 2 -> Company.FieldOfActivity.SALES;
            case 3 -> Company.FieldOfActivity.EDUCATION;
            case 4 -> Company.FieldOfActivity.TRADING;
            case 5 -> Company.FieldOfActivity.LOGISTICS;
            case 6 -> Company.FieldOfActivity.TRANSPORT;
            case 7 -> Company.FieldOfActivity.FINANCIAL;
            case 8 -> Company.FieldOfActivity.RAW_MATERIALS;
            case 9 -> Company.FieldOfActivity.CHEMICAL;
            default -> Company.FieldOfActivity.OTHER;
        };
    }
}
