package ch.obermuhlner.math.statistics.univariate.stream;

import ch.obermuhlner.math.statistics.type.SkewnessKurtosis;

// https://en.wikipedia.org/wiki/Algorithms_for_calculating_variance
public class PopulationSkewnessExcessKurtosisCalculator implements UnivariateStreamCalculator<SkewnessKurtosis> {

    private final PopulationSkewnessKurtosisCalculator populationSkewnessKurtosisCalculator;

    private final boolean calculateSkewness;
    private final boolean calculateKurtosis;

    public PopulationSkewnessExcessKurtosisCalculator() {
        this(true, true);
    }

    public PopulationSkewnessExcessKurtosisCalculator(boolean calculateSkewness, boolean calculateKurtosis) {
        this.populationSkewnessKurtosisCalculator = new PopulationSkewnessKurtosisCalculator(calculateSkewness, calculateKurtosis);
        this.calculateSkewness = calculateSkewness;
        this.calculateKurtosis = calculateKurtosis;
    }

    @Override
    public void add(double value) {
        populationSkewnessKurtosisCalculator.add(value);
    }

    public int getCount() {
        return populationSkewnessKurtosisCalculator.getCount();
    }

    public double getSkewness() {
        if (calculateSkewness) {
            return populationSkewnessKurtosisCalculator.getSkewness();
        } else {
            return Double.NaN;
        }
    }

    public double getKurtosis() {
        if (calculateKurtosis) {
            return populationSkewnessKurtosisCalculator.getKurtosis() - 3;
        } else {
            return Double.NaN;
        }
    }

    @Override
    public SkewnessKurtosis getResult() {
        return new SkewnessKurtosis(getSkewness(), getKurtosis());
    }

}