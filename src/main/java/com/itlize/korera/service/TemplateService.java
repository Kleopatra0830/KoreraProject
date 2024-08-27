package com.itlize.korera.service;

import com.itlize.korera.model.Template;

import java.util.List;
import java.util.Optional;

public interface TemplateService {
    Template createTemplate(Template template);
    Optional<Template> getTemplateById(Long id);
    List<Template> getTemplatesByProjectId(Long projectId);
    List<Template> getAllTemplates();
    Template updateTemplate(Long id, Template template);
    void deleteTemplate(Long id);
}
