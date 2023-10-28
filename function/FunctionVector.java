package function;

import vector.ArrayListVector;
import vector.Scale;
import vector.Vector;

/**
 * Rperesents a linear combination of functions.
 * Functions are vectors!
 * 
 * @version 2
 */
public class FunctionVector extends Function {

    private Vector<Function> terms;

    /**
     * Constructs the 0 function
     */
    public FunctionVector() {
        terms = new ArrayListVector<>();
    }

    /**
     * Copy constructor for private use
     */
    private FunctionVector(FunctionVector other) {
        this();
        terms.append(other.terms, 1);
    }

    public static FunctionVector scale(Function func, double scalar) {
        FunctionVector vec = new FunctionVector();
        vec.add(func, scalar);
        return vec;
    }

    public static double getScalar(Function func) {
        if (!(func instanceof FunctionVector))
            return 1;
        FunctionVector vec = (FunctionVector) func;
        if (vec.getSize() > 1)
            return 1;
        for (Scale<Function> term : vec.terms) { // remember there is only one
            return term.getScalar();
        }
        return 0;
    }

    public static Function getScaledFunc(Function func) {
        if (getScalar(func) == 1)
            throw new IllegalArgumentException("Cannot de-scale a vector with scalar 1");
        for (Scale<Function> term : ((FunctionVector) func).terms) { // remember there is only one
            return term.getVector();
        }
        return new FunctionVector(); // should not get here
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
        FunctionVector derivative = new FunctionVector();
        for (Scale<Function> term : terms) {
            derivative.add(term.getVector().derive(), term.getScalar());
        }
        return derivative;
    }

    @Override
    public String substitute(String x) {
        boolean prefixWithPlus = false;
        String result = "";

        if (this.getSize() == 0)
            return "0";

        for (Scale<Function> term : terms) {
            result += substituteInTerm(prefixWithPlus, term.getScalar(), term.getVector().substitute(x, false));
            prefixWithPlus = true; // will evaluate to true after the first element
        }

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
        FunctionVector sum = new FunctionVector(this);
        sum.add(other, 1);
        return sum;
    }

    @Override
    public Function minus(Function other) {
        FunctionVector diff = new FunctionVector(this);
        diff.add(other, -1);
        return diff;
    }

    /**
     * Adds a function to the linear combination with a specified coefficient
     * 
     * @param other       the function to add
     * @param coefficient its desired coefficient
     * @return the new sum
     */
    private void add(Function other, double coefficient) {
        if (other instanceof FunctionVector) { // if other is also a linear combination, add its terms separately
            FunctionVector combination = (FunctionVector) other;
            terms.append(combination.terms, coefficient);
        } else
            terms.add(other, coefficient);
    }

    @Override
    public Function times(Function other) {
        FunctionVector product = new FunctionVector();
        // if other factor is also a linear combination, we want to cross-add this.
        if (other instanceof FunctionVector) {
            // in such case, for instance (x+3)(x+4), this equates to (x+3)*x + (x+3)*4
            FunctionVector combination = (FunctionVector) other;
            for (Scale<Function> term : combination.terms) {
                product.add(this.times(term.getVector()), term.getScalar());
            }
        } else {
            // this is the case of (x+3)*x, multiply each of this combination element's by
            // the other function
            for (Scale<Function> term : terms) {
                product.add(term.getVector().times(other), term.getScalar());
            }
        }
        return product;
    }

    @Override
    public Function div(Function other) {
        if (getScalar(this) == 0)
            return new FunctionVector();

        return super.div(other);
    }

    @Override
    public Function compose(Function inner) {
        // composition of vector != vector of compositions!!!
        FunctionVector composition = new FunctionVector();
        for (Scale<Function> term : terms) {
            composition.add(term.getVector().compose(inner), term.getScalar());
        }
        return composition;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Function))
            return false;
        FunctionVector compareTo = scale((Function) other, 1);
        return this.terms.equals(compareTo.terms);
    }

}
