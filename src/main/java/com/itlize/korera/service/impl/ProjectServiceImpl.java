package com.itlize.korera.service.impl;

import com.itlize.korera.model.Project;
import com.itlize.korera.model.User;
import com.itlize.korera.repository.ProjectRepository;
import com.itlize.korera.repository.UserRepository;
import com.itlize.korera.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Project updateProject(Long id, Project project) {
        Project existingProject = projectRepository.findById(id).orElseThrow(() -> new RuntimeException("Project not found"));
        existingProject.setDescription(project.getDescription());
        existingProject.setProjectCode(project.getProjectCode());
        existingProject.setProjectName(project.getProjectName());
        existingProject.setUser(project.getUser());
        return projectRepository.save(existingProject);
    }

    @Override
    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }

    @Override
    public Project getProjectById(Long id) {
        return projectRepository.findById(id).orElseThrow(() -> new RuntimeException("Project not found"));
    }

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public List<Project> getProjectsByUser(User user) {
        return projectRepository.findByUser(user);
    }

    @Override
    public List<Project> getProjectsByUserId(Long userId) {
        // TODO Auto-generated method stub
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));
        return projectRepository.findByUser(user);
    }
}
