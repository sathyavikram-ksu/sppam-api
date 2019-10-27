package com.group1.sppam.repository;

import com.group1.sppam.models.Effort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EffortRepository extends CrudRepository<Effort, Long> {
    List<Effort> findAllByRequirement_IdOrderByCreatedAtDesc(Long id);
}
