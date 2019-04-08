package ch.obermuhlner.math.statistics.univariate.stream;

public class MinCalculator implements UnivariateStreamCalculator<Double> {

    private boolean empty = true;
    private double min;

    public void combine(MinCalculator other) {
        if (empty) {
            empty = other.empty;
            min = other.min;
        } else {
            if (!other.empty && other.min < min) {
                min = other.min;
            }
        }
    }

    @Override
    public void add(double value) {
        if (empty) {
            min = value;
            empty = false;
        } else {
             if (value < min) {
                 min = value;
             }
        }
    }

    @Override
    public Double getResult() {
        return min;
    }
}
