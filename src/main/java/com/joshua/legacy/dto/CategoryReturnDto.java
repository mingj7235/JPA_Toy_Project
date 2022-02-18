package com.joshua.legacy.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@Builder
public class CategoryReturnDto {

    private Long max_level;

    private Map<String, CategoryDTO> categories;

}
