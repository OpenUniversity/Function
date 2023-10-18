package function.arithmetics;

import function.Function;
import function.ValueNotInDomainException;

/**
 * Represents a binary operation that can be done on a pair of functions:
 * Addition, subtraction, multiplication, etc.
 * Supports pre-resolving the operation
 */
public abstract class FunctionArithmetic extends Function {

    protected Function left;
    protected Function right;
    private boolean isResolved;

    /**
     * Create a new arithmetic object representing left (action) right
     * 
     * @param left
     * @param right
     */
    public FunctionArithmetic(Function left, Function right) {
        this.left = left;
        this.right = right;
        try {
            this.left = this.tryResolve();
            isResolved = true;
        } catch (ArithmeticException e) {
            isResolved = false;
        }
    }

    /**
     * Try to directly perform the arithmetic on the pair of functions recived
     * 
     * @param left  the first function
     * @param right the second function
     * @return the result of the arithmetic, if successful
     * @throws ArithmeticException if could not resolve the arithmetic directly
     */
    protected Function tryResolve() throws ArithmeticException {
        throw new ArithmeticException("Operation cannot be resolved");
    };

    @Override
    public double evaluate(double x) throws ValueNotInDomainException {
        if (isResolved)
            return left.evaluate(x);
        return unresolvedEvaluate(x);
    }

    /**
     * Directly evaluate the value of the functions
     * 
     * @return the value of the function at x
     * @throws ValueNotInDomainException if value x is not in the function's domain
     */
    protected abstract double unresolvedEvaluate(double x) throws ValueNotInDomainException;

    @Override
    public Function derive() {
        if (isResolved)
            return left.derive();
        return unresolvedDerive();
    }

    /**
     * Directly derive the functions
     * 
     * @return the derivative function
     */
    protected abstract Function unresolvedDerive();

    @Override
    public String substitute(String x) {
        if (isResolved)
            return left.substitute(x);
        return unresolvedSubstitute(x);
    }

    /**
     * Directly substitute the value of x in the function
     * 
     * @return a string representation of the function
     */
    protected abstract String unresolvedSubstitute(String x);

    @Override
    public Function getScaledFunction() {
        if (isResolved)
            return left.getScaledFunction();
        return super.getScaledFunction();
    }

    @Override
    public double getScalar() {
        if (isResolved)
            return left.getScalar();
        return super.getScalar();
    }
}
