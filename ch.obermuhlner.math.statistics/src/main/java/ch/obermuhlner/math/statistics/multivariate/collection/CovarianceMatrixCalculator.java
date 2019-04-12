package ch.obermuhlner.math.statistics.multivariate.collection;

public class CovarianceMatrixCalculator extends DiagonallySymmetricMatrixCalculator {

    public CovarianceMatrixCalculator(AbstractCovarianceCalculator covarianceCalculator) {
        super(covarianceCalculator, covarianceCalculator);
    }
}
