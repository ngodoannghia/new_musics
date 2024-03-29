package com.shop.music.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.shop.music.model.User;

@Component
public interface IUserService {
	List<User> getAllUser();  
	
	User saveUser(User user);  

	void deleteUser(String id);  

	Optional<User> findUserById(String id);  
	
	Optional<User> findByUsername(String username);
	
	boolean existsByUsername(String username);
	
	boolean existsByEmail(String email);
	
	boolean existByPhone(String phone);
	
}
