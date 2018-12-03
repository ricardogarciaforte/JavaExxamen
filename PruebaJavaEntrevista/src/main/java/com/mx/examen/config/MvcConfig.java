package com.mx.examen.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * This class is responsible for indicating to the Spring MVC, 
 * how it should resovler the URLs executed in the system.
 * 
 * @author Ricardo Garcia Forte
 * @version 1.0
 * @created 2018-11-30
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.mx.examen")
public class MvcConfig extends WebMvcConfigurerAdapter {
	
	@Bean(name = "jspViewResolver")
	public InternalResourceViewResolver internalResourceViewResolver() {
		
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		//indicates where the application pages are
		resolver.setPrefix("/WEB-INF/views/");
		//indicates the type of pages to be executed
		resolver.setSuffix(".jsp");
		resolver.setViewClass(JstlView.class);
		
		return resolver;
	}

	/**
	 * Is responsible for indicating to Spring where to look for the application style files.
	 */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	
       registry.addResourceHandler("/css/**").addResourceLocations("/static/css/");
    }

}
