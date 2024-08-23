package com.itlize.korera.repository;

import com.itlize.korera.model.ResourceDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceDetailRepository extends JpaRepository<ResourceDetail, Long> {
}
