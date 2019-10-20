package com.group1.sppam.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class Requirement extends Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer orderNumber;

    @NotEmpty(message = "Please provide valid name")
    @Size(min = 1, max = 255, message = "Please provide name with 1-255 characters")
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    private RequirementType type;

    @ManyToOne(optional = false)
    @JoinColumn(name = "project_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Project project;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void update(Requirement updatedRequirement) {
        this.setOrderNumber(updatedRequirement.getOrderNumber());
        this.setName(updatedRequirement.getName());
        this.setDescription(updatedRequirement.getDescription());
        this.setType(updatedRequirement.getType());
    }
}
