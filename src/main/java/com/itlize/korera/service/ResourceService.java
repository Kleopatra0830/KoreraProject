package com.itlize.korera.service;

import com.itlize.korera.model.Resource;

import java.util.List;

public interface ResourceService {
    Resource createResource(Resource resource);
    Resource updateResource(Long id, Resource resource);
    void deleteResource(Long id);
    Resource getResourceById(Long id);
    List<Resource> getAllResources();
}
