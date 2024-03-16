package com.shop.music.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.music.model.Country;
import com.shop.music.repository.ICountryReporitory;

@Service
public class CountryService implements ICountryService {
	@Autowired
	private ICountryReporitory countryRepository;
	@Override
	public Optional<Country> findCountryById(Long id) {
		// TODO Auto-generated method stub
		return countryRepository.findById(id);
	}
	@Override
	public List<Country> getAllCountry() {
		// TODO Auto-generated method stub
		return countryRepository.findAll();
	}
	@Override
	public Country saveCountry(Country country) {
		// TODO Auto-generated method stub
		return countryRepository.save(country);
	}
	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		countryRepository.deleteById(id);
	}

}
