package com.shop.music.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.data.domain.Page;

import com.shop.music.model.Song;

@Component
public interface ISongService {
	Optional<Song> findById(String song_id);
	List<Song> getAllSong();
	Song saveSong(Song song);
	List<Song> findByTitle(String title);
	List<Song> findByCategory(Long category_id);
	List<Song> findByCountry(Long country_id);
	List<Song> findByAlbum(Long album_id);
	List<Song> findByPlaylist(Long playlist_id);
	void deleteById(String song_id);	
	Page<Song> pageFindAllSong(int page, int limit, boolean sort);
}
