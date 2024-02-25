package com.shop.music.service;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.shop.music.model.Playlist;

@Component
public interface IPlaylistService {
	Optional<Playlist> findPlaylistById(Long id);
}
