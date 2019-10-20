package com.group1.sppam.repository;

import com.group1.sppam.models.Project;
import com.group1.sppam.models.Requirement;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RequirementRepository extends CrudRepository<Requirement, Long> {
    List<Requirement> findAllByProject(Project project);
}
