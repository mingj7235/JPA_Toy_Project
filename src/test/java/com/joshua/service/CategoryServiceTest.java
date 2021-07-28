package com.joshua.service;

import com.joshua.domain.Category;
import com.joshua.dto.CategoryDTO;
import com.joshua.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class CategoryServiceTest {
    @Autowired
    CategoryService categoryService;
    @Autowired
    CategoryRepository categoryRepository;

    //SavedID
    private CategoryDTO createCategoryDTO(String testBranch, String testCode, String testName) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setBranch(testBranch);
        categoryDTO.setCode(testCode);
        categoryDTO.setName(testName);
        return categoryDTO;
    }

    //Find Category

    private Category findCategory (Long savedId) {
        return categoryRepository.findById(savedId).orElseThrow(IllegalArgumentException::new);
    }

    @Test
    public void 카테고리_저장_테스트 () {
        //given
        CategoryDTO categoryDTO = createCategoryDTO("TestBranch", "TestCode", "TestName");
        Long savedId = categoryService.saveCategory(categoryDTO);

        //when
        Category category = findCategory(savedId);

        //then
        assertThat(category.getCode()).isEqualTo("TestCode");

    }


    @Test
    public void 카테고리_업데이트_테스트 () {
        //given
        CategoryDTO categoryDTO = createCategoryDTO("TestBranch", "TestCode", "TestName");
        Long savedId = categoryService.saveCategory(categoryDTO);

        Category category = findCategory(savedId);
        CategoryDTO targetCategory = new CategoryDTO(category);
        targetCategory.setName("UpdateCategory");
        //when

        Long updateId = categoryService.updateCategory("TestBranch", "TestCode", targetCategory);
        Category updatedCategory = findCategory(updateId);

        //then
        assertThat(updatedCategory.getName()).isEqualTo("UpdateCategory");
    }

    @Test
    public void 카테고리_삭제_테스트 () {
        //given
        CategoryDTO categoryDTO = createCategoryDTO("TestBranch", "TestCode", "TestName");
        Long savedId = categoryService.saveCategory(categoryDTO);
        //when

        categoryService.deleteCategoryOld(savedId);

        //then

        IllegalArgumentException e =
                assertThrows(IllegalArgumentException.class, () -> categoryService.findCategoryOld(savedId));
        assertThat(e.getMessage()).isEqualTo("찾는 카테고리 없습니다.");

    }

    @Test
    public void 카테고리_삭제_테스트_소분류() {
        //given
//        Long parentId = getSavedId("branch", "parent", "parent");
//
//        Long childId = getSavedId("branch", "child", "child");
//
//        //when
//
//        categoryService.deleteCategoryOld(parentId);
//
//        String targetMame = categoryService.findCategoryOld(parentId).getName();
//
//        //then
//        //assertThat(parentCategory.getName()).isEqualTo("Deleted category");
//        //이건 dto의 name이기때문에 service에서 바꾼 setName은 Entity를 변화시킨 것이므로
//        //당연히 못찾는다.
//
//        assertThat(targetMame).isEqualTo("Deleted category");
    }
}