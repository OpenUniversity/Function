package plot.axes;

import javafx.geometry.Point2D;
import plot.bounded.Boundable;
import plot.bounded.BoundedCanvas;

/**
 * Represents a canvas with a cartesian axes
 */
public class CartesianAxesCanvas extends BoundedCanvas {

    private CartesianAxes axes;

    public CartesianAxesCanvas(double width, double height) {
        super(width, height);
    }

    protected void setAxes() {
        axes = new CartesianAxes(bounds, getWidth(), getHeight());
    }

    @Override
    protected void resetBounds() {
        super.resetBounds();
    }

    @Override
    public void expandBounds(Boundable expand) {
        super.expandBounds(expand);
        setAxes();
    }

    @Override
    protected void strokeLine(Point2D p1, Point2D p2) {
        super.strokeLine(axes.unitsToPx(p1), axes.unitsToPx(p2));
    }

    @Override
    protected void drawInBounds() {
        axes.draw(gc);
    }

}
