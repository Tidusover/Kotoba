package se.tidusover.kotoba.external;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;

import javax.inject.Inject;
import javax.ws.rs.core.UriBuilder;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import se.tidusover.kotoba.external.json.jisho.JishoRoot;

public class JishoWordInformation extends FetchWordInformation
{
	private final static String KEYWORD_PARAMETER = "keyword";
	private final ObjectMapper objectMapper;
	
	@Inject
	public JishoWordInformation(ObjectMapper objectMapper,
			@ConfigurationValue(propertyKey = "jisho.informationprovider.name")
			String informationProviderName,
			@ConfigurationValue(propertyKey = "jisho.informationprovider.url")
			String informationProviderUrl,
			@ConfigurationValue(propertyKey = "jisho.informationprovider.apiurl")
			String informationProviderApiUr)
	{
		super(informationProviderName,
				informationProviderUrl,
				informationProviderApiUr);
		this.objectMapper = objectMapper;
	}
	
	@Override
	public Optional<JishoRoot> getAllMatchingWords(String keyword)
	{
		URL queryUrl = getUrlWithKeywordParameter(keyword);
		
		JishoRoot retrievedData = null;
		try
		{
			retrievedData = objectMapper.readValue(queryUrl, JishoRoot.class);
		}
		catch (JsonParseException e)
		{
			throw new RuntimeException("Parsing of JSON-input went wrong.", e);
		}
		catch (JsonMappingException e)
		{
			throw new RuntimeException("Mapping of JSON to object went wrong.", e);
		}
		catch (IOException e)
		{
			StringBuilder sb = new StringBuilder()
					.append("Communication with: ")
					.append(getInformationProvider())
					.append(" went wrong.");
			throw new RuntimeException(sb.toString(), e);
		}
		
		return Optional.ofNullable(retrievedData);
	}

	private URL getUrlWithKeywordParameter(String keyword)
	{
		URL queryUrl = null;
		try
		{
			queryUrl = UriBuilder.fromUri(getInformationProviderApiUri())
					.queryParam(KEYWORD_PARAMETER, keyword)
					.build()
					.toURL();
		}
		catch (MalformedURLException e)
		{
			throw new RuntimeException("Invalid URL provided.", e);
		}
		return queryUrl;
	}

	@Override
	public boolean isInformationProviderOnline()
	{
		
		return false;
	}
}