package com.codeforge.testingdemo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//with @SpringBootTest application context will be started !!!
//indicates that the context under test is a @SpringBootApplication.
//The complete TestingDemoApplication is launched up during the unit test.
@SpringBootTest
public class DemoClassSpringBootTest
{
	//Autowiring will be done by Spring (demo class has to be somehow already configured properly: @Component, @Bean...)
	@Autowired
	DemoClass demoClass;

	@Test
	public void testPublicMethodNoArgsReturningString(){
		assertEquals("publicMethodNoArgsReturningString - genuine", demoClass.publicMethodNoArgsReturningString());
	}
}
