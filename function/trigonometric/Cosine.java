package function.trigonometric;

import function.Function;
import function.FunctionVector;
import utilities.vector.Scale;

public class Cosine extends TrigFunction {

    @Override
    public double evaluate(double x) throws ArithmeticException {
        return Math.cos(x);
    }

    @Override
    public Function derive() {
        return FunctionVector.scale(new Sine(), -1);
    }

    @Override
    public String getName() {
        return "cos";
    }

    @Override
    public Function compose(Function inner) {

        // apply identity cos(-x)=cos x
        Scale<Function> scale = FunctionVector.getScale(inner);
        if (scale.getScalar() < 0)
            return super.compose(FunctionVector.scale(scale.getVector(), -1 * scale.getScalar()));

        return super.compose(inner);
    }

}
