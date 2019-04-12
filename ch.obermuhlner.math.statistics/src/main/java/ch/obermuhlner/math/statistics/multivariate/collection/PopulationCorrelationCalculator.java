package ch.obermuhlner.math.statistics.multivariate.collection;

import ch.obermuhlner.math.statistics.univariate.collection.UnivariateCollectionCalculator;
import ch.obermuhlner.math.statistics.univariate.collection.UnivariateStreamAsCollectionCalculator;
import ch.obermuhlner.math.statistics.univariate.stream.PopulationStandardDeviationCalculator;

import java.util.Collection;

// https://en.wikipedia.org/wiki/Pearson_correlation_coefficient#For_a_population
public class PopulationCorrelationCalculator implements MultivariateCollectionsCalculator<Collection<Double>, Double> {

    private final int xValueIndex;
    private final int yValueIndex;

    private final PopulationCovarianceCalculator covarianceCalculator = new PopulationCovarianceCalculator();
    private final UnivariateCollectionCalculator<Collection<Double>, Double> standardDeviationCalculator = new UnivariateStreamAsCollectionCalculator<>(new PopulationStandardDeviationCalculator());

    public PopulationCorrelationCalculator() {
        this(0, 1);
    }

    public PopulationCorrelationCalculator(int xValueIndex, int yValueIndex) {
        this.xValueIndex = xValueIndex;
        this.yValueIndex = yValueIndex;
    }

    @Override
    public Double getResult(Collection<Double>... values) {
        Collection<Double> xValues = values[xValueIndex];
        Collection<Double> yValues = values[yValueIndex];

        double covariance = covarianceCalculator.getResult(xValues, yValues);
        double xStdDev = standardDeviationCalculator.getResult(xValues);
        double yStdDev = standardDeviationCalculator.getResult(xValues);

        return covariance / (xStdDev * yStdDev);
    }
}
