package com.joshua.service;

import com.joshua.domain.Category;
import com.joshua.dto.CategoryDTO;
import com.joshua.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
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

        //Refector
        //문제 : 사용자가 branch나 parent name을 오타를 냈을 경우 else로 빠져서 새로운 root를 생성하게 된다.

//        Category parent = categoryRepository.findByBranchAndName(category.getBranch(), categoryDTO.getParentCategoryName()).orElse(
//                Category.builder()
//                //parent를 찾기 위해서 repository를 검색하는데, 없다면 대분류를 만드는 것이므로 그위에 root container를 생성.
//                .branch(category.getBranch())
//                .code("ROOT")
//                .name("ROOT")
//                .build());
//        category.setParentCategory(parent);
//        category.setLevel(parent.getLevel() + 1);
//
//        try {
//            parent.getSubCategory().add(categoryRepository.save(category));
//            categoryRepository.save(parent);
//        } catch (Exception e) {
//            throw new RuntimeException("");
//        }
//
//        return category.getId();
//    }

        //////////////////////////////// saveCategory () Refector 전 코드 /////////////////////////////
        //대분류 등록
        if (categoryDTO.getParentCategoryName() == null) {

            //JPA 사용하여 DB에서 branch와 name의 중복값을 검사. (대분류에서만 가능)
            if (categoryRepository.existsByBranchAndName(categoryDTO.getBranch(), categoryDTO.getName())) {
                throw new RuntimeException("branch와 name이 같을 수 없습니다. ");
            }

            //ROOT 만들기 -> 고객이 branch로만 검색햇을 때 root가 나오게 !

            //ROOT가 없는 경우 (branch 최초)

            //orElse로 refactor
            Category rootCategory = categoryRepository.findByBranchAndName(categoryDTO.getBranch(),"ROOT")
                    .orElse(
                            Category.builder()
                            .name("ROOT")
                            .code("ROOT")
                            .level(0)
                            .build()
                    );
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>"+rootCategory);
            //ROOT를 중복으로 넣으면 안된다.
            categoryRepository.save(rootCategory);
            category.setParentCategory(rootCategory);



            category.setLevel(1);

//            if (!categoryRepository.existsByBranchAndName(categoryDTO.getBranch(), "ROOT")) {
//                Category rootCategory = categoryDTO.toEntity();
//                rootCategory.setName("ROOT");
//                rootCategory.setLevel(0);
//                rootCategory.setCode("ROOT");
//                categoryRepository.save(rootCategory);
//                category.setParentCategory(rootCategory);
//                //ROOT가 있는 경우
//            } else {
//                Category rootCategory = categoryRepository.findByName("ROOT")
//                        .orElseThrow(() -> new IllegalArgumentException("ROOT 없슴 "));
//                category.setParentCategory(rootCategory);
//            }


            //대분류일 경우, 같은 branch와 name을 가지면 안됌
            // SELECT count(*) from category WHERE branch = :baranch AND name = :name;
//            for (Category temp : categories()) {
//                if (temp.getBranch().equals(category.getBranch()) && temp.getName().equals(category.getName())){
//                    throw new RuntimeException("branch와 name이 같은 대분류는 있을 수 없습니다.");
//                }
//            }
        //중, 소분류 등록
        } else {
            String parentCategoryName = categoryDTO.getParentCategoryName();
            Category parentCategory = categoryRepository.findByBranchAndName(categoryDTO.getBranch(), parentCategoryName)
                    .orElseThrow(() -> new IllegalArgumentException("부모 카테고리 없음 예외"));

            //parent와 children의 branch가 다를 경우
//            if(!parentCategory.getBranch().equals(category.getBranch())) {
//                throw new RuntimeException("부모와 카테고리가 다릅니다. ");
//            }

//            if (!parentCategory.isLive()) {
//                throw new RuntimeException("부모 카테고리 찾을 수 없습니다.");
//            }

            category.setLevel(parentCategory.getLevel() + 1);
            category.setParentCategory(parentCategory);
            parentCategory.getSubCategory().add(category);
        }

        //category.setLive(true);
        return categoryRepository.save(category).getId();
    }
////////////////////////////

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

    public Map <String, CategoryDTO> getCategoryByBranch (String branch) {
        Category category = categoryRepository.findByBranchAndName(branch, "ROOT")
                .orElseThrow(() -> new IllegalArgumentException("찾는 대분류가 없습니다"));

        CategoryDTO categoryDTO = new CategoryDTO(category);

        Map <String, CategoryDTO> data = new HashMap<>();
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
