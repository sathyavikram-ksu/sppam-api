package com.group1.sppam.repository;

import com.group1.sppam.models.Project;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProjectRepository extends CrudRepository<Project, Long> {
    Iterable<Project> findDistinctByOwner_IdOrTeamMembers_IdOrderByCreatedAtAsc(Long ownerId, Long userId);
}
