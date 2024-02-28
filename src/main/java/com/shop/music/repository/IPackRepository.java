package com.shop.music.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shop.music.model.EPack;
import com.shop.music.model.Pack;

@Repository
public interface IPackRepository extends JpaRepository<Pack, Long> {
	Optional<Pack> findPackByName(EPack name);
}
