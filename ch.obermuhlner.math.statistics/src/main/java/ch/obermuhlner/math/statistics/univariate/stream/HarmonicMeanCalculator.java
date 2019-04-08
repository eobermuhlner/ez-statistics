package ch.obermuhlner.math.statistics.univariate.stream;

public class ArithmeticMeanCalculator implements UnivariateStreamCalculator<Double> {

    private double sum = 0;
    private int count = 0;

    public void combine(ArithmeticMeanCalculator other) {
        sum += other.sum;
        count += other.count;
    }

    @Override
    public void add(double value) {
        sum += value;
        count++;
    }

    @Override
    public Double getResult() {
        return sum / count;
    }

    public int getCount() {
        return count;
    }
}
