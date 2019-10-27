package com.group1.sppam.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.group1.sppam.payload.RequirementRequest;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
public class Requirement extends Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String description;

    private RequirementType type;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "project_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Project project;

    public Requirement() {
    }

    public Requirement(RequirementRequest requirementRequest) {
        this.setDescription(requirementRequest.getDescription());
        this.setType(requirementRequest.getType());
    }

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

    public void update(RequirementRequest updatedRequirement) {
        this.setDescription(updatedRequirement.getDescription());
        this.setType(updatedRequirement.getType());
    }
}
