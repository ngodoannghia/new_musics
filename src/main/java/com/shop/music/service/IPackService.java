package com.shop.music.service;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.shop.music.model.EPack;
import com.shop.music.model.Pack;

@Component
public interface IPackService {
	Optional<Pack> findPackById(Long id);
	Optional<Pack> findPackByName(EPack name);
}
