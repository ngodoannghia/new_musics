package com.shop.music.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shop.music.model.Singer;

@Repository
public interface ISingerRepository extends JpaRepository<Singer, Long> {

}
