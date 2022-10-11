package com.example.groupcalendar.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity(name="groups")
public class Group {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable=false)
	private Long groupId;
	
	private Long owner;
	
	private String groupName;
	
	@ManyToMany
	private List<User> members;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "group")
	private List<Event> events;
	
	
	
	//---------------------------------------------
	
	public Group() {

	}

	public Group(String groupName, Long owner, List<User> members) {
		super();
		this.groupName = groupName;
		this.owner = owner;
		this.members = members;
	}
	
	public Group(String groupName, Long owner) {
		super();
		this.groupName = groupName;

	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}



	public void setMembers(List<User> members) {
		this.members = members;
	}

	public Long getGroupId() {
		return groupId;
	}

	public String getGroupName() {
		return groupName;
	}



	public List<User> getMembers() {
		return members;
	}

	@Override
	public String toString() {
		return "Group [groupId=" + groupId + ", groupName=" + groupName + ", members=" + members
				+ "]";
	}

	public Long getOwner() {
		return owner;
	}

	public void setOwner(Long owner) {
		this.owner = owner;
	}
	
	

}
