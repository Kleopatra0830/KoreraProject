package com.itlize.korera.model;

import java.util.List;

import javax.persistence.*;

@Entity
public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resource_id")
    private Long resourceId;

    @Column(name = "resource_name", nullable = false, length = 100)
    private String resourceName;

    @Column(name = "resource_code", nullable = false)
    private int resourceCode;

    @Column(name = "type", length = 50)
    private String type;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "category", length = 50)
    private String category;

    @OneToOne(mappedBy = "resource", cascade = CascadeType.ALL)
    private ResourceDetail resourceDetail;

    @OneToMany(mappedBy = "resource", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProjectResource> projectResources;


    // Getters and Setters
    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public int getResourceCode() {
        return resourceCode;
    }

    public void setResourceCode(int resourceCode) {
        this.resourceCode = resourceCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public ResourceDetail getResourceDetail() {
        return resourceDetail;
    }

    public void setResourceDetail(ResourceDetail resourceDetail) {
        this.resourceDetail = resourceDetail;
    }

    public List<ProjectResource> getProjectResources() {
        return projectResources;
    }

    public void setProjectResources(List<ProjectResource> projectResources) {
        this.projectResources = projectResources;
    }

}
