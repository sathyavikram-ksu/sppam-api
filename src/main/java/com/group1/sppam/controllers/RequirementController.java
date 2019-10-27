package com.group1.sppam.controllers;

import com.group1.sppam.exception.ResourceNotFoundException;
import com.group1.sppam.models.Requirement;
import com.group1.sppam.payload.RequirementRequest;
import com.group1.sppam.repository.ProjectRepository;
import com.group1.sppam.repository.RequirementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/requirement")
public class RequirementController {
    @Autowired
    private RequirementRepository repository;

    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping("/project/{id}")
    Iterable<Requirement> byProjectId(@PathVariable Long id) {
        return repository.findAllByProject_IdOrderByCreatedAtAsc(id);
    }

    @GetMapping("/{id}")
    Optional<Requirement> byId(@PathVariable Long id) {
        return repository.findById(id);
    }

    @PostMapping("/project/{projectId}")
    @ResponseStatus(HttpStatus.CREATED)
    Requirement newRequirement(@Valid @RequestBody RequirementRequest requirementRequest, @PathVariable Long projectId) {
        return this.projectRepository.findById(projectId)
                .map(project -> {
                    Requirement requirement = new Requirement(requirementRequest);
                    requirement.setProject(project);
                    return repository.save(requirement);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Project", "id", projectId));
    }

    @PutMapping("/{reqId}")
    Requirement updateRequirement(@Valid @RequestBody RequirementRequest requirementRequest, @PathVariable Long reqId) {
        return repository.findById(reqId)
                .map(req -> {
                    req.update(requirementRequest);
                    return repository.save(req);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Requirement", "id", reqId));
    }

    @DeleteMapping("/{reqId}")
    void deleteEmployee(@PathVariable Long reqId) {
        repository.deleteById(reqId);
    }
}
