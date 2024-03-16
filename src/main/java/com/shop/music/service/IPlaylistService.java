package com.shop.music.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.shop.music.model.Playlist;

@Component
public interface IPlaylistService {
	Optional<Playlist> findPlaylistById(Long id);
	Page<Playlist> pageFindAllPlaylist(int page, int limit, boolean sort);
	Playlist savePlaylist(Playlist playlist);
	void deleteById(Long id);
	List<Playlist> getAllPlaylist();
}
