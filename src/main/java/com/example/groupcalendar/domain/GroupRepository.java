package com.example.groupcalendar.domain;

import org.springframework.data.repository.CrudRepository;

public interface GroupRepository extends CrudRepository<Group, Long>{

	Group findByGroupName(String name);
}
