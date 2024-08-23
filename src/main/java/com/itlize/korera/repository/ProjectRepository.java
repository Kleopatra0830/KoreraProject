package com.itlize.korera.repository;

import com.itlize.korera.model.Project;
import com.itlize.korera.model.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByProjectName(String projectName);
    List<Project> findByUser(User user);
}
