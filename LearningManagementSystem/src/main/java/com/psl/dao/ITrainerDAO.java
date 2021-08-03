package com.psl.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.psl.entities.Trainer;

public interface ITrainerDAO extends CrudRepository<Trainer, Integer>{
	@Query(value = "select trainerid, name, department, email, phonenumber, password=null as password from trainer", nativeQuery = true)
	public List<Trainer> findAll();

}
