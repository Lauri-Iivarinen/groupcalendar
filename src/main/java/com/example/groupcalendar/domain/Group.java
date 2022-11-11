package com.example.groupcalendar.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

//A SIGNLE GROUP THAT CONTAINS MEMBERS WHO CAN INTERACTI WITH EACH OTHER TROUGH EVENT CALENDAR
@Entity(name="groups")
public class Group {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable=false)
	private Long groupId;
	
	private Long owner;
	
	private String groupName;
	
	private String description;
	
	@ManyToMany
	private List<User> members;
	
	@ManyToMany
	private List<User> applicants;
	
	

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "group")
	private List<Event> events;
	
	
	
	//---------------------------------------------
	
	public Group() {
		this.members = new ArrayList<>();
		this.applicants = new ArrayList<>();
		this.events = new ArrayList<>();
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

	//SETTERS
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}


	public void setDescription(String description) {
		this.description = description;
	}

	public void setApplicants(List<User> applicants) {
		this.applicants = applicants;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public void setMembers(List<User> members) {
		this.members = members;
	}

	
	//GETTERS
	public Long getGroupId() {
		return groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public String getDescription() {
		return description;
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
	public List<User> getApplicants() {
		return applicants;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setOwner(Long owner) {
		this.owner = owner;
	}
	
	//adds one new member to group
	public void addMember(User user) {
		this.members.add(user);
	}
	
	//removes member from group
	public void removeMember(User user) {
		this.members.remove(user);
	}
	
	//removes applicant from group
	public void clearApplicant(User user) {
		this.applicants.remove(user);
	}
	
	

}
