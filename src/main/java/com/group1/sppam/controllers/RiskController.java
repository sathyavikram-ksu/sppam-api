package com.group1.sppam.controllers;

import com.group1.sppam.exception.ResourceNotFoundException;
import com.group1.sppam.models.Risk;
import com.group1.sppam.payload.RiskRequest;
import com.group1.sppam.repository.ProjectRepository;
import com.group1.sppam.repository.RiskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/risk")
public class RiskController {
    @Autowired
    private RiskRepository repository;

    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping("/project/{id}")
    Iterable<Risk> byProjectId(@PathVariable Long id) {
        return repository.findAllByProject_IdOrderByCreatedAtAsc(id);
    }

    @PostMapping("/project/{projectId}")
    @ResponseStatus(HttpStatus.CREATED)
    Risk newRisk(@Valid @RequestBody RiskRequest newRiskRequest, @PathVariable Long projectId) {
        return this.projectRepository.findById(projectId)
                .map(project -> {
                    Risk risk = new Risk(newRiskRequest);
                    risk.setProject(project);
                    return repository.save(risk);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Project", "id", projectId));
    }

    @PutMapping("/{riskId}")
    Risk updateRisk(@Valid @RequestBody RiskRequest updatedRisk, @PathVariable Long riskId) {
        return repository.findById(riskId)
                .map(risk -> {
                    risk.update(updatedRisk);
                    return repository.save(risk);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Risk", "id", riskId));
    }

    @DeleteMapping("/{riskId}")
    void deleteEmployee(@PathVariable Long riskId) {
        repository.deleteById(riskId);
    }
}
