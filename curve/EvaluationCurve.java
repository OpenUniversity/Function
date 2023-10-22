package curve;

import java.util.function.Function;

import javafx.geometry.Point2D;

/**
 * Represents evaluation that is done based on a rule
 */
public class EvaluationCurve extends Curve {

    public static final double EVALUATION_INTERVAL = 0.01;

    public EvaluationCurve(Function<Double, Double> eval, double startX, double endX) {
        super();
        for (double x = startX; x <= endX; x += EVALUATION_INTERVAL) {
            try {
                points.add(new Point2D(x, eval.apply(x)));
            } catch (ArithmeticException e) {
                points.add(null);
            }
        }
    }

}
