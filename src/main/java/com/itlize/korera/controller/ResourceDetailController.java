package com.itlize.korera.controller;

import com.itlize.korera.model.ResourceDetail;
import com.itlize.korera.service.ResourceDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/resource-details")
public class ResourceDetailController {

    @Autowired
    private ResourceDetailService resourceDetailService;

    // Create a new ResourceDetail
    @PostMapping("/create")
    public ResponseEntity<ResourceDetail> createResourceDetail(@RequestBody ResourceDetail resourceDetail) {
        ResourceDetail createdResourceDetail = resourceDetailService.createResourceDetail(resourceDetail);
        return new ResponseEntity<>(createdResourceDetail, HttpStatus.CREATED);
    }

    // Get a ResourceDetail by ID
    @GetMapping("/read/{id}")
    public ResponseEntity<ResourceDetail> getResourceDetailById(@PathVariable Long id) {
        Optional<ResourceDetail> resourceDetail = resourceDetailService.getResourceDetailById(id);
        return resourceDetail.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Get all ResourceDetails
    @GetMapping("/getAll")
    public ResponseEntity<List<ResourceDetail>> getAllResourceDetails() {
        List<ResourceDetail> resourceDetails = resourceDetailService.getAllResourceDetails();
        return new ResponseEntity<>(resourceDetails, HttpStatus.OK);
    }

    // Update an existing ResourceDetail
    @PutMapping("/update/{id}")
    public ResponseEntity<ResourceDetail> updateResourceDetail(@PathVariable Long id, @RequestBody ResourceDetail resourceDetail) {
        ResourceDetail updatedResourceDetail = resourceDetailService.updateResourceDetail(id, resourceDetail);
        return new ResponseEntity<>(updatedResourceDetail, HttpStatus.OK);
    }

    // Delete a ResourceDetail
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteResourceDetail(@PathVariable Long id) {
        try {
            resourceDetailService.deleteResourceDetail(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
