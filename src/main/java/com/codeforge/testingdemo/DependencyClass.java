package com.codeforge.testingdemo;

import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DependencyClass
{
	public String publicMethodNoArgsReturningString(){
		String str = "dependency.publicMethodNoArgsReturningString - genuine";
		log.info("Entered method: " + str);
		return str;
	}

	public String publicMethodNoArgsReturningStringWithPrivateCall(){
		String str = "dependency.publicMethodNoArgsReturningStringWithPrivateCall - genuine";
		log.info("Entered method: " + str);
		String privateCallResult = privateMethodNoArgsReturningString();
		log.info("Called private method: " + privateCallResult);
		return str + " # " +privateCallResult;
	}

	private String privateMethodNoArgsReturningString(){
		String str = "dependency.privateMethodNoArgsReturningString - genuine";
		log.info("Entered method: " + str);
		return str;
	}
}
