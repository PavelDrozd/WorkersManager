package com.example.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDto {

    private Long id;

    private String name;

    private BigDecimal enterpriseFund;

    private List<WorkerDto> workers;

    private FieldOfActivity fieldOfActivity;

    public enum FieldOfActivity {
        OTHER, SERVICE, SALES, EDUCATION, TRADING, LOGISTICS, TRANSPORT, FINANCIAL, RAW_MATERIALS, CHEMICAL
    }
}
