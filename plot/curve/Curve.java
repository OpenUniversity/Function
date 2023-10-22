package plot.curve;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import plot.Boundable;
import plot.Drawable;

/**
 * Represents something that can be drawn
 */
public abstract class Curve implements Drawable, Iterable<Point2D>, Boundable {

    @Override
    public void draw(GraphicsContext gc) {
        Point2D prev = null;
        for (Point2D curr : this) {
            if (prev != null && curr != null) {
                gc.strokeLine(prev.getX(), prev.getY(), curr.getX(), curr.getY());
            }
            prev = curr;
        }
    }

}
