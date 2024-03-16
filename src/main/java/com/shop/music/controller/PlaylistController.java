package com.shop.music.controller;

import java.io.IOException;
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
import com.shop.music.model.Category;
import com.shop.music.model.Country;
import com.shop.music.model.Playlist;
import com.shop.music.service.ICategoryService;
import com.shop.music.service.ICountryService;
import com.shop.music.service.IPlaylistService;

@RestController
@RequestMapping("/api/playlist/")
public class PlaylistController {
	
	@Autowired
	private IPlaylistService playlistService;
	
	@Autowired
	private ICategoryService categoryService;
	
	@Autowired
	private ICountryService countryService;
	
	@GetMapping("/get/all")
	public ApiResponse<?> getAllPlaylist(){
		try {
			List<Playlist> playlist = playlistService.getAllPlaylist();
			
			return new ApiResponse<>(200, AppConstant.SUCCESS_MESSAGE, playlist);
		}
		catch (org.hibernate.exception.ConstraintViolationException e) {
			return new ApiResponse<>(400, e.toString(), null);
		}
	}
	
	@PostMapping("/add")
	public ResponseEntity<ApiResponse<?>> addPlaylist(
			 @RequestParam("name") String name,
			 @RequestParam("country_id") Long country_id,
			 @RequestParam("category_id") Long category_id
			 ) throws IOException{
		
		try {
			// Define
			Country country = null;
			Category category = null;
			
			// Process country
			try {
				country = countryService.findCountryById(country_id).get();
			} catch (Exception e) {
				System.out.println(e.toString());
				country = null;
			}
			
			// Process category
			try {
				category = categoryService.findCategoryById(category_id).get();
			} catch(Exception e) {
				System.out.println(e.toString());
				category = null;
			}
			
			
			Playlist playlist = new Playlist();
			playlist.setName(name);
			playlist.setCategory(category);
			playlist.setCountry(country);
			playlist.setCreate_at(LocalDateTime.now());
			
			playlistService.savePlaylist(playlist);
			
			return ResponseEntity.ok().body(new ApiResponse<Playlist>(200, AppConstant.SUCCESS_MESSAGE, playlist));
		}
		
		catch (org.hibernate.exception.ConstraintViolationException e){
			return ResponseEntity.badRequest().body(new ApiResponse<>(500, AppConstant.BAD_REQUEST_MESSAGE, null));
		}

	}
	@DeleteMapping("/delete/{playlist_id}")
	public ResponseEntity<MessageResponse> deleteSong(@PathVariable Long playlist_id){
		Optional<Playlist> playlist = playlistService.findPlaylistById(playlist_id);
		if (playlist.isPresent()) {
			playlistService.deleteById(playlist_id);			
			return ResponseEntity.ok().body(new MessageResponse(AppConstant.SUCCESS_MESSAGE, (long) 200));
		}
		else {
			return ResponseEntity.badRequest().body(new MessageResponse(AppConstant.BAD_REQUEST_MESSAGE, (long) 400));
		}
	}
}
