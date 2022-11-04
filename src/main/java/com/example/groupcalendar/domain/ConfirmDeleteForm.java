package com.example.groupcalendar.domain;


//FORM FOR CONFIRMING GROUP DELETION
public class ConfirmDeleteForm {

	private String groupName;
	private String confirmGroupName;
	private Long groupId;
	

	public ConfirmDeleteForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ConfirmDeleteForm(String groupName, String confirmGroupName, Long groupId) {
		super();
		this.groupName = groupName;
		this.confirmGroupName = confirmGroupName;
		this.groupId = groupId;
	}
	
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public void setConfirmGroupName(String confirmGroupName) {
		this.confirmGroupName = confirmGroupName;
	}
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
	public String getGroupName() {
		return groupName;
	}
	public String getConfirmGroupName() {
		return confirmGroupName;
	}
	public Long getGroupId() {
		return groupId;
	}
	@Override
	public String toString() {
		return "ConfirmDeleteForm [groupName=" + groupName + ", confirmGroupName=" + confirmGroupName + ", groupId="
				+ groupId + "]";
	}
	
	
	
	
	
}

