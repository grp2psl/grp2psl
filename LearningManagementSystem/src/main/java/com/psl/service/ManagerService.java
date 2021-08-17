package com.psl.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.psl.dao.IManagerDAO;
import com.psl.entities.Learner;
import com.psl.entities.Manager;

@Service("managerService")
public class ManagerService {
	@Autowired
	private IManagerDAO dao;

	public static final Logger LOGGER = LoggerFactory.getLogger(ManagerService.class);
	private final String logPrefix = "Manager Service - ";
	
	/*
	 * ADD MANAGER
	 */
	public void addManager(Manager m) {
		LOGGER.info(logPrefix+"Adding a manager - "+m);
		Integer id = dao.getNextId();
		id = (id==null ? 30000 : id);
		m.setManagerId(id);
		dao.save(m);
	}
	
	/*
	 * GET DETAILS OF MANAGER BY ID
	 */
	public Manager getManager(int id) {
		LOGGER.info(logPrefix+"Returning details of a manager with ID - "+id);
		return dao.findById(id).get();
	}
	
	/*
	 * UPDATE MANAGER DETAILS
	 */
	public void updateManager(Manager manager) {
		LOGGER.info(logPrefix+"Updating details of a manager to - "+manager);
		dao.save(manager);
	}

	public int getNextId() {
		LOGGER.info(logPrefix+"Getting next ID of manager");
		Integer id = dao.getNextId();
		id = (id==null ? 30000 : id);
		return id;
	}
	
	public Manager login(String email, String password) {
		Manager manager = dao.findByEmailAndPassword(email, password);
		return manager;
	}
	
	public String checkPassword(int id) {
		Manager manager = dao.findById(id).get();
		return manager.getPassword();
	}
	
	//Function which updates credentials of a manager with given learnerId.
		public Manager updateAdminPassword(int id, String password) {
			Manager m = dao.findById(id).get();
		    m.setPassword(password);
		    return dao.save(m);
		}
}
