package com.joshua.controller;

import com.joshua.domain.Category;
import com.joshua.dto.CategoryDTO;
import com.joshua.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @GetMapping ("/categories/{id}")
    @ResponseBody
    public Map<String, CategoryDTO> getCategory (@PathVariable Long id) {
        return categoryService.getCategory(id);
    }
}
