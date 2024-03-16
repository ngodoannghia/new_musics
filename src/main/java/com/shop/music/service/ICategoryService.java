package com.shop.music.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.shop.music.model.Category;

@Component
public interface ICategoryService {
	Optional<Category> findCategoryById(Long id);
	Page<Category> pageFindAllCategory(int page, int limit, boolean sort);
	Category saveCategory(Category category);
	void deleteById(Long id);
	List<Category> getAllCategory();
}
