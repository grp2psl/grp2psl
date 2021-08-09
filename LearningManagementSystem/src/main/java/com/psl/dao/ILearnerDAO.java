
package com.psl.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.psl.entities.Learner;

public interface ILearnerDAO extends CrudRepository<Learner,Integer>{
	/*
	 * CUSTOM QUERY TO HIDE PASSWORD
	 */
	@Query(value = "select learnerid, name, department, email, phonenumber, password=null as password from learner", nativeQuery = true)
	public List<Learner> findAll();

	/*
	 * AUTO-INCREMENT ID
	 */
	@Query(value="select max(learnerid) + 1 from learner", nativeQuery=true)
	public Integer getNextId();

	/*
	 * CUSTOM QUERY FOR NEW RECORD INSERTION  
	 */
	@Modifying
	@Transactional
	@Query(value="insert into learner(learnerid, name, department, phonenumber, email, password) values(?1, ?2, ?3, ?4, ?5, ?6)", nativeQuery=true)
	public void saveNewEntry(@Param("id") int id, @Param("name") String name, @Param("department") String department, 
			@Param("phoneNumber") String phoneNumber, @Param("email") String email, @Param("password") String password);

	/*
	 * CUSTOM QUERY TO UPDATE RECORD  
	 */
	@Transactional
	@Modifying
	@Query(value="update learner set department = ?1, phonenumber = ?2 where learnerid = ?3", nativeQuery=true)
	public void updateEntry(@Param("department") String department, 
			@Param("phoneNumber") String phoneNumber, @Param("id") int id);
}
