package com.haanbhai.blogs.services.impl;

import com.haanbhai.blogs.entities.Category;
import com.haanbhai.blogs.exceptions.ResourceNotFoundException;
import com.haanbhai.blogs.payloads.CategoryDTO;
import com.haanbhai.blogs.repositiories.CategoryRepo;
import com.haanbhai.blogs.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category newCat = this.modelMapper.map(categoryDTO,Category.class);
        Category cat = this.categoryRepo.save(newCat);
        return this.modelMapper.map(cat,CategoryDTO.class);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer categoryId) {
        Category cat = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category"," category Id ", categoryId));
        cat.setCategoryTitle(categoryDTO.getCategoryTitle());
        cat.setCategoryDescription(categoryDTO.getCategoryDescription());
        Category updCat = this.categoryRepo.save(cat);
        return this.modelMapper.map(updCat,CategoryDTO.class);
    }

    @Override
    public void deleteCategory(Integer categoryId) {

    }

    @Override
    public CategoryDTO getCategory(Integer categoryId) {
        return null;
    }

    @Override
    public List<CategoryDTO> getAllCategory() {
        return null;
    }
}
