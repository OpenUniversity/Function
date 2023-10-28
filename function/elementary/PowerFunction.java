package function.elementary;

import function.Constant;
import function.Function;
import function.FunctionVector;
import function.Identity;
import function.arithmetics.Power;

/**
 * Represents a function in the form of x^power, where power is a real number
 */
public class PowerFunction extends Power {

    /**
     * Generate a new power function
     * 
     * @param power the exponent of the function
     */
    public PowerFunction(double power) {
        super(new Identity(), Constant.of(power));
        if (power == 0 || power == 1)
            throw new ArithmeticException("Can't create constant or identity as power function");
    }

    public static Function of(double power) {
        if (power == 0)
            return Constant.of(1);
        if (power == 1)
            return new Identity();
        return new PowerFunction(power);
    }

    public double getPower() {
        return FunctionVector.getScalar(right);
    }

    @Override
    public String substitute(String x) {
        if (getPower() < 0) {
            Function denominator = new PowerFunction(Math.abs(getPower()));
            return "1 / " + denominator.substitute(x);
        }
        if (getPower() == 1)
            return x;
        return x + "^" + getPower();
    }

    /*
     * @Override
     * public Function times(Function other) {
     * 
     * if (other instanceof PowerFunction) {
     * double otherPower = ((PowerFunction) other).power;
     * return PowerFunction.of(power + otherPower);
     * }
     * 
     * return super.times(other);
     * }
     * 
     * @Override
     * public Function div(Function other) {
     * 
     * if (other instanceof PowerFunction) {
     * double otherPower = ((PowerFunction) other).power;
     * return PowerFunction.of(power - otherPower);
     * }
     * 
     * return super.div(other);
     * }
     * 
     * @Override
     * public Function compose(Function inner) {
     * 
     * // The identity function composing any function will give us the identity
     * // function
     * if (power == 1)
     * return inner;
     * 
     * // apply (x^a)^b=x^(ab)
     * if (inner instanceof PowerFunction) {
     * double innerPower = ((PowerFunction) inner).power;
     * return PowerFunction.of(power * innerPower);
     * }
     * 
     * // apply (af)^n=a^nf^n
     * double scalar = FunctionVector.getScalar(inner);
     * if (scalar != 1)
     * return FunctionVector.scale(compose(FunctionVector.scale(inner, 1.0 /
     * scalar)), Math.pow(scalar, power));
     * 
     * // apply (a*b)^n=a^n*b^n
     * if (inner instanceof Product) {
     * Product prod = (Product) inner;
     * return compose(prod.getLeft()).times(compose(prod.getRight()));
     * }
     * 
     * // apply (a/b)^n = a^n / b^n
     * if (inner instanceof Quotient) {
     * Quotient quo = (Quotient) inner;
     * return compose(quo.getLeft()).div(compose(quo.getRight()));
     * }
     * 
     * return super.compose(inner);
     * }
     */

}
