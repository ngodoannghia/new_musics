package com.shop.music.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
	@Override
	public Page<Country> pageFindAllCountry(int page, int limit, boolean sort) {
		// TODO Auto-generated method stub
		Page<Country> pageCountry = null;
        Sort typeSort = null;
        if (sort){
            typeSort = Sort.by("create_at").descending();
        } else {
            typeSort = Sort.by("create_at").ascending();
        }
        Pageable pageable =  PageRequest.of(page, limit, typeSort );
        
        pageCountry = countryRepository.pageFindAll(pageable);
        
        return pageCountry;
	}

}
