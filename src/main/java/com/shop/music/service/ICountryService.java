package com.shop.music.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.shop.music.model.Country;

@Component
public interface ICountryService {
	Optional<Country> findCountryById(Long id);
	List<Country> getAllCountry();
	Country saveCountry(Country country);
	void deleteById(Long id);
	Page<Country> pageFindAllCountry(int page, int limit, boolean sort);
}
