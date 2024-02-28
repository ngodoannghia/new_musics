package com.shop.music.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shop.music.model.Song;

@Repository
public interface ISongRepository extends JpaRepository<Song, String> {
	List<Song> findByTitle(String title);
	
	@Query( nativeQuery = true,
			value = "SELECT song.* FROM category " + 
					"inner join song on category.category_id = song.category_id " + 
					"WHERE category.category_id = :category_id")
	List<Song> findByCategory(Long category_id);
	
	@Query( nativeQuery = true,
			value = "SELECT song.* FROM country " + 
					"inner join song on country.country_id = song.country_id " + 
					"WHERE country.country_id = :country_id")
	List<Song> findByCountry(Long country_id);
	
	@Query( nativeQuery = true,
			value = "SELECT song.* FROM album " + 
					"inner join song on album.album_id = song.album_id " + 
					"WHERE album.album_id = :album_id")
	List<Song> findByAlbum(Long album_id);
	
	@Query( nativeQuery = true,
			value = "SELECT song.* FROM playlist " + 
					"inner join song on playlist.playlist_id = song.playlist_id " + 
					"WHERE playlist.playlist_id = :playlist_id")
	List<Song> findByPlaylist(Long playlist_id);

}
