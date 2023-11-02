package curve;

import axis.SteppedAxis;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * Represents a canvas with a cartesian axes
 */
public class CartesianAxesCanvas extends CurveCanvas {

    public static final int MARK_SIZE_PX = 10;
    public static final Paint AXES_COLOR = Color.BLACK;

    private SteppedAxis xAxis;
    private SteppedAxis yAxis;

    public CartesianAxesCanvas(double width, double height) {
        super(width, height);
        xAxis = new SteppedAxis(width);
        yAxis = new SteppedAxis(height);
    }

    @Override
    public void addCurve(Curve curve) {
        super.addCurve(curve);
        for (Point2D point : curve) {
            if (point == null)
                continue;
            xAxis.expandTo(point.getX());
            yAxis.expandTo(point.getY());
        }
    }

    private Point2D toCanvasPoint(Point2D point) {
        return new Point2D(xAxis.getPixelsOfUnits(point.getX()), getHeight() - yAxis.getPixelsOfUnits(point.getY()));
    }

    @Override
    protected void strokeLine(Point2D p1, Point2D p2) {
        Point2D canvasP1 = toCanvasPoint(p1);
        Point2D canvasP2 = toCanvasPoint(p2);
        super.strokeLine(canvasP1, canvasP2);
    }

    @Override
    public void draw() {
        super.draw();
        setStroke(AXES_COLOR);
        double width = getWidth(), height = getHeight();
        double originX = xAxis.getOriginLocation(), originY = height - yAxis.getOriginLocation();
        // draw x axis
        super.strokeLine(0, originY, width, originY);
        // draw y axis
        super.strokeLine(originX, 0, originX, height);

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
            super.strokeLine(stepLocation, stepStart, stepLocation, stepStop);
            super.strokeText(String.valueOf(xStep), stepLabelX, stepLabelY);
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
            super.strokeLine(stepStart, stepLocation, stepStop, stepLocation);
            super.strokeText(String.valueOf(yStep), stepLabelX, stepLabelY);
        }
    }

}
