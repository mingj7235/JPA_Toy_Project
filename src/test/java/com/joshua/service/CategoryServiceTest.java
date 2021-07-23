package com.joshua.service;

import com.joshua.domain.Category;
import com.joshua.dto.CategoryDTO;
import com.joshua.repository.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class CategoryServiceTest {
    @Autowired
    CategoryService categoryService;
    @Autowired
    CategoryRepository categoryRepository;

    @Test
    public void saveCategoryTest () {

        //given
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setBranch("TestBranch");
        categoryDTO.setCode("TestCode");
        categoryDTO.setLevel(1);
        categoryDTO.setName("TestName");

        Category category = categoryDTO.toEntity();


        //when

        Long saveId = categoryService.saveCategory(categoryDTO);

        //then

        Map<String, CategoryDTO> findCategory = categoryService
                .getCategoryByBranch(categoryDTO.getBranch());

//        assertThat(categoryDTO.getBranch())
//                .isEqualTo(findCategory.get(categoryDTO.getName()).getBranch());
        assertThat(saveId).isEqualTo(findCategory.get("TestName").getCategoryId());
    }
}