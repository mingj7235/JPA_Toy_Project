package com.joshua.dto;

import com.joshua.domain.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
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
    private List<CategoryDTO> children;

    public CategoryDTO (Category entity) {
        this.categoryId = entity.getId();
        this.branch = entity.getBranch();
        this.code = entity.getCode();
        this.name = entity.getName();
        //this.level = entity.getLevel();
        if(entity.getParentCategory() == null) {
           // this.parent_category_id = entity.getId();
            this.parentCategoryName = entity.getName();

        } else {
           // this.parent_category_id = entity.getParentCategory().getId();
            this.parentCategoryName = entity.getParentCategory().getName();
        }
        //this.live = entity.isLive();
        this.children = entity.getSubCategory().stream()
                .map(CategoryDTO::new).collect(Collectors.toList());
    }

    public Category toEntity () {
        return Category.builder()
                .branch(branch)
                .code(code)
                .name(name)
                .build();
    }

}
