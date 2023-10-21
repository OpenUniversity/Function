package function;

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
    public String substitute(String x) {
        return "";
    }

    @Override
    public String substitute(String x, boolean parenthesize) {
        // here, we override the default behavior, and return an empty string nontheless
        return substitute(x);
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
    public Function compose(Function inner) {
        return this;
    }

}
