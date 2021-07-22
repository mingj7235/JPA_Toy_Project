package com.joshua.dto;

import com.joshua.domain.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDTO {

    private Long categoryId;
    private String branch;
    private String code;
    private String name;
    //private Long parent_category_id;
    private String parentCategoryName;
    //private Integer level;
    //private boolean live;
    //private List<CategoryDTO> children;
    private Map<String, CategoryDTO> children;

    public CategoryDTO (Category entity) {

        this.categoryId = entity.getId();
        this.branch = entity.getBranch();
        this.code = entity.getCode();
        this.name = entity.getName();
        //this.level = entity.getLevel();
        if(entity.getParentCategory() == null) {
           // this.parent_category_id = entity.getId();
            this.parentCategoryName = "대분류";

        } else {
           // this.parent_category_id = entity.getParentCategory().getId();
            this.parentCategoryName = entity.getParentCategory().getName();
        }
        //this.live = entity.isLive();
        this.children = entity.getSubCategory() == null ? null : entity.getSubCategory().stream().collect(Collectors.toMap(
                Category::getCode, CategoryDTO::new
        ));


    }

    public Category toEntity () {
        return Category.builder()
                .branch(branch)
                .code(code)
                .name(name)
                .build();
    }

}
