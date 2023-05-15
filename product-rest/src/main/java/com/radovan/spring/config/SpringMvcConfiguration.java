package com.radovan.spring.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.radovan.spring")
public class SpringMvcConfiguration implements WebMvcConfigurer{

	@Bean
	public SpringResourceTemplateResolver getTemplateResolver() {
		SpringResourceTemplateResolver returnValue = new SpringResourceTemplateResolver();
		returnValue.setPrefix("/WEB-INF/templates/");
		returnValue.setSuffix(".html");
		returnValue.setCacheable(false);
		return returnValue;
	}
	
	@Bean
	public SpringTemplateEngine getTemplateEngine() {
		SpringTemplateEngine returnValue = new SpringTemplateEngine();
		returnValue.setTemplateResolver(getTemplateResolver());
		returnValue.setEnableSpringELCompiler(true);
		return returnValue;
	}
	
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(getTemplateEngine());
		registry.viewResolver(viewResolver);
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");
	}
	
	@Bean
	public ModelMapper getModelMapper() {
		ModelMapper returnValue = new ModelMapper();
		returnValue.getConfiguration().setAmbiguityIgnored(true).setFieldAccessLevel(AccessLevel.PRIVATE);
		returnValue.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return returnValue;
	}
	
	
	
	
	
	
	
	
	
}
