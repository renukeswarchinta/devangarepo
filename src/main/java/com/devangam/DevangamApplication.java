package com.devangam;

import org.apache.catalina.Container;
import org.apache.catalina.startup.Tomcat;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DevangamApplication {
	public static void main(String[] args) {
		SpringApplication.run(DevangamApplication.class, args);
	}
	
	@Bean
	public TomcatEmbeddedServletContainerFactory tomcatFactory() {
	    return new TomcatEmbeddedServletContainerFactory() {
	        @Override
	        protected TomcatEmbeddedServletContainer getTomcatEmbeddedServletContainer(
	                Tomcat tomcat) {
	            tomcat.enableNaming();
	            String contextPath = getContextPath();
	            Container[] findChildren = tomcat.getHost().findChildren();
	            return super.getTomcatEmbeddedServletContainer(tomcat);
	        }
	    };
}
}