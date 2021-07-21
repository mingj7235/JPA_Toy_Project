package com.joshua.dto;

import com.joshua.domain.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDTO {

    private Long categoryId;
    private String branch;
    private String code;
    private String name;
    private Long parent_category_id;
    private Integer level;
    private boolean live;

    public CategoryDTO (Category entity) {
        this.categoryId = entity.getId();
        this.branch = entity.getBranch();
        this.code = entity.getCode();
        this.name = entity.getName();
        this.level = entity.getLevel();
        if(entity.getSubCategory() == null) {

        } else {
            this.parent_category_id = entity.getParentCategory().getId();
        }
        this.live = entity.isLive();
    }

    public Category toEntity () {
        return Category.builder()
                .branch(branch)
                .code(code)
                .name(name)
                .build();
    }

}
