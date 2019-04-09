package ch.obermuhlner.math.statistics.univariate.stream;

public class SampleVarianceCalculator implements UnivariateStreamCalculator<Double> {

    private final PopulationVarianceCalculator populationVarianceCalculator = new PopulationVarianceCalculator();

    @Override
    public void add(double value) {
        populationVarianceCalculator.add(value);
    }

    public void combine(SampleVarianceCalculator other) {
        populationVarianceCalculator.combine(other.populationVarianceCalculator);
    }

    @Override
    public Double getResult() {
        double populationVariance = populationVarianceCalculator.getResult();
        int n = populationVarianceCalculator.getCount();

        return populationVariance * n / (n - 1);
    }
}
