package com.shop.music.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.music.model.Pack;
import com.shop.music.repository.IPackRepository;

@Service
public class PackService implements IPackService {
	@Autowired
	private IPackRepository packRepository; 
	
	@Override
	public Optional<Pack> findPackById(Long id) {
		// TODO Auto-generated method stub
		return packRepository.findById(id);
	}

}
