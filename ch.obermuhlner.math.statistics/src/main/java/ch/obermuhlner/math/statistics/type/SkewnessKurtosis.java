package ch.obermuhlner.math.statistics.type;

public class SkewnessKurtosis {
    public final double skewness;
    public final double kurtosis;

    public SkewnessKurtosis(double skewness, double kurtosis) {
        this.skewness = skewness;
        this.kurtosis = kurtosis;
    }

    @Override
    public String toString() {
        return "(skewness=" + skewness + ", kurtosis=" + kurtosis + ")";
    }
}
