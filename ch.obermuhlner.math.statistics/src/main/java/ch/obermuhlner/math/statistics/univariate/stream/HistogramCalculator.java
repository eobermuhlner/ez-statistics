package ch.obermuhlner.math.statistics.univariate.stream;

import ch.obermuhlner.math.statistics.type.Histogram;

import java.util.Arrays;

public class HistogramCalculator implements UnivariateStreamCalculator<Histogram> {

    private final double start;
    private final double step;
    private final int count[];

    public HistogramCalculator(double start, double end, double step) {
        this.start = start;
        this.step = step;
        int size = (int) ((end - start) / step);

        this.count = new int[size];
    }

    @Override
    public void add(double value) {
        int index = (int) ((value - start) / step);
        if(index >= 0 && index < count.length) {
            count[index]++;
        }
    }

    public void combine(HistogramCalculator other) {
        if (start != other.start || step != other.step || count.length != other.count.length) {
            throw new IllegalArgumentException("Can only combine equivalent HistogramCalculator");
        }

        for (int i = 0; i < count.length; i++) {
            count[i] += other.count[i];
        }
    }

    @Override
    public Histogram getResult() {
        return new Histogram(start, step, Arrays.copyOf(count, count.length));
    }
}
