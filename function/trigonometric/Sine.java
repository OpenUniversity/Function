package function.trigonometric;

import function.Function;
import function.FunctionVector;

/**
 * Represents a sine function.
 * This class extends the TrigFunction class and provides implementation for evaluating, deriving, composing, and integrating the sine function.
 */
public class Sine extends TrigFunction {

    /**
     * Evaluates the sine function for the given input.
     *
     * @param x the input value
     * @return the result of the sine function evaluation
     * @throws ArithmeticException if an arithmetic error occurs
     */
    @Override
    public double evaluate(double x) throws ArithmeticException {
        return Math.sin(x);
    }

    /**
     * Returns the derivative of the sine function.
     *
     * @return the derivative of the sine function
     */
    @Override
    public Function derive() {
        return new Cosine();
    }

    /**
     * Returns the name of the sine function.
     *
     * @return the name of the sine function
     */
    @Override
    public String getName() {
        return "sin";
    }

    /**
     * Composes the sine function with the given inner function.
     *
     * @param inner the inner function to compose with
     * @return the composed function
     */
    @Override
    public Function compose(Function inner) {
        // apply identity sin(-x)=-sinx
        if (FunctionVector.getScalar(inner) < 0)
            return FunctionVector.scale(super.compose(FunctionVector.scale(inner, -1)), -1);

        return super.compose(inner);
    }

    /**
     * Returns the integral of the sine function.
     *
     * @return the integral of the sine function
     */
    @Override
    public Function integrate() {
        return FunctionVector.scale(new Cosine(), -1);
    }

}
