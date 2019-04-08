package ch.obermuhlner.math.statistics.univariate.stream;

public class MaxCalculator implements UnivariateStreamCalculator<Double> {

    private boolean empty = true;
    private double max;

    public void combine(MaxCalculator other) {
        if (empty) {
            empty = other.empty;
            max = other.max;
        } else {
            if (!other.empty && other.max > max) {
                max = other.max;
            }
        }
    }

    @Override
    public void add(double value) {
        if (empty) {
            max = value;
            empty = false;
        } else {
             if (value > max) {
                 max = value;
             }
        }
    }

    @Override
    public Double getResult() {
        return max;
    }
}
