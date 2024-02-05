package com.shop.music.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shop.music.model.Playlist;

@Repository
public interface IPlaylistRepository extends JpaRepository<Playlist, Long> {

}
