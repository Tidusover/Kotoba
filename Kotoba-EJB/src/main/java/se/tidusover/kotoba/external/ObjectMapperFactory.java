package se.tidusover.kotoba.external;

import javax.ejb.Singleton;
import javax.enterprise.inject.Produces;

import com.fasterxml.jackson.databind.ObjectMapper;

@Singleton
public class ObjectMapperFactory
{
	@Produces
	public ObjectMapper getGson()
	{
		return new ObjectMapper();
	}
}