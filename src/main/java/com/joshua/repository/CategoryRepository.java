package com.joshua.repository;

import com.joshua.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByName (String name);
    Optional<Category> findByBranchAndCode (String branch, String name);

    Boolean existsByBranchAndName(String branch, String name);

    @Query (value = "SELECT MAX(c.level) FROM category c WHERE c.branch = :branch")
    Long maxLevel (@Param("branch") String branch);
}