package com.shop.music.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.shop.music.model.Country;
import com.shop.music.repository.ICountryReporitory;

@Service
public class CountryService implements ICountryService {
	private ICountryReporitory countryRepository;
	@Override
	public Optional<Country> findCountryById(Long id) {
		// TODO Auto-generated method stub
		return countryRepository.findById(id);
	}

}
