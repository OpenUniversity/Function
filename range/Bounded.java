package range;

/**
 * Represents something in the plane that can be bounded by a rectangle
 */
public class Bounded implements Boundable {

    protected Range xRange, yRange;

    public Bounded() {
        this.xRange = new Range();
        this.yRange = new Range();
    }

    public Bounded(double minX, double maxX, double minY, double maxY) {
        this.xRange = new Range(minX, maxX);
        this.yRange = new Range(minY, maxY);
    }

    @Override
    public double getStartX() {
        return xRange.getStart();
    }

    @Override
    public double getEndX() {
        return xRange.getEnd();
    }

    @Override
    public double getStartY() {
        return yRange.getStart();
    }

    @Override
    public double getEndY() {
        return yRange.getEnd();
    }

    public void expand(Boundable bound) {
        xRange.expandStart(bound.getStartX());
        xRange.expandEnd(bound.getEndX());
        yRange.expandStart(bound.getStartY());
        yRange.expandEnd(bound.getEndY());
    }

}
