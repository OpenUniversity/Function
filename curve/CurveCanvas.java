package curve;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Point2D;

/**
 * Represents a canvas that can draw curves
 */
public class CurveCanvas extends Canvas {

    protected List<Curve> curves;
    private boolean isDrawn;

    public CurveCanvas(double width, double height) {
        super(width, height);
        this.curves = new ArrayList<>();
        isDrawn = false;
    }

    public void addCurve(Curve curve) {
        if (isDrawn)
            clear();
        curves.add(curve);
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

    @Override
    public void draw() {
        isDrawn = true;
        for (Curve curve : curves) {
            strokeCurve(curve);
        }
    }

}
