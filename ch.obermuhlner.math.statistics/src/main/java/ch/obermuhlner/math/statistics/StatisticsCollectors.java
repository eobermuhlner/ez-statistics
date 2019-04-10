package ch.obermuhlner.math.statistics;

import ch.obermuhlner.math.statistics.type.SkewnessKurtosis;
import ch.obermuhlner.math.statistics.univariate.collection.MedianCalculator;
import ch.obermuhlner.math.statistics.univariate.stream.*;

import java.util.stream.Collector;

public class StatisticsCollectors {
    public static Collector<Double, MinCalculator, Double> min() {
        return Collector.of(
                () -> new MinCalculator(),
                (calc, value) -> calc.add(value),
                (left, right) -> { left.combine(right); return left; },
                (calc) -> calc.getResult());
    }

    public static Collector<Double, MaxCalculator, Double> max() {
        return Collector.of(
                () -> new MaxCalculator(),
                (calc, value) -> calc.add(value),
                (left, right) -> { left.combine(right); return left; },
                (calc) -> calc.getResult());
    }

    public static Collector<Double, SumCalculator, Double> sum() {
        return Collector.of(
                () -> new SumCalculator(),
                (calc, value) -> calc.add(value),
                (left, right) -> { left.combine(right); return left; },
                (calc) -> calc.getResult());
    }

    public static Collector<Double, ProductCalculator, Double> product() {
        return Collector.of(
                () -> new ProductCalculator(),
                (calc, value) -> calc.add(value),
                (left, right) -> { left.combine(right); return left; },
                (calc) -> calc.getResult());
    }

    public static Collector<Double, ArithmeticMeanCalculator, Double> arithmeticMean() {
        return Collector.of(
                () -> new ArithmeticMeanCalculator(),
                (calc, value) -> calc.add(value),
                (left, right) -> { left.combine(right); return left; },
                (calc) -> calc.getResult());
    }

    public static Collector<Double, GeometricMeanCalculator, Double> geometricMean() {
        return Collector.of(
                () -> new GeometricMeanCalculator(),
                (calc, value) -> calc.add(value),
                (left, right) -> { left.combine(right); return left; },
                (calc) -> calc.getResult());
    }

    public static Collector<Double, HarmonicMeanCalculator, Double> harmonicMean() {
        return Collector.of(
                () -> new HarmonicMeanCalculator(),
                (calc, value) -> calc.add(value),
                (left, right) -> { left.combine(right); return left; },
                (calc) -> calc.getResult());
    }

    public static Collector<Double, UnivariateStreamCalculator<Double>, Double> median() {
        return Collector.of(
                () -> new UnivariateCollectionAsStreamCalculator<>(new MedianCalculator()),
                (calc, value) -> calc.add(value),
                (left, right) -> { throw new UnsupportedOperationException("parallel computation not supported for median"); },
                (calc) -> calc.getResult());
    }

    public static Collector<Double, PopulationVarianceCalculator, Double> populationVariance() {
        return Collector.of(
                () -> new PopulationVarianceCalculator(),
                (calc, value) -> calc.add(value),
                (left, right) -> { left.combine(right); return left; },
                (calc) -> calc.getResult());
    }

    public static Collector<Double, PopulationStandardDeviationCalculator, Double> populationStandardDeviation() {
        return Collector.of(
                () -> new PopulationStandardDeviationCalculator(),
                (calc, value) -> calc.add(value),
                (left, right) -> { left.combine(right); return left; },
                (calc) -> calc.getResult());
    }

    public static Collector<Double, SampleVarianceCalculator, Double> sampleVariance() {
        return Collector.of(
                () -> new SampleVarianceCalculator(),
                (calc, value) -> calc.add(value),
                (left, right) -> { left.combine(right); return left; },
                (calc) -> calc.getResult());
    }

    public static Collector<Double, SampleStandardDeviationCalculator, Double> sampleStandardDeviation() {
        return Collector.of(
                () -> new SampleStandardDeviationCalculator(),
                (calc, value) -> calc.add(value),
                (left, right) -> { left.combine(right); return left; },
                (calc) -> calc.getResult());
    }

