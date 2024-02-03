package com.shop.music.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "singer")
public class Singer {
	@Id
	@Column(name="singer_id")
	private int id;
	
	@Column(name="name", length=100)
	private String name;
	
	@Column(name="dateofbirth", columnDefinition="DATETIME")
	private Date dateofbirth;
	
	@Column(name="avatar", length=255)
	private String avatar;
}
