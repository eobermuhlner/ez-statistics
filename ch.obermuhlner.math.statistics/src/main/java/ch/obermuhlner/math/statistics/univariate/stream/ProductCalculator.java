package ch.obermuhlner.math.statistics.univariate.stream;

public class ProductCalculator implements UnivariateStreamCalculator<Double> {

    private double product = 1;

    public void combine(ProductCalculator other) {
        product *= other.product;
    }

    @Override
    public void add(double value) {
        product *= value;
    }

    @Override
    public Double getResult() {
        return product;
    }
}
