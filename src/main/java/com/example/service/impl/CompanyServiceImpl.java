package com.example.service.impl;

import com.example.data.dto.CompanyDto;
import com.example.service.CompanyService;
import com.example.shared.exception.service.ServiceNotFoundException;
import com.example.shared.exception.service.ServiceValidationException;
import com.example.shared.util.EntityDtoMapper;
import com.example.store.entity.Company;
import com.example.store.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private EntityDtoMapper mapper;

    @Override
    public CompanyDto create(CompanyDto companyDto) {
        checkCompanyNull(companyDto);
        Company company = companyRepository.save(mapper.mapToCompany(companyDto));
        return mapper.mapToCompanyDto(company);
    }

    @Override
    public Stream<CompanyDto> getAll() {
        return companyRepository.findAll().stream().map(mapper::mapToCompanyDto);
    }

    @Override
    public CompanyDto get(Long id) {
        checkId(id);
        return companyRepository.findById(id)
                .map(mapper::mapToCompanyDto)
                .orElseThrow(() -> new ServiceNotFoundException("Company with id: " + id + " doesn't exist"));
    }

    @Override
    public CompanyDto update(CompanyDto companyDto) {
        checkCompanyNull(companyDto);
        checkId(companyDto.getId());
        Company company = companyRepository.save(mapper.mapToCompany(companyDto));
        return mapper.mapToCompanyDto(company);
    }

    @Override
    public void delete(Long id) {
        checkId(id);
        companyRepository.findById(id)
                .orElseThrow(() -> new ServiceNotFoundException("Company with id: " + id + " doesn't exist"));
        companyRepository.deleteById(id);

    }

    private void checkCompanyNull(CompanyDto companyDto) {
        if (companyDto == null) {
            throw new ServiceValidationException("Company is null");
        }
    }

    private void checkId(Long id) {
        if (id == null) {
            throw new ServiceValidationException("ID is null");
        }
        if (id < 0) {
            throw new ServiceValidationException("Wrong ID, less than 0");
        }
    }
}
