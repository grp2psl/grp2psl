package com.psl;

import org.junit.runner.RunWith;
import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
//@DataJpaTest
//@SpringBootTest
//@AutoConfigureTestDatabase(replace = Replace.NONE)
//@TestMethodOrder(OrderAnnotation.class)
@ContextConfiguration(classes= LearningManagementSystemApplication.class)
class LearningManagementSystemApplicationTests {

	@Test
	void contextLoads() {
	}
	
}
