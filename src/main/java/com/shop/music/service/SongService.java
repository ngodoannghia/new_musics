package com.shop.music.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.music.model.Song;
import com.shop.music.repository.ISongRepository;

@Service
public class SongService implements ISongService {
	@Autowired
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

	@Override
	public List<Song> findByTitle(String title) {
		// TODO Auto-generated method stub
		return songRepository.findByTitle(title);
	}

	@Override
	public List<Song> findByCategory(Long category_id) {
		// TODO Auto-generated method stub
		return songRepository.findByCategory(category_id);
	}

	@Override
	public List<Song> findByCountry(Long country_id) {
		// TODO Auto-generated method stub
		return songRepository.findByCountry(country_id);
	}

	@Override
	public Optional<Song> findById(String song_id) {
		// TODO Auto-generated method stub
		return songRepository.findById(song_id);
	}

	@Override
	public void deleteById(String song_id) {
		// TODO Auto-generated method stub
		songRepository.deleteById(song_id);
	}

	@Override
	public List<Song> findByAlbum(Long album_id) {
		// TODO Auto-generated method stub
		return songRepository.findByAlbum(album_id);
	}

	@Override
	public List<Song> findByPlaylist(Long playlist_id) {
		// TODO Auto-generated method stub
		return songRepository.findByPlaylist(playlist_id);
	}

}
