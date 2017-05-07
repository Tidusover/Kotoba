package se.tidusover.kotoba.common.errorwrapper;

@FunctionalInterface
public interface ThrowingPredicate<T>
{
	boolean accept(T t) throws Exception;
}