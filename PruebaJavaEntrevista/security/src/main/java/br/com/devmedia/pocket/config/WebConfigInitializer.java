package br.com.devmedia.pocket.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class WebConfigInitializer implements WebApplicationInitializer {

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
