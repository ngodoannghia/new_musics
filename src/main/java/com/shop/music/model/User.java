package com.shop.music.model;

import java.util.Date;
import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name = "id_user", length=100)
	private long id;
	
	@Column(name="name", length=255)
	private String name;
	
	@Column(name="dateofbirth", columnDefinition="DATETIME")
	private Date dateofbirth;
	
	@Column(name="name", length=255)
	private String username;
	
	@Column(name="name", length=255)
	private String password;
	
	@Enumerated(EnumType.STRING)
	@Column(name="role", length=100)
	private ERole role;
	
	@Column(name="create_at")
    @Temporal(value = TemporalType.TIMESTAMP)
	private Date create_at;
	
	@Column(name="create_at")
    @Temporal(value = TemporalType.TIMESTAMP)
	private Date update_at;
	
	@Column(name="name", length=255)
	private String address;
	
	@Column(name="name", length=20)
	private String phone;
	
	@Column(name="name", length=255)
	private String avatar;
	
	@Column(name="name", length=255)
	private String email;
	
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

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Date getDate() {
		return dateofbirth;
	}
	public void setDate(Date dateofbirth) {
		this.dateofbirth = dateofbirth;
	}
	
	@Column(name="username", length=255, nullable=false, unique = true)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Column(name="password", length=255, nullable=false)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name="role", length=100)
	public ERole getRoles() {
	    return role;
	}
	public void setRoles(ERole roles) {
	    this.role = roles;
	}
	
    @Column(name="create_at")
    @Temporal(value = TemporalType.TIMESTAMP)
    public Date getCreate_at() {
    	return create_at;
    }
    public void setCreate_at(Date create_at) {
    	this.create_at = create_at;
    }
    
    @Column(name="update_at")
    @Temporal(value = TemporalType.TIMESTAMP)
    public Date getUpdate_at() {
    	return update_at;
    }
    public void setUpdate_at(Date update_at) {
    	this.update_at = update_at;
    }
	
	@Column(name="address", length=255)
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Column(name="phone", length=100, unique=true)
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Column(name="avatar", columnDefinition="TEXT")
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	@Column(name="email", length=255, unique=true)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
