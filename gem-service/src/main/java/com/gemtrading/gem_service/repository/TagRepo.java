package com.gemtrading.gem_service.repository;

import com.gemtrading.gem_service.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TagRepo extends JpaRepository<Tag, Long> {

    boolean existsByNameIgnoreCase(String name);

    Optional<Tag> findByNameIgnoreCase(String name);
}