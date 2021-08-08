package com.psl.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.psl.entities.Manager;
import com.psl.entities.Trainer;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class ManagerServiceTest {
	@Autowired
	ManagerService service;

	/*
	 * TEST ADD MANAGER
	 */
	@Test
	@Order(1)
	public void addManagerTest() throws JsonMappingException, JsonProcessingException {
		int id = service.getNextId();
		String request = "{\"name\":\"John Radnor\",\"email\":\"group2.learning.management.system@gmail.com\","
				+ "\"password\":\"admin123\",\"phonenumber\":\"9657892335\"}";
		ObjectMapper mapper = new ObjectMapper();
		Manager manager = mapper.readValue(request, Manager.class);		
		service.addManager(manager);
		Manager createdManager = service.getManager(id);
		assertNotNull(createdManager);
		assertThat(createdManager.getName()).isEqualTo("John Radnor");
		assertThat(createdManager.getEmail()).isEqualTo("group2.learning.management.system@gmail.com");
		assertThat(createdManager.getPassword()).isEqualTo("admin123");		
		assertThat(createdManager.getPhonenumber()).isEqualTo("9657892335");
	}
	
	/*
	 * TEST ADD MANAGER WITH DUPLICATE EMAIL ID
	 */
	@Test
	@Order(2)
	public void addManagerDuplicateEmailTest() throws JsonMappingException, JsonProcessingException {
		String request = "{\"name\":\"John Radnor\",\"email\":\"group2.learning.management.system@gmail.com\","
				+ "\"password\":\"admin123\",\"phonenumber\":\"9657892335\"}";
		ObjectMapper mapper = new ObjectMapper();
		Manager manager = mapper.readValue(request, Manager.class);		
		assertThrows(DataIntegrityViolationException.class, () -> service.addManager(manager));
	}
	
	/*
	 * TEST GET DETAILS OF MANAGER BY ID
	 */
	@Test
	@Order(3)
	public void getManagerTest() {
		int id = service.getNextId();
		Manager manager = service.getManager(id - 1);
		assertNotNull(manager);
		assertThat(manager.getName()).isEqualTo("John Radnor");
		assertThat(manager.getEmail()).isEqualTo("group2.learning.management.system@gmail.com");
		assertThat(manager.getPhonenumber()).isEqualTo("9657892335");
		assertThat(manager.getPassword()).isEqualTo("admin123");
	}
	
	/*
	 * TEST UPDATE MANAGER DETAILS
	 */
	@Test
	@Order(4)
	public void updateManagerTest() {
		int id = service.getNextId() - 1;
		Manager manager = service.getManager(id);
		manager.setPassword("manager123");
		service.updateManager(manager);
		manager = service.getManager(id);
		assertNotNull(manager);
		assertThat(manager.getName()).isEqualTo("John Radnor");
		assertThat(manager.getEmail()).isEqualTo("group2.learning.management.system@gmail.com");
		assertThat(manager.getPhonenumber()).isEqualTo("9657892335");
		assertThat(manager.getPassword()).isEqualTo("manager123");
	}

}
