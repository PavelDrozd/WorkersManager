package com.example.web.rest;

import com.example.data.dto.WorkerDto;
import com.example.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/workers")
public class WorkerRestController {

    @Autowired
    private WorkerService workerService;

    @GetMapping("/{id}")
    public WorkerDto get(@PathVariable Long id) {
        return workerService.get(id);
    }

    @GetMapping
    public Stream<WorkerDto> getAll() {
        return workerService.getAll();
    }

    @PostMapping("/create")
    public ResponseEntity<WorkerDto> create(@RequestParam("company") String companyName, @RequestBody WorkerDto worker, Model model) {
        WorkerDto created = workerService.create(worker, companyName);
        model.addAttribute("worker", created);
        return buildResponseWorker(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkerDto> update(@PathVariable Long id, @RequestParam("company") String companyName, @RequestBody WorkerDto worker, Model model) {
        worker.setId(id);
        WorkerDto updated = workerService.update(worker, companyName);
        model.addAttribute("worker", updated);
        return buildResponseWorker(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        workerService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    private ResponseEntity<WorkerDto> buildResponseWorker(WorkerDto worker, HttpStatus status) {
        return ResponseEntity.status(status)
                .location(getLocation(worker))
                .body(worker);
    }

    private URI getLocation(WorkerDto worker) {
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/workers/{id}")
                .buildAndExpand(worker.getId())
                .toUri();
    }
}
