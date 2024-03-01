package com.shop.music.common;

public class AuthenResponse<T> {
	private String token;
	private String path;
	private String name;
    public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	private T user;
    private Long code;

    public AuthenResponse() {
    	
    }
    
    public AuthenResponse(Long code, T user) {
        this.code = code;
        this.user = user;
    }

    public Long getCode() {
		return code;
	}
	public void setCode(Long code) {
		this.code = code;
	}

    public T getUser() {
        return user;
    }

    public void setUser(T user) {
        this.user = user;
    }
}
