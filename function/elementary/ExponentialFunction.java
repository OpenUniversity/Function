/**
 * This package contains classes for elementary mathematical functions.
 */
package function.elementary;

import function.Constant;
import function.Function;
import function.Identity;
import function.arithmetics.Power;

/**
 * Represents the function e to the power of f(x)
 * Represents an exponential function of the form e^x.
 */
public class ExponentialFunction extends Power {

    /**
     * Constructs a new ExponentialFunction object.
     * The base of the exponential function is the mathematical constant e (Euler's number),
     * and the exponent is the identity function.
     */
    public ExponentialFunction() {
        super(Constant.of(Math.E), new Identity());
    }

    /**
     * Substitutes the given value into the exponential function.
     *
     * @param x the value to substitute
     * @return the result of substituting the value into the exponential function
     */
    @Override
    public String substitute(String x) {
        return "e^" + x;
    }

    /**
     * Returns the integral of the exponential function.
     * Since the integral of e^x is e^x, the method returns the current exponential function itself.
     *
     * @return the integral of the exponential function
     */
    @Override
    public Function integrate() {
        return this;
    }

}
