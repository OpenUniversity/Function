package function;

import function.arithmetics.Compose;
import function.arithmetics.Product;
import function.arithmetics.Quotient;
import function.elementary.PowerFunction;

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
     * @param x the value to substitute x for
     * @return a string representation of the function
     */
    public abstract String substitute(String x);

    /**
     * Show a string representation of the function
     * 
     * @param x            the value to substitute x for
     * @param parenthesize is marked true if surrounding with parenthesis is
     *                     nessecary
     * @return a string representation of the function
     */
    public String substitute(String x, boolean parenthesize) {
        String result = this.substitute(x);
        if (parenthesize)
            result = "(" + result + ")";
        return result;
    }

    /**
     * Creates the sum of this function with another one
     * 
     * @param other the function to try to add
     * @return the sum function
     */
    public Function plus(Function other) {
        return new FunctionVector()
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
        return new FunctionVector()
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
        if (other instanceof FunctionVector || other instanceof Product || other instanceof Quotient)
            return other.times(this);
        return new Product(this, other);
    };

    /**
     * Divided this function by another one
     * 
     * @param other the function to divide by
     * @return the quotient function
     */
    public Function div(Function other) {
        if (other instanceof Constant)
            return this;
        if (other instanceof Product) {
            Product prod = (Product) other;
            return this.div(prod.getLeft()).div(prod.getRight());
        }
        if (other instanceof Quotient) {
            Quotient quo = (Quotient) other;
            return this.times(quo.getRight()).div(quo.getLeft());
        }
        return new Quotient(this, other);
    };

    /**
     * Composes this function with another one
     * For instance, (3x+1)^5 is a composition of x^5 (the outer function) with 3x+1
     * (the inner function)
     * 
     * @param inner the inner function
     * @return the composed function
     */
    public Function compose(Function inner) {
        if (inner.equals(PowerFunction.IDENTITY))
            return this;
        if (inner instanceof Constant)
            return Constant.of(evaluate(inner.evaluate(0))); // will just give us the value at 1
        if (inner instanceof Compose) { // function composition is associative
            Compose comp = (Compose) inner;
            return this.compose(comp.getLeft()).compose(comp.getRight());
        }
        return new Compose(this, inner);
    };

    /**
     * Checks if the function is equal to another
     * 
     * @param other the function to compare to
     * @return true if equal, false otherwise
     */
    public abstract boolean equals(Object obj);

    @Override
    public String toString() {
        return this.substitute("x", false);
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }
}
