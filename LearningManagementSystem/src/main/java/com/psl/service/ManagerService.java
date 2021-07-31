package com.psl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.psl.dao.IManagerDAO;
import com.psl.entities.Manager;

@Service("managerService")
public class ManagerService {
	@Autowired
	private IManagerDAO dao;
	
	public void addManager(Manager m) {
		dao.save(m);
	}
	
	public Manager getManager(int id) {
		return dao.findById(id).get();
	}
}
