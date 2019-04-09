package ch.obermuhlner.math.statistics.univariate.stream;

public class PopulationStandardDeviationCalculator implements UnivariateStreamCalculator<Double> {

    private final PopulationVarianceCalculator populationVarianceCalculator = new PopulationVarianceCalculator();

    @Override
    public void add(double value) {
        populationVarianceCalculator.add(value);
    }

    public void combine(PopulationStandardDeviationCalculator other) {
        populationVarianceCalculator.combine(other.populationVarianceCalculator);
    }

    @Override
    public Double getResult() {
        double populationVariance = populationVarianceCalculator.getResult();
        return Math.sqrt(populationVariance);
    }
}
