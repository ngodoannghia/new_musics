package com.shop.music.controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.shop.music.common.ApiResponse;
import com.shop.music.common.FileProcess;
import com.shop.music.common.MessageResponse;
import com.shop.music.config.AppConstant;
import com.shop.music.model.Album;
import com.shop.music.model.Country;
import com.shop.music.model.Pack;
import com.shop.music.model.Playlist;
import com.shop.music.model.Singer;
import com.shop.music.model.Song;
import com.shop.music.model.Category;
import com.shop.music.service.IAlbumService;
import com.shop.music.service.ICategoryService;
import com.shop.music.service.ICountryService;
import com.shop.music.service.IPackService;
import com.shop.music.service.IPlaylistService;
import com.shop.music.service.ISingerService;
import com.shop.music.service.ISongService;

@RestController
@RequestMapping("/api/song/")
public class SongController {
	private String save_data = "src/main/resources/static/";
	
	@Autowired
	private ICountryService countryService;
	
	@Autowired
	private IAlbumService albumService;
	
	@Autowired
	private ISingerService singerService;
	
	@Autowired
	private IPlaylistService playlistService;
	
	@Autowired
	private ISongService songService;
	
	@Autowired
	private IPackService packService;
	
	@Autowired
	private ICategoryService categoryService;
	
	@PostMapping("/upload")
	public ResponseEntity<ApiResponse<?>> uploadSong(@RequestParam("file_offical") MultipartFile file_offical,
													 @RequestParam("file_demo") MultipartFile file_demo,
													 @RequestParam("file_lyric") MultipartFile file_lyric,
													 @RequestParam("title") String title,
													 @RequestParam("description") String description,
													 @RequestParam("pack_id") Long pack_id,
													 @RequestParam("country_id") Long country_id,
													 @RequestParam("album_id") Long album_id,
													 @RequestParam("singer_id") List<Long> singers_id,
													 @RequestParam("playlist_id") Long playlist_id,
													 @RequestParam("category_id") Long category_id
													 ) throws IOException{
		
		String name_offical = StringUtils.cleanPath(file_offical.getOriginalFilename());
		String name_demo = StringUtils.cleanPath(file_demo.getOriginalFilename());
		String name_lyric = StringUtils.cleanPath(file_lyric.getOriginalFilename());
		
		String path_offical = "audio/offical/" + UUID.randomUUID().toString() + "_" + name_offical;
		String path_demo = "audio/demo/" + UUID.randomUUID().toString() + "_" + name_demo;
		String path_lyric = "lyrics/" + UUID.randomUUID().toString() + "_" + name_lyric;
		
		String text_response = "";
		
		FileProcess audio_offical = new FileProcess(save_data + path_offical, file_offical);
		FileProcess audio_demo = new FileProcess(save_data + path_demo, file_offical);
		FileProcess lyric_offical = new FileProcess(save_data + path_lyric, file_lyric);
		
		boolean status_offical = audio_offical.saveFile();
		boolean status_demo = audio_demo.saveFile();
		boolean status_lyric = lyric_offical.saveFile();
		
		// Define
		Pack pack = null;
		Album album = null;
		List<Singer> singers = null;
		Country country = null;
		Playlist playlist = null;
		Category category = null;
		
		
		// Process pack
		try {
			pack = packService.findPackById(pack_id).get();
		} catch (Exception e){
			pack = null;
		}
		
		// Process country
		try {
			country = countryService.findCountryById(country_id).get();
		} catch (Exception e) {
			country = null;
		}
		
		
		// Process album
		try {
			album = albumService.findAlbumById(album_id).get();
		} catch (Exception e) {
			album = null;
		}
		
		
		// Process singer
		try {
			singers = singerService.findSingerByIds(singers_id);
		} catch (Exception e) {
			singers = null;
		}
		
		
		// Process playlist
		try {
			playlist = playlistService.findPlaylistById(playlist_id).get();
		} catch(Exception e) {
			playlist = null;
		}
		
		
		// Process category
		try {
			category = categoryService.findCategoryById(category_id).get();
		} catch(Exception e) {
			category = null;
		}
		
		
        Song song = new Song();
        song.setSong_id(UUID.randomUUID().toString());
        song.setTitle(title);
        song.setDescription(description);
        if (status_offical) {
        	song.setLink_mp3(path_offical);
        }
        if (status_demo) {
        	song.setLink_demo(path_demo);
        }
        if (status_lyric) {
        	song.setLyris(path_lyric);
        }
        song.setAlbum(album);
        song.setCategory(category);
        song.setCountry(country);
        song.setSingers(new HashSet<>(singers));
        song.setPlaylist(playlist);
        song.setPack(pack);
        
        songService.saveSong(song);
		
        if (status_offical == false | status_demo == false) {
        	return ResponseEntity.badRequest().body(new ApiResponse<Song>(204, text_response ,song));
        }
        else {
        	return ResponseEntity.ok().body(new ApiResponse<Song>(200, AppConstant.SUCCESS_MESSAGE,song));
        }
	}
	
