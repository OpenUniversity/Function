package function;

import function.arithmetics.Power;

/**
 * Represents a function f(x)=1
 * DO NOT USE DIRECTLY! Always surround with a LinearCombination()
 */
public class Constant extends Function {

    public static Function of(double scalar) {
        return FunctionVector.scale(new Constant(), scalar);
    }

    // to prevent usage outside of this class
    private Constant() {
    }

    @Override
    public double evaluate(double x) throws ArithmeticException {
        return 1;
    }

    @Override
    public Function derive() {
        return new FunctionVector();
    }

    @Override
    public boolean shouldAddPatentheses() {
        return false;
    }

    @Override
    public String substitute(String x) {
        return "";
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof Constant;
    }

    @Override
    public Function times(Function other) {
        return other;
    }

    @Override
    public Function div(Function other) {
        return Power.inverse(other);
    }

    @Override
    public Function compose(Function inner) {
        return this;
    }

}
