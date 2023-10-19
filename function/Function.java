package function;

/**
 * Represents a function in java
 */
public abstract class Function {

    /**
     * Evaluate the function in a given value
     * 
     * @param x the point to evaluate at
     * @return the value of f(x)
     * @throws ArithmeticException if value not in domain
     */
    public abstract double evaluate(double x) throws ArithmeticException;

    /**
     * Derive the function
     * 
     * @return the function f'
     */
    public abstract Function derive();

    /**
     * Show a string representation of the function
     * 
     * @param x            the value to substitute x for
     * @param parenthesize is marked true if surrounding with parenthesis is
     *                     nessecary
     * @return a string representation of the function
     */
    public abstract String substitute(String x, boolean parenthesize);

    /**
     * Creates the sum of this function with another one
     * 
     * @param other the function to try to add
     * @return the sum function
     */
    public Function plus(Function other) {
        return new LinearCombination()
                .plus(this)
                .plus(other);
    }

    /**
     * Subtracts the other function from this one
     * 
     * @param other the function to subtract
     * @return the difference function
     */
    public Function minus(Function other) {
        return new LinearCombination()
                .plus(this)
                .minus(other);
    }

    /**
     * Multiplies this function by another one
     * 
     * @param other the function to multiply by
     * @return the product function
     */
    public Function times(Function other) {
        if (other instanceof Constant)
            return this;
        if (other instanceof LinearCombination)
            return other.times(this);
        throw new RuntimeException("Method unsupported");
    };

    /**
     * Checks if the function is equal to another
     * 
     * @param other the function to compare to
     * @return true if equal, false otherwise
     */
    public abstract boolean equals(Function other);

    @Override
    public String toString() {
        return this.substitute("x", false);
    }

}
