package com.example.service.impl;

import com.example.data.dto.WorkerDto;
import com.example.service.WorkerService;
import com.example.shared.exception.service.ServiceNotFoundException;
import com.example.shared.exception.service.ServiceValidationException;
import com.example.shared.util.EntityDtoMapper;
import com.example.store.entity.Company;
import com.example.store.entity.Worker;
import com.example.store.repository.CompanyRepository;
import com.example.store.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Stream;

@Service
public class WorkerServiceImpl implements WorkerService {

    @Autowired
    private WorkerRepository workerRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private EntityDtoMapper mapper;

    @Override
    public WorkerDto create(WorkerDto workerDto) {
        checkWorkerNull(workerDto);
        Worker worker = workerRepository.save(mapper.mapToWorker(workerDto));
        return mapper.mapToWorkerDto(worker);
    }

    @Override
    public WorkerDto create(WorkerDto workerDto, String companyName) {
        checkWorkerNull(workerDto);
        Worker worker = mapper.mapToWorker(workerDto);
        worker.setCompany(companyRepository.findByName(companyName)
                .orElseThrow(() -> new ServiceNotFoundException("Company with name: " + companyName + " doesn't exist")));
        return mapper.mapToWorkerDto(workerRepository.save(worker));
    }

    @Override
    public Stream<WorkerDto> getAll() {
        return workerRepository.findAll().stream().map(mapper::mapToWorkerDto);
    }

    @Override
    public WorkerDto get(Long id) {
        checkId(id);
        return workerRepository.findById(id)
                .map(mapper::mapToWorkerDto)
                .orElseThrow(() -> new ServiceNotFoundException("Worker with id: " + id + " doesn't exist"));
    }

    @Override
    public WorkerDto update(WorkerDto workerDto) {
        checkWorkerNull(workerDto);
        checkId(workerDto.getId());
        Worker worker = workerRepository.save(mapper.mapToWorker(workerDto));
        return mapper.mapToWorkerDto(worker);
    }

    @Override
    public WorkerDto update(WorkerDto workerDto, String companyName) {
        checkWorkerNull(workerDto);
        checkId(workerDto.getId());
        Worker worker = mapper.mapToWorker(workerDto);
        Optional<Company> company = companyRepository.findByName(companyName);
        company.ifPresent(worker::setCompany);
        return mapper.mapToWorkerDto(workerRepository.save(worker));
    }

    @Override
    public void delete(Long id) {
        checkId(id);
        workerRepository.findById(id)
                .orElseThrow(() -> new ServiceNotFoundException("Worker with id: " + id + " doesn't exist"));
        workerRepository.deleteById(id);
    }

    private void checkWorkerNull(WorkerDto workerDto) {
        if (workerDto == null) {
            throw new ServiceValidationException("Worker is null");
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
