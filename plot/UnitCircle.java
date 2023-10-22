package plot;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.geometry.Point2D;

public class UnitCircle extends CartesianAxesCurve {

    private List<Point2D> points;

    public UnitCircle(double width, double height) {
        super(width, height);
        points = new ArrayList<>();
        // Add Positive Points
        for (double x = -1; x <= 1; x += 0.01) {
            points.add(new Point2D(x, eval(x)));
        }
        // Add negative points
        for (double x = 1; x >= -1; x -= 0.01) {
            points.add(new Point2D(x, -eval(x)));
        }
    }

    @Override
    public void calculateBounds() {

    }

    @Override
    public Iterator<Point2D> getAbsolutePoints() {
        return points.iterator();
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
