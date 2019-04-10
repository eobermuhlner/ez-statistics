package ch.obermuhlner.math.statistics.univariate.collection;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Collection;

public class SampleKurtosisCalculator implements UnivariateCollectionCalculator<Collection<Double>, Double> {

    private final PopulationKurtosisCalculator populationKurtosisCalculator = new PopulationKurtosisCalculator();

    @Override
    public Double getResult(Collection<Double> values) {
        int n = values.size();

        double correction = (n+1) * (n-1) / ((n-2) * (n-3));
        double kurtosis = populationKurtosisCalculator.getResult(values);

        return correction * kurtosis;
    }
}
