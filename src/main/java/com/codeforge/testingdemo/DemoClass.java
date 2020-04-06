package com.codeforge.testingdemo;

import org.springframework.stereotype.Component;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@Data
@RequiredArgsConstructor
public class DemoClass
{
	private final DependencyClass dependency;

	public String publicMethodNoArgsReturningString(){
		String str = "publicMethodNoArgsReturningString - genuine";
		log.info("Entered method: " + str);
		return str;
	}

	public String publicMethodNoArgsWithDependencyReturningString(){
		String depStr = dependency.publicMethodNoArgsReturningString();
		String str = "publicMethodNoArgsReturningString - genuine";
		log.info("Entered method: " + str);
		return str + " # " + depStr;
	}

	public void publicVoidMethodNoArgs(){
		String str = "publicVoidMethodNoArgs - genuine";
		log.info("Entered method: " + str);
	}

	public String publicMethodReturningString(String input){
		String str = "publicMethodNoArgsReturningString - genuine";
		log.info("Entered method: " + str + " with input parameter " + input);
		return str;
	}

	public void publicVoidMethod(String input){
		String str = "publicVoidMethod - genuine";
		log.info("Entered method: " + str + " with input parameter " + input);
	}

	private String privateMethodNoArgsReturningString(){
		String str = "privateMethodReturningString - genuine";
		log.info("Entered method: " + str);
		return str;
	}

	private void privateVoidMethodNoArgs(){
		String str = "privateVoidMethod - genuine";
		log.info("Entered method: " + str);
	}

	public String publicMethodNoArgsReturningStringWithStaticDependencyMethod(){
		String str = "publicMethodNoArgsReturningStringWithStaticDependencyMethod - genuine";
		log.info("Entered method: " + str);
		String depStr =  DependencyStaticClass.publicStaticMethodNoArgsReturningString();
		return str + " # " + depStr;
	}

	public String publicVoidMethodNoArgsReturningStringWithVoidStaticDependencyMethod(){
		String str = "publicMethodNoArgsReturningStringWithStaticDependencyMethod - genuine";
		log.info("Entered method: " + str);
		DependencyStaticClass.publicStaticVoidMethodNoArgs();
		return str + " # void";
	}

	public String publicMethodNoArgsReturningStringWithDependencyMethodCallingPrivateMethod(){
		String str = "publicMethodNoArgsReturningStringWithDependencyMethodCallingPrivateMethod - genuine";
		log.info("Entered method: " + str);
		String depStr =  dependency.publicMethodNoArgsReturningStringWithPrivateCall();
		return str + " # " + depStr;
	}
}
