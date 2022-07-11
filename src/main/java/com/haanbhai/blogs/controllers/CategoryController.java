package com.haanbhai.blogs.controllers;

import com.haanbhai.blogs.payloads.ApiResponse;
import com.haanbhai.blogs.payloads.CategoryDTO;
import com.haanbhai.blogs.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @PostMapping("/")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO catDTO)
    {
        CategoryDTO createdCategory = this.categoryService.createCategory(catDTO);
        return new ResponseEntity<CategoryDTO>(createdCategory, HttpStatus.CREATED);
    }
    @PutMapping("/{catId}")
    public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO catDTO, @PathVariable Integer catId)
    {
        CategoryDTO updateCategory = this.categoryService.updateCategory(catDTO, catId);
        return new ResponseEntity<CategoryDTO>(updateCategory,HttpStatus.OK);
    }
    @DeleteMapping("/{catId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer catId)
    {
        this.categoryService.deleteCategory(catId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Category deleted successfully",true),HttpStatus.OK);
    }
    @GetMapping("/{catId}")
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable Integer catId)
    {
        CategoryDTO foundCat = this.categoryService.getCategory(catId);
        return new ResponseEntity<CategoryDTO>(foundCat, HttpStatus.OK);
    }
    @GetMapping("/")
    public ResponseEntity<List<CategoryDTO>> getAllCategories()
    {
        List<CategoryDTO> cats = this.categoryService.getAllCategories();
        return new ResponseEntity<List<CategoryDTO>>(cats, HttpStatus.OK);
    }
}
