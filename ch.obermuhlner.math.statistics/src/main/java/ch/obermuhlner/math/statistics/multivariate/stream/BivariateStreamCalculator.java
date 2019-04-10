package ch.obermuhlner.math.statistics.multivariate.stream;

public interface BivariateStreamCalculator<R> {

    void add(double value1, double value2);

    R getResult();
}
