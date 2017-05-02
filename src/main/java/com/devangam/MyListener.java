package com.devangam;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.devangam.service.AdvertisementService;

public class MyListener implements ApplicationListener {

	 @Override
	    public void onApplicationEvent(ApplicationEvent event) {
	        if (event instanceof ContextRefreshedEvent) {
	            ApplicationContext applicationContext = ((ContextRefreshedEvent) event).getApplicationContext();
	           System.out.println("In listner "+applicationContext.getBeanDefinitionCount());
	            
	        }
	    }

}
