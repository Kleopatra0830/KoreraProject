package com.itlize.korera.controller;

import com.itlize.korera.model.Resource;
import com.itlize.korera.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resources")
public class ResourceController {

    // @Autowired
    // private ResourceService resourceService;

    // @PostMapping
    // public ResponseEntity<Resource> createResource(@RequestBody Resource resource) {
    //     Resource createdResource = resourceService.createResource(resource);
    //     return new ResponseEntity<>(createdResource, HttpStatus.CREATED);
    // }

    // @PutMapping("/{id}")
    // public ResponseEntity<Resource> updateResource(@PathVariable Long id, @RequestBody Resource resource) {
    //     Resource updatedResource = resourceService.updateResource(id, resource);
    //     return new ResponseEntity<>(updatedResource, HttpStatus.OK);
    // }

    // @DeleteMapping("/{id}")
    // public ResponseEntity<Void> deleteResource(@PathVariable Long id) {
    //     resourceService.deleteResource(id);
    //     return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    // }

    // @GetMapping("/{id}")
    // public ResponseEntity<Resource> getResourceById(@PathVariable Long id) {
    //     Resource resource = resourceService.getResourceById(id);
    //     return new ResponseEntity<>(resource, HttpStatus.OK);
    // }

    // @GetMapping
    // public ResponseEntity<List<Resource>> getAllResources() {
    //     List<Resource> resources = resourceService.getAllResources();
    //     return new ResponseEntity<>(resources, HttpStatus.OK);
    // }
}
