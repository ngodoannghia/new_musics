package com.shop.music.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "category")
public class Category {
	@Id
	@Column(name="category_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long category_id;
	
	@Column(name="name", length=100)
	private String name;
	
	@JsonIgnore
	@OneToMany(mappedBy="category")
	private Set<Playlist> playlist;
	
	@JsonIgnore
	@OneToMany(mappedBy="category")
	private Set<Album> album;
	
	@JsonIgnore
	@OneToMany(mappedBy="category")
	private Set<Singer> singer;
	
	@JsonIgnore
	@OneToMany(mappedBy="category")
	private Set<Song> song;
	
	public Long getCategory_id() {
		return category_id;
	}
	public void setCategory_id(Long category_id) {
		this.category_id = category_id;
	}
	public Set<Album> getAlbum() {
		return album;
	}
	public void setAlbum(Set<Album> album) {
		this.album = album;
	}
	public Set<Singer> getSinger() {
		return singer;
	}
	public void setSinger(Set<Singer> singer) {
		this.singer = singer;
	}
	public Set<Song> getSong() {
		return song;
	}
	public void setSong(Set<Song> song) {
		this.song = song;
	}
	
	public Set<Playlist> getPlaylist() {
		return playlist;
	}
	public void setPlaylist(Set<Playlist> playlist) {
		this.playlist = playlist;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
