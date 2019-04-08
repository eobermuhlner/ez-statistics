package ch.obermuhlner.math.statistics.univariate.stream;

public class PopulationStandardDeviationCalculator implements UnivariateStreamCalculator<Double> {

    private final PopulationVarianceCalculator populationVarianceCalculator = new PopulationVarianceCalculator();

    @Override
    public void add(double value) {
        populationVarianceCalculator.add(value);
    }

    @Override
    public Double getResult() {
        double populationVariance = populationVarianceCalculator.getResult();
        return Math.sqrt(populationVariance);
    }
}
