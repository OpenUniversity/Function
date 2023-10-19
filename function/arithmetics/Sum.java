package function.arithmetics;

import function.Function;
import function.ValueNotInDomainException;

/**
 * Represents a sum of two functions
 */
public class Sum extends FunctionArithmetic {

    /**
     * Generates a new sum function
     * 
     * @param left  one of the functions in the sum
     * @param right the second function in the sum
     */
    public Sum(Function left, Function right) {
        super(left, right);
    }

    @Override
    protected Function tryResolve() throws ArithmeticException {
        System.out.println(right.getScalar() + ";" + right.getScaledFunction().getClass() + ";" + right.toString());
        if (right.getScalar() < 0)
            return new Difference(left, right.negate());
        return left.plus(right);
    }

    @Override
    public double unresolvedEvaluate(double x) throws ValueNotInDomainException {
        return left.evaluate(x) + right.evaluate(x);
    }

    @Override
    public Function unresolvedDerive() {
        return new Sum(left.derive(), right.derive());
    }

    @Override
    public String unresolvedSubstitute(String x) {
        return "(" + left.substitute(x) + " + " + right.substitute(x) + ")";
    }

}
