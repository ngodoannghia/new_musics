package com.shop.music.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "pack")
public class Pack {
	@Id
	@Column(name="pack_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pack_id;
	
	@Enumerated(EnumType.STRING)
	@Column(name="name", length=100)
	private EPack name;
	
	@Column(name="price")
	private float price;
	
	
	@JsonIgnore
	@OneToMany(mappedBy="pack")
	private Set<User> users;
	
	@JsonIgnore
	@OneToMany(mappedBy="pack")
	private Set<Song> song;
	
	public Long getPack_id() {
		return pack_id;
	}
	public void setPack_id(Long pack_id) {
		this.pack_id = pack_id;
	}
	public EPack getName() {
		return name;
	}
	public void setName(EPack name) {
		this.name = name;
	}
	
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
	public Set<User> getUsers(){
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
}
