package com.shop.music.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shop.music.common.ApiResponse;
import com.shop.music.common.MessageResponse;
import com.shop.music.config.AppConstant;
import com.shop.music.model.Category;
import com.shop.music.service.ICategoryService;

@RestController
@RequestMapping("/api/category/")
public class CategoryController {
	
	@Autowired
	private ICategoryService categoryService;
	
	@GetMapping("/get/all")
	public ApiResponse<?> getAllCategory(){
		try {
			List<Category> categories = categoryService.getAllCategory();
			
			return new ApiResponse<>(200, AppConstant.SUCCESS_MESSAGE, categories);
		}
		catch (org.hibernate.exception.ConstraintViolationException e) {
			return new ApiResponse<>(400, e.toString(), null);
		}
	}
	@PostMapping("/add")
	public ResponseEntity<ApiResponse<?>> addCategory( @RequestParam("name") String name){
		try {
			Category category = new Category();	
			category.setName(name);
			category.setCreate_at(LocalDateTime.now());
			categoryService.saveCategory(category);
			
			return ResponseEntity.ok().body(new ApiResponse<Category>(200, AppConstant.SUCCESS_MESSAGE, category));
		}
		catch (org.hibernate.exception.ConstraintViolationException e) {
			return ResponseEntity.badRequest().body(new ApiResponse<>(500, AppConstant.BAD_REQUEST_MESSAGE, null));
		}
		
	}
	@DeleteMapping("/delete/{category_id}")
	public ResponseEntity<MessageResponse> deleteSong(@PathVariable Long category_id){
		Optional<Category> category = categoryService.findCategoryById(category_id);
		if (category.isPresent()) {
			categoryService.deleteById(category_id);			
			return ResponseEntity.ok().body(new MessageResponse(AppConstant.SUCCESS_MESSAGE, (long) 200));
		}
		else {
			return ResponseEntity.badRequest().body(new MessageResponse(AppConstant.BAD_REQUEST_MESSAGE, (long) 400));
		}
	}
}
