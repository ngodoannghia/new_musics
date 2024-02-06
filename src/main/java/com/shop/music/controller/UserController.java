package com.shop.music.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import at.favre.lib.crypto.bcrypt.BCrypt;

import com.shop.music.common.ApiResponse;
import com.shop.music.common.AuthenResponse;
import com.shop.music.common.Authentication;
import com.shop.music.common.JwtTokenUtil;
import com.shop.music.config.AppConstant;
import com.shop.music.dto.LoginDTO;
import com.shop.music.dto.SignupDTO;
import com.shop.music.model.User;
import com.shop.music.service.IUserService;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/api/admin")
public class UserController {
	@Autowired
    private IUserService userService;
	
	@Autowired
	private Authentication authentication;
	
	@Autowired
    private JwtTokenUtil jwtTokenUtil;
	
    @Autowired
    BCrypt.Hasher bHasher;
	
	@PostMapping("/signup")
	public ResponseEntity<ApiResponse<?>> signup(@RequestBody SignupDTO signupDTO) {
		// Create new user's account
		String hashString =  bHasher.hashToString(12,signupDTO.getPassword().toCharArray());
		User user = new User(signupDTO.getUsername(),
							signupDTO.getEmail(),
							signupDTO.getPassword());
		user.setUser_id(UUID.randomUUID().toString());
		user.setPassword(hashString);
		user.setCreate_at(LocalDateTime.now());
		user = userService.saveUser(user);

	    return ResponseEntity.ok(new ApiResponse<>(0, AppConstant.SUCCESS_MESSAGE,user));
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) throws Exception{
		User user = userService.findByUsername(loginDTO.getUsername());
		user = authentication.authenticate(loginDTO, user);
        if (user == null){
            return ResponseEntity.ok(new ApiResponse<AuthenResponse<User>>(1, AppConstant.ERROR_MESSAGE,null));
        }
        final String token = jwtTokenUtil.generateToken(user);
        AuthenResponse<User> authenResponse = new AuthenResponse<User>(token);
        user.setPassword("");
        authenResponse.setUser(user);
        return ResponseEntity.ok(new ApiResponse<AuthenResponse<User>>(0, AppConstant.ERROR_MESSAGE,authenResponse));
	}
}
