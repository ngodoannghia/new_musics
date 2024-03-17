package com.shop.music.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class AlbumSingerKey implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="album_id")
	private Long album_id;
	
	@Column(name="song_id", length=100)
	private String song_id;
}
