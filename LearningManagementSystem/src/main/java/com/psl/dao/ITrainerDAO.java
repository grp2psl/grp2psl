package com.psl.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.psl.entities.Course;
import com.psl.entities.Trainer;

public interface ITrainerDAO extends CrudRepository<Trainer, Integer>{
	
	public Trainer findByTrainerId(int id);
	/*
	 * CUSTOM QUERY TO HIDE PASSWORD
	 */
	@Query(value = "select trainerid, name, department, email, phonenumber, password=null as password from trainer", nativeQuery = true)
	public List<Trainer> findAll();

	/*
	 * AUTO-INCREMENT ID
	 */
	@Query(value="select max(trainerid) + 1 from trainer", nativeQuery=true)
	public Integer getNextId();

	/*
	 * CUSTOM QUERY FOR NEW RECORD INSERTION  
	 */
	@Transactional
	@Modifying
	@Query(value="insert into trainer(trainerid, name, department, phonenumber, email, password) values(?1, ?2, ?3, ?4, ?5, ?6)", nativeQuery=true)
	public void saveNewEntry(@Param("id") int id, @Param("name") String name, @Param("department") String department, 
			@Param("phoneNumber") String phoneNumber, @Param("email") String email, @Param("password") String password);

	/*
	 * CUSTOM QUERY TO UPDATE RECORD  
	 */
	@Transactional
	@Modifying
	@Query(value="update trainer set department = ?1, phonenumber = ?2 where trainerid = ?3", nativeQuery=true)
	public void updateEntry(@Param("department") String department, 
			@Param("phoneNumber") String phoneNumber, @Param("id") int id);
}
