package com.psl.dao;

import org.springframework.data.repository.CrudRepository;

import com.psl.entity.Learner;

public interface ILearnerDAO extends CrudRepository<Learner,Integer>{
}
