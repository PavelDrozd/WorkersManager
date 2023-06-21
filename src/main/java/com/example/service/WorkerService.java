package com.example.service;

import com.example.data.dto.WorkerDto;

public interface WorkerService extends AbstractService<WorkerDto, Long> {

    WorkerDto create(WorkerDto workerDto, String companyName);
    WorkerDto update(WorkerDto workerDto, String companyName);
}
