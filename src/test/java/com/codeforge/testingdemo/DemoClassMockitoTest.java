package com.codeforge.testingdemo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

//Using Mockito without annotations
public class DemoClassMockitoTest
{
	@Test
	public void testPublicMethodNoArgsReturningString(){
		// all the object needed should be manually instantiated/mocked
		DependencyClass dependency = mock(DependencyClass.class);
		when(dependency.publicMethodNoArgsReturningString()).thenReturn("something");
		DemoClass demoClass = new DemoClass(dependency);
		assertEquals("publicMethodNoArgsReturningString - genuine", demoClass.publicMethodNoArgsReturningString());
	}
}
