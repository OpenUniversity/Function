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
            this.negativeShare = -this.start / getLength();
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
        return Math.min(pxSize, Math.max(0, getOriginLocationUnsafe()));
    }

    protected double getOriginLocationUnsafe() {
        return pxSize * negativeShare;
    }

    public double getPxSize() {
        return pxSize;
    }

    public double getPixelsOfUnits(double units) {
        return ((units - this.start) / getLength()) * pxSize;
    }

    public double getUnitsOfPixels(double pixels) {
        double axisShare = pixels / pxSize;
        return start + axisShare * getLength();
    }

    @Override
    public void setRange(double start, double end) {
        super.setRange(start, end);
        initNegativeShare();
    }

}
