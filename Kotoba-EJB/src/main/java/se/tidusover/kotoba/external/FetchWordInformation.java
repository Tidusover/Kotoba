package se.tidusover.kotoba.external;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

import javax.annotation.Nonnull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.common.base.Strings;

import lombok.Getter;

public abstract class FetchWordInformation
{
	protected final Logger LOG = LoggerFactory.getLogger(getClass());
	
	protected final static String[] VALID_PROTOCOLS = {"http", "https"};
	
	public final String INFORMATION_PROVIDER;
	@Getter private final URI informationProviderUri;
	@Getter private final URI informationProviderApiUri;
	
	protected FetchWordInformation(String informationProvider, String informationProviderUrl, String informationProviderApiUrl)
	{
		validateStringParameter(informationProvider);
		validateStringURLParameter(informationProviderUrl);
		validateStringURLParameter(informationProviderApiUrl);
		
		this.INFORMATION_PROVIDER = informationProvider;
		this.informationProviderUri = URI.create(informationProviderUrl);
		this.informationProviderApiUri = URI.create(informationProviderApiUrl);
	}
	
	public abstract Optional<?> getAllMatchingWords(@Nonnull String keyword) throws JsonParseException, JsonMappingException, IOException;
	
	public abstract boolean isInformationProviderOnline();
	
	private static void validateStringParameter(String string)
	{
		if(Strings.isNullOrEmpty(string))
		{
			throw new IllegalArgumentException("Provider name cannot be null or empty.");
		}
	}
	
	private static void validateStringURLParameter(String stringURL)
	{
		validateStringParameter(stringURL);
		
		try
		{
			URL url = new URL(stringURL);
			boolean urlMatchesValidProtocol = Arrays.stream(VALID_PROTOCOLS)
					.anyMatch(tjafs -> Objects.equals(tjafs, url.getProtocol()));
			if(!urlMatchesValidProtocol)
			{
				throw new IllegalArgumentException("URL contains invalid protocol.");
			}
			url.toURI();
		}
		catch (MalformedURLException | URISyntaxException e)
		{
			throw new IllegalArgumentException("URL is invalid.");
		}
	}
}