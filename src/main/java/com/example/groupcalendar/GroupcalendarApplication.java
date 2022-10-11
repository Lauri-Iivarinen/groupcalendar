package com.example.groupcalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.groupcalendar.domain.User;
import com.example.groupcalendar.domain.UserRepository;





@SpringBootApplication
public class GroupcalendarApplication {
	
	private static final Logger log = LoggerFactory.getLogger(GroupcalendarApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(GroupcalendarApplication.class, args);
	}

	
	@Bean
	public CommandLineRunner launchTestBook (UserRepository userRepo) {
		return (args) ->{
			log.info("testi");
			
			//pw: kayttaja
			userRepo.save(new User("user", "$2a$10$qZ8pfMseEhulhwJgIz88LO0lV5YYZe2zalTeTprISc6Gv4ZIAQ0ei", "USER"));
			
			//pw: valvoja
			userRepo.save(new User("admin", "$2a$10$KIsRwUl8JmJyj9iY8az5MOBEsO1cSZ.FuRaF1FHH6lVMN0Lh/ldzu", "ADMIN"));
			
		};
	}
}
