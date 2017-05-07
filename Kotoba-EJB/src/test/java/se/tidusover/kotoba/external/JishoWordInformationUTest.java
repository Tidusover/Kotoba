package se.tidusover.kotoba.external;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(CdiRunner.class)
@AdditionalClasses({
	ConfigurationValueProducer.class,
	PropertiesResolver.class
})
public class JishoWordInformationUTest
{
	@Produces
	@Mock
	ObjectMapper objectMapperMock;
	
	@Inject
	JishoWordInformation jishoWordInformation;

	@Test
	public void test() throws Exception
	{
		final String keyword = "house";
		System.out.println(jishoWordInformation.getAllMatchingWords(keyword));
	}
}