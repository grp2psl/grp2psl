/*
 * Data Access Object Interface of Learner Entity
 * Present in com.psl.dao package
 */
package com.psl.dao;

//Necessary imports for DAO declaration
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.psl.entities.Learner;

/*
 * Learner DAO interface extends CRUD (Create, Read ,Update, Delete) Repository
 * CrudRepository has two parameters Learner and Integer
 * Learner is Entity Class on which CRUD operations are to be performed
 * Integer is data type of primary key of Learner Class
 */
@Repository
public interface ILearnerDAO extends CrudRepository<Learner,Integer>{
}
