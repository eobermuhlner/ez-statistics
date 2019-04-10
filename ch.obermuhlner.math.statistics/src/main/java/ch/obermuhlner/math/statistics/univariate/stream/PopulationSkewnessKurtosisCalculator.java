package ch.obermuhlner.math.statistics.univariate.stream;

import ch.obermuhlner.math.statistics.type.SkewnessKurtosis;

// https://en.wikipedia.org/wiki/Algorithms_for_calculating_variance
public class PopulationSkewnessKurtosisCalculator implements UnivariateStreamCalculator<SkewnessKurtosis> {

    private final PopulationStandardDeviationCalculator standardDeviationCalculator;

    private final boolean calculateSkewness;
    private final boolean calculateKurtosis;

    private int n = 0;
    private double mean = 0;
    private double m2 = 0;
    private double m3 = 0;
    private double m4 = 0;

    public PopulationSkewnessKurtosisCalculator() {
        this(true, true);
    }

    public PopulationSkewnessKurtosisCalculator(boolean calculateSkewness, boolean calculateKurtosis) {
        this.standardDeviationCalculator = new PopulationStandardDeviationCalculator();
        this.calculateSkewness = calculateSkewness;
        this.calculateKurtosis = calculateKurtosis;
    }

    @Override
    public void add(double value) {
        n++;
        double nPow2 = n * n;

        double delta = value - mean;
        double deltaDivN = delta / n;
        double deltaDivNPow2 = deltaDivN * deltaDivN;

        mean = mean + deltaDivN;

        double term = delta * deltaDivN * (n-1);

        if (calculateKurtosis) {
            double m4Step = term * deltaDivNPow2 * (nPow2 - (n*3) + 3) + (6 * deltaDivNPow2 * m2) - (4 * deltaDivN * m3);
            m4 += m4Step;
        }

        double m3Step = term * deltaDivN * (n-2) - (3  * deltaDivN * m2);
        m3 = m3 += m3Step;

        double m2Step = term;
        m2 += m2Step;
    }

    public int getCount() {
        return n;
    }

    public double getSkewness() {
        if (calculateSkewness) {
            double nom = Math.sqrt(n) * m3;
            double denom = Math.pow(m2, 1.5);
            return nom / denom;
        } else {
            return Double.NaN;
        }
    }

    public double getKurtosis() {
        if (calculateKurtosis) {
            return n * m4 / (m2 * m2);
        } else {
            return Double.NaN;
        }
    }

    @Override
    public SkewnessKurtosis getResult() {
        return new SkewnessKurtosis(getSkewness(), getKurtosis());
    }

}