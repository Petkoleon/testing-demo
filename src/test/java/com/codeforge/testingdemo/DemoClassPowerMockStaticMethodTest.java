package com.codeforge.testingdemo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.powermock.api.mockito.PowerMockito.when;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ DependencyStaticClass.class })
public class DemoClassPowerMockStaticMethodTest
{
	@Mock
	DependencyClass dependency;

	@InjectMocks
	DemoClass demoClass;

	@Test
	public void testPublicMethodNoArgsWithDependencyReturningStringMockedStaticDependencyMethod()
	{
		PowerMockito.mockStatic(DependencyStaticClass.class);
		Mockito.when(DependencyStaticClass.publicStaticMethodNoArgsReturningString())
			.thenReturn("dependencyStatic.publicStaticMethodNoArgsReturningString - mocked");

		//this is test call, i.e in assertEquals we actually have test method call
		Assertions.assertEquals(
			"publicMethodNoArgsReturningStringWithStaticDependencyMethod - genuine # dependencyStatic"
				+ ".publicStaticMethodNoArgsReturningString - mocked",
			demoClass.publicMethodNoArgsReturningStringWithStaticDependencyMethod());

		// Different from Mockito, always use PowerMockito.verifyStatic(Class) first
		// to start verifying behavior
		PowerMockito.verifyStatic(DependencyStaticClass.class);
		// IMPORTANT:  Call the static method you want to verify!!!
		DependencyStaticClass.publicStaticMethodNoArgsReturningString();
	}

	@Test(expected = TestException.class)
	public void staticVoidMethodMockingThrowError() throws Exception
	{
		String errorMessage = "Static void method is mocked to throw error";
		PowerMockito.mockStatic(DependencyStaticClass.class);

		//Note: doThrow() is used instead when().thenThrow() as the compiler doesn't allow void methods inside brackets
		PowerMockito.doThrow(new TestException(errorMessage)).when(DependencyStaticClass.class, "publicStaticVoidMethodNoArgs");

		// NOTE: Different from Mockito, always use PowerMockito.verifyStatic(Class) first
		// to start verifying behavior
		DependencyStaticClass.publicStaticVoidMethodNoArgs();
		PowerMockito.verifyStatic(DependencyStaticClass.class, times(1));
		//if we call static method mock directly as test call, then there is no need to specify which static method to call in
		// verify mode (see previous method)!
		//DependencyStaticClass.publicStaticMethodNoArgsReturningString();
	}

	// if we want to check exception type by using @Test(expected = XXX.class), we have to rethrow exception in try-catch
	@Test(expected = TestException.class)
	public void staticVoidMethodMockingThrowErrorMessageChecked() throws Exception
	{
		String errorMessage = "Static void method is mocked to throw error";
		PowerMockito.mockStatic(DependencyStaticClass.class);

		//Note: doThrow() is used instead when().thenThrow() as the compiler doesn't allow void methods inside brackets
		PowerMockito.doThrow(new TestException(errorMessage)).when(DependencyStaticClass.class, "publicStaticVoidMethodNoArgs");

		try
		{
			//This is just a trivial call of mocked static method
			DependencyStaticClass.publicStaticVoidMethodNoArgs();
			PowerMockito.verifyStatic(DependencyStaticClass.class, times(1));
		}
		catch (Exception ex)
		{
			assertEquals(errorMessage, ex.getMessage());
			System.out.println("Message is checked!");
			// if we want to check exception by using @Test(expected = XXXException.class) we have to rethrow exception
			throw ex;
		}
	}

	// if we want to check exception type by using @Test(expected = XXXException.class), we have to rethrow exception in try-catch
	@Test(expected = TestException.class)
	public void staticVoidMethodMockingThrowErrorMessageChecked1() throws Exception
	{
		String errorMessage = "Static void method is mocked to throw error";
		PowerMockito.mockStatic(DependencyStaticClass.class);

		//Note: doThrow() is used instead when().thenThrow() as the compiler doesn't allow void methods inside brackets
		PowerMockito.doThrow(new TestException(errorMessage)).when(DependencyStaticClass.class, "publicStaticVoidMethodNoArgs");

		DemoClass demoCls = new DemoClass(new DependencyClass());
		// NOTE: Different from Mockito, always use PowerMockito.verifyStatic(Class) first
		// to start verifying behavior
		try
		{
			demoCls.publicVoidMethodNoArgsReturningStringWithVoidStaticDependencyMethod();
		}
		catch (Exception ex)
		{
			assertEquals(errorMessage, ex.getMessage());
			System.out.println("Message is checked!");

			PowerMockito.verifyStatic(DependencyStaticClass.class, times(1));
			//we must define method call which should be verified!!!
			DependencyStaticClass.publicStaticVoidMethodNoArgs();
			// if we want to check exception by using @Test(expected = XXXException.class)
			throw ex;
		}
	}

