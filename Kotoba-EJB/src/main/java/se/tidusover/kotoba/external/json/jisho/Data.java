package se.tidusover.kotoba.external.json.jisho;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
public class Data
{
	@JsonProperty("is_common")
	private Boolean isCommon;
	private List<String> tags = new ArrayList<String>();
	private List<Japanese> japanese = new ArrayList<Japanese>();
	private List<Sense> senses = new ArrayList<Sense>();
	private Attribution attribution;
}