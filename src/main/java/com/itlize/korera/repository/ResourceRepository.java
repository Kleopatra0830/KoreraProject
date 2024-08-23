package com.itlize.korera.repository;

import com.itlize.korera.model.Resource;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Long> {
    List<Resource> findByResourceName(String resourceName);
    List<Resource> findByResourceCode(int resourceCode);
}
