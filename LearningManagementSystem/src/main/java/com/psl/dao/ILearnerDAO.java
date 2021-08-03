package com.psl.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

import com.psl.entities.Learner;

public interface ILearnerDAO extends CrudRepository<Learner,Integer>{

    // List<Learner> findById(int learnerid);
    List<Learner> findByEmail(String email);
    
    @Query("SELECT r.learnerid FROM Learner r where r.email = :email") 
    int findLearneridByEmail(@Param("email") String email);
}
