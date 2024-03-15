package com.shop.music.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shop.music.model.Category;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Long> {
	@Query ( nativeQuery = true,
			 value = "SELECT * FROM category")
	Page<Category> pageFindAll(Pageable pageable);
}
