package com.group1.sppam.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.group1.sppam.payload.RiskRequest;
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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "project_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Project project;

    public Risk() {
    }

    public Risk(RiskRequest riskRequest) {
        this.id = riskRequest.getId();
        this.orderNumber = riskRequest.getOrderNumber();
        this.description = riskRequest.getDescription();
        this.status = riskRequest.getStatus();
        this.type = riskRequest.getType();
        this.project = riskRequest.getProject();
    }

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

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public void update(RiskRequest updatedRisk) {
        this.setDescription(updatedRisk.getDescription());
        this.setStatus(updatedRisk.getStatus());
        this.setType(updatedRisk.getType());
    }
}
