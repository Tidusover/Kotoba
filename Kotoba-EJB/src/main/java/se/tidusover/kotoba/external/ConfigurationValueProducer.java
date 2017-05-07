package se.tidusover.kotoba.external;

import static com.google.common.base.Strings.isNullOrEmpty;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;

public class ConfigurationValueProducer
{
	private PropertiesResolver propertiesResolver;
	
	@Inject
	public ConfigurationValueProducer(PropertiesResolver propertiesResolver)
	{
		this.propertiesResolver = propertiesResolver;
	}
	
	@Produces
	@ConfigurationValue
	public String getConfigurationValue(InjectionPoint injectionPoint)
	{
		String propertyKey = injectionPoint.getAnnotated().getAnnotation(ConfigurationValue.class).propertyKey();
		
		if(isNullOrEmpty(propertyKey))
		{
			throw new RuntimeException("Must contain an information provider name and a property name.");
		}
		
		return propertiesResolver.getProperty(propertyKey);
	}		
}
