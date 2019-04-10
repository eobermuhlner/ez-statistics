package ch.obermuhlner.math.statistics.multivariate.collection;

import java.util.Collection;

public interface MultivariateCollectionsCalculator<C extends Collection<Double>, R> {

    R getResult(C... values);
}
