package com.example.groupcalendar.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity(name="users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable=false , updatable=false)
	private Long id;
	@Column(nullable=false)
	private String username;
	@Column(name="password", nullable=false)
	private String pwHash;
	@Column(nullable=false)
	private String role;
	
	public User(String username, String pwHash, String role) {
		super();
		this.username = username;
		this.pwHash = pwHash;
		this.role = role;
	}

	public User() {

	}

	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPwHash() {
		return pwHash;
	}

	public String getRole() {
		return role;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPwHash(String pwHash) {
		this.pwHash = pwHash;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", pwHash=" + pwHash + ", role=" + role + "]";
	}
}
