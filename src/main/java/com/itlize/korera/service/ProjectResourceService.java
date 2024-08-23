package com.itlize.korera.service;

import com.itlize.korera.model.ProjectResource;

import java.util.List;

public interface ProjectResourceService {
    ProjectResource createProjectResource(ProjectResource projectResource);
    ProjectResource updateProjectResource(Long id, ProjectResource projectResource);
    void deleteProjectResource(Long id);
    ProjectResource getProjectResourceById(Long id);
    List<ProjectResource> getAllProjectResources();
}
