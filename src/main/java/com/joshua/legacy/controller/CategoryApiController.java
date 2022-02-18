package com.joshua.legacy.controller;

import com.joshua.legacy.dto.CategoryDTO;
import com.joshua.legacy.dto.CategoryReturnDto;
import com.joshua.legacy.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

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
    public CategoryReturnDto getCategoryByBranch (@PathVariable String branch) {
        return categoryService.getCategoryByBranch(branch);
    }

    @PutMapping ("/categories/{branch}/{code}")
    @ResponseBody
    public Long updateCategory (@PathVariable (name = "branch") @NotBlank String branch,
                                @PathVariable (name = "code") @NotBlank String code,
                                CategoryDTO categoryDTO) {
        return categoryService.updateCategory(branch, code,categoryDTO);
    }

    @DeleteMapping ("/categories/{branch}/{code}")
    @ResponseBody
    public void deleteCategory (@PathVariable (name = "branch") @NotBlank String branch,
                                @PathVariable (name = "code") @NotBlank String code) {
        categoryService.deleteCategory(branch, code);

    }



}
