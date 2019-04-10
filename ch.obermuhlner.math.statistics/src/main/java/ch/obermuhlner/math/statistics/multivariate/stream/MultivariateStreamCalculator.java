package ch.obermuhlner.math.statistics.multivariate.stream;

public interface MultivariateStreamCalculator<R> {

    void add(double... tuple);

    R getResult();
}
