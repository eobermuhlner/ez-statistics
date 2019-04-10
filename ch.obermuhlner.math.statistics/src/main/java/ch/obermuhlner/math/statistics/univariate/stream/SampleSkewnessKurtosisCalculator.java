package ch.obermuhlner.math.statistics.univariate.stream;

import ch.obermuhlner.math.statistics.type.SkewnessKurtosis;

// https://www.macroption.com/kurtosis-formula/
public class SampleSkewnessKurtosisCalculator implements UnivariateStreamCalculator<SkewnessKurtosis> {

    private final PopulationSkewnessKurtosisCalculator populationSkewnessKurtosisCalculator;

    private final boolean calculateSkewness;
    private final boolean calculateKurtosis;

    public SampleSkewnessKurtosisCalculator() {
        this(true, true);
    }

    public SampleSkewnessKurtosisCalculator(boolean calculateSkewness, boolean calculateKurtosis) {
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
            double n = populationSkewnessKurtosisCalculator.getCount();

            double correction = Math.sqrt(n * (n-1)) / (n-2);
            double skewness = populationSkewnessKurtosisCalculator.getSkewness();

            return correction * skewness;
        } else {
            return Double.NaN;
        }
    }

    public double getKurtosis() {
        if (calculateKurtosis) {
            // https://brownmath.com/stat/shape.htm
            int n = populationSkewnessKurtosisCalculator.getCount();

            double correction = (n+1) * (n-1) / ((n-2) * (n-3));
            double kurtosis = populationSkewnessKurtosisCalculator.getKurtosis();

            return correction * kurtosis;
        } else {
            return Double.NaN;
        }
    }

    @Override
    public SkewnessKurtosis getResult() {
        return new SkewnessKurtosis(getSkewness(), getKurtosis());
    }
}
