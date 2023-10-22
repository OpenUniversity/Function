package axis;

/**
 * Represents a range of numbers that span over a limited amount of pixels, and
 * must contain 0.
 */
public class Axis extends Range {

    protected double pxSize;
    protected double negativeShare;

    public Axis(double start, double end, double pxSize) {
        super(start, end);
        this.pxSize = pxSize;
        initNegativeShare();
    }

    public Axis(double pxSize) {
        this(0, 0, pxSize);
    }

    private void initNegativeShare() {
        if (this.getLength() == 0)
            this.negativeShare = 0.5;
        else
            this.negativeShare = Math.min(Math.max((-this.start) / getLength(), 0), 1);
    }

    @Override
    public boolean expandTo(double x) {
        boolean modified = super.expandTo(x);
        if (modified)
            initNegativeShare();
        return modified;
    }

    public double getPixelsPerUnit() {
        return pxSize / getLength();
    }

    public double getOriginLocation() {
        return pxSize * negativeShare;
    }

    public double getPixelsOfUnits(double units) {
        return ((units - this.start) / getLength()) * pxSize;
    }

}
