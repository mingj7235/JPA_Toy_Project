package com.joshua.legacy.dto;

import com.joshua.legacy.domain.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;
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
    //private String parentCategoryName;
    private String parentCategoryCode;
    private Integer level;
    //private boolean live;
    //private List<CategoryDTO> children;
    private Map<String, CategoryDTO> children;

    public CategoryDTO (Category entity) {

        this.categoryId = entity.getId();
        this.branch = entity.getBranch();
        this.code = entity.getCode();
        this.name = entity.getName();
        this.level = entity.getLevel();
        if(entity.getParentCategory() == null) {
            //this.parentCategoryName = "대분류";
            this.parentCategoryCode = entity.getCode();

        } else {
            //this.parentCategoryName = entity.getParentCategory().getName();
            this.parentCategoryCode = entity.getParentCategory().getCode();

        }
        //this.live = entity.isLive();
        this.children = entity.getSubCategory() == null ? null :
                entity.getSubCategory().stream().collect(Collectors.toMap(
                category -> category.getCode(), category -> new CategoryDTO(category)
                        // Category::getCode, CategoryDTO::new /* value 값 : category를 CategoryDTO생성자에 넣는것임 */
        ));
    }

    public Category toEntity () {
        return Category.builder()
                .branch(branch)
                .code(code)
                .level(level)
                .name(name)
                .build();
    }
}
