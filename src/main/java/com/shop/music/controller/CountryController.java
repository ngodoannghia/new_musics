package com.shop.music.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shop.music.common.ApiResponse;
import com.shop.music.common.MessageResponse;
import com.shop.music.config.AppConstant;
import com.shop.music.model.Country;
import com.shop.music.service.ICountryService;

@RestController
@RequestMapping("/api/country/")
public class CountryController {
	
	@Autowired
	private ICountryService countryService;
	
	@GetMapping("/get/all")
	public ApiResponse<?> getAllCountry(){
		try {
			List<Country> countries = countryService.getAllCountry();
			
			return new ApiResponse<>(200, AppConstant.SUCCESS_MESSAGE, countries);
		}
		catch (org.hibernate.exception.ConstraintViolationException e) {
			return new ApiResponse<>(400, e.toString(), null);
		}
	}
	@GetMapping("/get/bypage/{page}")
	public ApiResponse<?> getPageAlbumAll(@PathVariable int page,
			@RequestParam(value = "limit", required = false) Optional<Integer> limit,
			@RequestParam(value = "sortType", required = false) Optional<String> sortType) {
		try {
			int pageLimit = 20;
			boolean pageSortType = false;

			if (limit.isPresent()) {
				pageLimit = limit.get();
			}
			if (sortType.isPresent() && sortType.get() == "desc") {
				pageSortType = true;
			}
			return new ApiResponse<>(200, AppConstant.SUCCESS_MESSAGE,
					countryService.pageFindAllCountry(page, pageLimit, pageSortType));
		} catch (org.hibernate.exception.ConstraintViolationException e) {
			return new ApiResponse<>(400, e.toString(), null);
		}
	}
	@PostMapping("/add")
	public ResponseEntity<ApiResponse<?>> addCountry( 
			@RequestParam("name") String name,
			@RequestParam("description") String description){
		try {
			Country country = new Country();	
			country.setName(name);
			country.setDescription(description);
			country.setCreate_at(LocalDateTime.now());
			countryService.saveCountry(country);
			
			return ResponseEntity.ok().body(new ApiResponse<Country>(200, AppConstant.SUCCESS_MESSAGE, country));
		}
		catch (org.hibernate.exception.ConstraintViolationException e) {
			return ResponseEntity.badRequest().body(new ApiResponse<>(500, AppConstant.BAD_REQUEST_MESSAGE, null));
		}
		
	}
	
	@DeleteMapping("/delete/{country_id}")
	public ResponseEntity<MessageResponse> deleteSong(@PathVariable Long country_id){
		Optional<Country> country = countryService.findCountryById(country_id);
		if (country.isPresent()) {
			countryService.deleteById(country_id);			
			return ResponseEntity.ok().body(new MessageResponse(AppConstant.SUCCESS_MESSAGE, (long) 200));
		}
		else {
			return ResponseEntity.badRequest().body(new MessageResponse(AppConstant.BAD_REQUEST_MESSAGE, (long) 400));
		}
	}
}
