package com.shop.music.dto;

import java.time.LocalDateTime;

public class SongDTO {
	private String title;
	
	private String lyris;
	
	private String description;
	
	private LocalDateTime create_at;
	
	private LocalDateTime update_at;
	
	private LocalDateTime publish_at;
	
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

	public String getLink_demo() {
		return link_demo;
	}

	public void setLink_demo(String link_demo) {
		this.link_demo = link_demo;
	}

	public float getDuration() {
		return duration;
	}

	public void setDuration(float duration) {
		this.duration = duration;
	}

	public Long getPack() {
		return pack;
	}

	public void setPack(Long pack) {
		this.pack = pack;
	}

	public Long getCategory() {
		return category;
	}

	public void setCategory(Long category) {
		this.category = category;
	}

	public Long getCountry() {
		return country;
	}

	public void setCountry(Long country) {
		this.country = country;
	}

	private String link_mp3;
	
	private String link_demo;
	
	private float duration;
	
	private Long pack;
	
	private Long category;
	
	private Long country;
}
