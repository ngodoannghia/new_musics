package com.shop.music.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.music.model.Playlist;
import com.shop.music.repository.IPlaylistRepository;

@Service
public class PlaylistService implements IPlaylistService {
	@Autowired
	private IPlaylistRepository playlistRepository;
	
	@Override
	public Optional<Playlist> findPlaylistById(Long id) {
		// TODO Auto-generated method stub
		return playlistRepository.findById(id);
	}

}
