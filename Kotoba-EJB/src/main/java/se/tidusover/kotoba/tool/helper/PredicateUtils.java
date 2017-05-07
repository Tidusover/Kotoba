package se.tidusover.kotoba.tool.helper;

import java.util.function.Predicate;

public class PredicateUtils
{
	public static <R> Predicate<R> not(Predicate<R> predicate)
	{
		return predicate.negate();
	}
}