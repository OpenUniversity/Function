package function;

import function.arithmetics.Scale;

/**
 * Represents a function in java
 */
public abstract class Function {

    /**
     * Return a constant that the function is multiplied by
     * 
     * @return the constant
     */
    public double getScalar() {
        return 1;
    }

    /**
     * Return the result of this / getScalar()
     * 
     * @return the function that is scaled
     */
    public Function getScaledFunction() {
        return this;
    }

    /**
     * Evaluate the function in a given value
     * 
     * @param x the point to evaluate at
     * @return the value of f(x)
     * @throws ValueNotInDomainException if value not in domain
     */
    public abstract double evaluate(double x) throws ValueNotInDomainException;

    /**
     * Derive the function
     * 
     * @return the function f'
     */
    public abstract Function derive();

    /**
     * Show a string representation of the function
     * 
     * @param x the value to substitute x for
     * @return a string representation of the function
     */
    public abstract String substitute(String x);

    /**
     * Calculates the negative of this function
     * 
     * @return the negated function
     */
    public Function negate() {
        return new Scale(-1, this);
    }

    /**
     * Creates the sum of this function with another one
     * 
     * @param other the function to try to add
     * @return the sum function
     * @throws ArithmeticException if adding the two functions doesn't generate a
     *                             simpler response
     */
    public Function plus(Function other) throws ArithmeticException {
        if (this.getScaledFunction().equals(other.getScaledFunction()))
            return new Scale(this.getScalar() + other.getScalar(), this.getScaledFunction());
        if (other.getScalar() == 0)
            return this;
        throw new ArithmeticException();
    }

    /**
     * Subtracts the other function from this one
     * 
     * @param other the function to subtract
     * @return the difference function
     * @throws ArithmeticException if functions cannot be subtarcted
     */
    public Function minus(Function other) throws ArithmeticException {
        return this.plus(other.negate());
    }

    @Override
    public String toString() {
        return this.substitute("x");
    }

}
