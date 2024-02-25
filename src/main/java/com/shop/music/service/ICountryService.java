package com.shop.music.service;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.shop.music.model.Country;

@Component
public interface ICountryService {
	Optional<Country> findCountryById(Long id);
}
