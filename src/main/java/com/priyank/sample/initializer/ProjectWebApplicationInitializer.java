package com.priyank.sample.initializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.priyank.sample.configuration.WebConfig;

public class ProjectWebApplicationInitializer implements
		WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext container) throws ServletException {
		AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
		applicationContext.register(WebConfig.class);
		Dynamic registration = container.addServlet("dispatcher",
				new DispatcherServlet(applicationContext));
		registration.setLoadOnStartup(1);
		registration.addMapping("/");

	}
}
