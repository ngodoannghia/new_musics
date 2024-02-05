package com.shop.music.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class SongUserKey implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="song_id", length=100)
	private String song_id;
	
	@Column(name="user_id", length=100)
	private String user_id;

}
