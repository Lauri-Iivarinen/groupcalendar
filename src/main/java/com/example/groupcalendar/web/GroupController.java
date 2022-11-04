package com.example.groupcalendar.web;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.groupcalendar.domain.EventRepository;
import com.example.groupcalendar.domain.Group;
import com.example.groupcalendar.domain.GroupRepository;
import com.example.groupcalendar.domain.NewGroupForm;
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
		
		//CHECKS FOR INVALID GROUP MEMBER
		if(!validMember(user,group)) return "redirect:/notingroup";
		
		model.addAttribute("group",group);
		return "groupHomePage";
		
	}
	
	//CHECK VALID USER
	@GetMapping("/{id}/applicants")
	public String getApplicants(@PathVariable("id") Long id,Model model,Authentication auth) {
		Group group =  groupRepo.findById(id).get();
		
		//CHECKS FOR INVALID GROUP MEMBER
		if(!validMember(userRepo.findByUsername(auth.getName()),group)) return "redirect:/notingroup";		
		
		List<User> applicants = group.getApplicants();
		model.addAttribute("applicants",applicants);
		return "applicantList";
	}
	
	//INVITES APPLICANT TO GROUP

	@GetMapping("{groupId}/invite/{applicantId}")
	public String inviteApplicant(@PathVariable("groupId") Long groupId,@PathVariable("applicantId") Long applicantId,Model model,Authentication auth) {
		Group group = groupRepo.findById(groupId).get();
		
		//CHECKS FOR INVALID GROUP MEMBER
		if(!validMember(userRepo.findByUsername(auth.getName()),group)) return "redirect:/notingroup";
		
		User applicant = userRepo.findById(applicantId).get();
		
		//Remove applicant from applicants and add to members using methods created in Group
		group.clearApplicant(applicant);
		group.addMember(applicant);
		
		groupRepo.save(group);
		
		return "redirect:/grouphome/" + groupId;
	}
	
	//TEMPLATE FOR CREATING A NEW GROUP
	@GetMapping("/creategroup")
	public String getCreateGroup(Model model,Authentication auth) {
		//saves current user as owner of the group
		NewGroupForm group = new NewGroupForm();
		User user = userRepo.findByUsername(auth.getName());
		group.setOwner(user.getId());
		
		model.addAttribute("group", group);
		return "creategroup";
	}
	
	@PostMapping("/creategroup")
	public String postCreateGroup(@Valid @ModelAttribute("group") NewGroupForm group,BindingResult br,Authentication auth) {
		//general errors
		if(br.hasErrors()) return "/newgroup";
				
		//existing groupname
		if(groupRepo.findByGroupName(group.getGroupName()) != null) {
			br.rejectValue("groupName", "err.groupName", "Group already exists");
			return "createGroup";
		}
				
		//create new group and set memberlist
		Group newgroup = new Group();
		newgroup.setGroupName(group.getGroupName());
		newgroup.setOwner(group.getOwner());
		newgroup.addMember(userRepo.findByUsername(auth.getName()));
				
		groupRepo.save(newgroup);
				
		//RETURN TO GROUPS NEW HOMEPAGE
		return "redirect:/grouphome/" + newgroup.getGroupId();

	}
	
}