    public static Collector<Double, PopulationSkewnessKurtosisCalculator, Double> populationSkewness() {
        return Collector.of(
                () -> new PopulationSkewnessKurtosisCalculator(true, false),
                (calc, value) -> calc.add(value),
                (left, right) -> { throw new UnsupportedOperationException("parallel computation not supported for skewness"); },
                (calc) -> calc.getSkewness());
    }

    public static Collector<Double, PopulationSkewnessKurtosisCalculator, Double> populationKurtosis() {
        return Collector.of(
                () -> new PopulationSkewnessKurtosisCalculator(false, true),
                (calc, value) -> calc.add(value),
                (left, right) -> { throw new UnsupportedOperationException("parallel computation not supported for kurtosis"); },
                (calc) -> calc.getKurtosis());
    }

    public static Collector<Double, PopulationSkewnessExcessKurtosisCalculator, Double> populationExcessKurtosis() {
        return Collector.of(
                () -> new PopulationSkewnessExcessKurtosisCalculator(false, true),
                (calc, value) -> calc.add(value),
                (left, right) -> { throw new UnsupportedOperationException("parallel computation not supported for kurtosis"); },
                (calc) -> calc.getKurtosis());
    }

    public static Collector<Double, PopulationSkewnessKurtosisCalculator, SkewnessKurtosis> populationSkewnessKurtosis() {
        return Collector.of(
                () -> new PopulationSkewnessKurtosisCalculator(),
                (calc, value) -> calc.add(value),
                (left, right) -> { throw new UnsupportedOperationException("parallel computation not supported for skewness and kurtosis"); },
                (calc) -> calc.getResult());
    }

    public static Collector<Double, PopulationSkewnessExcessKurtosisCalculator, SkewnessKurtosis> populationSkewnessExcessKurtosis() {
        return Collector.of(
                () -> new PopulationSkewnessExcessKurtosisCalculator(),
                (calc, value) -> calc.add(value),
                (left, right) -> { throw new UnsupportedOperationException("parallel computation not supported for skewness and kurtosis"); },
                (calc) -> calc.getResult());
    }

    public static Collector<Double, SampleSkewnessKurtosisCalculator, Double> sampleSkewness() {
        return Collector.of(
                () -> new SampleSkewnessKurtosisCalculator(true, false),
                (calc, value) -> calc.add(value),
                (left, right) -> { throw new UnsupportedOperationException("parallel computation not supported for skewness"); },
                (calc) -> calc.getSkewness());
    }

    public static Collector<Double, SampleSkewnessKurtosisCalculator, Double> sampleKurtosis() {
        return Collector.of(
                () -> new SampleSkewnessKurtosisCalculator(false, true),
                (calc, value) -> calc.add(value),
                (left, right) -> { throw new UnsupportedOperationException("parallel computation not supported for kurtosis"); },
                (calc) -> calc.getKurtosis());
    }

    public static Collector<Double, SampleSkewnessExcessKurtosisCalculator, Double> sampleExcessKurtosis() {
        return Collector.of(
                () -> new SampleSkewnessExcessKurtosisCalculator(false, true),
                (calc, value) -> calc.add(value),
                (left, right) -> { throw new UnsupportedOperationException("parallel computation not supported for kurtosis"); },
                (calc) -> calc.getKurtosis());
    }

    public static Collector<Double, SampleSkewnessKurtosisCalculator, SkewnessKurtosis> sampleSkewnessKurtosis() {
        return Collector.of(
                () -> new SampleSkewnessKurtosisCalculator(),
                (calc, value) -> calc.add(value),
                (left, right) -> { throw new UnsupportedOperationException("parallel computation not supported for skewness and kurtosis"); },
                (calc) -> calc.getResult());
    }

    public static Collector<Double, SampleSkewnessExcessKurtosisCalculator, SkewnessKurtosis> sampleSkewnessExcessKurtosis() {
        return Collector.of(
                () -> new SampleSkewnessExcessKurtosisCalculator(),
                (calc, value) -> calc.add(value),
                (left, right) -> { throw new UnsupportedOperationException("parallel computation not supported for skewness and kurtosis"); },
                (calc) -> calc.getResult());
    }
}
