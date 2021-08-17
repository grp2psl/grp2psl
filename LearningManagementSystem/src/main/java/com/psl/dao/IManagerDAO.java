package com.psl.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.psl.entities.Manager;

public interface IManagerDAO extends CrudRepository<Manager, Integer>{
	/*
	 * AUTO-INCREMENT ID
	 */
	@Query(value="select max(managerid) + 1 from manager", nativeQuery=true)
	Integer getNextId();
	Manager findByEmailAndPassword(String email, String password);
	Manager findByEmail(String email);
	
	
	//@Query(value="select password from manager where managerid=?1",nativeQuery=true)
	//Manager findById(@Param("id") int id);
}
