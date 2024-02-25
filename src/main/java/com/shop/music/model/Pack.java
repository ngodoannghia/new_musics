package com.shop.music.model;

import java.util.Date;
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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

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
	
	@Column(name="activate")
	private boolean activate;
	
	@Column(name="date_buy")
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date date_buy;
	
	@Column(name="date_expiration")
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date date_expiration;
	
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
	public Date getDate_buy() {
		return date_buy;
	}
	public void setDate_buy(Date date_buy) {
		this.date_buy = date_buy;
	}
	public Date getDate_expiration() {
		return date_expiration;
	}
	public void setDate_expiration(Date date_expiration) {
		this.date_expiration = date_expiration;
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
	
	public boolean getActivate() {
		return activate;
	}
	public void setActivate(boolean activate) {
		this.activate = activate;
	}
	
	public Set<User> getUsers(){
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
}
