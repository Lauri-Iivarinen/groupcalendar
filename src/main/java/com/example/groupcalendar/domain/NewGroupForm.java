package com.example.groupcalendar.domain;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

//FORM FOR CREATING GROUPS
public class NewGroupForm {

	@NotEmpty
    @Size(min=4, max=30)
	private String groupName;
	
	@NotEmpty
    @Size(min=1, max=30)
	private String description;

	private Long owner;
	
	


	public NewGroupForm(@NotEmpty @Size(min = 4, max = 30) String groupName,
			@NotEmpty @Size(min = 1, max = 30) String description, Long owner) {
		super();
		this.groupName = groupName;
		this.description = description;
		this.owner = owner;
	}

	public NewGroupForm() {
		super();
		// TODO Auto-generated constructor stub
	}

	//SETTERS
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}


	public void setDescription(String description) {
		this.description = description;
	}

	public void setOwner(Long owner) {
		this.owner = owner;
	}


	//GETTERS
	public String getGroupName() {
		return groupName;
	}


	public String getDescription() {
		return description;
	}

	public Long getOwner() {
		return owner;
	}

	//TOSTRING
	@Override
	public String toString() {
		return "NewGroupForm [groupName=" + groupName + ", owner=" + owner + "]";
	}
	
	
	
	
}
