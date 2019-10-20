package com.group1.sppam.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
public class Risk extends Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer orderNumber;

    @Column(columnDefinition = "TEXT")
    private String description;

    private RiskStatus status;

    private RiskType type;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
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

    public RiskStatus getStatus() {
        return status;
    }

    public void setStatus(RiskStatus status) {
        this.status = status;
    }

    public RiskType getType() {
        return type;
    }

    public void setType(RiskType type) {
        this.type = type;
    }

    @JsonIgnore
    public Project getProject() {
        return project;
    }

    @JsonProperty
    public void setProject(Project project) {
        this.project = project;
    }

    public void update(Risk updatedRisk) {
        this.setOrderNumber(updatedRisk.getOrderNumber());
        this.setDescription(updatedRisk.getDescription());
        this.setStatus(updatedRisk.getStatus());
        this.setType(updatedRisk.getType());
    }
}
