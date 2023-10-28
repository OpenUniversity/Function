package function.arithmetics;

import function.Constant;
import function.Function;
import function.elementary.LogarithmicFunction;

/**
 * Represents the function left^right
 */
public class Power extends FunctionArithmetic {

    public static Function inverse(Function other) {
        return other.pow(Constant.of(-1));
    }

    public Power(Function left, Function right) {
        super(left, right);
    }

    @Override
    public double evaluate(double x) throws ArithmeticException {
        double leftVal = left.evaluate(x);
        double rightVal = right.evaluate(x);
        if (leftVal == 0 && rightVal < 0)
            throw new ArithmeticException("Can't divide by 0");
        return Math.pow(leftVal, rightVal);
    }

    @Override
    public Function derive() {
        // f^g is equivalent to e^(ln(f)*g)
        // deriving ln(f)*g gives us (f'g/f + ln(f)*g')
        // Therefore, the derivative would be (f'g/f + ln(f)*g') * e^(ln(f) * g), which
        // is equivalent to (f'g/f + ln(f)*g') * (f^g)
        Function innerDerivative = left.derive().times(right).div(left)
                .plus(right.derive().times(new LogarithmicFunction().compose(left)));
        return innerDerivative.times(this);
    }

    @Override
    public String substitute(String x) {
        return left.substitute(x, true) + "^" + right.substitute(x, true);
    }

    @Override
    public Function times(Function other) {
        if (other instanceof Power) {
            Power pow = (Power) other;

            // apply rule #1: a^n * a^m = a^(n+m)
            if (left.equals(pow.left))
                return left.pow(right.plus(pow.right));

            // don't apply rule #2: a^n * b^n = (ab)^n, unless simpler:
            if (right.equals(pow.right) && !(left.times(pow.left) instanceof Product))
                return left.times(pow.left).pow(right);

        }

        // apply rule #1 again:
        if (left.equals(other))
            return times(new Power(other, Constant.of(1)));

        return super.times(other);
    }

    @Override
    public Function div(Function other) {

        if (other instanceof Power) {
            Power pow = (Power) other;

            // apply rule #1: a^n / a^m = a^(n-m)
            if (left.equals(pow.left))
                return left.pow(right.minus(pow.right));

            // don't apply rule #2: a^n / b^n = (a/b)^n, unless simpler:
            if (right.equals(pow.right) && !(left.div(pow.left) instanceof Quotient))
                return left.div(pow.left).pow(right);

        }

        // apply rule #1 again:
        if (left.equals(other))
            return div(new Power(other, Constant.of(1)));

        return super.div(other);
    }

    @Override
    public Function compose(Function inner) {
        return left.compose(inner).pow(right.compose(inner));
    }

    @Override
    public Function pow(Function exponent) {
        return left.pow(right.times(exponent)); // apply rule (a^b)^c = a^(bc)
    }

}
