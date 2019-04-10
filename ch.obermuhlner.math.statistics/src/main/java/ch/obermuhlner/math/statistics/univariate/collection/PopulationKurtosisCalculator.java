package ch.obermuhlner.math.statistics.univariate.collection;

import ch.obermuhlner.math.statistics.Statistics;

import java.util.Collection;

// Fisher-Pearson coefficient
// trivial implementation
// https://www.itl.nist.gov/div898/handbook/eda/section3/eda35b.htm
public class PopulationKurtosisCalculator implements UnivariateCollectionCalculator<Collection<Double>, Double> {

    @Override
    public Double getResult(Collection<Double> values) {
        double n = values.size();
        double mean = Statistics.arithmeticMean(values);
        double s = Statistics.populationStandardDeviation(values);

        double nom = 0;
        for (double value : values) {
            double diff = value - mean;
            double diffPow4 = diff * diff * diff * diff;
            nom += diffPow4;
        }

        double sPow4 = s * s * s * s;
        double denom = n * sPow4;

        return nom / denom;
    }
}
