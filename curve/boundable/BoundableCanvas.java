package curve.boundable;

import axis.Axis;
import curve.CartesianAxesCanvas;
import curve.Curve;
import javafx.event.EventHandler;
import javafx.scene.input.ScrollEvent;

/**
 * Represents a cartesian axes canvas that supports zooming
 */
public class BoundableCanvas extends CartesianAxesCanvas implements EventHandler<ScrollEvent> {

    public static final double ZOOM_SENSITIVITY = 1.01; // this is the zoom factor when scrolling one pixel

    public BoundableCanvas(double width, double height) {
        super(width, height);
        setOnScroll(this);
    }

    @Override
    public void addCurve(Curve curve) {
        if (!(curve instanceof Boundable))
            throw new UnsupportedOperationException("Cannot add non-boundable curve to a boundable canvas");
        super.addCurve(curve);
    }

    private void zoomAxis(Axis axis, double fixedPx, double zoomFactor) {
        if (fixedPx < 0 || fixedPx > axis.getPxSize())
            throw new IllegalArgumentException("fixed point must be inside axis");
        double edgeWeight = 1 / zoomFactor;
        double fixedWeight = 1 - edgeWeight;
        double fixedUnits = axis.getUnitsOfPixels(fixedPx);
        double newStart = fixedWeight * fixedUnits + edgeWeight * axis.getStart();
        double newEnd = fixedWeight * fixedUnits + edgeWeight * axis.getEnd();
        axis.setRange(newStart, newEnd);
    }

    @Override
    public void handle(ScrollEvent event) {
        if (!isDrawn())
            return;
        clear();
        double x = event.getX();
        double y = getHeight() - event.getY();
        double zoomFactor = Math.pow(ZOOM_SENSITIVITY, event.getDeltaY());
        // if we zoom by x2, each unit will double its pixel range
        zoomAxis(xAxis, x, zoomFactor);
        zoomAxis(yAxis, y, zoomFactor);

        for (Curve curve : curves) {
            ((Boundable) curve).setBounds(xAxis.getStart(), xAxis.getEnd());
        }
        draw();
    }

}
