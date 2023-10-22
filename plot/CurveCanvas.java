package plot;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Point2D;
import plot.curve.Curve;

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
        expandBounds(curve);
    }

    public void removeCurve(Curve curve) {
        if (curves.remove(curve)) {
            resetBounds();
            for (Curve c : curves) {
                expandBounds(c);
            }
        }
    }

    protected void strokeCurve(Curve curve) {
        gc.setFill(curve.getPaint());
        Point2D prev = null;
        for (Point2D curr : curve) {
            if (prev != null && curr != null)
                strokeLine(prev, curr);
            prev = curr;
        }
    }

    @Override
    protected void drawInBounds() {
        super.drawInBounds();
        for (Curve curve : curves) {
            strokeCurve(curve);
        }
    }

}
