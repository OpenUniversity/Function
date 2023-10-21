package function.trigonometric;

import function.Function;
import function.FunctionVector;

import utilities.vector.Scale;

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
        Scale<Function> scale = FunctionVector.getScale(inner);
        if (scale.getScalar() < 0)
            return FunctionVector.scale(super.compose(FunctionVector.scale(scale.getVector(), -1 * scale.getScalar())),
                    -1);

        return super.compose(inner);
    }

}
