package function;

import utilities.vector.ArrayListVector;
import utilities.vector.Scale;
import utilities.vector.Vector;

/**
 * Rperesents a linear combination of functions.
 * Functions are vectors!
 * 
 * @version 2
 */
public class LinearCombination extends Function {

    private Vector<Function> terms;

    /**
     * Constructs the 0 function
     */
    public LinearCombination() {
        terms = new ArrayListVector<>();
    }

    /**
     * Copy constructor for private use
     * 
     * @param coefficients
     * @param functions
     */
    private LinearCombination(LinearCombination other) {
        this();
        terms.append(other.terms, 1);
    }

    public static LinearCombination Scale(Function func, double scalar) {
        return (LinearCombination) new LinearCombination().plus(func, scalar);
    }

    /**
     * Returns the number of functions in the sum
     * 
     * @return the size of the linear combination
     */
    public int getSize() {
        return terms.dimensions();
    }

    @Override
    public double evaluate(double x) throws ArithmeticException {
        double value = 0;
        for (Scale<Function> term : terms) {
            value += term.getScalar() * term.getVector().evaluate(x);
        }
        return value;
    }

    @Override
    public Function derive() {
        LinearCombination derivative = new LinearCombination();
        for (Scale<Function> term : terms) {
            derivative = (LinearCombination) derivative.plus(term.getVector().derive(), term.getScalar());
        }
        return derivative;
    }

    @Override
    public String substitute(String x, boolean parenthesize) {
        boolean prefixWithPlus = false;
        String result = "";

        if (this.getSize() == 0)
            return "0";

        for (Scale<Function> term : terms) {
            result += substituteInTerm(prefixWithPlus, term.getScalar(), term.getVector().substitute(x, false));
            prefixWithPlus = true; // will evaluate to true after the first element
        }

        if (parenthesize)
            result = "(" + result + ")";

        return result;
    }

    private String substituteInTerm(boolean prefixWithPlus, double scalar, String substitution) {
        String result = "";
        double coefficient;
        // determine sign
        if (prefixWithPlus && scalar > 0)
            result += "+";
        else if (scalar < 0)
            result += "-";

        // determine coefficient string
        coefficient = Math.abs(scalar);
        if (coefficient != 1 || substitution.isEmpty())
            result += coefficient;

        result += substitution;
        return result;
    }

    @Override
    public Function plus(Function other) {
        return this.plus(other, 1);
    }

    @Override
    public Function minus(Function other) {
        return this.plus(other, -1);
    }

    /**
     * Adds a function to the linear combination with a specified coefficient
     * 
     * @param other       the function to add
     * @param coefficient its desired coefficient
     * @return the new sum
     */
    public Function plus(Function other, double coefficient) {
        LinearCombination sum = new LinearCombination(this);
        if (other instanceof LinearCombination) { // if other is also a linear combination, add its terms separately
            LinearCombination combination = (LinearCombination) other;
            sum.terms.append(combination.terms, coefficient);
        } else
            sum.terms.add(other, coefficient);
        return sum;
    }

    @Override
    public Function times(Function other) {
        LinearCombination product = new LinearCombination();
        // if other factor is also a linear combination, we want to cross-add this.
        if (other instanceof LinearCombination) {
            // in such case, for instance (x+3)(x+4), this equates to (x+3)*x + (x+3)*4
            LinearCombination combination = (LinearCombination) other;
            for (Scale<Function> term : combination.terms) {
                product.terms.append(((LinearCombination) this.times(term.getVector())).terms, term.getScalar());
            }
        } else {
            // this is the case of (x+3)*x, multiply each of this combination element's by
            // the other function
            for (Scale<Function> term : terms) {
                product.terms.add(term.getVector().times(other), term.getScalar());
            }
        }
        return product;
    }

    @Override
    public Function div(Function other) {
        // TODO: Implement function division

        return super.div(other);
    }

    @Override
    public Function compose(Function inner) {
        // TODO: Implement composition
        return super.compose(inner);
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Function))
            return false;
        LinearCombination compareTo = Scale((Function) other, 1);
        return this.terms.equals(compareTo.terms);
    }

}
