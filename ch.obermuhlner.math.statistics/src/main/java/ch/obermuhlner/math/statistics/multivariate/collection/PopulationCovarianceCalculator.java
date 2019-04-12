package ch.obermuhlner.math.statistics.multivariate.collection;

import java.util.Collection;

public class PopulationCovarianceCalculator extends AbstractCovarianceCalculator {

    public PopulationCovarianceCalculator() {
        super();
    }

    public PopulationCovarianceCalculator(int xValueIndex, int yValueIndex) {
        super(xValueIndex, yValueIndex);
    }

    @Override
    public Double getResult(Collection<Double>... values) {
        return calculateCovariance(0, values);
    }
}
