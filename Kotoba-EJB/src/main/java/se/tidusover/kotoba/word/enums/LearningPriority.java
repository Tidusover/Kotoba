package se.tidusover.kotoba.word.enums;

import lombok.Getter;

public enum LearningPriority
{
	HIGH("High"),
	MEDIUM("Medium"),
	LOW("Low"),
	NOT_SELECTED("Not selected");
	
	@Getter final String priority;
	
	private LearningPriority (String priorityString)
	{
		this.priority = priorityString;
	}
}
