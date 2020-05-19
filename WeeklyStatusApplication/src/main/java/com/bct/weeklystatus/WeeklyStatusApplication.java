package com.bct.weeklystatus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.WebApplicationInitializer;

@SpringBootApplication
public class WeeklyStatusApplication extends SpringBootServletInitializer implements WebApplicationInitializer  {

	public static void main(String[] args) {
		System.setProperty("server.servlet.context-path", "/weeklystatus");
		SpringApplication.run(WeeklyStatusApplication.class, args);	}

	 
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(WeeklyStatusApplication.class);
	}
}
