package com.devangam;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

import com.devangam.entity.AdvertisementEntity;
import com.devangam.entity.Education;
import com.devangam.entity.Events;
import com.devangam.entity.Matrimony;
import com.devangam.entity.OldageHome;
import com.devangam.entity.Patients;

@Configuration
public class RepositoryConfig extends RepositoryRestConfigurerAdapter {
	 @Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config)
	    {
	        super.configureRepositoryRestConfiguration(config);
	        config.exposeIdsFor(Education.class,
	        					Events.class,
	        					AdvertisementEntity.class,
	        					Matrimony.class,
	        					OldageHome.class,
	        					Patients.class);
	    }
 /*   @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(true);
        provider.addIncludeFilter(new AssignableTypeFilter(IdentifiableEntity.class));
        Set<BeanDefinition> components = provider.findCandidateComponents(this.getClass().getPackage().getName());
        List<Class<?>> classes = new ArrayList<>();
 
        components.forEach(component -> {
            try {
                classes.add(Class.forName(component.getBeanClassName()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
 
        config.exposeIdsFor(classes.toArray(new Class[classes.size()]));
    }*/
}