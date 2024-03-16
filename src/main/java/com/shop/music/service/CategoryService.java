package com.shop.music.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.shop.music.model.Category;
import com.shop.music.repository.ICategoryRepository;

@Service
public class CategoryService implements ICategoryService {
	@Autowired
	private ICategoryRepository categoryRepository;
	
	@Override
	public Optional<Category> findCategoryById(Long id) {
		// TODO Auto-generated method stub
		return categoryRepository.findById(id);
	}

	@Override
	public Page<Category> pageFindAllCategory(int page, int limit, boolean sort) {
		// TODO Auto-generated method stub
		Page<Category> pageCategory = null;
        Sort typeSort = null;
        if (sort){
            typeSort = Sort.by("create_at").descending();
        } else {
            typeSort = Sort.by("create_at").ascending();
        }
        Pageable pageable =  PageRequest.of(page, limit, typeSort );
        
        pageCategory = categoryRepository.pageFindAll(pageable);
        
        return pageCategory;
	}

	@Override
	public Category saveCategory(Category category) {
		// TODO Auto-generated method stub
		return categoryRepository.save(category);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		categoryRepository.deleteById(id);
		
	}

	@Override
	public List<Category> getAllCategory() {
		// TODO Auto-generated method stub
		return categoryRepository.findAll();
	}

}
