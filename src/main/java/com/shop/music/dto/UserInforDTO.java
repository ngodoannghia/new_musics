package com.shop.music.dto;

public class UserInforDTO {
	private String user_id;
	
	private String username;
	
	private String password;
	
	private String role;
	
	private String token;
	
	private String path;
	
	private String name;
	
	public UserInforDTO(String user_id, String username, String password, String role, String token, String path, String name) {
		this.user_id = user_id;
		this.username = username;
		this.password = password;
		this.role = role;
		this.token = token;
		this.path = path;
		this.name = name;
		
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	
}
