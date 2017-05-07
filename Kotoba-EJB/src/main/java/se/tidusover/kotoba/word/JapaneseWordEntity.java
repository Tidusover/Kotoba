package se.tidusover.kotoba.word;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;
import se.tidusover.kotoba.word.enums.JLPTLevel;

@Entity
public class JapaneseWordEntity extends BaseWordEntity
{
	@Getter @Setter private String meaning;
	@Getter @Setter private String romaji;
	@Getter @Setter private String hiragana;
	@Getter @Setter private String katakana;
	private Set<String> kanjiList;
	@Getter @Setter private JLPTLevel jlptLevel;

	public JapaneseWordEntity()
	{
		kanjiList = new HashSet<>();
	}
	
	public void addKanji(String kanji)
	{
		kanjiList.add(kanji);
	}
	
	public boolean removeKanji(String kanji)
	{
		return kanjiList.remove(kanji);
	}
	
}
