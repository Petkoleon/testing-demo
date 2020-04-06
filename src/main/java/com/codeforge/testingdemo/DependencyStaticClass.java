package com.codeforge.testingdemo;

import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DependencyStaticClass
{
	public static String publicStaticMethodNoArgsReturningString(){
		String str = "dependencyStatic.publicStaticMethodNoArgsReturningString - genuine";
		log.info("Entered method: " + str);
		return str;
	}

	public static void publicStaticVoidMethodNoArgs(){
		String str = "dependencyStatic.publicStaticVoidMethodNoArgs - genuine";
		log.info("Entered method: " + str);
	}
}
