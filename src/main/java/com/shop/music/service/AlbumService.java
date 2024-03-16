package com.shop.music.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

	@Override
	public Page<Album> pageFindAllAlbum(int page, int limit, boolean sort) {
		// TODO Auto-generated method stub
		Page<Album> pageAlbum = null;
        Sort typeSort = null;
        if (sort){
            typeSort = Sort.by("create_at").descending();
        } else {
            typeSort = Sort.by("create_at").ascending();
        }
        Pageable pageable =  PageRequest.of(page, limit, typeSort );
        
        pageAlbum = albumRepository.pageFindAll(pageable);
        
        return pageAlbum;
	}

	@Override
	public Album saveAlbum(Album album) {
		// TODO Auto-generated method stub
		return albumRepository.save(album);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		albumRepository.deleteById(id);
	}

	@Override
	public List<Album> getAllAlbum() {
		// TODO Auto-generated method stub
		return albumRepository.findAll();
	}

}
