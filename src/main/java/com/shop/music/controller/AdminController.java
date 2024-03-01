package com.shop.music.controller;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.music.common.ApiResponse;
import com.shop.music.common.AuthenResponse;
import com.shop.music.common.JwtUtils;
import com.shop.music.config.AppConstant;
import com.shop.music.dto.LoginDTO;
import com.shop.music.dto.SignupDTO;
import com.shop.music.dto.UserInforDTO;
import com.shop.music.model.EPack;
import com.shop.music.model.ERole;
import com.shop.music.model.Pack;
import com.shop.music.model.User;
import com.shop.music.service.IPackService;
import com.shop.music.service.IUserService;
import com.shop.music.service.UserDetailsImpl;

@RestController
@RequestMapping("/api/admin/")
public class AdminController {
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
    private IUserService userService;
	
	@Autowired
	private IPackService packService;
	
	@Autowired
    private JwtUtils jwtUtils;
	
	@Autowired
	PasswordEncoder encoder;
	
	@PostMapping("/signup")
	public ResponseEntity<ApiResponse<?>> signup(@RequestBody SignupDTO signupDTO) {
		if (userService.existsByUsername(signupDTO.getUsername())) {
			return ResponseEntity.badRequest().body(new ApiResponse<SignupDTO>(400, "Error: Username is already taken!", signupDTO));
		}
		
	    User user = new User(signupDTO.getUsername(), encoder.encode(signupDTO.getPassword()));
	    user.setUser_id(UUID.randomUUID().toString());
	    user.setCreate_at(LocalDateTime.now());
	    user.setEmail(signupDTO.getEmail());
	    user.setPhone(signupDTO.getPhone());
	    user.setAddress(signupDTO.getAddress());
	    user.setDateofbirth(signupDTO.getDateofbirth());
	    user.setRole(ERole.ROLE_ADMIN);
	    userService.saveUser(user);
	    
	    Pack pack = packService.findPackByName(EPack.Normal).get();
	    user.setPack(pack);
	    userService.saveUser(user);
	    
	    return ResponseEntity.ok().body(new ApiResponse<User>(200, AppConstant.SUCCESS_MESSAGE,user));
	}
	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginDTO loginDTO){
		
		Authentication authentication = authenticationManager
			        .authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		
		if (userDetails.getRole() != ERole.ROLE_ADMIN.toString()) {
			return ResponseEntity.badRequest().body(new ApiResponse<>(403, "Bạn chưa có quyền admin",null));
		}
		
		ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);
		
		UserInforDTO userInfor = new UserInforDTO(userDetails.getId(),
						                userDetails.getUsername(),
						                userDetails.getEmail(),
						                userDetails.getRole());
      
		AuthenResponse<UserInforDTO> authenResponse = new AuthenResponse<UserInforDTO>();
		authenResponse.setCode((long)200);
		authenResponse.setToken(jwtCookie.getValue());
		authenResponse.setName(jwtCookie.getName());
		authenResponse.setPath(jwtCookie.getPath());
		authenResponse.setUser(userInfor);
		
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(new ApiResponse<AuthenResponse<UserInforDTO>>(200, AppConstant.SUCCESS_MESSAGE,authenResponse));
	}
	
	@PostMapping("/signout")
	public ResponseEntity<?> logoutUser() {
		ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
				.body(new ApiResponse<>(200, "You've been signed out!", null));
	}
}
