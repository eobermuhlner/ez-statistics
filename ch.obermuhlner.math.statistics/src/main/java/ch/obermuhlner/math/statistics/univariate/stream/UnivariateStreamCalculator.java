package ch.obermuhlner.math.statistics.univariate.stream;

public interface UnivariateStreamCalculator<R> {

    default boolean needsSorted() {
        return false;
    }

    void add(double value);

    R getResult();
}
