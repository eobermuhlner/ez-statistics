package ch.obermuhlner.math.statistics.multivariate.collection;

import java.util.Collection;

public class SampleCovarianceCalculator extends AbstractCovarianceCalculator {

    public SampleCovarianceCalculator() {
        super();
    }

    public SampleCovarianceCalculator(int xValueIndex, int yValueIndex) {
        super(xValueIndex, yValueIndex);
    }

    @Override
    public Double getResult(Collection<Double>... values) {
        return calculateCovariance(-1, values);
    }
}
