package com.group1.sppam.controllers;

import com.group1.sppam.exception.ResourceNotFoundException;
import com.group1.sppam.models.Requirement;
import com.group1.sppam.repository.ProjectRepository;
import com.group1.sppam.repository.RequirementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/requirement")
public class RequirementController {
    @Autowired
    private RequirementRepository repository;

    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping("/{id}")
    Optional<Requirement> byId(@PathVariable Long id) {
        return repository.findById(id);
    }

//    @PostMapping("/project/{id}")
//    @ResponseStatus(HttpStatus.CREATED)
//    Requirement newRisk(@Valid @RequestBody Requirement newRequirement) {
//        return this.projectRepository.findById(id).map(project -> {
//            return repository.findAllByProject(project);
//        })
//                .orElseThrow(() -> new ResourceNotFoundException("Project with " + id + " not found"));
//
//
//        return repository.save(newRisk);
//    }

    @GetMapping("/project/{id}")
    Iterable<Requirement> byProjectId(@PathVariable Long id) {
        return this.projectRepository.findById(id).map(project -> {
            return repository.findAllByProject(project);
        })
                .orElseThrow(() -> new ResourceNotFoundException("Project", "id", id));

    }

    @PutMapping("/{id}")
    Requirement updateRisk(@RequestBody Requirement updatedRequirement, @PathVariable Long id) {
        return repository.findById(id)
                .map(requirement -> {
                    requirement.update(updatedRequirement);
                    return repository.save(requirement);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Project", "id", id));
    }

    @DeleteMapping("/{id}")
    void deleteRequirement(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
