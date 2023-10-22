package plot;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.geometry.Point2D;
import plot.axes.CartesianAxes;

public class UnitCircle extends CatesianAxesCurve {

    private List<Point2D> points;

    public UnitCircle(CartesianAxes axes) {
        super(axes);
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
    public Iterator<Point2D> getAbsolutePoints() {
        return points.iterator();
    }

    private double eval(double x) {
        return Math.sqrt(1 - Math.pow(x, 2));
    }

}
