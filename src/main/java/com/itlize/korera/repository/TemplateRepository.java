package com.itlize.korera.repository;

import com.itlize.korera.model.Template;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemplateRepository extends JpaRepository<Template, Long> {
    // List<Template> findBtemplateName(String name);
    // List<Template> findByProject_Id(Long project_id);
}
