package com.joshua.repository;

import com.joshua.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByName (String name);
    Optional<Category> findByBranchAndCode (String branch, String name);

    Boolean existsByBranchAndName(String branch, String name);
}