package com.itlize.korera.controller;

import com.itlize.korera.model.Project;
import com.itlize.korera.model.Template;
import com.itlize.korera.service.ProjectService;
import com.itlize.korera.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/templates")
public class TemplateController {

    @Autowired
    private TemplateService templateService;

    @Autowired
    private ProjectService projectService;

    // Create a new Template
    @PostMapping("/create")
    public ResponseEntity<Template> createTemplate(@RequestBody Template template) {
        try {
            Template createdTemplate = templateService.createTemplate(template);
            return new ResponseEntity<>(createdTemplate, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Get a Template by ID
    @GetMapping("/read/{id}")
    public ResponseEntity<Template> getTemplateById(@PathVariable Long id) {
        Optional<Template> template = templateService.getTemplateById(id);
        if (template.isPresent()) {
            return new ResponseEntity<>(template.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Get all Templates by Project ID
    @GetMapping("/read/project/{projectId}")
    public ResponseEntity<List<Template>> getTemplatesByProjectId(@PathVariable Long projectId) {
        List<Template> templates = templateService.getTemplatesByProjectId(projectId);
        if (templates != null && !templates.isEmpty()) {
            return new ResponseEntity<>(templates, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Get all Templates
    @GetMapping("/getAll")
    public ResponseEntity<List<Template>> getAllTemplates() {
        List<Template> templates = templateService.getAllTemplates();
        return new ResponseEntity<>(templates, HttpStatus.OK);
    }

    // Update an existing Template
    @PutMapping("/update/{id}")
    public ResponseEntity<Template> updateTemplate(@PathVariable Long id, @RequestBody Template updatedTemplate) {
        try {
            Template template = templateService.updateTemplate(id, updatedTemplate);
            return new ResponseEntity<>(template, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a Template
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTemplate(@PathVariable Long id) {
        try {
            templateService.deleteTemplate(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
