package com.itlize.korera.service.impl;

import com.itlize.korera.model.ProjectResource;
import com.itlize.korera.repository.ProjectResourceRepository;
import com.itlize.korera.service.ProjectResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectResourceServiceImpl implements ProjectResourceService {

    @Autowired
    private ProjectResourceRepository projectResourceRepository;

    @Override
    public ProjectResource createProjectResource(ProjectResource projectResource) {
        return projectResourceRepository.save(projectResource);
    }

    @Override
    public ProjectResource updateProjectResource(Long id, ProjectResource projectResource) {
        ProjectResource existingProjectResource = projectResourceRepository.findById(id).orElseThrow(() -> new RuntimeException("ProjectResource not found"));
        existingProjectResource.setProject(projectResource.getProject());
        existingProjectResource.setResource(projectResource.getResource());
        return projectResourceRepository.save(existingProjectResource);
    }

    @Override
    public void deleteProjectResource(Long id) {
        projectResourceRepository.deleteById(id);
    }

    @Override
    public ProjectResource getProjectResourceById(Long id) {
        return projectResourceRepository.findById(id).orElseThrow(() -> new RuntimeException("ProjectResource not found"));
    }

    @Override
    public List<ProjectResource> getAllProjectResources() {
        return projectResourceRepository.findAll();
    }
}
