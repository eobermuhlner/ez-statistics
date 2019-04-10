package ch.obermuhlner.math.statistics.multivariate.collection;

import ch.obermuhlner.math.statistics.multivariate.stream.MultivariateStreamCalculator;

import java.util.Collection;
import java.util.Iterator;

public class MultivariateStreamAsListsCalculator<C extends Collection<Double>, R> implements MultivariateCollectionsCalculator<C, R> {

    private final MultivariateStreamCalculator<R> calculator;

    public MultivariateStreamAsListsCalculator(MultivariateStreamCalculator<R> calculator) {
        this.calculator = calculator;
    }

    @Override
    public R getResult(C... values) {
        int n = values.length;
        Iterator<Double>[] iterators = new Iterator[n];
        for (int i = 0; i < n; i++) {
            iterators[i] = values[i].iterator();
        }

        boolean finished = false;
        while (!finished) {
            finished = true;
            double[] tuple = new double[n];
            for (int i = 0; i < n; i++) {
                if (iterators[i].hasNext()) {
                    finished = false;
                    tuple[i] = iterators[i].next();
                }
            }
            if (!finished) {
                calculator.add(tuple);
            }
        }

        return calculator.getResult();
    }
}
