package com.joshua.service;

import com.joshua.domain.Category;
import com.joshua.dto.CategoryDTO;
import com.joshua.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Long saveCategory (CategoryDTO categoryDTO) {

        Category category = categoryDTO.toEntity();

        //대분류 등록
        if (categoryDTO.getParentCategoryName() == null) {
            category.setLevel(1);

            //대분류일 경우, 같은 branch와 name을 가지면 안됌
            for (Category temp : categories()) {
                if (temp.getBranch().equals(category.getBranch()) && temp.getName().equals(category.getName())){
                    throw new RuntimeException("branch와 name이 같은 대분류는 있을 수 없습니다.");
                }
            }
        //중, 소분류 등록
        } else {
            String parentCategoryName = categoryDTO.getParentCategoryName();
            Category parentCategory = categoryRepository.findByName(parentCategoryName)
                    .orElseThrow(() -> new IllegalArgumentException("부모 카테고리 없음 예외"));

            //parent와 children의 branch가 다를 경우
            if(!parentCategory.getBranch().equals(category.getBranch())) {
                throw new RuntimeException("부모와 카테고리가 다릅니다. ");

            }

            if (!parentCategory.isLive()) {
                throw new RuntimeException("부모 카테고리 찾을 수 없습니다.");
            }

            category.setLevel(parentCategory.getLevel() + 1);
            category.setParentCategory(parentCategory);
            parentCategory.getSubCategory().add(category);
        }

        category.setLive(true);
        return categoryRepository.save(category).getId();
    }

    //카테고리 찾기 #1 : id로 찾기
    public Map <String, CategoryDTO> getCategory (Long categoryId) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("찾는 카테고리 없슴"));

        CategoryDTO categoryDTO = new CategoryDTO(category);

        Map<String, CategoryDTO> data = new HashMap<>();

        data.put(category.getName(), categoryDTO);
        System.out.println(data);
        return data;

    }

    //카테고리 찾기 #2 : branch와 name으로 찾기
    public Map <String, CategoryDTO> getCategoryByBranchAndName(String branch, String name) {
        Category category = categoryRepository.findByBranchAndName(branch, name)
                .orElseThrow(() -> new IllegalArgumentException("찾는 대분류 없슴"));

        CategoryDTO categoryDTO = new CategoryDTO(category);

        Map<String, CategoryDTO> data = new HashMap<>();
        data.put(categoryDTO.getName(), categoryDTO);

        return data;
    }


    public Long updateCategory (Long categoryId, CategoryDTO categoryDTO) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("카테고리 없슴"));

        category.setName(categoryDTO.getName());

        return category.getId();
    }

    public void deleteCategory (Long categoryId) {

    }

    //모든 카테고리 찾기 메소드
    public List<Category> categories () {
        return categoryRepository.findAll();
    }

}
