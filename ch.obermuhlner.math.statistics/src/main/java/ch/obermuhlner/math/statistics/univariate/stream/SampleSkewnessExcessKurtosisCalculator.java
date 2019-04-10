package ch.obermuhlner.math.statistics.univariate.stream;

import ch.obermuhlner.math.statistics.type.SkewnessKurtosis;

// https://www.macroption.com/kurtosis-formula/
public class SampleSkewnessExcessKurtosisCalculator implements UnivariateStreamCalculator<SkewnessKurtosis> {

    private final SampleSkewnessKurtosisCalculator sampleSkewnessKurtosisCalculator;

    private final boolean calculateSkewness;
    private final boolean calculateKurtosis;

    public SampleSkewnessExcessKurtosisCalculator() {
        this(true, true);
    }

    public SampleSkewnessExcessKurtosisCalculator(boolean calculateSkewness, boolean calculateKurtosis) {
        this.sampleSkewnessKurtosisCalculator = new SampleSkewnessKurtosisCalculator(calculateSkewness, calculateKurtosis);
        this.calculateSkewness = calculateSkewness;
        this.calculateKurtosis = calculateKurtosis;
    }

    @Override
    public void add(double value) {
        sampleSkewnessKurtosisCalculator.add(value);
    }

    public double getSkewness() {
        if (calculateSkewness) {
            return sampleSkewnessKurtosisCalculator.getSkewness();
        } else {
            return Double.NaN;
        }
    }

    public double getKurtosis() {
        if (calculateKurtosis) {
            int n = sampleSkewnessKurtosisCalculator.getCount();

            double kurtosis = sampleSkewnessKurtosisCalculator.getKurtosis();
            double deltaCorrection = 3 * (n-1) * (n-1) / ((n-2) * (n-3));

            return kurtosis - deltaCorrection;
        } else {
            return Double.NaN;
        }
    }

    @Override
    public SkewnessKurtosis getResult() {
        return new SkewnessKurtosis(getSkewness(), getKurtosis());
    }
}
