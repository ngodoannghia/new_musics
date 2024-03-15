package com.shop.music.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shop.music.model.Album;

@Repository
public interface IAlbumRepository extends JpaRepository<Album, Long> {
	@Query ( nativeQuery = true,
			 value = "SELECT * FROM album")
	Page<Album> pageFindAll(Pageable pageable);
}
