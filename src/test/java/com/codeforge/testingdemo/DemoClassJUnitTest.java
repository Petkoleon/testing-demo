package com.codeforge.testingdemo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//Plain JUnit5 test
public class DemoClassJUnitTest
{
	DemoClass demoClass = new DemoClass(null);

	@Test
	public void testPublicMethodNoArgsReturningString(){
		assertEquals("publicMethodNoArgsReturningString - genuine", demoClass.publicMethodNoArgsReturningString());
	}

}
