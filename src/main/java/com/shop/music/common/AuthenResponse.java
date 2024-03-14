package com.shop.music.common;

import org.springframework.http.ResponseCookie;


public class AuthenResponse<T> {
	private ResponseCookie cookie;

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

	public ResponseCookie getCookie() {
		return cookie;
	}

	public void setCookie(ResponseCookie cookie) {
		this.cookie = cookie;
	}

    
}
