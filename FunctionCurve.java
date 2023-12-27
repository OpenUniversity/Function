/**
 * This class represents a curve in a mathematical function.
 */
import curve.Curve;
import curve.boundable.Boundable;
import function.Function;
import javafx.geometry.Point2D;
import javafx.scene.paint.Paint;

/**
 * Represents evaluation that is done based on a rule
 */
public class FunctionCurve extends Curve implements Boundable {

    public static final double EVALUATION_INTERVAL = 0.01;

    public static final double JUMP_TRESHOLD = 2000; // the maximal amount of units that can be jumped in one x inteval

    private Function function;

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
        this.function = func;
        evaluate(startX, endX);
    }

    @Override
    public void setBounds(double startX, double endX) {
        clear();
        evaluate(startX, endX);
    }

    private void evaluate(double startX, double endX) {
        Function derivative = function.derive();
        double y, dy;
        for (double x = startX; x <= endX; x += EVALUATION_INTERVAL) {
            try {
                y = function.evaluate(x);
                dy = derivative.evaluate(x) * EVALUATION_INTERVAL;
                if (Math.abs(dy) >= JUMP_TRESHOLD)
                    throw new ArithmeticException("Jump is too big!");
                points.add(new Point2D(x, y));
            } catch (ArithmeticException e) {
                points.add(null);
            }
        }
    }
}