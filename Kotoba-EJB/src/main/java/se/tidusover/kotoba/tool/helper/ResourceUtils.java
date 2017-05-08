package se.tidusover.kotoba.tool.helper;

import static java.util.Objects.isNull;

import java.io.File;
import java.net.URISyntaxException;

public class ResourceUtils
{
	public File loadDirectoryFromClasspath(String directoryPath)
	{
		File propertiesDirectory;
		try
		{
			propertiesDirectory = new File(Thread.currentThread().getContextClassLoader().getResource(directoryPath).toURI());
		}
		catch (URISyntaxException e)
		{
			throw new RuntimeException("Invalid directory path: " + directoryPath + ".");
		}
		if (isNull(propertiesDirectory))
		{
			throw new RuntimeException("Could not find directory: " + directoryPath + ".");
		}
		
		return propertiesDirectory;
	}
}