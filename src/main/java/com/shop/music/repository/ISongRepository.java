package com.shop.music.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shop.music.model.Song;

@Repository
public interface ISongRepository extends JpaRepository<Song, String> {

}
