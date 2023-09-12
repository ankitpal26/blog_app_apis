package com.app.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.blog.entities.Category;
import com.app.blog.exceptions.ResourceNotFoundException;
import com.app.blog.payloads.CategoryDto;
import com.app.blog.repositories.CategoryRepo;
import com.app.blog.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepo categoryRepo;

	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category Cat = this.modelMapper.map(categoryDto,  Category.class);
		Category addedCat = this.categoryRepo.save(Cat);
		return this.modelMapper.map(addedCat, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
	    Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Categoty", "Categoty Id",categoryId));
	    
	    category.setCategoryTitle(categoryDto.getCategoryTitle());
	    category.setCategoryDescription(categoryDto.getCategoryDescription());
	    
	    Category updatedCategory=this.categoryRepo.save(category);
	    
		return this.modelMapper.map(updatedCategory, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
	  Category category =this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", categoryId));
	  this.categoryRepo.delete(category);
		
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		Category category=this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Category id", categoryId));

		return this.modelMapper.map(category, CategoryDto.class);
		
	}

	@Override
	public List<CategoryDto> getCategories() {
		List<Category> categories = this.categoryRepo.findAll();
		List<CategoryDto> catDtos = categories.stream().map((cat)-> this.modelMapper.map(cat, CategoryDto.class))
				.collect(Collectors.toList());
		
		
		return catDtos;
	}
	

}
