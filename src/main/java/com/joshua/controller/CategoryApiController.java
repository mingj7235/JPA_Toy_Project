package com.joshua.controller;

import com.joshua.domain.Category;
import com.joshua.dto.CategoryDTO;
import com.joshua.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class CategoryApiController {

    private final CategoryService categoryService;

    @PostMapping ("/categories/{key}")
    @ResponseBody
    public Map<String, Category> saveCategory (@PathVariable String key, CategoryDTO categoryDTO) {
        return categoryService.saveCategory(categoryDTO);
    }
}
