package com.psl.dao;

import org.springframework.data.repository.CrudRepository;

import com.psl.entities.Trainer;

public interface ITrainerDAO extends CrudRepository<Trainer, Integer>{
}
