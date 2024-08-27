package com.itlize.korera.service.impl;

import com.itlize.korera.model.ResourceDetail;
import com.itlize.korera.repository.ResourceDetailRepository;
import com.itlize.korera.service.ResourceDetailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResourceDetailServiceImpl implements ResourceDetailService {

    @Autowired
    private ResourceDetailRepository resourceDetailRepository;

    @Override
    public ResourceDetail createResourceDetail(ResourceDetail resourceDetail) {
        return resourceDetailRepository.save(resourceDetail);
    }

    @Override
    public Optional<ResourceDetail> getResourceDetailById(Long id) {
        return resourceDetailRepository.findById(id);
    }

    @Override
    public List<ResourceDetail> getAllResourceDetails() {
        return resourceDetailRepository.findAll();
    }

    @Override
    public ResourceDetail updateResourceDetail(Long id, ResourceDetail resourceDetail) {
        ResourceDetail existingResourceDetail = resourceDetailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ResourceDetail not found"));

        existingResourceDetail.setDetailName(resourceDetail.getDetailName());
        existingResourceDetail.setDetailValue(resourceDetail.getDetailValue());
        existingResourceDetail.setResource(resourceDetail.getResource());
        existingResourceDetail.setTemplate(resourceDetail.getTemplate());

        return resourceDetailRepository.save(existingResourceDetail);
    }

    @Override
    public void deleteResourceDetail(Long id) {
        resourceDetailRepository.deleteById(id);
    }
}
