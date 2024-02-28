package com.shop.music.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shop.music.model.User;
import com.shop.music.repository.IUserRepository;

@Component
public class UserService implements IUserService {
	
	@Autowired
    private IUserRepository userRepository;

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return (List<User>) userRepository.findAll();
	}

	@Override
	public User saveUser(User user) {
		// TODO Auto-generated method stub
		return userRepository.save(user);
		
	}

	@Override
	public Optional<User> findUserById(String id) {
		// TODO Auto-generated method stub
		return userRepository.findById(id);
	}

	@Override
	public void deleteUser(String id) {
		// TODO Auto-generated method stub
		userRepository.deleteById(id);
		
	}

	@Override
	public Optional<User> findByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepository.findByUsername(username);
	}

	@Override
	public boolean existsByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepository.existsByUsername(username);
	}

	@Override
	public boolean existsByEmail(String email) {
		// TODO Auto-generated method stub
		return userRepository.existsByEmail(email);
	}

	@Override
	public boolean existByPhone(String phone) {
		// TODO Auto-generated method stub
		return userRepository.existsByPhone(phone);
	}


}
