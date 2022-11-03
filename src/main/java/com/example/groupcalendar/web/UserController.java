package com.example.groupcalendar.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.groupcalendar.domain.NewUserForm;
import com.example.groupcalendar.domain.User;
import com.example.groupcalendar.domain.UserRepository;


//Class for handling user authentication, login/new account etc.
@Controller
public class UserController {
	
	@Autowired
	private UserRepository userRepo;
	
	//LOGIN PAGE
	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}

	//SIGNPU PAGE
	@GetMapping("/signup")
	public String getSignupForm(Model model) {
		model.addAttribute("newUser",new NewUserForm());
		return "signup";
	}
	
	//CHECKS VALID INPUTS AND SAVES NEW USER TO DATABASE
	@PostMapping("/newuser")
	public String postSignupForm(@Valid @ModelAttribute("newUser") NewUserForm newuser, BindingResult br) {
		
		//general errors
		if(br.hasErrors()) return "/signup";
		
		//existing username
		if(userRepo.findByUsername(newuser.getUsername()) != null) {
			br.rejectValue("username", "err.username", "Username exists"); 
			return "signup";
		}
		
		//check for matching password and passwordcheck
		if(newuser.getPassword().equals(newuser.getPasswordCheck())) {
			//TODO SAVE TO USER
			BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
			User addUser = new User();
			addUser.setFirstName(newuser.getFirstName());
			addUser.setLastName(newuser.getLastName());
			addUser.setUsername(newuser.getUsername());
			addUser.setPwHash(bc.encode(newuser.getPassword()));
			addUser.setRole("USER");
			
			userRepo.save(addUser);
		}else {
			br.rejectValue("passwordCheck", "err.passwordCheck", "Passwords do not match");    	
			return "signup";
		}
		
		return "redirect:/login";
	}
}
