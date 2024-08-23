package com.itlize.korera.service.impl;

import com.itlize.korera.model.Resource;
import com.itlize.korera.repository.ResourceRepository;
import com.itlize.korera.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceRepository resourceRepository;

    @Override
    public Resource createResource(Resource resource) {
        return resourceRepository.save(resource);
    }

    @Override
    public Resource updateResource(Long id, Resource resource) {
        Resource existingResource = resourceRepository.findById(id).orElseThrow(() -> new RuntimeException("Resource not found"));
        existingResource.setResourceName(resource.getResourceName());
        existingResource.setResourceCode(resource.getResourceCode());
        existingResource.setType(resource.getType());
        existingResource.setDescription(resource.getDescription());
        existingResource.setCategory(resource.getCategory());
        return resourceRepository.save(existingResource);
    }

    @Override
    public void deleteResource(Long id) {
        resourceRepository.deleteById(id);
    }

    @Override
    public Resource getResourceById(Long id) {
        return resourceRepository.findById(id).orElseThrow(() -> new RuntimeException("Resource not found"));
    }

    @Override
    public List<Resource> getAllResources() {
        return resourceRepository.findAll();
    }
}
