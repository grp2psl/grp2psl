/*
 * Data Access Object Interface of Learner Entity
 * Present in com.psl.dao package
 */
package com.psl.dao;

//Necessary imports for DAO declaration
import org.springframework.stereotype.Repository;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.psl.entities.Learner;

/*
 * Learner DAO interface extends CRUD (Create, Read ,Update, Delete) Repository
 * CrudRepository has two parameters Learner and Integer
 * Learner is Entity Class on which CRUD operations are to be performed
 * Integer is data type of primary key of Learner Class
 */
@Repository
public interface ILearnerDAO extends CrudRepository<Learner,Integer>{
	/*
	 * CUSTOM QUERY TO HIDE PASSWORD
	 */
	@Query(value = "select learnerid, name, department, email, phonenumber, password=null as password from learner", nativeQuery = true)
	List<Learner> findAll();

	/*
	 * AUTO-INCREMENT ID
	 */
	@Query(value="select max(learnerid) + 1 from learner", nativeQuery=true)
	Integer getNextId();

	/*
	 * CUSTOM QUERY FOR NEW RECORD INSERTION  
	 */
	@Modifying
	@Transactional
	@Query(value="insert into learner(learnerid, name, department, phonenumber, email, password) values(?1, ?2, ?3, ?4, ?5, ?6)", nativeQuery=true)
	void saveNewEntry(@Param("id") int id, @Param("name") String name, @Param("department") String department, 
			@Param("phoneNumber") String phoneNumber, @Param("email") String email, @Param("password") String password);

	/*
	 * CUSTOM QUERY TO UPDATE RECORD  
	 */
	@Transactional
	@Modifying
	@Query(value="update learner set department = ?1, phonenumber = ?2 where learnerid = ?3", nativeQuery=true)
	void updateEntry(@Param("department") String department, 
			@Param("phoneNumber") String phoneNumber, @Param("id") int id);
	

	Learner findByEmail(String email);
}
