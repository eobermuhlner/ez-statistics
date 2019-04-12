package ch.obermuhlner.math.statistics.multivariate.collection;

import ch.obermuhlner.math.statistics.Statistics;

import java.util.Collection;
import java.util.Iterator;

public abstract class AbstractCovarianceCalculator implements MultivariateCollectionsCalculator<Collection<Double>, Double> {

    private final int xValueIndex;
    private final int yValueIndex;

    public AbstractCovarianceCalculator() {
        this(0, 1);
    }

    public AbstractCovarianceCalculator(int xValueIndex, int yValueIndex) {
        this.xValueIndex = xValueIndex;
        this.yValueIndex = yValueIndex;
    }

    public double calculateCovariance(int offsetN, Collection<Double>... values) {
        double xMean = Statistics.arithmeticMean(values[xValueIndex]);
        double yMean = Statistics.arithmeticMean(values[yValueIndex]);

        Iterator<Double> xIterator = values[xValueIndex].iterator();
        Iterator<Double> yIterator = values[yValueIndex].iterator();

        int n = 0;
        double sumTerms = 0;
        while (xIterator.hasNext() && yIterator.hasNext()) {
            double x = xIterator.next();
            double y = yIterator.next();

            double term = (x - xMean) * (y - yMean);
            sumTerms += term;
            n++;
        }

        return sumTerms / (n + offsetN);
    }
}
