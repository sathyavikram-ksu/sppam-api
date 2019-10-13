package com.group1.sppam.models;

import javax.persistence.*;

@Entity
public class Requirement extends Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer orderNumber;

    @Column(columnDefinition = "TEXT")
    private String description;

    private RequirementType type;

    @ManyToOne(optional = false)
    @JoinColumn(name = "project_id")
    private Project project;

}
