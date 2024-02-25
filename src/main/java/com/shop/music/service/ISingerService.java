package com.shop.music.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.shop.music.model.Singer;

@Component
public interface ISingerService {
	Optional<Singer> findSingerById(Long id);
	List<Singer> findSingerByIds(List<Long> ids);
}
