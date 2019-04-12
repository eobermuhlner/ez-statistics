package ch.obermuhlner.math.statistics.multivariate.collection;

import java.util.Collection;

public class CorrelationMatrixCalculator extends DiagonallySymmetricMatrixCalculator {
    public CorrelationMatrixCalculator(MultivariateCollectionsCalculator<Collection<Double>, Double> calculator) {
        super(calculator, (collections) -> 1.0);
    }
}
