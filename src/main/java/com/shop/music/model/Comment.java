package com.shop.music.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import com.shop.music.common.LocalDateTimeDeserializer;
import com.shop.music.common.LocalDateTimeSerializer;

@Entity
@Table(name = "comment")
public class Comment {
	@Id
	@Column(name="comment_id", length=100)
	private String comment_id;
	
	@Column(name="message", length=255)
	private String message;
	
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@Column(name="create_at")
	private LocalDateTime create_at;
	
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@Column(name="update_at")
	private LocalDateTime update_at;
	
	@Column(name="is_delete")
	private boolean is_delete;
	
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name = "song_id", referencedColumnName="song_id"),
		@JoinColumn(name = "user_id", referencedColumnName="user_id")
	})
	private SongUser songuser;
	
	public String getComment_id() {
		return comment_id;
	}

	public void setComment_id(String comment_id) {
		this.comment_id = comment_id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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

	public boolean isIs_delete() {
		return is_delete;
	}

	public void setIs_delete(boolean is_delete) {
		this.is_delete = is_delete;
	}
	
}
