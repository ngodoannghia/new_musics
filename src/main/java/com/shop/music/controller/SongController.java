package com.shop.music.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.shop.music.common.ApiResponse;
import com.shop.music.common.AudioProcess;
import com.shop.music.config.AppConstant;
import com.shop.music.dto.SongDTO;
import com.shop.music.model.Song;
import com.shop.music.service.ISongService;
import com.shop.music.service.ISongUserService;
import com.shop.music.service.IUserService;

@RestController
@RequestMapping("/api/song/")
public class SongController {
	private String save_music = "src/main/resources/static/audio/";
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private ISongService songService;
	
	@Autowired
	private ISongUserService songUserService;
	
	@PostMapping("/upload")
	public ResponseEntity<ApiResponse<?>> uploadSong(@RequestParam("file_offical") MultipartFile file_offical,
													 @RequestParam("file_demo") MultipartFile file_demo,
													 @RequestParam("file_lyris") MultipartFile file_lyris,
													 @RequestParam("title") String title,
													 @RequestParam("description") String description,
													 @RequestParam("pack") Long pack,
													 @RequestParam("country") Long country,
													 @RequestParam("album") Long album,
//													 @RequestParam("singers") List<Long> singers,
													 @RequestParam("playlist") Long playlist
													 ) throws IOException{
		String path_offical = save_music + "offical/" + UUID.randomUUID().toString() + ".mp3";
		String path_demo = save_music + "demo/" + UUID.randomUUID().toString() + ".mp3";
		
		String text_response = "";
		
		AudioProcess audio_offical = new AudioProcess(path_offical, file_offical);
		AudioProcess audio_demo = new AudioProcess(path_demo, file_offical);
		
		boolean status_offical = audio_offical.saveAudio();
		boolean status_demo = audio_demo.saveAudio();
        
        Song song = new Song();
        song.setSong_id(UUID.randomUUID().toString());
        song.setTitle(title);
        if (status_offical) {
        	song.setLink_mp3(path_offical);
        }
        else {
        	text_response = "Upload offical file faile";
        }
        if (status_demo) {
        	song.setLink_demo(path_demo);
        }
        else {
        	text_response += "\n Upload demo file faile";
        }
        
        songService.saveSong(song);
		
        if (status_offical == false | status_demo == false) {
        	return ResponseEntity.ok().body(new ApiResponse<Song>(204, text_response ,song));
        }
        else {
        	return ResponseEntity.ok().body(new ApiResponse<Song>(200, AppConstant.SUCCESS_MESSAGE,song));
        }
		
	}
	
}
