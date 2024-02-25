package com.shop.music.model;

import java.time.LocalDateTime;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "singer")
public class Singer {
	@Id
	@Column(name="singer_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long singer_id;
	
	@Column(name="name", length=100)
	private String name;
	
	@Column(name="dateofbirth", columnDefinition="DATETIME")
	private LocalDateTime dateofbirth;
	
	@Column(name="avatar", length=255)
	private String avatar;
	
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;
	
	@ManyToOne
	@JoinColumn(name="country_id")
	private Country country;
	
	@ManyToMany(mappedBy="singers")
	private Set<Album> albums;
	
	@ManyToMany(mappedBy="singers")
	private Set<Song> songs;
	
	public Long getSinger_id() {
		return singer_id;
	}

	public void setSinger_id(Long singer_id) {
		this.singer_id = singer_id;
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

	public Set<Album> getAlbums() {
		return albums;
	}

	public void setAlbums(Set<Album> albums) {
		this.albums = albums;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getDateofbirth() {
		return dateofbirth;
	}

	public void setDateofbirth(LocalDateTime dateofbirth) {
		this.dateofbirth = dateofbirth;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Set<Song> getSongs() {
		return songs;
	}

	public void setSongs(Set<Song> songs) {
		this.songs = songs;
	}
}
