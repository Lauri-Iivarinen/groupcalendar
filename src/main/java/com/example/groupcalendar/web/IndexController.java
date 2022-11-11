package com.example.groupcalendar.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.groupcalendar.domain.Event;
import com.example.groupcalendar.domain.EventDisplay;
import com.example.groupcalendar.domain.EventRepository;
import com.example.groupcalendar.domain.Group;
import com.example.groupcalendar.domain.GroupRepository;
import com.example.groupcalendar.domain.User;
import com.example.groupcalendar.domain.UserRepository;

//CONTROLLER HANDLING BASIC REQUEST THAT ARE NOT REALTED TO GROUPS OR EVENTS
@Controller
public class IndexController {

	@Autowired
	private UserRepository userRepo;
	@Autowired 
	private GroupRepository groupRepo;
	@Autowired 
	private EventRepository eventRepo;

	//INDEX PAGE
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	//LISTS GROUPS AND USERS 
	//TODO REMOVE UPON LAUNCH or restrict to admin
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/list")
	public String listUsersAndGroups(Model model) {
		model.addAttribute("users",userRepo.findAll());
		model.addAttribute("groups",groupRepo.findAll());
		return "listUsersAndGroups";
	}
	
	//HOMEPAGE FOR LOGGED IN USER
	@PreAuthorize("hasAuthority('USER')")
	@GetMapping("/home")
	public String getDefaultHomepage(Model model,Authentication auth) {
		User user = userRepo.findByUsername(auth.getName());
		
		List<Group> groups = (List<Group>) groupRepo.findAll();
		List<Group> usersGroups = new ArrayList<>();
		List<Event> events = new ArrayList<>();
		List <EventDisplay> display = new ArrayList<>();
		
		for (Group group:groups) {
			if (group.getMembers().contains(user)) usersGroups.add(group);
			events.addAll(group.getEvents());
		}
		//IF USER IS PARTICIPATING IN AN EVENT IT IS ADDED FOR DISPLAY
		for (Event event: events) {
			if (event.getParticipants().contains(user))  display.add(new EventDisplay(event));
		}

		model.addAttribute("events", display);
		model.addAttribute("groups",usersGroups);
		model.addAttribute("user",user);
		return "homepage";
	}
	
	//SAME AS GET
	@PostMapping("/home")
	public String postDefaultHomePage() {
		return "redirect:/home";
	}
}
