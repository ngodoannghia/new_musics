package com.shop.music.service;

import java.util.List;

import com.shop.music.model.Song;
import com.shop.music.repository.ISongRepository;

public class SongService implements ISongService {
	private ISongRepository songRepository;
	
	@Override
	public List<Song> getAllSong() {
		// TODO Auto-generated method stub
		return (List<Song>) songRepository.findAll();
	}

	@Override
	public Song saveSong(Song song) {
		// TODO Auto-generated method stub
		return songRepository.save(song);
	}

}
