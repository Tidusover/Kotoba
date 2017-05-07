package se.tidusover.kotoba.tool.language;

import static org.junit.Assert.fail;

import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;

import se.tidusover.kotoba.common.LoggerProducer;

@RunWith(CdiRunner.class)
@AdditionalClasses({Logger.class, LoggerProducer.class})
public class JapaneseCharacterCheckerTest
{
	@Before
	public void setUp() throws Exception
	{
	}

	@After
	public void tearDown() throws Exception
	{
	}

	@Test
	public void testContainsHiragana()
	{
		fail("Not yet implemented");
	}
	
	@Test
	public void testContainsKatakana() throws Exception
	{
		
	}
	
	@Ignore
	@Test
	public void testContainsKanji() throws Exception
	{
		
	}
	
	@Test
	public void testTest() throws Exception
	{
		String hira = "あぱ";
		String kata = "アパ";
		String kanji = "家";
		String hirakata = hira + kata;
		String hirakatakanji = hirakata + kanji;
		String textOchannat = "あぱモA";
		JapaneseCharacterChecker japaneseCharacterChecker = new JapaneseCharacterChecker();
		System.out.println(japaneseCharacterChecker.test(hirakatakanji));
	}
	
	@Ignore
	@Test
	public void testContainsJapaneseFormattingCharacters() throws Exception
	{
		
	}
}
