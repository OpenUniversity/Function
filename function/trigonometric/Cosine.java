package function.trigonometric;

import function.Function;
import function.FunctionVector;

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
        if (FunctionVector.getScalar(inner) < 0)
            return super.compose(FunctionVector.scale(inner, -1));

        return super.compose(inner);
    }

}
