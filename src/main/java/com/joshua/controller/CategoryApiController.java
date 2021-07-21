package com.joshua.controller;

import com.joshua.domain.Category;
import com.joshua.dto.CategoryDTO;
import com.joshua.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class CategoryApiController {

    private final CategoryService categoryService;

    @PostMapping ("/categories")
    @ResponseBody
    public Map<String, Category> saveCategory (CategoryDTO categoryDTO) {
        return categoryService.saveCategory(categoryDTO);
    }
}
