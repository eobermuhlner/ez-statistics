package ch.obermuhlner.math.statistics;

import ch.obermuhlner.math.statistics.univariate.stream.ArithmeticMeanCalculator;
import org.junit.Test;

import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class StatisticsCollectorsTest {

    private static final double EPSILON = 0.0001;

    @Test
    public void testMin() {
        double result = Stream.of(4, 5, 1, 2, 3)
                .parallel()
                .map(Double::valueOf)
                .collect(StatisticsCollectors.min());
        assertEquals(1, result, EPSILON);
    }

    @Test
    public void testMax() {
        double result = Stream.of(4, 5, 1, 2, 3)
                .parallel()
                .map(Double::valueOf)
                .collect(StatisticsCollectors.max());
        assertEquals(5, result, EPSILON);
    }

    @Test
    public void testSum() {
        double result = Stream.of(0, 1, 2, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
                .parallel()
                .map(Double::valueOf)
                .collect(StatisticsCollectors.sum());
        assertEquals(6, result, EPSILON);
    }

    @Test
    public void testProduct() {
        double result = Stream.of(1, 2, 3, 4)
                .parallel()
                .map(Double::valueOf)
                .collect(StatisticsCollectors.product());
        assertEquals(1*2*3*4, result, EPSILON);
    }

    @Test
    public void testArithmeticMean() {
        double result = Stream.of(1, 2, 3)
                .parallel()
                .map(Double::valueOf)
                .collect(StatisticsCollectors.arithmeticMean());
        assertEquals(6/3, result, EPSILON);
    }

    @Test
    public void testArithmeticMean_Stream_variants() {
        double result1a = Stream.of(1, 2, 3)
                .parallel()
                .map(Double::valueOf)
                .collect(Collectors.averagingDouble(Double::doubleValue));
        assertEquals(6 / 3, result1a, EPSILON);

        double result1b = DoubleStream.of(1, 2, 3)
                .parallel()
                .average()
                .getAsDouble();
        assertEquals(6 / 3, result1b, EPSILON);

        double result2 = DoubleStream.of(1, 2, 3)
                .parallel()
                .collect(
                        ArithmeticMeanCalculator::new,
                        ArithmeticMeanCalculator::add,
                        ArithmeticMeanCalculator::combine)
                .getResult();
        assertEquals(6 / 3, result2, EPSILON);

        double result4 = DoubleStream.of(1, 2, 3)
                .parallel()
                .boxed()
                .collect(StatisticsCollectors.arithmeticMean());
        assertEquals(6 / 3, result4, EPSILON);
    }

    @Test
    public void testMedian() {
        double result = DoubleStream.of(1, 2, 3, 4, 2, 2)
                .boxed()
                .collect(StatisticsCollectors.median());
        assertEquals(2, result, EPSILON);
    }

    @Test
    public void testPopulationSkewness() {
        // https://www.wolframalpha.com/input/?i=Skewness%5B1,2,3,4,4%5D
        double result = DoubleStream.of(1, 2, 3, 4, 4)
                .boxed()
                .collect(StatisticsCollectors.populationSkewness());
        assertEquals(-0.36317, result, EPSILON);
    }

    @Test
    public void testPopulationKurtosis() {
        // https://www.wolframalpha.com/input/?i=kurtosis+1,2,3,4,4
        double result = DoubleStream.of(1, 2, 3, 4, 4)
                .boxed()
                .collect(StatisticsCollectors.populationKurtosis());
        assertEquals(1.6280, result, EPSILON);
    }

    @Test
    public void testPopulationExcessKurtosis() {
        // https://www.wolframalpha.com/input/?i=kurtosis+1,2,3,4,4
        double result = DoubleStream.of(1, 2, 3, 4, 4)
                .boxed()
                .collect(StatisticsCollectors.populationExcessKurtosis());
        assertEquals(1.6280 - 3, result, EPSILON);
    }

    @Test
    public void testSampleSkewness() {
        // Excel: =SKEW(1;2;3;4;4)
        double result = DoubleStream.of(1, 2, 3, 4, 4)
                .boxed()
                .collect(StatisticsCollectors.sampleSkewness());
        assertEquals(-0.54139, result, EPSILON);
    }

    @Test
    public void testSampleExcessKurtosis() {
        // Excel: =KURT(1;2;3;4;4)
        double result = DoubleStream.of(1, 2, 3, 4, 4)
                .boxed()
                .collect(StatisticsCollectors.sampleExcessKurtosis());
        assertEquals(-1.4879, result, EPSILON);
    }
/*
    @Test
    public void testHistogram() {
        Histogram histogram = Stream.of(1, 2, 3, 5, 5, 5, 5, 6, 6, 7, 8, 8, 9)
                .parallel()
                .map(x -> BigDecimal.valueOf(x))
                .collect(StatisticsCollectors.histogram(BigDecimal.valueOf(0), BigDecimal.valueOf(10), BigDecimal.valueOf(2)));

        assertEquals(5, histogram.size());

        assertEquals(BigDecimal.valueOf(0), histogram.getStart(0));
        assertEquals(BigDecimal.valueOf(2), histogram.getStart(1));
        assertEquals(BigDecimal.valueOf(4), histogram.getStart(2));
        assertEquals(BigDecimal.valueOf(6), histogram.getStart(3));
        assertEquals(BigDecimal.valueOf(8), histogram.getStart(4));

        assertEquals(BigDecimal.valueOf(2), histogram.getEnd(0));
        assertEquals(BigDecimal.valueOf(4), histogram.getEnd(1));
        assertEquals(BigDecimal.valueOf(6), histogram.getEnd(2));
        assertEquals(BigDecimal.valueOf(8), histogram.getEnd(3));
        assertEquals(BigDecimal.valueOf(10), histogram.getEnd(4));

        assertEquals(1, histogram.getCount(0));
        assertEquals(2, histogram.getCount(1));
        assertEquals(4, histogram.getCount(2));
        assertEquals(3, histogram.getCount(3));
        assertEquals(3, histogram.getCount(4));
    }

    @Test
    public void testCorrelation() {
        assertBigDecimal(
                BigDecimal.valueOf(1),
                Stream.of(tuple(1, 1), tuple(2, 2), tuple(3, 3))
                    .parallel()
                    .collect(StatisticsCollectors.correlation(MC)));

        assertBigDecimal(
                BigDecimal.valueOf(-1),
                Stream.of(tuple(1, 3), tuple(2, 2), tuple(3, 1))
                        .parallel()
                        .collect(StatisticsCollectors.correlation(MC)));

        // http://www.statstutor.ac.uk/resources/uploaded/pearsons.pdf
        assertBigDecimal(
                BigDecimal.valueOf(0.877),
                Stream.of(tuple(15.5, 0.450), tuple(13.6, 0.420), tuple(13.5, 0.440), tuple(13.0, 0.395), tuple(13.3, 0.395), tuple(12.4, 0.370), tuple(11.1, 0.390), tuple(13.1, 0.400), tuple(16.1, 0.445), tuple(16.4, 0.470), tuple(13.4, 0.390), tuple(13.2, 0.400), tuple(14.3, 0.420), tuple(16.1, 0.450))
                        .parallel()
                        .collect(StatisticsCollectors.correlation(MC)).round(new MathContext(3)));
    }
*/
}
