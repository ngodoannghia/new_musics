package com.shop.music.common;

public class MessageResponse {
	private String message;
	private Long error_code;

	public MessageResponse(String message, Long error_code) {
	    this.message = message;
	    this.setError_code(error_code);
	  }

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getError_code() {
		return error_code;
	}

	public void setError_code(Long error_code) {
		this.error_code = error_code;
	}
}
