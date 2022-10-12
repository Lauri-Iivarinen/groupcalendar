package com.example.groupcalendar.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.groupcalendar.domain.EventRepository;
import com.example.groupcalendar.domain.GroupRepository;
import com.example.groupcalendar.domain.UserRepository;

@Controller
public class CalendarController {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired 
	private GroupRepository groupRepo;
	
	@Autowired 
	private EventRepository eventRepo;

	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/list")
	public String listUsersAndGroups(Model model) {
		model.addAttribute("users",userRepo.findAll());
		model.addAttribute("groups",groupRepo.findAll());
		return "listUsersAndGroups";
	}
}
