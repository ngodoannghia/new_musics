package com.shop.music.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.shop.music.model.Category;
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

	@Override
	public Page<Playlist> pageFindAllPlaylist(int page, int limit, boolean sort) {
		// TODO Auto-generated method stub
		Page<Playlist> pagePlaylist = null;
        Sort typeSort = null;
        if (sort){
            typeSort = Sort.by("create_at").descending();
        } else {
            typeSort = Sort.by("create_at").ascending();
        }
        Pageable pageable =  PageRequest.of(page, limit, typeSort );
        
        pagePlaylist = playlistRepository.pageFindAll(pageable);
        
        return pagePlaylist;
	}

	@Override
	public Playlist savePlaylist(Playlist playlist) {
		// TODO Auto-generated method stub
		return playlistRepository.save(playlist);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		playlistRepository.deleteById(id);
	}

}
