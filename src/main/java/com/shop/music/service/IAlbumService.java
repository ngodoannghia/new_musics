package com.shop.music.service;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.shop.music.model.Album;

@Component
public interface IAlbumService {
	Optional<Album> findAlbumById(Long id);
}
