package com.shop.music.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shop.music.common.ApiResponse;
import com.shop.music.config.AppConstant;
import com.shop.music.model.Album;
import com.shop.music.model.Category;
import com.shop.music.model.Country;
import com.shop.music.model.Singer;
import com.shop.music.service.IAlbumService;
import com.shop.music.service.ICategoryService;
import com.shop.music.service.ICountryService;
import com.shop.music.service.ISingerService;

@RestController
@RequestMapping("/api/album/")
public class AlbumController {

	@Autowired
	private IAlbumService albumService;
	
	@Autowired
	private ICountryService countryService;
	
	@Autowired
	private ICategoryService categoryService;
	
	@Autowired
	private ISingerService singerService;

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
					albumService.pageFindAllAlbum(page, pageLimit, pageSortType));
		} catch (org.hibernate.exception.ConstraintViolationException e) {
			return new ApiResponse<>(400, e.toString(), null);
		}
	}
	@PostMapping("/add")
	public ResponseEntity<ApiResponse<?>> addAlbum(
			 @RequestParam("name") String name,
			 @RequestParam("country_id") Long country_id,
			 @RequestParam("singer_id") List<Long> singers_id,
			 @RequestParam("category_id") Long category_id
			 ) throws IOException{
		
		try {
			// Define
			List<Singer> singers = null;
			Country country = null;
			Category category = null;
			
			// Process country
			try {
				country = countryService.findCountryById(country_id).get();
			} catch (Exception e) {
				System.out.println(e.toString());
				country = null;
			}
			
			// Process singer
			try {
				singers = singerService.findSingerByIds(singers_id);
			} catch (Exception e) {
				System.out.println(e.toString());
				singers = null;
			}
			
			// Process category
			try {
				category = categoryService.findCategoryById(category_id).get();
			} catch(Exception e) {
				System.out.println(e.toString());
				category = null;
			}
			
			
			Album album = new Album();
			album.setName(name);
			album.setCategory(category);
			album.setCountry(country);
			album.setSingers(new HashSet<> (singers));
			album.setCreate_at(LocalDateTime.now());
			
			albumService.saveAlbum(album);
			
			return ResponseEntity.ok().body(new ApiResponse<Album>(200, AppConstant.SUCCESS_MESSAGE, album));
		}
		
		catch (org.hibernate.exception.ConstraintViolationException e){
			return ResponseEntity.badRequest().body(new ApiResponse<Album>(500, AppConstant.BAD_REQUEST_MESSAGE, null));
		}

	}
}