	// 1. if we DON'T want to check exception type by using @Test(expected = XXX.class), we have to check exception type for
	// ourselves
	// 2. if we are mocking static method call (concrete static method of the class)
	@Test
	public void staticVoidMethodMockingThrowErrorMessageCheckedNoExpectedAnnotation() throws Exception
	{
		String errorMessage = "Static void method is mocked to throw error";
		PowerMockito.mockStatic(DependencyStaticClass.class);

		//Note: doThrow() is used instead when().thenThrow() as the compiler doesn't allow void methods inside brackets
		//PowerMockito.doThrow(new Error("user name must be not empty")).when(Calculator.class);
		//we are mocking concrete method, not class as a whole...
		PowerMockito.doThrow(new TestException(errorMessage)).when(DependencyStaticClass.class, "publicStaticVoidMethodNoArgs");

		// NOTE: check this: Different from Mockito, always use PowerMockito.verifyStatic(Class) first
		// to start verifying behavior
		try
		{
			DependencyStaticClass.publicStaticVoidMethodNoArgs();
			PowerMockito.verifyStatic(DependencyStaticClass.class, times(1));
		}
		catch (Exception ex)
		{
			assertEquals(errorMessage, ex.getMessage());
			System.out.println("Message is checked!");
			// if we don't want to check exception by using @Test(expected = XXX.class) we have to check exception type by
			// ourselves
			assertTrue(ex instanceof TestException);
		}
	}

	// 1. if we DON'T want to check exception type by using @Test(expected = XXX.class), we have to check exception type for
	// ourselves
	// 2. if we are mocking static method call out of doThrow() method
	@Test
	public void staticVoidMethodMockingThrowErrorMessageCheckedNoExpectedAnnotationMethodBehaviorNotInDoThrow() throws Exception
	{
		String errorMessage = "Static void method is mocked to throw error";
		PowerMockito.mockStatic(DependencyStaticClass.class);

		//Note: doThrow() is used instead when().thenThrow() as the compiler doesn't allow void methods inside brackets
		//PowerMockito.doThrow(new Error("user name must be not empty")).when(Calculator.class);
		//we are mocking concrete method, not class as a whole...
		PowerMockito.doThrow(new TestException(errorMessage)).when(DependencyStaticClass.class);
		//the following line is part of mocking process !!! not the validation part!!!
		DependencyStaticClass.publicStaticVoidMethodNoArgs();
		// previous two lines are the same as:
		// PowerMockito.doThrow(new TestException(errorMessage)).when(DependencyStaticClass.class, "publicStaticVoidMethodNoArgs");

		// NOTE: check this: Different from Mockito, always use PowerMockito.verifyStatic(Class) first
		// to start verifying behavior
		try
		{
			DependencyStaticClass.publicStaticVoidMethodNoArgs();
			PowerMockito.verifyStatic(DependencyStaticClass.class, times(1));
		}
		catch (Exception ex)
		{
			assertEquals(errorMessage, ex.getMessage());
			System.out.println("Message is checked!");
			// if we don't want to check exception by using @Test(expected = XXX.class) we have to check exception type by
			// ourselves
			assertTrue(ex instanceof TestException);
		}
	}

	// 1. if we DON'T want to check exception type by using @Test(expected = XXX.class), we have to check exception type for
	// ourselves
	// 2. if we are mocking static method call out of doThrow() method
	@Test
	public void staticMethodReturningStringMockingThrowErrorMessageCheckedNoExpectedAnnotationMethodBehaviorNotInDoThrow()
		throws Exception
	{
		String errorMessage = "Static void method is mocked to throw error";
		PowerMockito.mockStatic(DependencyStaticClass.class);

		//Note: doThrow() is used instead when().thenThrow() as the compiler doesn't allow void methods inside brackets
		//PowerMockito.doThrow(new Error("user name must be not empty")).when(Calculator.class);
		//we are mocking concrete method, not class as a whole...
		when(DependencyStaticClass.publicStaticMethodNoArgsReturningString()).thenThrow(new TestException(errorMessage));

		// NOTE: check this: Different from Mockito, always use PowerMockito.verifyStatic(Class) first
		// to start verifying behavior
		try
		{
			DependencyStaticClass.publicStaticMethodNoArgsReturningString();
			PowerMockito.verifyStatic(DependencyStaticClass.class, times(1));
		}
		catch (Exception ex)
		{
			assertEquals(errorMessage, ex.getMessage());
			System.out.println("Message is checked!");
			// if we don't want to check exception by using @Test(expected = XXX.class) we have to check exception type by
			// ourselves
			assertTrue(ex instanceof TestException);
		}
	}

	@Test
	public void staticMethodReturningStringMockingThrowErrorMessageCheckedNoExpectedAnnotationMethodBehaviorNotInDoThrow1()
		throws Exception
	{
		String errorMessage = "Static void method is mocked to throw error";
		PowerMockito.mockStatic(DependencyStaticClass.class);

		//Note: doThrow() is used instead when().thenThrow() as the compiler doesn't allow void methods inside brackets
		//PowerMockito.doThrow(new Error("user name must be not empty")).when(Calculator.class);
		//we are mocking concrete method, not class as a whole...
		when(DependencyStaticClass.publicStaticMethodNoArgsReturningString()).thenThrow(new TestException(errorMessage));

		// NOTE: check this: Different from Mockito, always use PowerMockito.verifyStatic(Class) first
		// to start verifying behavior
		try
		{
			DependencyStaticClass.publicStaticMethodNoArgsReturningString();
			PowerMockito.verifyStatic(DependencyStaticClass.class, times(1));
		}
		catch (Exception ex)
		{
			assertEquals(errorMessage, ex.getMessage());
			System.out.println("Message is checked!");
			// if we don't want to check exception by using @Test(expected = XXX.class) we have to check exception type by
			// ourselves
			assertTrue(ex instanceof TestException);
		}
	}
}
