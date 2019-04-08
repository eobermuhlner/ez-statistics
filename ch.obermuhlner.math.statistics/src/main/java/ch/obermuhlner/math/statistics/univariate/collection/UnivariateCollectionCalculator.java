package ch.obermuhlner.math.statistics.univariate.collection;

import java.util.Collection;

public interface UnivariateCollectionCalculator<C extends Collection<Double>, R> {

    default boolean needsSorted() {
        return false;
    }

    R getResult(C values);
}
