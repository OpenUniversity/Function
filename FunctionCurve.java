import curve.Curve;
import function.Function;
import javafx.geometry.Point2D;
import javafx.scene.paint.Paint;

/**
 * Represents evaluation that is done based on a rule
 */
public class FunctionCurve extends Curve {

    public static final double EVALUATION_INTERVAL = 0.01;

    /**
     * Constructs a new function path
     * 
     * @param func   the function to draw
     * @param startX the start value of the function
     * @param endX   the end value of the function *
     * @param paint  the painting of the curve
     */
    public FunctionCurve(Function func, double startX, double endX, Paint paint) {
        super(paint);
        for (double x = startX; x <= endX; x += EVALUATION_INTERVAL) {
            try {
                points.add(new Point2D(x, func.evaluate(x)));
            } catch (ArithmeticException e) {
                points.add(null);
            }
        }
    }
}