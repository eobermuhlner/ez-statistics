package ch.obermuhlner.math.statistics.univariate.stream;

public class SampleStandardDeviationCalculator implements UnivariateStreamCalculator<Double> {

    private final SampleVarianceCalculator sampleVarianceCalculator = new SampleVarianceCalculator();

    @Override
    public void add(double value) {
        sampleVarianceCalculator.add(value);
    }

    public void combine(SampleStandardDeviationCalculator other) {
        sampleVarianceCalculator.combine(other.sampleVarianceCalculator);
    }

    @Override
    public Double getResult() {
        double sampleVariance = sampleVarianceCalculator.getResult();
        return Math.sqrt(sampleVariance);
    }
}
