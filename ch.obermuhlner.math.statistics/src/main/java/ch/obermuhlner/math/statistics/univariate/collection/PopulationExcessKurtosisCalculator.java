package ch.obermuhlner.math.statistics.univariate.collection;

import java.util.Collection;

// Fisher-Pearson coefficient
// trivial implementation
// https://www.itl.nist.gov/div898/handbook/eda/section3/eda35b.htm
public class PopulationExcessKurtosisCalculator implements UnivariateCollectionCalculator<Collection<Double>, Double> {

    private final PopulationKurtosisCalculator populationKurtosisCalculator = new PopulationKurtosisCalculator();

    @Override
    public Double getResult(Collection<Double> values) {
        double kurtosis = populationKurtosisCalculator.getResult(values);

        return kurtosis - 3;
    }
}
