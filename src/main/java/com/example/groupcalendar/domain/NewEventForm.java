package com.example.groupcalendar.domain;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


//FORM FOR VALIDATING CREATION OF A NEW EVENT
public class NewEventForm {

	@NotEmpty
    @Size(min=1, max=30)
	private String title;
	
	@NotEmpty
    @Size(min=1, max=30)
	private String location;
	
	@NotEmpty
    @Size(min=1, max=30)
	private String date;
	
	private String organizerName;
	
	private Group group;

	public NewEventForm() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NewEventForm(@NotEmpty @Size(min = 1, max = 30) String title,
			@NotEmpty @Size(min = 1, max = 30) String location, @NotEmpty @Size(min = 1, max = 30) String date,
			String organizerName, Group group) {
		super();
		this.title = title;
		this.location = location;
		this.date = date;
		this.organizerName = organizerName;
		this.group = group;
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

	public void setOrganizerName(String organizerName) {
		this.organizerName = organizerName;
	}

	public void setGroup(Group group) {
		this.group = group;
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

	public String getOrganizerName() {
		return organizerName;
	}

	public Group getGroup() {
		return group;
	}

	@Override
	public String toString() {
		return "NewEventForm [title=" + title + ", location=" + location + ", date=" + date + ", organizerName="
				+ organizerName + ", group=" + group + "]";
	}

	
	
	
	
}
