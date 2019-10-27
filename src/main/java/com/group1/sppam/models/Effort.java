package com.group1.sppam.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
public class Effort extends Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long fromDate;
    private Long toDate;
    private Long reqAnalysisHrs;
    private Long designingHrs;
    private Long codingHrs;
    private Long testingHrs;
    private Long projectManagementHrs;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "requirement_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Requirement requirement;

    public void update(Effort effort) {
        this.setFromDate(effort.getFromDate());
        this.setToDate(effort.getToDate());
        this.setReqAnalysisHrs(effort.getReqAnalysisHrs());
        this.setDesigningHrs(effort.getDesigningHrs());
        this.setCodingHrs(effort.getCodingHrs());
        this.setTestingHrs(effort.getTestingHrs());
        this.setProjectManagementHrs(effort.getProjectManagementHrs());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFromDate() {
        return fromDate;
    }

    public void setFromDate(Long fromDate) {
        this.fromDate = fromDate;
    }

    public Long getToDate() {
        return toDate;
    }

    public void setToDate(Long toDate) {
        this.toDate = toDate;
    }

    public Long getReqAnalysisHrs() {
        return reqAnalysisHrs;
    }

    public void setReqAnalysisHrs(Long reqAnalysisHrs) {
        this.reqAnalysisHrs = reqAnalysisHrs;
    }

    public Long getDesigningHrs() {
        return designingHrs;
    }

    public void setDesigningHrs(Long designingHrs) {
        this.designingHrs = designingHrs;
    }

    public Long getCodingHrs() {
        return codingHrs;
    }

    public void setCodingHrs(Long codingHrs) {
        this.codingHrs = codingHrs;
    }

    public Long getTestingHrs() {
        return testingHrs;
    }

    public void setTestingHrs(Long testingHrs) {
        this.testingHrs = testingHrs;
    }

    public Long getProjectManagementHrs() {
        return projectManagementHrs;
    }

    public void setProjectManagementHrs(Long projectManagementHrs) {
        this.projectManagementHrs = projectManagementHrs;
    }

    public Requirement getRequirement() {
        return requirement;
    }

    public void setRequirement(Requirement requirement) {
        this.requirement = requirement;
    }
}
