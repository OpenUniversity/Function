package curve;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

/**
 * Represents an encapsulated version of the canvas class, with a controlled
 * {@code GraphicsContext}
 */
public abstract class Canvas extends javafx.scene.canvas.Canvas {

    private GraphicsContext gc; // we manually take control of the Graphics context here

    public Canvas(double width, double height) {
        super(width, height);
        gc = super.getGraphicsContext2D();
    }

    @Override
    public GraphicsContext getGraphicsContext2D() {
        return null; // don't allow drawing outside of this object
    }

    /**
     * Clear the canvas
     */
    protected void clear() {
        gc.clearRect(0, 0, getWidth(), getHeight());
    }

    /**
     * Stroke a line between 4 coords
     */
    protected void strokeLine(double x1, double y1, double x2, double y2) {
        gc.strokeLine(x1, y1, x2, y2);
    }

    /**
     * Stoke a line between two points
     */
    protected void strokeLine(Point2D p1, Point2D p2) {
        strokeLine(p1.getX(), p1.getY(), p2.getX(),
                p2.getY());
    }

    /**
     * Stroke text on the canvas
     */
    protected void strokeText(String text, double x, double y) {
        gc.strokeText(text, x, y);
    }

    /**
     * Set the stroke paint
     * 
     * @param paint the new paint
     */
    protected void setStroke(Paint paint) {
        gc.setStroke(paint);
    }

    /**
     * Draw stuff
     */
    public abstract void draw();
}
