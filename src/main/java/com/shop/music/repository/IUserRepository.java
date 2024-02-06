package com.shop.music.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shop.music.model.User;

@Repository
public interface IUserRepository extends CrudRepository<User, String> {
	User findByUsername(String username);
}
