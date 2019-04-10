package ch.obermuhlner.math.statistics.multivariate.collection;

import ch.obermuhlner.math.statistics.multivariate.stream.MultivariateStreamCalculator;

import java.util.Collection;

public class MultivariateStreamAsCollectionCalculator<C extends Collection<double[]>, R> implements MultivariateCollectionCalculator<C, R> {

    private final MultivariateStreamCalculator<R> calculator;

    public MultivariateStreamAsCollectionCalculator(MultivariateStreamCalculator<R> calculator) {
        this.calculator = calculator;
    }

    @Override
    public R getResult(C tuples) {
        for(double[] tuple : tuples) {
            calculator.add(tuple);
        }
        return calculator.getResult();
    }
}
