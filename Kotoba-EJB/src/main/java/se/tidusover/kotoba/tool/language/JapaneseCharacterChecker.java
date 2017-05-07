package se.tidusover.kotoba.tool.language;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class JapaneseCharacterChecker
{
	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	// TODO: Skapa en för OR och en för AND
	
	public Matcher createMatcherWithUnicodeRanges(String text, JapaneseUnicodeRange... unicodeRanges)
	{
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("^[");
		for (JapaneseUnicodeRange japaneseUnicodeRange : unicodeRanges)
		{
			stringBuilder.append(japaneseUnicodeRange.getUnicodeRange());
		}
		
		stringBuilder.append("]*$");
		LOG.debug("Creating UnicodeRange: {}", stringBuilder.toString());
		Pattern pattern = Pattern.compile(stringBuilder.toString());
		return pattern.matcher(text);
	}
	
	public boolean test(String text)
	{
		Matcher createMatcherWithUnicodeRanges = createMatcherWithUnicodeRanges(text, 
				JapaneseUnicodeRange.HIRAGANA, 
				JapaneseUnicodeRange.KATAKANA,
				JapaneseUnicodeRange.KANJI);
		return createMatcherWithUnicodeRanges.matches();
	}
	
	public boolean containsJapanesePunctuation(String text)
	{
//		Pattern pattern = Pattern.
		return false;
	}
	
	public boolean containsHiragana(String text)
	{
		return false;
	}
	
	public boolean containsKatakana(String text)
	{
		return false;
	}
	
	public boolean containsKanji(String text)
	{
		return false;
	}
	
	public boolean containsJapaneseFormattingCharacters(String text)
	{
		return false;
	}
	
	public boolean containsWesternAlphabet(String text)
	{
		return false;
	}
}
