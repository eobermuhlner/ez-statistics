package ch.obermuhlner.math.statistics.univariate.collection;

import java.util.List;

public class MedianCalculator implements UnivariateCollectionCalculator<List<Double>, Double> {
    @Override
    public boolean needsSorted() {
        return true;
    }

    @Override
    public Double getResult(List<Double> sortedValues) {
        int n = sortedValues.size();
        if (n % 2 == 0) {
            return (sortedValues.get(n / 2) + sortedValues.get(n / 2 - 1)) / 2;
        } else {
            return sortedValues.get(n / 2);
        }
    }
}
