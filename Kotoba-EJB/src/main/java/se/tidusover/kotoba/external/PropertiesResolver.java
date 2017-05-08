package se.tidusover.kotoba.external;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import se.tidusover.kotoba.tool.helper.ResourceUtils;

@ApplicationScoped
public class PropertiesResolver
{
	private static final String PROPERTIES_FILE_EXTENSION = ".properties";
	private static final String PROPERTIES_DIRECTORY = "properties";
	private static final String PROPERTIES_KEY_DELIMITER = ".";
	private static volatile Map<String, Properties> availablePropertiesMap;
	
	@Inject
	private ResourceUtils resourcesUtil;

	@PostConstruct
	private void init()
	{
		File propertiesDirectory = resourcesUtil.loadDirectoryFromClasspath(PROPERTIES_DIRECTORY);
		
		availablePropertiesMap = getPropertiesFromDirectory(propertiesDirectory);
		
		if (availablePropertiesMap.isEmpty())
		{
			throw new RuntimeException("No properties found.");
		}
	}
	
	public String getPropertyValue(String propertyKeyPath)
	{
		String propertyFilename = getPropertyFilename(propertyKeyPath);
		String propertyKey = getPropertyKey(propertyKeyPath);

		Properties properties = availablePropertiesMap.entrySet().stream()
				.filter(propertiesSet -> propertyFilename.equals(propertiesSet.getKey()))
				.map(propertiesSet -> propertiesSet.getValue())
				.findFirst()
				.orElseThrow(() -> new RuntimeException("Properties file: " + propertyFilename + " not found."));
		
		String propertyValue = properties.getProperty(propertyKey);
		if (Objects.isNull(propertyValue))
		{
			throw new RuntimeException("Property: " + propertyKey + " not found.");
		}
		
		return propertyValue;
	}
	
	private String getPropertyFilename(String propertyKeyPath)
	{
		StringBuilder sb = new StringBuilder()
				.append(propertyKeyPath.substring(0, propertyKeyPath.indexOf(PROPERTIES_KEY_DELIMITER)))
				.append(PROPERTIES_FILE_EXTENSION);
		
		return sb.toString();
	}
	
	private String getPropertyKey(String propertyKeyPath)
	{
		return propertyKeyPath.substring(propertyKeyPath.indexOf(PROPERTIES_KEY_DELIMITER) + 1, propertyKeyPath.length());
	}

	private Map<String, Properties> getPropertiesFromDirectory(File directory)
	{
		if (isNull(directory) || !directory.isDirectory())
		{
			return Collections.emptyMap();
		}
		
		Map<String, Properties> propertiesMap = new HashMap<String, Properties>();
		
		for (File item : directory.listFiles())
		{
			if(item.isFile() && item.getName().endsWith(PROPERTIES_FILE_EXTENSION))
			{
				Properties propertiesFromFile = getPropertiesFromFile(item);
				
				if(nonNull(propertiesMap.put(item.getName(), propertiesFromFile)))
				{
					throwExceptionAmbigiousPropertiesFile(item);
				}
			}
			else
			{
				for (Entry<String, Properties> propertiesFileInDirectory : getPropertiesFromDirectory(item).entrySet())
				{
					if(nonNull(propertiesMap.put(propertiesFileInDirectory.getKey(), propertiesFileInDirectory.getValue())))
					{
						throwExceptionAmbigiousPropertiesFile(item);
					}
				}
			}
		}
		
		return propertiesMap;
	}

	private Properties getPropertiesFromFile(File file)
	{
		Properties properties = new Properties();
		try
		{
			properties.load(new FileInputStream(file));
		}
		catch (FileNotFoundException e)
		{
			throw new RuntimeException("Could not find file: " + file.getPath());
		}
		catch (IOException e)
		{
			throw new RuntimeException("Could not load file: " + file.getPath());
		}
		
		return properties;
	}
	
	private void throwExceptionAmbigiousPropertiesFile(File item)
	{
		throw new RuntimeException("Ambigious properties file, more than one file with the name: " + item.getName() + " exists.");
	}
}