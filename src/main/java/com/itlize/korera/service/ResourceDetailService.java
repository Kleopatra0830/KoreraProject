package com.itlize.korera.service;

import com.itlize.korera.model.ResourceDetail;

import java.util.List;
import java.util.Optional;

public interface ResourceDetailService {

    ResourceDetail createResourceDetail(ResourceDetail resourceDetail);

    Optional<ResourceDetail> getResourceDetailById(Long id);

    List<ResourceDetail> getAllResourceDetails();

    ResourceDetail updateResourceDetail(Long id, ResourceDetail resourceDetail);

    void deleteResourceDetail(Long id);
}
