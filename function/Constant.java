package function;

/**
 * Represents a function f(x)=1
 * DO NOT USE DIRECTLY! Always surround with a LinearCombination()
 */
public class Constant extends Function {

    public static Function of(double scalar) {
        return LinearCombination.Scale(new Constant(), scalar);
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
        return new LinearCombination();
    }

    @Override
    public String substitute(String x, boolean parenthesize) {
        return "";
    }

    @Override
    public boolean equals(Function other) {
        return other instanceof Constant;
    }

    @Override
    public Function times(Function other) {
        return other;
    }

}
