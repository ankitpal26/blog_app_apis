package com.app.blog.services;

import java.util.List;

import com.app.blog.payloads.CategoryDto;

public interface CategoryService {
	
	//create
	 CategoryDto createCategory(CategoryDto categoryDto);
	//update
	 CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);
	//delete
	 public void deleteCategory(Integer categoryId);
	//get single
	 CategoryDto getCategory(Integer categoryId);
	//get all
	 List<CategoryDto> getCategories();
}
