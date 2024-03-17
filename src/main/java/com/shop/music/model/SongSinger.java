package com.shop.music.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

public class SongSinger {
	@EmbeddedId
	private SongSingerKey id;
	
	@ManyToOne
    @MapsId("song_id")
    @JoinColumn(name = "song_id")
    private Song song;
    
    @ManyToOne
    @MapsId("singer_id")
    @JoinColumn(name = "singer_id")
    private Singer singer;

	public SongSingerKey getId() {
		return id;
	}

	public void setId(SongSingerKey id) {
		this.id = id;
	}

	public Song getSong() {
		return song;
	}

	public void setSong(Song song) {
		this.song = song;
	}

	public Singer getSinger() {
		return singer;
	}

	public void setSinger(Singer singer) {
		this.singer = singer;
	}
	
}
