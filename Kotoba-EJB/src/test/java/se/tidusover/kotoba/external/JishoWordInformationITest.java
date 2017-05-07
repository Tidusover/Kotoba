package se.tidusover.kotoba.external;

import java.util.Collection;
import java.util.Optional;

import javax.inject.Inject;

import org.apache.commons.lang3.ObjectUtils;
import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.event.Level;

import se.tidusover.kotoba.external.json.jisho.Data;
import se.tidusover.kotoba.external.json.jisho.JishoRoot;

@RunWith(CdiRunner.class)
@AdditionalClasses({
	ObjectMapperFactory.class,
	ConfigurationValueProducer.class,
	PropertiesResolver.class
})
public class JishoWordInformationITest
{
	@Inject
	JishoWordInformation jishoWordInformation;
	
	@BeforeClass
	public static void setUpClass() throws Exception
	{
		System.setProperty("org.slf4j.simpleLogger.defaultLogLevel", Level.DEBUG.toString().toLowerCase());
		System.setProperty("org.slf4j.simpleLogger.showDateTime", Boolean.TRUE.toString());
		System.setProperty("org.slf4j.simpleLogger.logFile", "System.out");
		System.setProperty("org.slf4j.simpleLogger.dateTimeFormat", "HH:mm:ss.SSS");
	}

	@Test
	public void test() throws Exception
	{	
		final String keyword = "life";
		Optional<JishoRoot> allMatchingWords = jishoWordInformation.getAllMatchingWords(keyword);
		allMatchingWords.get().getData().stream()
			.map(Data::getJapanese)
			.flatMap(Collection::stream)
			.map(word -> ObjectUtils.firstNonNull(word.getWord(), word.getReading()))
			.forEachOrdered(System.out::println);
	}
}