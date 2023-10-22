package plot.curve;

import java.util.function.Function;

import javafx.geometry.Point2D;
import range.Range;

/**
 * Represents evaluation that is done based on a rule
 */
public class EvaluationCurve extends Curve {

    public static final double EVALUATION_INTERVAL = 0.01;

    public EvaluationCurve(Function<Double, Double> eval, double startX, double endX) {
        super();
        this.xRange = new Range(startX, endX);
        double y;
        for (double x = startX; x <= endX; x += EVALUATION_INTERVAL) {
            try {
                y = eval.apply(x);
                this.yRange.expandStart(y);
                this.yRange.expandEnd(y);
                points.add(new Point2D(x, y));
            } catch (ArithmeticException e) {
                points.add(null);
            }
        }
    }

}
