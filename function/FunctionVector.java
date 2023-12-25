package function;

import java.util.function.UnaryOperator;

import function.arithmetics.Quotient;
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

    /**
     * Adds a function to the linear combination with a specified coefficient
     * 
     * @param other       the function to add
     * @param coefficient its desired coefficient
     * @return the new sum
     */
    protected void add(Function other, double coefficient) {
        if (other instanceof FunctionVector) { // if other is also a linear combination, add its terms separately
            FunctionVector combination = (FunctionVector) other;
            terms.append(combination.terms, coefficient);
        } else
            terms.add(other, coefficient);
    }

    /**
     * Applies a linear transformation on the function
     */
    public FunctionVector transform(UnaryOperator<Function> transformation) {
        FunctionVector result = new FunctionVector();
        for (Scale<Function> term : terms) {
            result.add(transformation.apply(term.getVector()), term.getScalar());
        }
        return result;
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
        return transform((term) -> term.derive());
    }

    @Override
    public boolean shouldAddPatentheses() {
        return getSize() >= 1;
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

    @Override
    public Function times(Function other) {
        // if other factor is also a linear combination, we want to cross-add this.
        if (other instanceof FunctionVector)
            // in such case, for instance (x+3)(x+4), this equates to (x+3)*x + (x+3)*4
            return ((FunctionVector) other).transform((term) -> this.times(term));

        // this is the case of (x+3)*x, multiply each of this combination element's by
        // the other function
        return transform((term) -> term.times(other));

    }

    @Override
    public Function div(Function other) {
        if (getScalar(this) == 0)
            return new FunctionVector();
        Function superResult = super.div(other);
        if (!(superResult instanceof Quotient))
            return superResult;

        // if result is a fraction, try resolving
        FunctionVector quotient = new FunctionVector();
        Function current;
        for (Scale<Function> term : terms) {
            current = term.getVector().div(other);
            if (current instanceof Quotient)
                return superResult;
            quotient.add(current, term.getScalar());
        }
        return quotient;
    }

    @Override
    public Function compose(Function inner) {
        // composition of vector != vector of compositions!!!
        return transform(term -> term.compose(inner));
    }

    @Override
    public Function integrate() {
        return transform(term -> term.integrate());
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Function))
            return false;
        FunctionVector compareTo = scale((Function) other, 1);
        return this.terms.equals(compareTo.terms);
    }

}
