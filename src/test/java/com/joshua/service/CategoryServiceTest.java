package com.joshua.service;

import com.joshua.domain.Category;
import com.joshua.dto.CategoryDTO;
import com.joshua.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

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


        Category category = categoryDTO.toEntity();



        //when

        Long saveId = categoryService.saveCategory(categoryDTO);

        //then

        /**
         * //given
         *         Member member = new Member();
         *         member.setName("spring");
         *
         *         //when
         *         Long saveId = memberService.join(member);
         *
         *         //then
         *         Member findMember = memberService.findeOne(saveId).get();
         *         Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
         */

    }





}