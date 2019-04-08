package ch.obermuhlner.math.statistics.univariate.collection;

import ch.obermuhlner.math.statistics.univariate.stream.UnivariateStreamCalculator;

import java.util.Collection;

public class UnivariateStreamAsCollectionCalculator<C extends Collection<Double>, R> implements UnivariateCollectionCalculator<C, R> {

    private final UnivariateStreamCalculator<R> calculator;

    public UnivariateStreamAsCollectionCalculator(UnivariateStreamCalculator<R> calculator) {
        this.calculator = calculator;
    }

    @Override
    public R getResult(C values) {
        for(Double value : values) {
            calculator.add(value);
        }
        return calculator.getResult();
    }
}
