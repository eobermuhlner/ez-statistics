package ch.obermuhlner.math.statistics.multivariate.collection;

import java.util.Collection;
import java.util.List;

public class DiagonallySymmetricMatrixCalculator implements MultivariateCollectionsCalculator<List<Double>, double[][]> {

    private final MultivariateCollectionsCalculator<Collection<Double>, Double> calculator;
    private final MultivariateCollectionsCalculator<Collection<Double>, Double> diagonalCalculator;

    public DiagonallySymmetricMatrixCalculator(MultivariateCollectionsCalculator<Collection<Double>, Double> calculator, MultivariateCollectionsCalculator<Collection<Double>, Double> diagonalCalculator) {
        this.calculator = calculator;
        this.diagonalCalculator = diagonalCalculator;
    }

    @Override
    public double[][] getResult(List<Double>... values) {
        int size = values.length;
        double[][] matrix = new double[size][];

        for (int x = 0; x < size; x++) {
            matrix[x] = new double[size];
            for (int y = 0; y < size; y++) {
                double result;
                if (x == y) {
                    result = diagonalCalculator.getResult(values[x], values[y]);;
                } else if (x > y) {
                    result = matrix[y][x];
                } else {
                    result = calculator.getResult(values[x], values[y]);
                }
                matrix[x][y] = result;
            }
        }

        return matrix;
    }
}
