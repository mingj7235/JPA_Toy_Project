package com.joshua.domain;

import com.joshua.dto.CategoryDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Category {

    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false)
    private String branch;

    private String code;

    private String name;

    @ManyToOne
    @JoinColumn (name ="parent_cagegory_id")
    private Category parentCategory;

    @OneToMany (mappedBy = "parentCategory", cascade = CascadeType.ALL)
    private List<Category> subCategory = new ArrayList<>();
    //private Map<String, Category> subCategory = new HashMap<>();
    private Integer level;

    private boolean live;

    @Builder
    public Category(String branch, String code, String name, Category parentCategory) {
        this.branch = branch;
        this.code = code;
        this.name = name;
        this.parentCategory = parentCategory;
    }
}
