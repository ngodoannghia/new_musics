package com.shop.music.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.music.model.Album;
import com.shop.music.repository.IAlbumRepository;

@Service
public class AlbumService implements IAlbumService {
	@Autowired
	private IAlbumRepository albumRepository;
	
	@Override
	public Optional<Album> findAlbumById(Long id) {
		// TODO Auto-generated method stub
		return albumRepository.findById(id);
	}

}
