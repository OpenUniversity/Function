package function.trigonometric;

import function.Function;
import function.FunctionVector;

/**
 * Represents a sine function
 */
public class Sine extends TrigFunction {

    @Override
    public double evaluate(double x) throws ArithmeticException {
        return Math.sin(x);
    }

    @Override
    public Function derive() {
        return new Cosine();
    }

    @Override
    public String getName() {
        return "sin";
    }

    @Override
    public Function compose(Function inner) {

        // apply identity sin(-x)=-sinx
        if (FunctionVector.getScalar(inner) < 0)
            return FunctionVector.scale(super.compose(FunctionVector.scale(inner, -1)),
                    -1);

        return super.compose(inner);
    }

    @Override
    public Function integrate() {
        return FunctionVector.scale(new Cosine(), -1);
    }

}
