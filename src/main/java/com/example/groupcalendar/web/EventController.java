package com.example.groupcalendar.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.groupcalendar.domain.Event;
import com.example.groupcalendar.domain.EventRepository;
import com.example.groupcalendar.domain.Group;
import com.example.groupcalendar.domain.GroupRepository;
import com.example.groupcalendar.domain.NewEventForm;
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
		
		//Checks for valid groupmember
		if(!validMember(userRepo.findByUsername(auth.getName()), groupRepo.findById(id).get())) return "redirect:/notingroup";
		
		NewEventForm event = new NewEventForm();
		event.setGroup(groupRepo.findById(id).get());
		event.setOrganizerName(auth.getName());
		
		model.addAttribute("event", event);
		return "createEvent";
	}
	
	//SAVES THE EVENT TO DB
	@PostMapping("/newevent")
	public String postNewEvent(@Valid @ModelAttribute("event") NewEventForm newEvent,BindingResult br, Authentication auth) {
		
		if (br.hasErrors()) return "createEvent";
		
		Event event = new Event();
		event.setTitle(newEvent.getTitle());
		event.setDate(newEvent.getDate());
		event.setLocation(newEvent.getLocation());
		event.setGroup(newEvent.getGroup());
		event.setOrganizerName(newEvent.getOrganizerName());
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
	
	//DELETING EVENTS
	@GetMapping("/delete-event/{id}")
	public String removeEvent(@PathVariable("id") Long id,Authentication auth) {
		Event event = eventRepo.findById(id).get();
		User user = userRepo.findByUsername(auth.getName());
		
		//check for valid groupmember
		if(!validMember(user,event.getGroup())) return "redirect:/notingroup";
		if(event.getOrganizerName().equals(user.getUsername())) eventRepo.delete(event);
		return "redirect:/grouphome/" + event.getGroup().getGroupId();
	}
	
}
