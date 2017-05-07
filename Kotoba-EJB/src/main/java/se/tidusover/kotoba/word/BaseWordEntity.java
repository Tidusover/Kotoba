package se.tidusover.kotoba.word;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import lombok.Getter;
import lombok.Setter;
import se.tidusover.kotoba.word.enums.LearningPriority;
import se.tidusover.kotoba.word.WordType;

@Entity
public abstract class BaseWordEntity
{
	@Id
	@Getter private String id;
	@Getter @Setter private String description;
	@Getter private ZonedDateTime dateAdded;
	@Getter private ZonedDateTime dateModified;
	@Getter @Setter private LearningPriority learningPriority;
	private List<WordType> wordTypeList;
	
	public BaseWordEntity()
	{
		wordTypeList = new ArrayList<>();
	}
	
	@PrePersist
	private void updateDateAdded()
	{
		dateAdded = ZonedDateTime.now();
	}
	
	@PrePersist
	@PreUpdate
	private void updateDateModified()
	{
		dateModified = ZonedDateTime.now();
	}
	
}
