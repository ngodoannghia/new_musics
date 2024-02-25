package com.shop.music.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.music.model.Singer;
import com.shop.music.repository.ISingerRepository;

@Service
public class SingerService implements ISingerService {
	@Autowired
	private ISingerRepository singerRepository;
	
	@Override
	public Optional<Singer> findSingerById(Long id) {
		// TODO Auto-generated method stub
		return singerRepository.findById(id);
	}

	@Override
	public List<Singer> findSingerByIds(List<Long> ids) {
		// TODO Auto-generated method stub
		return singerRepository.findAllById(ids);
	}

}
