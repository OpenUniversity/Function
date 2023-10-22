package plot.curve;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

/**
 * Represents a canvas that can draw curves
 */
public class CurveCanvas extends Canvas {

    protected List<Curve> curves;
    private GraphicsContext gc;

    public CurveCanvas(double width, double height) {
        super(width, height);
        this.curves = new ArrayList<>();
        this.gc = getGraphicsContext2D();
    }

    public void addCurve(Curve curve) {
        curves.add(curve);
    }

    public boolean removeCurve(Curve curve) {
        return curves.remove(curve);
    }

    protected void strokeLine(Point2D p1, Point2D p2) {
        gc.strokeLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
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

    public void strokeCurves() {
        clear();
        for (Curve curve : curves) {
            strokeCurve(curve);
        }
    }

    public void clear() {
        gc.clearRect(0, 0, getWidth(), getHeight());
    }

}
