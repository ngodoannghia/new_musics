package com.shop.music.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shop.music.model.Song;

@Repository
public interface ISongRepository extends JpaRepository<Song, String> {
//	@Query( nativeQuery = true,
//            value = "SELECT category.category_id, song.* FROM  WHERE u.username = :username")
//	List<Song> filterByCategory(Long categroy_id);
}
