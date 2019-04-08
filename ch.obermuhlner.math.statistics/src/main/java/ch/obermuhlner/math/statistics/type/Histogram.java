package ch.obermuhlner.math.statistics.type;

public class Histogram {

    private final double start;
    private final double step;
    private final int[] count;

    public Histogram(double start, double step, int[] count) {
        this.start = start;
        this.step = step;
        this.count = count;
    }

    public int size() {
        return count.length;
    }

    public int getCount(int index) {
        return count[index];
    }

    public double getStart(int index) {
        return start + step * index;
    }

    public double getEnd(int index) {
        return start + step * (index + 1);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Histogram(");
        for (int i = 0; i < size(); i++) {
            if (i > 0) {
                builder.append(", ");
            }
            builder.append(getStart(i));
            builder.append(":");
            builder.append(getCount(i));
        }
        builder.append(")");
        return builder.toString();
    }
}
