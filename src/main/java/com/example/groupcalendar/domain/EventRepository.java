package com.example.groupcalendar.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<Event, Long>{
	List<Event> findByTitle(String title);
}
