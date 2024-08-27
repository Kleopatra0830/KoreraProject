package com.itlize.korera.service;

import com.itlize.korera.model.Resource;

import java.util.List;
import java.util.Optional;

public interface ResourceService {
    Resource createResource(Resource resource);
    Resource updateResource(Long id, Resource resource);
    void deleteResource(Long id);
    Optional<Resource> getResourceById(Long id);
    List<Resource> getAllResources();
}
