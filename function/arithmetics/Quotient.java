package function.arithmetics;

import function.Function;
import function.elementary.PowerFunction;

/**
 * Represents a ratio of two functions
 */
public class Quotient extends FunctionArithmetic {

    public Quotient(Function left, Function right) {
        super(left, right);
    }

    @Override
    public double evaluate(double x) throws ArithmeticException {
        return left.evaluate(x) / right.evaluate(x);
    }

    @Override
    public Function derive() {
        // based on formula [f/g]'=(f'g-fg')/g^2
        Function numerator = left.derive().times(right).minus(left.times(right.derive()));
        Function denominator = PowerFunction.of(2).compose(right);
        return numerator.div(denominator);
    }

    @Override
    public String substitute(String x) {
        return left.substitute(x, true) + "/" + right.substitute(x, true);
    }

    @Override
    public Function times(Function other) {
        return left.times(other).div(right);
    }

    public Function div(Function other) {
        return left.div(right.times(other));
    }

}
