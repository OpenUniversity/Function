package curve;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Point2D;

/**
 * Represents a canvas that can draw curves
 */
public class CurveCanvas extends CartesianAxesCanvas {

    protected List<Curve> curves;

    public CurveCanvas(double width, double height) {
        super(width, height);
        this.curves = new ArrayList<>();
    }

    public void addCurve(Curve curve) {
        curves.add(curve);
        for (Point2D point : curve) {
            expandXAxis(point.getX());
            expandYAxis(point.getY());
        }
    }

    private void strokeCurve(Curve curve) {
        setStroke(curve.getPaint());
        Point2D prev = null;
        for (Point2D curr : curve) {
            if (prev != null && curr != null)
                strokeLine(prev, curr);
            prev = curr;
        }
    }

    public void drawCurves() {
        for (Curve curve : curves) {
            strokeCurve(curve);
        }
    }

}
