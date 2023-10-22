package plot.bounded;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

/**
 * Represents a canvas that shows a rectangle subset of the plane
 */
public abstract class BoundedCanvas extends Canvas {

    protected Bounded bounds;
    protected GraphicsContext gc;

    public BoundedCanvas(double width, double height) {
        super(width, height);
        this.gc = getGraphicsContext2D();
        resetBounds();
    }

    protected void resetBounds() {
        bounds = new Bounded();
    }

    protected void expandBounds(Boundable expand) {
        bounds.expand(expand);
    }

    /**
     * Stroke a line between two points
     * 
     * @param p1 the point to start the line from
     * @param p2 the point to end the line at
     */
    protected void strokeLine(Point2D p1, Point2D p2) {
        gc.strokeLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
    }

    public void clear() {
        gc.clearRect(0, 0, getWidth(), getHeight());
    }

    protected abstract void drawInBounds();

    public void draw() {
        // TODO: Check if bounds are defined
        clear();
        drawInBounds();
    }

}
