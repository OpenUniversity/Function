import curve.CartesianAxesCanvas;
import function.Function;
import javafx.scene.paint.Color;

/**
 * Expands the curve canvas to directly add functions and their derivatives
 */
public class FunctionCanvas extends CartesianAxesCanvas {

    public FunctionCanvas(double width, double height) {
        super(width, height);
    }

    public void addFunction(Function func, double startX, double endX, Color color, int numOfDerivatives) {
        addCurve(new FunctionCurve(func, startX, endX, color));
        for (int i = 0; i < numOfDerivatives; i++) {
            color = color.brighter();
            func = func.derive();
            addCurve(new FunctionCurve(func, startX, endX, color));
        }
    }

    public void addFunction(Function func, double startX, double endX, Color color) {
        addFunction(func, startX, endX, color, 0);
    }

}
