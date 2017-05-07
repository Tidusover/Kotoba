package se.tidusover.kotoba.external.json.jisho;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Sense
{
	@JsonProperty("english_definitions")
	private List<String> englishDefinitions = new ArrayList<String>();
	@JsonProperty("parts_of_speech")
	private List<String> partsOfSpeech = new ArrayList<String>();
	private List<Link> links = new ArrayList<Link>();
	private List<String> tags = new ArrayList<String>();
	private List<Object> restrictions = new ArrayList<Object>();
	@JsonProperty("see_also")
	private List<String> seeAlso = new ArrayList<String>();
	private List<Object> antonyms = new ArrayList<Object>();
	private List<Object> source = new ArrayList<Object>();
	private List<String> info = new ArrayList<String>();
	private List<Object> sentences = new ArrayList<Object>();
}