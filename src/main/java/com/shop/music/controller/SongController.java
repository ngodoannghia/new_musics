package com.shop.music.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
	
	
}
