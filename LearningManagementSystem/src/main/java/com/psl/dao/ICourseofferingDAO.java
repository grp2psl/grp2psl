package com.psl.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.psl.entities.Courseoffering;

public interface ICourseofferingDAO extends CrudRepository<Courseoffering, Integer> {

	@Query(value="select max(courseofferingid) from courseoffering", nativeQuery=true)
	public Integer getMaxId();
	
}
