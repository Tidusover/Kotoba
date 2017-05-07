package se.tidusover.kotoba.external;

import javax.ejb.Singleton;
import javax.enterprise.inject.Produces;

import okhttp3.OkHttpClient;

@Singleton
public class HttpClientFactory
{
	private static final OkHttpClient INSTANCE = new OkHttpClient();
	
	@Produces
	public OkHttpClient getHttpClient()
	{
		return INSTANCE;
	}
}
