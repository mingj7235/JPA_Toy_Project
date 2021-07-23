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
    public void 카테고리_저장_테스트 () {
        //given
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setBranch("TestBranch");
        categoryDTO.setCode("TestCode");
        categoryDTO.setName("TestName");

        //when

        Long saveId = categoryService.saveCategory(categoryDTO);

        //then

        Map<String, CategoryDTO> findCategory = categoryService
                .getCategoryByBranchAndName(categoryDTO.getBranch(), categoryDTO.getName());

        assertThat(saveId).isEqualTo(findCategory.get("TestName").getCategoryId());
    }

    @Test
    public void 카테고리_업데이트_테스트 () {
        //given
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setBranch("TestBranch");
        categoryDTO.setCode("TestCode");
        categoryDTO.setName("TestName");
        categoryService.saveCategory(categoryDTO);


        //when

        CategoryDTO targetCategory = categoryService
                .getCategoryByBranchAndName(categoryDTO.getBranch(), categoryDTO.getName()).get(categoryDTO.getName());
        targetCategory.setName("UpdateName");
        categoryService.updateCategory(targetCategory.getCategoryId(), targetCategory);

        //then
        assertThat(targetCategory.getName()).isEqualTo("UpdateName");
    }

    @Test
    public void 카테고리_삭제_테스트_대분류 () {
        //given
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setBranch("TestBranch");
        categoryDTO.setCode("TestCode");
        categoryDTO.setName("TestName");
        Long targetId = categoryService.saveCategory(categoryDTO);

        //when

        categoryService.deleteCategory(targetId);

        //then

        IllegalArgumentException e =
                assertThrows(IllegalArgumentException.class, () -> categoryService.findCategory(targetId));
        assertThat(e.getMessage()).isEqualTo("찾는 카테고리 없습니다.");

    }

    @Test
    public void 카테고리_삭제_테스트_소분류() {
        //given
        CategoryDTO parentCategory = new CategoryDTO();
        parentCategory.setBranch("branch");
        parentCategory.setCode("parent");
        parentCategory.setName("parent");
        Long parentId = categoryService.saveCategory(parentCategory);

        CategoryDTO childCategory = new CategoryDTO();
        childCategory.setBranch("branch");
        childCategory.setCode("child");
        childCategory.setName("child");
        childCategory.setParentCategoryName("parent");
        Long childId = categoryService.saveCategory(childCategory);

        //when

        categoryService.deleteCategory(parentId);

        String targetMame = categoryService.findCategory(parentId).getName();

        //then
        //assertThat(parentCategory.getName()).isEqualTo("Deleted category");
        //이건 dto의 name이기때문에 service에서 바꾼 setName은 Entity를 변화시킨 것이므로
        //당연히 못찾는다.

        assertThat(targetMame).isEqualTo("Deleted category");
    }
}