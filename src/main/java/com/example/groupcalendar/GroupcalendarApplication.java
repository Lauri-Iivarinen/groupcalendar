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

	
	//kommentoitu poist kun postgre käytössä
	
	
	
}
