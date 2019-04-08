package ch.obermuhlner.math.statistics.univariate.stream;

import java.math.BigDecimal;
import java.math.MathContext;

public class PopulationVarianceCalculator implements UnivariateStreamCalculator<Double> {

    private final ArithmeticMeanCalculator arithmeticMeanCalculator = new ArithmeticMeanCalculator();

    private double sumSquares = 0;

    @Override
    public void add(double value) {
        arithmeticMeanCalculator.add(value);

        double valueSquared = value * value;
        sumSquares  += valueSquared;
    }

    public void combine(PopulationVarianceCalculator other) {
        arithmeticMeanCalculator.combine(other.arithmeticMeanCalculator);

        sumSquares += sumSquares + other.sumSquares;
    }

    @Override
    public Double getResult() {
        double n = getCount();
        double mean = arithmeticMeanCalculator.getResult();
        return sumSquares / n - mean * mean;
    }

    public int getCount() {
        return arithmeticMeanCalculator.getCount();
    }
}