	@GetMapping("/get/byid/{song_id}")
	public ApiResponse<?> getSongById(@PathVariable String song_id) {
		try {
			Song song = songService.findById(song_id).get();
			if (song != null) {
				return new ApiResponse<>(200, AppConstant.SUCCESS_MESSAGE, song);
			}
			else {
				return new ApiResponse<>(400, "Data empty", song);
			}
		} catch(org.hibernate.exception.ConstraintViolationException e) {
			return new ApiResponse<>(400, e.toString(), null);
		}

	}
	
	@GetMapping("/get/bytitle/{title}")
    public ApiResponse<?> getSongByTitle(@PathVariable String title) {
		try {
			List<Song> songs = songService.findByTitle(title);
			if (songs != null) {
				return new ApiResponse<>(200, AppConstant.SUCCESS_MESSAGE, songs); 
			}
			else {
				return new ApiResponse<>(400, "Data empty", songs); 
			}
		} catch(org.hibernate.exception.ConstraintViolationException e) {
			return new ApiResponse<>(400, e.toString(), null);
		}
    }
	
	@GetMapping("/get/bycategory/{category_id}")
	public ApiResponse<?> getSongByCategory(@PathVariable Long category_id){
		try {
			List<Song> songs = songService.findByCategory(category_id);
			if (songs != null) {
				return new ApiResponse<>(200, AppConstant.SUCCESS_MESSAGE, songs); 
			}
			else {
				return new ApiResponse<>(400, "Data empty", songs); 
			}
		} catch(org.hibernate.exception.ConstraintViolationException e) {
			return new ApiResponse<>(400, e.toString(), null);
		}

	}
	
	@GetMapping("/get/bycountry/{country_id}")
	public ApiResponse<?> getSongByCountry(@PathVariable Long country_id){
		try {
			List<Song> songs = songService.findByCountry(country_id);
			
			if (songs != null) {
				return new ApiResponse<>(200, AppConstant.SUCCESS_MESSAGE, songs); 
			}
			else {
				return new ApiResponse<>(400, "Data empty", songs); 
			}
		} catch(org.hibernate.exception.ConstraintViolationException e) {
			return new ApiResponse<>(400, e.toString(), null);
		}
	}
	
	@GetMapping("/get/byalbum/{album_id}")
	public ApiResponse<?> getSongByAlbum(@PathVariable Long album_id){
		try {
			List<Song> songs = songService.findByAlbum(album_id);
			if (songs != null) {
				return new ApiResponse<>(200, AppConstant.SUCCESS_MESSAGE, songs); 
			}
			else {
				return new ApiResponse<>(400, "Data empty", songs); 
			}
		} catch(org.hibernate.exception.ConstraintViolationException e) {
			return new ApiResponse<>(400, e.toString(), null);
		}
	}
	
	@GetMapping("/get/byplaylist/{playlist_id}")
	public ApiResponse<?> getSongByPlaylist(@PathVariable Long playlist_id){
		try {
			List<Song> songs = songService.findByPlaylist(playlist_id);
			if (songs != null) {
				return new ApiResponse<>(200, AppConstant.SUCCESS_MESSAGE, songs); 
			}
			else {
				return new ApiResponse<>(400, "Data empty", songs); 
			}
		} catch(org.hibernate.exception.ConstraintViolationException e) {
			return new ApiResponse<>(400, e.toString(), null);
		}
	
	}
	@GetMapping("/get/bypage/{page}")
    public ApiResponse<?> getPageSongAll(@PathVariable  int page,
                                      @RequestParam(value = "limit",required = false) Optional<Integer> limit,
                                      @RequestParam(value = "sortType",required = false) Optional<String> sortType){
		try {
			int pageLimit = 20;
	        boolean pageSortType = false;

	        if (limit.isPresent()){
	            pageLimit = limit.get();
	        }
	        if (sortType.isPresent() && sortType.get() == "desc"){
	            pageSortType = true;
	        }
	        return  new ApiResponse<>(200,AppConstant.SUCCESS_MESSAGE,songService.pageFindAllSong(page,pageLimit,pageSortType));
		}
		catch(org.hibernate.exception.ConstraintViolationException e) {
			return new ApiResponse<>(400, e.toString(), null);
		}
	}
                                      
	@DeleteMapping("/delete/{song_id}")
	public ResponseEntity<MessageResponse> deleteSong(@PathVariable String song_id){
		Optional<Song> song = songService.findById(song_id);
		if (song.isPresent()) {
			songService.deleteById(song_id);			
			return ResponseEntity.ok().body(new MessageResponse(AppConstant.SUCCESS_MESSAGE, (long) 200));
		}
		else {
			return ResponseEntity.badRequest().body(new MessageResponse(AppConstant.BAD_REQUEST_MESSAGE, (long) 400));
		}
	}
	
	
	
}
