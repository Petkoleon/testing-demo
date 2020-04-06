package com.codeforge.testingdemo;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;
//import org.junit.jupiter.api.Test;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DemoClassMockitoAnnotationsTest
{
	@Mock
	DependencyClass dependency;

	@InjectMocks
	DemoClass demoClass;

	//IMPORTANT: this test should be public, and @Test is org.junit.Test not org.junit.jupiter.api.Test!!!
	// This means there are both the Junit 4.x and Junit 5.x libraries on the classpath but likely don't have all the required
	// dependencies for 5.x, or whatever runner is used isn't updated to be able to run 5.x;
	// check what is wrong
	@Test
	public void testPublicMethodNoArgsReturningString()
	{
		assertEquals("publicMethodNoArgsReturningString - genuine", demoClass.publicMethodNoArgsReturningString());
	}

	// dependency class is mocked, so if we don't mock method implementation, then method returns null !!!
	@Test
	public void testPublicMethodNoArgsWithDependencyReturningString()
	{
		Assertions.assertEquals(
			"publicMethodNoArgsReturningString - genuine # null",
			demoClass.publicMethodNoArgsWithDependencyReturningString());
	}

	// mocked dependency class' method
	@Test
	public void testPublicMethodNoArgsWithDependencyReturningStringMockedDependencyMethod()
	{
		when(dependency.publicMethodNoArgsReturningString()).thenReturn("dependency.publicMethodNoArgsReturningString - mocked");
		Assertions.assertEquals(
			"publicMethodNoArgsReturningString - genuine # dependency.publicMethodNoArgsReturningString - mocked",
			demoClass.publicMethodNoArgsWithDependencyReturningString());
	}
}
