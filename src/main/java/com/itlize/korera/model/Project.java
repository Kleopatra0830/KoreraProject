package com.itlize.korera.model;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Long project_id;

    @Column(name = "project_name", nullable = false, length = 100)
    private String projectName;

    @Column(name = "project_code", nullable = false)
    private int projectCode;

    @Column(name = "description", length = 255)
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<ProjectResource> projectResources;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Template> templates;

    // Getters and Setters
    public Long getProjectId() {
        return project_id;
    }

    public void setProjectId(Long project_id) {
        this.project_id = project_id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public int getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(int projectCode) {
        this.projectCode = projectCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<ProjectResource> getProjectResources() {
        return projectResources;
    }

    public void setProjectResources(List<ProjectResource> projectResources) {
        this.projectResources = projectResources;
    }

    public List<Template> getTemplates() {
        return templates;
    }

    public void setTemplates(List<Template> templates) {
        this.templates = templates;
    }
}
