package com.shop.music.model;


import java.time.LocalDateTime;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "song")
public class Song {
	@Id
    @Column(name = "song_id", columnDefinition="varchar(100)", length=100)
	private String song_id;
	
	@Column(name="title", length=255)
	private String title;
	
	@Column(name="lyris", columnDefinition="TEXT")
	private String lyris;
	
	@Column(name="description", length=255)
	private String description;
	
	@Column(name="create_at")
	@Temporal(value = TemporalType.TIMESTAMP)
	private LocalDateTime create_at;
	
	@Column(name="update_at")
	@Temporal(value = TemporalType.TIMESTAMP)
	private LocalDateTime update_at;
	
	@Column(name="publish_at")
	@Temporal(value = TemporalType.TIMESTAMP)
	private LocalDateTime publish_at;
	
	@Column(name="link_mp3", length=255)
	private String link_mp3;
	
	@Column(name="link_demo", length=255)
	private String link_demo;
	
	@Column(name="duration")
	private float duration;
	
	@ManyToOne
	@JoinColumn(name="pack_id")
	private Pack pack;
	
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;
	
	@ManyToOne
	@JoinColumn(name="country_id")
	private Country country;
	
	@ManyToOne
	@JoinColumn(name="album_id")
	private Album album;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "song_singer", 
               joinColumns = @JoinColumn(name = "song_id"),
               inverseJoinColumns = @JoinColumn(name = "singer_id"))
	private Set<Singer> singers;
	
	@ManyToOne
	@JoinColumn(name="playlist_id")
	private Playlist playlist;
	
	@ManyToMany
	@JoinTable(name = "song_user", joinColumns = @JoinColumn(name = "song_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	private Set<User> users;
	
	public String getLink_demo() {
		return link_demo;
	}
	
	public void setLink_demo(String link_demo) {
		this.link_demo = link_demo;
	}

	public String getSong_id() {
		return song_id;
	}

	public void setSong_id(String song_id) {
		this.song_id = song_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLyris() {
		return lyris;
	}

	public void setLyris(String lyris) {
		this.lyris = lyris;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getCreate_at() {
		return create_at;
	}

	public void setCreate_at(LocalDateTime create_at) {
		this.create_at = create_at;
	}

	public LocalDateTime getUpdate_at() {
		return update_at;
	}

	public void setUpdate_at(LocalDateTime update_at) {
		this.update_at = update_at;
	}

	public LocalDateTime getPublish_at() {
		return publish_at;
	}

	public void setPublish_at(LocalDateTime publish_at) {
		this.publish_at = publish_at;
	}

	public String getLink_mp3() {
		return link_mp3;
	}

	public void setLink_mp3(String link_mp3) {
		this.link_mp3 = link_mp3;
	}

	public float getDuration() {
		return duration;
	}

	public void setDuration(float duration) {
		this.duration = duration;
	}

	public Pack getPack() {
		return pack;
	}

	public void setPack(Pack pack) {
		this.pack = pack;
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

	public Set<Singer> getSingers() {
		return singers;
	}

	public void setSingers(Set<Singer> singers) {
		this.singers = singers;
	}

	public Playlist getPlaylist() {
		return playlist;
	}

	public void setPlaylist(Playlist playlist) {
		this.playlist = playlist;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
}
