package ch.obermuhlner.math.statistics;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class StatisticsTest {

    private static final double EPSILON = 0.0001;

    @Test
    public void testSum() {
        assertEquals(6.0, Statistics.sum(toDoubles(1, 2, 3)), EPSILON);
    }

    @Test
    public void testProduct() {
        assertEquals(24.0, Statistics.product(toDoubles(2, 3, 4)), EPSILON);
    }

    @Test
    public void testMin() {
        assertEquals(4.0, Statistics.min(toDoubles(6, 5, 4, 7)), EPSILON);
        assertEquals(-1.0, Statistics.min(toDoubles(3, 2, 1, 0, -1)), EPSILON);
    }

    @Test
    public void testMax() {
        assertEquals(7.0, Statistics.max(toDoubles(6, 5, 4, 7)), EPSILON);
        assertEquals(3.0, Statistics.max(toDoubles(3, 2, 1, 0, -1)), EPSILON);
    }

    @Test
    public void testArithmeticMean() {
        assertEquals(6.0/3, Statistics.arithmeticMean(toDoubles(1, 2, 3)), EPSILON);
    }

    @Test
    public void testMedianSorted() {
        assertEquals(4.0, Statistics.medianSorted(toDoubles(1, 2, 3, 4, 4, 4, 4)), EPSILON);
        assertEquals(1.0, Statistics.medianSorted(toDoubles(1, 1, 1, 2)), EPSILON);

        assertEquals(3.5, Statistics.medianSorted(toDoubles(1, 2, 3, 4, 4, 4)), EPSILON);
        assertEquals(1.5, Statistics.medianSorted(toDoubles(1, 1, 2, 4)), EPSILON);
    }

    @Test
    public void testMedianUnsorted() {
        assertEquals(4.0, Statistics.medianUnsorted(toDoubles(4, 2, 3, 4, 1, 4, 4)), EPSILON);
        assertEquals(1.0, Statistics.medianUnsorted(toDoubles(1, 2, 1, 1)), EPSILON);

        assertEquals(3.5, Statistics.medianUnsorted(toDoubles(1, 4, 3, 2, 4, 4)), EPSILON);
        assertEquals(1.5, Statistics.medianUnsorted(toDoubles(2, 1, 4, 1)), EPSILON);
    }

    @Test
    public void testSampleStandardDeviation() {
        // https://www.wolframalpha.com/input/?i=sample+standard+deviation+of+1,2,3
        assertEquals(
                1.0,
                Statistics.sampleStandardDeviation(toDoubles(1, 2, 3)), EPSILON);

        // https://www.wolframalpha.com/input/?i=sample+standard+deviation+of+1,2,3,4
        assertEquals(
                1.2910,
                Statistics.sampleStandardDeviation(toDoubles(1, 2, 3, 4)), EPSILON);
    }

    @Test
    public void testPopulationStandardDeviation() {
        // https://www.wolframalpha.com/input/?i=population+standard+deviation+of+1,2,3
        assertEquals(
                0.81650,
                Statistics.populationStandardDeviation(toDoubles(1, 2, 3)), EPSILON);
    }

    @Test
    public void testPopulationVariance() {
        // https://www.wolframalpha.com/input/?i=populationvariance+1,2,3
        assertEquals(
                0.66667,
                Statistics.populationVariance(toDoubles(1, 2, 3)),
                EPSILON);
    }

    @Test
    public void testSampleVariance() {
        // https://www.wolframalpha.com/input/?i=sample+variance+of+1,2,3,4
        assertEquals(
                1.6667,
                Statistics.sampleVariance(toDoubles(1, 2, 3, 4)),
                EPSILON);
    }

    /*
    @Test
    public void testPopulationSkewness() {
        // https://www.wolframalpha.com/input/?i=Skewness%5B1,2,3,4,4%5D
        assertEquals(
                -0.36317,
                Statistics.populationSkewness(toDoubles(1, 2, 3, 4, 4)),
                EPSILON);
    }

    @Test
    public void testPopulationKurtosis() {
        // https://www.wolframalpha.com/input/?i=kurtosis+1,2,3,4,4
        assertEquals(
                1.6280,
                Statistics.populationKurtosis(toDoubles(1, 2, 3, 4, 4)),
                EPSILON);
    }

    @Test
    public void testPopulationExcessKurtosis() {
        // https://www.wolframalpha.com/input/?i=kurtosis+1,2,3,4,4
        assertEquals(
                1.6280 - 3,
                Statistics.populationExcessKurtosis(toDoubles(1, 2, 3, 4, 4)),
                EPSILON);
    }

    @Test
    public void testSampleSkewness() {
        // Excel: =SKEW(1;2;3;4;4)
        assertEquals(
                -0.54139,
                Statistics.sampleSkewness(toDoubles(1, 2, 3, 4, 4)),
                EPSILON);
    }

    @Test
    public void testSampleExcessKurtosis() {
        // Excel: =KURT(1;2;3;4;4)
        assertEquals(
                -1.4879,
                Statistics.sampleExcessKurtosis(toDoubles(1, 2, 3, 4, 4)),
                EPSILON);
    }

    @Test
    public void testHistogram() {
        Histogram histogram = Statistics.histogram(
                Arrays.asList(1, 2, 3, 5, 5, 5, 5, 6, 6, 7, 8, 8, 9),
                0.0, 10.0, 2.0);

        assertEquals(5, histogram.size());

        assertEquals(0.0, histogram.getStart(0), EPSILON);
        assertEquals(2.0, histogram.getStart(1), EPSILON);
        assertEquals(4.0, histogram.getStart(2), EPSILON);
        assertEquals(6.0, histogram.getStart(3), EPSILON);
        assertEquals(8.0, histogram.getStart(4), EPSILON);

        assertEquals(2.0, histogram.getEnd(0), EPSILON);
        assertEquals(4.0, histogram.getEnd(1), EPSILON);
        assertEquals(6.0, histogram.getEnd(2), EPSILON);
        assertEquals(8.0, histogram.getEnd(3), EPSILON);
        assertEquals(10.0, histogram.getEnd(4), EPSILON);

        assertEquals(1, histogram.getCount(0));
        assertEquals(2, histogram.getCount(1));
        assertEquals(4, histogram.getCount(2));
        assertEquals(3, histogram.getCount(3));
        assertEquals(3, histogram.getCount(4));
    }

    @Test
    public void testCorrelation() {
        assertEquals(
                1,
                Statistics.correlation(Arrays.asList(tuple(1, 1), tuple(2, 2), tuple(3, 3))),
                EPSILON);

        assertEquals(
                -1,
                Statistics.correlation(Arrays.asList(tuple(1, 3), tuple(2, 2), tuple(3, 1))),
                EPSILON);

        assertEquals(
                0.877,
                Statistics.correlation(Arrays.asList(tuple(15.5, 0.450), tuple(13.6, 0.420), tuple(13.5, 0.440), tuple(13.0, 0.395), tuple(13.3, 0.395), tuple(12.4, 0.370), tuple(11.1, 0.390), tuple(13.1, 0.400), tuple(16.1, 0.445), tuple(16.4, 0.470), tuple(13.4, 0.390), tuple(13.2, 0.400), tuple(14.3, 0.420), tuple(16.1, 0.450))),
                EPSILON);
    }

    @Test
    public void testCorrelation2() {
        assertEquals(
                1.0,
                Statistics.correlation(
                        toDoubles(1, 2, 3),
                        toDoubles(1, 2, 3)),
                EPSILON);

        assertEquals(
                -1.0,
                Statistics.correlation(
                        toDoubles(1, 2, 3),
                        toDoubles(3, 2, 1)),
                EPSILON);

        assertEquals(
                0.877,
                Statistics.correlation(
                        Arrays.asList(15.5, 13.6, 13.5, 13.0, 13.3, 12.4, 11.1, 13.1, 16.1, 16.4, 13.4, 13.2, 14.3, 16.1),
                        Arrays.asList(0.450, 0.420, 0.440, 0.395, 0.395, 0.370, 0.390, 0.400, 0.445, 0.470, 0.390, 0.400, 0.420, 0.450)),
                EPSILON);
    }
    */

    private static List<Double> toDoubles(int... values) {
        return Arrays.stream(values)
                .mapToObj(v -> (double) v)
                .collect(Collectors.toList());
    }
}
