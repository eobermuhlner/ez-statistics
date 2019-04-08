package ch.obermuhlner.math.statistics.univariate.stream;

public class SumCalculator implements UnivariateStreamCalculator<Double> {

    private double sum = 0;

    public void combine(SumCalculator other) {
        sum += other.sum;
    }

    @Override
    public void add(double value) {
        sum += value;
    }

    @Override
    public Double getResult() {
        return sum;
    }
}
