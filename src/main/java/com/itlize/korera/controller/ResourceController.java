package com.itlize.korera.controller;

import com.itlize.korera.model.Resource;
import com.itlize.korera.model.ResourceDetail;
import com.itlize.korera.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/resources")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    // Create a new Resource
    @PostMapping("/create")
    public ResponseEntity<Resource> createResource(@RequestBody Resource resource) {
        Resource createdResource = resourceService.createResource(resource);
        return new ResponseEntity<>(createdResource, HttpStatus.CREATED);
    }

    // Get a Resource by ID
    @GetMapping("/read/{id}")
    public ResponseEntity<Resource> getResourceById(@PathVariable Long id) {
        Optional<Resource> resource = resourceService.getResourceById(id);
        return resource.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Get all Resources
    @GetMapping("/getAll")
    public ResponseEntity<List<Resource>> getAllResources() {
        List<Resource> resources = resourceService.getAllResources();
        return new ResponseEntity<>(resources, HttpStatus.OK);
    }

    // Update an existing Resource
    @PutMapping("/update/{id}")
    public ResponseEntity<Resource> updateResource(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        Resource existingResource = resourceService.getResourceById(id)
                                .orElseThrow(() -> new RuntimeException("Resource not found"));

        // Check each field and update if present in the request body
        if (updates.containsKey("resourceName")) {
            existingResource.setResourceName((String) updates.get("resourceName"));
        }

        if (updates.containsKey("type")) {
            existingResource.setType((String) updates.get("type"));
        }

        if (updates.containsKey("description")) {
            existingResource.setDescription((String) updates.get("description"));
        }

        // if (updates.containsKey("resourceDetails")) {
        //     ResourceDetail existingDetail = existingResource.getResourceDetail();

        //     // Assuming resourceDetails is provided as a list of key-value pairs
        //     @SuppressWarnings("unchecked")
        //     List<Map<String, String>> detailsList = (List<Map<String, String>>) updates.get("resourceDetails");

        //     if (existingDetail == null) {
        //         existingDetail = new ResourceDetail();
        //     }

        //     for (Map<String, String> detailMap : detailsList) {
        //         existingDetail.setDetailName(detailMap.get("key"));
        //         existingDetail.setDetailValue(detailMap.get("value"));
        //     }

        //     existingDetail.setResource(existingResource);
        //     existingResource.setResourceDetail(existingDetail);
        // }

        Resource updatedResource = resourceService.updateResource(existingResource.getResourceId(), existingResource);
        return new ResponseEntity<>(updatedResource, HttpStatus.OK);
    }




    // Delete a Resource
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteResource(@PathVariable Long id) {
        try {
            resourceService.deleteResource(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
