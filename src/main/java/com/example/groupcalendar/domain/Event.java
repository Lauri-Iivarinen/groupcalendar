package com.example.groupcalendar.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


//EVENT IS A THING THAT HAPPENS FOR A GROUP
//FOR EXAMPLE BIRTHDAY OR A PARTY
@Entity(name="events")
public class Event {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long eventId;
	
	@Column(nullable=true)
	private String title;
	
	@Column(nullable=true)
	private String location;
	
	@Column(name="eventDate")
	private String date;
	
	@Column(nullable=true)
	private String organizerName;
	
	@ManyToMany
	@Column(nullable=true)
	private List<User> participants;
	
	@ManyToOne
	@JsonIgnoreProperties() //group luokan List<Event> events
	@JoinColumn(name="groupId")
	private Group group;


	
	//----------------------------
	
	public Event() {
		this.participants = new ArrayList<>();

	}



	public Event(String title, String location, String date, List<User> participants, Group group) {
		super();
		this.title = title;
		this.location = location;
		this.date = date;
		this.participants = participants;
		this.group = group;
	}



	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public void setLocation(String location) {
		this.location = location;
	}



	public void setDate(String date) {
		this.date = date;
	}



	public void setParticipants(List<User> participants) {
		this.participants = participants;
	}



	public void setGroup(Group group) {
		this.group = group;
	}



	public Long getEventId() {
		return eventId;
	}



	public String getTitle() {
		return title;
	}



	public String getLocation() {
		return location;
	}



	public String getDate() {
		return date;
	}



	public List<User> getParticipants() {
		return participants;
	}



	public Group getGroup() {
		return group;
	}



	@Override
	public String toString() {
		return "Event [eventId=" + eventId + ", title=" + title + ", location=" + location + ", date=" + date
				+ ", participants=" + participants + ", group=" + group + "]";
	}


	//ORGANIZER GETTER + SETTER
	public String getOrganizerName() {
		return organizerName;
	}
	public void setOrganizerName(String organizerName) {
		this.organizerName = organizerName;
	}



	//ADD A NEW PARTICIPANT
	public void addParticipant(User user) {
		this.participants.add(user);
	}
	
	public void removeParticipant(User user) {
		this.participants.remove(user);
	}

	
	

}
