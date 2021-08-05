package com.psl.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.psl.entities.Learner;

public interface ILearnerDAO extends CrudRepository<Learner,Integer>{
	@Query(value = "select learnerid, name, department, email, phonenumber, password=null as password from learner", nativeQuery = true)
	public List<Learner> findAll();

	@Query(value="select max(learnerid) from learner", nativeQuery=true)
	public Integer getNextId();
	
	
	
	
}
