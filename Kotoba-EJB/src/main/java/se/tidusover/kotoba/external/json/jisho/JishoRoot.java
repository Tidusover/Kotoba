package se.tidusover.kotoba.external.json.jisho;

import java.util.ArrayList;
import java.util.List;


@lombok.Data
public class JishoRoot
{
	private Meta meta;
	private List<Data> data = new ArrayList<Data>();
}