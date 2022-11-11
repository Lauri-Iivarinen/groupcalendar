package com.example.groupcalendar.domain;


//Used for easier viewing of EVENT class in thymeleafs
public class EventDisplay {
	private Event event;
	private int participating;
	private boolean userParticipating;
	private boolean isOrganizer;
	
	//SETS EVENT AND CALCULATES PARTICIPANT AMOUNT
	public EventDisplay(Event event) {
		this.event = event;
		this.participating = event.getParticipants().size();
	}

	public EventDisplay() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	//SETTERS
	public void setEvent(Event event) {
		this.event = event;
	}

	public void setParticipating(int participating) {
		this.participating = participating;
	}

	public void setUserParticipating(boolean userParticipating) {
		this.userParticipating = userParticipating;
	}

	
	public void setOrganizer(boolean isOrganizer) {
		this.isOrganizer = isOrganizer;
	}

	//GETTERS
	public Event getEvent() {
		return event;
	}

	public int getParticipating() {
		return participating;
	}

	public boolean isUserParticipating() {
		return userParticipating;
	}

	
	public boolean isOrganizer() {
		return isOrganizer;
	}

	//TOSTRING
	@Override
	public String toString() {
		return "EventDisplay [event=" + event + ", participating=" + participating + ", userParticipating="
				+ userParticipating + "]";
	}
	
	
	
	

}
