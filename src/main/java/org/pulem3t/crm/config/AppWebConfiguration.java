package org.pulem3t.crm.config;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


@EnableWebMvc
@ComponentScan(basePackages={"org.pulem3t.crm.controller"})
public class AppWebConfiguration extends WebMvcConfigurerAdapter {
	
//	@Bean
//	public InternalResourceViewResolver viewResolver(){
//		InternalResourceViewResolver res = new InternalResourceViewResolver();
//		res.setPrefix("/WEB-INF/views");
//		res.setSuffix(".jsp");
//		return res;
//	}
	
//	@Bean
//	public MessageSource messageSource(){
//		ReloadableResourceBundleMessageSource messageSource = 
//				new ReloadableResourceBundleMessageSource();
//		messageSource.setBasename("/WEB-INF/messages/message");
//		messageSource.setDefaultEncoding("UTF-8");
//		messageSource.setCacheSeconds(5);
//		return messageSource;
//	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		registry.addResourceHandler("/crm/**").addResourceLocations("/static/");
		registry.addResourceHandler("/js/**").addResourceLocations("/static/js/");
		registry.addResourceHandler("/css/**").addResourceLocations("/static/css/");
		registry.addResourceHandler("/lib-css/**").addResourceLocations("/static/lib-css/");
		registry.addResourceHandler("/partials/**").addResourceLocations("/static/partials/");
		registry.addResourceHandler("favicon.png").addResourceLocations("/static/");
		registry.addResourceHandler("index.html").addResourceLocations("/static/");
	}

	
	
	@Bean
	public PropertyPlaceholderConfigurer placeholderConfig(){
		PropertyPlaceholderConfigurer props = new PropertyPlaceholderConfigurer();
		props.setLocations(new Resource[] {new ClassPathResource("config.properties")});
		return props;
	}
	


}
