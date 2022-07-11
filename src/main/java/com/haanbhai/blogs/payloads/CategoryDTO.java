package com.haanbhai.blogs.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDTO {
    private Integer categoryId;
    @NotBlank
    @Size(min = 3, message = "Category title should be atleast 3 charactes long")
    private String categoryTitle;
    @Size(min = 10, message = "Please provide more description")
    private String categoryDescription;
}
