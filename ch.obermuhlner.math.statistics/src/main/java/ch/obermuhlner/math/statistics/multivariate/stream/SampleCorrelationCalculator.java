package ch.obermuhlner.math.statistics.multivariate.stream;

public class SampleCorrelationCalculator implements BivariateStreamCalculator<Double> {

    private int n = 0;
    private double sumX = 0;
    private double sumY = 0;
    private double sumXY = 0;
    private double sumXX = 0;
    private double sumYY = 0;

    @Override
    public void add(double x, double y) {
        sumX += x;
        sumY += y;
        sumXY += x * y;
        sumXX += x * x;
        sumYY += y * y;
        n++;
    }

    public void combine(SampleCorrelationCalculator other) {
        sumX += other.sumX;
        sumY += other.sumY;
        sumXY += other.sumXY;
        sumXX += other.sumXX;
        sumYY += other.sumYY;
        n += other.n;
    }

    @Override
    public Double getResult() {
        double nom = n * sumXY - (sumX*sumY);
        double denom1 = Math.sqrt(n * sumXX - (sumX*sumX));
        double denom2 = Math.sqrt(n * sumYY - (sumY* sumY));
        return nom / (denom1 * denom2);
    }
}
