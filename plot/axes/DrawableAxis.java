package plot.axes;

import javafx.scene.canvas.GraphicsContext;
import plot.Drawable;

public abstract class DrawableAxis extends Axis implements Drawable {

    protected double location;

    public DrawableAxis(double pxSize, double minValue, double maxValue) {
        super(pxSize, minValue, maxValue);
        this.location = 0;
    }

    public abstract void drawAxis(GraphicsContext gc);

    public abstract void drawStep(GraphicsContext gc, int units);

    public void setLocation(double location) {
        this.location = location;
    }

    @Override
    public void draw(GraphicsContext gc) {
        drawAxis(gc);

        for (int units = unitsPerStep; units <= maxValue; units += unitsPerStep) {
            drawStep(gc, units);
        }
        for (int units = -unitsPerStep; units >= minValue; units -= unitsPerStep) {
            drawStep(gc, units);
        }
    }

}
