package com.shop.music.model;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "country")
public class Country {
	@Id
	@Column(name="country_id")
	private Long country_id;
	
	@Column(name="name", length=100)
	private String name;
	
	@OneToMany(mappedBy="country")
	private Set<Playlist> playlist;
	
	@OneToMany(mappedBy="country")
	private Set<Album> album;
	
	@OneToMany(mappedBy="country")
	private Set<Song> song;
	
	@OneToMany(mappedBy="country")
	private Set<Singer> singer;
	
	public Long getCountry_id() {
		return country_id;
	}
	public void setCountry_id(Long country_id) {
		this.country_id = country_id;
	}
	public Set<Playlist> getPlaylist() {
		return playlist;
	}
	public void setPlaylist(Set<Playlist> playlist) {
		this.playlist = playlist;
	}
	public Set<Album> getAlbum() {
		return album;
	}
	public void setAlbum(Set<Album> album) {
		this.album = album;
	}
	public Set<Song> getSong() {
		return song;
	}
	public void setSong(Set<Song> song) {
		this.song = song;
	}
	public Set<Singer> getSinger() {
		return singer;
	}
	public void setSinger(Set<Singer> singer) {
		this.singer = singer;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
