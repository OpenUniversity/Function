package function.arithmetics;

import function.Function;
import function.ValueNotInDomainException;

/**
 * Represents a difference of two functions
 */
public class Difference extends FunctionArithmetic {

    /**
     * Generates a new difference function
     * 
     * @param left  one of the functions in the difference
     * @param right the second function in the difference
     */
    public Difference(Function left, Function right) {
        super(left, right);
    }

    @Override
    protected Function tryResolve() throws ArithmeticException {
        return left.minus(right);
    }

    @Override
    public double unresolvedEvaluate(double x) throws ValueNotInDomainException {
        return left.evaluate(x) - right.evaluate(x);
    }

    @Override
    public Function unresolvedDerive() {
        return new Difference(left.derive(), right.derive());
    }

    @Override
    public String unresolvedSubstitute(String x) {
        return "(" + left.substitute(x) + " - " + right.substitute(x) + ")";
    }
}
