package plot.curve;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

import javafx.geometry.Point2D;
import plot.axes.CartesianAxes;

/**
 * Represents evaluation that is done based on a rule
 */
public class EvaluationCurve extends CartesianAxesCurve {

    public static final double EVALUATION_INTERVAL = 0.01;

    private Function<Double, Double> eval;

    private double startX;
    private double endX;
    private double startY;
    private double endY;

    private List<Point2D> points;

    public EvaluationCurve(Function<Double, Double> eval, double startX, double endX, double width, double height) {
        super();
        this.eval = eval;
        this.startX = startX;
        this.endX = endX;
        this.startY = Double.MAX_VALUE;
        this.endY = Double.MIN_VALUE;
        this.points = new ArrayList<>();
        populatePoints(); // generates actual bounds
        this.axes = new CartesianAxes(this, width, height);
    }

    /**
     * Populates the points
     */
    protected void populatePoints() {
        double y;
        for (double x = startX; x <= endX; x += EVALUATION_INTERVAL) {
            try {
                y = eval.apply(x);
                startY = Math.min(startY, y);
                endY = Math.max(endY, y);
                points.add(new Point2D(x, y));
            } catch (ArithmeticException e) {
                points.add(null);
            }
        }
    }

    @Override
    public double getStartX() {
        return startX;
    }

    @Override
    public double getEndX() {
        return endX;
    }

    @Override
    public double getStartY() {
        return startY;
    }

    @Override
    public double getEndY() {
        return endY;
    }

    @Override
    public Iterator<Point2D> getAbsolutePoints() {
        return points.iterator();
    }

}
