package com.example.groupcalendar.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.groupcalendar.domain.EventRepository;
import com.example.groupcalendar.domain.GroupRepository;
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
	//TODO REMOVE UPON LAUNCH
	@GetMapping("/list")
	public String listUsersAndGroups(Model model) {
		model.addAttribute("users",userRepo.findAll());
		model.addAttribute("groups",groupRepo.findAll());
		return "listUsersAndGroups";
	}
	
	//HOMEPAGE FOR LOGGED IN USER
	@PreAuthorize("hasAuthority('USER')")
	@GetMapping("/home")
	public String getDefaultHomepage() {
		return "homepage";
	}
}
