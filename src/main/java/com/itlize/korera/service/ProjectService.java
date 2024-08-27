package com.itlize.korera.service;

import com.itlize.korera.model.Project;
import com.itlize.korera.model.User;

import java.util.List;
import java.util.Optional;

public interface ProjectService {
    Project createProject(Project project);
    Project updateProject(Long id, Project project);
    void deleteProject(Long id);
    List<Project> getAllProjects();
    List<Project> getProjectsByUser(User user);
    List<Project> getProjectsByUserId(Long userId);
    Optional<Project> getProjectById(Long id);
}
