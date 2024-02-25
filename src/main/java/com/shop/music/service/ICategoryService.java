package com.shop.music.service;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.shop.music.model.Category;

@Component
public interface ICategoryService {
	Optional<Category> findCategoryById(Long id);
}
