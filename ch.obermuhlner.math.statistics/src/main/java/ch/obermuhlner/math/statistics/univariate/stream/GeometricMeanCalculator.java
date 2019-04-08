package ch.obermuhlner.math.statistics.univariate.stream;

public class GeometricMeanCalculator implements UnivariateStreamCalculator<Double> {

    private double product = 1;
    private int count = 0;

    public void combine(GeometricMeanCalculator other) {
        product *= other.product;
        count += other.count;
    }

    @Override
    public void add(double value) {
        product *= value;
        count++;
    }

    @Override
    public Double getResult() {
        return Math.pow(product, 1.0/count);
    }

    public int getCount() {
        return count;
    }
}
