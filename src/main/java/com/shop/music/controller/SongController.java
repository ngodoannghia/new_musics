package com.shop.music.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
import com.shop.music.config.AppConstant;
import com.shop.music.dto.SongDTO;
import com.shop.music.model.Song;
import com.shop.music.service.ISongService;
import com.shop.music.service.ISongUserService;
import com.shop.music.service.IUserService;

@RestController
@RequestMapping("/api/song/")
public class SongController {
	@Autowired
	private IUserService userService;
	
	@Autowired
	private ISongService songService;
	
	@Autowired
	private ISongUserService songUserService;
	
	@PostMapping("/upload")
	public ResponseEntity<ApiResponse<?>> uploadSong(@RequestParam("file") MultipartFile file, @RequestParam("title") String title) throws IOException{
		byte[] bytes = file.getBytes();
		String path_save_song = "UploadSong/" + UUID.randomUUID().toString();
		File songFile = new File(path_save_song);
		BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(songFile));
		stream.write(bytes);
        stream.close();
        
        Song song = new Song();
        song.setSong_id(UUID.randomUUID().toString());
        song.setTitle(title);
        song.setLink_mp3(path_save_song);
        
        songService.saveSong(song);
		
		return ResponseEntity.ok().body(new ApiResponse<Song>(200, AppConstant.SUCCESS_MESSAGE,song));
	}

}
