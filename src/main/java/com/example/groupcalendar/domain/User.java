package com.example.groupcalendar.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;


//ALL USERS DEFAULT ROLE IS "USER" USED FOR VALIDATING ACTIONS INSIDE GROUP OR AUTHENTICATION
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
	private String firstName;
	
	@Column(nullable=false)
	private String lastName;
	

	@Column(nullable=true)
	@ManyToMany
	private List<Group> groups;
	
	@Column(nullable=true)
	@ManyToMany
	private List<Group> applicationList;
	
	
	
	@Column(nullable=false)
	private String role;

	public User(String username, String pwHash, String firstName, String lastName, String role) {
		super();
		this.username = username;
		this.pwHash = pwHash;
		this.firstName = firstName;
		this.lastName = lastName;
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

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
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

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", pwHash=" + pwHash + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", groups=" + groups + ", applicationList=" + applicationList + ", role="
				+ role + "]";
	}

	
	
}
