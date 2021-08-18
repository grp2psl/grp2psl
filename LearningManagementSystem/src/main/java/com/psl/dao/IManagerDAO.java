package com.psl.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.psl.entities.Manager;

public interface IManagerDAO extends CrudRepository<Manager, Integer>{
	/*
	 * AUTO-INCREMENT ID
	 */
	@Query(value="select max(managerid) + 1 from manager", nativeQuery=true)
	Integer getNextId();
	Manager findByEmailAndPassword(String email, String password);
	Manager findByEmail(String email);
}
