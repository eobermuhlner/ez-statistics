package ch.obermuhlner.math.statistics.multivariate.stream;

import ch.obermuhlner.math.statistics.multivariate.collection.MultivariateCollectionsCalculator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MultivariateCollectionsAsStreamCalculator<R> implements MultivariateStreamCalculator<R> {

    private final MultivariateCollectionsCalculator<Collection<Double>, R> multivariateCollectionsCalculator;

    private Collection<Double>[] lists = null;

    public MultivariateCollectionsAsStreamCalculator(MultivariateCollectionsCalculator<Collection<Double>, R> multivariateCollectionsCalculator) {
        this.multivariateCollectionsCalculator = multivariateCollectionsCalculator;
    }

    @Override
    public void add(double... tuple) {
        if (lists == null) {
            lists = new List[tuple.length];
            for (int i = 0; i < tuple.length; i++) {
                lists[i] = new ArrayList<>();
            }
        }

        for (int i = 0; i < tuple.length; i++) {
            lists[i].add(tuple[i]);
        }
    }

    @Override
    public R getResult() {
        return multivariateCollectionsCalculator.getResult(lists);
    }
}
