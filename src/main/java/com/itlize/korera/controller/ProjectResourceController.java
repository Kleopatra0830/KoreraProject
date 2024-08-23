package com.itlize.korera.controller;

import com.itlize.korera.model.ProjectResource;
import com.itlize.korera.service.ProjectResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project-resources")
public class ProjectResourceController {

    // @Autowired
    // private ProjectResourceService projectResourceService;

    // @PostMapping
    // public ResponseEntity<ProjectResource> createProjectResource(@RequestBody ProjectResource projectResource) {
    //     ProjectResource createdProjectResource = projectResourceService.createProjectResource(projectResource);
    //     return new ResponseEntity<>(createdProjectResource, HttpStatus.CREATED);
    // }

    // @PutMapping("/{id}")
    // public ResponseEntity<ProjectResource> updateProjectResource(@PathVariable Long id, @RequestBody ProjectResource projectResource) {
    //     ProjectResource updatedProjectResource = projectResourceService.updateProjectResource(id, projectResource);
    //     return new ResponseEntity<>(updatedProjectResource, HttpStatus.OK);
    // }

    // @DeleteMapping("/{id}")
    // public ResponseEntity<Void> deleteProjectResource(@PathVariable Long id) {
    //     projectResourceService.deleteProjectResource(id);
    //     return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    // }

    // @GetMapping("/{id}")
    // public ResponseEntity<ProjectResource> getProjectResourceById(@PathVariable Long id) {
    //     ProjectResource projectResource = projectResourceService.getProjectResourceById(id);
    //     return new ResponseEntity<>(projectResource, HttpStatus.OK);
    // }

    // @GetMapping("/project/{projectId}")
    // public ResponseEntity<List<ProjectResource>> getProjectResourcesByProjectId(@PathVariable Long projectId) {
    //     List<ProjectResource> projectResources = projectResourceService.getProjectResourcesByProjectId(projectId);
    //     return new ResponseEntity<>(projectResources, HttpStatus.OK);
    // }

    // @GetMapping
    // public ResponseEntity<List<ProjectResource>> getAllProjectResources() {
    //     List<ProjectResource> projectResources = projectResourceService.getAllProjectResources();
    //     return new ResponseEntity<>(projectResources, HttpStatus.OK);
    // }
}
