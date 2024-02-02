package com.shop.music.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pack")
public class Pack {
	@Id
	@Column(name="id_pack")
	private int id;
	
	@Enumerated(EnumType.STRING)
	@Column(name="name", length=100)
	private EPack name;
	
	@Column(name="price")
	private float price;
	
	@Column(name="activate")
	private boolean activate;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
}
