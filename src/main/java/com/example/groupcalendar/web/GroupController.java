package com.example.groupcalendar.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.groupcalendar.domain.EventRepository;
import com.example.groupcalendar.domain.Group;
import com.example.groupcalendar.domain.GroupRepository;
import com.example.groupcalendar.domain.User;
import com.example.groupcalendar.domain.UserRepository;

//CONTROLLER FOR HANDLING EVERYTHING RELATED TO A GROUP
@Controller
public class GroupController {

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
	
	//ENDPOINT FOR USERS THAT POKE THEIR NOSE IN WRONG BUSINESS
	@GetMapping("/notingroup")
	public String notInGroup(Model model) {
		model.addAttribute("message","NOT PART OF THE GROUP");
		return "messagetemplate";
	}
	
	//Lists all groups that are aivailable
	@PreAuthorize("hasAuthority('USER')")
	@GetMapping("/grouplist")
	public String groupList(Model model) {
		model.addAttribute("groups",groupRepo.findAll());
		return "grouplist";
	}
	
	
	
	//----
	//----
	
	//HANDLING CERTAIN GROUP
	
	//----
	//----
	//User has attempted to apply for a group
	@PreAuthorize("hasAuthority('USER')")
	@GetMapping("/apply/{id}")
	public String applyToGroup(@PathVariable("id") Long id, Authentication auth) {
		User user = userRepo.findByUsername(auth.getName());
		Group group = groupRepo.findById(id).get();
		
		//user is already member
		if (group.getMembers().contains(user)) return "redirect:/grouphome/" + group.getGroupId();
		
		//user is already applicant
		if (group.getApplicants().contains(user)) return "redirect:/grouplist";
		
		
		
		//user is set to be applicant
		List<User> applicants = group.getApplicants();
		applicants.add(user);
		group.setApplicants(applicants);
		groupRepo.save(group);
		return "redirect:/grouplist";
	}
	
	//Homepage for a group
	//CHECK VALID USER
	@GetMapping("/grouphome/{id}")
	public String groupHomePage(@PathVariable("id") Long id,Model model,Authentication auth) {
		
		Group group = groupRepo.findById(id).get();
		User user = userRepo.findByUsername(auth.getName());
		
		//CHECKS FOR VALID GROUP MEMBER
		if(validMember(user,group)) {
			model.addAttribute("group",group);
			return "groupHomePage";
		}
		
		return "redirect:/notingroup/";
		
		
	}
	
	//CHECK VALID USER
	@GetMapping("/{id}/applicants")
	public String getApplicants(@PathVariable("id") Long id,Model model,Authentication auth) {
		Group group =  groupRepo.findById(id).get();
		
		//CHECKS FOR VALID GROUP MEMBER
		if(validMember(userRepo.findByUsername(auth.getName()),group)) {
			List<User> applicants = group.getApplicants();
			model.addAttribute("applicants",applicants);
			return "applicantList";
		}
		
		return "redirect:/notingroup/";
	}
	
	//INVITES APPLICANT TO GROUP

	@GetMapping("{groupId}/invite/{applicantId}")
	public String inviteApplicant(@PathVariable("groupId") Long groupId,@PathVariable("applicantId") Long applicantId,Model model,Authentication auth) {
		Group group = groupRepo.findById(groupId).get();
		//CHECKS FOR VALID GROUP MEMBER
		if(validMember(userRepo.findByUsername(auth.getName()),group)) {
			User applicant = userRepo.findById(applicantId).get();
			
			List<User> applicants = group.getApplicants();
			applicants.remove(applicant);
			group.setApplicants(applicants);
			
			List<User> members = group.getMembers();
			members.add(applicant);
			
			groupRepo.save(group);
			return "redirect:/grouphome/" + groupId;
		}
		
		return "redirect:/notingroup/";
	}
	
}
