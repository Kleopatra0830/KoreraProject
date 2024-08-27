package com.itlize.korera.service.impl;

import com.itlize.korera.model.Resource;
import com.itlize.korera.model.ResourceDetail;
import com.itlize.korera.repository.ResourceRepository;
import com.itlize.korera.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceRepository resourceRepository;

    @Override
    @Transactional
    public Resource createResource(Resource resource) {
        // Link each ResourceDetail to the Resource
        // for (ResourceDetail detail : resource.getResourceDetails()) {
        //     detail.setResource(resource);
        // }
        
        // Save the Resource, which will cascade the save to ResourceDetails
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
    public Optional<Resource> getResourceById(Long id) {
        return resourceRepository.findById(id);
    }

    @Override
    public List<Resource> getAllResources() {
        return resourceRepository.findAll();
    }
}
