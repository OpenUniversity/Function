package range;

/**
 * Represents a rectangle in the real plane
 */
public class Range {

    private double start;
    private double end;

    public Range() {
        this(Double.MAX_VALUE, Double.MIN_VALUE);
    }

    public Range(double start, double end) {
        this.start = start;
        this.end = end;
    }

    public Range(Range other) {
        this(other.getStart(), other.getEnd());
    }

    public double getStart() {
        return start;
    }

    public void expandStart(double x) {
        start = Math.min(start, x);
    }

    public double getEnd() {
        return end;
    }

    public void expandEnd(double x) {
        end = Math.max(end, x);
    }

}
