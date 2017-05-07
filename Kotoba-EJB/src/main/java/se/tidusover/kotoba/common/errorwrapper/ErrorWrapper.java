package se.tidusover.kotoba.common.errorwrapper;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class ErrorWrapper
{
	public static <T, R> Function<T, R> functionExceptionWrapper(ThrowingFunction<T, R> throwingFunction)
	{
		return function ->
		{
			try
			{
				return throwingFunction.accept(function);
			}
			catch (Throwable throwable)
			{
				throw new RuntimeException(throwable);
			}
		};
	}

	public static <T> Predicate<T> predicateExceptionWrapper(ThrowingPredicate<T> throwingPredicate)
	{
		return predicate ->
		{
			try
			{
				return throwingPredicate.accept(predicate);
			}
			catch (Throwable ex)
			{
				throw new RuntimeException(ex);
			}
		};
	}

	public static <T> Consumer<T> consumerExceptionWrapper(ThrowingConsumer<T> throwingConsumer) 
	{
		return consumer ->
		{
			try
			{
				throwingConsumer.accept(consumer);
			}
			catch (Throwable ex)
			{
				throw new RuntimeException(ex);
			}
		};
	}
}
