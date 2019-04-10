package ch.obermuhlner.math.statistics.univariate.collection;

import java.util.Collection;

public class SampleSkewnessCalculator implements UnivariateCollectionCalculator<Collection<Double>, Double> {

    private final PopulationSkewnessCalculator populationSkewnessCalculator = new PopulationSkewnessCalculator();

    @Override
    public Double getResult(Collection<Double> values) {
        int n = values.size();

        double correction = Math.sqrt(n * (n-1)) / (n-2);
        double skewness = populationSkewnessCalculator.getResult(values);

        return correction * skewness;
    }}
