package plot;

import javafx.geometry.Point2D;
import plot.curve.Curve;

public class UnitCircle extends Curve {

    public UnitCircle() {
        super();
        // Add Positive Points
        for (double x = -1; x <= 1; x += 0.01) {
            points.add(new Point2D(x, eval(x)));
        }
        // Add negative points
        for (double x = 1; x >= -1; x -= 0.01) {
            points.add(new Point2D(x, -eval(x)));
        }
    }

    private double eval(double x) {
        return Math.sqrt(1 - Math.pow(x, 2));
    }

    @Override
    public double getStartX() {
        return -1;
    }

    @Override
    public double getEndX() {
        return 1;
    }

    @Override
    public double getStartY() {
        return -1;
    }

    @Override
    public double getEndY() {
        return 1;
    }

}
