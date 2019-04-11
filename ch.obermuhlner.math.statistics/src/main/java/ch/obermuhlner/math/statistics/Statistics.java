package ch.obermuhlner.math.statistics;

import ch.obermuhlner.math.statistics.multivariate.collection.CovarianceCalculator;
import ch.obermuhlner.math.statistics.multivariate.collection.MultivariateStreamAsCollectionCalculator;
import ch.obermuhlner.math.statistics.multivariate.collection.MultivariateStreamAsCollectionsCalculator;
import ch.obermuhlner.math.statistics.multivariate.stream.BivariateAsMultivariateStreamCalculator;
import ch.obermuhlner.math.statistics.multivariate.stream.CorrelationCalculator;
import ch.obermuhlner.math.statistics.multivariate.stream.MultivariateCollectionsAsStreamCalculator;
import ch.obermuhlner.math.statistics.type.Histogram;
import ch.obermuhlner.math.statistics.univariate.collection.*;
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

    public static double populationVariance(Collection<Double> values) {
        return new UnivariateStreamAsCollectionCalculator<>(new PopulationVarianceCalculator()).getResult(values);
    }

    public static double populationStandardDeviation(Collection<Double> values) {
        return new UnivariateStreamAsCollectionCalculator<>(new PopulationStandardDeviationCalculator()).getResult(values);
    }

    public static double sampleVariance(Collection<Double> values) {
        return new UnivariateStreamAsCollectionCalculator<>(new SampleVarianceCalculator()).getResult(values);
    }

    public static double sampleStandardDeviation(Collection<Double> values) {
        return new UnivariateStreamAsCollectionCalculator<>(new SampleStandardDeviationCalculator()).getResult(values);
    }

    public static double populationSkewness(Collection<Double> values) {
        return new PopulationSkewnessCalculator().getResult(values);
    }

    public static double populationKurtosis(Collection<Double> values) {
        return new PopulationKurtosisCalculator().getResult(values);
    }

    public static double populationExcessKurtosis(Collection<Double> values) {
        return new PopulationExcessKurtosisCalculator().getResult(values);
    }

    public static double sampleSkewness(Collection<Double> values) {
        return new SampleSkewnessCalculator().getResult(values);
    }

    public static double sampleKurtosis(Collection<Double> values) {
        return new SampleKurtosisCalculator().getResult(values);
    }

    public static double sampleExcessKurtosis(Collection<Double> values) {
        return new SampleExcessKurtosisCalculator().getResult(values);
    }

    public static Histogram histogram(Collection<Double> values, double start, double end, double step) {
        return new UnivariateStreamAsCollectionCalculator<>(new HistogramCalculator(start, end, step)).getResult(values);
    }

    public static double correlation(Collection<double[]> xyValues) {
        return new MultivariateStreamAsCollectionCalculator<>(new BivariateAsMultivariateStreamCalculator<>(new CorrelationCalculator())).getResult(xyValues);
    }

    public static double correlation(List<Double> xValues, List<Double> yValues) {
        return new MultivariateStreamAsCollectionsCalculator<>(new BivariateAsMultivariateStreamCalculator<>(new CorrelationCalculator())).getResult(xValues, yValues);
    }

    public static double covariance(Collection<double[]> xyValues) {
        return new MultivariateStreamAsCollectionCalculator<>(new MultivariateCollectionsAsStreamCalculator<>(new CovarianceCalculator())).getResult(xyValues);
    }

    public static double covariance(List<Double> xValues, List<Double> yValues) {
        return new CovarianceCalculator().getResult(xValues, yValues);
    }
}
