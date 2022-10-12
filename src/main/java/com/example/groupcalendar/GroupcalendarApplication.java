package com.example.groupcalendar;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.groupcalendar.domain.Event;
import com.example.groupcalendar.domain.EventRepository;
import com.example.groupcalendar.domain.Group;
import com.example.groupcalendar.domain.GroupRepository;
import com.example.groupcalendar.domain.User;
import com.example.groupcalendar.domain.UserRepository;





@SpringBootApplication
public class GroupcalendarApplication {
	
	private static final Logger log = LoggerFactory.getLogger(GroupcalendarApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(GroupcalendarApplication.class, args);
	}

	
	@Bean
	public CommandLineRunner launchTestBook (UserRepository userRepo, GroupRepository groupRepo, EventRepository eventRepo) {
		return (args) ->{
			log.info("testi");
			
			
			//--------------KÄYTTÄJÄT------------
			//pw: kayttaja
			User user = new User("user", "$2a$10$qZ8pfMseEhulhwJgIz88LO0lV5YYZe2zalTeTprISc6Gv4ZIAQ0ei", "Lauri", "Lapanen", "USER");
			userRepo.save(user);
			
			//pw: valvoja
			User admin = new User("admin", "$2a$10$KIsRwUl8JmJyj9iY8az5MOBEsO1cSZ.FuRaF1FHH6lVMN0Lh/ldzu", "Matti", "Marsu", "ADMIN");
			userRepo.save(admin);
			
			//pw: kissa
			User kissa = new User("cat","$2a$10$SgS1pO0KbVjPrYO5RLrNJuPx1.4yzzKnMTr3JZM42Zdn8JY0fRQEW","cat","katten","USER");
			userRepo.save(kissa);
			
			//--------------RYHMÄT------------
			List<User> family = new ArrayList<User>();
			family.add(user);
			
			List<User> friends = new ArrayList<User>();
			friends.add(user);
			friends.add(admin);
			friends.add(kissa);
			
			
			Group group1 = new Group("family",user.getId(),family);
			Group group2 = new Group("friends",user.getId(),friends);
			
			groupRepo.save(group1);
			groupRepo.save(group2);
			
			
			//--------------TAPAHTUMAT------------
			List<User> attendants = new ArrayList<User>();
			
			eventRepo.save(new Event("bday","mcdonalds","2.2.2022",attendants,group1));
			
			//--------------RYHMÄÄN HAKIJAT------------
			List<User> applicants = new ArrayList<User>();
			applicants.add(kissa);
			group1.setApplicants(applicants);
			groupRepo.save(group1);
			
		};
	}
}
