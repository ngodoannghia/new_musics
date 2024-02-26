package com.shop.music.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "song_user")
public class SongUser {
	@EmbeddedId
	private SongUserKey id;

    @ManyToOne
    @MapsId("song_id")
    @JoinColumn(name = "song_id")
    private Song song;
    
    @ManyToOne
    @MapsId("user_id")
    @JoinColumn(name = "user_id")
    private User user;
    
    @Column(name="activate")
    private boolean activate;
    
    @Column(name="react")
    private boolean react;
    
    @JsonIgnore
    @OneToMany(mappedBy="songuser")
    private Set<Comment> comments;

	public SongUserKey getId() {
		return id;
	}

	public void setId(SongUserKey id) {
		this.id = id;
	}

	public boolean getReact() {
		return react;
	}

	public void setReact(boolean react) {
		this.react = react;
	}


	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public Song getSong() {
		return song;
	}
	
	public void setSong(Song song) {
		this.song = song;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean getActivate() {
		return activate;
	}

	public void setActivate(boolean activate) {
		this.activate = activate;
	}
}
