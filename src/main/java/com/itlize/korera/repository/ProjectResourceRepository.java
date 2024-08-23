package com.itlize.korera.repository;

import com.itlize.korera.model.Project;
import com.itlize.korera.model.ProjectResource;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectResourceRepository extends JpaRepository<ProjectResource, Long> {

    List<ProjectResource> findByProject(Project project1);
    // List<ProjectResource> findByProjectId(Long projectId);
    // List<ProjectResource> findByResourceId(Long resourceId);
}
