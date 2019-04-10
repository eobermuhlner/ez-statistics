package ch.obermuhlner.math.statistics.univariate.collection;

import java.util.Collection;

// Fisher-Pearson coefficient
// trivial implementation
// https://www.itl.nist.gov/div898/handbook/eda/section3/eda35b.htm
public class SampleExcessKurtosisCalculator implements UnivariateCollectionCalculator<Collection<Double>, Double> {

    private final SampleKurtosisCalculator sampleKurtosisCalculator = new SampleKurtosisCalculator();

    @Override
    public Double getResult(Collection<Double> values) {
        int n = values.size();
        double kurtosis = sampleKurtosisCalculator.getResult(values);

        double deltaCorrection = 3 * (n-1) * (n-1) / ((n-2) * (n-3));

        return kurtosis - deltaCorrection;
    }
}
