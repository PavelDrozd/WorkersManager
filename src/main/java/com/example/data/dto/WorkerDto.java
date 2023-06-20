package com.example.data.dto;

import com.example.store.entity.Company;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkerDto {

    private Long id;

    private String firstName;

    private String lastName;

    private Integer age;

    private Double salary;

    private String profession;

    private Company company;
}
