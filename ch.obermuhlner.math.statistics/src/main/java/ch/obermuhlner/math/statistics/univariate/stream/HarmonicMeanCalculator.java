package ch.obermuhlner.math.statistics.univariate.stream;

public class HarmonicMeanCalculator implements UnivariateStreamCalculator<Double> {

    private double sumReciprocals = 0;
    private int count = 0;

    public void combine(HarmonicMeanCalculator other) {
        sumReciprocals += other.sumReciprocals;
        count += other.count;
    }

    @Override
    public void add(double value) {
        sumReciprocals += 1.0 / value;
        count++;
    }

    @Override
    public Double getResult() {
        return count / sumReciprocals;
    }

    public int getCount() {
        return count;
    }
}
