package plot.curve;

import java.util.Iterator;

import javafx.geometry.Point2D;
import plot.axes.CartesianAxes;

/**
 * Represents a curve that should be drawn in relation to cartesian axes
 */
public abstract class CartesianAxesCurve extends Curve {

    protected CartesianAxes axes;

    public CartesianAxesCurve() {
    }

    public CartesianAxesCurve(CartesianAxes axes) {
        this.axes = axes;
    }

    public CartesianAxes getAxes() {
        return axes;
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
