package com.shop.music.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shop.music.model.SongUser;
import com.shop.music.model.SongUserKey;

@Repository
public interface ISongUserRepository extends JpaRepository<SongUser, SongUserKey> {

}
