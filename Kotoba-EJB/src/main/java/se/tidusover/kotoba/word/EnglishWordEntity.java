package se.tidusover.kotoba.word;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
public class EnglishWordEntity extends BaseWordEntity
{
	@Getter @Setter String word;
}
