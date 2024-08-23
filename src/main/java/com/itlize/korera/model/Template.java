package com.itlize.korera.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Template {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "template_id")
    private Long template_id;

    @Column(name = "template_name", nullable = false, length = 100)
    private String templateName;

    @Column(name = "template_description", length = 255)
    private String templateDescription;

    @Column(name = "type", length = 50)
    private String type;

    @Column(name = "field", length = 100)
    private String field;

    @Column(name = "formula", length = 255)
    private String formula;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    /// hmmm
    @OneToMany(mappedBy = "template", cascade = CascadeType.ALL)
    private List<ResourceDetail> resourceDetails;

    // Getters and Setters
    public Long getId() {
        return template_id;
    }

    public void setId(Long id) {
        this.template_id = id;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getTemplateDescription() {
        return templateDescription;
    }

    public void setTemplateDescription(String templateDescription) {
        this.templateDescription = templateDescription;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public List<ResourceDetail> getResourceDetails() {
        return resourceDetails;
    }

    public void setResourceDetails(List<ResourceDetail> resourceDetails) {
        this.resourceDetails = resourceDetails;
    }
}
