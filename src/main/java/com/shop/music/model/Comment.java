package com.shop.music.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "comment")
public class Comment {
	@Id
	@Column(name="comment_id", length=100)
	private String comment_id;
	
	@Column(name="message", length=255)
	private String message;
	
	@Column(name="create_at")
    @Temporal(value = TemporalType.TIMESTAMP)
	private Date create_at;
	
	@Column(name="update_at")
    @Temporal(value = TemporalType.TIMESTAMP)
	private Date update_at;
	
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

	public Date getCreate_at() {
		return create_at;
	}

	public void setCreate_at(Date create_at) {
		this.create_at = create_at;
	}

	public Date getUpdate_at() {
		return update_at;
	}

	public void setUpdate_at(Date update_at) {
		this.update_at = update_at;
	}

	public boolean isIs_delete() {
		return is_delete;
	}

	public void setIs_delete(boolean is_delete) {
		this.is_delete = is_delete;
	}
	
}
