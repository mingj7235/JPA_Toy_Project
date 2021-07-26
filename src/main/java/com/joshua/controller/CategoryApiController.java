package com.joshua.controller;

import com.joshua.domain.Category;
import com.joshua.dto.CategoryDTO;
import com.joshua.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class CategoryApiController {

    private final CategoryService categoryService;

    @PostMapping ("/categories")
    @ResponseBody
    public Long saveCategory (CategoryDTO categoryDTO) {
        return categoryService.saveCategory(categoryDTO);
    }

//    @GetMapping ("/categories/{id}")
//    @ResponseBody
//    public Map<String, CategoryDTO> getCategory (@PathVariable Long id) {
//        return categoryService.getCategory(id);
//    }

//    @GetMapping ("/categories/{branch}/{name}")
//    @ResponseBody
//    public Map <String, CategoryDTO> getCategoryByBranchAndName (@PathVariable String branch, @PathVariable String name) {
//        return categoryService.getCategoryByBranchAndName(branch, name);
//    }

    @GetMapping ("/categories/{branch}")
    @ResponseBody
    public Map<String, CategoryDTO> getCategoryByBranch (@PathVariable String branch) {
        //branch명으로만 조회
        return categoryService.getCategoryByBranch(branch);

    }

    @PutMapping ("/categories/{categoryId}")
    @ResponseBody
    public Long updateCategory (@PathVariable Long categoryId, CategoryDTO categoryDTO) {
        return categoryService.updateCategory(categoryId,categoryDTO);
    }

    @DeleteMapping ("/categories/{categoryId}")
    @ResponseBody
    public void deleteCategory (@PathVariable Long categoryId) {
        categoryService.deleteCategory(categoryId);

    }



}
