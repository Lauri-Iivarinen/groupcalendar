package com.example.groupcalendar.domain;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/*
 * 
 * @NotEmpty
    @Size(min=5, max=30)
 */

public class NewUserForm {

	@NotEmpty
    @Size(min=4, max=30)
	private String username;
	@NotEmpty
    @Size(min=5, max=30)
	private String password;
	@NotEmpty
    @Size(min=5, max=30)
	private String passwordCheck;
	@NotEmpty
    @Size(min=1, max=30)
	private String firstName;
	@NotEmpty
    @Size(min=1, max=30)
	private String lastName;
	
	
	public NewUserForm(String username, String password, String passwordCheck, String firstName, String lastName) {
		super();
		this.username = username;
		this.password = password;
		this.passwordCheck = passwordCheck;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	public NewUserForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setPasswordCheck(String passwordCheck) {
		this.passwordCheck = passwordCheck;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String getPasswordCheck() {
		return passwordCheck;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	@Override
	public String toString() {
		return "NewUserForm [username=" + username + ", password=" + password + ", passwordCheck=" + passwordCheck
				+ ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}
	

}
