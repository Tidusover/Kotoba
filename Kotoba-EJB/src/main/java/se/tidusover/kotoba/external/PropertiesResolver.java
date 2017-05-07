package se.tidusover.kotoba.external;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PropertiesResolver
{
	private static final String PROPERTIES_FILE_EXTENSION = ".properties";
	private static final String PROPERTIES_DIRECTORY = "properties";
	private static final String PROPERTIES_KEY_DELIMITER = ".";
	
	private static List<File> availablePropertiesFileSet;
	
	@PostConstruct
	public void init()
	{
		File propertiesFolder;
		try
		{
			propertiesFolder = new File(Thread.currentThread().getContextClassLoader().getResource(PROPERTIES_DIRECTORY).toURI());
		}
		catch (URISyntaxException e)
		{
			throw new RuntimeException("Could not load resources from classpath.");
		}
		if(isNull(propertiesFolder))
		{
			throw new RuntimeException("Could not find any properties files.");
		}
		
		availablePropertiesFileSet = getPropertiesFilesFromDirectory(propertiesFolder);
		
		if (availablePropertiesFileSet.isEmpty())
		{
			throw new RuntimeException("No properties files found.");
		}
	}
	
	public String getProperty(String property)
	{
		String propertyFilename = getPropertyFilename(property);
		String propertyKey = getPropertyKey(property);
		
		Properties propertiesFile = availablePropertiesFileSet.stream()
				.filter(file -> propertyFilename.equals(file.getName()))
				.map(this::getPropertiesFromFile)
				.findAny()
				.orElseThrow(() -> new RuntimeException("Properties file: " + propertyFilename + " not found."));
		
		String propertyValue = propertiesFile.getProperty(propertyKey);
		if (Objects.isNull(propertyValue))
		{
			throw new RuntimeException("Property: " + propertyKey + " not found.");
		}
		
		return propertyValue;
	}
	
	private String getPropertyFilename(String property)
	{
		StringBuilder sb = new StringBuilder()
				.append(property.substring(0, property.indexOf(PROPERTIES_KEY_DELIMITER)))
				.append(PROPERTIES_FILE_EXTENSION);
		
		return sb.toString();
	}
	
	private String getPropertyKey(String property)
	{
		return property.substring(property.indexOf(PROPERTIES_KEY_DELIMITER) + 1, property.length());
	}

	private List<File> getPropertiesFilesFromDirectory(File directory)
	{
		if (isNull(directory) || !directory.isDirectory())
		{
			return Collections.emptyList();
		}
		
		HashMap<String, File> propertiesFileMap = new HashMap<String, File>();
		
		for (File file : directory.listFiles())
		{
			System.out.println(file.getName());
			if(file.isFile() && file.getName().endsWith(PROPERTIES_FILE_EXTENSION))
			{
				if(nonNull(propertiesFileMap.put(file.getName(), file)))
				{
					throw new RuntimeException("Ambigious properties file, more than one file with the name: " + file.getName() + " exists.");
				}
			}
			else if(file.isDirectory())
			{
				for (File fileInDirectory : getPropertiesFilesFromDirectory(file))
				{
					if(nonNull(propertiesFileMap.put(file.getName(), fileInDirectory)))
					{
						throw new RuntimeException("Ambigious properties file, more than one file with the name: " + file.getName() + " exists.");
					}
				}
			}
		}
		
		return new ArrayList<>(propertiesFileMap.values());
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
}