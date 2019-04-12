package ch.obermuhlner.math.statistics.multivariate.collection;

import java.util.Collection;
import java.util.List;

public class SimpleMatrixCalculator implements MultivariateCollectionsCalculator<List<Double>, double[][]> {

    private final MultivariateCollectionsCalculator<Collection<Double>, Double> calculator;

    public SimpleMatrixCalculator(MultivariateCollectionsCalculator<Collection<Double>, Double> calculator) {
        this.calculator = calculator;
    }

    @Override
    public double[][] getResult(List<Double>... values) {
        int size = values.length;
        double[][] matrix = new double[size][];

        for (int x = 0; x < size; x++) {
            matrix[x] = new double[size];
            for (int y = 0; y < size; y++) {
                double result = calculator.getResult(values[x], values[y]);
                matrix[x][y] = result;
            }
        }

        return matrix;
    }
}
