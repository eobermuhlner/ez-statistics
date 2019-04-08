package ch.obermuhlner.math.statistics;

import ch.obermuhlner.math.statistics.univariate.collection.MedianCalculator;
import ch.obermuhlner.math.statistics.univariate.collection.UnivariateStreamAsCollectionCalculator;
import ch.obermuhlner.math.statistics.univariate.stream.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Statistics {
    public static double min(Collection<Double> values) {
        return new UnivariateStreamAsCollectionCalculator<>(new MinCalculator()).getResult(values);
    }

    public static double max(Collection<Double> values) {
        return new UnivariateStreamAsCollectionCalculator<>(new MaxCalculator()).getResult(values);
    }

    public static double sum(Collection<Double> values) {
        return new UnivariateStreamAsCollectionCalculator<>(new SumCalculator()).getResult(values);
    }

    public static double product(Collection<Double> values) {
        return new UnivariateStreamAsCollectionCalculator<>(new ProductCalculator()).getResult(values);
    }

    public static double arithmeticMean(Collection<Double> values) {
        return new UnivariateStreamAsCollectionCalculator<>(new ArithmeticMeanCalculator()).getResult(values);
    }

    public static double geometricMean(Collection<Double> values) {
        return new UnivariateStreamAsCollectionCalculator<>(new GeometricMeanCalculator()).getResult(values);
    }

    public static double harmonicMean(Collection<Double> values) {
        return new UnivariateStreamAsCollectionCalculator<>(new HarmonicMeanCalculator()).getResult(values);
    }

    public static double medianSorted(List<Double> sortedValues) {
        return new MedianCalculator().getResult(sortedValues);
    }

    public static double medianUnsorted(Collection<Double> unsortedValues) {
        List<Double> sortedValues = new ArrayList<>(unsortedValues);
        Collections.sort(sortedValues);
        return new MedianCalculator().getResult(sortedValues);
    }

}
