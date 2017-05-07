package se.tidusover.kotoba.common.errorwrapper;

@FunctionalInterface
public interface ThrowingFunction<T, R>
{
    R accept(T t) throws Exception;
}