package com.mx.examen.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * This class is responsible for initializing Spring
 * 
 * @author Ricardo Garcia Forte
 * @version 1.0
 * @created 2018-11-30
 */
public class WebConfigInitializer implements WebApplicationInitializer {

	/**
	 * This method is responsible for replacing and performing the tasks 
	 * that are performed in the web.xml file
	 */
	public void onStartup(ServletContext servletContext) throws ServletException {
		
		AnnotationConfigWebApplicationContext webContext = new AnnotationConfigWebApplicationContext();
		
		webContext.register(MvcConfig.class);
		webContext.setServletContext(servletContext);

		DispatcherServlet dispatcherServlet = new DispatcherServlet(webContext);
		dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
		
		ServletRegistration.Dynamic reDynamic = servletContext.addServlet("dispacher", dispatcherServlet);
		
		reDynamic.setLoadOnStartup(1);
		reDynamic.addMapping("/");	
	}

}
