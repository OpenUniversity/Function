/**
 * This class represents a constant value used in mathematical functions.
 */
package function;

import function.arithmetics.Power;

/**
 * Represents a function f(x)=1 (a constant function that always returns the value 1).
 * DO NOT USE DIRECTLY! Always surround with a LinearCombination()
 */
/ public class Constant extends Function {

    /**
     * Creates a new Constant function with the specified scalar value.
     * 
     * @param scalar The scalar value to multiply the Constant function by.
     * @return A new Function object representing the scaled Constant function.
     */
    public static Function of(double scalar) {
        return FunctionVector.scale(new Constant(), scalar);
    }

    /**
     * Private constructor to prevent usage outside of this class.
     */
    private Constant() {
    }

    /**
     * Evaluates the Constant function at the specified value of x.
     * 
     * @param x The value of x at which to evaluate the function.
     * @return The value of the Constant function, which is always 1.
     * @throws ArithmeticException if an arithmetic error occurs during evaluation.
     */
    @Override
    public double evaluate(double x) throws ArithmeticException {
        return 1;
    }

    /**
     * Returns the derivative of the Constant function.
     * 
     * @return A new Function object representing the derivative of the Constant function, which is always 0.
     */
    @Override
    public Function derive() {
        return new FunctionVector();
    }

    /**
     * Indicates whether parentheses should be added when printing the Constant function.
     * 
     * @return false, indicating that parentheses should not be added.
     */
    @Override
    public boolean shouldAddPatentheses() {
        return false;
    }

    /**
     * Substitutes the value of x in the Constant function.
     * 
     * @param x The value to substitute in the function.
     * @return An empty string, as the Constant function does not depend on the value of x.
     */
    @Override
    public String substitute(String x) {
        return "";
    }

    /**
     * Checks if the Constant function is equal to another object.
     * 
     * @param other The object to compare with the Constant function.
     * @return true if the other object is an instance of Constant, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        return other instanceof Constant;
    }

    /**
     * Multiplies the Constant function by another function.
     * 
     * @param other The function to multiply with the Constant function.
     * @return The other function, as multiplying by the Constant function does not change the value.
     */
    @Override
    public Function times(Function other) {
        return other;
    }

    /**
     * Divides the Constant function by another function.
     * 
     * @param other The function to divide the Constant function by.
     * @return The inverse of the other function, as dividing by the Constant function is equivalent to multiplying by its inverse.
     */
    @Override
    public Function div(Function other) {
        return Power.inverse(other);
    }

    /**
     * Composes the Constant function with another function.
     * 
     * @param inner The function to compose with the Constant function.
     * @return The Constant function itself, as composing with the Constant function does not change the value.
     */
    @Override
    public Function compose(Function inner) {
        return this;
    }

    /**
     * Returns the integral of the Constant function.
     * 
     * @return A new Function object representing the integral of the Constant function, which is the Identity function.
     */
    @Override
    public Function integrate() {
        return new Identity();
    }

}
