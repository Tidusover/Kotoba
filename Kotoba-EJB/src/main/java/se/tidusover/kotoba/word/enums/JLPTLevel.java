package se.tidusover.kotoba.word.enums;

public enum JLPTLevel
{
	N5(5, "N5"),
	N4(4, "N4"),
	N3(3, "N3"),
	N2(2, "N2"),
	N1(1, "N1"),
	NOT_SELECTED(0, "Not selected"),
	NOT_AVAILABLE(-1, "Not available");
	
	final int levelInt;
	final String levelString;
	
	private JLPTLevel(int levelInt, String levelString)
	{
		this.levelInt = levelInt;
		this.levelString = levelString;
	}
	
	public int getJPLTLevelAsInt()
	{
		return levelInt;
	}
	
	public String getJLPTLevelAsString()
	{
		return levelString;
	}
}
