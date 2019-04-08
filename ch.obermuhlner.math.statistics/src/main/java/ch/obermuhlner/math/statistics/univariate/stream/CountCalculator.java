package ch.obermuhlner.math.statistics.univariate.stream;

public class CountCalculator implements UnivariateStreamCalculator<Double> {

    private int count = 0;

    public void combine(CountCalculator other) {
        count += other.count;
    }

    @Override
    public void add(double value) {
        count++;
    }

    @Override
    public Double getResult() {
        return Double.valueOf(count);
    }
}
