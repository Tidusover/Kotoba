package se.tidusover.kotoba.tool.language;

import java.util.Objects;

//3000-303F : punctuation
	// 3040-309F : hiragana
	// 30A0-30FF : katakana
	// FF00-FFEF : Full-width roman + half-width katakana
	// 4E00-9FAF : Common and uncommon kanji
	// 
	// Non-Japanese punctuation/formatting characters commonly used in Japanese text
	// 2605-2606 : Stars
	// 2190-2195 : Arrows
	// u203B     : Weird asterisk thing
	public enum JapaneseUnicodeRange
	{
		JAPANESE_PUNCTUATION("3000", "303F"),
		HIRAGANA("3040", "309F"),
		KATAKANA("30A0","30FF"),
		ROMAN_CHARACTER("ff00", "ff66"),
		HALF_WIDTH_KATAKANA("ff67", "ffef"),
		KANJI("4E00", "9FAF"),
		COMMON_STAR_CHARACTER("2605", "2606"),
		COMMON_ARROW_CHARACTER("2190", "2195"),
		REFERENCE_MARK_CHARACTER("203B", "203B");
		
		//TODO, kolla med denna sida om det funkgerar bra med det här?
		//http://www.rikai.com/library/kanjitables/kanji_codes.unicode.shtml
		
		private final String unicodeRangeStart;
		private final String unicodeRangeEnd;
		
		private static final String UNICODE_PREFIX = "\\u";
		
		private JapaneseUnicodeRange(String unicodeRangeStart, String unicodeRangeEnd)
		{
			this.unicodeRangeStart = unicodeRangeStart;
			this.unicodeRangeEnd = unicodeRangeEnd;
		}
		
		public String getUnicodeRange()
		{
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(UNICODE_PREFIX);
			stringBuilder.append(unicodeRangeStart);
			if(!Objects.equals(unicodeRangeStart, unicodeRangeEnd))
			{
				stringBuilder.append("-");
				stringBuilder.append(UNICODE_PREFIX);
				stringBuilder.append(unicodeRangeEnd);
			}
			
			return stringBuilder.toString();
		}
	}
	