package com.shop.music.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shop.music.model.User;

@Repository
public interface IUserRepository extends CrudRepository<User, String> {
	Optional<User> findByUsername(String username);
	boolean existsByUsername(String username);
	boolean existsByEmail(String email);
	boolean existsByPhone(String phone);
}
