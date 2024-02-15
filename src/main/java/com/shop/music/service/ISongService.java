package com.shop.music.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.shop.music.model.Song;

@Component
public interface ISongService {
	List<Song> getAllSong();
	Song saveSong(Song song);
}
