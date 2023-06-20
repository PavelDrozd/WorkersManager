package com.example.shared.util;

import com.example.data.dto.CompanyDto;
import com.example.data.dto.WorkerDto;
import com.example.store.entity.Company;
import com.example.store.entity.Worker;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class EntityDtoMapper {

    private ModelMapper mapper;

    public Company mapToCompany(CompanyDto companyDto) {
        return mapper.map(companyDto, Company.class);
    }

    public CompanyDto mapToCompanyDto(Company company) {
        return mapper.map(company, CompanyDto.class);
    }

    public Worker mapToWorker(WorkerDto workerDto){
        return mapper.map(workerDto, Worker.class);
    }

    public WorkerDto mapToWorkerDto(Worker worker) {
        return mapper.map(worker, WorkerDto.class);
    }
}
