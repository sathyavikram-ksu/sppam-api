package com.group1.sppam.payload;

import com.group1.sppam.models.Project;
import com.group1.sppam.models.RequirementType;

public class RequirementRequest {
    private Long id;
    private String description;
    private RequirementType type;
    private Project project;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RequirementType getType() {
        return type;
    }

    public void setType(RequirementType type) {
        this.type = type;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
