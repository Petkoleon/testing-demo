package com.codeforge.testingdemo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

// Spring application context will be loaded by using @SpringBootTest, so all beans will be created;
// if some application properties are needed, they should be provided to test context !
@SpringBootTest
class TestingDemoApplicationTests {

	// application load will be tested
	@Test
	void contextLoads() {
	}

}
