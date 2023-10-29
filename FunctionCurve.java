import curve.Curve;
import function.Function;
import javafx.geometry.Point2D;
import javafx.scene.paint.Paint;

/**
 * Represents evaluation that is done based on a rule
 */
public class FunctionCurve extends Curve {

    public static final double EVALUATION_INTERVAL = 0.01;

    public static final double JUMP_TRESHOLD = 2000; // the maximal amount of units that can be jumped in one x inteval

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
        Function derivative = func.derive();
        double y, dy;
        for (double x = startX; x <= endX; x += EVALUATION_INTERVAL) {
            try {
                y = func.evaluate(x);
                dy = derivative.evaluate(x);
                if (Math.abs(dy * EVALUATION_INTERVAL) >= JUMP_TRESHOLD)
                    throw new ArithmeticException("Jump is too big!");
                points.add(new Point2D(x, y));
            } catch (ArithmeticException e) {
                points.add(null);
            }
        }
    }
}