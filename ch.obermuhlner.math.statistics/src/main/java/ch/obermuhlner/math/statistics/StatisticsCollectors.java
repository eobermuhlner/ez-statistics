package ch.obermuhlner.math.statistics;

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
}
