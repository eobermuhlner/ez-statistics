package ch.obermuhlner.math.statistics.multivariate.stream;

public class BivariateAsMultivariateStreamCalculator<R> implements MultivariateStreamCalculator<R> {

    private final BivariateStreamCalculator<R> bivariateStreamCalculator;
    private final int value1Index;
    private final int value2Index;

    public BivariateAsMultivariateStreamCalculator(BivariateStreamCalculator<R> bivariateStreamCalculator) {
        this(bivariateStreamCalculator, 0, 1);
    }

    public BivariateAsMultivariateStreamCalculator(BivariateStreamCalculator<R> bivariateStreamCalculator, int value1Index, int value2Index) {
        this.bivariateStreamCalculator = bivariateStreamCalculator;
        this.value1Index = value1Index;
        this.value2Index = value2Index;
    }

    @Override
    public void add(double... tuple) {
        bivariateStreamCalculator.add(tuple[value1Index], tuple[value2Index]);
    }

    @Override
    public R getResult() {
        return bivariateStreamCalculator.getResult();
    }
}
