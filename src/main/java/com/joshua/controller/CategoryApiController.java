package com.joshua.controller;

import com.joshua.domain.Category;
import com.joshua.dto.CategoryDTO;
import com.joshua.dto.CategoryReturnDto;
import com.joshua.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
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
    public CategoryReturnDto getCategoryByBranch (@PathVariable String branch) {
        //branch명으로만 조회
        return categoryService.getCategoryByBranchWithLevel(branch);
    }

    @PutMapping ("/categories/{branch}/{code}")
    @ResponseBody
    public Long updateCategory (@PathVariable (name = "branch") @NotBlank String branch,
                                @PathVariable (name = "code") @NotBlank String code,
                                CategoryDTO categoryDTO) {
        return categoryService.updateCategory(branch, code,categoryDTO);
    }

    @DeleteMapping ("/categories/{categoryId}")
    @ResponseBody
    public void deleteCategory (@PathVariable (name = "branch") @NotBlank String branch,
                                @PathVariable (name = "code") @NotBlank String code) {
        categoryService.deleteCategory(branch, code);

    }



}
