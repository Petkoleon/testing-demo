package com.codeforge.testingdemo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
@PrepareForTest({DependencyClass.class})
public class DemoClassPowerMockPrivateMethodTest
{
	@Test
	public void testPublicMethodNoArgsWithDependencyReturningStringMockedStaticDependencyMethod() throws Exception
	{
		DependencyClass dependencySpy = PowerMockito.spy(new DependencyClass());
		PowerMockito.when(dependencySpy, "privateMethodNoArgsReturningString")
			.thenReturn("dependency.privateMethodNoArgsReturningString - mocked");
		DemoClass demoClass = new DemoClass(dependencySpy);

		//this is test call
		Assertions.assertEquals(
			"publicMethodNoArgsReturningStringWithDependencyMethodCallingPrivateMethod - genuine # dependency"
				+ ".publicMethodNoArgsReturningStringWithPrivateCall - genuine # dependency.privateMethodNoArgsReturningString - "
				+ "mocked",
			demoClass.publicMethodNoArgsReturningStringWithDependencyMethodCallingPrivateMethod());

		//use PowerMockito.verifyPrivate() to verify private mocked method; in verifyPrivate provide spied object, not the class as with verifyStatic !!!
		PowerMockito.verifyPrivate(dependencySpy).invoke("privateMethodNoArgsReturningString");
	}
}
