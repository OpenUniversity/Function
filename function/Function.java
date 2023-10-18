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

    @Override
    public String toString() {
        return this.substitute("x");
    }

}
