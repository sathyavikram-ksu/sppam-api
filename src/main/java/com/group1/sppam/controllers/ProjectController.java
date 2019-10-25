package com.group1.sppam.controllers;

import com.group1.sppam.models.Project;
import com.group1.sppam.repository.ProjectRepository;
import com.group1.sppam.security.CurrentUser;
import com.group1.sppam.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    private ProjectRepository repository;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    Project newProject(@Valid @RequestBody Project newProject) {
        return repository.save(newProject);
    }

    @GetMapping("/byuser")
    Iterable<Project> byUser(@CurrentUser UserPrincipal currentUser) {
        return repository.findDistinctByOwner_IdOrTeamMembers_Id(currentUser.getId(), currentUser.getId());
    }

    @GetMapping("/{id}")
    Optional<Project> byId(@PathVariable Long id) {
        return repository.findById(id);
    }
//
//    @PutMapping("/{id}")
//    Project replaceEmployee(@RequestBody Project updatedProject, @PathVariable Long id) {
//        return repository.findById(id)
//                .map(project -> {
//                    project.update(updatedProject);
//                    return repository.save(project);
//                })
//                .orElseThrow(() -> new ResourceNotFoundException("Project", "id", id));
//    }
//
//    @DeleteMapping("/{id}")
//    void deleteEmployee(@PathVariable Long id) {
//        //delete risks
//        repository.deleteById(id);
//    }
}
