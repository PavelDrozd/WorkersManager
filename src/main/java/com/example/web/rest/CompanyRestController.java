package com.example.web.rest;

import com.example.data.dto.CompanyDto;
import com.example.service.CompanyService;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/companies")
public class CompanyRestController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/{id}")
    public CompanyDto get(@PathVariable Long id) {
        return companyService.get(id);
    }

    @GetMapping
    public Stream<CompanyDto> getAll() {
        return companyService.getAll();
    }

    @PostMapping("/create")
    public ResponseEntity<CompanyDto> create(@RequestBody CompanyDto company, Model model) {
        CompanyDto created = companyService.create(company);
        model.addAttribute("company", created);
        return buildResponseCompany(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyDto> update(@PathVariable Long id, @RequestBody CompanyDto company, Model model) {
        company.setId(id);
        CompanyDto updated = companyService.update(company);
        model.addAttribute("company", updated);
        return buildResponseCompany(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        companyService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    private ResponseEntity<CompanyDto> buildResponseCompany(CompanyDto company, HttpStatus status) {
        return ResponseEntity.status(status)
                .location(getLocation(company))
                .body(company);
    }

    private URI getLocation(CompanyDto company) {
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/workers/{id}")
                .buildAndExpand(company.getId())
                .toUri();
    }
}
