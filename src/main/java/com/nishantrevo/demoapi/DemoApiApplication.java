package com.nishantrevo.demoapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DemoApiApplication {
	
	private static ConfigurableApplicationContext applicationContext;
	
	public static void main(String[] args) {
		ConfigurableApplicationContext con = SpringApplication.run(DemoApiApplication.class, args);
		applicationContext = con;
	}
	
	public static void close(){
		if(applicationContext != null)
			applicationContext.close();
	}

}
