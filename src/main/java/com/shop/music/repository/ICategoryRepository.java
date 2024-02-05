package com.shop.music.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shop.music.model.Category;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Long> {

}
