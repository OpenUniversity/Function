package function;

import java.util.ArrayList;

/**
 * Rperesents a linear combination of functions
 */
public class LinearCombination extends Function {

    private ArrayList<Double> coefficients;

    private ArrayList<Function> functions;

    /**
     * Constructs the 0 function
     */
    public LinearCombination() {
        this.coefficients = new ArrayList<>();
        this.functions = new ArrayList<>();
    }

    /**
     * Copy constructor for private use
     * 
     * @param coefficients
     * @param functions
     */
    private LinearCombination(LinearCombination other) {
        this();
        for (int i = 0; i < other.getSize(); i++) {
            coefficients.add(other.coefficients.get(i));
            functions.add(other.functions.get(i));
        }
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
        return functions.size();
    }

    @Override
    public double evaluate(double x) throws ArithmeticException {
        double value = 0;
        for (int i = 0; i < functions.size(); i++) {
            value += coefficients.get(i) * functions.get(i).evaluate(x);
        }
        return value;
    }

    @Override
    public Function derive() {
        LinearCombination derivative = new LinearCombination();
        for (int i = 0; i < getSize(); i++) {
            derivative.add(functions.get(i).derive(), coefficients.get(i));
        }
        return derivative;
    }

    @Override
    public String substitute(String x, boolean parenthesize) {
        boolean prefixWithPlus = false;
        String result = "";

        if (this.getSize() == 0)
            return "0";

        for (int i = 0; i < getSize(); i++) {
            result += substituteInTerm(x, prefixWithPlus, i);
            prefixWithPlus = true; // will evaluate to true after the first element
        }

        if (parenthesize)
            result = "(" + result + ")";

        return result;
    }

    private String substituteInTerm(String x, boolean prefixWithPlus, int i) {
        String result = "", substitution = "";
        double coefficient;
        // determine sign
        if (prefixWithPlus && coefficients.get(i) > 0)
            result += "+";
        else if (coefficients.get(i) < 0)
            result += "-";

        // determine coefficient string
        coefficient = Math.abs(coefficients.get(i));
        substitution = functions.get(i).substitute(x, false);
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
        sum.add(other, coefficient);
        return sum;
    }

    // recursive method to add stuff
    private void add(Function other, double coefficient) {
        // check if other function is a linear combination. If so, add each of its
        // elements separately
        if ((other instanceof LinearCombination)) {
            LinearCombination combination = (LinearCombination) other;
            for (int i = 0; i < combination.getSize(); i++) {
                add(combination.functions.get(i), coefficient * combination.coefficients.get(i));
            }
            return;
        }

        // check if function already exists, and add to its coefficient if it does
        for (int i = 0; i < getSize(); i++) {
            if (!(functions.get(i).equals(other)))
                continue;
            double newCoefficient = coefficients.get(i) + coefficient;
            if (newCoefficient == 0) {
                functions.remove(i);
                coefficients.remove(i);
            } else
                coefficients.set(i, newCoefficient);
            return;
        }

        // need to add new record
        if (coefficient != 0) {
            coefficients.add(coefficient);
            functions.add(other);
        }
    }

    @Override
    public Function times(Function other) {
        LinearCombination product = new LinearCombination();
        // check if other function is a linear combination. If so, add each of its
        // elements separately
        if (other instanceof LinearCombination) {
            // in such case, for instance (x+3)(x+4), this equates to (x+3)*x + (x+3)*4
            LinearCombination combination = (LinearCombination) other;
            for (int i = 0; i < combination.getSize(); i++) {
                product.add(this.times(combination.functions.get(i)), combination.coefficients.get(i));
            }
        } else {
            // this is the case of (x+3)*x, multiply each of this combination element's by
            // the other function
            for (int i = 0; i < getSize(); i++) {
                product.add(this.functions.get(i).times(other), this.coefficients.get(i));
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
        try {
            LinearCombination compareTo = Scale((Function) other, 1);
            for (int i = 0; i < getSize(); i++) {
                if (!functions.get(i).equals(compareTo.functions.get(i))
                        || coefficients.get(i) != compareTo.coefficients.get(i))
                    return false;
            }
            return true;
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

}
