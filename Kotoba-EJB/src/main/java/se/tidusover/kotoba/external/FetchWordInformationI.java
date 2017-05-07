package se.tidusover.kotoba.external;

import java.util.List;

import javax.ejb.Local;

import se.tidusover.kotoba.word.BaseWordEntity;

@Local
public interface FetchWordInformationI
{
	//TODO: Allt som behövs för att hämta information
	public List<BaseWordEntity> fetchWordList(String wordString);
}