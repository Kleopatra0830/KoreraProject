package com.itlize.korera.service.impl;

import com.itlize.korera.model.Template;
import com.itlize.korera.repository.TemplateRepository;
import com.itlize.korera.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class TemplateServiceImpl implements TemplateService {

    @Autowired
    private TemplateRepository templateRepository;

    @Override
    public Template createTemplate(Template template) {
        return templateRepository.save(template);
    }

    @Override
    public Optional<Template> getTemplateById(Long id) {
        return templateRepository.findById(id);
    }

    // @Override
    // public List<Template> getTemplatesByProjectId(Long projectId) {
    //     return templateRepository.findByProject_Id(projectId);
    // }

    @Override
    public List<Template> getAllTemplates() {
        return templateRepository.findAll();
    }

    @Override
    public Template updateTemplate(Long id, Template updatedTemplate) {
        Optional<Template> existingTemplateOpt = templateRepository.findById(id);
        if (existingTemplateOpt.isPresent()) {
            Template existingTemplate = existingTemplateOpt.get();
            existingTemplate.setTemplateName(updatedTemplate.getTemplateName());
            existingTemplate.setTemplateDescription(updatedTemplate.getTemplateDescription());
            existingTemplate.setType(updatedTemplate.getType());
            existingTemplate.setField(updatedTemplate.getField());
            existingTemplate.setFormula(updatedTemplate.getFormula());
            existingTemplate.setProject(updatedTemplate.getProject());
            return templateRepository.save(existingTemplate);
        } else {
            throw new EntityNotFoundException("Template not found with id: " + id);
        }
    }

    @Override
    public void deleteTemplate(Long id) {
        if (templateRepository.existsById(id)) {
            templateRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Template not found with id: " + id);
        }
    }

    @Override
    public List<Template> getTemplatesByProjectId(Long projectId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTemplatesByProjectId'");
    }
}
