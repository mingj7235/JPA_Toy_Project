package com.joshua.legacy.repository;

import com.joshua.legacy.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByName (String name);
    Optional<Category> findByBranchAndCode (String branch, String name);

    Boolean existsByBranchAndName(String branch, String name);

    //Category는 객체명이므로 앞이 대문자여야한다.
    @Query (value = "SELECT MAX(c.level) FROM Category c WHERE c.branch = :branch")
    Long maxLevel (@Param("branch") String branch);

    int deleteByBranchAndCode (String branch, String code);
}