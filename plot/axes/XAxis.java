package plot.axes;

import javafx.scene.canvas.GraphicsContext;

public class XAxis extends DrawableAxis {

    public XAxis(double pxSize, double minValue, double maxValue) {
        super(pxSize, minValue, maxValue);
    }

    @Override
    public void drawAxis(GraphicsContext gc) {
        gc.strokeLine(0, location, pxSize, location);
    }

    @Override
    public void drawStep(GraphicsContext gc, int units) {
        double stepLocation = unitsToPx(units);
        double lineMaxHeight = location + MARK_SIZE_PX / 2;
        double lineMinHeight = location - MARK_SIZE_PX / 2;
        double textHeight = location + 2 * MARK_SIZE_PX;
        gc.strokeLine(stepLocation, lineMaxHeight, stepLocation, lineMinHeight);
        gc.strokeText(String.valueOf(units), stepLocation - MARK_SIZE_PX / 3, textHeight);
    }

}
