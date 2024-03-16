package com.shop.music.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.shop.music.model.Album;

@Component
public interface IAlbumService {
	Optional<Album> findAlbumById(Long id);
	Page<Album> pageFindAllAlbum(int page, int limit, boolean sort);
	Album saveAlbum(Album album);
	void deleteById(Long id);
	List<Album> getAllAlbum();
}
