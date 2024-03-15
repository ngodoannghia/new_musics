package com.shop.music.model;

import java.time.LocalDateTime;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.shop.music.common.LocalDateTimeDeserializer;
import com.shop.music.common.LocalDateTimeSerializer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "album")
public class Album {
	@Id
	@Column(name="album_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long album_id;
	
	@Column(name="name", length=100)
	private String name;
	
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@Column(name="create_at")
	private LocalDateTime create_at;
	
	@ManyToOne
	@JoinColumn(name="country_id")
	private Country country;

	@JsonIgnore
	@OneToMany(mappedBy="album")
	private Set<Song> song;
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "album_singer", 
               joinColumns = @JoinColumn(name = "album_id"),
               inverseJoinColumns = @JoinColumn(name = "singer_id"))
	@Column(name="singer_id", length=100)
	private Set<Singer> singers;
	
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;
	
	public Long getAlbum_id() {
		return album_id;
	}
	public void setAlbum_id(Long album_id) {
		this.album_id = album_id;
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
	public Set<Song> getSong() {
		return song;
	}
	public void setSong(Set<Song> song) {
		this.song = song;
	}
	public Set<Singer> getSingers() {
		return singers;
	}
	public void setSingers(Set<Singer> singers) {
		this.singers = singers;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDateTime getCreate_at() {
		return create_at;
	}
	public void setCreate_at(LocalDateTime create_at) {
		this.create_at = create_at;
	}
}
