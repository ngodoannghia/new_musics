package com.shop.music.model;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "playlist")
public class Playlist {
	@Id
	@Column(name="playlist_id")
	private Long playlist_id;
	
	@Column(name="name", length=100)
	private String name;
	
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;
	
	@ManyToOne
	@JoinColumn(name="country_id")
	private Country country;
	
	@OneToMany(mappedBy="playlist")
	private Set<Song> songs;
	
	public Long getPlaylist_id() {
		return playlist_id;
	}
	public void setPlaylist_id(Long playlist_id) {
		this.playlist_id = playlist_id;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	public Set<Song> getSongs() {
		return songs;
	}
	public void setSongs(Set<Song> songs) {
		this.songs = songs;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
