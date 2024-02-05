package com.shop.music.model;

import java.util.Date;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name = "user_id", columnDefinition="varchar(100)", length=100)
	private String user_id;
	
	@Column(name="dateofbirth", columnDefinition="DATETIME")
	private Date dateofbirth;
	
	@Column(name="username", length=255, nullable=false, unique = true)
	private String username;
	
	@Column(name="password", length=255, nullable=false)
	private String password;
	
	@Enumerated(EnumType.STRING)
	@Column(name="role", length=100)
	private ERole role;
	
	@Column(name="create_at")
    @Temporal(value = TemporalType.TIMESTAMP)
	private Date create_at;
	
	@Column(name="update_at")
    @Temporal(value = TemporalType.TIMESTAMP)
	private Date update_at;
	
	@Column(name="address", length=255)
	private String address;
	
	@Column(name="phone", length=20, unique=true)
	private String phone;
	
	@Column(name="avatar", length=255)
	private String avatar;
	
	@Column(name="email", length=100)
	private String email;
	
	@ManyToOne
	@JoinColumn(name="pack_id")
	private Pack pack;
	
	@ManyToMany(mappedBy="users")
	private Set<Song> songs;
	
	public User() {
		
	}
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	public User(String username, String email, String password) {
		this.username = username;
		this.password = password;
		this.email = email;
	}
	public User(String username, String email, String password, String phone) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.phone = phone;
	}
	
	public Date getDate() {
		return dateofbirth;
	}
	public void setDate(Date dateofbirth) {
		this.dateofbirth = dateofbirth;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public ERole getRoles() {
	    return role;
	}
	public void setRoles(ERole roles) {
	    this.role = roles;
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
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Pack getPack() {
		return pack;
	}
	public void setpack(Pack pack) {
		this.pack = pack;
	}
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	public Date getDateofbirth() {
		return dateofbirth;
	}
	public void setDateofbirth(Date dateofbirth) {
		this.dateofbirth = dateofbirth;
	}
	
	public ERole getRole() {
		return role;
	}
	public void setRole(ERole role) {
		this.role = role;
	}
	
	public void setPack(Pack pack) {
		this.pack = pack;
	}
}
