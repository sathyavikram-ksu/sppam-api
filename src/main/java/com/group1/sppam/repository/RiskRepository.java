package com.group1.sppam.repository;

import com.group1.sppam.models.Project;
import com.group1.sppam.models.Risk;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RiskRepository extends CrudRepository<Risk, Long> {
    List<Risk> findAllByProject(Project project);
}
