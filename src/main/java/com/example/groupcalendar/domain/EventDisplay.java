package com.example.groupcalendar.domain;

public class EventDisplay {
	private Event event;
	private int participating;
	private boolean userParticipating;
	
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

	//TOSTRING
	@Override
	public String toString() {
		return "EventDisplay [event=" + event + ", participating=" + participating + ", userParticipating="
				+ userParticipating + "]";
	}
	
	
	
	

}
