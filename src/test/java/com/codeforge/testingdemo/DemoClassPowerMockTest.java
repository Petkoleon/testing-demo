package com.codeforge.testingdemo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

// Test ran by PowerMock runner; usually when needed to mock private or static methods, constructors...
// This test demo class is just to present how to setup class to work with PowerMockRunner and to execute Mockito tests
@RunWith(PowerMockRunner.class)
//@PrepareForTest({DependencyStaticClass.class})
// The following is used to define an array of fully qualified names of types we want to mock. In this case, we use a package
// name with a wildcard to tell PowerMockito to prepare all types within the com.codeforge.testingdemo package for mocking
//@PrepareForTest(fullyQualifiedNames = "com.codeforge.testingdemo.*")
public class DemoClassPowerMockTest
{
	@Mock
	DependencyClass dependency;

	@InjectMocks
	DemoClass demoClass;

	//IMPORTANT: this test should be public, and much important, @Test is org.junit.Test not org.junit.jupiter.api.Test!!!
	// This means there are both the Junit 4.x and Junit 5.x libraries on the classpath but likely don't have all the required
	// dependencies for 5.x, or whatever runner is used isn't updated to be able to run 5.x;
	// check what is wrong
	// UPDATE: it looks that PowerMock does not support JUnit5 yet (hence, org.junit.jupiter.api.Test annotation support is not
	// implemented yet!)

	// ordinary tests works as usual no matter which runner is used
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

	// mocking of static method -> moved to DemoClassPowerMockStaticMethodTest
//	@Test
//	public void testPublicMethodNoArgsWithDependencyReturningStringMockedPrivateDependencyMethod()
//	{
//		//creating new instance of DemoClass because DependencyClass is already mocked on class level (so, we need to prepare base object properly for test case)
//		PowerMockito.mockStatic(DependencyStaticClass.class);
//		Mockito.when(DependencyStaticClass.publicStaticMethodNoArgsReturningString()).thenReturn("dependency.publicStaticMethodNoArgsReturningString - mocked");
//		//this is test call
//		//demoClass.publicMethodNoArgsReturningStringWithStaticDependencyMethod();
//		Assertions.assertEquals(
//			"publicMethodNoArgsReturningStringWithStaticDependencyMethod - genuine # dependency.publicStaticMethodNoArgsReturningString - mocked",
//			demoClass.publicMethodNoArgsReturningStringWithStaticDependencyMethod());
//
//		// Different from Mockito, always use PowerMockito.verifyStatic(Class) first
//		// to start verifying behavior
//		PowerMockito.verifyStatic(DependencyStaticClass.class);
//		// IMPORTANT:  Call the static method you want to verify!!!
//		DependencyStaticClass.publicStaticMethodNoArgsReturningString();
//
//	}

}
