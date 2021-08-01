package com.psl.dao;

import org.springframework.data.repository.CrudRepository;

import com.psl.entities.Manager;

public interface IManagerDAO extends CrudRepository<Manager, Integer>{
}
