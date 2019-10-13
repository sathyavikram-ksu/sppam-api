package com.group1.sppam.controllers;

import com.group1.sppam.controllers.exception.ResourceNotFoundException;
import com.group1.sppam.models.Risk;
import com.group1.sppam.repository.ProjectRepository;
import com.group1.sppam.repository.RiskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/risk")
public class RiskController {
    @Autowired
    private RiskRepository repository;

    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping("/{id}")
    Optional<Risk> byId(@PathVariable Long id) {
        return repository.findById(id);
    }

    @GetMapping("/project/{id}")
    Iterable<Risk> byProjectId(@PathVariable Long id) {
        return this.projectRepository.findById(id)
                .map(project -> {
                    return repository.findAllByProject(project);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Project with" + id + "not found"));
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    Risk newRisk(@Valid @RequestBody Risk newRisk) {
        return repository.save(newRisk);
    }

    @PutMapping("/{id}")
    Risk updateRisk(@RequestBody Risk updatedRisk, @PathVariable Long id) {
        return repository.findById(id)
                .map(risk -> {
                    risk.update(updatedRisk);
                    return repository.save(risk);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Risk with" + id + "not found"));
    }

    @DeleteMapping("/{id}")
    void deleteEmployee(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
