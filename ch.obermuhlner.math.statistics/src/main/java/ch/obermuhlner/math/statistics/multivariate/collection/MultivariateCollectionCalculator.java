package ch.obermuhlner.math.statistics.multivariate.collection;

import java.util.Collection;

public interface MultivariateCollectionCalculator<C extends Collection<double[]>, R> {

    R getResult(C tuples);
}
