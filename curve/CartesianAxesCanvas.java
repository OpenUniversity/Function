package curve;

import axis.SteppedAxis;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

/**
 * Represents a canvas with a cartesian axes
 */
public class CartesianAxesCanvas extends Canvas {

    public static final int MARK_SIZE_PX = 10;

    private SteppedAxis xAxis;
    private SteppedAxis yAxis;
    private GraphicsContext gc; // we manually take control of the Graphics context here

    public CartesianAxesCanvas(double width, double height) {
        super(width, height);
        xAxis = new SteppedAxis(width);
        yAxis = new SteppedAxis(height);
        gc = super.getGraphicsContext2D();
    }

    @Override
    public GraphicsContext getGraphicsContext2D() {
        return null; // don't allow drawing outside of this object
    }

    public void expandXAxis(double x) {
        xAxis.expandTo(x);
    }

    public void expandYAxis(double y) {
        yAxis.expandTo(y);
    }

    public void strokeLine(Point2D p1, Point2D p2) {
        Point2D canvasP1 = toCanvasPoint(p1);
        Point2D canvasP2 = toCanvasPoint(p2);
        gc.strokeLine(canvasP1.getX(), canvasP1.getY(), canvasP2.getX(),
                canvasP2.getY());
    }

    public void setStroke(Paint paint) {
        gc.setStroke(paint);
    }

    private Point2D toCanvasPoint(Point2D point) {
        return new Point2D(xAxis.getPixelsOfUnits(point.getX()), getHeight() - yAxis.getPixelsOfUnits(point.getY()));
    }

    public void drawAxes() {
        double width = getWidth(), height = getHeight();
        double originX = xAxis.getOriginLocation(), originY = height - yAxis.getOriginLocation();
        // draw x axis
        gc.strokeLine(0, originY, width, originY);
        // draw y axis
        gc.strokeLine(originX, 0, originX, height);

        // draw steps and labels
        double stepLocation, stepStart, stepStop;
        double stepLabelX, stepLabelY;

        // draw x axis steps
        stepStart = originY + MARK_SIZE_PX / 2;
        stepStop = originY - MARK_SIZE_PX / 2;
        stepLabelY = originY + 2 * MARK_SIZE_PX;
        if (stepLabelY > height)
            stepLabelY = originY - 3 * MARK_SIZE_PX / 2.0;
        for (int xStep : xAxis.getSteps()) {
            stepLocation = xAxis.getPixelsOfUnits(xStep);
            stepLabelX = stepLocation - MARK_SIZE_PX / 3;
            gc.strokeLine(stepLocation, stepStart, stepLocation, stepStop);
            gc.strokeText(String.valueOf(xStep), stepLabelX, stepLabelY);
        }

        // draw y axis steps
        stepStart = originX + MARK_SIZE_PX / 2;
        stepStop = originX - MARK_SIZE_PX / 2;
        stepLabelX = originX - 2 * MARK_SIZE_PX;
        if (stepLabelX < 0)
            stepLabelX = originX + 3 * MARK_SIZE_PX / 2.0;
        for (int yStep : yAxis.getSteps()) {
            stepLocation = height - yAxis.getPixelsOfUnits(yStep);
            stepLabelY = stepLocation + MARK_SIZE_PX / 3;
            gc.strokeLine(stepStart, stepLocation, stepStop, stepLocation);
            gc.strokeText(String.valueOf(yStep), stepLabelX, stepLabelY);
        }
    }

}
