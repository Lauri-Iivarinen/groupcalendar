package com.example.groupcalendar.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.groupcalendar.domain.Event;
import com.example.groupcalendar.domain.EventRepository;
import com.example.groupcalendar.domain.Group;
import com.example.groupcalendar.domain.GroupRepository;
import com.example.groupcalendar.domain.User;
import com.example.groupcalendar.domain.UserRepository;


//CONTROLLER FOR HANDLING EVENTS AND CALENDAR
@Controller
public class EventController {
	
	@Autowired
	private UserRepository userRepo;
	@Autowired 
	private GroupRepository groupRepo;
	@Autowired 
	private EventRepository eventRepo;
	
	//CHECKS IF CURRENT USER IS PART OF A GROUP
	public boolean validMember(User user,Group group) {
		boolean outcome = false;
		List<User> members = group.getMembers();
		if (members.contains(user)) outcome = true;
		return outcome;
	}
	
	//OPENS FORM FOR CREATING NEW EVENTS
	@GetMapping("/newevent/{id}")
	public String newEvent(@PathVariable("id") Long id,Model model,Authentication auth) {
		
		User user = userRepo.findByUsername(auth.getName());
		
		//Checks for valid groupmember
		if(!validMember(userRepo.findByUsername(auth.getName()), groupRepo.findById(id).get())) return "redirect:/notingroup";
		
		Event event = new Event();
		event.setGroup(groupRepo.findById(id).get());
		event.setOrganizerName(auth.getName());
		
		model.addAttribute("event", event);
		return "createEvent";
	}
	
	//SAVES THE EVENT TO DB
	@PostMapping("/newevent")
	public String postNewEvent(@ModelAttribute("event") Event event,Authentication auth) {
		
		event.addParticipant(userRepo.findByUsername(auth.getName()));
		eventRepo.save(event);
		return "redirect:/grouphome/" + event.getGroup().getGroupId();
	}
	
	
	//CAHNGES USERS PARTICIPATION STATUS BASED ON PREVIOUS STATUS FOR AN EVENT
	@GetMapping("/participationstatus/{id}/{action}")
	public String changeParticipationStatus(@PathVariable("id") Long id,@PathVariable("action") Long action,Authentication auth) {
		Event event = eventRepo.findById(id).get();
		User user = userRepo.findByUsername(auth.getName());
		
		//check for valid groupmember
		if(!validMember(user,event.getGroup())) return "redirect:/notingroup";
		
		//checks if user is already participating and removes the participation or adds it
		if (event.getParticipants().contains(user)) {
			event.removeParticipant(user);
		}else {
			event.addParticipant(user);
		}
		
		eventRepo.save(event);
		
		//ACTION = 1 ONLY WHEN METHOD IS CALLED FROM /HOME
		if (action==1) return "redirect:/home";
		
		return "redirect:/grouphome/" + event.getGroup().getGroupId();
	}
	
}
