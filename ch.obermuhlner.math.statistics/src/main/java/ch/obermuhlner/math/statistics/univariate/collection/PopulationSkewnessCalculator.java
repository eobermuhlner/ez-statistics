package ch.obermuhlner.math.statistics.univariate.collection;

import ch.obermuhlner.math.statistics.Statistics;

import java.util.Collection;

// Fisher-Pearson coefficient
// trivial implementation
// https://www.itl.nist.gov/div898/handbook/eda/section3/eda35b.htm
public class PopulationSkewnessCalculator implements UnivariateCollectionCalculator<Collection<Double>, Double> {

    @Override
    public Double getResult(Collection<Double> values) {
        double n = values.size();
        double mean = Statistics.arithmeticMean(values);
        double s = Statistics.populationStandardDeviation(values);

        double nom = 0;
        for (Double value : values) {
            double diff = value - mean;
            double diffPow3 = diff * diff * diff;
            nom += diffPow3;
        }

        double sPow3 = s * s * s;
        double denom = n * sPow3;

        return nom / denom;
    }
}
