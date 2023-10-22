package plot.axes;

import javafx.scene.canvas.GraphicsContext;

public class YAxis extends DrawableAxis {

    public YAxis(double pxSize, double minValue, double maxValue) {
        super(pxSize, minValue, maxValue);
        this.originLocationPx = pxSize - this.originLocationPx;
    }

    @Override
    public double unitsToPx(double units) {
        return 2 * originLocationPx - super.unitsToPx(units);
    }

    @Override
    public void drawAxis(GraphicsContext gc) {
        gc.strokeLine(location, 0, location, pxSize);
    }

    @Override
    public void drawStep(GraphicsContext gc, int units) {
        double stepLocation = unitsToPx(units);
        double lineStart = location - MARK_SIZE_PX / 2;
        double lineEnd = location + MARK_SIZE_PX / 2;
        double textStart = location - 2 * MARK_SIZE_PX;
        if (textStart < 0)
            textStart = location + 3 * MARK_SIZE_PX / 2.0;
        gc.strokeLine(lineStart, stepLocation, lineEnd, stepLocation);
        gc.strokeText(String.valueOf(units), textStart, stepLocation + MARK_SIZE_PX / 3);
    }

}
