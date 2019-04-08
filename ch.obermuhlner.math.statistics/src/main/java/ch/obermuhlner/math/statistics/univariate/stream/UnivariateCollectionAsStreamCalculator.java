package ch.obermuhlner.math.statistics.univariate.stream;

import ch.obermuhlner.math.statistics.univariate.collection.UnivariateCollectionCalculator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class UnivariateCollectionAsStreamCalculator<C extends Collection<Double>, R> implements UnivariateStreamCalculator<R> {

    private final UnivariateCollectionCalculator<C, R> calculator;

    private final List<Double> values = new ArrayList<>();

    private Double lastValue = null;
    private boolean valuesSorted = true;

    public UnivariateCollectionAsStreamCalculator(UnivariateCollectionCalculator<C, R> calculator) {
        this.calculator = calculator;
    }

    @Override
    public boolean needsSorted() {
        return calculator.needsSorted();
    }

    @Override
    public void add(double value) {
        values.add(value);

        if (valuesSorted) {
            if (lastValue != null && value < lastValue) {
                valuesSorted = false;
            }
            lastValue = value;
        }
    }

    public void combine(UnivariateCollectionAsStreamCalculator<C, R> other) {
        if (valuesSorted && other.valuesSorted) {
            // TODO use merge sort if both are sorted
            values.addAll(other.values);
            valuesSorted = false;
        } else {
            values.addAll(other.values);
            valuesSorted = false;
        }
    }

    @Override
    public R getResult() {
        if (calculator.needsSorted() && !valuesSorted) {
            Collections.sort(values);
        }
        return calculator.getResult((C) values);
    }
}
