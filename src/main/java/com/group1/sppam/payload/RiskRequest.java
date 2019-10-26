package com.group1.sppam.payload;

import com.group1.sppam.models.Project;
import com.group1.sppam.models.RiskStatus;
import com.group1.sppam.models.RiskType;

public class RiskRequest {
    private Long id;
    private Integer orderNumber;
    private String description;
    private RiskStatus status;
    private RiskType type;
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

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
