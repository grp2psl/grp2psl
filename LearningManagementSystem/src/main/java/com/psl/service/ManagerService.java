package com.psl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.psl.dao.IManagerDAO;
import com.psl.entities.Manager;

@Service("managerService")
public class ManagerService {
	@Autowired
	private IManagerDAO dao;
	
	/*
	 * ADD MANAGER
	 */
	public void addManager(Manager m) {
		Integer id = dao.getNextId();
		id = (id==null ? 30000 : id);
		m.setManagerid(id);
		dao.save(m);
	}
	
	/*
	 * GET DETAILS OF MANAGER BY ID
	 */
	public Manager getManager(int id) {
		return dao.findById(id).get();
	}
	
	/*
	 * UPDATE MANAGER DETAILS
	 */
	public void updateManager(Manager manager) {
		dao.save(manager);
	}

	public int getNextId() {
		Integer id = dao.getNextId();
		id = (id==null ? 30000 : id);
		return id;
	}
}
