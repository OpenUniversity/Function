package plot;

import java.util.Iterator;

import javafx.geometry.Point2D;
import plot.axes.CartesianAxes;

/**
 * Represents a curve that should be drawn in relation to cartesian axes
 */
public abstract class CatesianAxesCurve extends Curve {

    private CartesianAxes axes;

    public CatesianAxesCurve(CartesianAxes axes) {
        this.axes = axes;
    }

    /**
     * Returns the points that should be drawn in relation to the axes
     * 
     * @return the value of the points, in its absolute form (without relation to
     *         the axes)
     */
    public abstract Iterator<Point2D> getAbsolutePoints();

    @Override
    public Iterator<Point2D> iterator() {
        Iterator<Point2D> absoluteIterator = getAbsolutePoints();
        return new Iterator<Point2D>() {

            @Override
            public boolean hasNext() {
                return absoluteIterator.hasNext();
            }

            @Override
            public Point2D next() {
                Point2D point = absoluteIterator.next();
                if (point == null)
                    return null;
                return axes.unitsToPx(point);
            }

        };
    }

}