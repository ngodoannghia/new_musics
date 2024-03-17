package com.shop.music.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

public class AlbumSinger {
	@EmbeddedId
	private AlbumSingerKey id;
	
    @ManyToOne
    @MapsId("album_id")
    @JoinColumn(name = "album_id")
    private Album album;
    
    @ManyToOne
    @MapsId("singer_id")
    @JoinColumn(name = "singer_id")
    private Singer singer;
}
