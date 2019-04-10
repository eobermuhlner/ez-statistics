package ch.obermuhlner.math.statistics.multivariate.stream;

public class CorrelationCalculator implements BivariateStreamCalculator<Double> {

    private int count = 0;
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
        count++;
    }

    public void combine(CorrelationCalculator other) {
        sumX += other.sumX;
        sumY += other.sumY;
        sumXY += other.sumXY;
        sumXX += other.sumXX;
        sumYY += other.sumYY;
        count += other.count;
    }

    @Override
    public Double getResult() {
        double nom = count * sumXY - (sumX*sumY);
        double denom1 = Math.sqrt(count * sumXX - (sumX*sumX));
        double denom2 = Math.sqrt(count * sumYY - (sumY* sumY));
        return nom / (denom1 * denom2);
    }
}
