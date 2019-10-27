package com.group1.sppam.controllers;

import com.group1.sppam.exception.ResourceNotFoundException;
import com.group1.sppam.models.Effort;
import com.group1.sppam.repository.EffortRepository;
import com.group1.sppam.repository.RequirementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/effort")
public class EffortController {
    @Autowired
    private EffortRepository repository;

    @Autowired
    private RequirementRepository requirementRepository;

    @GetMapping("/requirement/{reqId}")
    Iterable<Effort> byProjectId(@PathVariable Long reqId) {
        return repository.findAllByRequirement_IdOrderByCreatedAtDesc(reqId);
    }

    @PostMapping("/requirement/{reqId}")
    @ResponseStatus(HttpStatus.CREATED)
    Effort newEffort(@Valid @RequestBody Effort newEffortRequest, @PathVariable Long reqId) {
        return this.requirementRepository.findById(reqId)
                .map(requirement -> {
                    newEffortRequest.setRequirement(requirement);
                    return repository.save(newEffortRequest);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Requirement", "id", reqId));
    }

    @PutMapping("/{effortId}")
    Effort updateEffort(@Valid @RequestBody Effort updatedEffortRequest, @PathVariable Long effortId) {
        return repository.findById(effortId)
                .map(effort -> {
                    effort.update(updatedEffortRequest);
                    return repository.save(effort);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Effort", "id", effortId));
    }

    @DeleteMapping("/{effortId}")
    void deleteEmployee(@PathVariable Long effortId) {
        repository.deleteById(effortId);
    }
}
